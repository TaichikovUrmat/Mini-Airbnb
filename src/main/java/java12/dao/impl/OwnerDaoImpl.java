package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.DataBaseConfig;
import java12.dao.OwnerDao;
import java12.entity.Agency;
import java12.entity.House;
import java12.entity.Owner;
import java12.entity.Rent_info;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerDaoImpl implements OwnerDao {
    EntityManagerFactory entityManagerFactory = DataBaseConfig.entityManagerFactory();

    @Override
    public String saveOwner(Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Rent_info rentInfo = new Rent_info();
        rentInfo.setOwner(newOwner);
        entityManager.persist(newOwner);
        entityManager.persist(rentInfo);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Добавлен" + newOwner.getFirs_Name();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        try {
            entityManager.getTransaction().begin();
            owner = entityManager.createQuery("select o from Owner  o where  o.id = ?1", Owner.class)
                    .setParameter(1, id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(owner);
    }

    @Override
    public List<Owner> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Owner> owners = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            owners = entityManager
                    .createQuery("select o from Owner o", Owner.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return owners;
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.find(Owner.class, id);
            entityManager.remove(id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public Boolean update(Long id, Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);
            if (owner != null) {
                entityManager
                        .createQuery("update Owner  a set a.firs_Name = ?1 where a.id = ?2")
                        .setParameter(1, newOwner)
                        .setParameter(2, id)
                        .executeUpdate();
                entityManager.getTransaction().commit();
            } else {
                return false;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return false;
    }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String saveOwnerAndHouse(Owner newOwner, List<House> newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            newOwner.setHouses(newHouse);


            for (House house : newHouse) {
                entityManager.persist(house);
            }
            entityManager.persist(newOwner);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
                entityManager.close();

            return "sdf";
        }
    }

        @Override
        public String asSignOwnerToAgency(Long ownerId, Long agencyId){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Owner owner = null;
            Agency agency = null;

            try {
                entityManager.getTransaction().begin();
                 owner = entityManager.find(Owner.class, ownerId);
                 agency = entityManager.find(Agency.class,agencyId);
                if(owner == null){
                    return ("Владелец с ID " + ownerId + " не найден");
                }
                if (agency== null){
                    return ("Дом с ID " + agencyId + " не найден");
                }
                owner.getAgencies().add(agency);
                agency.getOwners().add(owner);
//                entityManager.persist(owner);
                entityManager.getTransaction().commit();
                return "Владелец успешно связан с агентством";
            }catch (Exception e){
                if(entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
                System.out.println(e.getMessage());
            }finally {
                entityManager.close();
            }
            return " добавлен";
        }

    @Override
    public void checkOwnerAndHouse(Long ownerID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;

        try {
          entityManager.getTransaction().begin();
             owner = entityManager.find(Owner.class, ownerID);
             //   ???????
            //   ?????????/

            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Owner> gelAllOwnerToAgencyId(Long agencyID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = null;
        try {
          entityManager.getTransaction().begin();
            owner =  entityManager
                 .createQuery("select o from  Owner  o join o.agencies a where a.id = ?1",Owner.class)
                    .setParameter(1,agencyID)
                 .getSingleResult();

         entityManager.getTransaction().commit();
//
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }

        return  Optional.ofNullable(owner);
    }

    @Override
    public void getAllOwnerToAge() {
//        Бардык owner-лердин аттарын жана жаштарын чыгарган метод болсун
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int owner = 0;
        try {
            entityManager.getTransaction().begin();
             owner = entityManager.createQuery(
                            "SELECT COUNT(o) FROM Owner o WHERE FUNCTION('age', CURRENT_DATE()) - FUNCTION('age', o.data_of_Birth) >= 18", Long.class)
                    .getSingleResult().intValue();
            System.out.println("Количество владельцев возрастом 18 и старше: " + owner);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage()); // Логирование ошибки
        } finally {
            entityManager.close();
        }
//        int owner = 0;
//        try {
//          entityManager.getTransaction().begin();
//            owner =  entityManager.createQuery("select count (*)from Owner o where function('age', current_date() )-function('age',o.data_of_Birth) >=18 ",int.class)
//                    .getFirstResult();
//            System.out.println("Количество владельцев возрастом 18 и старше: " + owner);
//            entityManager.getTransaction().commit();
//        }catch (Exception e){
//            if(entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
//            System.out.println(e.getMessage());
//        }finally {
//            entityManager.close();
//        }

    }
}

