package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Model.MaGiamGia;

public interface MaGiamGiaDAO extends JpaRepository<MaGiamGia, Integer> {
	MaGiamGia findById(int maGiamGiaId);
}
