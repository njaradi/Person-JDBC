package person.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Data
public class Putovanje {
    private int id_putovanja;
    private int sifra_vozila;
    private LocalDate datum;
    private LocalTime vreme;
    private int id_planelita;

    public Putovanje(int id_putovanja, int sifra_vozila, LocalDate datum, LocalTime vreme, int id_planelita) {
        this.id_putovanja = id_putovanja;
        this.sifra_vozila = sifra_vozila;
        this.datum = datum;
        this.vreme = vreme;
        this.id_planelita = id_planelita;
    }
}
