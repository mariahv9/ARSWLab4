package edu.eci.arsw.cinema.test;

import com.google.gson.Gson;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.annotation.Target;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author cristian
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ApplicationServicesTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private CinemaServices cinemaServices;

    private Gson gson = new Gson();

    @Test
    public void getCinemasName () throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get("/cinemas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());
    }

    @Test
    public void noCinemaName () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemas/cmko")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isCinemaName () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemas/cinePolis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());
    }

    @Test
    public void noCinemaDate () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemAX/2018-12-18 15:31")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isCinemaDate () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemaX/2018-12-18 15:30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());
    }

    @Test
    public void noCinemaNameDate () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinX/2018-12-18 15:30/The Night")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isNotFound());
    }

    @Test
    public void isCinemaNameDate () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemaX/2018-12-18 15:30/The Night")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isAccepted());
    }

    @Test
    public void postCinemaName () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemas/cinemaX")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isCreated());
    }

    @Test
    public void putCinemaName () throws Exception{
        mock.perform(
                MockMvcRequestBuilders.get("/cinemas/cinePolis/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isCreated());
    }
}