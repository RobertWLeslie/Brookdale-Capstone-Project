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

public class barChit extends Application{

	int memNum;
	//String lastName;
	String orderDesc;
	Double orderTotal;
	
	Label lblStatus = new Label();
	TextField lastNameEnter = new TextField();
	
	TextField memNumEnter = new TextField();
	
	TextArea orderField = new TextArea();
	
	TextField orderTotalTextField = new TextField();
	
	Button placeOrder = new Button("Order!");
	
	Statement stmnt;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*
		//Enter the date
		Label date = new Label("Date");
		TextField dateEnter = new TextField();
		*/
		
		// Enter the member number
		Label memNum = new Label("Member Number:");
		
		
		// Enter the last name
		//Label lastName = new Label("Member Last Name: ");
		
		
		
		// Enter the order
		Label orderTxt = new Label("Order: (Place total at end of order)");
		
		orderField.setPrefHeight(4);
		orderField.setPrefColumnCount(10);
		
		// Enter the total
		
		Label orderTotal = new Label("Total: ");
		
		// Order button and a message
		
		Label drinkResponsible = new Label ("Please drink responsibly");
		
		GridPane barChit = new GridPane();
		//barChit.add(date, 0, 0);
		barChit.add(lblStatus, 0, 5);
		barChit.add(memNum, 0, 1);
		barChit.add(memNumEnter,1,1);
		barChit.add(orderTxt, 0, 2);
		barChit.add(orderField, 1, 2);
		barChit.add(orderTotalTextField, 1, 3);
		barChit.add(orderTotal, 0, 3);
		barChit.add(placeOrder, 0, 4);
		barChit.add(drinkResponsible,1,4);
		
		barChit.setVgap(10);
		barChit.setHgap(10);
		
		Scene bArChit = new Scene(barChit);
		primaryStage.setTitle("SBC Bar");
		primaryStage.setScene(bArChit);
		primaryStage.show();
		
		initializeDB();
		
		placeOrder.setOnAction(e ->insertBar());
	}
	
	// Insert a bar order
	void insertBar(){
		String insertStmtntBar = "INSERT INTO bar_reciepts VALUES("+memNumEnter.getText().trim() +","
																	+ "NOW(),"
																	+ "'"+orderField.getText().trim()+"',"
																	+ ""+orderTotalTextField.getText().trim()+");";
		try{
			stmnt.executeUpdate(insertStmtntBar);
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
