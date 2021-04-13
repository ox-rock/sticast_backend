package com.sticast.service;

import java.util.List;
import com.sticast.entity.Forecast;
import com.sticast.entity.User;

public interface ForecastService {

	public Double calculatePayout(Forecast forecast);
	public void makeForecast(Forecast forecast);
	public List<Forecast> findByUsername_Id(User use);
}
