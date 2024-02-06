package java12.service.impl;

import java12.dao.HouseDao;
import java12.dao.impl.HouseDaoImpl;
import java12.entity.House;
import java12.entity.Owner;
import java12.service.HouseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HouseServiceImpl implements HouseService {

  HouseDao houseDao = new HouseDaoImpl();

  ///////////////  crud  //////////////////
    @Override
    public String save(House house) {
        return houseDao.save(house);
    }

    @Override
    public String delete(Long houseId) {
        return houseDao.delete(houseId);
    }

    @Override
    public Optional<House> findByID(Long Id) {
        return houseDao.findByID(Id);
    }

    @Override
    public List<House> getAll() {
        return houseDao.getAll();
    }

    @Override
    public Boolean update(Long id, House newHouse) {
        return houseDao.update(id,newHouse);
    }

    @Override
    public String saveHouseAndOwner(House newHouse, Owner newOwner) {
        return houseDao.saveHouseAndOwner(newHouse,newOwner);
    }

    @Override
    public String deleteHouse(Long houseId) {
        return houseDao.deleteHouse(houseId);
    }

    @Override
    public List<House> getRegion(String region) {
        return houseDao.getRegion(region);
    }

    @Override
    public List<House> getAgencyHouses(Long agencyId) {
        return houseDao.getAgencyHouses(agencyId);
    }

    @Override
    public List<House> getOwnerHouses(Long id) {
        return houseDao.getOwnerHouses(id);
    }

    @Override
    public List<House> getCheck(LocalDate checkin, LocalDate checkout) {
        return getCheck(checkin,checkout);
    }


    //////////////////////////////////////////////////////////////////////////////
}
