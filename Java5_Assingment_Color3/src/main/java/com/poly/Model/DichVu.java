package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "dich_vu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	@Column(name = "ten_dich_vu")
	@NotBlank(message = "Tên dịch vụ không được để trống")
	public String tenDichVu;

	@Column(name = "mo_ta")
	public String moTa;

	@Column(name = "trang_thai")
	public boolean trangThai;

	@Column(name = "gia")
	@Positive(message = "Giá phải là số dương")
	@NotNull(message = "Giá không được để trống")
	public Double gia;


	@OneToMany(mappedBy = "dichVu")
	public List<DonHangDichVu> donHangDichVuList;
}
