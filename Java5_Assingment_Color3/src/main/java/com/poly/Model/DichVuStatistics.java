package com.poly.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DichVuStatistics {
	@Id
	private String tenDichVu;
	private long soLuong;
	private double tongTien;
}
