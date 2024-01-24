package it.epicode;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocationDao {
    private EntityManager em;

    public LocationDao(EntityManager em) {
        this.em = em;
    }

    public Location findById(Long id) {
        return em.find(Location.class, id);
    }

    public List<Location> findAll() {
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
        return query.getResultList();
    }

    public void save(Location location) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(location);
        transaction.commit();
        System.out.println("Location " + location.getNome() + " generato!");
    }

    public void delete(Location location) {
        if (em.contains(location)) {
            em.remove(location);
        } else {
            Location toRemove = findById(location.getId());
            em.remove(toRemove);
        }
    }
}