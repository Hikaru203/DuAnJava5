package com.poly.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "thanh_toan_qua_the")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhToanQuaThe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "thanh_toan_id")
	public int thanhToanId;

	@OneToOne
	@JoinColumn(name = "phuong_thuc_id")
	public PhuongThucThanhToan phuongThucThanhToan;

	@Column(name = "so_the")
	public String soThe;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_het_han")
	Date ngayHetHan = new Date();

	@Column(name = "ma_bao_mat")
	public String maBaoMat;
}
