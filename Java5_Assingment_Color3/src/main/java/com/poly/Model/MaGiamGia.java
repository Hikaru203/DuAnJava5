package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "magiamgia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaGiamGia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Lỗi không được để trống")
	@Column(name = "ma", nullable = false)
	private String ma;

	@NotNull(message = "Lỗi không được để trống")
	@Column(name = "gia_tri", nullable = false)
	@DecimalMin(value = "0", inclusive = true, message = "Giá trị phải lớn hơn hoặc bằng 0")
	@DecimalMax(value = "100", inclusive = true, message = "Giá trị phải nhỏ hơn hoặc bằng 100")
	private Double giaTri;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_bat_dau")
	private Date ngayBatDau = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_ket_thuc")
	private Date ngayKetThuc = new Date();

	@NotNull(message = "Lỗi không được để trống")
	@Min(value = 0, message = "Số lượng sản phẩm phải lớn hơn hoặc bằng 0")
	@Column(name = "so_luong_san_pham", nullable = false)
	private Integer soLuongSanPham;

	@OneToMany(mappedBy = "maGiamGia")
	private List<SanPhamMaGiamGia> sanPhamMaGiamGiaList;
}
