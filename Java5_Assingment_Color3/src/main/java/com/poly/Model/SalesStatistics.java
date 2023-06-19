package com.poly.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesStatistics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int month;
	private int year;
	private double totalRevenue;
	private double totalExpense;
	private double remainingAmount;

	// Constructors, getters, and setters
}
