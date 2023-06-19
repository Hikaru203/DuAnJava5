package com.poly.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.ProductStatisticsRepository;
import com.poly.Model.ProductStatistics;

import java.util.List;

@Service
public class ProductService {

	private final ProductStatisticsRepository statisticsRepository;

	@Autowired
	public ProductService(ProductStatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}

	public List<ProductStatistics> getProductStatistics() {
		return statisticsRepository.getProductStatistics();
	}
}
