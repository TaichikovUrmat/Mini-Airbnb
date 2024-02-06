package java12.service.impl;

import java12.dao.Rent_infoDao;
import java12.dao.impl.Rent_infoDaoImpl;
import java12.service.Rent_infoService;

import java.time.LocalDate;

public class Rent_infoServiceImpl implements Rent_infoService {

  Rent_infoDao rentInfoDao = new Rent_infoDaoImpl();
    @Override
    public String getCheckinAndCherOut(LocalDate checkin, LocalDate checkOut) {
        return rentInfoDao.getCheckinAndCherOut(checkin,checkOut);
    }
}
