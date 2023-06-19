package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "phuong_thuc_thanh_toan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhuongThucThanhToan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "phuong_thuc_id", nullable = false)
	public int phuong_thuc_id;

	@OneToOne
	@JoinColumn(name = "don_hang_id", nullable = false)
	public DonHang donHang;
}
