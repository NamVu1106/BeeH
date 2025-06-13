package com.beearena.controller;

import com.beearena.entity.NguoiDung;
import com.beearena.entity.VaiTro;
import com.beearena.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/nhan-vien")
public class NhanVienRestController {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @GetMapping
    public List<NguoiDung> getAllNhanVien() {
        return nguoiDungRepository.findByVaiTro(VaiTro.NHAN_VIEN);
    }

    @PostMapping
    public NguoiDung addNhanVien(@RequestBody NguoiDung nv) {
        nv.setVaiTro(VaiTro.NHAN_VIEN);
        return nguoiDungRepository.save(nv);
    }

    @PutMapping("/{id}")
    public NguoiDung updateNhanVien(@PathVariable Long id, @RequestBody NguoiDung nv) {
        NguoiDung old = nguoiDungRepository.findById(id).orElseThrow();
        old.setHoTen(nv.getHoTen());
        old.setEmail(nv.getEmail());
        old.setSoDienThoai(nv.getSoDienThoai());
        old.setDiaChi(nv.getDiaChi());
        // Không cho sửa vai trò ở đây
        return nguoiDungRepository.save(old);
    }

    @DeleteMapping("/{id}")
    public void deleteNhanVien(@PathVariable Long id) {
        nguoiDungRepository.deleteById(id);
    }
} 