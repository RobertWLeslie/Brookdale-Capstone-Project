import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MemberRetriever extends Application{

	
	
	
	TextArea membersarea;
	private Button retrive, clear;
	
	Statement stmnt;
	
	public static void main(String[] args) {
		launch(args);

	}
	
	public void start (Stage primaryStage){
		
		BorderPane mainPane = new BorderPane();
		
		VBox membersTextArea = new VBox(10);
		membersarea = new TextArea();
		membersarea.setPrefColumnCount(10);
		membersarea.setPrefRowCount(8);
		membersarea.setEditable(false);
		membersTextArea.getChildren().addAll(new Label("List of members"), new ScrollPane(membersarea));
		
		mainPane.setCenter(membersTextArea);
		
		HBox but_pane1 = new HBox(10); // heh heh...
		retrive = new Button("Retrive members");
		clear = new Button("Clear members");
		but_pane1.getChildren().addAll(retrive, clear);
		
		mainPane.setBottom(but_pane1);
		
		RetrieverHandler rH = new RetrieverHandler();
		ClearHandler cH = new ClearHandler();
		retrive.setOnAction(rH);
		clear.setOnAction(cH);
		
		Scene scene = new Scene(mainPane);
		primaryStage.setTitle("Member Retriever");
		primaryStage.setScene(scene);
	
		primaryStage.show();
		
		TextArea consoleOutPut = new TextArea();
		consoleOutPut.setPrefWidth(800);
		consoleOutPut.setPrefHeight(600);
		consoleOutPut.setWrapText(true);
		
		Console console = new Console(consoleOutPut);
		
		PrintStream ps = new PrintStream(console, true);
		System.setOut(ps);
		System.setErr(ps);
		
		Scene consoleScene = new Scene(consoleOutPut);
		
		Stage consoleWindow = new Stage();
		consoleWindow.setScene(consoleScene);
		consoleWindow.setTitle("Console output");
		consoleWindow.show();
		
	}

	public static class Console extends OutputStream{
		
		private TextArea output;
		public Console(TextArea ta){
			this.output = ta;
		}
		
		@Override
		public void write(int i) throws IOException{
			output.appendText(String.valueOf((char)i));
		}
	}

}

class RetrieverHandler implements EventHandler<ActionEvent>{

	public void handle(ActionEvent e) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Driver Loaded");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adv_soft_proj","root","");
			
			System.out.println("Database Connected");
			
			Statement stmnt = conn.createStatement();
			
			ResultSet rsltSt = stmnt.executeQuery("SELECT family_name, idmembers FROM members");
			
			while(rsltSt.next()){
				//membersarea.appendText(rsltSt.getString(1)+"\t"+rsltSt.getString(2)+"\n");
				System.out.print(rsltSt.getString(1)+"\t"+rsltSt.getString(2)+"\n");
			}
			
			conn.close();
			
		}
		catch(ClassNotFoundException ex){
			message_time("Class not found","Error viewing members");
		}
		catch(SQLException ex){
			message_time("SQL exception", "Error viewing members");
		}
		
	}

	public void message_time(String message, String title) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
		
	}
	
}

class ClearHandler implements EventHandler<ActionEvent>{
	public void handle(ActionEvent e){
		//membersarea.setText("");
		System.out.println("Okay, this was supposed to empty out the text area, but unfortunately that isn't working at the moment...");
	}
}


