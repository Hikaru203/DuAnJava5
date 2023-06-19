package com.poly.Controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.DAO.ChiTietDonHangDAO;
import com.poly.DAO.DonHangDAO;
import com.poly.DAO.GioHangSanPhamDAO;
import com.poly.DAO.KhoDAO;
import com.poly.DAO.NhaCungCapDAO;
import com.poly.DAO.SanPhamDAO;
import com.poly.DAO.SanPhamMaGiamGiaDAO;
import com.poly.Model.DonHang;
import com.poly.Model.Kho;
import com.poly.Model.NhaCungCap;
import com.poly.Model.SanPham;
import com.poly.Service.ParamService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class Admin_ProductController {
	@Autowired
	SanPhamDAO sanPhamDAO;

	@Autowired
	NhaCungCapDAO nhaCungCapDAO;

	@Autowired
	KhoDAO khoDAO;

	@Autowired
	SanPhamMaGiamGiaDAO sanPhamMaGiamGiaDAO;

	@Autowired
	GioHangSanPhamDAO gioHangSanPhamDAO;

	@Autowired
	ChiTietDonHangDAO chiTietDonHangDAO;

	@Autowired
	private ParamService paramService;

	SanPham pham;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	DonHangDAO donHangDAO;

	@GetMapping("Admin/ProductManagement")
	public String ProductManagement(Model model) {
		List<SanPham> list = sanPhamDAO.findAll();
		model.addAttribute("listSanPham", list);
		return "admin/ProductManagement";
	}

	@GetMapping("Admin/OrderManagement")
	public String OrderManagement(Model model) {
		List<DonHang> list = donHangDAO.findAll();
		model.addAttribute("listDonHang", list);
		return "admin/OrderManagement";
	}

	@GetMapping("Admin/AddProduct")
	public String AddProduct(Model model, @ModelAttribute("product") SanPham s, @ModelAttribute("kho") Kho k) {
		model.addAttribute("product", new SanPham());
		model.addAttribute("kho", new Kho());

		List<NhaCungCap> listNhaCungCaps = nhaCungCapDAO.findAll();
		model.addAttribute("listNhaCungCaps", listNhaCungCaps);
		return "admin/AddProduct";
	}

	@PostMapping("/Admin/AddProduct")
	public String addProduct(@Valid @ModelAttribute("product") SanPham s, BindingResult productResult,
			@Valid @ModelAttribute("kho") Kho kho, BindingResult khoResult, Model model,
			@RequestParam("file") MultipartFile file, @RequestParam("nhaCungCapId") int nhaCungCapId,
			@RequestParam(required = false, value = "ngayNhap") String ngayNhap1,
			RedirectAttributes redirectAttributes) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		try {
			Date ngayNhap = dateFormat.parse(ngayNhap1);
			kho.setNgayNhap(ngayNhap);
		} catch (ParseException e) { 
			// Xử lý lỗi nếu chuỗi ngày không hợp lệ
			e.printStackTrace();
		}

		if (productResult.hasErrors() || khoResult.hasErrors()) {
			System.out.println(khoResult);
			List<NhaCungCap> listNhaCungCaps = nhaCungCapDAO.findAll();
			model.addAttribute("listNhaCungCaps", listNhaCungCaps);
			return "admin/AddProduct";
		}

		try {
			if (!file.isEmpty()) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				// Đường dẫn đến thư mục images trong thư mục static
				String imagePath = "classpath:static/images/";
				Path uploadDir = Paths.get(ResourceUtils.getURL(imagePath).toURI());
				Path filePath = uploadDir.resolve(fileName);

				// Copy file vào thư mục images
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

				// Cập nhật tên file ảnh cho sản phẩm
				s.setHinhAnh(fileName);
			}

			Optional<NhaCungCap> optionalNhaCungCap = nhaCungCapDAO.findById(nhaCungCapId);

			if (optionalNhaCungCap.isPresent()) {
				NhaCungCap nhaCungCap = optionalNhaCungCap.get();
				s.setNhaCungCap(nhaCungCap);
			}

			sanPhamDAO.save(s);
			kho.setSanPham(s);
			khoDAO.save(kho);
			System.out.println(kho.getNgayNhap());
			return "redirect:/Admin/ProductManagement";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Lỗi khi lưu file: " + e.getMessage());
			return "redirect:/Admin/AddProduct";
		}
	}

	@GetMapping("Admin/EditProduct/{sanPhamId}")
	public String Edit(Model model, @PathVariable("sanPhamId") Integer id) {
		SanPham item = sanPhamDAO.findById(id).get();
		model.addAttribute("item", item);
		pham = sanPhamDAO.findById(id).get();
		return "admin/EditProduct";
	}

	@Transactional
	@PostMapping("/Admin/EditProduct/update")
	public String updateProduct(SanPham item, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		SanPham sanPham = sanPhamDAO.findById(item.getId());

		if (StringUtils.isEmpty(item.getTenSanPham())) {
			item.setTenSanPham(sanPham.getTenSanPham());
		}
		if (item.getGia() == null) {
			item.setGia(sanPham.getGia());
		}

		// Lưu hình vào thư mục
		String imagePath = "classpath:static/images/";

		try {
			if (!file.isEmpty()) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());

				// Đường dẫn đến thư mục images trong thư mục static
				Path uploadDir = Path.of(ResourceUtils.getURL(imagePath).toURI());
				Path filePath = uploadDir.resolve(fileName);

				// Copy file vào thư mục images
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

				// Cập nhật tên file ảnh cho sản phẩm
				item.setHinhAnh(fileName);
			} else {
				item.setHinhAnh(sanPham.getHinhAnh());
			}
		} catch (Exception e) {
			// Xử lý lỗi khi lưu file
			redirectAttributes.addFlashAttribute("error", "Lỗi khi lưu file: " + e.getMessage());
			return "redirect:/Admin/ProductManagement";
		}

		// Xử lý và lưu thông tin sản phẩm vào cơ sở dữ liệu
		sanPhamDAO.updateSanPhamInfoById(item.getId(), item.getTenSanPham(), item.getGia(), item.getMoTa(),
				item.getHinhAnh());

		return "redirect:/Admin/ProductManagement";
	}

	@GetMapping("Admin/lockProduct/{sanPhamId}/{trangThai}")
	@Transactional
	public String delete(Model model, @PathVariable("sanPhamId") int id, @PathVariable("trangThai") Boolean trangThai) {
		SanPham sanPham = sanPhamDAO.findById(id);
		if (sanPham.isTrangThai()) {
			sanPham.setTrangThai(false);
		} else {
			sanPham.setTrangThai(true);
		}

		return "redirect:/Admin/ProductManagement";
	}

}
