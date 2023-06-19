package com.poly.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DAO.ChiTietDonHangDAO;
import com.poly.DAO.DichVuDAO;
import com.poly.DAO.DichVuRepository;
import com.poly.DAO.DonHangDichVuDAO;
import com.poly.Model.DichVuStatistics;

@Service
public class DichVuService {
	private final DichVuDAO dichVuDAO;
	private final DichVuRepository dichVuRepository;
	private final DonHangDichVuDAO donHangDichVuDAO;
	private final ChiTietDonHangDAO chiTietDonHangDAO;

	public DichVuService(DichVuDAO dichVuDAO, DonHangDichVuDAO donHangDichVuDAO, ChiTietDonHangDAO chiTietDonHangDAO,
			DichVuRepository dichVuRepository) {
		this.dichVuDAO = dichVuDAO;
		this.donHangDichVuDAO = donHangDichVuDAO;
		this.chiTietDonHangDAO = chiTietDonHangDAO;
		this.dichVuRepository = dichVuRepository;
	}

	public List<Object[]> getDichVuStatistics() {
		return dichVuRepository.getDichVuStatistics();
	}
}
