package t4j.ws.dto;

public class RolenameDTO {

    private int idRolename;
    private String designacao;
    private String descricao;

    public RolenameDTO(){

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
}
