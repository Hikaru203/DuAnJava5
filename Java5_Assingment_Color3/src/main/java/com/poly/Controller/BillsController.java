package com.poly.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.DAO.GioHangDAO;
import com.poly.DAO.GioHangSanPhamDAO;
import com.poly.DAO.GioHangSanPhamInfoRepository;
import com.poly.DAO.SanPhamDAO;
import com.poly.DAO.UserDAO;
import com.poly.Model.GioHang;
import com.poly.Model.GioHangSanPham;
import com.poly.Model.GioHangSanPhamInfo;
import com.poly.Model.NguoiDung;
import com.poly.Model.SanPham;
import com.poly.Service.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BillsController {
	@Autowired
	SanPhamDAO sanPhamDAO;
	@Autowired
	SessionService session;
	@Autowired
	GioHangDAO gioHangDAO;
	@Autowired
	GioHangSanPhamDAO gioHangSanPhamDAO;
	@Autowired
	UserDAO customerDAO;
	@Autowired
	GioHangSanPhamInfoRepository gioHangSanPhamInfoRepository;
	
	@PostMapping("/Cart/Checkout")
	public String pay(Model model) {
		// lấy thông tin 
		NguoiDung ng = session.get("session_NguoiDung");		return "redirect:/index";
	}
}
