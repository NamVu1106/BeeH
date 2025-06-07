package com.beearena.repository;

import com.beearena.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {
    DanhMuc findByTenDM(String tenDM);
    boolean existsByTenDM(String tenDM);
} 