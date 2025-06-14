package com.beearena.repository;

import com.beearena.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {
    List<NhaCungCap> findByTenNCCContaining(String tenNCC);
    List<NhaCungCap> findByTrangThai(boolean trangThai);
    List<NhaCungCap> findByEmailContaining(String email);
    List<NhaCungCap> findBySdtContaining(String sdt);
    boolean existsByEmail(String email);
    boolean existsBySdt(String sdt);
} 