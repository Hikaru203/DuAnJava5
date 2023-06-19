package com.poly.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.poly.DAO.DichVuDAO;
import com.poly.Model.DichVu;

@Controller
public class ServiceController {
	@Autowired
	DichVuDAO dichVuDAO;

	@GetMapping("dichvu")
	public String service(Model model) {
		List<DichVu> listDichVu = new ArrayList<>();
		for (DichVu dichVu : dichVuDAO.findAll()) {
			if (dichVu.isTrangThai()) {
				listDichVu.add(dichVu);
			}
		}
		model.addAttribute("ListDichVu", listDichVu);
		return "service";
	}

	@GetMapping("dichVuChiTiet/{id}")
	public String serviceChiTiet(Model model, @PathVariable("id") int id) {
		DichVu dichVu = dichVuDAO.findById(id);
		model.addAttribute("DichVu", dichVu);

		return "serviceDetails";
	}
}
