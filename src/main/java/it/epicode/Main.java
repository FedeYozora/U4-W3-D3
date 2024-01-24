package it.epicode;

import javax.persistence.*;
import java.time.LocalDate;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestioneEventi");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        EventoDao eventDao = new EventoDao(em);
        LocationDao locationDao = new LocationDao(em);
        PersonaDao personDao = new PersonaDao(em);
        PartecipazioneDao participationDao = new PartecipazioneDao(em);

        // crea Location
        Location location = new Location("Casa di Mario", "Via Roma 12");
        locationDao.save(location);

        // crea Persona
        Persona person = new Persona("Mario", "Placeholder", "test@test.com",LocalDate.of(2000,7,15), "male");
        personDao.save(person);

        // crea Evento
        Evento event = new Evento("Evento Test", "Descrizione Test", LocalDate.of(2023, 2, 1), TipoEvento.PRIVATO, 5, location);
        eventDao.save(event);

        // crea Partecipazioni
        Partecipazione participation = new Partecipazione(person, event, StatoPartecipazione.CONFERMATA);
        participationDao.save(participation);

        // print di Partecipazioni
        System.out.println(participation);

        // close Entity Manager
        em.close();
        emf.close();
    }
}
