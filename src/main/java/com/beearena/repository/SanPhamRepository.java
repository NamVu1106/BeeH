package com.beearena.repository;

import com.beearena.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer>, JpaSpecificationExecutor<SanPham> {
    java.util.List<SanPham> findByTenSPContaining(String tenSP);
    java.util.List<SanPham> findByTrangThai(Boolean trangThai);
    java.util.List<SanPham> findByTenSPContainingIgnoreCase(String tenSP);

    // Tìm kiếm không dấu (accent-insensitive)
    @Query(value = "SELECT * FROM SanPham WHERE LOWER(TenSP) COLLATE Vietnamese_CI_AI LIKE LOWER(CONCAT('%', :keyword, '%')) COLLATE Vietnamese_CI_AI", nativeQuery = true)
    java.util.List<SanPham> searchIgnoreAccent(@Param("keyword") String keyword);

    // Tìm kiếm có dấu (accent-sensitive)
    @Query(value = "SELECT * FROM SanPham WHERE LOWER(TenSP) COLLATE Vietnamese_CI_AS LIKE LOWER(CONCAT('%', :keyword, '%')) COLLATE Vietnamese_CI_AS", nativeQuery = true)
    java.util.List<SanPham> searchWithAccent(@Param("keyword") String keyword);

    @Query("SELECT SUM(s.soLuong) FROM SanPham s")
    Long sumSoLuongTonKho();

    @Query("SELECT SUM(s.soLuong * s.gia) FROM SanPham s")
    java.math.BigDecimal sumGiaTriTonKho();
} 