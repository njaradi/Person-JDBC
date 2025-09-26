package person.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import person.model.Karta;
import person.model.Planelit;
import person.model.Putovanje;
import person.model.StambeniObjekat;
import person.model.utility.JDBCUtils;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static person.model.base.Server.SERVER;

public class HomeView extends Stage {
    private final BorderPane root = new BorderPane();
    private HBox hBox = new HBox();
    private VBox vBoxPlanetBiranje = new VBox();
    private VBox vBoxDesna = new VBox();
    private VBox vBoxOpisPlanet = new VBox();

    private VBox box = new VBox();
    private VBox vBoxStan = new VBox();
    private VBox vBoxKarte = new VBox();
    private Label lblLivable = new Label("Livable");
    private ObservableList<Planelit> olPlanelits = FXCollections.observableArrayList(SERVER.getPlanelitiLivable());
    private TableView<Planelit> tvPlanelit = new PlanelitTable(olPlanelits);
    private HBox hBoxPlenelitTitle = new HBox();
    private Label lblName = new Label("");
    private Label lblType = new Label("");
    private Label lblTemp = new Label("");
    private Label lblAtm = new Label("");
    private Label lblGrav = new Label("");
    private Label lblOrb = new Label("");
    private Label lblDist = new Label("");
    private Label lblBuildings = new Label("Available apartments for you: ");
    private Label lblTickets = new Label("Available tickets for you: ");
    private List<StambeniObjekat> stambeniObjekats= new ArrayList<>();
    private ObservableList<StambeniObjekat> olStambeniObjekats =FXCollections.observableArrayList(stambeniObjekats);
    private TableView<StambeniObjekat> tvstambeniObjekats = new ObjekatTable(olStambeniObjekats);
    private List<Putovanje> putovanjeList = new ArrayList<>();
    private ObservableList<Putovanje> olPutovanjes = FXCollections.observableArrayList(putovanjeList);
    private TableView<Putovanje> tvPutovanja = new PutovanjeTable(olPutovanjes);
    private Button btnBuyAp = new Button("Buy");
    private Button btnBuyTicket = new Button("Buy");
    private Button btnCart = new Button("My Cart");

    private RadioButton rbKarta = new RadioButton("Ticket");
    private RadioButton rbStan = new RadioButton("Appartmants");
    private ToggleGroup toggleGroup = new ToggleGroup();
    private HBox hBoxRadioB = new HBox();

    public HomeView() {
        super.setTitle("ZUS shop");
        root.setCenter(hBox);
        super.getIcons().add(new Image("spaceship.png"));
        hBox.setPadding(new Insets(40));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        vBoxDesna.setSpacing(15);
        vBoxOpisPlanet.setSpacing(5);
        lblName.setFont(new Font("Comic Sans MS",24));
        vBoxKarte.setSpacing(10);
        vBoxStan.setSpacing(10);

        super.setHeight(600);
        super.setWidth(800);
        hBox.getChildren().addAll(btnCart, vBoxPlanetBiranje, vBoxDesna);
        vBoxPlanetBiranje.getChildren().addAll(lblLivable, tvPlanelit);
        rbKarta.setToggleGroup(toggleGroup);
        rbStan.setToggleGroup(toggleGroup);
        hBoxRadioB.getChildren().addAll(rbKarta, rbStan);
        hBoxPlenelitTitle.getChildren().addAll(lblName, lblType);//!!
        vBoxOpisPlanet.getChildren().addAll(hBoxPlenelitTitle, lblTemp, lblAtm, lblGrav, lblOrb, lblDist);
        vBoxStan.getChildren().addAll(lblBuildings, tvstambeniObjekats,btnBuyAp);
        vBoxKarte.getChildren().addAll(lblTickets, tvPutovanja, btnBuyTicket);
        vBoxDesna.getChildren().addAll(vBoxOpisPlanet,hBoxRadioB,box);

        //System.out.println(tvPlanelit.getSelectionModel().getSelectedItem().toString());
        tvPlanelit.getSelectionModel().selectedItemProperty().addListener((newValue) -> {
            if (newValue != null) {
                //System.out.println(newValue.getClass().toString());
                Planelit planelit = tvPlanelit.getSelectionModel().getSelectedItem();
                lblName.setText(planelit.getIme()+"   ");
                lblType.setText(planelit.getTip().toString());
                lblTemp.setText(planelit.getMin_temp()+"K - "+planelit.getMax_temp()+"K");
                lblAtm.setText("Oxygen "+ planelit.getProcenat_kiseonika()+"% - "+planelit.getGas()+" "+planelit.getProcenat_gasa()+"%");
                lblGrav.setText("Height of gravitation: "+ planelit.getVisina_gravitacije());
                lblOrb.setText("Orbitational speed: "+planelit.getBrzina_orbitiranja());
                lblDist.setText("Distance from the nearest star: "+planelit.getUdaljenost());

                putovanjeList = (List<Putovanje>) JDBCUtils.selectPutovanjaPlanelit(planelit.getId_planelita());
                olPutovanjes.clear();
                olPutovanjes.addAll(putovanjeList);
                tvPutovanja.setItems(olPutovanjes);

                stambeniObjekats = (List<StambeniObjekat>) JDBCUtils.selectObjektiPlanelit(planelit.getId_planelita());
                olStambeniObjekats.clear();
                olStambeniObjekats.addAll(stambeniObjekats);
                tvstambeniObjekats.setItems(olStambeniObjekats);
                //System.out.println(putovanjeList);
            }
                //System.out.println("Selected person: " + ((Planelit)newValue).getIme() + " " + newValue.getLastName());
                // Perform your action here
        });

        btnBuyAp.setOnAction(e -> {
            JDBCUtils.addKupovinaObjekta(tvstambeniObjekats.getSelectionModel().getSelectedItem().getId_objekta(),
                    SERVER.currUser.getId_korisnika());
            System.out.println("Kupljen stan uspesno " +tvstambeniObjekats.getSelectionModel().getSelectedItem().getNaziv());
        });

        btnBuyTicket.setOnAction(e -> {
            JDBCUtils.addKarta(SERVER.currUser.getId_korisnika(), tvPutovanja.getSelectionModel().getSelectedItem().getId_putovanja());
            System.out.println("Kupljena karta uspesno za putovanje " +tvPutovanja.getSelectionModel().getSelectedItem().getSifra_vozila());

        });

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
                if (rb == rbStan) {
                    box.getChildren().clear();
                    box.getChildren().add(vBoxStan);
                }
                else if(rb == rbKarta) {
                    box.getChildren().clear();
                    box.getChildren().add(vBoxKarte);
                }
            }
        });

        btnCart.setOnAction(e -> {
            CartView cartView = new CartView();
            cartView.show();
        });



        //vBoxStan.setMinSize();

        super.setScene(new Scene(this.root));
    }
}
