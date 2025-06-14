package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "PhieuXuat")
public class PhieuXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPX")
    private Long maPX;

    @Column(name = "NgayXuat")
    private LocalDateTime ngayXuat;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "phieuXuat", cascade = CascadeType.ALL)
    private List<ChiTietPhieuXuat> danhSachChiTietPhieuXuat;

    public LocalDateTime getNgayXuat() { return ngayXuat; }
    public void setNgayXuat(LocalDateTime ngayXuat) { this.ngayXuat = ngayXuat; }
    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }
    public java.util.List<ChiTietPhieuXuat> getDanhSachChiTietPhieuXuat() { return danhSachChiTietPhieuXuat; }
    public void setDanhSachChiTietPhieuXuat(java.util.List<ChiTietPhieuXuat> danhSachChiTietPhieuXuat) { this.danhSachChiTietPhieuXuat = danhSachChiTietPhieuXuat; }
} 