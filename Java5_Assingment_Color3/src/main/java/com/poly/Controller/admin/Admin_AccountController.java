package com.poly.Controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.DAO.NguoiDungDAO;
import com.poly.Model.NguoiDung;
import com.poly.Service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class Admin_AccountController {
	@Autowired
	NguoiDungDAO nguoiDungDAO;
	@Autowired
	SessionService session;

	@GetMapping("Admin/AccountManagement")
	public String AccountManagement(Model model) {
		List<NguoiDung> list = nguoiDungDAO.findAll();
		List nguoiDungList = new ArrayList<>();
		NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");
		NguoiDung item = nguoiDungDAO.FindNguoiDungUserName(ng.getTenDangNhap());
		model.addAttribute("item", item);

		for (NguoiDung nguoiDung : list) {
			if (nguoiDung.getNguoi_dung_id() != ng.getNguoi_dung_id() && nguoiDung.getTenDangNhap() != null
					&& !nguoiDung.getTenDangNhap().isEmpty()) {
				nguoiDungList.add(nguoiDung);
			}
		}

		model.addAttribute("listNguoiDung", nguoiDungList);
		return "admin/AccountManagement";
	}

	@RequestMapping("Admin/EditAccount/{tenDangNhap}")
	public String edit(Model model, @PathVariable("tenDangNhap") String username, @ModelAttribute("user") NguoiDung u) {
		NguoiDung item = nguoiDungDAO.FindNguoiDungUserName(username);
		model.addAttribute("item", item);
		return "admin/EditAccount";
	}

	@RequestMapping("Admin/lockAccount/{tenDangNhap}/{khoa}")
	@Transactional
	public String lock(Model model, HttpServletRequest request, @PathVariable("tenDangNhap") String username,
			@PathVariable("khoa") Boolean khoa) {
		System.out.println(khoa);
		if (khoa) {
			nguoiDungDAO.lockNguoiDung(username, false);
		} else {
			nguoiDungDAO.lockNguoiDung(username, true);
		}
		return "redirect:/Admin/AccountManagement";
	}

	@Transactional
	@RequestMapping("Admin/EditAccount/update")
	public String update(NguoiDung item, RedirectAttributes redirectAttributes) {
		NguoiDung nguoiDung = nguoiDungDAO.FindNguoiDungUserName(item.getTenDangNhap());
		if (item.getHoTen().equals("")) {
			item.setHoTen(nguoiDung.getHoTen());

		} else if (item.getEmail().equals("")) {
			item.setEmail(nguoiDung.getEmail());

		} else if (item.getDiaChi().equals("")) {
			item.setDiaChi(nguoiDung.getDiaChi());

		} else if (item.getSoDienThoai().equals("")) {
			item.setSoDienThoai(nguoiDung.getSoDienThoai());

		}
		nguoiDungDAO.updateNguoiDung(item.getTenDangNhap(), item.getHoTen(), item.getMatKhau(), item.getEmail(),
				item.getDiaChi(), item.getSoDienThoai(), item.isChucVu());
		return "redirect:/Admin/AccountManagement";
	}

	@GetMapping("Admin/AddAccount")
	public String AddAccount(Model result, @ModelAttribute("user") NguoiDung u) {
		result.addAttribute("user", new NguoiDung());
		return "admin/AddAccount";
	}

	@PostMapping("Admin/AddAccount")
	public String AddUser(@Valid @ModelAttribute("user") NguoiDung u, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getObjectName());
			System.out.println(result);
			System.out.println(result.getAllErrors());
			return "admin/AddAccount";
		}

		List<NguoiDung> list = new ArrayList<>();
		for (NguoiDung nguoiDung : nguoiDungDAO.findAll()) {
			if (nguoiDung.getEmail() != null && !nguoiDung.getEmail().isEmpty() && nguoiDung.getTenDangNhap() != null
					&& !nguoiDung.getTenDangNhap().isEmpty() && nguoiDung.getDiaChi() != null
					&& !nguoiDung.getDiaChi().isEmpty()) {
				list.add(nguoiDung);
			}
		}
		for (NguoiDung nguoiDung : list) {
			if (nguoiDung.getTenDangNhap().equals(u.getTenDangNhap())) {
				System.out.println("Trùng tài khoản");
				model.addAttribute("mgs", "Trùng tài khoản vui lòng nhập ");
				result.reject("user", "Trùng tài khoản vui lòng nhập");
				return "admin/AddAccount";
			}
		}

		nguoiDungDAO.save(u);
		return "redirect:/Admin/AccountManagement";
	}

}
