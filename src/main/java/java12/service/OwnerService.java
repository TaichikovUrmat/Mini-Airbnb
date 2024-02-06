package java12.service;

import java12.entity.House;
import java12.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    String saveOwner(Owner newOwner);
    Optional<Owner> findById(Long id);
    List<Owner> getAll();
    String delete (Long id);
    Boolean update (Long id,Owner newOwner);

    ///////////////////////////////////////////////////////////////////

    String saveOwnerAndHouse(Owner newOwner,List<House> newHouse);

    String   asSignOwnerToAgency(Long ownerId, Long agencyId);
    Optional<Owner> gelAllOwnerToAgencyId(Long agencyID);
    void getAllOwnerToAge();

}
