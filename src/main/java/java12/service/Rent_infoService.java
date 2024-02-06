package java12.service;

import java.time.LocalDate;

public interface Rent_infoService {
    String getCheckinAndCherOut(LocalDate checkin, LocalDate checkOut);
}
