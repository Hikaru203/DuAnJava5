package com.poly.Controller.admin;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.DAO.DichVuDAO;
import com.poly.DAO.DonHangDAO;
import com.poly.DAO.DonHangDichVuDAO;
import com.poly.DAO.NguoiDungDAO;
import com.poly.DAO.NguoiDungDAO2;
import com.poly.DAO.NhaCungCapDAO;
import com.poly.Model.DichVu;
import com.poly.Model.DonHang;
import com.poly.Model.DonHangDichVu;
import com.poly.Model.NguoiDung;
import com.poly.Model.NguoiDung2;
import com.poly.Model.NhaCungCap;
import com.poly.Model.SanPham;
import com.poly.Model.SanPhamMaGiamGia;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class Admin_ServiceController {

	@Autowired
	DichVuDAO dichVuDAO;

	@Autowired
	DonHangDichVuDAO donHangDichVuDAO;

	@Autowired
	NguoiDungDAO nguoiDungDAO;

	@Autowired
	DonHangDAO donHangDAO;

	@Autowired
	NguoiDungDAO2 nguoiDungDAO2;
	NguoiDung2 ngDung = new NguoiDung2();

	@GetMapping("Admin/ServiceManagement")
	public String ServiceManagement(Model model) {
		List<DichVu> dichVus = dichVuDAO.findAll();
		model.addAttribute("listDichVu", dichVus);
		return "admin/ServiceManagement";
	}

	@GetMapping("Admin/AddService")
	public String AddService(Model model, @ModelAttribute("dichVu") DichVu d) {
		model.addAttribute("dichVu", new DichVu());
		return "admin/AddService";
	}

	@PostMapping("Admin/AddService")
	public String addService(Model model, @Valid @ModelAttribute("dichVu") DichVu d, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/AddService";
		}
		d.setTrangThai(true);
		dichVuDAO.save(d);
		return "redirect:/Admin/ServiceManagement";
	}

	@GetMapping("Admin/EditService/{dichVuId}")
	public String EditService(DichVu dichVu, Model model, @PathVariable("dichVuId") Integer id) {
		DichVu dichvu = dichVuDAO.findById(id).get();
		model.addAttribute("item", dichvu);
		return "admin/EditService";
	}

	@Transactional
	@PostMapping("Admin/EditService/update")
	public String updataDichVu(Model model, @Valid DichVu dichVu, BindingResult result,
			RedirectAttributes redirectAttributes) {
		DichVu dichVu2 = dichVuDAO.findById(dichVu.getId());
		model.addAttribute("dichVu", dichVu);
		model.addAttribute("item", dichVu);
		model.addAttribute("dichVuId", dichVu.id); // Thêm biến id vào model

		if (result.hasErrors()) {
			return "redirect:/Admin/ServiceManagement";
		}
		if (dichVu.getTenDichVu().equals("")) {
			dichVu.setTenDichVu(dichVu2.getTenDichVu());
		}
		dichVuDAO.updateDichVuById(dichVu.getId(), dichVu.getGia(), dichVu.getMoTa(), dichVu.getTenDichVu());
		return "redirect:/Admin/ServiceManagement";
	}

	@Transactional
	@GetMapping("Admin/lockService/{ServiceId}/{trangThai}")

	public String deleteService(Model model, @PathVariable("ServiceId") int id,
			@PathVariable("trangThai") Boolean trangThai) {
		DichVu dichVu = dichVuDAO.findById(id);
		if (dichVu.isTrangThai()) {
			dichVu.setTrangThai(false);
		} else {
			dichVu.setTrangThai(true);
		}
		return "redirect:/Admin/ServiceManagement";
	}

	@GetMapping("Admin/AddService_Oder")
	public String AddService2(Model model, @ModelAttribute("dichVu") DichVu d) {
		DichVu dichVu1 = new DichVu();
		dichVu1.setGia(100.0); // Gán giá trị cho thuộc tính "gia" của dichVu1
		model.addAttribute("dichVu1", dichVu1);

		model.addAttribute("dichVus", dichVuDAO.findAll());
		List<NguoiDung> listNguoiDung = nguoiDungDAO.findAll();
		model.addAttribute("listNguoiDung", listNguoiDung);

		return "admin/AddService2";
	}

	@PostMapping("/Admin/AddService_Oder2")
	public String addAccount2(@RequestParam("hoTen") String tenNguoiDung) {
		// Xử lý dữ liệu tên người dùng ở đây
		System.out.println("Tên người dùng: " + tenNguoiDung);

		ngDung.setHoTen(tenNguoiDung);
		ngDung.setChucVu(false);
		ngDung.setKhoa(true);
		nguoiDungDAO2.save(ngDung);
		// Chuyển hướng đến trang thành công hoặc trang khác
		return "redirect:/Admin/AddService_Oder";

	}

	@PostMapping("Admin/AddService_Oder")
	public String AddService3(Model model, @RequestParam("selectedUser") int selectedUserId,
			@RequestParam("r1") double selectedValue1, @RequestParam("totalPrice") double totalPrice,
			@RequestParam("id") int selectedService) {
		try {
			DonHang donHang = new DonHang();
			donHang.setNguoiDung(nguoiDungDAO.findById(selectedUserId));
			donHang.setNgay_dat_hang(new Date());
			model.addAttribute("selectedValue1", selectedValue1); // Thêm giá trị selectedValue1 vào model
			System.out.println(selectedService);
			DichVu dichVu = dichVuDAO.findById(selectedService);
			donHang.setTongGiaTri(totalPrice);
			donHang.setTrangThai(true);
			donHangDAO.save(donHang);
			DonHangDichVu donHangDichVu = new DonHangDichVu();
			donHangDichVu.setDichVu(dichVu);
			donHangDichVu.setDonHang(donHang);
			System.out.println(donHang.getTongGiaTri());
			donHangDichVuDAO.save(donHangDichVu);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return "redirect:/Admin/AddService_Oder"; // Chuyển qua phương thức khác để nhận giá trị từ model
	}

}
