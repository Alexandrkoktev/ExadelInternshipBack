package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Car;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRouteRepository extends JpaRepository<FavouriteRoute, Long> {
}
