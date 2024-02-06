package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.DataBaseConfig;
import java12.dao.Rent_infoDao;
import java12.entity.Rent_info;

import java.time.LocalDate;
import java.util.List;

public class Rent_infoDaoImpl implements Rent_infoDao {

    EntityManagerFactory entityManagerFactory = DataBaseConfig.entityManagerFactory();
    @Override
    public String getCheckinAndCherOut(LocalDate checkin, LocalDate checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Rent_info> rentInfos = null;
        try {
            entityManager.getTransaction().begin();
            rentInfos = entityManager.createQuery("SELECT ri FROM Rent_info ri WHERE ri.checkin >= :startDate AND ri.checkOut <= :endDate",Rent_info.class)
                    .setParameter("startDate", checkin)
                    .setParameter("endDate", checkOut)
                    .getResultList();
            for (Rent_info rentInfo : rentInfos){
                entityManager.remove(rentInfo);
            }

            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive())entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return ""+ rentInfos;
    }
}
