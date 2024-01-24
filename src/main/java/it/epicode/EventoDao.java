package it.epicode;

import javax.persistence.*;

public class EventoDao {

    private final EntityManager em;

    public EventoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Evento evento) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(evento);
        transaction.commit();
        System.out.println("Evento " + evento.getTitolo() + " generato!");
    }

    public void delete(Long id) {
        Evento found = this.findById(id);
        if(found != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Evento " + found.getTitolo() + " cancellato!");
        } else {
            System.out.println("L'Evento con id " + id + " non è stato trovato");
        }
    }

    private Evento findById(Long id) {
        return em.find(Evento.class, id);
    }
}
