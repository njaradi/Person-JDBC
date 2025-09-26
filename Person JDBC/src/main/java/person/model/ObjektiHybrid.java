package person.model;

import lombok.Data;

@Data
public class ObjektiHybrid {
    private String ime_planelita;
    private String ime_objekta;

    public ObjektiHybrid(String ime_planelita, String ime_objekta) {
        this.ime_planelita = ime_planelita;
        this.ime_objekta = ime_objekta;
    }
}
