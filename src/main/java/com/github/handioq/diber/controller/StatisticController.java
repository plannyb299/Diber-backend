package com.github.handioq.diber.controller;

import com.github.handioq.diber.model.dto.StatisticsDto;
import com.github.handioq.diber.service.AddressService;
import com.github.handioq.diber.service.OrderService;
import com.github.handioq.diber.service.UserService;
import com.github.handioq.diber.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_URL + Constants.URL_STATISTICS)
public class StatisticController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;

    @Autowired
    public StatisticController(UserService userService, OrderService orderService, AddressService addressService) {
        this.userService = userService;
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getStatistic() {
        LOGGER.info("Start getStatistics");
        Long usersCount = userService.count();
        Long ordersCount = orderService.count();
        Long addressesCount = addressService.count();
        StatisticsDto statisticsDto = new StatisticsDto(usersCount, ordersCount, addressesCount);
        return new ResponseEntity<>(statisticsDto, HttpStatus.OK);
    }

}
