package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.Model.NguoiDung;
import com.poly.Model.NguoiDung2;

public interface NguoiDungDAO2 extends JpaRepository<NguoiDung2, Integer> {

}
