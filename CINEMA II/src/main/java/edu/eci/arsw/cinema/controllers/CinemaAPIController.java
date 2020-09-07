/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import edu.eci.arsw.cinema.services.CinemaServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author cristian
 */

@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {

    @Autowired
    @Qualifier("cinemaServicies")
    CinemaServicesInterface cinemaServices;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cinemaServices.getAllCinemas(), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{cinemaName}",method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaByName(@PathVariable String cinemaName){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cinemaServices.getCinemaByName(cinemaName), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error HTTP 404",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cinemaName}/{date}",method = RequestMethod.GET)
    public ResponseEntity<?> getFunctionsbyCinemaAndDate(@PathVariable String cinemaName,@PathVariable String date){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cinemaServices.getFunctionsbyCinemaAndDate(cinemaName,date), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error HTTP 404",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{cinemaName}/{date}/{movieName}",method = RequestMethod.GET)
    public ResponseEntity<?> getMovie(@PathVariable String cinemaName,@PathVariable String date,@PathVariable String movieName){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cinemaServices.getMovie(cinemaName, date, movieName), HttpStatus.ACCEPTED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error HTTP 404",HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{cinema}",method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoXX(@PathVariable String cinema,@RequestBody CinemaFunction cinemaFunction){
        try {
            //registrar dato
            cinemaServices.addFunction(cinema, cinemaFunction);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CinemaException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.FORBIDDEN);
        }

    }



    
}
