package com.beearena.repository;

import com.beearena.entity.DonHang;
import com.beearena.enums.TrangThaiDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByNguoiDungId(Long nguoiDungId);
    List<DonHang> findByTrangThai(TrangThaiDonHang trangThai);
    List<DonHang> findByNguoiDungIdAndTrangThai(Long nguoiDungId, TrangThaiDonHang trangThai);
} 