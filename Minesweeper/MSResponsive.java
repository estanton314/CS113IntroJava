//MINESWEEPER: MSResponsive
//code by Eliot Stanton
//May 2018

//imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.event.*;
import javafx.scene.image.*;

//MSResponsive class
public class MSResponsive extends Application{

   //initialization
   MSModel ms;
   int s;
   GridPane grid;
   Image [] pics;
   Label title;
   Label bTotal;
   Label bMarked;
   Label next;
   Button [] buttons;
   Button reset;
   RadioButton easy;
   RadioButton medium;
   RadioButton hard;
   HBox hbox;
   VBox right;
   VBox root;
   VBox radio;

   //main
   public static void main(String argv[]){
      launch(argv);
   }//main

   //start
   @Override
   public void start(Stage stage){
      s=4;
      startGame(stage);
      }//start

   //startGame
   public void startGame(Stage stage){

      //MSModel object
      ms = new MSModel(s);

      //COMPONENTS

      //grid
      grid = new GridPane();

      //images for buttons
      pics = new Image[12];
      for(int i=0;i<9;i++)
         pics[i] = new Image("file:"+i+".png");
      pics[9] = new Image("file:bomb.png");
      pics[10] = new Image("file:flag.png");
      pics[11] = new Image("file:blank.png");

      //buttons for grid (initially blank)
      buttons = new Button[s*s];
      for(int i=0;i<s*s;i++){
         addButton(11,i);}

      //reset button (calls restart function)
      reset = new Button("RESET");
      reset.setOnAction(e -> {
         restart(stage);
         });

      //radio buttons
      easy = new RadioButton("easy");
      medium = new RadioButton("medium");
      hard = new RadioButton("hard");

      ToggleGroup radioGroup = new ToggleGroup();
      easy.setToggleGroup(radioGroup);
      medium.setToggleGroup(radioGroup);
      hard.setToggleGroup(radioGroup);

      if (s==4){
            easy.setSelected(true);
         }
         else if (s==6){
            medium.setSelected(true);
         }
         else if (s==8){
            hard.setSelected(true);
         }

      //labels
      title = new Label("MineSweeper");

      bTotal = new Label("Total Bombs: " + ms.getTotalBombs());
      bMarked = new Label("Marked Bombs: 0");

      next = new Label("Left click a space to reveal, right click to mark.");

      //LAYOUT

      radio = new VBox(10,easy,medium,hard);

      right = new VBox(30,bTotal,bMarked,radio,reset);

      hbox = new HBox(200,grid,right);
      hbox.setPadding(new Insets(30));

      root = new VBox(30,title,next,hbox);
      root.setPadding(new Insets(50));

      //scene, added ms.css to scene
      Scene scene = new Scene(root,1000,700);
      scene.getStylesheets().add("ms.css");

      //stage
      stage.setScene(scene);
      stage.setTitle("MineSweeper");
      stage.show();


   }//startGame

   //restart (called by reset button)
   public void restart(Stage stage){

      //adjust size based on radio buttons
      if (easy.isSelected())
            s = 4;
         else if (medium.isSelected())
            s = 6;
         else if (hard.isSelected())
            s = 8;

      //refresh stage
      startGame(stage);

   }//restart

   //ButtonClickLeftRightHandler class
   class ButtonClickLeftRightHandler implements EventHandler<MouseEvent> {

      //handle
      @Override
      public void handle(MouseEvent event) {

         //initialization to impossible values
         int c = 2;
         int p = -1;

         //right click or left click
         MouseButton clickedButton = event.getButton();

         if(clickedButton==MouseButton.PRIMARY){
            c = 0;}
         else if(clickedButton==MouseButton.SECONDARY){
            c = 1;}

         //index of button clicked
         for(int i=0; i <s*s; i++){
            if(event.getSource().equals(buttons[i])){
               p = i;}}

         //turn
         ms.takeTurn(p,c);

         //change buttons based on turn
         if (ms.getShown(p) == -2){
            addButton(10,p);}
         else if (ms.getShown(p) == -1){
            addButton(9,p);
            for(int i=0; i<s*s; i++){
               if (ms.getShown(i) == -1){
                  addButton(9,i);}}}
         else{
            addButton(ms.getShown(p),p);}

         //update labels based on turn

         //progress in game
         if (ms.reportWinner().equals("L")){
            next.setText("Oh no, you clicked a bomb! You lose.");}
         else if (ms.reportWinner().equals("W")){
            next.setText("You correctly marked all " + ms.getTotalBombs() + " bombs! You win.");}
         else if (ms.reportWinner().equals("I")){
            if (ms.getMarkedBombs() == ms.getTotalBombs())
               next.setText("You marked enough spots, but didn't get them all right. Keep trying...");
            else
               next.setText("Click another square...");}

         bMarked.setText("Marked Bombs: " + ms.getMarkedBombs());

         //freeze buttons if game is over
         if (ms.gameOverStatus()){
            for (int i=0; i<s*s;i++)
               buttons[i].setOnMouseClicked(null);}

      }//handle
   }//ButtonClickLeftRightHandler class


   //addButton
   public void addButton(int t, int n){

      //imageview from image index
      ImageView img = new ImageView(pics[t]);
      img.setFitWidth(200.0/s);
      img.setFitHeight(260.0/s);

      //new button
      Button temp = new Button("",img);

      //add button to list
      buttons[n] = temp;

      //connect to handler
      temp.setOnMouseClicked(new ButtonClickLeftRightHandler());

      //add to grid
      grid.add(temp,n%s,n/s);

   }//addButton

} //MSResponsive class
