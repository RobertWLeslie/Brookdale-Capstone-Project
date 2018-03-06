/* This is the GUI that would be used by the bar
 * at the club, for members to fill out to order
 * drinks.
 * 
 * Because all of the reciepts at the club look
 * nearly the same, the GUIs will not have to
 * be changed much from counter to counter. */
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

public class southChit extends Application{

	TextField memNumEnter = new TextField();
	TextArea orderField = new TextArea();
	TextField orderTotalTextField = new TextField();
	Button placeOrder = new Button("Order!");
	Label lblStatus = new Label();
	Statement stmnt;
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Enter the date
		//Label date = new Label("Date");
		//TextField dateEnter = new TextField();
		
		// Enter the last name
		//Label lastName = new Label("Member Last Name: ");
		//TextField lastNameEnter = new TextField();
		
		// Enter the member number
		Label memNum = new Label("Member Number:");
		
		
		// Enter the order
		Label orderTxt = new Label("Order: (Place price at end of order name)");
		
		orderField.setPrefHeight(4);
		orderField.setPrefColumnCount(10);
		
		// Enter the total
		
		Label orderTotal = new Label("Total: ");
		
		
		
		GridPane southChitt = new GridPane();
		//southChitt.add(date, 0, 0);
		southChitt.add(lblStatus, 0, 5);
		southChitt.add(memNum, 0, 1);
		southChitt.add(memNumEnter,1,1);
		southChitt.add(orderTxt, 0, 2);
		southChitt.add(orderField, 1, 2);
		southChitt.add(orderTotalTextField, 1, 3);
		southChitt.add(orderTotal, 0, 3);
		southChitt.add(placeOrder, 0, 4);
		
		southChitt.setVgap(10);
		southChitt.setHgap(10);
		
		Scene sSouthChit = new Scene(southChitt);
		primaryStage.setTitle("SBC South Stand");
		primaryStage.setScene(sSouthChit);
		primaryStage.show();
		
		initializeDB();
		
		placeOrder.setOnAction(e -> insertSouth());
	}
	
	void insertSouth(){
		String insertStmntSouth = "INSERT INTO south_stand_reciepts VALUES("+memNumEnter.getText().trim() +",NOW(),'"+orderField.getText().trim()+"',"+orderTotalTextField.getText().trim()+");";
		try{
			stmnt.executeUpdate(insertStmntSouth);
			lblStatus.setText("order placed");
		}catch(SQLException ex){
			lblStatus.setText("insert Failed");
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