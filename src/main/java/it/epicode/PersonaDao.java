package it.epicode;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonaDao {
    private EntityManager em;

    public PersonaDao(EntityManager em) {
        this.em = em;
    }

    public Persona findById(Long id) {
        return em.find(Persona.class, id);
    }

    public List<Persona> findAll() {
        TypedQuery<Persona> query = em.createQuery("SELECT p FROM Person p", Persona.class);
        return query.getResultList();
    }

    public void save(Persona person) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(person);
        transaction.commit();
        System.out.println("persona " + person.getNome() + " generato!");
    }

    public void delete(Persona person) {
        if (em.contains(person)) {
            em.remove(person);
        } else {
            Persona toRemove = findById(person.getId());
            em.remove(toRemove);
        }
    }
}