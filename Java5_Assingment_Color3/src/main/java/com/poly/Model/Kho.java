package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "kho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id", nullable = false)
	public int id;

	@ManyToOne
	@JoinColumn(name = "san_pham_id", nullable = false)
	public SanPham sanPham;

	@Column(name = "so_luong", nullable = false)
	@Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
	@NotNull(message = "Số lượng không được để trỗng")
	public Integer soLuong;

	@Column(name = "gia_nhap", nullable = false)
	@Min(value = 0, message = "Giá nhập phải lớn hơn hoặc bằng 0")
	@NotNull(message = "giá nhập không được để trỗng")
	public Double giaNhap;

	@Column(name = "ngay_nhap")
	@Temporal(TemporalType.DATE)
	@NotNull
	public Date ngayNhap;
}
