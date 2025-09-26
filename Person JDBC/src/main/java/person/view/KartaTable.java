package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.KartaHybrid;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class KartaTable extends TableView<KartaHybrid> {
    public KartaTable(List<KartaHybrid> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<KartaHybrid, String> ime = new TableColumn<>("Planelit");
        TableColumn<KartaHybrid, Integer> sifra_vozila = new TableColumn<>("Vehicle");
        TableColumn<KartaHybrid, LocalDate> datum = new TableColumn<>("Date");
        TableColumn<KartaHybrid, LocalTime> vreme = new TableColumn<>("Time");

        ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
        sifra_vozila.setCellValueFactory(new PropertyValueFactory<>("sifravozila"));
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        vreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        super.getColumns().add(ime);
        super.getColumns().add(sifra_vozila);
        super.getColumns().add(datum);
        super.getColumns().add(vreme);

    }
}