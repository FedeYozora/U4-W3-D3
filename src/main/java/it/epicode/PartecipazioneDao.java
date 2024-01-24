package it.epicode;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import java.util.List;

public class PartecipazioneDao {
    private EntityManager em;

    public PartecipazioneDao(EntityManager em) {
        this.em = em;
    }

    public Partecipazione findById(Long id) {
        return em.find(Partecipazione.class, id);

    }

    public List<Partecipazione> findAll() {
        TypedQuery<Partecipazione> query = em.createQuery("SELECT p FROM Participation p", Partecipazione.class);
        return query.getResultList();
    }

    public void save(Partecipazione participation) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(participation);
        transaction.commit();
        System.out.println("Partecipazione di " + participation.getPersona().getNome() + " generato!");
    }

    public void delete(Partecipazione participation) {
        if (em.contains(participation)) {
            em.remove(participation);
        } else {
            Partecipazione toRemove = findById(participation.getId());
            em.remove(toRemove);
        }
    }
}