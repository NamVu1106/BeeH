package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LichSuTonKho")
public class LichSuTonKho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLS")
    private Long maLS;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;

    @Column(name = "LoaiPhatSinh", nullable = false)
    private String loaiPhatSinh;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "TonKhoTruoc", nullable = false)
    private Integer tonKhoTruoc;

    @Column(name = "TonKhoSau", nullable = false)
    private Integer tonKhoSau;

    @Column(name = "NgayPhatSinh")
    private LocalDateTime ngayPhatSinh;

    @Column(name = "GhiChu")
    private String ghiChu;

    public SanPham getSanPham() { return sanPham; }
    public void setSanPham(SanPham sanPham) { this.sanPham = sanPham; }
    public String getLoaiPhatSinh() { return loaiPhatSinh; }
    public void setLoaiPhatSinh(String loaiPhatSinh) { this.loaiPhatSinh = loaiPhatSinh; }
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
    public Integer getTonKhoTruoc() { return tonKhoTruoc; }
    public void setTonKhoTruoc(Integer tonKhoTruoc) { this.tonKhoTruoc = tonKhoTruoc; }
    public Integer getTonKhoSau() { return tonKhoSau; }
    public void setTonKhoSau(Integer tonKhoSau) { this.tonKhoSau = tonKhoSau; }
    public java.time.LocalDateTime getNgayPhatSinh() { return ngayPhatSinh; }
    public void setNgayPhatSinh(java.time.LocalDateTime ngayPhatSinh) { this.ngayPhatSinh = ngayPhatSinh; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
} 