package person.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Korisnik {
    private int id_korisnika;
    private String korisnicko_ime;
    private String lozinka;
    private String ime;
    private String prezime;
    private LocalDate datum_rodjenja;
    private LocalDate datum_smrti;

    public Korisnik() {
        this.id_korisnika = 1;
    }

    public Korisnik(int id_korisnika, String korisnicko_ime, String lozinka, String ime, String prezime, LocalDate datum_rodjenja) {
        this.id_korisnika = id_korisnika;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodjenja = datum_rodjenja;
    }

    public Korisnik(int idKorisnika, String korisnickoIme, String lozinka, String ime, String prezime, LocalDate datumRodjenja, LocalDate datumSmrti) {
        this.id_korisnika = id_korisnika;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodjenja = datum_rodjenja;
        this.datum_smrti = datum_smrti;
    }
}
