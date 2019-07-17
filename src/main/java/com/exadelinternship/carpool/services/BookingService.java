package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.BookingAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteIdentityDTO;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.dto.BookingFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.BookingRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final int PAGE_SIZE = 10;

    @Autowired
    BookingAdapter bookingAdapter;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActiveRouteRepository activeRouteRepository;

    public List<BookingFastInformationDTO> getPageOfBookingsInformation(ActiveRouteIdentityDTO activeRouteIdentity){
        Set<Booking> bookings = bookingRepository.getByUser_Id(activeRouteIdentity.getUserId());
        return activeRoutesToActiveRoutesFastInformation(getPageOfActiveRoutes(bookings,PAGE_SIZE));
    }

    public BookingInformationDTO getBookingInformation(long id){
        return bookingAdapter.bookingToBookingInformationDTO(bookingRepository.getOne(id));
    }

    public void addBooking(BookingAddingDTO bookingToAdd){
        User user = userRepository.getOne(bookingToAdd.getUserId());
        ActiveRoute activeRoute = activeRouteRepository.getOne(bookingToAdd.getActiveRouteId());
        Booking booking = bookingAdapter.bookingAddingToBooking(bookingToAdd,user,activeRoute);
        bookingRepository.save(booking);
    }

    public void deleteBooking(long id){
        bookingRepository.deleteById(id);
    }

    private List<Booking> getPageOfActiveRoutes(Set<Booking> activeRoutes, int pageNumber){
        return activeRoutes.stream()
                .sorted((x,y)->{return x.getActiveRoute().getTimeAndDate().compareTo(y.getActiveRoute().getTimeAndDate());})
                .skip(pageNumber*PAGE_SIZE).limit(PAGE_SIZE).collect(Collectors.toList());
    }
    private List<BookingFastInformationDTO> activeRoutesToActiveRoutesFastInformation(List<Booking> activeRoutes){
        List<BookingFastInformationDTO> bookingFastInformationDTOS = new ArrayList<>();
        activeRoutes.stream()
                .forEach(x->bookingFastInformationDTOS.add(bookingAdapter.bookingToBookingFastInformationDTO(x)));
        return bookingFastInformationDTOS;
    }
}
