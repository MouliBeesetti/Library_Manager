import javafx.beans.property.SimpleStringProperty;

public class User {

    private  final SimpleStringProperty ID;
    private  final SimpleStringProperty userName;
    private  final SimpleStringProperty password;
    private  final SimpleStringProperty firstName;
    private  final SimpleStringProperty lastName;
    private  final SimpleStringProperty email;
    private  final SimpleStringProperty is_admin;

    public  User(String id,String uName,String pswd,String fName,String lName,String mail,String is_Admin){
        this.ID = new SimpleStringProperty(id);
        this.userName = new SimpleStringProperty(uName);
        this.password = new SimpleStringProperty(pswd);
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(mail);
        this.is_admin = new SimpleStringProperty(is_Admin);

    }

    public  String getID(){
        return  ID.get();
    }

    public  String getUserName(){
        return  userName.get();
    }

    public  String getPassword(){
        return  password.get();
    }

    public  String getFirstName(){
        return  firstName.get();
    }

    public  String getLastName(){
        return  lastName.get();
    }

    public  String getEmail(){

        return  email.get();
    }

    public  String getIs_Admin(){
        return  is_admin.get();
    }

    public void setID(String id) {
        ID.set(id);
    }

    public void setUserName(String uName) {
        userName.set(uName);
    }

    public void setPassword(String pswd) {
        password.set(pswd);
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public void setLastName(String lName) {
        lastName.set(lName);
    }

    public void setEmail(String mail) {
        email.set(mail);
    }

    public void setIs_admin(String is_Admin) {
        is_admin.set(is_Admin);
    }
}
