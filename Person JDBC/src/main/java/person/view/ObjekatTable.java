package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.StambeniObjekat;
import java.util.List;

public class ObjekatTable extends TableView<StambeniObjekat> {
    public ObjekatTable(List<StambeniObjekat> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<StambeniObjekat, String> naziv = new TableColumn<>("Name");

        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));

        super.getColumns().add(naziv);
    }
}
