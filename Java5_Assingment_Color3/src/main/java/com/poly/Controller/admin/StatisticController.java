package com.poly.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poly.DAO.StatisticRepository;
import com.poly.Model.DichVuStatistics;
import com.poly.Model.ProductStatistics;
import com.poly.Model.Statistic;
import com.poly.Service.DichVuService;
import com.poly.Service.ProductService;

import java.util.List;

@Controller
public class StatisticController {

	@Autowired
	private StatisticRepository statisticRepository;

	@Autowired
	private DichVuService dichVuService;

	@Autowired
	private ProductService productService;

	@GetMapping("/Admin/ThongKeDoanhThu")
	public String showStatistics(Model model) {
		List<Statistic> statistics = statisticRepository.getStatistics();
		model.addAttribute("statistics", statistics);
		return "/Admin/ThongKeDoanhThu";
	}

	@GetMapping("Admin/ThongKeDichVu")
	public String ThongKeDichVu(Model model) {
		List<Object[]> statistics = dichVuService.getDichVuStatistics();
		model.addAttribute("statistics", statistics);
		return "admin/ThongKeDichVu";
	}

	@GetMapping("Admin/ThongKeSanPham")
	public String ThongKeSanPham(Model model) {
		List<ProductStatistics> statistics = productService.getProductStatistics();
		model.addAttribute("statistics", statistics);
		return "admin/ThongKeSanPham";
	}
}
