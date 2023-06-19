package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Model.GioHang;
import com.poly.Model.NguoiDung;

public interface GioHangDAO extends JpaRepository<GioHang, Integer> {

	GioHang findBynguoiDung(NguoiDung nguoiDung);
}
