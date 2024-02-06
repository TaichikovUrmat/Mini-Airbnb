package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.DataBaseConfig;
import java12.dao.CustomerDao;
import java12.entity.Agency;
import java12.entity.Customer;
import java12.entity.House;
import java12.entity.Rent_info;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    EntityManagerFactory entityManagerFactory = DataBaseConfig.entityManagerFactory();

    @Override
    public String save(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Rent_info rentInfo = new Rent_info();
        rentInfo.setCustomer(customer);

        entityManager.persist(rentInfo);
        entityManager.persist(customer);

        entityManager.getTransaction().commit();
        entityManager.close();
        return customer.getFirs_Name() + "Добавлен";
    }
    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return  "Удалена";
    }

    @Override
    public Optional<Customer> CustomerById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try {
             entityManager.getTransaction().begin();
         customer = entityManager
                     .createQuery("select c from Customer c where c.id = ?1",Customer.class)
                     .setParameter(1,id)
                     .getSingleResult();
             entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally{
            entityManager.close();
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
           customers = entityManager
                    .createQuery("select c from Customer c",Customer.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().commit();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return customers;
    }

    @Override
    public boolean update(Long id, Customer newCustomer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            if (customer != null) {
                entityManager.createQuery("update Customer c set c.firs_Name = ?1 where  c.id= ?2")
                        .setParameter(1, newCustomer)
                        .setParameter(2, id)
                        .executeUpdate();
                entityManager.getTransaction().commit();
            }else {
                return false;
            }

        }catch (Exception e){
            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().isActive();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public String saveCustomerToRentIntoBronHouse(Customer newcustomer, List<Rent_info> newRentInfo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            newcustomer.setRentInfo(newRentInfo);
            entityManager.persist(newRentInfo);
            entityManager.persist(newRentInfo);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return newcustomer.getFirs_Name()+"Добавлен";
    }

    @Override
    public String CustomerBronHouse(Long houseId, Long customerId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
           EntityManager entityManager = entityManagerFactory.createEntityManager();
           try {
             entityManager.getTransaction().begin();

               House house = entityManager.createQuery("select h from House h where h.id = ?1",House.class)
                       .setParameter(1,houseId)
                       .getSingleResult();
               Agency agency = entityManager.createQuery("select a from Agency a where  a.id=?2",Agency.class)
                               .setParameter(2,agencyId)
                                       .getSingleResult();
               Customer customer = entityManager.find(Customer.class,customerId);
               Rent_info rentInfo = new Rent_info(checkIn,checkOut);

               rentInfo.setHouse(house);
               rentInfo.setCustomer(customer);
               rentInfo.setAgency(agency);

               customer.getRentInfo().add(rentInfo);
               agency.getRentInfo().add(rentInfo);
               house.setRentInfo(rentInfo);

               entityManager.getTransaction().commit();
           }catch (Exception e){
               if(entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
               System.out.println(e.getMessage());
           }finally {
               entityManager.close();
           }
        return customerId +"Добавлен";
    }

    @Override
    public void deleteCustomerToRentInf(Long customerId) {
//        Customer-ди очуруп жатканда. Ижарасы жок Customer-лер очсун, эгерде
//        ижарасы бар болсо checkout датасын текшерсин. Учурдагы датадан мурун
//        болсо rent_info customer-менен чогу очуп кетсин

//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//           entityManager.getTransaction().begin();
//
//           Customer customer = entityManager.find(Customer.class,customerId);
//           if(customer == null){
//               throw  new IllegalArgumentException("Клиент с ID " + customerId + " не найден");
//           }
//            Rent_info rentInfo = new Rent_info();
//           if (rentInfo != null){
//
//           }
//
//
//        }catch (Exception e){
//            if(entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
//            System.out.println(e.getMessage());
//        }finally {
//            entityManager.close();
//        }
    }





}
