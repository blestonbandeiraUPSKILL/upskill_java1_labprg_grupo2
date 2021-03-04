/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t4jws.controller;

import com.t4jws.dto.ErroDTO;
import com.t4jws.dto.ListaUtilizadoresDTO;
import com.t4jws.dto.UtilizadorDTO;
import com.t4jws.model.Email;
import com.t4jws.service.UtilizadoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author acris
 */
@RestController
@RequestMapping("/api")

public class UtilizadoresController {
    
    @RequestMapping(value = "/utilizadores",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getUtilizadores() {
        try {
            ListaUtilizadoresDTO listaUtilizadoresDTO = UtilizadoresService.getUtilizadores();

            if (listaUtilizadoresDTO.getUtilizadores().size() > 0) {
                return new ResponseEntity<>(listaUtilizadoresDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/utilizadores/{email}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)

    public ResponseEntity<Object> getUtilizadores(@PathVariable("email") Email email) {
        try {
            UtilizadorDTO utilizadorDTO = UtilizadoresService.getUtilizador(email);

            if (utilizadorDTO != null) {
                return new ResponseEntity<>(utilizadorDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/utilizadores",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    
    public ResponseEntity<Object> addUtilizador(@RequestBody UtilizadorDTO utilizadorDTO) {
        try {
            UtilizadoresService.addUtilizador(utilizadorDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/utilizadores/{email}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> updateUtilizador(@PathVariable("email") String email, @RequestBody UtilizadorDTO utilizadorDTO
    ) {
        
        try {
            UtilizadoresService.updateFreguesia(email, utilizadorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/utilizadores/{email}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> removeUtilizador(@PathVariable("email") String email) {
        try {
            UtilizadoresService.removeUtilizador(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.CONFLICT);
        }
    }
    
}
