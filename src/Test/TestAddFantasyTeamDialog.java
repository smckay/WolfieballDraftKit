/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.EditPlayerDialog;
import static javafx.application.Application.launch;
import file.JsonFileManager;
import java.util.ArrayList;
import data.Hitter;

/**
 *
 * @author sammckay
 */
public class TestAddFantasyTeamDialog{
    

    
    public static void main(String[] args) throws Exception{
        JsonFileManager j = new JsonFileManager();
        ArrayList<Hitter> hitters = j.loadHitters("./data/Hitters.json");
        

        
    }
    
}
