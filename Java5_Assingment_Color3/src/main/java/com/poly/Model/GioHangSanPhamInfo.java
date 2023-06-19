package com.poly.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class GioHangSanPhamInfo {
	@Id
	int nguoiDungId;
	int idSanPham;
	String tenSanPham;
	double gia;
	String moTa;
	String hinhAnh;
	int soLuongSanPham;

	// Các phương thức getter/setter
}
