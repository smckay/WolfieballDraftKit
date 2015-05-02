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
import data.*;
import java.util.ArrayList;
import file.*;
/**
 *
 * @author sammckay
 */
public class gui {
    
    // THIS IS THE APPLICATION WINDOW
    Stage primaryStage;
    
     // THIS IS THE STAGE'S SCENE GRAPH
    Scene primaryScene;
    
    GridPane ex;
    
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
    
    RadioButton all;
    RadioButton c;
    RadioButton b1;
    RadioButton cl;
    RadioButton b3;
    RadioButton b2;
    RadioButton mi;
    RadioButton ss;
    RadioButton of;
    RadioButton u;
    RadioButton p;
    
    public ComboBox selectTeams;
    
    //fantasyTeamsPane components

    Button fAdd ;
    Button fRemove;
    Button fEdit;
    HBox fInnerBox; 
    VBox fVbox;
    
    //players screen components
    HBox pBox;
    Button pAdd;
    Button pRemove;
    
    // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    BorderPane workspacePane;
    
    TableView<Player> playersTable;
    TableView teamsTable;
    
    FileController filecontroller;
    MessageDialog messageDialog;
    AddFantasyTeamDialog addFantasyTeamDialog;
    AddNewPlayerDialog addNewPlayerDialog;
    EditPlayerDialog editPlayerDialog;
    EditFantasyTeamDialog editTeamDialog; 
    
    JsonFileManager j = new JsonFileManager();
    

    ArrayList<Hitter> hitters;
    ArrayList<Pitcher> pitchers;
    ArrayList<Player> players;
    ArrayList<String> teamNames;
    
    ObservableList<Player> play;
    
    ArrayList<Team> fTeams;
    ObservableList<Team> fantasyTeams;
    ArrayList<String> fNames;
    public ObservableList<String> fTeamNames;
    
    
    Player addedPlayer;
    Team addedTeam;
    
    
    
    public gui(Stage initPrimaryStage) throws Exception{
        primaryStage = initPrimaryStage;
        hitters = j.loadHitters("./data/Hitters.json");
        pitchers = j.loadPitchers("./data/Pitchers.json");
        players = new ArrayList<Player>();
        teamNames = new ArrayList<String>();
        fTeams = new ArrayList<Team>();
        fNames = new ArrayList<String>();
        for(Player p : hitters){
            players.add(p);
        }
        for(Player p : pitchers){
            players.add(p);
        }
        for(Hitter p: hitters){
           if(teamNames.indexOf(p.getTeam()) == -1)
               teamNames.add(p.getTeam());
        }
        play = FXCollections.observableArrayList(players);
        fantasyTeams = FXCollections.observableArrayList(fTeams);
        for(int i = 0; i < fTeams.size(); i++){
            fNames.add(fTeams.get(i).name);
        }
        fTeamNames = FXCollections.observableArrayList(fNames);
        
    }
    
    public ObservableList<Team> getFantasyTeams(){
        return fantasyTeams;
    }
 
    public void setAddedTeam(Team t){
        addedTeam = t;
        fantasyTeams.add(t);
        fTeamNames.add(t.name);
    }
    
    public void setAddedPlayer(Player p){
        addedPlayer = p;
        play.add(p);
        boolean b = false;
    }
    
    public void replaceTeamByName(String n, String replace){
        int index = fTeamNames.indexOf(n);
        System.out.println(replace);
        fTeamNames.set(index, replace);
        int i = 0;
       for(i = 0; i < fTeams.size(); i++){
           System.out.print(n + " " + fTeams.get(i).name);
           if(((String)(fTeams.get(i).name)).equals(n))
               break;
       }
       fTeams.set(i, new Team(replace, fTeams.get(i).owner));
    }
    
    public void initGui(){
    
            initDialogs();
        
            initTopToolbar();
            
            initBottomToolbar();
            
            initWindow("Wolfieball");
            
            initFantasyTeamsScreen();
            
            initPlayersScreen();
            
            initEventHandlers();
        
    }
    
    public void initEventHandlers(){
        filecontroller = new FileController(messageDialog, addFantasyTeamDialog, addNewPlayerDialog, editPlayerDialog, editTeamDialog);
        newDraftButton.setOnAction(e -> {
            filecontroller.handleNewDraftRequest(this);
            wbPane.setCenter(fantasyTeamsPane);
            wbPane.setBottom(bottomToolbarPane);
        });
        FantasyTeams.setOnAction(e -> {
            wbPane.setCenter(fantasyTeamsPane);
            wbPane.setBottom(bottomToolbarPane);
        });
        Players.setOnAction(e -> {
            wbPane.setCenter(playersPane);
            wbPane.setBottom(bottomToolbarPane);
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
        fAdd.setOnAction(e -> {
            filecontroller.handleAddTeamRequest(this);
        });
        pAdd.setOnAction(e -> {
            filecontroller.handleAddNewPlayerRequest(this);
            
        });
        pRemove.setOnAction(e -> {
           play.remove(playersTable.getSelectionModel().getSelectedItem());
          
        });
        playersTable.setOnMouseClicked(e -> {
           if(e.getClickCount() > 1){
               filecontroller.handleEditPlayerRequest(this);
           }
        });
        fRemove.setOnAction(e -> {
          String n = (String)selectTeams.getValue();
          fTeamNames.remove(n);
          for(int i = 0; i < fantasyTeams.size(); i++){
              if(fantasyTeams.get(i).name.equals(n)){
                  fantasyTeams.remove(i);
                  break;
              }
          }
          
        });
        fEdit.setOnAction(e -> {
            filecontroller.handleEditTeamRequest(this);
          
        });
        
    };
    
    public void initFantasyTeamsScreen(){

        fantasyTeamsPane = new BorderPane();
        fantasyTeamsText = new Text("Fantasy Teams");
        fantasyTeamsText.setFont(Font.font("Verdana", 30));

        
        teamsTable = new TableView();
        teamsTable.setEditable(true);       
        
        //INITIALIZING TABLE COLUMNS
        TableColumn posCol = new TableColumn("Position");
        posCol.setEditable(false); 
        
        TableColumn firstNameCol = new TableColumn("First");
        firstNameCol.setEditable(false);
        
        TableColumn lastNameCol = new TableColumn("Last");
        lastNameCol.setEditable(false);
        
        TableColumn proTeamCol = new TableColumn("Pro Team");
        proTeamCol.setEditable(false);
        
        TableColumn positionsCol = new TableColumn("Positions");
        positionsCol.setEditable(false);
        
        TableColumn rwCol = new TableColumn("R/W");
        rwCol.setEditable(false);
        
        TableColumn hrsvCol = new TableColumn("HR/SV");
        hrsvCol.setEditable(false);
        
        TableColumn rbikCol = new TableColumn("RB/IK");
        rbikCol.setEditable(false);
        
        TableColumn sberaCol = new TableColumn("SB/ERA");
        sberaCol.setEditable(false);
        
        TableColumn bawhipCol = new TableColumn("BA/WHIP");
        bawhipCol.setEditable(false);
        
        TableColumn estCol = new TableColumn("Estimated...");
        estCol.setEditable(false);
        
        TableColumn contractCol = new TableColumn("Contract");
        contractCol.setEditable(false);
        
        TableColumn salCol = new TableColumn("Salary");
        salCol.setEditable(false);
       
        

        teamsTable.getColumns().addAll(posCol, firstNameCol, lastNameCol, proTeamCol, positionsCol, rwCol, hrsvCol, rbikCol, sberaCol, bawhipCol, estCol, salCol);
        
        //ADDING TABLE
        fantasyTeamsPane.setCenter(teamsTable);
        
        //CREATING COMPONENTS FOR TOP OF FANTASYTEAMSSCREEN
        fVbox = new VBox();
        fInnerBox = new HBox();
        Label draftName = new Label("Draft Name:");
        draftName.setFont(Font.font("Verdana", 15));
        
        Label select = new Label("Select Fantasy Team");
        select.setFont(Font.font("Verdana", 15));
        
        selectTeams = new ComboBox();
        selectTeams.setItems(fTeamNames);
        
        TextField text = new TextField();
        
        fAdd = new Button();
        fRemove = new Button();
        fEdit = new Button();
        
        
        fAdd.setGraphic(new ImageView(new Image("file:./images/Add.png"))); 
        fRemove.setGraphic(new ImageView(new Image("file:./images/DeleteScheduleItem.png"))); 
        fEdit.setGraphic(new ImageView(new Image("file:./images/EditScheduleItem.png"))); 
        
        Tooltip addTip = new Tooltip("Add");
        Tooltip removeTip = new Tooltip("Remove");
        Tooltip editTip = new Tooltip("Edit Player");
        
        fAdd.setTooltip(addTip);
        fRemove.setTooltip(removeTip);
        fEdit.setTooltip(editTip);
        
        fInnerBox.getChildren().addAll(fAdd, fRemove, fEdit, draftName, text, select, selectTeams);

        
        fVbox.getChildren().addAll(fantasyTeamsText, fInnerBox);
        
        
        fantasyTeamsPane.setTop(fVbox);
        
        //ADDING IT ALL TO THE SCREEN
        //wbPane.setCenter(fantasyTeamsPane);
        //wbPane.setBottom(bottomToolbarPane);
        
         
      
    }
    
    public void initPlayersScreen(){

        playersPane = new BorderPane();
        playersText = new Text("Available Players");
        playersText.setFont(Font.font("Verdana", 30));
        
        innerPlayersPane = new BorderPane();
        
        playersTable = new TableView<Player>();
        playersTable.setEditable(true);       
       
        TableColumn firstNameCol = new TableColumn("First");
        firstNameCol.setEditable(false);
        
        TableColumn lastNameCol = new TableColumn("Last");
        lastNameCol.setEditable(false);
        
        TableColumn teamCol = new TableColumn("Pro Team");
        teamCol.setEditable(false);
        
        TableColumn positionsCol = new TableColumn("Positions");
        positionsCol.setEditable(false);
        
        TableColumn birthCol = new TableColumn("Year of Birth");
        birthCol.setEditable(false);
        
        TableColumn rwCol = new TableColumn("R/W");
        rwCol.setEditable(false);
        
        TableColumn hrsvCol = new TableColumn("HR/SV");
        hrsvCol.setEditable(false);
        
        TableColumn rbikCol = new TableColumn("RBI/K");
        rbikCol.setEditable(false);
        
        TableColumn sberaCol = new TableColumn("SB/ERA");
        sberaCol.setEditable(false);
        
        TableColumn bawhipCol = new TableColumn("BA/WHIP");
        bawhipCol.setEditable(false);
        
        TableColumn estValCol = new TableColumn("Estimated Value");
        estValCol.setEditable(false);
        
        TableColumn notesCol = new TableColumn("Notes");
        notesCol.setEditable(true);
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        notesCol.setCellValueFactory(new PropertyValueFactory<Player, String>("notes"));
        teamCol.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));
        birthCol.setCellValueFactory(new PropertyValueFactory<Player, String>("birthYear"));
        
        playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, rwCol, hrsvCol, sberaCol, bawhipCol, estValCol, notesCol);
        
        
        
        playersTable.setItems(play);
        
        pBox = new HBox();
        
        ToggleGroup group = new ToggleGroup();
        
        all = new RadioButton("All  ");
        all.setToggleGroup(group);
        all.setSelected(true);
        
        c = new RadioButton("C  ");
        c.setToggleGroup(group);
        
        b1 = new RadioButton("1B  ");
        b1.setToggleGroup(group);
        
        cl = new RadioButton("Cl  ");
        cl.setToggleGroup(group);
        
        b3 = new RadioButton("3B  ");
        b3.setToggleGroup(group);
        
        b2 = new RadioButton("2B  ");
        b2.setToggleGroup(group);
        
        mi = new RadioButton("MI  ");
        mi.setToggleGroup(group);
        
        ss = new RadioButton("SS  ");
        ss.setToggleGroup(group);
        
        of = new RadioButton("OF  ");
        of.setToggleGroup(group);
        
        u = new RadioButton("U  ");
        u.setToggleGroup(group);
        
        p = new RadioButton("P  ");
        p.setToggleGroup(group);

        pBox.getChildren().addAll(all, c, b1, cl, b3, b2, mi, ss, of, u, p);
        
        pBox.setStyle("-fx-background-color: #336699;");
        
        VBox vbox = new VBox();
        HBox innerBox = new HBox();
        Label search = new Label("        Search:");
        search.setFont(Font.font("Verdana", 15));
        
        TextField text = new TextField();
        
        pAdd = new Button();
        pRemove = new Button();
        
        pAdd.setGraphic(new ImageView(new Image("file:./images/Add.png"))); 
        pRemove.setGraphic(new ImageView(new Image("file:./images/DeleteScheduleItem.png"))); 
        
        Tooltip addTip = new Tooltip("Add");
        Tooltip removeTip = new Tooltip("Remove");
        
        pAdd.setTooltip(addTip);
        pRemove.setTooltip(removeTip);
        
        innerBox.getChildren().addAll(pAdd, pRemove, search, text);
        
        ex = new GridPane();
        ex.setStyle("-fx-background-color: #FF0000;");
       
        
        vbox.getChildren().addAll(playersText, innerBox, pBox);
        
        ex.getChildren().addAll(vbox);
        
        innerPlayersPane.setTop(vbox);
        innerPlayersPane.setCenter(playersTable);
       
        playersPane.setCenter(innerPlayersPane);

        //wbPane.setCenter(playersPane);
        //wbPane.setBottom(bottomToolbarPane);
        
      
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
       newDraftButton.setGraphic(new ImageView(new Image("file:./images/New.png")));
       loadDraftButton = initChildButton(topToolbarPane, PropertyType.LOAD_DRAFT_ICON, "Load Draft", false);
       loadDraftButton.setGraphic(new ImageView(new Image("file:./images/Load.png")));
       saveDraftButton = initChildButton(topToolbarPane, PropertyType.SAVE_DRAFT_ICON, "Save Draft", true);
       saveDraftButton.setGraphic(new ImageView(new Image("file:./images/Save.png")));
       exportButton = initChildButton(topToolbarPane, PropertyType.EXPORT_ICON, "Export Draft", true);
       exportButton.setGraphic(new ImageView(new Image("file:./images/Export.png")));
       exitButton = initChildButton(topToolbarPane, PropertyType.EXIT_ICON, "Exit", false);
       exitButton.setGraphic(new ImageView(new Image("file:./images/Exit.png"))); 
    };
    
    public void initBottomToolbar(){
       bottomToolbarPane = new FlowPane();
      
       // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
       // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
       FantasyTeams = initChildButton(bottomToolbarPane, PropertyType.FANTASY_TEAMS_ICON, "Fantasy Teams Screen", false);
       FantasyTeams.setGraphic(new ImageView(new Image("http://i.imgur.com/lKhcJJ9.png")));
       Players = initChildButton(bottomToolbarPane, PropertyType.PLAYERS_ICON, "Players Screen", false);
       Players.setGraphic(new ImageView(new Image("http://i.imgur.com/7gwUSia.png")));
       FantasyStandings = initChildButton(bottomToolbarPane, PropertyType.FANTASY_STANDINGS_ICON, "Fantasy Standings Screen", false);
       FantasyStandings.setGraphic(new ImageView(new Image("http://i.imgur.com/N8E5XDf.png")));
       Draft = initChildButton(bottomToolbarPane, PropertyType.DRAFT_ICON, "Draft Screen", false);
       Draft.setGraphic(new ImageView(new Image("http://i.imgur.com/1GfRp9a.png")));
       MLBTeams = initChildButton(bottomToolbarPane, PropertyType.MLBTEAMS_ICON, "MLB Teams Screen", false);
       MLBTeams.setGraphic(new ImageView(new Image("http://i.imgur.com/YIZrqJd.png")));
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
    
     public void updateTable(String radioButton){
     /*
    RadioButton all;
    RadioButton c;
    RadioButton b1;
    RadioButton cl;
    RadioButton b3;
    RadioButton b2;
    RadioButton mi;
    RadioButton ss;
    RadioButton of;
    RadioButton u;
    RadioButton p;
        */
         if(radioButton.equals("all")){
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
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("c")){
            TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("b1")){
             TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("cl")){
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
             
         }
         else if(radioButton.equals("b3")){
         
              TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("b2")){
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("mi")){
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("ss")){
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("of")){
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else if(radioButton.equals("u")){
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
         else{
          TableColumn firstNameCol = new TableColumn("First");
            TableColumn lastNameCol = new TableColumn("Last");
            TableColumn teamCol = new TableColumn("Pro Team");
            TableColumn positionsCol = new TableColumn("Positions");
            TableColumn birthCol = new TableColumn("Year of Birth");
             
            TableColumn estValCol = new TableColumn("Estimated Value");
            TableColumn notesCol = new TableColumn("Notes");
            playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, positionsCol, birthCol, estValCol, notesCol);
            
            innerPlayersPane.setCenter(playersTable);
         }
     
     };
  
     
     private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, "Close");
        addFantasyTeamDialog = new AddFantasyTeamDialog(primaryStage, "", this);
        addNewPlayerDialog = new AddNewPlayerDialog(primaryStage, teamNames, this);
        editPlayerDialog = new EditPlayerDialog(primaryStage);
        editTeamDialog = new EditFantasyTeamDialog(primaryStage, this);
    }
    
}
