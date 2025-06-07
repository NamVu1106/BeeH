package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPN")
    private Long maPN;

    @ManyToOne
    @JoinColumn(name = "MaNCC")
    private NhaCungCap nhaCungCap;

    @Column(name = "NgayNhap")
    private LocalDateTime ngayNhap;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "phieuNhap", cascade = CascadeType.ALL)
    private List<ChiTietPhieuNhap> danhSachChiTietPhieuNhap;

    public LocalDateTime getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(LocalDateTime ngayNhap) { this.ngayNhap = ngayNhap; }
    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }
    public java.util.List<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() { return danhSachChiTietPhieuNhap; }
    public void setDanhSachChiTietPhieuNhap(java.util.List<ChiTietPhieuNhap> danhSachChiTietPhieuNhap) { this.danhSachChiTietPhieuNhap = danhSachChiTietPhieuNhap; }
} 