import java.util.*;

class GuessingGame{

   //***main*************************************************************************************
   public static void main(String argv[]){
      
      //instructions for game
      instructions();

      //makes secret Card object
      Card secret = new Card();
      
      //makes two Player objects
      System.out.println("For the first guesser...");
      Player p1 = getPlayer();
      System.out.println("For the second guesser...");
      Player p2 = getPlayer();
      
      //initializes alias and LCVs
      Player currentP;
      int guessCount=0;
      boolean isWinner = false;
      
      //loop runs until 6 guesses (3 each) are up, or someone's guess matches secret Card
      while (guessCount<6 && !isWinner){
         
         //decides whose turn it is this time through the loop, assigns alias
         System.out.println();
         if (guessCount%2==0){
            currentP = p1;
            System.out.println(p1.getName() + "'s turn.");}
         else{
            currentP = p2;
            System.out.println(p2.getName() + "'s turn.");}
         
         //gets current Player's guess
         currentP.setCard(getGuessCard());
         
         //compares guess to secret card, tells how many turns current player has left
         if (currentP.compareTo(secret) == 0){
            System.out.println("You guessed the card correctly- you win!");
            isWinner = true;}
         else if (currentP.compareTo(secret) >0){
            System.out.println("you guessed a card that is too high");
            System.out.println(currentP.getName() + "'s turns left: " + (2-(guessCount/2)));}
         else{
            System.out.println("you guessed a card that is too low");
            System.out.println(currentP.getName() + "'s turns left: " + (2-(guessCount/2)));}
         
         //increments guessCount
         guessCount += 1;
       
      }//while loop
      
      //reports secret card and if either Player won the game
      System.out.println("\nThe secret card was " + secret);
      System.out.println("\n*Score Report*");
      System.out.println(p1.getName() + ": " + p1.getGamesWon() + ", " + p2.getName() + ": " + p2.getGamesWon());
      
   }//***main***
   
   //***instructions()*****************************************************************************
   public static void instructions(){
      System.out.println("This is a card guessing game. Two players will take turns guessing a " +
                          "\ncard from a standard 52-card deck (options and format for guessing " +
                          "\nthe rank and suit of a card will be provided). You can decide who " +
                          "\nwill get to guess first. You'll take turns guessing a card and " +
                          "\nlearning if your card is too high, too low, or just right. Clubs is " +
                          "\nthe lowest suit, then Diamonds, Hearts, and Spades. Within a suit, " +
                          "\nthe lowest rank is 2 and ace is the highest. You'll each have 3 " +
                          "\nturns to guess the secret card. Good luck!\n");
   }//***instructions()***
   
   //***getGuessCard()****************************************************************************
   public static Card getGuessCard(){
      Scanner input = new Scanner(System.in);
      
      //instructions for guess
      System.out.println("Guess a rank and suit in the format \"Rank of Suit\"");
      System.out.println("Ranks- 2,3,4,5,6,7,8,9,10,J,Q,K,A");
      System.out.println("Suits- C,D,H,S");
      
      //format guess
      String together = input.nextLine();
      String [] apart = together.split(" of ");
      
      //makes Card object
      return(new Card(apart[0],apart[1]));
   }//***getGuessCard()***
   
   //***getPlayer()****************************************************************************
   public static Player getPlayer(){
      Scanner input = new Scanner(System.in);
      
      //inputs for Player
      System.out.println("What's your name?");
      String n = input.next();
      
      //makes Player object
      return(new Player(n));
   }//***getPlayer()***
   
}//class GuessingGame