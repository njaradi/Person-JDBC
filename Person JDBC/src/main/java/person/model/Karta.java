package person.model;

import lombok.Data;

@Data
public class Karta {
    private int id_korisnika;
    private int id_putovanja;

    public Karta(int id_korisnika, int id_putovanja) {
        this.id_korisnika = id_korisnika;
        this.id_putovanja = id_putovanja;
    }
}
