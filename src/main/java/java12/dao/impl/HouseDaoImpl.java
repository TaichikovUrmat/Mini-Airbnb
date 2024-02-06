package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.DataBaseConfig;
import java12.dao.HouseDao;
import java12.entity.Address;
import java12.entity.House;
import java12.entity.Owner;
import java12.entity.Rent_info;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HouseDaoImpl  implements HouseDao {
    EntityManagerFactory entityManagerFactory = DataBaseConfig.entityManagerFactory();

    @Override
    public String save(House house) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Rent_info rentInfo = new Rent_info();
        rentInfo.setHouse(house);
        entityManager.persist(house);
        entityManager.persist(rentInfo);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "jhg";
    }

    @Override
    public String delete(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, houseId);


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
    public Optional<House> findByID(Long Id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try {
            entityManager.getTransaction().begin();
            house = entityManager.createQuery("select   h from  House  h where h.id = ?1", House.class)
                    .setParameter(1, Id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(house);
    }

    @Override
    public List<House> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from  House h", House.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public Boolean update(Long id, House newHouse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            if (house != null) {
                entityManager.createQuery("update House  h set h.room= ?1 where h.id = ?2")
                        .setParameter(1, newHouse.getRoom())
                        .setParameter(2, id)
                        .executeUpdate();
                entityManager.getTransaction().commit();
            } else {
                return false;
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String saveHouseAndOwner(House newHouse, Owner newOwner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Rent_info rentInfo = new Rent_info();
            Address address = new Address();

            newHouse.setOwner(newOwner);
            rentInfo.setHouse(newHouse);;
            newHouse.setAddress(address);

            entityManager.persist(rentInfo);
            entityManager.persist(newHouse);
            entityManager.persist(address);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return " Добавлен";
    }

    @Override
    public String deleteHouse(Long houseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            House house = entityManager.find(House.class, houseId);
            if (house != null) {
                Rent_info rentInfo = house.getRentInfo();
                if (rentInfo != null) {
                    LocalDate currentDate = LocalDate.now();
                    LocalDate moveOutDate = rentInfo.getCheckOut();
                    if (moveOutDate != null && moveOutDate.isBefore(currentDate)) {
                        entityManager.remove(rentInfo);
                        entityManager.remove(house);
                    } else {
                        return "Нельзя удалить дом, так как арендный срок еще не истек.";
                    }
                } else {
                    entityManager.remove(house);
                }
                entityManager.getTransaction().commit();
            } else {
                return "Дом с ID " + houseId + " не найден.";
            }
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<House> getRegion(String region) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h where h.address.region = ?1", House.class).setParameter(1, region).getResultList();
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List getAgencyHouses(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager
                    .createQuery("select h from House h inner join Rent_info r on h.id = r.house.id inner join Agency  ag on r.agency.id = ag.id where ag.id = ?1")
                    .setParameter(1, agencyId)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getOwnerHouses(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List houses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            houses = entityManager
                    .createQuery("select h from House h inner join Owner  o on h.owner.id = o.id where o.id = ?1")
                    .setParameter(1, id)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getCheck(LocalDate checkin, LocalDate checkout) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            String jpql = "select h from House h " +
                    "where h.rentInfo.checkin between :checkin and :checkout " +
                    "or h.rentInfo.checkOut between :checkin and :checkout";

            houses = entityManager.createQuery(jpql, House.class)
                    .setParameter("checkin", checkin)
                    .setParameter("checkout", checkout)
                    .getResultList();

            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        return houses;


    }
}
