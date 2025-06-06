package com.beearena.repository;

import com.beearena.entity.ChiTietPhieuXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietPhieuXuatRepository extends JpaRepository<ChiTietPhieuXuat, Long> {
    List<ChiTietPhieuXuat> findByPhieuXuat_MaPX(Long maPX);
    List<ChiTietPhieuXuat> findBySanPham_MaSP(Long maSP);
} 