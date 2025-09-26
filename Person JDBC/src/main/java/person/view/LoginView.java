package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.model.utility.JDBCUtils;

public class LoginView extends Stage {
    private final BorderPane root = new BorderPane();
    private Label label = new Label("Log in");
    private Label lblUserName = new Label("Username: ");
    private Label lblPassword = new Label("Password: ");
    private TextField tfUserName = new TextField();
    private TextField tfPassword = new TextField();
    private VBox vBox = new VBox();
    private HBox hBoxU = new HBox();
    private HBox hBoxP = new HBox();
    private Button btnLogin = new Button("Log in");
    private Label message = new Label("");
    public LoginView() {
        super.setTitle("Log in");
        super.setWidth(350);
        super.setHeight(400);
        super.getIcons().add(new Image("spaceship.png"));
        vBox.setPadding(new Insets(40));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);

        vBox.getChildren().addAll(label, hBoxU, hBoxP, btnLogin, message);
        hBoxU.getChildren().addAll(lblUserName, tfUserName);
        hBoxP.getChildren().addAll(lblPassword, tfPassword);
        root.setCenter(vBox);

        btnLogin.setOnAction(e -> {
            boolean success = JDBCUtils.checkCredentials(tfUserName.getText(),tfPassword.getText());
            if(success)
            {
                HomeView homeView = new HomeView();
                homeView.show();

                this.close();
            }
            else
            {
                message.setText("Nisi uspeo :(");
            }
        });

        super.setScene(new Scene(this.root));
    }
}
