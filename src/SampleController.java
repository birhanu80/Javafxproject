/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SampleController implements Initializable {
 	@FXML
	Button login;
	@FXML
	 TextField tfuser;
	@FXML
	 PasswordField tfpass;
	@FXML
	 Label label;
	@FXML
	Button btnLogin,btnCancel;
	@FXML
	TextField tfUser;
	@FXML
	PasswordField tfPass;
	@FXML
	Label message;
    /**
     * Initializes the controller class.
     */
	   static final String DB_URL = "jdbc:mysql://localhost/jdbcclass";
	    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	    static final String DB_USER = "root";
	    static final String DB_PASS = "";
	    
	    
	    
		 Connection connection ;
	     ResultSet result;
	       PreparedStatement ps;
	       public void onSignUp(ActionEvent event) throws IOException {
	    		            Parent root=FXMLLoader.load(getClass().getResource("Scene.fxml"));
	    	             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	             Scene scene=new Scene(root);
	    	         stage.setScene(scene);
	    	         stage.show();
	       }
	       public void onLogin(ActionEvent event) throws IOException {
	       if (tfUser.getText().trim().isEmpty()||tfPass.getText().trim().isEmpty()) {
	    	   message.setText("either name or password cannot be empty");
	    	   
	       }
	       String inputName=tfUser.getText();
	       String inputPassword=tfPass.getText();
	        	try {
	    	   connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
	           String query="select * from student where UserName=? AND Password=?";
	           ps=connection.prepareStatement(query);
	           ps.setString(1,inputName );
	           ps.setString(2,inputPassword);
	          result=ps.executeQuery();
	          if(!result.next())
	             message.setText("Please check your User name or password");
                  else{
                         Pane root=FXMLLoader.load(getClass().getResource("FXML.fxml"));
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      Label txt=new Label();
       txt.setText("username="+inputName+" password="+inputPassword);
        Scene scene=new Scene(root);
         stage.setScene(scene);
         try{
         root.getChildren().add(txt);}
         catch(Exception ec){
             System.out.println(ec);
         }
         stage.show();
                  }
	 
	       }
                     catch (SQLException ex) {
          System.out.println(ex);
         }        
               }
	public void signUp(ActionEvent event)throws ClassNotFoundException, IOException {
		 Class.forName(DB_DRIVER);
	        String username = tfuser.getText();
	        String password = tfpass.getText();
	        if (tfuser.getText().trim().isEmpty() || tfpass.getText().trim().isEmpty()) {
	            label.setText("All fields are required");
	        
	        }
                else{
	        String query = "INSERT INTO student (UserName, Password) VALUES (?, ?)";
	try {
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		   PreparedStatement statement = connection.prepareStatement(query);
           statement.setString(1, username);
           statement.setString(2, password);
           statement.executeUpdate();
           statement.close();
		Parent root=FXMLLoader.load(getClass().getResource("Sample.fxml"));
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
         stage.setScene(scene);
         stage.show();}
         catch (SQLException ex) {
          System.out.println(ex);
         }
	}}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
