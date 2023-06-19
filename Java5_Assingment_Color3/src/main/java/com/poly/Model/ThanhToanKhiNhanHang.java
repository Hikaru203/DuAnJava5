package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "thanh_toan_khi_nhan_hang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhToanKhiNhanHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "thanh_toan_id")
	public int thanhToanId;

	@OneToOne
	@JoinColumn(name = "phuong_thuc_id")
	public PhuongThucThanhToan phuongThucThanhToan;

	@Column(name = "dia_chi_giao_hang")
	public String diaChiGiaoHang;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_giao_hang")
	Date ngayGiaoHang = new Date();
}
