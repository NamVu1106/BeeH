package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "DanhMuc")
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDM")
    private Long maDM;

    @Column(name = "TenDM", nullable = false)
    private String tenDM;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "danhMuc")
    private List<SanPham> danhSachSanPham;
} 