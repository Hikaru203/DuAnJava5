package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.Model.Statistic;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, String> {
	@Query(value = "SELECT FORMAT(CONVERT(date, t1.thang_nam + '-01'), 'MM-yyyy') AS thang_nam, "
			+ "COALESCE(t2.tong_thu, 0) AS tong_thu, " + "COALESCE(t3.tong_chi, 0) AS tong_chi, "
			+ "COALESCE(t2.tong_thu, 0) - COALESCE(t3.tong_chi, 0) AS loi " + "FROM ( "
			+ "    SELECT DISTINCT CONVERT(VARCHAR(7), ngay_dat_hang, 126) AS thang_nam " + "    FROM don_hang "
			+ "    UNION " + "    SELECT DISTINCT CONVERT(VARCHAR(7), ngay_nhap, 126) AS thang_nam " + "    FROM kho "
			+ ") t1 " + "LEFT JOIN ( " + "    SELECT CONVERT(VARCHAR(7), ngay_dat_hang, 126) AS thang_nam, "
			+ "           SUM(tong_gia_tri) AS tong_thu " + "    FROM don_hang "
			+ "    GROUP BY CONVERT(VARCHAR(7), ngay_dat_hang, 126) " + ") t2 ON t1.thang_nam = t2.thang_nam "
			+ "LEFT JOIN ( " + "    SELECT CONVERT(VARCHAR(7), ngay_nhap, 126) AS thang_nam, "
			+ "           SUM(gia_nhap * so_luong) AS tong_chi " + "    FROM kho "
			+ "    GROUP BY CONVERT(VARCHAR(7), ngay_nhap, 126) " + ") t3 ON t1.thang_nam = t3.thang_nam "
			+ "ORDER BY t1.thang_nam;", nativeQuery = true)

	List<Statistic> getStatistics();
}
