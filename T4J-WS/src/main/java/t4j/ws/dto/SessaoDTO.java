package t4j.ws.dto;

public class SessaoDTO {

    private UtilizadorDTO utilizadorDTO;
    private String timeStamp;

    public SessaoDTO() {

    }

    public void setUtilizadorDTO(UtilizadorDTO utilizadorDTO) {
        this.utilizadorDTO = utilizadorDTO;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public UtilizadorDTO getUtilizadorDTO() {
        return utilizadorDTO;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
