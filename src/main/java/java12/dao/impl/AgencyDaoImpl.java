package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.DataBaseConfig;
import java12.dao.AgencyDao;
import java12.entity.Address;
import java12.entity.Agency;
import java12.entity.Rent_info;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyDaoImpl implements AgencyDao {
    EntityManagerFactory entityManagerFactory = DataBaseConfig.entityManagerFactory();
    @Override
    public String saveAgencyToAddress(Agency agency, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Rent_info  rentInfo = new Rent_info();
        entityManager.getTransaction().begin();
        agency.setAddresses(address);
        rentInfo.setAgency(agency);
//        address.setAgency(agency);
        entityManager.persist(rentInfo);
        entityManager.persist(agency);
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        return agency.getName() + "Добавлен " + address.getCity();
    }

    @Override
    public String deleteAgencyToAddressAndRentInfo(Long AgencyId) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        Agency agency = entityManager.find(Agency.class, AgencyId);
//
//        Address addresses = agency.getAddresses();
//        addresses.setHouse(null);
//        for (Rent_info rentInfo : agency.getRentInfo()) {
//            rentInfo.setHouse(null);
//            rentInfo.setOwner(null);
//            rentInfo.setCustomer(null);
//        }
//        entityManager.remove(agency);
//        entityManager.getTransaction().commit();
//
////        if (addresses != null) addresses.setAgency(null);
////
////        if (agency != null) {
////            entityManager.remove(agency);
////            entityManager.getTransaction().commit();
////        }
//        entityManager.close();
//        return "Удалено";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency findAgency = entityManager.find(Agency.class, AgencyId);

            for (Rent_info rentInfo : findAgency.getRentInfo()) {
                rentInfo.setOwner(null);
                rentInfo.setHouse(null);
                rentInfo.setCustomer(null);
            }
            entityManager.remove(findAgency);
            entityManager.getTransaction().commit();
            return findAgency.getName() + " Successfully deleted";
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return "Failed: "+e.getMessage();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Agency> AgencyById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try {
            entityManager.getTransaction().begin();
           agency = entityManager
                    .createQuery("select a from  Agency  a where  a.id = ?1",Agency.class)
                     .setParameter(1,id)
                     .getSingleResult();
             entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return Optional.ofNullable(agency);
        }

    @Override
    public List<Agency> getAllAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
           agencies =entityManager
                    .createQuery("select a from Agency a ",Agency.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return agencies;
    }

    @Override
    public Boolean update(Long id, Agency newagency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            if (agency != null){
                entityManager.createQuery("""
              update Agency  a set a.name = ?1 where a.id = ?2""")
                        .setParameter(1,newagency.getName())
                        .setParameter(2,id)
                        .executeUpdate();
                entityManager.getTransaction().commit();
            }else {
                return false;
            }
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }

        return false;
    }


}
