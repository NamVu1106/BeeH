package com.beearena.service;

import com.beearena.entity.LichSuTonKho;
import com.beearena.repository.LichSuTonKhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LichSuTonKhoService {
    @Autowired
    private LichSuTonKhoRepository lichSuTonKhoRepository;

    public List<LichSuTonKho> layTatCaLichSu() {
        return lichSuTonKhoRepository.findAll();
    }

    public List<LichSuTonKho> layTheoSanPham(Long maSP) {
        return lichSuTonKhoRepository.findBySanPham_MaSP(maSP);
    }

    public List<LichSuTonKho> layTheoLoaiPhatSinh(String loaiPhatSinh) {
        return lichSuTonKhoRepository.findByLoaiPhatSinh(loaiPhatSinh);
    }

    public List<LichSuTonKho> layTheoKhoangThoiGian(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return lichSuTonKhoRepository.findByNgayPhatSinhBetween(tuNgay, denNgay);
    }

    public LichSuTonKho themLichSu(LichSuTonKho lichSu) {
        lichSu.setNgayPhatSinh(LocalDateTime.now());
        return lichSuTonKhoRepository.save(lichSu);
    }
} 