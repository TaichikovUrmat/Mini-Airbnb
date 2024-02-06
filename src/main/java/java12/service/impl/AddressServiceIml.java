package java12.service.impl;

import java12.dao.AddressDao;
import java12.dao.impl.AddressDaoImpl;
import java12.entity.Address;
import java12.service.AddressService;

import java.util.List;
import java.util.Optional;

public class AddressServiceIml implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public String delete(Long addressId) {
        return addressDao.delete(addressId);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    @Override
    public Boolean update(Long id, Address newAddress) {
        return addressDao.update(id,newAddress);
    }

    @Override
    public List<Address> getAllAddressToAgency() {
        return addressDao.getAllAddressToAgency();
    }
}
