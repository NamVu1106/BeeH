package com.beearena.entity;

import com.beearena.enums.TrangThaiDonHang;
import com.beearena.enums.PhuongThucThanhToan;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", nullable = false)
    private NguoiDung nguoiDung;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> danhSachChiTiet;

    @Column(nullable = false)
    private BigDecimal tongTien;

    @Column(nullable = false)
    private String diaChiGiaoHang;

    @Column(nullable = false)
    private String soDienThoai;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrangThaiDonHang trangThai = TrangThaiDonHang.CHO_XAC_NHAN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhuongThucThanhToan phuongThucThanhToan;

    @Column(nullable = false)
    private LocalDateTime ngayTao;

    private LocalDateTime ngayCapNhat;

    @PrePersist
    protected void khiTao() {
        ngayTao = LocalDateTime.now();
    }

    @PreUpdate
    protected void khiCapNhat() {
        ngayCapNhat = LocalDateTime.now();
    }

    public void setTrangThai(TrangThaiDonHang trangThai) {
        this.trangThai = trangThai;
    }
} 