package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.NguoiDung;


public interface UserDAO extends JpaRepository<NguoiDung, Integer>{
	NguoiDung findByTenDangNhap(String tenDangNhap);
	
	NguoiDung findByEmail(String email);
}