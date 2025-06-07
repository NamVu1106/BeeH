-- Tạo cơ sở dữ liệu
CREATE DATABASE ProductManagement;
GO
USE ProductManagement;
GO

-- Bảng DanhMuc
CREATE TABLE DanhMuc (
    MaDM INT IDENTITY(1,1) PRIMARY KEY,
    TenDM NVARCHAR(100) NOT NULL,
    MoTa NVARCHAR(500),
    NgayTao DATETIME DEFAULT GETDATE(),
    NgayCapNhat DATETIME DEFAULT GETDATE(),
    TrangThai BIT DEFAULT 1
);
GO

-- Bảng SanPham
CREATE TABLE SanPham (
    MaSP INT IDENTITY(1,1) PRIMARY KEY,
    TenSP NVARCHAR(200) NOT NULL,
    MaDM INT FOREIGN KEY REFERENCES DanhMuc(MaDM),
    Gia DECIMAL(18,2) NOT NULL,
    MoTa NVARCHAR(MAX),
    SoLuong INT DEFAULT 0,
    HinhAnh NVARCHAR(500),
    MauSac NVARCHAR(255),
    NgayTao DATETIME DEFAULT GETDATE(),
    NgayCapNhat DATETIME DEFAULT GETDATE(),
    TrangThai BIT DEFAULT 1
);
GO

-- Bảng NhaCungCap
CREATE TABLE NhaCungCap (
    MaNCC INT IDENTITY(1,1) PRIMARY KEY,
    TenNCC NVARCHAR(200) NOT NULL,
    DiaChi NVARCHAR(500),
    SDT VARCHAR(20),
    Email VARCHAR(100),
    NgayTao DATETIME DEFAULT GETDATE(),
    TrangThai BIT DEFAULT 1
);
GO

-- Bảng PhieuNhap & ChiTietPhieuNhap
CREATE TABLE PhieuNhap (
    MaPN INT IDENTITY(1,1) PRIMARY KEY,
    MaNCC INT FOREIGN KEY REFERENCES NhaCungCap(MaNCC),
    NgayNhap DATETIME DEFAULT GETDATE(),
    TongTien DECIMAL(18,2) DEFAULT 0,
    GhiChu NVARCHAR(500),
    TrangThai BIT DEFAULT 1
);
GO
CREATE TABLE ChiTietPhieuNhap (
    MaCTPN INT IDENTITY(1,1) PRIMARY KEY,
    MaPN INT FOREIGN KEY REFERENCES PhieuNhap(MaPN),
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    ThanhTien DECIMAL(18,2) NOT NULL
);
GO

-- Bảng PhieuXuat & ChiTietPhieuXuat
CREATE TABLE PhieuXuat (
    MaPX INT IDENTITY(1,1) PRIMARY KEY,
    NgayXuat DATETIME DEFAULT GETDATE(),
    TongTien DECIMAL(18,2) DEFAULT 0,
    GhiChu NVARCHAR(500),
    TrangThai BIT DEFAULT 1
);
GO
CREATE TABLE ChiTietPhieuXuat (
    MaCTPX INT IDENTITY(1,1) PRIMARY KEY,
    MaPX INT FOREIGN KEY REFERENCES PhieuXuat(MaPX),
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    ThanhTien DECIMAL(18,2) NOT NULL
);
GO

-- Bảng LichSuTonKho
CREATE TABLE LichSuTonKho (
    MaLS INT IDENTITY(1,1) PRIMARY KEY,
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    LoaiPhatSinh NVARCHAR(20) NOT NULL, -- 'NHAP' hoặc 'XUAT'
    SoLuong INT NOT NULL,
    TonKhoTruoc INT NOT NULL,
    TonKhoSau INT NOT NULL,
    NgayPhatSinh DATETIME DEFAULT GETDATE(),
    GhiChu NVARCHAR(500)
);
GO

-- Bảng NguoiDung (Tài khoản người dùng & admin)
CREATE TABLE NguoiDung (
    MaND INT IDENTITY(1,1) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    MatKhau NVARCHAR(255) NOT NULL,
    SoDienThoai NVARCHAR(20),
    DiaChi NVARCHAR(255),
    NgaySinh DATE,
    VaiTro NVARCHAR(20) DEFAULT 'USER', -- 'USER' hoặc 'ADMIN'
    NgayTao DATETIME DEFAULT GETDATE(),
    TrangThai BIT DEFAULT 1
);
GO

-- Bảng DonHang (cho phép MaND NULL, lưu thông tin giao hàng cho khách vãng lai)
CREATE TABLE DonHang (
    MaDH INT IDENTITY(1,1) PRIMARY KEY,
    MaND INT NULL FOREIGN KEY REFERENCES NguoiDung(MaND),
    HoTen NVARCHAR(100),
    Email NVARCHAR(100),
    SoDienThoai NVARCHAR(20),
    DiaChiGiao NVARCHAR(255),
    NgayDat DATETIME DEFAULT GETDATE(),
    TongTien DECIMAL(18,2) DEFAULT 0,
    TrangThai NVARCHAR(30) DEFAULT N'Chờ xác nhận',
    GhiChu NVARCHAR(500)
);
GO
CREATE TABLE ChiTietDonHang (
    MaCTDH INT IDENTITY(1,1) PRIMARY KEY,
    MaDH INT FOREIGN KEY REFERENCES DonHang(MaDH),
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    MauSac NVARCHAR(50),
    ThanhTien DECIMAL(18,2) NOT NULL
);
GO

-- Bảng GioHang & ChiTietGioHang
CREATE TABLE GioHang (
    MaGH INT IDENTITY(1,1) PRIMARY KEY,
    MaND INT FOREIGN KEY REFERENCES NguoiDung(MaND),
    NgayTao DATETIME DEFAULT GETDATE()
);
GO
CREATE TABLE ChiTietGioHang (
    MaCTGH INT IDENTITY(1,1) PRIMARY KEY,
    MaGH INT FOREIGN KEY REFERENCES GioHang(MaGH),
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    SoLuong INT NOT NULL,
    MauSac NVARCHAR(50)
);
GO

-- Tài khoản admin 
INSERT INTO NguoiDung (HoTen, Email, MatKhau, VaiTro)
VALUES (N'Đào Việt Linh', N'linhdvpt00022@gmail.com', N'123', N'ADMIN');
GO

-- Dữ liệu mẫu cho DanhMuc
INSERT INTO DanhMuc (TenDM, MoTa) VALUES 
(N'Phụ kiện máy tính', N'Các loại phụ kiện cho máy tính như chuột, bàn phím, tai nghe, v.v.'),
(N'Nội thất gaming', N'Bàn ghế, tủ kệ, phụ kiện trang trí cho game thủ'),
(N'Mô hình', N'Mô hình nhân vật, xe, robot, sưu tầm'),
(N'Thiết bị di động', N'Điện thoại, máy tính bảng, phụ kiện di động'),
(N'Linh kiện máy tính', N'Các linh kiện thay thế cho máy tính'),
(N'Thiết bị mạng', N'Router, switch, card mạng và các thiết bị mạng khác');
GO

-- Dữ liệu mẫu cho NhaCungCap
INSERT INTO NhaCungCap (TenNCC, DiaChi, SDT, Email) VALUES 
(N'Công ty TNHH Phụ kiện máy tính ABC', N'123 Đường ABC, Quận 1, TP.HCM', '0123456789', 'contact@abc.com'),
(N'Công ty TNHH Thiết bị số XYZ', N'456 Đường XYZ, Quận 2, TP.HCM', '0987654321', 'info@xyz.com');
GO

-- Dữ liệu mẫu cho SanPham
INSERT INTO SanPham (TenSP, MaDM, Gia, MoTa, SoLuong, HinhAnh, MauSac) VALUES 
(N'Chuột Gaming Logitech G502 HERO', 1, 1290000, N'Chuột gaming cao cấp với cảm biến HERO 25K, 11 nút có thể lập trình', 50, '\g502.jpg', N'Đen,Xám'),
(N'Chuột Không Dây Microsoft Surface', 1, 890000, N'Chuột không dây cao cấp cho Surface và các máy tính Windows', 30, '\surface.jpg', N'Đen,Trắng,Xanh'),
(N'Chuột Gaming Razer DeathAdder V2', 1, 1490000, N'Chuột gaming với cảm biến quang học 20K DPI', 40, '\razer.jpg', N'Đen,Xanh');
GO

-- Stored Procedure: Thêm đơn hàng mới (cho cả khách vãng lai và khách có tài khoản)
CREATE PROCEDURE sp_ThemDonHang
    @MaND INT = NULL,
    @HoTen NVARCHAR(100),
    @Email NVARCHAR(100),
    @SoDienThoai NVARCHAR(20),
    @DiaChiGiao NVARCHAR(255),
    @TongTien DECIMAL(18,2),
    @TrangThai NVARCHAR(30),
    @GhiChu NVARCHAR(500) = NULL,
    @MaDH INT OUTPUT
AS
BEGIN
    INSERT INTO DonHang (MaND, HoTen, Email, SoDienThoai, DiaChiGiao, TongTien, TrangThai, GhiChu)
    VALUES (@MaND, @HoTen, @Email, @SoDienThoai, @DiaChiGiao, @TongTien, @TrangThai, @GhiChu);
    SET @MaDH = SCOPE_IDENTITY();
END
GO

-- Stored Procedure: Thêm chi tiết đơn hàng
CREATE PROCEDURE sp_ThemChiTietDonHang
    @MaDH INT,
    @MaSP INT,
    @SoLuong INT,
    @DonGia DECIMAL(18,2),
    @MauSac NVARCHAR(50),
    @ThanhTien DECIMAL(18,2)
AS
BEGIN
    INSERT INTO ChiTietDonHang (MaDH, MaSP, SoLuong, DonGia, MauSac, ThanhTien)
    VALUES (@MaDH, @MaSP, @SoLuong, @DonGia, @MauSac, @ThanhTien);
END
GO

-- Stored Procedure: Thêm sản phẩm vào giỏ hàng (nếu đã có thì cập nhật số lượng)
CREATE PROCEDURE sp_ThemVaoGioHang
    @MaGH INT,
    @MaSP INT,
    @SoLuong INT,
    @MauSac NVARCHAR(50)
AS
BEGIN
    IF EXISTS (SELECT 1 FROM ChiTietGioHang WHERE MaGH = @MaGH AND MaSP = @MaSP AND MauSac = @MauSac)
    BEGIN
        UPDATE ChiTietGioHang
        SET SoLuong = SoLuong + @SoLuong
        WHERE MaGH = @MaGH AND MaSP = @MaSP AND MauSac = @MauSac;
    END
    ELSE
    BEGIN
        INSERT INTO ChiTietGioHang (MaGH, MaSP, SoLuong, MauSac)
        VALUES (@MaGH, @MaSP, @SoLuong, @MauSac);
    END
END
GO

-- Stored Procedure: Cập nhật số lượng sản phẩm trong giỏ hàng
CREATE PROCEDURE sp_CapNhatSoLuongGioHang
    @MaCTGH INT,
    @SoLuong INT
AS
BEGIN
    UPDATE ChiTietGioHang
    SET SoLuong = @SoLuong
    WHERE MaCTGH = @MaCTGH;
END
GO

-- Stored Procedure: Xóa sản phẩm khỏi giỏ hàng
CREATE PROCEDURE sp_XoaSanPhamGioHang
    @MaCTGH INT
AS
BEGIN
    DELETE FROM ChiTietGioHang WHERE MaCTGH = @MaCTGH;
END
GO 