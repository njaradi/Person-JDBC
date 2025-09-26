package person.model.base;

import person.model.*;
import person.model.utility.JDBCUtils;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Server {

    public static final Server SERVER = new Server();
    public static Korisnik currUser = new Korisnik();
    private final List<Person> people = new ArrayList<>();
    private final List<Korisnik> korisnici = new ArrayList<>();
    private final List<Karta> karte = new ArrayList<>();
    private final List<KupovinaObjekta> kupovinaObjekata = new ArrayList<>();
    private final List<Planelit> planelitiLivable = new ArrayList<>();
    private final List<Putovanje> putovanja = new ArrayList<>();
    private final List<StambeniObjekat> stambeniObjekti = new ArrayList<>();

    private Server() {
        //this.setPeople(JDBCUtils.selectAllFromPerson());
//        this.setKarte();
//        this.setKorisnici();
//        this.setKupovinaObjekata();
        this.setPlanelitiLivable(JDBCUtils.selectLivablePlaneliti());
        System.out.println(this.getPlanelitiLivable());
//        this.setPutovanja();
//        this.setStambeniObjekti();
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public List<Karta> getKarte() {
        return karte;
    }

    public List<KupovinaObjekta> getKupovinaObjekata() {
        return kupovinaObjekata;
    }

    public List<Planelit> getPlanelitiLivable() {
        return planelitiLivable;
    }

    public List<Putovanje> getPutovanja() {
        return putovanja;
    }

    public List<StambeniObjekat> getStambeniObjekti() {
        return stambeniObjekti;
    }

    public void setPeople(Collection<Person> people) {
        this.people.clear();
        this.people.addAll(people);
    }

    public void setKarte(Collection<Karta> karte) {
        this.karte.clear();
        this.karte.addAll(karte);
    }

    public void setKorisnici(Collection<Korisnik> korisnici) {
        this.korisnici.clear();
        this.korisnici.addAll(korisnici);
    }

    public void setKupovinaObjekata(Collection<KupovinaObjekta> kupovinaObjekata) {
        this.kupovinaObjekata.clear();
        this.kupovinaObjekata.addAll(kupovinaObjekata);
    }

    public void setPlanelitiLivable(Collection<Planelit> planelitiLivable) {
        this.planelitiLivable.clear();
        this.planelitiLivable.addAll(planelitiLivable);
    }

    public void setPutovanja(Collection<Putovanje> putovanja) {
        this.putovanja.clear();
        this.putovanja.addAll(putovanja);
    }
    public void setStambeniObjekti(Collection<StambeniObjekat> stambeniObjekti) {
        this.stambeniObjekti.clear();
        this.stambeniObjekti.addAll(stambeniObjekti);
    }

}
