package person.model;

import lombok.Data;

@Data
public class StambeniObjekat {
    private int id_objekta;
    private int id_planelita;
    private String naziv;

    public StambeniObjekat(int id_objekta, int id_planelita, String naziv) {
        this.id_objekta = id_objekta;
        this.id_planelita = id_planelita;
        this.naziv = naziv;
    }
}
