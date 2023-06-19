package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "nguoidung")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "nguoi_dung_id", nullable = false)
	public int nguoi_dung_id;

	@Column(name = "ten_dang_nhap", nullable = false)
	@NotBlank(message = "Tên đăng nhập không được trống")
	@Size(min = 5, message = "Tên đăng nhập phải có ít nhất 5 kí tự")
	public String tenDangNhap;

	@Column(name = "mat_khau", nullable = false)
	@NotBlank(message = "Mật khẩu không được trống")
	@Size(min = 5, message = "Mật khẩu phải có ít nhất 5 kí tự")
	public String matKhau;

	@Column(name = "email", nullable = false)
	@NotBlank(message = "Email không được trống")
	@Email(message = "Email không hợp lệ")
	public String email;

	@Column(name = "ho_ten")
	@NotBlank(message = "Họ tên không được trống")
	public String hoTen;

	@Column(name = "dia_chi")
	@NotBlank(message = "địa chỉ không được trống")
	public String diaChi;

	@Column(name = "so_dien_thoai")
	@Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại không hợp lệ")
	public String soDienThoai;

	@Column(name = "chuc_vu")
	@NotNull(message = "Chức vụ không được trống")
	public boolean chucVu;

	@Column(name = "khoa")
	public boolean khoa;

//	@OneToMany(mappedBy = "nguoiDung")
//	public List<GioHang> gioHangList;
//
//	@OneToMany(mappedBy = "nguoiDung")
//	public List<DonHang> donHangList;
//
//	@OneToMany(mappedBy = "nguoiDung")
//	public List<NguoiDungDichVu> nguoiDungDichVuList;
}