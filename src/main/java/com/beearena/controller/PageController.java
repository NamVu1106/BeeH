package com.beearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.beearena.service.SanPhamService;
import com.beearena.entity.SanPham;
import jakarta.servlet.http.HttpServletRequest;
import com.beearena.service.NguoiDungService;
import org.springframework.web.bind.annotation.RequestParam;
import com.beearena.service.DonHangService;
import com.beearena.enums.TrangThaiDonHang;
import com.beearena.repository.UserRepository;
import com.beearena.repository.SanPhamRepository;
import com.beearena.repository.DanhMucRepository;
import com.beearena.model.Cart;
import com.beearena.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private DonHangService donHangService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private DanhMucRepository danhMucRepository;

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
    @GetMapping("/danh-muc")
    public String danhMuc() {
        return "danh-muc";
    }
    @GetMapping("/gio-hang")
    public String gioHang() {
        return "gio-hang";
    }
    @GetMapping("/tai-khoan")
    public String taiKhoan() {
        return "tai-khoan";
    }
    @GetMapping("/nha-cung-cap")
    public String nhaCungCap() {
        return "nha-cung-cap";
    }
    @GetMapping("/san-pham")
    public String sanPham() {
        return "san-pham";
    }
    @GetMapping("/tin-tuc")
    public String tinTuc() {
        return "tin-tuc";
    }
    @GetMapping("/don-hang")
    public String donHang() {
        return "don-hang";
    }
    @GetMapping("/lich-su")
    public String lichSu() {
        return "lich-su";
    }
    @GetMapping("/ban-phim")
    public String banPhim() {
        return "ban-phim";
    }
    @GetMapping("/chuot")
    public String chuot() {
        return "chuot";
    }
    @GetMapping("/tai-nghe")
    public String taiNghe() {
        return "tainghe";
    }
    @GetMapping("/mo-hinh")
    public String moHinh() {
        return "mo-hinh";
    }
    @GetMapping("/san-pham/{id}")
    public String chiTietSanPham(@org.springframework.web.bind.annotation.PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.laySanPhamTheoId(Long.valueOf(id)).orElse(null);
        if (sanPham == null) {
            return "redirect:/san-pham";
        }
        model.addAttribute("sanPham", sanPham);
        // Nếu có sản phẩm mua kèm, có thể lấy thêm ở đây
        return "ChiTietSanPham";
    }
    @GetMapping("/admin/bang-dieu-khien")
    public String bangDieuKhien(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        // Số sản phẩm
        long tongSanPham = sanPhamService.demTatCaSanPham();
        // Số đơn hàng
        long tongDonHang = donHangService.demTatCaDonHang();
        // Số người dùng
        long tongNguoiDung = nguoiDungService.layTatCaNguoiDung().size();
        // Đơn hàng gần đây (lấy 5 đơn mới nhất)
        java.util.List<com.beearena.entity.DonHang> donHangGanDay = donHangService.layTatCaDonHang();
        donHangGanDay = donHangGanDay.stream()
            .sorted((a, b) -> b.getNgayTao().compareTo(a.getNgayTao()))
            .limit(5)
            .toList();
        model.addAttribute("tongSanPham", tongSanPham);
        model.addAttribute("tongDonHang", tongDonHang);
        model.addAttribute("tongNguoiDung", tongNguoiDung);
        model.addAttribute("donHangGanDay", donHangGanDay);
        return "admin/bang-dieu-khien";
    }
    @GetMapping("/admin/quan-ly-don-hang")
    public String quanLyDonHang(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "trangThai", required = false) String trangThai,
        @RequestParam(value = "ngayTu", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate ngayTu,
        @RequestParam(value = "ngayDen", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate ngayDen,
        @RequestParam(value = "tongTienTu", required = false) Long tongTienTu,
        @RequestParam(value = "tongTienDen", required = false) Long tongTienDen,
        HttpServletRequest request, Model model) {
        int pageSize = 10;
        org.springframework.data.domain.Page<com.beearena.entity.DonHang> donHangPage = donHangService.locDonHang(page, pageSize, keyword, trangThai, ngayTu, ngayDen, tongTienTu, tongTienDen);
        model.addAttribute("donHangList", donHangPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", donHangPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("trangThai", trangThai);
        model.addAttribute("ngayTu", ngayTu);
        model.addAttribute("ngayDen", ngayDen);
        model.addAttribute("tongTienTu", tongTienTu);
        model.addAttribute("tongTienDen", tongTienDen);
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("tongDonHang", donHangService.demTatCaDonHang());
        model.addAttribute("donHangCho", donHangService.demDonHangTheoTrangThai(TrangThaiDonHang.CHO_XAC_NHAN));
        model.addAttribute("donHangDangGiao", donHangService.demDonHangTheoTrangThai(TrangThaiDonHang.DANG_GIAO));
        model.addAttribute("donHangDaGiao", donHangService.demDonHangTheoTrangThai(TrangThaiDonHang.DA_GIAO));
        model.addAttribute("donHangDaHuy", donHangService.demDonHangTheoTrangThai(TrangThaiDonHang.DA_HUY));
        model.addAttribute("tongDoanhThu", donHangService.tongDoanhThu());
        return "admin/quan-ly-don-hang";
    }
    @GetMapping("/admin/quan-ly-nguoi-dung")
    public String quanLyNguoiDung(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        java.util.List<com.beearena.entity.User> allUsers = userRepository.findAll();
        java.util.List<com.beearena.entity.User> filteredUsers = allUsers.stream()
            .filter(u -> "QUAN_TRI".equals(u.getRole()) || "NGUOI_DUNG".equals(u.getRole()))
            .sorted((u1, u2) -> {
                if ("QUAN_TRI".equals(u1.getRole()) && !"QUAN_TRI".equals(u2.getRole())) return -1;
                if (!"QUAN_TRI".equals(u1.getRole()) && "QUAN_TRI".equals(u2.getRole())) return 1;
                return 0;
            })
            .toList();
        System.out.println("==== USERS ====");
        for (var u : filteredUsers) {
            System.out.println(u.getMaND() + " - " + u.getName() + " - " + u.getRole());
        }
        model.addAttribute("users", filteredUsers);
        return "admin/quan-ly-nguoi-dung";
    }
    @GetMapping("/admin/quan-ly-danh-muc")
    public String quanLyDanhMuc(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "admin/quan-ly-danh-muc";
    }
    @GetMapping("/admin/thong-ke")
    public String thongKe(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "admin/thong-ke";
    }
    @GetMapping("/admin/quan-ly-bai-viet")
    public String quanLyBaiViet(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "admin/quan-ly-bai-viet";
    }
    @GetMapping("/admin/quan-ly-noi-dung")
    public String quanLyNoiDung(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "admin/quan-ly-noi-dung";
    }
    @GetMapping("/admin/quan-ly-nhan-vien")
    public String quanLyNhanVien(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("nhanVienList", nguoiDungService.layNguoiDungTheoVaiTro(com.beearena.entity.VaiTro.NHAN_VIEN));
        return "admin/quan-ly-nhan-vien";
    }
    @GetMapping("/admin")
    public String adminRedirect() {
        return "redirect:/admin/bang-dieu-khien";
    }
    @GetMapping("/pos")
    public String posView(Model model, HttpSession session, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "danhMuc", required = false) Long danhMucId) {
        java.util.List<SanPham> sanPhamList;
        if (keyword != null && !keyword.isBlank()) {
            sanPhamList = sanPhamRepository.findByTenSPContainingIgnoreCase(keyword);
        } else if (danhMucId != null) {
            sanPhamList = sanPhamRepository.findAll().stream().filter(sp -> sp.getDanhMuc() != null && sp.getDanhMuc().getMaDM().equals(danhMucId)).toList();
        } else {
            sanPhamList = sanPhamRepository.findAll();
        }
        model.addAttribute("sanPhamList", sanPhamList);
        model.addAttribute("danhMucList", danhMucRepository.findAll());
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        model.addAttribute("cart", cart);
        return "bantaiquay/POSView";
    }

    @PostMapping("/pos/add")
    public String addToCart(@RequestParam Integer productId, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
        SanPham sp = sanPhamRepository.findById(productId).orElse(null);
        if (sp == null) return "redirect:/pos";
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        CartItem item = new CartItem(sp.getMaSP(), sp.getTenSP(), sp.getHinhAnh(), sp.getDanhMuc() != null ? sp.getDanhMuc().getTenDM() : "", sp.getGia(), quantity);
        cart.addItem(item);
        session.setAttribute("cart", cart);
        return "redirect:/pos";
    }

    @PostMapping("/pos/update")
    public String updateCart(@RequestParam Integer productId, @RequestParam int quantity, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateItem(productId, quantity);
            session.setAttribute("cart", cart);
        }
        return "redirect:/pos";
    }

    @PostMapping("/pos/remove")
    public String removeFromCart(@RequestParam Integer productId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeItem(productId);
            session.setAttribute("cart", cart);
        }
        return "redirect:/pos";
    }

    @PostMapping("/pos/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/pos";
    }

    @PostMapping("/pos/checkout")
    public String checkout(HttpSession session) {
        // TODO: Lưu đơn hàng vào database
        session.removeAttribute("cart");
        return "redirect:/pos?success";
    }
} 