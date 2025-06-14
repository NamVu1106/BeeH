package com.beearena.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GiamGiaDTO {
    private String ma;
    private Integer phanTramGiam;
    private BigDecimal soTienGiam;
    private String loai; // PERCENT, AMOUNT
    private LocalDateTime ngayHetHan;
} 