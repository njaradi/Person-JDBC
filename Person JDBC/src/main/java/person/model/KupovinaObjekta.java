package person.model;

import lombok.Data;

@Data
public class KupovinaObjekta {
    private int id_korisnika;
    private int id_objekta;

    public KupovinaObjekta(int id_korisnika, int id_objekta) {
        this.id_korisnika = id_korisnika;
        this.id_objekta = id_objekta;
    }
}
