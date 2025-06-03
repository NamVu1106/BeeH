package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String matKhau;

    @Column(nullable = false)
    private String hoTen;

    private String soDienThoai;

    private String diaChi;

    @OneToMany(mappedBy = "nguoiDung")
    private List<DonHang> danhSachDonHang;

    @Column(nullable = false)
    private Boolean conHoatDong = true;

    @Column(nullable = false)
    private LocalDateTime ngayTao;

    private LocalDateTime ngayCapNhat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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