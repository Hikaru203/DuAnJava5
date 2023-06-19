package com.poly.DAO;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.DonHang;
import com.poly.Model.NguoiDung;

import jakarta.transaction.Transactional;

public interface DonHangDAO extends JpaRepository<DonHang, Integer> {
	@Transactional
    @Modifying
	@Query("Update DonHang SET tongGiaTri =:tongTien, trangThai = True WHERE trangThai = False AND nguoiDung =:nguoiDung  ")
	void updateTongGiaTriAndTrangThai(@Param("tongTien") Double tongTien,@Param("nguoiDung") NguoiDung nguoiDung);
}

