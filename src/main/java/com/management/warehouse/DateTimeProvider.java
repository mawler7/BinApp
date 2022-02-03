package com.management.warehouse;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DateTimeProvider {

    public static LocalDateTime getLocalDateTimeNowTruncatedToMinutes(){
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }
}
