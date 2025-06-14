package com.beearena.service;

import com.beearena.entity.DonHang;
import com.beearena.enums.TrangThaiDonHang;
import com.beearena.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DonHangService {
    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> layTatCaDonHang() {
        return donHangRepository.findAll();
    }

    public Optional<DonHang> layDonHangTheoId(Long id) {
        return donHangRepository.findById(id);
    }

    public List<DonHang> layDonHangTheoNguoiDung(Long nguoiDungId) {
        return donHangRepository.findByNguoiDungId(nguoiDungId);
    }

    public List<DonHang> layDonHangTheoTrangThai(TrangThaiDonHang trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }

    public DonHang taoDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    public DonHang capNhatTrangThaiDonHang(Long id, TrangThaiDonHang trangThai) {
        Optional<DonHang> donHangOpt = donHangRepository.findById(id);
        if (donHangOpt.isPresent()) {
            DonHang donHang = donHangOpt.get();
            donHang.setTrangThai(trangThai);
            return donHangRepository.save(donHang);
        }
        return null;
    }

    public void xoaDonHang(Long id) {
        donHangRepository.deleteById(id);
    }

    public int countDonHangMoi() {
        // TODO: implement logic
        return 0;
    }

    public int countDonHangChoGiao() {
        // TODO: implement logic
        return 0;
    }

    public List<Map<String, Object>> getDonHangGanDay() {
        // TODO: implement logic
        return List.of();
    }

    public List<Map<String, Object>> getAllDonHang() {
        // TODO: implement logic
        return List.of();
    }

    public void xacNhanDonHang(String maDon) {
        // TODO: implement logic
    }

    public void danhDauSanSangGiao(String maDon) {
        // TODO: implement logic
    }

    public Map<String, Object> getChiTietDonHang(String maDon) {
        // TODO: implement logic
        return Map.of();
    }

    public void taoDonHang(Map<String, Object> request) {
        // TODO: implement logic
    }

    public org.springframework.data.domain.Page<DonHang> layTatCaDonHangPhanTrang(int page, int pageSize) {
        return donHangRepository.findAll(org.springframework.data.domain.PageRequest.of(page, pageSize));
    }

    public org.springframework.data.domain.Page<DonHang> locDonHang(int page, int pageSize, String keyword, String trangThai, java.time.LocalDate ngayTu, java.time.LocalDate ngayDen, Long tongTienTu, Long tongTienDen) {
        org.springframework.data.jpa.domain.Specification<DonHang> spec = (root, query, cb) -> {
            java.util.List<jakarta.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
            if (keyword != null && !keyword.isEmpty()) {
                jakarta.persistence.criteria.Expression<String> hoTen = root.get("hoTen");
                jakarta.persistence.criteria.Expression<String> maDH = cb.concat("", root.get("maDH").as(String.class));
                predicates.add(cb.or(
                    cb.like(cb.lower(hoTen), "%" + keyword.toLowerCase() + "%"),
                    cb.like(maDH, "%" + keyword + "%")
                ));
            }
            if (trangThai != null && !trangThai.isEmpty()) {
                predicates.add(cb.equal(root.get("trangThai"), trangThai));
            }
            if (ngayTu != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("ngayDat").as(java.time.LocalDate.class), ngayTu));
            }
            if (ngayDen != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("ngayDat").as(java.time.LocalDate.class), ngayDen));
            }
            if (tongTienTu != null) {
                predicates.add(cb.ge(root.get("tongTien"), new java.math.BigDecimal(tongTienTu)));
            }
            if (tongTienDen != null) {
                predicates.add(cb.le(root.get("tongTien"), new java.math.BigDecimal(tongTienDen)));
            }
            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
        return donHangRepository.findAll(spec, org.springframework.data.domain.PageRequest.of(page, pageSize));
    }

    public long demTatCaDonHang() {
        return donHangRepository.count();
    }

    public long demDonHangTheoTrangThai(TrangThaiDonHang trangThai) {
        return donHangRepository.count((root, query, cb) -> cb.equal(root.get("trangThai"), trangThai));
    }

    public java.math.BigDecimal tongDoanhThu() {
        java.math.BigDecimal result = donHangRepository.sumTongTienTheoTrangThai(TrangThaiDonHang.DA_GIAO);
        return result != null ? result : java.math.BigDecimal.ZERO;
    }
} 