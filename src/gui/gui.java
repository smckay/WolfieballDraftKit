/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wolfieballdraftkit.PropertyType;
import wolfieballdraftkit.StartupConstants;
import Controller.FileController;
import javafx.scene.text.Text;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.control.TableView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author sammckay
 */
public class gui {
    
    // THIS IS THE APPLICATION WINDOW
    Stage primaryStage;
    
     // THIS IS THE STAGE'S SCENE GRAPH
    Scene primaryScene;
    
    Text fantasyTeamsText;
    Text playersText;
    Text fantasyStandingsText;
    Text draftText;
    Text mlbTeamsText;
    
    // THESE PANES ORGANIZE THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane wbPane;
    
    BorderPane outerPane;
    
    BorderPane fantasyTeamsPane;
    BorderPane playersPane;
    BorderPane fantasyStandingsPane;
    BorderPane draftPane;
    BorderPane mlbTeamsPane;
    
    BorderPane innerPlayersPane;
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane topToolbarPane;
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportButton;
    Button exitButton;
    
    // THIS IS THE BOTTOM TOOLBAR AND ITS CONTROLS
    FlowPane bottomToolbarPane;
    Button FantasyTeams;
    Button Players;
    Button FantasyStandings;
    Button Draft;
    Button MLBTeams;
    
    // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    BorderPane workspacePane;
    
    TableView playersTable;
    
    FileController filecontroller;
    
    
    public gui(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }
 
    public void initGui(){
    
            initTopToolbar();
            
            initBottomToolbar();
            
            initEventHandlers();
            
            initWindow("Wolfieball");
        
    }
    
    public void initEventHandlers(){
        filecontroller = new FileController();
        newDraftButton.setOnAction(e -> {
            filecontroller.handleNewDraftRequest(this);
        });
        FantasyTeams.setOnAction(e -> {
            initFantasyTeamsScreen();
        });
        Players.setOnAction(e -> {
            initPlayersScreen();
        });
        FantasyStandings.setOnAction(e -> {
            initFantasyStandingsScreen();
        });
        Draft.setOnAction(e -> {
            initDraftScreen();
        });
        MLBTeams.setOnAction(e -> {
            initMlbTeamsScreen();
        });
    
    };
    
    public void initFantasyTeamsScreen(){

        fantasyTeamsPane = new BorderPane();
        fantasyTeamsText = new Text("   Fantasy Teams");
        fantasyTeamsText.setFont(Font.font("Verdana", 30));

        fantasyTeamsPane.setTop(fantasyTeamsText);

        wbPane.setCenter(fantasyTeamsPane);
        wbPane.setBottom(bottomToolbarPane);
        
      
    }
    
    public void initPlayersScreen(){

        playersPane = new BorderPane();
        playersText = new Text("   Available Players");
        playersText.setFont(Font.font("Verdana", 30));
        
        innerPlayersPane = new BorderPane();
        
        playersTable = new TableView();
        playersTable.setEditable(true);       
       
        TableColumn firstNameCol = new TableColumn("First");
        TableColumn lastNameCol = new TableColumn("Last");
        TableColumn teamCol = new TableColumn("Pro Team");
        TableColumn positionsCol = new TableColumn("Positions");
        TableColumn birthCol = new TableColumn("Year of Birth");
        
        TableColumn rwCol = new TableColumn("R/W");
        TableColumn hrsvCol = new TableColumn("HR/SV");
        TableColumn rbikCol = new TableColumn("RBI/K");
        TableColumn sberaCol = new TableColumn("SB/ERA");
        TableColumn bawhipCol = new TableColumn("BA/WHIP");
        TableColumn estValCol = new TableColumn("Estimated Value");
        TableColumn notesCol = new TableColumn("Notes");
        
        playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, rwCol, hrsvCol, sberaCol, bawhipCol, estValCol, notesCol);
        
        HBox box = new HBox();
        
        ToggleGroup group = new ToggleGroup();
        
        RadioButton all = new RadioButton("All  ");
        all.setToggleGroup(group);
        all.setSelected(true);
        
        RadioButton c = new RadioButton("C  ");
        c.setToggleGroup(group);
        
        RadioButton b1 = new RadioButton("1B  ");
        b1.setToggleGroup(group);
        
        RadioButton cl = new RadioButton("Cl  ");
        cl.setToggleGroup(group);
        
        RadioButton b3 = new RadioButton("3B  ");
        b3.setToggleGroup(group);
        
        RadioButton b2 = new RadioButton("2B  ");
        b2.setToggleGroup(group);
        
        RadioButton mi = new RadioButton("MI  ");
        mi.setToggleGroup(group);
        
        RadioButton ss = new RadioButton("SS  ");
        ss.setToggleGroup(group);
        
        RadioButton of = new RadioButton("OF  ");
        of.setToggleGroup(group);
        
        RadioButton u = new RadioButton("U  ");
        u.setToggleGroup(group);
        
        RadioButton p = new RadioButton("P  ");
        p.setToggleGroup(group);

        box.getChildren().addAll(all, c, b1, cl, b3, b2, mi, ss, of, u, p);
        
        box.setStyle("-fx-background-color: #336699;");
        
        VBox vbox = new VBox();
        HBox innerBox = new HBox();
        Label search = new Label("Search:");
        TextField text = new TextField();
        
        Button add = new Button();
        Button remove = new Button();
        
        innerBox.getChildren().addAll(add, remove, search, text);
        
        vbox.getChildren().addAll(playersText, innerBox, box);
        
        innerPlayersPane.setTop(vbox);
        innerPlayersPane.setCenter(playersTable);
       
        playersPane.setCenter(innerPlayersPane);

        wbPane.setCenter(playersPane);
        wbPane.setBottom(bottomToolbarPane);
        
      
    }
    
    public void initFantasyStandingsScreen(){

        fantasyStandingsPane = new BorderPane();
        fantasyStandingsText = new Text("   Fantasy Standings");
        fantasyStandingsText.setFont(Font.font("Verdana", 30));

        fantasyStandingsPane.setTop(fantasyStandingsText);

        wbPane.setCenter(fantasyStandingsPane);
        wbPane.setBottom(bottomToolbarPane);
        
      
    }
    
    public void initDraftScreen(){

        draftPane = new BorderPane();
        draftText = new Text("   Draft Summary");
        draftText.setFont(Font.font("Verdana", 30));

        draftPane.setTop(draftText);

        wbPane.setCenter(draftPane);
        wbPane.setBottom(bottomToolbarPane);
        
      
    }
    
    public void initMlbTeamsScreen(){

        mlbTeamsPane = new BorderPane();
        mlbTeamsText = new Text("   MLB Teams");
        mlbTeamsText.setFont(Font.font("Verdana", 30));

        mlbTeamsPane.setTop(mlbTeamsText);

        wbPane.setCenter(mlbTeamsPane);
        wbPane.setBottom(bottomToolbarPane);
        
      
    }
    
    public void initTopToolbar(){
       topToolbarPane = new FlowPane();
      
       // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
       // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
       newDraftButton = initChildButton(topToolbarPane, PropertyType.NEW_DRAFT_ICON, "New Draft", false);
       loadDraftButton = initChildButton(topToolbarPane, PropertyType.LOAD_DRAFT_ICON, "Load Draft", false);
       saveDraftButton = initChildButton(topToolbarPane, PropertyType.SAVE_DRAFT_ICON, "Save Draft", true);
       exportButton = initChildButton(topToolbarPane, PropertyType.EXPORT_ICON, "Export Draft", true);
       exitButton = initChildButton(topToolbarPane, PropertyType.EXIT_ICON, "Exit", false);
    };
    
    public void initBottomToolbar(){
       bottomToolbarPane = new FlowPane();
      
       // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
       // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
       FantasyTeams = initChildButton(bottomToolbarPane, PropertyType.FANTASY_TEAMS_ICON, "Fantasy Teams Screen", false);
       Players = initChildButton(bottomToolbarPane, PropertyType.PLAYERS_ICON, "Players Screen", false);
       FantasyStandings = initChildButton(bottomToolbarPane, PropertyType.FANTASY_STANDINGS_ICON, "Fantasy Standings Screen", false);
       Draft = initChildButton(bottomToolbarPane, PropertyType.DRAFT_ICON, "Draft Screen", false);
       MLBTeams = initChildButton(bottomToolbarPane, PropertyType.MLBTEAMS_ICON, "MLB Teams Screen", false);
    }
    
    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Course IS CREATED OR LOADED
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        wbPane = new BorderPane();
        wbPane.setTop(topToolbarPane);
        primaryScene = new Scene(wbPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        //primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        
        wbPane.setStyle("-fx-background-color: #336699;");
    }
    
    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, PropertyType icon, String tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + StartupConstants.PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(tooltip);
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
     public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveDraftButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadDraftButton.setDisable(false);
        exportButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }
     
  
    
}
