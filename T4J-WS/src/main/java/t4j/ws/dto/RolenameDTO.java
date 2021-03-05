package t4j.ws.dto;

public class RolenameDTO {

    private String designacao;
    private String descricao;

    public RolenameDTO(){

    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getDescricao() {
        return descricao;
    }
}
