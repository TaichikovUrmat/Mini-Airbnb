package java12.service.impl;

import java12.dao.OwnerDao;
import java12.dao.impl.OwnerDaoImpl;
import java12.entity.House;
import java12.entity.Owner;
import java12.service.OwnerService;

import java.util.List;
import java.util.Optional;

public class OwnerServiceImpl implements OwnerService {
    OwnerDao ownerDao = new OwnerDaoImpl();
    @Override
    public String saveOwner(Owner newOwner) {
        return ownerDao.saveOwner(newOwner);
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerDao.findById(id);
    }

    @Override
    public List<Owner> getAll() {
        return ownerDao.getAll();
    }

    @Override
    public String delete(Long id) {
        return ownerDao.delete(id);
    }

    @Override
    public Boolean update(Long id, Owner newOwner) {
        return  ownerDao.update(id,newOwner);
    }

    @Override
    public String saveOwnerAndHouse(Owner newOwner, List<House> newHouse) {
        return ownerDao.saveOwnerAndHouse(newOwner,newHouse);
    }

    @Override
    public String asSignOwnerToAgency(Long ownerId, Long agencyId) {
        return ownerDao.asSignOwnerToAgency(ownerId, agencyId);
    }

    @Override
    public Optional<Owner> gelAllOwnerToAgencyId(Long agencyID) {
        return ownerDao.gelAllOwnerToAgencyId(agencyID);
    }

    @Override
    public void getAllOwnerToAge() {
        ownerDao.getAllOwnerToAge();
    }
}
