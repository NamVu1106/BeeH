package com.beearena.repository;

import com.beearena.entity.PhieuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, Long> {
    List<PhieuNhap> findByNhaCungCap_MaNCC(Long maNCC);
    List<PhieuNhap> findByNgayNhapBetween(LocalDateTime tuNgay, LocalDateTime denNgay);
    List<PhieuNhap> findByTrangThai(boolean trangThai);
} 