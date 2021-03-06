//MINESWEEPER: MSModel
//code by Eliot Stanton
//(some code adapted from Nanette)
//May 2018

//imports
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

//MSModel class
public class MSModel extends GameModel{

   //initialization
   public int sideSize;
   private int[] hidden;
   private int[] shown;
   private int totalBombs;
   private int bombsFound;
   private int bombsMarked;
   private boolean bomb;

   //constructor
   MSModel(int s){
      resetGame(s);
   }//MSModel

   //resetGame
   public void resetGame(int s){

      //set attributes
      sideSize = s;
      bomb = false;
      bombsFound = 0;
      bombsMarked = 0;
      totalBombs = s;

      //set shown all to base (impossible) value 9
      shown = new int[sideSize*sideSize];
      for(int i=0;i<sideSize*sideSize;i++){
         shown[i] = 9;}

      //initialize hidden
      hidden = makeHidden();

      }//resetGame

   //makeHidden
   public int[] makeHidden(){

      hidden = new int[sideSize*sideSize];

      Random rand = new Random();
      int count = 0;

      //add bombs randomly (-1 symbolizes a bomb)
      while(count<totalBombs){
         int num = rand.nextInt(sideSize*sideSize);
         if (hidden[num] != -1){
            hidden[num] = -1;
            count++;}}

      //calculates bomb neighbors for each space
      calcNeighbors();

      return(hidden);
   }//makeHidden

   //calcNeighbors
   public void calcNeighbors(){

      //loops once for each space
      for(int i=0;i<hidden.length;i++){

         //only does anything if a space has a bomb
         if (hidden[i] == -1){

            //checks if hidden[-] != -1 many times
               //to avoid incrementing other bombs

            if (i%sideSize != sideSize-1){ //not on right
               if (hidden[i+1] != -1)
                  hidden[i+1] += 1; //R
               if (i/sideSize != 0){ //not on top
                  if (hidden[i-sideSize] != -1)
                     hidden[i-sideSize] +=1; //T
                  if (hidden[i-sideSize+1] != -1)
                     hidden[i-sideSize+1] +=1;} //TR
               if (i/sideSize != sideSize-1){ //not on bottom
                  if (hidden[i+sideSize] != -1)
                     hidden[i+sideSize] +=1; //B
                  if (hidden[i+sideSize+1] != -1)
                     hidden[i+sideSize+1] +=1;} //BR
            }
            else{ //on right
               if (i/sideSize != 0){ //not on top
                  if (hidden[i-sideSize] != -1)
                     hidden[i-sideSize] +=1;} //T
               if (i/sideSize != sideSize-1){//not on bottom
                  if (hidden[i+sideSize] != -1)
                     hidden[i+sideSize] +=1;} //B
            }
            if (i%sideSize != 0){ //not on left
               if (hidden[i-1] != -1)
                  hidden[i-1] +=1; //L
               if (i/sideSize != 0){ //not on top
                  if (hidden[i-sideSize-1] != -1)
                     hidden[i-sideSize-1] +=1;} //TL
               if (i/sideSize != sideSize-1){ //not on bottom
                  if (hidden[i+sideSize-1] != -1)
                     hidden[i+sideSize-1] +=1;} //BL
            }

            }//if i is a bomb
         }//for loop

   }//calcNeighbors

   //takeTurn
   public void takeTurn(int a, int b){

      //if left click- reveal
      if (b == 0){

         //revealing a mark
         if (shown[a] == -2){
            bombsMarked --;}

         //display what's in hidden
         shown[a] = hidden[a];

         //if revealed a bomb, show all the bombs
         if (hidden[a] == -1){
            bomb = true;
            for(int i=0;i<hidden.length;i++){
               if (hidden[i] == -1)
                  shown[i] = hidden[i];}}}

      //right click- mark
      else if (b==1){

         //space that's not already marked
         if (shown[a] != -2){

            //(-2 symbolizes a mark)
            shown[a] = -2;
            bombsMarked++;

            //marked bomb correctly
            if (hidden[a] == -1)
               bombsFound++;}}

   }//takeTurn

   //gameOverStatus
   public boolean gameOverStatus(){

      //bomb was clicked
      if (bomb)
         return(true);

      //found all the bombs with right number of marks
      else if (bombsFound == totalBombs && bombsMarked == totalBombs)
         return(true);

      else
         return(false);
      }//gameOverStatus

   //get
   public int get(int r, int c){
      return(r*sideSize+c);
   }//get

   //getShown
   public int getShown(int p){
      return(shown[p]);
   }//getShown

   //getRows
   public int getRows(){
      return(sideSize);
   }//getRows

   //getCols
   public int getCols(){
      return(sideSize);
   }//getCols

   //display
      // to debug
      //can change hidden to shown
   public void display(){
      for(int i=0;i<hidden.length;i++){
         if(i%sideSize== 0)
            System.out.println();
         System.out.print(hidden[i]+" ");}
   }//display

   //getTotalBombs
   public int getTotalBombs(){
      return(totalBombs);
   }//getTotalBombs

   //getMarkedBombs
   public int getMarkedBombs(){
      return(bombsMarked);
   }//getMarkedBombs

   //reportWinner
   public String reportWinner(){

      //game over
      if (gameOverStatus()){

         //lost
         if (bomb)
            return("L");

         //won
         else
            return("W");
      }

      //game not over
      else
         return("I");

   }//reportWinner

}//MSModel class
