import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class memberForm extends Application{

	String lastName;
	int familySize;
	
	Button btAddMem = new Button("Add Member");
	TextField lastNameField = new TextField();
	TextField familySizeField = new TextField();
	
	Label lblStatus = new Label();
	
	//Statement for processing queeries
	
	Statement stmnt;
	
	public void start(Stage addMembers) throws Exception {
		
	/* The add memebers window */
		Label lastNameLabel = new Label("Last Name:");
		Label familySizeLabel = new Label("Family Size:");
		
		GridPane addMembersPane = new GridPane();
				
		addMembersPane.setVgap(10);
		addMembersPane.setHgap(10);
		addMembersPane.add(lastNameLabel, 0, 0);
		addMembersPane.add(lastNameField, 1, 0);
		addMembersPane.add(familySizeLabel, 0, 1);
		addMembersPane.add(familySizeField, 1, 1);
		addMembersPane.add(btAddMem, 2, 0);
		addMembersPane.add(lblStatus, 2, 1);
	
		Scene addMemScene = new Scene(addMembersPane);
		addMembers.setTitle("SBC Key Office Window");
		addMembers.setScene(addMemScene);
		addMembers.show();
		
		initializeDB();
	
		btAddMem.setOnAction(e ->insertMem());
		
	}
	
	/* Insert a new member */
	void insertMem(){
		String insertStmntMem = "INSERT INTO members VALUES(DEFAULT,"
														+ "'"+lastNameField.getText().trim()+"',"
														+ "'"+familySizeField.getText().trim()+"');";
		try{
			stmnt.executeUpdate(insertStmntMem);
			lblStatus.setText("Member Added");
		}catch(SQLException ex){
			lblStatus.setText("insert failed");
		}
	}
	
	void initializeDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// Connect to local InterBase database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adv_soft_proj","root","");
			System.out.println("Databse COnected \n");
			lblStatus.setText("Database Connected");
			stmnt = conn.createStatement();
		}
		catch(Exception ex){
			lblStatus.setText("Connection failed: "+ ex);
			System.out.println("Connection failed: "+ ex);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);
	}
}
