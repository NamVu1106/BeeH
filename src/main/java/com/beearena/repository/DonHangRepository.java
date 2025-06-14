package com.beearena.repository;

import com.beearena.entity.DonHang;
import com.beearena.enums.TrangThaiDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long>, JpaSpecificationExecutor<DonHang> {
    List<DonHang> findByNguoiDungId(Long nguoiDungId);
    List<DonHang> findByTrangThai(TrangThaiDonHang trangThai);
    List<DonHang> findByNguoiDungIdAndTrangThai(Long nguoiDungId, TrangThaiDonHang trangThai);

    @Query("SELECT SUM(d.tongTien) FROM DonHang d WHERE d.trangThai = :trangThai")
    BigDecimal sumTongTienTheoTrangThai(@Param("trangThai") TrangThaiDonHang trangThai);
} 