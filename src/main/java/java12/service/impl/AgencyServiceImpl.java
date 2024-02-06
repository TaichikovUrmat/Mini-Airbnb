package java12.service.impl;

import java12.dao.AgencyDao;
import java12.dao.impl.AgencyDaoImpl;
import java12.entity.Address;
import java12.entity.Agency;
import java12.service.AgencyService;

import java.util.List;
import java.util.Optional;

public class AgencyServiceImpl   implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();
    @Override
    public String saveAgencyToAddress(Agency agency, Address address) {
        return agencyDao.saveAgencyToAddress(agency,address);
    }

    @Override
    public String deleteAgencyToAddressAndRentInfo(Long AgencyId) {
        return agencyDao.deleteAgencyToAddressAndRentInfo(AgencyId);
    }

    @Override
    public Optional<Agency> AgencyById(Long id) {
        return agencyDao.AgencyById(id);
    }


    @Override
    public List<Agency> getAllAgency() {
        return agencyDao.getAllAgency();
    }

    @Override
    public Boolean update(Long id, Agency newagency) {
        return agencyDao.update(id,newagency);
    }
}
