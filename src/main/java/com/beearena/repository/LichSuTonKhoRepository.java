package com.beearena.repository;

import com.beearena.entity.LichSuTonKho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LichSuTonKhoRepository extends JpaRepository<LichSuTonKho, Long> {
    List<LichSuTonKho> findBySanPham_MaSP(Long maSP);
    List<LichSuTonKho> findByLoaiPhatSinh(String loaiPhatSinh);
    List<LichSuTonKho> findByNgayPhatSinhBetween(LocalDateTime tuNgay, LocalDateTime denNgay);
} 