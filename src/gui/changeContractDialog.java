/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import data.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import data.*;

/**
 * This class serves to present custom text messages to the user when
 * events occur. Note that it always provides the same controls, a label
 * with a message, and a single ok button. The scheduled release of 
 * Java 8 version 40 in March 2015 will make this class irrelevant 
 * because the Alert class will do what this does and much more.
 * 
 * @author Richard McKenna
 */
public class changeContractDialog extends Stage {
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button completeButton;
    Button cancelButton;
    
    Label team;
    Label pos;
    Label con;
    Label sal;
    
    ComboBox teamBox;
    ComboBox posBox;
    ComboBox conBox;
    TextField salField;
    Player p;
    
    /**
     * Initializes this dialog so that it can be used repeatedly
     * for all kinds of messages.
     * 
     * @param owner The owner stage of this modal dialoge.
     * 
     * @param closeButtonText Text to appear on the close button.
     */
    public changeContractDialog(Stage owner, gui GUI) {
        // MAKE IT MODAL
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        
        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label("Player Details");        

        // CLOSE BUTTON
        completeButton = new Button("Complete");
        completeButton.setOnAction(e->{ 
            changeContractDialog.this.close(); });

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e->{ EditPlayerDialog.this.close(); });
        
        team = new Label("Fantasy Team:");
        pos = new Label("Position:");
        con = new Label("Contract:");
        sal = new Label("Salary ($):");
        
        teamBox = new ComboBox();
        teamBox.setItems(GUI.fTeamNames);
        conBox = new ComboBox();
        ArrayList contracts = new ArrayList();
        contracts.add("S1");
        contracts.add("S2");
        contracts.add("X");
        ObservableList cons = FXCollections.observableArrayList(contracts);
        conBox.setItems(cons);
        
        salField = new TextField();
        
        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().addAll(team, teamBox);
        messagePane.getChildren().addAll(con, conBox);
        messagePane.getChildren().addAll(sal, salField);
        messagePane.getChildren().addAll(completeButton, cancelButton);
        
         messagePane.setStyle("-fx-border: 10px solid black;"
                + "-fx-background-color:#111; color:#F00;");
        
        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(10, 20, 20, 20));
        messagePane.setSpacing(10);

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane);
        this.setScene(messageScene);
    }
 
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @param message Message to appear inside the dialog.
     */
    public void show(String message) {
        messageLabel.setText(message);
        this.showAndWait();
    }
}
