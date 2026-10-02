package com.goroute.service;

import com.goroute.model.Route;

public interface RouteService {

    Route createRoute(Route route);

    Route getRouteById(Long id);

    Route findRoute(String source, String destination);
}