package edu.eci.arsw.cinema.test;

import edu.eci.arsw.cinema.model.*;
import edu.eci.arsw.cinema.persistence.*;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * @author cristian
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void buyTicket() throws CinemaException {
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogotá", functions);

        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException e) {
            fail("Cinema persistence failed inserting the first cinema.");
        }

        ipct.buyTicket(2, 2, "Movies Bogotá", "2018-12-18 15:30", "The Night 2");

        try {
            ipct.buyTicket(2, 2, "Movies Bogotá", "2018-12-18 15:30", "The Night 2");
            assertEquals(0,1);
        } catch (CinemaException e){
            assertEquals(1,1);
        }

    }
    @Test
    public void getFunctionsbyCinemaAndDateTest(){
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);

        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException e) {
            fail("Cinema persistence failed inserting the first cinema.");
        }

        assertEquals(ipct.getFunctionsbyCinemaAndDate("Movies Bogotá",functionDate),functions);

    }

    @Test
    public void saveNewAndLoadTest() throws CinemaPersistenceException{
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        ipct.saveCinema(c);
        
        assertNotNull("Loading a previously stored cinema returned null.",ipct.getCinema(c.getName()));
        assertEquals("Loading a previously stored cinema returned a different cinema.",ipct.getCinema(c.getName()), c);
    }


    @Test
    public void saveExistingCinemaTest() {
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        
        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
        
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3","Action"),functionDate);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3","Horror"),functionDate);
        functions.add(funct12);
        functions.add(funct22);
        Cinema c2=new Cinema("Movies Bogotá",functions2);
        try{
            ipct.saveCinema(c2);
            fail("An exception was expected after saving a second cinema with the same name");
        }
        catch (CinemaPersistenceException ex){
        }
    }
}