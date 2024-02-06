package java12.dao;

import java12.entity.Customer;
import java12.entity.Rent_info;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {


    String saveCustomerToRentIntoBronHouse(Customer newcustomer, List<Rent_info> newRentInfo);

    String CustomerBronHouse(Long houseId, Long customerId, Long agencyId,
                             LocalDate checkIn,LocalDate checkOut);

    void deleteCustomerToRentInf(Long customerId);

   //////// crud   /////////////
   String save(Customer customer);
   String delete(Long id);
   Optional<Customer> CustomerById(Long id);
   List<Customer> getAllCustomer();
   boolean update (Long id,Customer newCustomer);

   //////////////////////////////////////////////////////////////////















}
