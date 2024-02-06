package java12.service;

import java12.entity.House;
import java12.entity.Owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HouseService {

    // crud
    String save(House house);
    String delete (Long houseId);
    Optional<House> findByID(Long Id);
    List<House> getAll();

    Boolean update  (Long id, House newHouse);
    ////////////////////////////////////////////////////////
    String saveHouseAndOwner(House newHouse, Owner newOwner);

    String deleteHouse(Long houseId);

    List<House> getRegion(String region);

    List<House> getAgencyHouses(Long agencyId);

    List<House> getOwnerHouses(Long id);

    List<House> getCheck(LocalDate checkin, LocalDate checkout);
}
