package person.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class KartaHybrid {
    private String ime;
    private int sifravozila;
    private LocalDate datum;
    private LocalTime vreme;

    public KartaHybrid(String ime, int sifravozila, LocalDate datum, LocalTime vreme) {
        this.ime = ime;
        this.sifravozila = sifravozila;
        this.datum = datum;
        this.vreme = vreme;
    }

    @Override
    public String toString() {
        return "KartaHybrid{" +
                "ime='" + ime + '\'' +
                ", sifravozila=" + sifravozila +
                ", datum=" + datum +
                ", vreme=" + vreme +
                '}';
    }
    //    private
//
//    public KartaHybrid(int id_korisnika, int id_putovanja, opis) {
//        this.id_korisnika = id_korisnika;
//        this.id_putovanja = id_putovanja;
//        this.opis = opis;
//    }
}
