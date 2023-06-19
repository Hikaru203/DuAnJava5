package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "nhacungcap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "ten_nhacungcap", nullable = false)
	@NotBlank(message = "Tên nhà cung cấp không được để trống")
	public String tenNhaCungCap;

	@Column(name = "dia_chi")
	@NotBlank(message = "Địa chỉ không được để trống")
	public String diaChi;

	@Column(name = "so_dien_thoai")
	@Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
	public String soDienThoai;

	@Column(name = "email")
	@Email(message = "Địa chỉ email không hợp lệ")
	@NotBlank(message = "email không được để trống")
	public String email;

	@OneToMany(mappedBy = "nhaCungCap")
	public List<SanPham> sanPhamList;

}
