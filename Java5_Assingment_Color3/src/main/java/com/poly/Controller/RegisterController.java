package com.poly.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.DAO.UserDAO;
import com.poly.Model.NguoiDung;
import com.poly.Service.ParamService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {
	@Autowired
	UserDAO customerDAO;

	@GetMapping("/user/register")
	public String get_register(Model model, @ModelAttribute("errorMessage") String errorMessage) {
		NguoiDung user = new NguoiDung();
		model.addAttribute("user", user);
		if (!errorMessage.isEmpty()) {
			model.addAttribute("errorMessage", errorMessage);
		}
		return "register";
	}

	@PostMapping("/user/register")
	public String post_register(@Valid @ModelAttribute("user") NguoiDung user, BindingResult result, Model model) {
		// List<NguoiDung> list = customerDAO.findAll();
		if (!result.hasErrors()) {
			return "register";
		}
		List<NguoiDung> list = customerDAO.findAll();
//		List<NguoiDung> list = new ArrayList<>();
//		for (NguoiDung nguoiDung : customerDAO.findAll()) {
//			if (nguoiDung.getEmail() != null && !nguoiDung.getEmail().isEmpty() && nguoiDung.getTenDangNhap() != null
//					&& !nguoiDung.getTenDangNhap().isEmpty() && nguoiDung.getDiaChi() != null
//					&& !nguoiDung.getDiaChi().isEmpty()) {
//				list.add(nguoiDung);
//			}
//		}
		for (NguoiDung nguoiDung : list) {
			if (nguoiDung.getTenDangNhap().equals(user.getTenDangNhap())) {
				model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại !");
				return "register";
			}
			if (nguoiDung.getEmail().equals(user.getEmail())) {
				model.addAttribute("errorMessage", "Email này đã đăng ký !");
				return "register";
			}
			if (nguoiDung.getSoDienThoai().equals(user.getSoDienThoai())) {
				model.addAttribute("errorMessage", "Số điện thoại này đã đăng ký !");
				return "register";
			}
		}

		NguoiDung ng = user;
		ng.setChucVu(false);
		ng.setKhoa(true);
		customerDAO.save(ng);
		return "redirect:/login";

	}
}
