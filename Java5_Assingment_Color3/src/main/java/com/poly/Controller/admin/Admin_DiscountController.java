package com.poly.Controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.DAO.MaGiamGiaDAO;
import com.poly.DAO.SanPhamDAO;
import com.poly.DAO.SanPhamMaGiamGiaDAO;
import com.poly.Model.MaGiamGia;
import com.poly.Model.NguoiDung;
import com.poly.Model.NhaCungCap;
import com.poly.Model.SanPham;
import com.poly.Model.SanPhamMaGiamGia;

import jakarta.validation.Valid;

@Controller
public class Admin_DiscountController {
	@Autowired
	MaGiamGiaDAO maGiamGiaDAO;

	@Autowired
	SanPhamDAO sanPhamDAO;

	@Autowired
	SanPhamMaGiamGiaDAO sanPhamMaGiamGiaDAO;

	@GetMapping("Admin/DiscountManagement")
	public String ServiceManagement(Model model) {
		List<MaGiamGia> maGiamGias = maGiamGiaDAO.findAll();
		model.addAttribute("listMaGiamGia", maGiamGias);
		return "admin/DiscountManagement";
	}

	@GetMapping("Admin/AddDiscount")
	public String AddSupplier(Model model, @ModelAttribute("MaGiamGia") MaGiamGia m,
			@RequestParam(required = false, value = "startDate") String startDate,
			@RequestParam(required = false, value = "endDate") String endDate) {
		model.addAttribute("MaGiamGia", new MaGiamGia());
		List<SanPham> sanPhams = sanPhamDAO.findAll();
		model.addAttribute("listSanPham", sanPhams);

		return "admin/AddDiscount";
	}

	@PostMapping("Admin/AddDiscount")
	public String addSupplier(Model model, @Valid @ModelAttribute("MaGiamGia") MaGiamGia m, BindingResult result,
			@RequestParam(required = false, value = "_ngayBatDau") String ngayBatDau,
			@RequestParam(required = false, value = "_ngayKetThuc") String ngayKetThuc,
			@RequestParam("selectedProducts") List<Integer> selectedProductIds) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date batDau = dateFormat.parse(ngayBatDau);
			Date ketThuc = dateFormat.parse(ngayKetThuc);
			m.setNgayBatDau(batDau);
			m.setNgayKetThuc(ketThuc);
		} catch (ParseException e) {
			// Xử lý lỗi nếu chuỗi ngày không hợp lệ
			e.printStackTrace();
		}
		if (result.hasErrors()) {
			List<SanPham> sanPhams = sanPhamDAO.findAll();
			model.addAttribute("listSanPham", sanPhams);
			return "admin/AddDiscount";
		}
		if (m.getNgayBatDau() != null && m.getNgayKetThuc() != null) {

			maGiamGiaDAO.save(m);
			SanPhamMaGiamGia sanPhamMaGiamGia = new SanPhamMaGiamGia();
			for (int long1 : selectedProductIds) {
				SanPham sanPham = sanPhamDAO.findById(long1);
				sanPhamMaGiamGia = new SanPhamMaGiamGia();
				sanPhamMaGiamGia.setMaGiamGia(m);
				sanPhamMaGiamGia.setSanPham(sanPham);
				sanPhamMaGiamGiaDAO.save(sanPhamMaGiamGia);
				continue;
			}

		}
		return "redirect:/Admin/DiscountManagement";

	}

	@RequestMapping("Admin/EditDiscount/{maGiamGia}")
	public String edit(Model model, @ModelAttribute("discount") MaGiamGia m, @PathVariable("maGiamGia") int id) {
		MaGiamGia maGiamGia = maGiamGiaDAO.findById(id);
		System.out.println(maGiamGia.getId());
		model.addAttribute("MaGiamGia", maGiamGia);
		List<SanPham> sanPhams = sanPhamDAO.findAll();
		model.addAttribute("listSanPham", sanPhams);
		return "admin/EditDiscount";
	}

}
