package person.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartView extends Stage {
    private final BorderPane root = new BorderPane();

    VBox vBox = new VBox();
    private final Button btLog = new Button("Log in");
    private final Button btReg = new Button("Register");
    public StartView() {
        super.setTitle("ZUS");
        super.setWidth(180);
        super.setHeight(250);
        super.getIcons().add(new Image("spaceship.png"));
        vBox.setPadding(new Insets(40));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        btLog.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.show();

            this.close();
        });

        btReg.setOnAction(e -> {
            RegisterView registerView = new RegisterView();
            registerView.show();

            this.close();
        });
        vBox.getChildren().addAll(btLog,btReg);
        root.setCenter(vBox);

        super.setScene(new Scene(this.root));
    }

}
