package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.DataBaseConfig;
import java12.dao.AddressDao;
import java12.entity.Address;
import java12.entity.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao {

    EntityManagerFactory entityManagerFactory = DataBaseConfig.entityManagerFactory();

    @Override
    public String delete(Long addressId) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            entityManager.getTransaction().begin();
//            Address address = entityManager.find(Address.class, addressId);
//              for (House house: address.getHouse())
//
//        }catch (Exception e){
//            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
//            return " Failed"+e.getMessage();
//        }finally {
//            entityManager.close();
//        }
        return null;
    }

    @Override
    public Optional<Address> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address address = null;
        try {
            entityManager.getTransaction().begin();
         address= entityManager.createQuery("select  a from  Address a where  a.id = ?1",Address.class)
                    .setParameter(1,id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return Optional.ofNullable(address);
    }

    @Override
    public List<Address> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addressList = new ArrayList<>();
        try {
          entityManager.getTransaction().begin();
         addressList = entityManager
                  .createQuery("select a from Address a",Address.class)
                  .getResultList();
          entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return addressList;
    }

    @Override
    public Boolean update(Long id, Address newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);
            if (address != null){
                entityManager.createQuery("update  Address  a set a.city = ?1 where  a.id= ?2 ")
                        .setParameter(1,newAddress.getCity())
                        .setParameter(2,id)
                        .executeUpdate();
                entityManager.getTransaction().commit();
            }else {
                return false;
            }
        }catch (Exception e){
            if(entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public List<Address> getAllAddressToAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addressList =  new ArrayList<>();

        try {
          entityManager.getTransaction().begin();
         addressList = entityManager
                  .createQuery("select a  from  Address a inner join  Agency  ag on a.id = ag.id ",Address.class)
                  .getResultList();
          entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return addressList;
    }
}
