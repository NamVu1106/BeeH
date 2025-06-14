package com.beearena.service;

import com.beearena.entity.SanPham;
import com.beearena.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    public Page<SanPham> layTatCaSanPham(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }

    public Optional<SanPham> laySanPhamTheoId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        try {
            return sanPhamRepository.findById(id.intValue());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<SanPham> timSanPhamTheoTen(String keyword) {
        return sanPhamRepository.findByTenSPContainingIgnoreCase(keyword);
    }

    

    public List<SanPham> laySanPhamDangHoatDong() {
        return sanPhamRepository.findByTrangThai(true);
    }

    public SanPham luuSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    public void xoaSanPham(Long id) {
        if (id != null) {
            try {
                sanPhamRepository.deleteById(id.intValue());
            } catch (Exception e) {
                // Log error or handle exception
            }
        }
    }

    public Page<SanPham> timKiemNangCao(String keyword, Integer maDM, BigDecimal giaTu, BigDecimal giaDen, Integer danhGiaTu, String nhan, Pageable pageable) {
        Specification<SanPham> spec = Specification.where(null);
        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("tenSP")), "%" + keyword.toLowerCase() + "%"));
        }
        if (maDM != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("maDM"), maDM));
        }
        if (giaTu != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("gia"), giaTu));
        }
        if (giaDen != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("gia"), giaDen));
        }
        // Bổ sung các điều kiện khác nếu có (danhGiaTu, nhan...)
        return sanPhamRepository.findAll(spec, pageable);
    }

    public int countSanPhamSapHet() {
        // TODO: implement logic
        return 0;
    }

    public List<Map<String, Object>> getSanPhamBanChay() {
        // TODO: implement logic
        return List.of();
    }

    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    public Map<String, Object> getChiTietSanPham(Long id) {
        // TODO: implement logic
        return Map.of();
    }

    public void nhapHang(Long id, int soLuong) {
        // TODO: implement logic
    }

    public List<SanPham> timSanPhamKhongDau(String keyword) {
        return sanPhamRepository.searchIgnoreAccent(keyword);
    }

    public List<SanPham> timSanPhamCoDau(String keyword) {
        return sanPhamRepository.searchWithAccent(keyword);
    }

    public org.springframework.data.domain.Page<SanPham> locSanPham(int page, int pageSize, String keyword, Long danhMucId, Boolean trangThai, Long giaTu, Long giaDen) {
        org.springframework.data.jpa.domain.Specification<SanPham> spec = (root, query, cb) -> {
            java.util.List<jakarta.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("tenSP")), "%" + keyword.toLowerCase() + "%"));
            }
            if (danhMucId != null) {
                predicates.add(cb.equal(root.get("danhMuc").get("maDM"), danhMucId));
            }
            if (trangThai != null) {
                predicates.add(cb.equal(root.get("trangThai"), trangThai));
            }
            if (giaTu != null) {
                predicates.add(cb.ge(root.get("gia"), new java.math.BigDecimal(giaTu)));
            }
            if (giaDen != null) {
                predicates.add(cb.le(root.get("gia"), new java.math.BigDecimal(giaDen)));
            }
            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
        return sanPhamRepository.findAll(spec, org.springframework.data.domain.PageRequest.of(page, pageSize));
    }

    public long demTatCaSanPham() {
        return sanPhamRepository.count();
    }

    public long demSanPhamHienThi() {
        return sanPhamRepository.count((root, query, cb) -> cb.equal(root.get("trangThai"), true));
    }

    public long demSanPhamAn() {
        return sanPhamRepository.count((root, query, cb) -> cb.equal(root.get("trangThai"), false));
    }

    public long tongSoLuongTonKho() {
        return sanPhamRepository.sumSoLuongTonKho();
    }

    public java.math.BigDecimal tongGiaTriTonKho() {
        java.math.BigDecimal result = sanPhamRepository.sumGiaTriTonKho();
        return result != null ? result : java.math.BigDecimal.ZERO;
    }
} 