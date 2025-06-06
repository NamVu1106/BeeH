package com.beearena.repository;

import com.beearena.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer>, JpaSpecificationExecutor<SanPham> {
    java.util.List<SanPham> findByTenSPContaining(String tenSP);
    java.util.List<SanPham> findByDanhMuc_MaDM(Long maDM);
    java.util.List<SanPham> findByTrangThai(Boolean trangThai);
} 