package com.goroute.service.impl;

import com.goroute.model.Route;
import com.goroute.repository.RouteRepository;
import com.goroute.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    @Override
    public Route findRoute(String source, String destination) {
        return routeRepository.findBySourceAndDestination(
                source,
                destination
        ).orElseThrow(() ->
                new RuntimeException("Route not found"));
    }
}