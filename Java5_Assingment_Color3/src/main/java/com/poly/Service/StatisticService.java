package com.poly.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.StatisticRepository;
import com.poly.Model.Statistic;

import java.util.List;

@Service
public class StatisticService {
	private final StatisticRepository statisticRepository;

	@Autowired
	public StatisticService(StatisticRepository statisticRepository) {
		this.statisticRepository = statisticRepository;
	}

	public List<Statistic> getStatistics() {
		return statisticRepository.getStatistics();
	}
}
