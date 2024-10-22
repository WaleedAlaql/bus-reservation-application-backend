package com.waleed.BusReservation.service;

import com.waleed.BusReservation.entity.BusRoute;
import java.util.List;

public interface BusRouteService {
    BusRoute addBusRoute(BusRoute busRoute);
    List<BusRoute> getAllBusRoute();
    BusRoute getBusRouteByRouteName(String routeName);
    BusRoute getBusRouteByCityFromAndCityTo(String cityFrom, String cityTo);
}
