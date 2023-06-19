package com.poly.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Statistic {
	@Id
	private String thangNam;
	private double tongThu;
	private double tongChi;
	private double loi;
}
