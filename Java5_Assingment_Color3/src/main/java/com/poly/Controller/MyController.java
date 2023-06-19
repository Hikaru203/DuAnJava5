package com.poly.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.DAO.DichVuDAO;
import com.poly.DAO.GioHangDAO;
import com.poly.DAO.GioHangSanPhamDAO;
import com.poly.DAO.SanPhamDAO;
import com.poly.DAO.UserDAO;
import com.poly.Model.DichVu;
import com.poly.Model.GioHang;
import com.poly.Model.GioHangSanPham;
import com.poly.Model.NguoiDung;
import com.poly.Model.SanPham;
import com.poly.Service.CookieService;
import com.poly.Service.ParamService;
import com.poly.Service.SessionService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MyController {
	@Autowired
	UserDAO customerDAO;
	@Autowired
	SanPhamDAO sanphamDAO;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	CookieService cookieService;
	@Autowired
	DichVuDAO dichVuDAO;
	@Autowired
	GioHangSanPhamDAO gioHangSanPhamDAO;
	@Autowired
	GioHangDAO gioHangDAO;
	@Autowired
	SessionService session;

	@GetMapping("/index")
	public String index(Model model, @ModelAttribute("errorMessage") String errorMessage) {
		String username = paramService.getString("tenDangNhap", "");
		List<SanPham> list = new ArrayList<>();
		for (SanPham sanPham : sanphamDAO.findAll()) {
			if (sanPham.isTrangThai()) {
				list.add(sanPham);
			}
		}
		List<DichVu> listDichVu = new ArrayList<>();
		for (DichVu dichVu : dichVuDAO.findAll()) {
			if (dichVu.isTrangThai()) {
				listDichVu.add(dichVu);
			}
		}
		model.addAttribute("listDichVu", listDichVu);
		model.addAttribute("listSP", list);
		if (errorMessage.isEmpty()) {
			model.addAttribute("errorMessage", errorMessage);
			System.out.println(errorMessage);
		}
		return "index";
	}

//	@GetMapping("/card")
//	public String card(Model model) {
//		if (sessionService.get("session_NguoiDung") == null) {
//			System.out.println("Chưa đăng nhập");
//			return "redirect:/index";
//		} else {
//			return "shoppingCart";
//		}
//
//	}

	@GetMapping("AccountInformation.html")
	public String AccountInformation() {
		return "AccountInformation";
	}

	@GetMapping("about.html")
	public String about() {
		return "about";
	}

	@GetMapping("blog.html")
	public String blog() {
		return "blog";
	}

	@GetMapping("blogSingle.html")
	public String blogSingle() {
		return "blogSingle";
	}

	@GetMapping("contact.html")
	public String contact() {
		return "contact";
	}

	@GetMapping("productDetails.html")
	public String productDetails() {
		return "productDetails";
	}

	@GetMapping("reservation.html")
	public String reservation() {
		return "reservation";
	}

	@GetMapping("editProfile")
	public String editProfile(Model model) {
		try {
			NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");// lấy session rán vào đối tượng ngdung
			NguoiDung ngDung = customerDAO.findByTenDangNhap(ng.getTenDangNhap()); // từ đói tượng ngdung đó tìm ra doi
																					// tuong ngdung
			model.addAttribute("User", ngDung); // doi tuong ngdung do rán vào biến user
			return "AccountInformation";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/index";

		}
	}

	@PostMapping("editProfile")
	public String updataProfile(@Valid @ModelAttribute("User") NguoiDung user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "AccountInformation";
		}
		NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");
		NguoiDung ngDung = customerDAO.findByTenDangNhap(ng.getTenDangNhap());
		if (ng != null) {
			customerDAO.save(user);
		}

		return "redirect:/editProfile";
	}

}
