import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {

    static Connection connection;
    static PreparedStatement pst = null;
    static ResultSet rs = null;


    public static Stage adminPage (){
        Stage adminPage = new Stage();
        adminPage.setTitle("Library Manager - Admin");

        //Labels
        Label label = new Label("Welcome to Admin Page");
        Label userInfoLabel = new Label("User Info");
        Label createUserLabel = new Label("Create User");

        // Text Fields
        TextField userName = new TextField();
        userName.setPromptText("User Name");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");

        TextField emailId = new TextField();
        emailId.setPromptText("Email ID");

        CheckBox is_AdminBox = new CheckBox();

        Label is_AdminLabel = new Label("Admin");

        Button saveBtn = new Button("Save");
        Button userManagerBtn = new Button("User Manager");
        Button homeBtn = new Button("Back");
        Button loadBtn = new Button("Load User Data");



        //User Table view
        TableView<User> userTable = new TableView<>();
        ObservableList<User> userData = FXCollections.observableArrayList();
        userTable.setItems(userData);

        TableColumn idColumn = new TableColumn("S.No");
        idColumn.setMaxWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn userNameColumn = new TableColumn("Username");
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));

        TableColumn passwordColumn = new TableColumn("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn firstnameColumn = new TableColumn("First Name");
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn lastnameColumn = new TableColumn("Last Name");
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn emailColumn = new TableColumn("Email ID");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(150);

        TableColumn is_adminColumn = new TableColumn("User Level");
        is_adminColumn.setCellValueFactory(new PropertyValueFactory<>("is_Admin"));

        userTable.getColumns().addAll(idColumn,userNameColumn,passwordColumn,firstnameColumn,lastnameColumn,emailColumn,is_adminColumn);

        //Layouts

        //Left Side bar
        VBox leftSideBar = new VBox(15);
        HBox hbar1 = new HBox(5);
        HBox hbar2 = new HBox(10);
        hbar2.setPadding(new Insets(80,20,20,20));
        hbar2.setAlignment(Pos.BOTTOM_CENTER);
        hbar2.getChildren().addAll(loadBtn);
        leftSideBar.setPadding(new Insets(80,20,20,20));
        leftSideBar.setMinWidth(250);
        hbar1.getChildren().addAll(is_AdminBox,is_AdminLabel);
        leftSideBar.getChildren().addAll(createUserLabel,userName,password,confirmPassword,emailId,hbar1,saveBtn,hbar2);

        //Right Side bar
        VBox rightSidebar = new VBox(50);
        rightSidebar.getChildren().addAll();

        //Top bar
        HBox topBar2 = new HBox(20);
        topBar2.setPadding(new Insets(20,20,20,20));
        topBar2.setAlignment(Pos.TOP_LEFT);
        topBar2.getChildren().addAll(userManagerBtn);

        //Top bar
        HBox topBar3 = new HBox(20);
        topBar3.setPadding(new Insets(20,20,20,20));
        topBar3.setAlignment(Pos.TOP_LEFT);
        topBar3.getChildren().addAll(homeBtn);

        //Bottom bar
        HBox bottomBar = new HBox();
        bottomBar.getChildren().addAll();

        //Center bar
        StackPane centerBar = new StackPane();
        centerBar.setPadding(new Insets(20,20,60,20));
        centerBar.getChildren().addAll(userTable);

        //main Layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftSideBar);
        borderPane.setRight(rightSidebar);
        borderPane.setCenter(centerBar);
        borderPane.setBottom(bottomBar);
        borderPane.setTop(topBar3);

        //StackPane
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().addAll(topBar2,label);
        mainLayout.setAlignment(Pos.CENTER);

        Scene adminScene = new Scene(mainLayout,1080,680);
        Scene userManagerScene = new Scene(borderPane,1080,680);
        adminPage.setScene(adminScene);

        userManagerBtn.setOnAction(e->{
        adminPage.setScene(userManagerScene);
        });

        homeBtn.setOnAction(e->{
            adminPage.setScene(adminScene);
        });

        loadBtn.setOnAction(e->{
        userTable.setItems(Main.GetUserData());

        });

        return adminPage;

    }

}
