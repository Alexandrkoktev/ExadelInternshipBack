package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.NotificationAdapter;
import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.dto.NotificationMessageDTO;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.BookingRepository;
import com.exadelinternship.carpool.repository.NotificationRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationAdapter notificationAdapter;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    public JavaMailSender emailSender;

    public List<NotificationDTO> getAllNotifications(){
        List<NotificationDTO> result=new ArrayList<>();
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        notificationRepository.findAllByUser_Id(userId).stream()
                .sorted((x,y)->{return x.getDatetime().compareTo(y.getDatetime());})
                .forEach(notif -> { result.add(notificationAdapter.notifToNotifDTO(notif, userId));
        });
        return result;
    }

    public void setChecked(long id){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Notification notifiction = notificationRepository.getOne(id);
        if(notifiction!=null && notifiction.getUser().getId() == userId){
            notifiction.setChecked(true);
            notificationRepository.save(notifiction);
        }
    }

    public void delete(long id){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Optional<Notification> notification = notificationRepository.findById(id);
        if(notification.isPresent() && notification.get().getUser().getId()==userId){
            notificationRepository.delete(notification.get());
        }
    }

    public void sendBookingMessages(NotificationMessageDTO message){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if(message.getBookingId().length==1){
            Booking booking = bookingRepository.getOne(message.getBookingId()[0]);
            if(booking.getUser().getId()==userId){
                Notification notification = notificationAdapter
                        .createNotification(booking.getUser(),message.getInformation(),booking.getActiveRoute());
                notification.setDriver(true);
                notificationRepository.save(notification);
                SimpleMailMessage messageMail = new SimpleMailMessage();
                messageMail.setTo(booking.getUser().getEmail());
                messageMail.setSubject("Route message");
                messageMail.setText(message.getInformation());
                emailSender.send(messageMail);
            }
        }
    }

    public void sendRouteMessages(NotificationMessageDTO message){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Arrays.stream(message.getBookingId()).forEach(x->{
            Booking booking = bookingRepository.getOne(x);
            if(booking!=null && booking.getActiveRoute().isEnabled() && booking.getActiveRoute().getUser().getId()==userId){
                Notification notification = notificationAdapter
                        .createNotification(booking.getUser(),message.getInformation(),booking.getActiveRoute());
                notification.setDriver(false);
                notificationRepository.save(notification);
                SimpleMailMessage messageMail = new SimpleMailMessage();
                messageMail.setTo(booking.getUser().getEmail());
                messageMail.setSubject("Route message");
                messageMail.setText(message.getInformation());
                emailSender.send(messageMail);
            }
        });
    }
}
