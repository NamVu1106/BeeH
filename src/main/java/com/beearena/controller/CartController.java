package com.beearena.controller;

import com.beearena.entity.Cart;
import com.beearena.entity.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        model.addAttribute("cart", cart);
        return "gio-hang";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam String productName,
                            @RequestParam String image,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        cart.addItem(new CartItem(productId, productName, image, price, quantity));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId,
                             @RequestParam int quantity,
                             HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.removeItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("order", new Object()); // Tạo DTO cho order nếu cần
        return "thanh-toan";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("order") Object order, HttpSession session) {
        // Xử lý lưu đơn hàng vào DB nếu cần
        session.removeAttribute("cart");
        return "redirect:/success";
    }

    @ModelAttribute("cart")
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        return cart;
    }
} 
package com.beearena.controller;

import com.beearena.model.Cart;
import com.beearena.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        model.addAttribute("cart", cart);
        return "gio-hang";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer productId,
                            @RequestParam String productName,
                            @RequestParam String image,
                            @RequestParam String category,
                            @RequestParam BigDecimal price,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        cart.addItem(new CartItem(productId, productName, image, category, price, quantity));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Integer productId,
                             @RequestParam int quantity,
                             HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.updateItem(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Integer productId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.removeItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("order", new Object()); // Tạo DTO cho order nếu cần
        return "thanh-toan";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("order") Object order, HttpSession session) {
        // Xử lý lưu đơn hàng vào DB nếu cần
        session.removeAttribute("cart");
        return "redirect:/success";
    }

    @ModelAttribute("cart")
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();
        return cart;
    }
} 