package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer> {

	@Modifying
	@Query("UPDATE SanPham sp SET sp.tenSanPham = :tenSanPham, sp.gia = :gia, sp.moTa = :moTa, sp.hinhAnh = :hinhAnh WHERE sp.id = :id")
	void updateSanPhamInfoById(@Param("id") int id, @Param("tenSanPham") String tenSanPham, @Param("gia") double gia,
			@Param("moTa") String moTa, @Param("hinhAnh") String hinhAnh);

	@Query("SELECT s FROM SanPham s WHERE s.nhaCungCap.id = :nhaCungCapId")
	List<SanPham> findBySanPhamId(@Param("nhaCungCap") Integer nhaCungCapId);
	
	SanPham findById(int sp);

}