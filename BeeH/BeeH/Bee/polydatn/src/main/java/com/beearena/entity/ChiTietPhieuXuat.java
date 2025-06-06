package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ChiTietPhieuXuat")
public class ChiTietPhieuXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaCTPX")
    private Long maCTPX;

    @ManyToOne
    @JoinColumn(name = "MaPX")
    private PhieuXuat phieuXuat;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "DonGia", nullable = false)
    private BigDecimal donGia;

    @Column(name = "ThanhTien", nullable = false)
    private BigDecimal thanhTien;

    public Long getMaCTPX() { return maCTPX; }
    public void setMaCTPX(Long maCTPX) { this.maCTPX = maCTPX; }
    public PhieuXuat getPhieuXuat() { return phieuXuat; }
    public void setPhieuXuat(PhieuXuat phieuXuat) { this.phieuXuat = phieuXuat; }
    public SanPham getSanPham() { return sanPham; }
    public void setSanPham(SanPham sanPham) { this.sanPham = sanPham; }
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    public BigDecimal getThanhTien() { return thanhTien; }
    public void setThanhTien(BigDecimal thanhTien) { this.thanhTien = thanhTien; }
} 