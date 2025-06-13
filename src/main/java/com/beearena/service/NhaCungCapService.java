package com.beearena.service;

import com.beearena.entity.NhaCungCap;
import com.beearena.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NhaCungCapService {
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    public List<NhaCungCap> layTatCaNhaCungCap() {
        return nhaCungCapRepository.findAll();
    }

    public Optional<NhaCungCap> layNhaCungCapTheoId(Long maNCC) {
        return nhaCungCapRepository.findById(maNCC);
    }

    public List<NhaCungCap> timKiemTheoTen(String tenNCC) {
        return nhaCungCapRepository.findByTenNCCContaining(tenNCC);
    }

    public List<NhaCungCap> layTheoTrangThai(boolean trangThai) {
        return nhaCungCapRepository.findByTrangThai(trangThai);
    }

    public boolean kiemTraEmailTonTai(String email) {
        return nhaCungCapRepository.existsByEmail(email);
    }

    public boolean kiemTraSDTTonTai(String sdt) {
        return nhaCungCapRepository.existsBySdt(sdt);
    }

    public NhaCungCap themNhaCungCap(NhaCungCap nhaCungCap) {
        nhaCungCap.setNgayTao(LocalDateTime.now());
        return nhaCungCapRepository.save(nhaCungCap);
    }

    public NhaCungCap capNhatNhaCungCap(NhaCungCap nhaCungCap) {
        return nhaCungCapRepository.save(nhaCungCap);
    }

    public void xoaNhaCungCap(Long maNCC) {
        nhaCungCapRepository.deleteById(maNCC);
    }
} 