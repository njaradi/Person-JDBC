package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.ObjektiHybrid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class KupljeniObjektiTable extends TableView<ObjektiHybrid> {
    public KupljeniObjektiTable(List<ObjektiHybrid> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<ObjektiHybrid, String> ime_planelita = new TableColumn<>("Planelit");
        TableColumn<ObjektiHybrid, String> ime_objekta = new TableColumn<>("Apartment");

        ime_planelita.setCellValueFactory(new PropertyValueFactory<>("ime_planelita"));
        ime_objekta.setCellValueFactory(new PropertyValueFactory<>("ime_objekta"));

        super.getColumns().add(ime_planelita);
        super.getColumns().add(ime_objekta);
    }
}