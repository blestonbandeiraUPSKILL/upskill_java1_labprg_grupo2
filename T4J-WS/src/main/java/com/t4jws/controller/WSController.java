package com.t4jws.controller;

import com.t4jws.dto.ErroDTO;
import com.t4jws.service.UtilizadoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

            ContextDTO

            if (listRolesDTO != null) {
                return new ResponseEntity<>(listRolesDTO, HttpStatus.OK);
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
                                            @RequestParam("description") String designacao,
                                            @RequestParam("rolename") String rolename) {

        try {
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
            UtilizadoresService.updateRoles(app_context, rolename, designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles", method = RequestMethod.DELETE, params = {"app_context", "rolename"})
    public ResponseEntity<?> deleteRoles(@RequestParam("app_context") String app_context,
                                         @RequestParam("rolename") String rolename,
                                         @RequestParam("description") String designacao){

        try {
            UtilizadoresService.deleteRoles(app_context, rolename, designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/userRoles", method = RequestMethod.GET, params = {"app_context", "user_id"})
    public ResponseEntity<?> getUserRoles(@RequestParam("app_context") String app_context,
                                          @RequestParam("user_id") String username) {

        try {
            RoleDTO roleDTO = UtilizadoresService.getUtilizadorRoles(username);

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
    public ResponseEntity<?> addRoleToUser(@RequestParam("app_context") String app_context,
                                           @RequestParam("user_id") String username,
                                           @RequestParam("rolenames") String rolename) {

        try {
            UtilizadoresService.addRoleToUser(username, rolename);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
