package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.ActiveRouteIdentityDTO;
import com.exadelinternship.carpool.dto.ActiveRouteInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActiveRouteService {

    private final int PAGE_SIZE = 10;

    @Autowired
    ActiveRouteRepository activeRouteRepository;
    @Autowired
    ActiveRouteAdapter activeRouteAdapter;

    public List<ActiveRouteFastInformationDTO> getPageOfActiveRoutesInformation(ActiveRouteIdentityDTO activeRouteIdentity){
        Set<ActiveRoute> activeRoutes = activeRouteRepository.getByUser_Id(activeRouteIdentity.getUserId());
        return activeRoutesToActiveRoutesFastInformation(getPageOfActiveRoutes(activeRoutes,PAGE_SIZE));
    }

    public ActiveRouteInformationDTO getActiveRouteInformation(long id){
        return activeRouteAdapter.activeRouteToActiveRouteInformationDTO(activeRouteRepository.getOne(id));
    }

    private List<ActiveRoute> getPageOfActiveRoutes(Set<ActiveRoute> activeRoutes, int pageNumber){
        return activeRoutes.stream()
                .sorted((x,y)->{return x.getTimeAndDate().compareTo(y.getTimeAndDate());})
                .skip(pageNumber*PAGE_SIZE).limit(PAGE_SIZE).collect(Collectors.toList());
    }
    private List<ActiveRouteFastInformationDTO> activeRoutesToActiveRoutesFastInformation(List<ActiveRoute> activeRoutes){
        List<ActiveRouteFastInformationDTO> activeRouteFastInformationDTOS = new ArrayList<>();
        activeRoutes.stream()
                .forEach(x->activeRouteFastInformationDTOS.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(x)));
        return activeRouteFastInformationDTOS;
    }
}
