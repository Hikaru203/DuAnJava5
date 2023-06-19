package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.DichVu;
import com.poly.Model.DonHangDichVu;
import com.poly.Model.SanPhamMaGiamGia;

public interface DonHangDichVuDAO extends JpaRepository<DonHangDichVu, Integer> {
	@Query("SELECT s FROM DonHangDichVu s WHERE s.dichVu.id = :dichVuId")
	List<DonHangDichVu> findByDichVuId(@Param("dichVuId") Integer sanPhamId);
}



