package calculator;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalculatorApp extends Application {
	
	TextField tfIn,tfOut;


	@Override
	public void start(Stage primaryStage) {
		//Create the TextFields
		tfIn=new TextField();
		tfIn.setEditable(false);
		
		tfOut=new TextField();
		tfOut.setEditable(false);
		
		//Create the Label
		Label lb=new Label("=");
		
		//Create the Buttons
		Button[] btnN = new Button[10];
		 for (int i = 0; i < btnN.length; i++) {
			 btnN[i] = new Button(Integer.toString(i));
		 }
		 Button btnPt=new Button(".");
		 Button btnAdd=new Button("+");
		 Button btnSub=new Button("ー");
		 Button btnMul=new Button("×");
		 Button btnDiv=new Button("÷");
		 Button btnEqu=new Button("=");
		 Button btnC=new Button("C");
		 Button btnAC=new Button("AC");
		 
		//Set Buttons Size
		 double a=55;
		 double b=35;
		 double c=60;
		 double d=60;
		 for (int i = 0; i < btnN.length; i++) {
			 btnN[i].setPrefWidth(a);
			 btnN[i].setPrefHeight(b);
		 }
		 btnPt.setPrefWidth(c);
		 btnPt.setPrefHeight(d);
		 btnAdd.setPrefWidth(c);
		 btnAdd.setPrefHeight(d);
		 btnSub.setPrefWidth(c);
		 btnSub.setPrefHeight(d);
		 btnMul.setPrefWidth(c);
		 btnMul.setPrefHeight(d);
		 btnDiv.setPrefWidth(c);
		 btnDiv.setPrefHeight(d);
		 btnEqu.setPrefWidth(c);
		 btnEqu.setPrefHeight(d);
		 btnC.setPrefWidth(a);
		 btnC.setPrefHeight(b);
		 btnAC.setPrefWidth(a);
		 btnAC.setPrefHeight(b);
		 
		 //Set Buttons Actions
		 for (int i = 0; i < btnN.length; i++) {
			 btnN[i].setOnAction(e -> btnN_Action(e));
		 }
		 btnPt.setOnAction(e -> btnN_Action(e));
		 btnAdd.setOnAction(e -> btnN_Action(e));
		 btnSub.setOnAction(e -> btnN_Action(e));
		 btnMul.setOnAction(e -> btnN_Action(e));
		 btnDiv.setOnAction(e -> btnN_Action(e));
		 btnC.setOnAction(e -> btnC_Action());
		 btnAC.setOnAction(e -> btnAC_Action());
		 btnEqu.setOnAction(e->showAns());
		 
		// Create the Layout
		 HBox hb1 = new HBox(10, btnC, btnAC, btnEqu);
		 hb1.setAlignment(Pos.CENTER);
		 HBox hb2 = new HBox(10, btnN[7], btnN[8], btnN[9]);
		 hb2.setAlignment(Pos.CENTER);
		 HBox hb3 = new HBox(10, btnN[4], btnN[5], btnN[6]);
		 hb3.setAlignment(Pos.CENTER);
		 HBox hb4 = new HBox(10, btnN[1], btnN[2], btnN[3]);
		 hb4.setAlignment(Pos.CENTER);
		 HBox hb5 = new HBox(10, btnN[0], btnC, btnAC);
		 hb5.setAlignment(Pos.CENTER);
		 HBox hb6 = new HBox(10, tfIn, lb, tfOut);
		 hb6.setAlignment(Pos.CENTER);
		 VBox vb1 = new VBox(20,  hb2, hb3, hb4, hb5);
		 VBox vb2 = new VBox(20, btnAdd, btnDiv, btnPt);
		 VBox vb3 = new VBox(20, btnSub,btnMul,btnEqu);
		 HBox hb7 = new HBox(20, vb1, vb2,vb3);
		 hb7.setAlignment(Pos.CENTER);
		 VBox vb4 = new VBox(20, hb6,hb7);
		 vb4.setPadding(new Insets(20));
		 
		 //Create Buttons Style
		 String style = String.join(";", buttonStyle);
		 btnPt.setStyle(style);
		 btnAdd.setStyle(style);
		 btnSub.setStyle(style);
		 btnMul.setStyle(style);
		 btnDiv.setStyle(style);
		 btnEqu.setStyle(style);
		 
		 //Set the Stages
		 Scene scene = new Scene(vb4);
		 File css = new File("src/calculator/stylesheet.css");
		 scene.getStylesheets().add(css.toURI().toString());
		 primaryStage.setScene(scene);
		 primaryStage.setTitle("Calculator Application");
		 primaryStage.show();
	}
	
	public void btnN_Action(ActionEvent e) {
		tfOut.clear();
		Button b = (Button) e.getSource();
		tfIn.appendText(b.getText());
	}
	
	public void btnC_Action() {
		 tfOut.clear();
		 int len = tfIn.getLength();
		 if (len > 0) {
		 tfIn.deleteText(len - 1, len);
		 }
	}
	
	public void btnAC_Action() {
		 tfIn.clear();
		 tfOut.clear();
	}
	
	public void showAns() {
		 String ans = Calculation.calculate(tfIn.getText());
		 tfOut.setText(ans);
	}
	
	String[] buttonStyle = {
			"-fx-background-radius:50;",
			"-fx-front-family:serif;",
			"-fx-text-fill:black;",
			"-fx-background-color:gold",
			"-fx-font-size:10pt;",
			"-fx-font-weight:bold;",
	};
	
	public static void main(String[] args) {
		 // アプリケーションを起動する
		 Application.launch(args);
	     System.out.println("完了--CalculationApp");
	}
}



