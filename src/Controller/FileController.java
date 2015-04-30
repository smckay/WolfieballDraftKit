/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import properties_manager.PropertiesManager;
import gui.gui;
import java.io.IOException;
import gui.MessageDialog;
import gui.AddFantasyTeamDialog;

/**
 *
 * @author sammckay
 */
public class FileController {
 
    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;
    
    // WE'LL USE THIS TO GET OUR VERIFICATION FEEDBACK
    PropertiesManager properties;
    
    MessageDialog messageDialog;
    
    AddFantasyTeamDialog addFantasyTeamDialog;
    

    
    public FileController(MessageDialog initMessageDialog, AddFantasyTeamDialog initAddFantasyTeamDialog){
        saved = true;
        properties = PropertiesManager.getPropertiesManager();
        messageDialog = initMessageDialog;
        addFantasyTeamDialog = initAddFantasyTeamDialog;
    }
    
     public void markAsEdited(gui GUI) {
        // THE Course OBJECT IS NOW DIRTY
        saved = false;
        
        // LET THE UI KNOW
        GUI.updateToolbarControls(saved);
    }
     
     public void handleAddTeamRequest(gui GUI){
         addFantasyTeamDialog.show();
     }
     
      public void handleNewDraftRequest(gui GUI) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
               
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                GUI.updateToolbarControls(saved);
                
                
                messageDialog.show("New draft created");
                
                
            }
        } catch (Exception ioe) {
            // SOMETHING WENT WRONG, PROVIDE FEEDBACK
            System.out.println("Something went wrong");
        }
    }
      
      
      
    
}
