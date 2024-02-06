package java12.service.impl;

import java12.config.DataBaseConfig;
import java12.dao.CustomerDao;
import java12.dao.impl.CustomerDaoImpl;
import java12.entity.Customer;
import java12.entity.Rent_info;
import java12.service.CustomerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
CustomerDao customerDao = new CustomerDaoImpl();



////////////////////////// crud //////////////////////////////////

    @Override
    public String save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public String delete(Long id) {
        return customerDao.delete(id);
    }

    @Override
    public Optional<Customer> CustomerById(Long id) {
        return customerDao.CustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    @Override
    public boolean update(Long id, Customer newCustomer) {
        return customerDao.update(id,newCustomer);
    }
    @Override
    public void deleteCustomerToRentInf(Long customerId) {
        customerDao.deleteCustomerToRentInf(customerId);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String CustomerBronHouse(Long houseId, Long customerId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        return customerDao.CustomerBronHouse(houseId,customerId,agencyId,checkIn,checkOut);
    }

    @Override
    public String saveCustomerToRentIntoBronHouse(Customer newcustomer, List<Rent_info> newRentInfo) {
        return customerDao.saveCustomerToRentIntoBronHouse(newcustomer,newRentInfo);
    }

}
