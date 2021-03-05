package t4j.ws.domain;

import java.util.Objects;

public class Rolename {

    private int idRolename;
    private String designacao;
    private String descricao;

    public Rolename() {

    }

    public Rolename(int idRolename, String designacao, String descricao){
        setIdRolename(idRolename);
        setDesignacao(designacao);
        setDescricao(descricao);
    }

    public void setIdRolename(int idRolename) {
        this.idRolename = idRolename;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdRolename() {
        return idRolename;
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rolename)) return false;
        Rolename rolename = (Rolename) o;
        return getDesignacao().equals(rolename.getDesignacao())
                && getDescricao().equals(rolename.getDescricao());
    }

    @Override
    public String toString() {
        return "Rolename{" +
                "designacao='" + designacao + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
