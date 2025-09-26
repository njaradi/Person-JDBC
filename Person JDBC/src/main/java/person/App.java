package person;

import javafx.application.Application;
import javafx.stage.Stage;
import person.model.utility.JDBCUtils;
import person.view.HomeView;
import person.view.LoginView;
import person.view.RegisterView;
import person.view.StartView;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        JDBCUtils.connect();
        stage = new StartView();
        stage.show();
    }
}
