package com.beearena.service;

import com.beearena.entity.PhieuNhap;
import com.beearena.entity.ChiTietPhieuNhap;
import com.beearena.entity.SanPham;
import com.beearena.entity.LichSuTonKho;
import com.beearena.repository.PhieuNhapRepository;

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
public class PhieuNhapService {
    @Autowired
    private PhieuNhapRepository phieuNhapRepository;
    
  
    
    @Autowired
    private SanPhamRepository sanPhamRepository;
    
    @Autowired
    private LichSuTonKhoRepository lichSuTonKhoRepository;

    public List<PhieuNhap> layTatCaPhieuNhap() {
        return phieuNhapRepository.findAll();
    }

    public Optional<PhieuNhap> layPhieuNhapTheoId(Long maPN) {
        return phieuNhapRepository.findById(maPN);
    }

    public List<PhieuNhap> layTheoNhaCungCap(Long maNCC) {
        return phieuNhapRepository.findByNhaCungCap_MaNCC(maNCC);
    }

    public List<PhieuNhap> layTheoKhoangThoiGian(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return phieuNhapRepository.findByNgayNhapBetween(tuNgay, denNgay);
    }

    public List<PhieuNhap> layTheoTrangThai(boolean trangThai) {
        return phieuNhapRepository.findByTrangThai(trangThai);
    }

    @Transactional
    public PhieuNhap taoPhieuNhap(PhieuNhap phieuNhap) {
        phieuNhap.setNgayNhap(LocalDateTime.now());
        phieuNhap.setTrangThai(true);
        
        // Tính tổng tiền
        BigDecimal tongTien = BigDecimal.ZERO;
        for (ChiTietPhieuNhap chiTiet : phieuNhap.getDanhSachChiTietPhieuNhap()) {
            chiTiet.setPhieuNhap(phieuNhap);
            chiTiet.setThanhTien(chiTiet.getDonGia().multiply(new BigDecimal(chiTiet.getSoLuong())));
            tongTien = tongTien.add(chiTiet.getThanhTien());
            
            // Cập nhật số lượng sản phẩm
            SanPham sanPham = chiTiet.getSanPham();
            int soLuongCu = sanPham.getSoLuong();
            sanPham.setSoLuong(soLuongCu + chiTiet.getSoLuong());
            sanPhamRepository.save(sanPham);
            
            // Ghi lịch sử tồn kho
            LichSuTonKho lichSu = new LichSuTonKho();
            lichSu.setSanPham(sanPham);
            lichSu.setLoaiPhatSinh("NHAP");
            lichSu.setSoLuong(chiTiet.getSoLuong());
            lichSu.setTonKhoTruoc(soLuongCu);
            lichSu.setTonKhoSau(sanPham.getSoLuong());
            lichSu.setNgayPhatSinh(LocalDateTime.now());
            lichSu.setGhiChu("Nhập hàng từ phiếu nhập " + phieuNhap.getMaPN());
            lichSuTonKhoRepository.save(lichSu);
        }
        
        phieuNhap.setTongTien(tongTien);
        return phieuNhapRepository.save(phieuNhap);
    }

    @Transactional
    public void xoaPhieuNhap(Long maPN) {
        PhieuNhap phieuNhap = phieuNhapRepository.findById(maPN)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu nhập"));
            
        // Hoàn trả số lượng sản phẩm
        for (ChiTietPhieuNhap chiTiet : phieuNhap.getDanhSachChiTietPhieuNhap()) {
            SanPham sanPham = chiTiet.getSanPham();
            sanPham.setSoLuong(sanPham.getSoLuong() - chiTiet.getSoLuong());
            sanPhamRepository.save(sanPham);
        }
        
        phieuNhapRepository.deleteById(maPN);
    }
} 