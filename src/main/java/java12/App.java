package java12;

import java12.config.DataBaseConfig;
import java12.dao.CustomerDao;
import java12.dao.HouseDao;
import java12.dao.impl.CustomerDaoImpl;
import java12.entity.*;
import java12.enums.FamilyStatus;
import java12.enums.Gender;
import java12.enums.HouseType;
import java12.service.*;
import java12.service.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    //    DataBaseConfig.entityManagerFactory();
       AgencyService agencyService = new AgencyServiceImpl();
       AddressService addressService = new AddressServiceIml();
        CustomerService customerService =  new CustomerServiceImpl();
        HouseService houseService  = new HouseServiceImpl();
        OwnerService ownerService =  new OwnerServiceImpl();
        Rent_infoService rentInfo = new Rent_infoServiceImpl();
    //    AddressService addressService = new AddressServiceIml();
//        agencyService.saveAgencyToAddress(new Agency("Eldar",
//                778000600),new Address("Bishkek","Kochkor-Ata","Mombekov"));
      //  agencyService.deleteAgencyToAddressAndRentInfo(4L);
    //    System.out.println(agencyService.AgencyById(5L));

     //   System.out.println(agencyService.getAllAgency());
      //  System.out.println(agencyService.update(5L, new Agency("Nurlan", 0777434356)));

        ///////////// Address  ///////////////////////////////
//        System.out.println(addressService.getAll());
//         addressService.update(1L, new Address("Osh","Karasu","Toktomuratov"));
//        System.out.println(addressService.findById(2L));
//     System.out.println(addressService.getAllAddressToAgency());

        ////////////////// Customer //////////////////////////////
//        customerService.save(new Customer("Urmar","Taichikov","urmat@gmail.com", LocalDate.of(2003,6,5), Gender.MALE,"Kyrgyz", FamilyStatus.NOT_MARRIED));
//        customerService.save(new Customer("Nurlan","Nurrrr","nurlan@gmail.com", LocalDate.of(2006,6,6), Gender.MALE,"Kyrgyz", FamilyStatus.NOT_MARRIED));
//        customerService.save(new Customer("Myxa","Muxaaat","Muxa@gmail.com", LocalDate.of(2007,7,7), Gender.MALE,"Kyrgyz", FamilyStatus.NOT_MARRIED));
//        Rent_info rentInfo = new Rent_info(LocalDate.of(2002,3,4),LocalDate.of(2004,5,7));
//        List<Rent_info> rentInfos = new ArrayList<>();
//        rentInfos.add(rentInfo);
//        customerService.saveCustomerToRentIntoBronHouse(new Customer("Syimonkyl","MambetAsanov","@gmail.com",
//             LocalDate.of(2003,6,5), Gender.MALE,"Kyrgyz", FamilyStatus.NOT_MARRIED),rentInfos);
//        System.out.println(customerService.delete(1L));
    //    System.out.println(customerService.CustomerById(2L));
//        System.out.println(customerService.getAllCustomer());

        /////////////////////////////// House  //////////////////////////////////////////////////
//     House house = new House(HouseType.HOUSE_S,8.1,"jakshy",9,true);
//      houseService.save(house);
//        System.out.println(houseService.getAll());
      //  System.out.println(houseService.findByID(12L));
//          houseService.saveHouseAndOwner(new House(HouseType.HOUSE_S,1.2,"Хороший",3,true)
//                  ,new Owner("Nurti","Nuur","nuur@gmail.com",LocalDate.of(2005,9,7),Gender.MALE));



        //////////////////////// owner  /////////////////////////////////////////////////////////
//        Owner owner = new Owner("Uson","Akkulov","Uson@gmail.com",LocalDate.of(2004,9,5),Gender.MALE);
//        System.out.println(ownerService.saveOwner(owner));
//        System.out.println(ownerService.getAll());
//        System.out.println(ownerService.findById(2L));
//        House house = new House(HouseType.HOUSE_S,5.1,"jakshy",5,true);
//        List<House> hh = new ArrayList<>();
//        hh.add(house);
//        ownerService.saveOwnerAndHouse(new Owner("Myrzaiym","Keldibekova","myr@gmail.com",
//                LocalDate.of(2004,9,5),Gender.FEMALE),hh);
//        System.out.println(ownerService.asSignOwnerToAgency(2L, 7L));

//        System.out.println(ownerService.gelAllOwnerToAgencyId(7L));
//          ownerService.getAllOwnerToAge();
        //////////////////////// Rent_info  //////////////////////////////



    }
}
