package person.model;

import lombok.Data;

@Data
public class Planelit {
    private int id_planelita;
    private String ime;
    private Double udaljenost;//od zvezde
    private Double min_temp;
    private Double max_temp;
    private Double procenat_kiseonika;
    private String gas;
    private Double procenat_gasa;
    private Double visina_gravitacije;
    private Double brzina_orbitiranja;
    private Tip tip;//planeta/satelit

    public Planelit(int id_planelita, String ime, Double udaljenost,
                    Double min_temp, Double max_temp, Double procenat_kiseonika,
                    String gas, Double procenat_gasa, Double visina_gravitacije,
                    Double brzina_orbitiranja, String tip) {
        this.id_planelita = id_planelita;
        this.ime = ime;
        this.udaljenost = udaljenost;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.procenat_kiseonika = procenat_kiseonika;
        this.gas = gas;
        this.procenat_gasa = procenat_gasa;
        this.visina_gravitacije = visina_gravitacije;
        this.brzina_orbitiranja = brzina_orbitiranja;
        if(tip.equals("PLANETA"))
            this.tip = Tip.PLANETA;
        else
            this.tip = Tip.SATELIT;
    }
}

