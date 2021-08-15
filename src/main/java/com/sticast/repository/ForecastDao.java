package com.sticast.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Forecast;
import com.sticast.entity.User;

@Repository("ForecastRepository")
public interface ForecastDao extends JpaRepository<Forecast, Integer> {
	List<Forecast> findByUser(User user);
}
