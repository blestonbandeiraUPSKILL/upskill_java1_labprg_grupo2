package t4j.ws.domain;

public class Sessao {

    private Utilizador utilizador;
    private Contexto contexto;
    private String timeStamp;

    public Sessao(Utilizador utilizador, Contexto contexto, String timeStamp) {
        setUtilizador(utilizador);
        setContexto(contexto);
        setTimeStamp(timeStamp);
    }

    public Sessao(Utilizador utilizador, Contexto contexto) {
        setUtilizador(utilizador);
        setContexto(contexto);
    }

    public Sessao() {

    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public Contexto getContexto() {
        return contexto;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
