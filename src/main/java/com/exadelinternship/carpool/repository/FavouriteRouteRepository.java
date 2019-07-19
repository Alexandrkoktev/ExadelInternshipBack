package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Car;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FavouriteRouteRepository extends JpaRepository<FavouriteRoute, Long> {
    Set<FavouriteRoute> findAllByUser_Id(long id);
}
