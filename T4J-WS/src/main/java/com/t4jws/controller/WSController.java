package com.t4jws.controller;

import com.t4jws.dto.*;
import com.t4jws.model.Email;
import com.t4jws.service.UtilizadoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
public class WSController {

    @RequestMapping(value = "/context" , method = RequestMethod.GET, params = {"app_key"})
    public ResponseEntity<?> getContext(@RequestParam("app_key") String app_key) {
        try {

            ContextDTO contextDTO = GestaoUtilizadoresService.generateContext(app_key);

            if (contextDTO != null) {
                return new ResponseEntity<>(contextDTO, HttpStatus.OK);
            }
            else {
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET , params = {"app_context"})
    public ResponseEntity<?> userRoles(@RequestParam("app_context") String app_context) {
        try {

            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if (!GestaoUtilizadoresService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ListaRolenamesDTO listaRolesDTO = UtilizadoresService.getRoles();

            if(listaRolesDTO != null) {
                return new ResponseEntity<>(listaRolesDTO, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST, params = {"app_context", "rolename", "description"})
    public ResponseEntity<?> createRoles(@RequestParam("app_context") String app_context,
                                             @RequestParam("rolename") String rolename,
                                             @RequestParam("description") String designacao) {

        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if (!GestaoUtilizadoresService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.createRoles(app_context, rolename, designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST, params = {"app_context", "rolename", "description"})
    public ResponseEntity<?> updateRoles(@RequestParam("app_context") String app_context,
                                         @RequestParam("rolename") String rolename,
                                         @RequestParam("description") String designacao) {

        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if (!GestaoUtilizadoresService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.updateRoles(app_context, rolename, designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles", method = RequestMethod.DELETE, params = {"app_context", "rolename"})
    public ResponseEntity<?> deleteRoles(@RequestParam("app_context") String app_context,
                                         @RequestParam("rolename") String rolename){

        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if (!GestaoUtilizadoresService.validateContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.deleteRoles(app_context, rolename);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/userRoles", method = RequestMethod.GET, params = {"app_context", "user_id"})
    public ResponseEntity<?> getUserRoles(@RequestParam("app_context") String app_context,
                                          @RequestParam("user_id") EmailDTO emailDTO) {

        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if(!GestaoUtilizadoresService.validadeContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RolenameDTO roleDTO = UtilizadoresService.getUserRoles(emailDTO);

            if (roleDTO != null) {
                return new ResponseEntity<>(roleDTO, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles", method = RequestMethod.POST, params = {"app_context", "user_id", "rolenames"})
    public ResponseEntity<?> createUserRoles(@RequestParam("app_context") String app_context,
                                           @RequestParam("user_id") String email,
                                           @RequestParam("rolenames") String rolename) {

        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if(!GestaoUtilizadoresService.validadeContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            EmailDTO emailDTO = new EmailDTO(email);

            RoleDTO roleDTO = new RoleDTO(rolename);

            UtilizadoresService.createUserRoles(app_context, emailDTO, roleDTO );

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles", method = RequestMethod.DELETE, params = {"app_context", "user_id", "rolenames"})
    public ResponseEntity<?> deleteUserRoles(@RequestParam("app_context") String app_context,
                                             @RequestParam("user_id") String email,
                                             @RequestParam("rolenames") String rolename) {
        try {
            ContextDTO contextDTO = new ContextDTO();
            contextDTO.setAppContext(app_context);

            if(!GestaoUtilizadoresService.validadeContext(contextDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.deleteUserRoles(app_context, email, rolename);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
