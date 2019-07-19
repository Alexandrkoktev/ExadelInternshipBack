package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.ActiveRouteIdentityDTO;
import com.exadelinternship.carpool.dto.ActiveRouteInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<ActiveRouteFastInformationDTO> getPageOfActiveRoutesInformation(int pageNumber){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<ActiveRoute> activeRoutes = activeRouteRepository.getByUser_Id(user.getId());
        return activeRoutesToActiveRoutesFastInformation(getPageOfActiveRoutes(activeRoutes,pageNumber));
    }

    public ActiveRouteInformationDTO getActiveRouteInformation(long id){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ActiveRoute activeRoute = activeRouteRepository.getOne(id);
        if(activeRoute!=null && activeRoute.getUser().getId()==user.getId()) {
            return activeRouteAdapter.activeRouteToActiveRouteInformationDTO(activeRouteRepository.getOne(id));
        } else{
            return null;
        }
    }

    public void deleteActiveRoute(long id){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ActiveRoute activeRoute = activeRouteRepository.getOne(id);
        if(activeRoute!=null && activeRoute.getUser().getId()==user.getId()){
            activeRouteRepository.delete(activeRoute);
        }
        else{

        }
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
