package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.Model.ProductStatistics;

public interface ProductStatisticsRepository extends JpaRepository<ProductStatistics, Long> {

	@Query(value = "SELECT dh.don_hang_id, sp.ten_san_pham, " + "SUM(ct.so_luong) AS so_luong_ban, "
			+ "SUM(ct.gia_ban * ct.so_luong) AS tong_tien_ban, "
			+ "(SUM(nh.so_luong) - SUM(ct.so_luong)) AS so_luong_con_lai " + "FROM don_hang dh "
			+ "JOIN chi_tiet_don_hang ct ON dh.don_hang_id = ct.don_hang_id "
			+ "JOIN sanpham sp ON ct.san_pham_id = sp.id " + "LEFT JOIN kho nh ON sp.id = nh.san_pham_id "
			+ "GROUP BY dh.don_hang_id, sp.ten_san_pham", nativeQuery = true)
	List<ProductStatistics> getProductStatistics();

}
