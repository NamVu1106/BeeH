package com.beearena.service;

import com.beearena.entity.DonHang;
import com.beearena.enums.TrangThaiDonHang;
import com.beearena.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {
    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> layTatCaDonHang() {
        return donHangRepository.findAll();
    }

    public Optional<DonHang> layDonHangTheoId(Long id) {
        return donHangRepository.findById(id);
    }

    public List<DonHang> layDonHangTheoNguoiDung(Long nguoiDungId) {
        return donHangRepository.findByNguoiDungId(nguoiDungId);
    }

    public List<DonHang> layDonHangTheoTrangThai(TrangThaiDonHang trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }

    public DonHang taoDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    public DonHang capNhatTrangThaiDonHang(Long id, TrangThaiDonHang trangThai) {
        Optional<DonHang> donHangOpt = donHangRepository.findById(id);
        if (donHangOpt.isPresent()) {
            DonHang donHang = donHangOpt.get();
            donHang.setTrangThai(trangThai);
            return donHangRepository.save(donHang);
        }
        return null;
    }

    public void xoaDonHang(Long id) {
        donHangRepository.deleteById(id);
    }
} 