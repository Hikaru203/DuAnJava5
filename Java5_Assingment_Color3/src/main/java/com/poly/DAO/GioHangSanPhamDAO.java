package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Model.GioHang;
import com.poly.Model.GioHangSanPham;

public interface GioHangSanPhamDAO extends JpaRepository<GioHangSanPham, Integer> {
    List<GioHangSanPham> findBySanPhamId(Integer sanPhamId);

    List<GioHangSanPham> findByGioHang(GioHang gioHang);
    
}

