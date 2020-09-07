package edu.eci.arsw.cinema.test;

import edu.eci.arsw.cinema.model.*;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class FilterTest {

    private ApplicationContext ac;
    private CinemaServices cinemaServices;

    @Before
    public void setUp(){
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        cinemaServices = ac.getBean(CinemaServices.class);
    }

    @Test
    public void genreFilter() throws CinemaException{
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c2 = new Cinema("Movies Bogotá", functions);

        cinemaServices.addNewCinema(c2);

        Cinema c =cinemaServices.getCinemaByName("Movies Bogotá");

        List<CinemaFunction> cinemaFunctions =cinemaServices.getFunctionsFilter(c, "2018-12-18 15:30","Horror");

        assertEquals(cinemaFunctions.get(0).getMovie().getName(),"The Night 2");
    }

    @Test
    public void seatFilter() throws CinemaException{
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c2 = new Cinema("Movies Bogotá", functions);

        cinemaServices.addNewCinema(c2);

        cinemaServices.filterChange("filterOfSeats");

        Cinema c =cinemaServices.getCinemaByName("Movies Bogotá");

        List<CinemaFunction> cinemaFunctions =cinemaServices.geFunctionsFilter(c,"2018-12-18 15:30",2);

        assertEquals(cinemaFunctions.size(),c.getFunctions().size());
    }
}