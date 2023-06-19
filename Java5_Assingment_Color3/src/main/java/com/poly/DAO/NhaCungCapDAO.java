package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.Model.NhaCungCap;

@Repository
public interface NhaCungCapDAO extends JpaRepository<NhaCungCap, Integer> {

	@Modifying
	@Query("UPDATE NhaCungCap SET tenNhaCungCap = :tenNhaCungCap, diaChi = :diaChi, soDienThoai = :soDienThoai, email = :email WHERE id = :id")
	void updateNhaCungCap(@Param("id") int id, @Param("tenNhaCungCap") String tenNhaCungCap,
			@Param("diaChi") String diaChi, @Param("soDienThoai") String soDienThoai, @Param("email") String email);
	
}
