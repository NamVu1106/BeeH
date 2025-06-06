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
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    public Page<SanPham> layTatCaSanPham(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }

    public Optional<SanPham> laySanPhamTheoId(Long id) {
        return sanPhamRepository.findById(id != null ? id.intValue() : null);
    }

    public List<SanPham> timSanPhamTheoTen(String ten) {
        return sanPhamRepository.findByTenSPContaining(ten);
    }

    public List<SanPham> laySanPhamTheoDanhMuc(Long maDM) {
        return sanPhamRepository.findByDanhMuc_MaDM(maDM);
    }

    public List<SanPham> laySanPhamDangHoatDong() {
        return sanPhamRepository.findByTrangThai(true);
    }

    public SanPham luuSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    public void xoaSanPham(Long id) {
        sanPhamRepository.deleteById(id != null ? id.intValue() : null);
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
} 