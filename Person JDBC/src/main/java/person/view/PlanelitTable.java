package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.Planelit;

import java.time.LocalDate;
import java.util.List;

public class PlanelitTable extends TableView<Planelit> {
    public PlanelitTable(List<Planelit> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Planelit, Integer> tcName = new TableColumn<>("Name");
        TableColumn<Planelit, String> tcType = new TableColumn<>("Type");

        tcName.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tcType.setCellValueFactory(new PropertyValueFactory<>("tip"));

        super.getColumns().add(tcName);
        super.getColumns().add(tcType);
    }
}
