package com.poly.Controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.DAO.NhaCungCapDAO;
import com.poly.DAO.SanPhamDAO;
import com.poly.Model.DichVu;
import com.poly.Model.DonHangDichVu;
import com.poly.Model.NhaCungCap;
import com.poly.Model.SanPham;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class Admin_SupplierController {
	@Autowired
	NhaCungCapDAO nhaCungCapDAO;

	@Autowired
	SanPhamDAO sanPhamDAO;

	@GetMapping("Admin/SupplierManagement")
	public String ServiceManagement(Model model) {
		List<NhaCungCap> nhaCungCaps = nhaCungCapDAO.findAll();
		model.addAttribute("listNhaCungCap", nhaCungCaps);
		return "admin/SupplierManagement";
	}

	@GetMapping("Admin/AddSupplier")
	public String AddSupplier(Model model, @ModelAttribute("NhaCungCap") NhaCungCap n) {
		model.addAttribute("NhaCungCap", new NhaCungCap());
		return "admin/AddSupplier";
	}

	@PostMapping("Admin/AddSupplier")
	public String addSupplier(Model model, @Valid @ModelAttribute("NhaCungCap") NhaCungCap n, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/AddSupplier";
		}
		nhaCungCapDAO.save(n);
		return "redirect:/Admin/SupplierManagement";
	}

	@GetMapping("Admin/EditSupplier/{NhaCungCapId}")
	public String EditService(DichVu dichVu, Model model, @PathVariable("NhaCungCapId") Integer id) {
		NhaCungCap nhaCungCap = nhaCungCapDAO.findById(id).get();
		model.addAttribute("item", nhaCungCap);
		return "admin/EditSupplier";
	}

	@Transactional
	@PostMapping("Admin/EditSupplier/update")
	public String updataDichVu(NhaCungCap nhaCungCap) {
		NhaCungCap cungCap = nhaCungCapDAO.getOne(nhaCungCap.getId());
		if (nhaCungCap.getTenNhaCungCap().equals("")) {
			nhaCungCap.setTenNhaCungCap(cungCap.getTenNhaCungCap());
		} else if (nhaCungCap.getDiaChi().equals("")) {
			nhaCungCap.setDiaChi(cungCap.getDiaChi());
		} else if (nhaCungCap.getEmail().equals("")) {
			nhaCungCap.setEmail(cungCap.getEmail());
		} else if (nhaCungCap.getSoDienThoai().equals("")) {
			nhaCungCap.setSoDienThoai(cungCap.getSoDienThoai());
		} else {
			nhaCungCapDAO.updateNhaCungCap(nhaCungCap.getId(), nhaCungCap.getTenNhaCungCap(), nhaCungCap.getDiaChi(),
					nhaCungCap.getSoDienThoai(), nhaCungCap.getEmail());
		}

		return "redirect:/Admin/SupplierManagement";
	}

}
