package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.BookingAdapter;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.dto.BookingFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.BookingRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    BookingAdapter bookingAdapter;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActiveRouteRepository activeRouteRepository;

    public List<BookingFastInformationDTO> getPageOfBookingsInformation(){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Booking> bookings = bookingRepository.getByUser_IdAndActiveRoute_Enabled(user.getId(), true);
        return bookingsToBookingsFastInformation(bookings.stream().collect(Collectors.toList()));
    }

    public BookingInformationDTO getBookingInformation(long id){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Booking booking = bookingRepository.getOne(id);
        if(booking!=null && booking.getUser().getId()==user.getId()) {
            return bookingAdapter.bookingToBookingInformationDTO(bookingRepository.getOne(id));
        } else{
            return null;
        }
    }

    public void addBooking(BookingAddingDTO bookingToAdd){
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        ActiveRoute activeRoute = activeRouteRepository.getOne(bookingToAdd.getActiveRouteId());
        if(activeRoute.getFreeSeats() != 0){
            Booking booking = bookingAdapter.bookingAddingToBooking(bookingToAdd,user,activeRoute);
            activeRoute.setFreeSeats((short)(activeRoute.getFreeSeats() - 1));
            activeRouteRepository.save(activeRoute);
            bookingRepository.save(booking);
        }
    }

    public void deleteBooking(long id){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Booking booking = bookingRepository.getOne(id);
        ActiveRoute activeRoute = activeRouteRepository.getOne(booking.getActiveRoute().getId());
        if(booking!=null && booking.getUser().getId()==user.getId()){
            bookingRepository.delete(booking);
            activeRoute.setFreeSeats((short)(activeRoute.getFreeSeats()+1));
        }
        else{

        }
    }

    private List<BookingFastInformationDTO> bookingsToBookingsFastInformation(List<Booking> activeRoutes){
        List<BookingFastInformationDTO> bookingFastInformationDTOS = new ArrayList<>();
        activeRoutes.stream()
                .forEach(x->bookingFastInformationDTOS.add(bookingAdapter.bookingToBookingFastInformationDTO(x)));
        return bookingFastInformationDTOS;
    }

    public List<BookingFastInformationDTO> getHistory(){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Booking> bookings = bookingRepository.getByUser_IdAndActiveRoute_Enabled(user.getId(),false)
                .stream().collect(Collectors.toList());
        return bookingsToBookingsFastInformation(bookings);

    }
}
