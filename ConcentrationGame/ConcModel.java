//some code adapted from Nanette
//code by Ellen

import java.util.*;
import java.io.*;

public class ConcModel extends GameModel{
   
   //initializes variables
   private String[] shown = new String[16];
   private int[] hidden = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
   public final int NSQUARES = 16;
   public final int SIZE = 4;
   public int numAttempts;
   public boolean turnOver;
   public int oneMatches;
   public int twoMatches;
   
   //constructor
   ConcModel(){
      resetGame();
   }//constructor ConcModel
   
   //resets variables for beginning of game
   public void resetGame(){
      turnOver = false;
      numAttempts = 0;
      oneMatches = 0;
      twoMatches = 0;
      
      for(int i=0;i<NSQUARES;i++)
         shown[i] = "N";
  
      hidden = makeHidden();
      
   }//function resetGame()
   
   //checks for a match, updates oneMatches/twoMatches and turnOver
   public void takeTurn(int b1, int b2){
      if (hidden[b1] == hidden[b2]){
         shown[b1] = "Y";
         shown[b2] = "Y";
         if(getPlayer().equals("Player 1"))
            oneMatches++;
         else
            twoMatches++;
            turnOver = false;}         
      else{
         turnOver = true;
         numAttempts++;}
   }//function takeTurn
   
   //returns 0-7 corresponding to index of an image
   public int getPic(int i){
      return(hidden[i]);
   }//function getPic
   
   //makes and randomizes random list containing 0-7 twice
   private int [] makeHidden(){
    
      Random rand = new Random();
      
      for(int i=0;i<8;i++){
         boolean kgOne = true;
         boolean kgTwo = true;
         while (kgOne){
            int num = rand.nextInt(16);
            if (hidden[num] == -1){
               hidden[num] = i;
               kgOne = false;}}
         while (kgTwo){
            int num = rand.nextInt(16);
            if (hidden[num] == -1){
               hidden[num] = i;
               kgTwo = false;}}}
      return(hidden);
   }//function makeHidden
   
   //returns which player's turn it is
   public String getPlayer(){
      if(numAttempts%2 == 0)
         return("Player 1");
      else
         return("Player 2");
   }//function getPlayer
   
   //returns boolean for whether a turn is over
   public boolean isTurnEnding() {
      return(turnOver);
   }//function isTurnEnding()
   
   //returns boolean for whether game is over
   public boolean gameOverStatus(){
      if (oneMatches + twoMatches == 8)
         return(true);
      else
         return(false);
   }//function gameOverStatus
   
   //returns whether an image at a spot on the grid is shown or not
   public String get(int r, int c){
      return(shown[r*4+c]);
   }//function get
   
   //returns number of rows
   public int getRows(){
      return(SIZE);
   }//function getRows
   
   //returns number of columns
   public int getCols(){
      return(SIZE);
   }//function getCols
   
   //unused, overrides abstract GameModel function
   public void display(){
   }//function display  
   
   //returns player one's matches
   public int getOne(){
      return(oneMatches);
   }//function getOne
   
   //returns player two's matches
   public int getTwo(){
      return(twoMatches);
   }//function getTwo
   
   //returns who won, or that it's a tie, or that game isn't finished
   public String reportWinner(){
      if (gameOverStatus()){
         if (oneMatches > twoMatches)
            return("1");
         else if (twoMatches > oneMatches)
            return("2");
         else
            return("tie");}
      else
         return("none");
   }//function reportWinner
   
}//class ConcModel