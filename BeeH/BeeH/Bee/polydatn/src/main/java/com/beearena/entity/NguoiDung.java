package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaND")
    private Long id;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "DiaChi")
    private String diaChi;

    @OneToMany(mappedBy = "nguoiDung")
    private List<DonHang> danhSachDonHang;

    @Column(name = "ConHoatDong", nullable = false)
    private Boolean conHoatDong = true;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat;

    @Enumerated(EnumType.STRING)
    @Column(name = "VaiTro", nullable = false)
    private VaiTro vaiTro = VaiTro.NGUOI_DUNG;

    @PrePersist
    protected void khiTao() {
        ngayTao = LocalDateTime.now();
    }

    @PreUpdate
    protected void khiCapNhat() {
        ngayCapNhat = LocalDateTime.now();
    }
} 