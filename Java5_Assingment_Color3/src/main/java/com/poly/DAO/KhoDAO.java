package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.Kho;

import jakarta.transaction.Transactional;

public interface KhoDAO extends JpaRepository<Kho, Integer> {
	Kho findBySanPhamId(Integer sanPhamId);

	@Transactional
	@Modifying
	@Query("UPDATE Kho k SET k.soLuong = :soLuong WHERE k.sanPham.id = :id")
	void updateSoLuongBySanPham(Integer id, Integer soLuong);
	
//	@Query("UPDATE NhaCungCap SET tenNhaCungCap = :tenNhaCungCap, diaChi = :diaChi, soDienThoai = :soDienThoai, email = :email WHERE id = :id")
//	void updateNhaCungCap(@Param("id") int id, @Param("tenNhaCungCap") String tenNhaCungCap,
//			@Param("diaChi") String diaChi, @Param("soDienThoai") String soDienThoai, @Param("email") String email);
	
	
}
