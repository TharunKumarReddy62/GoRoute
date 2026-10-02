package com.goroute.repository;

import com.goroute.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findBySourceAndDestination(
            String source,
            String destination
    );
}