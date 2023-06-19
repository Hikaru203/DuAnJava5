package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Model.DichVu;
import com.poly.Model.DichVuStatistics;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Long> {

    @Query("SELECT dv.tenDichVu AS ten_dich_vu, COUNT(dd.dichVu.id) AS so_luong, SUM(dv.gia) AS tong_tien " +
            "FROM DichVu dv JOIN DonHangDichVu dd ON dv.id = dd.dichVu.id " +
            "GROUP BY dv.tenDichVu")
    List<Object[]> getDichVuStatistics();
}
