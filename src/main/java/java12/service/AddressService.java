package java12.service;

import java12.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    String delete (Long addressId);
    Optional<Address> findById(Long id);
    List<Address> getAll();
    Boolean update (Long id,Address newAddress);

    ////////////////////////////////////////////////////
    List<Address> getAllAddressToAgency();
}
