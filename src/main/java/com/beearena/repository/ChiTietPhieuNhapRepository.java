package com.beearena.repository;

import com.beearena.entity.ChiTietPhieuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietPhieuNhapRepository extends JpaRepository<ChiTietPhieuNhap, Long> {
    List<ChiTietPhieuNhap> findByPhieuNhap_MaPN(Long maPN);
    List<ChiTietPhieuNhap> findBySanPham_MaSP(Long maSP);
} 