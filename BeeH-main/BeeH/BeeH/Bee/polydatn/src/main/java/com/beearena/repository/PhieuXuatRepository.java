package com.beearena.repository;

import com.beearena.entity.PhieuXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhieuXuatRepository extends JpaRepository<PhieuXuat, Long> {
    List<PhieuXuat> findByNgayXuatBetween(LocalDateTime tuNgay, LocalDateTime denNgay);
    List<PhieuXuat> findByTrangThai(boolean trangThai);
} 