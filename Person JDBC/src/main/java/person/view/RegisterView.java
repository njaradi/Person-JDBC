package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.model.utility.JDBCUtils;

import java.time.LocalDate;

public class RegisterView extends Stage {
    private final BorderPane root = new BorderPane();
    private Label label = new Label("Register");
    private Label lblName = new Label("First Name: ");
    private Label lblLast = new Label("Last Name: ");
    private Label lblUserName = new Label("Username: ");
    private Label lblPassword = new Label("Password: ");
    private Label lblDate = new Label("Date of birth: ");
    private TextField tfUserName = new TextField();
    private TextField tfPassword = new TextField();
    private TextField tfName = new TextField();
    private TextField tfLast = new TextField();
    private DatePicker datePicker = new DatePicker(
            LocalDate.now().plusYears(100));
    private VBox vBox = new VBox();
    private HBox hBoxU = new HBox();
    private HBox hBoxF = new HBox();
    private HBox hBoxL = new HBox();
    private HBox hBoxP = new HBox();
    private HBox hBoxD = new HBox();
    private Button btnRegister = new Button("Register");
    private Label message = new Label("");

    public RegisterView() {
        super.setTitle("Register");
        super.setWidth(350);
        super.setHeight(400);
        super.getIcons().add(new Image("spaceship.png"));
        vBox.setPadding(new Insets(40));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        hBoxF.setAlignment(Pos.BASELINE_RIGHT);
        hBoxU.setAlignment(Pos.BASELINE_RIGHT);
        hBoxP.setAlignment(Pos.BASELINE_RIGHT);
        hBoxL.setAlignment(Pos.BASELINE_RIGHT);
        hBoxD.setAlignment(Pos.BASELINE_RIGHT);

        btnRegister.setOnAction(e -> {
            JDBCUtils.addUser(
                    tfName.getText(),tfLast.getText(),
                    tfUserName.getText(),tfPassword.getText(),datePicker.getValue());
            HomeView homeView = new HomeView();
            homeView.show();

            this.close();
        });

        hBoxF.getChildren().addAll(lblName,tfName);
        hBoxL.getChildren().addAll(lblLast,tfLast);
        hBoxU.getChildren().addAll(lblUserName,tfUserName);
        hBoxP.getChildren().addAll(lblPassword,tfPassword);
        hBoxD.getChildren().addAll(lblDate,datePicker);


        vBox.getChildren().addAll(label,hBoxF,hBoxL,hBoxU,hBoxP,hBoxD,btnRegister,message);
        root.setCenter(vBox);
        super.setScene(new Scene(this.root));
    }
}
