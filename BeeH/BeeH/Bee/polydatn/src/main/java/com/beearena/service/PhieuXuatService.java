package com.beearena.service;

import com.beearena.entity.PhieuXuat;
import com.beearena.entity.ChiTietPhieuXuat;
import com.beearena.entity.SanPham;
import com.beearena.entity.LichSuTonKho;
import com.beearena.repository.PhieuXuatRepository;

import com.beearena.repository.SanPhamRepository;
import com.beearena.repository.LichSuTonKhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuXuatService {
    @Autowired
    private PhieuXuatRepository phieuXuatRepository;
    
 
    
    @Autowired
    private SanPhamRepository sanPhamRepository;
    
    @Autowired
    private LichSuTonKhoRepository lichSuTonKhoRepository;

    public List<PhieuXuat> layTatCaPhieuXuat() {
        return phieuXuatRepository.findAll();
    }

    public Optional<PhieuXuat> layPhieuXuatTheoId(Long maPX) {
        return phieuXuatRepository.findById(maPX);
    }

    public List<PhieuXuat> layTheoKhoangThoiGian(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return phieuXuatRepository.findByNgayXuatBetween(tuNgay, denNgay);
    }

    public List<PhieuXuat> layTheoTrangThai(boolean trangThai) {
        return phieuXuatRepository.findByTrangThai(trangThai);
    }

    @Transactional
    public PhieuXuat taoPhieuXuat(PhieuXuat phieuXuat) {
        phieuXuat.setNgayXuat(LocalDateTime.now());
        phieuXuat.setTrangThai(true);
        
        // Tính tổng tiền
        BigDecimal tongTien = BigDecimal.ZERO;
        for (ChiTietPhieuXuat chiTiet : phieuXuat.getDanhSachChiTietPhieuXuat()) {
            chiTiet.setPhieuXuat(phieuXuat);
            chiTiet.setThanhTien(chiTiet.getDonGia().multiply(new BigDecimal(chiTiet.getSoLuong())));
            tongTien = tongTien.add(chiTiet.getThanhTien());
            
            // Cập nhật số lượng sản phẩm
            SanPham sanPham = chiTiet.getSanPham();
            int soLuongCu = sanPham.getSoLuong();
            if (soLuongCu < chiTiet.getSoLuong()) {
                throw new RuntimeException("Số lượng sản phẩm không đủ");
            }
            sanPham.setSoLuong(soLuongCu - chiTiet.getSoLuong());
            sanPhamRepository.save(sanPham);
            
            // Ghi lịch sử tồn kho
            LichSuTonKho lichSu = new LichSuTonKho();
            lichSu.setSanPham(sanPham);
            lichSu.setLoaiPhatSinh("XUAT");
            lichSu.setSoLuong(chiTiet.getSoLuong());
            lichSu.setTonKhoTruoc(soLuongCu);
            lichSu.setTonKhoSau(sanPham.getSoLuong());
            lichSu.setNgayPhatSinh(LocalDateTime.now());
            lichSu.setGhiChu("Xuất hàng từ phiếu xuất " + phieuXuat.getMaPX());
            lichSuTonKhoRepository.save(lichSu);
        }
        
        phieuXuat.setTongTien(tongTien);
        return phieuXuatRepository.save(phieuXuat);
    }

    @Transactional
    public void xoaPhieuXuat(Long maPX) {
        PhieuXuat phieuXuat = phieuXuatRepository.findById(maPX)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu xuất"));
            
        // Hoàn trả số lượng sản phẩm
        for (ChiTietPhieuXuat chiTiet : phieuXuat.getDanhSachChiTietPhieuXuat()) {
            SanPham sanPham = chiTiet.getSanPham();
            sanPham.setSoLuong(sanPham.getSoLuong() + chiTiet.getSoLuong());
            sanPhamRepository.save(sanPham);
        }
        
        phieuXuatRepository.deleteById(maPX);
    }
} 