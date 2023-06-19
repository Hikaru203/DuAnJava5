package com.poly.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.DAO.UserDAO;
import com.poly.Model.NguoiDung;
import com.poly.Service.MailerService;
import com.poly.Service.MailerServiceImpl;

import jakarta.mail.MessagingException;

import com.poly.Model.MailInfo;

@Controller
public class ForgotController {
	@Autowired
	UserDAO customerDAO;

	@Autowired
	MailerServiceImpl impl;

	public String generateRandomString() {
		int length = 10; // Độ dài của chuỗi ký tự ngẫu nhiên
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder sb = new StringBuilder();

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}

		return sb.toString();
	}

	@GetMapping("/forgot-password")
	public String get_forgot(Model model) {
		NguoiDung user = new NguoiDung();
		model.addAttribute("user", user);
		return "forgot-password";
	}

	@PostMapping("/forgot-password")
	public String post_forgot(Model model, @ModelAttribute("user") NguoiDung user,
			@RequestParam("email") String recipient) {

		boolean check = false;
		List<NguoiDung> list = new ArrayList<>();
		for (NguoiDung nguoiDung : customerDAO.findAll()) {
			if (nguoiDung.getEmail() != null && !nguoiDung.getEmail().isEmpty() && nguoiDung.getTenDangNhap() != null
					&& !nguoiDung.getTenDangNhap().isEmpty() && nguoiDung.getDiaChi() != null
					&& !nguoiDung.getDiaChi().isEmpty()) {
				list.add(nguoiDung);
			}
		}

		for (NguoiDung nguoiDung : list) {
			if (nguoiDung.getEmail().equals(user.getEmail())) {
				check = true;
				break;
			}
		}

		if (check) {
			String newPassword = generateRandomString();
			NguoiDung customer = customerDAO.findByEmail(user.getEmail());
			customer.setMatKhau(newPassword);
			customerDAO.save(customer);

			String subject = "Đổi mật khẩu";
			String message = newPassword;

			MailInfo mail = new MailInfo(recipient, subject, message);
			try {
				impl.send(mail);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return "login";
		} else {
			return "forgot-password";
		}
	}

}
