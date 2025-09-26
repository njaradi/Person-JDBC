package person.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.model.Karta;
import person.model.KartaHybrid;
import person.model.ObjektiHybrid;
import person.model.StambeniObjekat;
import person.model.base.Server;
import person.model.utility.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class CartView extends Stage {
    private final BorderPane root = new BorderPane();
    private Label lblObjekti = new Label("Bought Apartments: ");
    private Label lblKarte = new Label("Bought Tickets: ");
    private ArrayList<ObjektiHybrid> stambeniObjekats = new ArrayList<>();
    private ObservableList<ObjektiHybrid> olStambeniObjekats = FXCollections.observableArrayList(stambeniObjekats);
    private TableView<ObjektiHybrid> tvStambeniObjekti = new KupljeniObjektiTable(olStambeniObjekats);

    private List<KartaHybrid> kartas = new ArrayList<>();
    private ObservableList<KartaHybrid> olKartas = FXCollections.observableArrayList(kartas);
    private TableView<KartaHybrid> tvKartas = new KartaTable(olKartas);
    private VBox vBox = new VBox();


    public CartView(){
        super.setTitle("Cart");
        root.setCenter(vBox);
        super.getIcons().add(new Image("spaceship.png"));
        vBox.setPadding(new Insets(40));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        kartas = (List<KartaHybrid>) JDBCUtils.selectKupljeneKarte(Server.currUser.getId_korisnika());
        for(KartaHybrid k : kartas)
            System.out.println(k.toString());
        olKartas.clear();
        olKartas.addAll(kartas);
        tvKartas.setItems(olKartas);

        stambeniObjekats = (ArrayList<ObjektiHybrid>) JDBCUtils.selectKupljeniObjekti(Server.currUser.getId_korisnika());
        olStambeniObjekats.clear();
        olStambeniObjekats.addAll(stambeniObjekats);
        tvStambeniObjekti.setItems(olStambeniObjekats);

        super.setWidth(400);
        super.setHeight(601);
        vBox.getChildren().addAll(lblKarte, tvKartas, lblObjekti, tvStambeniObjekti);
        root.setCenter(vBox);

        super.setScene(new Scene(this.root));
    }
}
