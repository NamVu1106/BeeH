package com.beearena.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DonHangDTO {
    private Long id;
    private Long nguoiDungId;
    private String hoTenNguoiDung;
    private String diaChiGiaoHang;
    private String soDienThoaiGiaoHang;
    private BigDecimal tongTien;
    private String trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngayCapNhat;
    private List<ChiTietDonHangDTO> chiTietDonHang;
} 