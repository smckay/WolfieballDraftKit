/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import data.*;

/**
 *
 * @author sammckay
 */
public class EditFantasyTeamDialog extends Stage{
 VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button completeButton;
    Button cancelButton;
    
    Label team;
    Label name;
    
    TextField teamField;
    TextField ownerField;
    
    /**
     * Initializes this dialog so that it can be used repeatedly
     * for all kinds of messages.
     * 
     * @param owner The owner stage of this modal dialoge.
     * 
     * @param closeButtonText Text to appear on the close button.
     */
    public EditFantasyTeamDialog(Stage owner, gui GUI) {
        // MAKE IT MODAL
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        
        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label("Edit Fantasy Team");        

        team = new Label("Name:");
        name = new Label("Owner:");
        
        teamField = new TextField();
        ownerField = new TextField();
        
        // CLOSE BUTTON
        completeButton = new Button("Complete");
        completeButton.setOnAction(e->{            
            //GUI.fTeamNames.set(GUI.fTeamNames.indexOf((String)GUI.selectTeams.getValue(), teamField.getText()));
            EditFantasyTeamDialog.this.close(); });

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e->{ EditFantasyTeamDialog.this.close(); });
        

        
        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().addAll(team, teamField);
        messagePane.getChildren().addAll(name, ownerField);
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
