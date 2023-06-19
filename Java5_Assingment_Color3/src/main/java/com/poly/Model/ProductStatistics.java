package com.poly.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatistics {
	@Id
	private String tenSanPham;
	private int soLuongBan;
	private int soLuongConLai;
	private double tongTienBan;
}
