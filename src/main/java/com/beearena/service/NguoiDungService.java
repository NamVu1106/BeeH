package com.beearena.service;

import com.beearena.entity.NguoiDung;
import com.beearena.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> layTatCaNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    public Optional<NguoiDung> layNguoiDungTheoId(Long id) {
        return nguoiDungRepository.findById(id);
    }

    public Optional<NguoiDung> layNguoiDungTheoEmail(String email) {
        return nguoiDungRepository.findByEmail(email);
    }

    public boolean kiemTraEmailTonTai(String email) {
        return nguoiDungRepository.existsByEmail(email);
    }

    public NguoiDung dangKyNguoiDung(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung capNhatNguoiDung(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    public void xoaNguoiDung(Long id) {
        nguoiDungRepository.deleteById(id);
    }

    public Optional<NguoiDung> dangNhap(String email, String matKhau) {
        Optional<NguoiDung> userOpt = nguoiDungRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getMatKhau().equals(matKhau)) {
            return userOpt;
        }
        return Optional.empty();
    }

    public java.util.List<NguoiDung> layNguoiDungTheoVaiTro(com.beearena.entity.VaiTro vaiTro) {
        return nguoiDungRepository.findByVaiTro(vaiTro);
    }
} 