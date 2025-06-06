package com.beearena.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNCC")
    private Long maNCC;

    @Column(name = "TenNCC", nullable = false)
    private String tenNCC;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "Email")
    private String email;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToMany(mappedBy = "nhaCungCap")
    private List<PhieuNhap> danhSachPhieuNhap;

    public Long getMaNCC() { return maNCC; }
    public void setMaNCC(Long maNCC) { this.maNCC = maNCC; }
    public String getTenNCC() { return tenNCC; }
    public void setTenNCC(String tenNCC) { this.tenNCC = tenNCC; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public java.time.LocalDateTime getNgayTao() { return ngayTao; }
    public void setNgayTao(java.time.LocalDateTime ngayTao) { this.ngayTao = ngayTao; }
    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }
    public java.util.List<PhieuNhap> getDanhSachPhieuNhap() { return danhSachPhieuNhap; }
    public void setDanhSachPhieuNhap(java.util.List<PhieuNhap> danhSachPhieuNhap) { this.danhSachPhieuNhap = danhSachPhieuNhap; }
} 