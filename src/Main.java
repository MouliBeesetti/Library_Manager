import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main extends Application {

    Stage window;
    Button loginBtn;
    static Connection conn;
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    Stage adminpage;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Library Manager");

        //Username Field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(200);

        //Password Field

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);

        // Login Button
        loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            try {

                String query = "select * from Users where Username=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, usernameField.getText());
                rs = pst.executeQuery();
                if (JavaPasswordSecurity.validatePassword(passwordField.getText(), rs.getString("Password"))) {
                    String query_isAdmin = "select * from Users WHERE Username =?";
                    pst = conn.prepareStatement(query_isAdmin);
                    pst.setString(1, usernameField.getText());
                    rs = pst.executeQuery();
                    int is_Admin = Integer.parseInt(rs.getString("Is_Admin"));

                    if (is_Admin == 1) {
                        System.out.println("Admin");
                        window.hide();
                        adminpage = Admin.adminPage();
                        adminpage.show();
                    } else if (is_Admin == 0) {
                        System.out.println("staff");
                    }

                } else {
                    Alert loginError = new Alert(Alert.AlertType.ERROR);
                    loginError.setTitle("Error");
                    loginError.setContentText("Login Failed\nPlease Check Username and Password...");
                    loginError.setHeaderText(null);
                    loginError.showAndWait();
                }


            } catch (Exception err) {
                Alert SqlError = new Alert(Alert.AlertType.ERROR);
                SqlError.setTitle("Error");
                SqlError.setContentText("SQL Error:\n" + err);
                SqlError.setHeaderText(null);
                SqlError.showAndWait();
            }

        });

        CheckConnection();

        //Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(usernameField, passwordField, loginBtn);
        layout.setAlignment(Pos.CENTER);

        Scene mainScene = new Scene(layout, 330, 400);

        window.setScene(mainScene);
        window.show();
    }


    public void CheckConnection() {
        conn = DatabaseManager.DbConnector();
        if (conn == null) {
            Alert connError = new Alert(Alert.AlertType.ERROR);
            connError.setTitle("Error");
            connError.setContentText("Unable to connect to database \nClosing Program...");
            connError.setHeaderText(null);
            connError.showAndWait();
            System.exit(1);
        } else {
            System.out.println("Connection  successful");
        }
    }

    public static ObservableList<User> GetUserData() {
        ObservableList<User> userData = FXCollections.observableArrayList();
        try {
            String query = "select * from Users";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                userData.add(new User(
                        rs.getString("ID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Firstname"),
                        rs.getString("Lastname"),
                        rs.getString("Email"),
                        rs.getString("Is_Admin")

                ));
            }

        } catch (Exception e1) {
            System.out.println(e1);
        }
        return userData;
    }
}