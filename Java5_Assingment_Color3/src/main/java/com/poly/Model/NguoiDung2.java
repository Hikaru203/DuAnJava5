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
public class NguoiDung2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "nguoi_dung_id", nullable = false)
	public int nguoi_dung_id;

	@Column(name = "ho_ten")
	public String hoTen;

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
