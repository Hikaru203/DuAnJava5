package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Model.ChiTietDonHang;

public interface ChiTietDonHangDAO extends JpaRepository<ChiTietDonHang, Integer> {
    List<ChiTietDonHang> findBySanPhamId(Integer sanPhamId);

}

