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
/**
 *
 * @author sammckay
 */
public class AddFantasyTeamDialog extends Stage{
    Label messageLabel;
    VBox messagePane;
    Scene messageScene;
    Label nameLabel;
    Label ownerLabel;
    TextField nameField;
    TextField ownerField;
    
    Button completeButton;
    Button cancelButton;
    
    /**
     * Initializes this dialog so that it can be used repeatedly
     * for all kinds of messages.
     * 
     * @param owner The owner stage of this modal dialoge.
     * 
     * @param closeButtonText Text to appear on the close button.
     */
    public AddFantasyTeamDialog(Stage owner, String closeButtonText) {
    // MAKE IT MODAL
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        
        messageLabel = new Label("Fantasy Team Details");
              
        
        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        nameLabel = new Label("Name: ");   
        ownerLabel = new Label("Owner: ");
        nameField = new TextField();
        ownerField = new TextField();
        
        // CLOSE BUTTON
        completeButton = new Button("Complete");
        completeButton.setOnAction(e->{ AddFantasyTeamDialog.this.close(); });
        
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e->{ AddFantasyTeamDialog.this.close(); });
        

        
        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setMaxHeight(100.0);
        messagePane.setMinWidth(300);
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().addAll(messageLabel);
        messagePane.getChildren().addAll(nameLabel, nameField);
        messagePane.getChildren().addAll(ownerLabel, ownerField);
        messagePane.getChildren().addAll(completeButton, cancelButton);
        messagePane.setStyle("-fx-border: 10px solid black;"
                + "-fx-background-color:#111; color:#F00;");
        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(10, 20, 20, 20));
        messagePane.setSpacing(10);

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane, 300, 300);
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