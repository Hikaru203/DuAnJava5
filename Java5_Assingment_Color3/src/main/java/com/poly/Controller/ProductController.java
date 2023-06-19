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

import com.poly.DAO.ChiTietDonHangDAO;
import com.poly.DAO.DonHangDAO;
import com.poly.DAO.GioHangDAO;
import com.poly.DAO.GioHangSanPhamDAO;
import com.poly.DAO.GioHangSanPhamInfoRepository;
import com.poly.DAO.KhoDAO;
import com.poly.DAO.SanPhamDAO;
import com.poly.DAO.UserDAO;
import com.poly.Model.ChiTietDonHang;
import com.poly.Model.DonHang;
import com.poly.Model.GioHang;
import com.poly.Model.GioHangSanPham;
import com.poly.Model.GioHangSanPhamInfo;
import com.poly.Model.Kho;
import com.poly.Model.NguoiDung;
import com.poly.Model.SanPham;
import com.poly.Service.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
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
	@Autowired
	KhoDAO khoDao;
	@Autowired
	DonHangDAO dhDao;
	@Autowired
	ChiTietDonHangDAO ctdhDao;

	@GetMapping("/menu")
	public String get_memmu(Model model) {
		List<SanPham> list = new ArrayList<>();
		for (SanPham sanPham : sanPhamDAO.findAll()) {
			if (sanPham.isTrangThai()) {
				list.add(sanPham);
			}
		}
		model.addAttribute("listSP", list);
		return "menu";
	}

	@GetMapping("/productDetails/{id}")
	public String Edit(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("errorMessage") String errorMessage) {
		SanPham sp = sanPhamDAO.findById(id).get();
		model.addAttribute("product", sp);
		if (errorMessage.isEmpty()) {
			model.addAttribute("errorMessage", errorMessage);
		}
		return "productDetails";
	}

	@GetMapping("/User/Cart")
	public String selectUserCart(Model model) {
		NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");
		if (ng != null) {
			List<GioHangSanPhamInfo> gioHangSanPhams = new ArrayList<>();
			for (GioHangSanPham gioHangSanPham : gioHangSanPhamDAO.findAll()) {
				GioHangSanPhamInfo gioHangSanPhamInfo = new GioHangSanPhamInfo();
				gioHangSanPhamInfo.setIdSanPham(gioHangSanPham.getSanPham().getId());
				gioHangSanPhamInfo.setGia(gioHangSanPham.getSanPham().gia);
				gioHangSanPhamInfo.setHinhAnh(gioHangSanPham.getSanPham().hinhAnh);
				gioHangSanPhamInfo.setMoTa(gioHangSanPham.getSanPham().moTa);
				gioHangSanPhamInfo.setNguoiDungId(gioHangSanPham.getGioHang().getNguoiDung().getNguoi_dung_id());
				gioHangSanPhamInfo.setSoLuongSanPham(gioHangSanPham.getSoLuong());
				gioHangSanPhamInfo.setTenSanPham(gioHangSanPham.getSanPham().tenSanPham);
				if (gioHangSanPhamInfo.getNguoiDungId() == ng.getNguoi_dung_id()) {
					gioHangSanPhams.add(gioHangSanPhamInfo);
				}
			}
			model.addAttribute("gioHangSanPhamInfoList", gioHangSanPhams);
			model.addAttribute("username", ng.getHoTen());
			model.addAttribute("diaChi", ng.getDiaChi());
			System.out.println(ng.getHoTen());
			return "shoppingCart";
		} else {
			model.addAttribute("errorMessage", "Bạn chưa đăng nhập !!!");
			return "redirect:/login";
		}

	}

	@PostMapping("/addCart/{id}")
	public String addToCart(Model model, @PathVariable("id") int id, HttpServletRequest request) {

		NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");
		if (ng != null) {
			Date ngayTao = new Date();

			GioHang gh = new GioHang();

			SanPham pham = sanPhamDAO.findById(id);

			if (gioHangDAO.findBynguoiDung(ng) != null) {
				gh = gioHangDAO.findBynguoiDung(ng);
			} else {
				gh.setNguoiDung(ng);
				gh.setNgay_tao(ngayTao);
				gioHangDAO.save(gh);
			}
			boolean check = false;
			for (GioHangSanPham ghsp : gioHangSanPhamDAO.findAll()) {
				if (ghsp.getGioHang().getGio_hang_id() == gh.getGio_hang_id()) {
					if (ghsp.getSanPham().getId() == pham.getId()) {
						check = false;
					} else {
						check = true;
					}
				} else {
					check = true;
					System.out.println("here");
				}
			}
			if (check == false) {
				model.addAttribute("errorMessage", "Sản phẩm đã tồn tại trong giỏ hàng");
				return "redirect:/index";
			} else {
				GioHangSanPham ghs = new GioHangSanPham();
				ghs.setGioHang(gh);
				ghs.setSanPham(pham);
				ghs.setSoLuong(1);
				gioHangSanPhamDAO.save(ghs);

				return "redirect:/User/Cart";
			}

		} else {
			model.addAttribute("errorMessage", "Vui lòng đăng nhập");
			return "redirect:/login";
		}

	}

	@RequestMapping("/delete/{id}")
	public String removeProduct(Model model, @PathVariable("id") int id) {
		NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");
		// Tạo Người dùng
		NguoiDung ngDung = customerDAO.findByTenDangNhap(ng.getTenDangNhap());
		GioHang gioHang = gioHangDAO.findBynguoiDung(ngDung);
		List<GioHangSanPham> listGhsp = gioHangSanPhamDAO.findBySanPhamId(id);
		for (GioHangSanPham gioHangSanPham : listGhsp) {
			if (gioHangSanPham.getGioHang() == gioHang) {
				gioHangSanPhamDAO.delete(gioHangSanPham);
				return "redirect:/User/Cart";
			}
		}
		return "redirect:/User/Cart";
	}

	@RequestMapping("/Checkout")
	public String checkOut(Model model) {
		// Tạo Người dùng
		NguoiDung ng = (NguoiDung) session.get("session_NguoiDung");
		NguoiDung ngDung = customerDAO.findByTenDangNhap(ng.getTenDangNhap());

		GioHang gioHang = gioHangDAO.findBynguoiDung(ngDung);

		// Tạo đơn hàng
		Date date = new Date();
		DonHang dh = new DonHang();
		dh.setNguoiDung(ngDung);
		dh.setNgay_dat_hang(date);
		dh.setTongGiaTri(0);
		dh.setTrangThai(false);
		dhDao.save(dh);
		System.out.println(date);
		double tongTien = 0;

		for (GioHangSanPham gioHangSanPham : gioHangSanPhamDAO.findByGioHang(gioHang)) {
			// Tính tổng tiền
			tongTien += gioHangSanPham.getSoLuong() * gioHangSanPham.getSanPham().getGia();
			// Update lại số lượng trong kho
			int soLuongKho = khoDao.findBySanPhamId(gioHangSanPham.getSanPham().getId()).getSoLuong();
			int soLuong = gioHangSanPham.getSoLuong();
			khoDao.updateSoLuongBySanPham(gioHangSanPham.getSanPham().getId(), soLuongKho - soLuong);
			ChiTietDonHang ctdh = new ChiTietDonHang();
			ctdh.setDonHang(dh);
			ctdh.setSanPham(gioHangSanPham.getSanPham());
			ctdh.setSoLuong(gioHangSanPham.getSoLuong());
			ctdh.setGia(gioHangSanPham.getSoLuong() * gioHangSanPham.getSanPham().getGia());
			ctdhDao.save(ctdh);

			gioHangSanPhamDAO.delete(gioHangSanPham);
		}
		dhDao.updateTongGiaTriAndTrangThai(tongTien, ngDung);

		return "redirect:/index";
	}

	@RequestMapping("/infoCheckout")
	public String infoCheckout(Model model) {

		return "checkout";
	}
}
