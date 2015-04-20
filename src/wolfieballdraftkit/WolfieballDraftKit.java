/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import gui.gui;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author sammckay
 */
public class WolfieballDraftKit extends Application {
    
    gui GUI;
    
    @Override
    public void start(Stage primaryStage) {
        //boolean success = loadProperties();
        //if (success) {
            GUI = new gui(primaryStage);
            GUI.initGui();
        //}
        
    }
    
        /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    
    //TODO Create Error Handler 
    public boolean loadProperties() {
            try{
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, StartupConstants.PATH_DATA);
            props.loadProperties(StartupConstants.PROPERTIES_FILE_NAME, StartupConstants.PROPERTIES_SCHEMA_FILE_NAME);
            return true; }
            catch(InvalidXMLFileFormatException ixmlffe){return false;}
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
