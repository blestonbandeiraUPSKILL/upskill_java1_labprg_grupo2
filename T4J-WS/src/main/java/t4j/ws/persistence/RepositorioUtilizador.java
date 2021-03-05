package t4j.ws.persistence;

import t4j.ws.domain.Email;
import t4j.ws.domain.Password;
import t4j.ws.domain.Utilizador;
import t4j.ws.exception.UtilizadorDuplicadoException;

import java.util.List;

public class RepositorioUtilizador {

    private static RepositorioUtilizador repositorioUtilizador;

    private RepositorioUtilizador(){    }

    public static RepositorioUtilizador getInstance(){
        if(repositorioUtilizador == null) {
            repositorioUtilizador = new RepositorioUtilizador();
        }
        return repositorioUtilizador;
    }

    public boolean save(Utilizador utilizador) {
        return false;
    }

    public Utilizador findByEmail(String email) {
        return null;
    }

    public List<Utilizador> getAll() {
        return null;
    }

    public Utilizador getByEmail(String email) {
        return null;
    }
}
