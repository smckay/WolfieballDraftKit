/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.AddFantasyTeamDialog;
import static javafx.application.Application.launch;

/**
 *
 * @author sammckay
 */
public class TestAddFantasyTeamDialog extends Application{
    
    public void start(Stage primaryStage) throws Exception {

        AddFantasyTeamDialog testDialog = new AddFantasyTeamDialog(primaryStage, "Complete");
        testDialog.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
