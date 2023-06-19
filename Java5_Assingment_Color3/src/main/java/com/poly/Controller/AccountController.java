package com.poly.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.DAO.UserDAO;
import com.poly.Model.NguoiDung;
import com.poly.Service.CookieService;
import com.poly.Service.ParamService;
import com.poly.Service.SessionService;

import jakarta.validation.Valid;

public class AccountController {
	@Autowired
	UserDAO customerDAO;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired 
	CookieService cookieService;
	
	private void checkCookie() {
		String username = cookieService.getValue("username");
		String password = cookieService.getValue("password");
	}
	
	void loadProductInCart(Model model) {
		try {
			NguoiDung user = (NguoiDung) sessionService.get("Session_custumer");
			//model.addAttribute("countItem", DetailDAO.countByIdCustomerId(customer.getCustomer_id()));
		} catch (Exception e) {
			model.addAttribute("countItem", 0);
		}
	}
	
	@GetMapping("/login")
	public String getLogin(NguoiDung user,Model model, @RequestParam("tenDangNhap") Optional<String> tenDangNhap) {
		checkCookie();
		String username = cookieService.getValue("tenDangNhap");
		String password = cookieService.getValue("matKhau");
		user = new NguoiDung();
		user.setTenDangNhap(username);
		user.setMatKhau(password);
		model.addAttribute("user", user);
		
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(@Valid @ModelAttribute("user") Optional<NguoiDung> user, BindingResult result, 
			Model model) {
		if(result.hasErrors()) {
			return "login";
		}
		
		String username = paramService.getString("tenDangNhap", "");
		String password = paramService.getString("matKhau", "");
		
		return "index";
	}
}
