package com.sticast.service.impl.it;

import com.sticast.conf.SpringBootConfig;
import com.sticast.entity.Forecast;
import com.sticast.entity.User;
import com.sticast.entity.enumeration.Answer;
import com.sticast.entity.enumeration.Type;
import com.sticast.service.ForecastService;
import com.sticast.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SpringBootConfig.class)
class ForecastServiceTest {
    @Autowired
    private ForecastService forecastService;

    private Forecast forecast;

    @BeforeEach
    void setUp(){
        forecast = new Forecast();
    }

    //Test domanda non esistente
    @Test
    void makeForecast_TC0() {
        assertThrows(ResponseStatusException.class, () -> forecastService.makeForecast(1,-1, forecast));
    }

    //Test utente non esistente
    @Test
    void makeForecast_TC1() {
        assertThrows(ResponseStatusException.class, () -> forecastService.makeForecast(-1,1, forecast));
    }

    //Test vendita share yes che fallisce
    @Test
    void makeForecast_TC2() {
        forecast.setQuantity(11);
        forecast.setAnswer(Answer.yes);
        forecast.setType(Type.sell);
        assertThrows(ResponseStatusException.class, () -> forecastService.makeForecast(1,1, forecast));
    }

    @Test
    void makeForecast_TC3() {
        forecast.setQuantity(11);
        forecast.setAnswer(Answer.no);
        forecast.setType(Type.sell);
        assertThrows(ResponseStatusException.class, () -> forecastService.makeForecast(1,1, forecast));
    }

    @Test
    void makeForecast_TC4() {
        forecast.setQuantity(100);
        forecast.setAnswer(Answer.no);
        forecast.setType(Type.buy);
        assertThrows(ResponseStatusException.class, () -> forecastService.makeForecast(1,1, forecast));
    }

    @Test
    void makeForecast_TC5() {
        forecast.setQuantity(100);
        forecast.setAnswer(Answer.yes);
        forecast.setType(Type.buy);
        assertThrows(ResponseStatusException.class, () -> forecastService.makeForecast(1,1, forecast));
    }
}