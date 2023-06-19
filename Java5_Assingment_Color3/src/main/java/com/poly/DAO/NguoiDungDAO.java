package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.NguoiDung;

public interface NguoiDungDAO extends JpaRepository<NguoiDung, Integer> {
	@Query("SELECT u from NguoiDung u where u.tenDangNhap=:userName")
	NguoiDung FindNguoiDungUserName(@Param("userName") String tenDangNhap);

	@Modifying
	@Query("UPDATE NguoiDung u SET u.hoTen = :hoTen, u.matKhau = :matKhau, u.email = :email, u.diaChi = :diaChi, u.soDienThoai = :soDienThoai, u.chucVu = :chucVu WHERE u.tenDangNhap = :userName")
	void updateNguoiDung(@Param("userName") String userName, @Param("hoTen") String hoTen,
			@Param("matKhau") String matKhau, @Param("email") String email, @Param("diaChi") String diaChi,
			@Param("soDienThoai") String soDienThoai, @Param("chucVu") Boolean chucVu);

	@Modifying
	@Query("UPDATE NguoiDung u SET u.khoa = :khoa WHERE u.tenDangNhap = :userName")
	void lockNguoiDung(@Param("userName") String userName, @Param("khoa") Boolean khoa);

	NguoiDung findByTenDangNhap(String tenDangNhap);

	NguoiDung findByEmail(String email);

	NguoiDung findById(int id);

}
