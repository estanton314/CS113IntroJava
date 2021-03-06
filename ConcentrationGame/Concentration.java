import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.*;
import javafx.scene.layout.*;

//Concentration class
public class Concentration extends Application{

   //initialization
   Button [] buttons;   
   GridPane grid;  
   Image [] images;   
   Image background = new Image("file:card.png"); 
   int history = -1;
   int choice = -1;
   Label title;
   Text instructions;
   Label myLabel;
   Label p1M;
   Label p2M;
   Label p1N;
   Label p2N;
   TextField p1TF;
   TextField p2TF;
   Button reset = new Button();
   ConcModel conc= new ConcModel();
   HBox enterName1 = new HBox();
   HBox enterName2 = new HBox();
   Button enter;
   Button enter1;
 
   
   //main function
   public static void main(String[] args){
     
      // Launch the application.
      launch(args);
   }//main function
   
   
   //start function
   @Override
   public void start(Stage stage){
      
      //bird images   
      images = new Image[8];
      for (int i=0;i<8;i++)
         images[i] = new Image("file:bird"+(i+1)+".jpg");
   
      //COMPONENTS
   
      //card buttons
      buttons = new Button[16];
      for(int i=0;i<16;i++)
         buttons[i] = makeButton(background);
         
      //title   
      Label title = new Label("Concentration Game");
      title.setFont (new Font("Futura Bold", 26)); //top center
      title.setTextFill(Color.web("#0076a3"));
      title.setTextAlignment(TextAlignment.CENTER);
   
      //instructions
      Text instructions = new Text("Click a pair of cards to flip them. The goal is to pick " +
               "two with matching images underneath, so concentrate and remember what you see. Your turn " +
               "ends when you fail to pick a matching pair. Try to pick more pairs than your opponent. Good luck!");
      instructions.setWrappingWidth(800);
      instructions.setFont(new Font("Futura Medium", 16));
      instructions.setTextAlignment(TextAlignment.CENTER); //middle
   
      //myLabel
      myLabel = new Label("Player 1 goes first!");
      myLabel.setFont (new Font("Futura Bold", 20)); //button at some point
      myLabel.setTextAlignment(TextAlignment.CENTER);
      myLabel.setTextFill(Color.web("#ff1a75"));
           
           
      //*player 1 info*
      
      //player 1 name label
      p1N = new Label("Enter Player 1 name: ");
      p1N.setFont (new Font("Futura Bold", 14));
      p1N.setTextFill(Color.web("#006622"));
      p1N.setStyle("-fx-background-color: #c6ecd9; " );
      
      //////player 1 name input
      p1TF = new TextField (" ");
      enter = new Button("Enter");
      enter.setStyle("-fx-base: #32914c;");
      enter.setOnAction(new enters());
      
     //player 1 matches amount
      p1M = new Label ("Player 1 matches: 0");
      p1M.setFont (new Font("Futura Bold", 14));
      p1M.setTextFill(Color.web("#006622"));
      p1M.setStyle("-fx-background-color: #c6ecd9; " );
       
   
      //*player 2 info*
      
      //player 1 name label
      p2N = new Label ("Enter Player 2 name: ");
      p2N.setFont (new Font("Futura Bold", 14));
      p2N.setTextFill(Color.web("#5900b3"));
      p2N.setStyle("-fx-background-color: #e0b3ff; " );
      
      ////player 2 name input
      p2TF = new TextField ();
      enter1 = new Button("Enter");
      enter1.setStyle("-fx-base: #b895e2;");
      enter1.setOnAction(new enters());
      
      //player 2 matches amount
      p2M = new Label ("Player 2 matches: 0");
      p2M.setFont (new Font("Futura Bold", 14));
      p2M.setTextFill(Color.web("#5900b3"));
      p2M.setStyle("-fx-background-color: #e0b3ff; " );
      
      
      //reset button
      reset = new Button("RESET");
      reset.setOnAction(new ResetHandler());
      reset.setPadding(new Insets(20));
      reset.setStyle("-fx-base: #a5352b;");
  
      
      ///// spacing fixes
      Rectangle r = new Rectangle();
      r.setFill(Color.TRANSPARENT);
      r.setHeight(60);  
      
      Rectangle r1 = new Rectangle();
      r1.setFill(Color.TRANSPARENT);
      r1.setHeight(60); 
      
      Rectangle r2 = new Rectangle();
      r2.setFill(Color.TRANSPARENT);
      r2.setHeight(100); 
      
      Rectangle r3 = new Rectangle();
      r3.setFill(Color.TRANSPARENT);
      r3.setHeight(30);
      r3.setWidth(70); 
   
      Rectangle r4 = new Rectangle();
      r4.setFill(Color.TRANSPARENT);
      r4.setHeight(20);
      r4.setWidth(80);
      
      Rectangle r5 = new Rectangle();
      r5.setFill(Color.TRANSPARENT);
      r5.setWidth(80);
      ////end
      
      
      //LAYOUT
      
      //Put the Button components in a new GridPane.
      grid = new GridPane();
      grid.setVgap(10); 
           
      for(int i =0;i<16;i++){
         grid.add(buttons[i],i%4,i/4);
         buttons[i].setOnAction(new ButtonClickHandler());}
      
      //player info
      enterName1.getChildren().addAll(p1N, p1TF, enter);
      enterName2.getChildren().addAll(p2N, p2TF, enter1);
      
      //right side panel: player info + reset button
      VBox players = new VBox(30, enterName1, p1M, r, enterName2, p2M, r2, reset);
      players.setPadding(new Insets(30));
      players.setAlignment(Pos.CENTER_LEFT);
       
      //puts the grid and the right side together
      HBox hbox = new HBox(60,r1, grid, players); 
   
      //main screen
      VBox vbox = new VBox(title, instructions,r3, hbox, r4, myLabel); 
      vbox.setAlignment(Pos.CENTER);
        
      // Create a Scene with the vbox as its root node.
      Scene scene = new Scene(vbox,1100,850);      
     
      // Add the Scene to the Stage.
      stage.setScene(scene);    
     
      // Set the stage title.
      stage.setTitle("Concentration Game");
     
      // Show the window.
      stage.show();
      
   }//start function
  
  
   //makeButton function (takes in image, returns button) 
   Button makeButton(Image img){
      ImageView iView = new ImageView(img);
      iView.setFitWidth(100); 
      iView.setFitHeight(130);
      Button newButton =  new Button("",iView);
      return(newButton); 
   }//makeButton function


   //ButtonClickHandler class
   class ButtonClickHandler implements EventHandler<ActionEvent>{
   
      //handle function
      @Override
      public void handle(ActionEvent event){
         
         //first button click
         if (history == -1){
            for(int i=0; i < 16; i++){
               if(event.getSource().equals(buttons[i])){
                  history = i;}}
             Image h = images[conc.getPic(history)];
             grid.add(makeButton(h), history % 4, history / 4);     
             myLabel.setText("Now pick a second one...");}
         
         //second button click
         else if (choice==-1){
            for(int i=0; i < 16; i++){
               if(event.getSource().equals(buttons[i])){
                  choice = i;}}
            
            //if second click was same button as first click, 
               //second click is invalid, program waits for another
            if (choice==history)
            choice=-1;
            
            //if second click was valid
            else{
               
            //whose turn is it
            String Player = conc.getPlayer();
               
            //make two buttons with hidden images to display
           // Image h = images[conc.getPic(history)];
            Image c = images[conc.getPic(choice)];
           // grid.add(makeButton(h), history % 4, history / 4);
            grid.add(makeButton(c), choice % 4, choice / 4);
               
            //takeTurn
            conc.takeTurn(history, choice);
               
            //report of progress
            String winner = conc.reportWinner();
            if(winner.equals("none")){
               if (conc.turnOver){        
                  myLabel.setText("No match. Click the Reset button. " + Player + ", your turn is over. Next player, pick a card!");}
               else{
                  myLabel.setText("Yay, " + Player + " got a match! You get to go again.");
                  buttons[history].setOnAction(null);
                  buttons[choice].setOnAction(null);
                  history = -1;
                  choice = -1;}}
            else
               if(winner.equals("tie"))
                  myLabel.setText(Player + " got a match! Game over! Tie!");     
               else
                  myLabel.setText(Player + " got a match! Game over! The winner : Player " + winner);
            
            //update each player's # of matches   
            p1M.setText("Player 1 matches: " + conc.getOne());
            p2M.setText("Player 2 matches: " + conc.getTwo());
         
         }//valid second click (else)
         
         }//any second click (else if)
            
      }//handle function
   }//ButtonClickHandler class


   //ResetHandler class
   class ResetHandler implements EventHandler<ActionEvent>{
      
      //handle function
      @Override
      public void handle(ActionEvent event){
      
         //makes two background buttons
         Button one = makeButton(background);
         one.setOnAction(new ButtonClickHandler());
         Button two = makeButton(background);
         two.setOnAction(new ButtonClickHandler());
         
         //flips history and choice buttons back over
         buttons[history] = one;
         buttons[choice] = two;
         grid.add(one,history%4,history/4);
         grid.add(two,choice%4,choice/4);
         
         history = -1;
         choice = -1;
      }//handle function  
   }//ResetHandler class
   
   
   //enters class
   class enters implements EventHandler<ActionEvent>{
      
      //handle function
      @Override
      public void handle(ActionEvent event){
         
         if (event.getSource().equals(enter)){
         
         //updates name of player 1
         String name = p1TF.getText();   
         p1N.setText("Player 1: " + name);
         enterName1.getChildren().clear();
         enterName1.getChildren().add(p1N);}
         
         else if (event.getSource().equals(enter1)){
         
         //updates name of player 2
         String name = p2TF.getText();   
         p2N.setText("Player 2: " + name);
         enterName2.getChildren().clear();
         enterName2.getChildren().add(p2N);
         }
      
      }//handle function
   }//enters class
   

}//Concentration class