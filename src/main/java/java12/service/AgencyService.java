package java12.service;

import java12.entity.Address;
import java12.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {
    String saveAgencyToAddress(Agency agency, Address address);
    String deleteAgencyToAddressAndRentInfo(Long AgencyId);

   Optional<Agency>  AgencyById(Long id);
    List<Agency> getAllAgency();
    Boolean update (Long id, Agency newagency);
}


