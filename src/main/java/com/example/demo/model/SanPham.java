package com.example.demo.model;

import java.util.List;

public class SanPham {
    private Long id;
    private String ten;
    private Long gia;
    private Long giaCu;
    private Integer giamPhanTram;
    private String hinhAnh;
    private List<String> dsAnhPhu;

    // Getter  Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public Long getGia() { return gia; }
    public void setGia(Long gia) { this.gia = gia; }
    public Long getGiaCu() { return giaCu; }
    public void setGiaCu(Long giaCu) { this.giaCu = giaCu; }
    public Integer getGiamPhanTram() { return giamPhanTram; }
    public void setGiamPhanTram(Integer giamPhanTram) { this.giamPhanTram = giamPhanTram; }
    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
    public List<String> getDsAnhPhu() { return dsAnhPhu; }
    public void setDsAnhPhu(List<String> dsAnhPhu) { this.dsAnhPhu = dsAnhPhu; }
} 