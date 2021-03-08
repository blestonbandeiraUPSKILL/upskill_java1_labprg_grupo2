package t4j.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import t4j.ws.domain.Email;
import t4j.ws.domain.Password;
import t4j.ws.domain.Rolename;
import t4j.ws.dto.*;
import t4j.ws.service.GestaoUtilizadoresService;
import t4j.ws.service.UtilizadoresService;

import java.sql.SQLException;

@RestController
public class GestaoUtilizadoresController {

    @RequestMapping(value = "/session",
            method = RequestMethod.GET,
            params = {"app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSession(@RequestParam("app_context") String appContext) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if(!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            SessaoDTO sessaoDTO = GestaoUtilizadoresService.getSession(contextoDTO);

            if(sessaoDTO != null) {
                return new ResponseEntity<>(sessaoDTO, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (SQLException exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/context",
            method = RequestMethod.GET,
            params = { "app_key"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContext(@RequestParam("app_key") String appKey) {
        try {

            ContextoDTO contextoDTO = GestaoUtilizadoresService.generateContext(appKey);
            if (contextoDTO != null) {
                return new ResponseEntity<>(contextoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErroDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            params = {"app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userRoles(@RequestParam("app_context") String appContext) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if(!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ListaRolenamesDTO listaRolenamesDTO = UtilizadoresService.getRoles();

            return new ResponseEntity<>(listaRolenamesDTO, HttpStatus.OK);
        }
        catch (SQLException exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles",
            method = RequestMethod.GET,
            params = {"app_context", "user_id"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserRoles(@RequestParam("app_context") String appContext,
                                          @RequestParam("user_id") String email) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if(!GestaoUtilizadoresService.validateContexto(contextoDTO)){
                return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.getUserRolenames(email);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles",
            method = RequestMethod.POST,
            params = {"app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoleToUser(@RequestParam("app_context") String appContext,
                                           @RequestParam("user_id") String email,
                                           @RequestParam("rolenames") String designacao) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if(!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.addRolenameToUser(email, designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            params = {"app_context", "rolenames", "description"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserRoles(@RequestParam("app_context") String appContext,
                                             @RequestParam("rolenames") String designacao,
                                             @RequestParam("description") String descricao) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RolenameDTO rolenameDTO = new RolenameDTO();
            rolenameDTO.setDesignacao(designacao);
            rolenameDTO.setDescricao(descricao);

            UtilizadoresService.createUserRole(rolenameDTO);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.DELETE,
            params = {"app_context", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserRoles(@RequestParam("app_context") String appContext,
                                             @RequestParam("rolenames") String designacao) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.deleteUserRole(designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/userRoles",
            method = RequestMethod.DELETE,
            params = {"app_context", "user_id", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRoleFromUser(@RequestParam("app_context") String appContext,
                                                @RequestParam("user_id") String email,
                                                @RequestParam("rolenames") String designacao) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadoresService.deleteRoleFromUser(email, designacao);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/registerUser",
            method = RequestMethod.POST,
            params = {"app_context", "username", "user_id", "password"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestParam("app_context") String appContext,
                                          @RequestParam("username") String username,
                                          @RequestParam("user_id") String email,
                                          @RequestParam("password") String password) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadorDTO utilizadorDTO = new UtilizadorDTO();
            utilizadorDTO.setUsername(username);
            utilizadorDTO.setEmail(email);
            utilizadorDTO.setPassword(new Password(password));

            UtilizadoresService.registerUser(utilizadorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/registerUserWithRoles",
            method = RequestMethod.POST,
            params = {"app_context", "username", "user_id", "password", "rolenames"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUserWithRoles(@RequestParam("app_context") String appContext,
                                                   @RequestParam("username") String username,
                                                   @RequestParam("user_id") String email,
                                                   @RequestParam("password") String password,
                                                   @RequestParam("rolenames") Rolename rolename) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UtilizadorDTO utilizadorDTO = new UtilizadorDTO();
            utilizadorDTO.setEmail(email);
            utilizadorDTO.setUsername(username);
            utilizadorDTO.setPassword(new Password(password));
            utilizadorDTO.setRolename(rolename);

            UtilizadoresService.registerUserWithRoles(utilizadorDTO, rolename);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            params = {"app_context", "user_id", "password"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestParam("app_context") String appContext,
                                   @RequestParam("user_id") String email,
                                   @RequestParam("password") String password) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setEmail(new Email(email));
            loginDTO.setPassword(new Password(password));

            if(GestaoUtilizadoresService.login(loginDTO, contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/logout",
            method = RequestMethod.POST,
            params = {"app_context"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@RequestParam("app_context") String appContext) {
        try {
            ContextoDTO contextoDTO = new ContextoDTO();
            contextoDTO.setAppContext(appContext);

            if (!GestaoUtilizadoresService.validateContexto(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            if(GestaoUtilizadoresService.logout(contextoDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new ErroDTO(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
