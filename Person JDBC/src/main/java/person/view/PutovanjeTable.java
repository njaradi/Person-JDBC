package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.Putovanje;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PutovanjeTable extends TableView<Putovanje> {
    public PutovanjeTable(List<Putovanje> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Putovanje, Integer> sifra_vozila = new TableColumn<>("Vehicle");
        TableColumn<Putovanje, LocalDate> datum = new TableColumn<>("Date");
        TableColumn<Putovanje, LocalTime> vreme = new TableColumn<>("Time");

        sifra_vozila.setCellValueFactory(new PropertyValueFactory<>("sifra_vozila"));
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        vreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        super.getColumns().add(sifra_vozila);
        super.getColumns().add(datum);
        super.getColumns().add(vreme);

    }
}
