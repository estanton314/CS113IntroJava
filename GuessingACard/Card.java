import java.util.*;

//Card class
class Card{
   
   //attributes
   private String rank;
   private String suit;
   private ArrayList<String> decode = new ArrayList<String>(Arrays.asList("2","3","4","5","6","7","8","9","10","J","Q","K","A"));
   
   //regular constructor
   Card(String r,String s){
     
      //input validation- sets to default 2 or C if invalid input
      if (decode.contains(r))
         rank = r;
      else{
         rank = "2";
         System.out.println("Invalid input for rank. Set to default of 2.");}
      if (s.equals("C")||s.equals("D")||s.equals("H")||s.equals("S"))
         suit = s;
      else{
         suit = "C";
         System.out.println("Invalid input for suit. Set to default of C.");}
   }
   
   //copy constructor
   Card(Card c){
      rank = c.getRank();
      suit = c.getSuit();
   }
   
   //random constructor
   Card(){
      
      //randomly chooses rank
      Random rand = new Random();
      int num = rand.nextInt(13);
      rank = decode.get(num);
      
      //randomly chooses suit 
      String [] suits = {"C","D","H","S"};
      num = rand.nextInt(4);
      suit = suits[num];
   }
   
   //toString
   public String toString(){
      return(rank + " of " + suit);
   }
   
   //getRank
   public String getRank(){
      return(rank);
   }   
   
   //getSuit
   public String getSuit(){
      return(suit);  
   }
   
   //no set functions: user should not be able to change cards
                     //1. changing secret card would ruin the game
                     //2. changing guessed card can only happen through existing 
                          //means to guess again, where guesses are limited
   
   //equals
   public boolean equals(Card c){
      
      //need same suit and rank to be considered equal
      if(this.rank.equals(c.rank)&&this.suit.equals(c.suit))
         return(true);
      else
         return(false);
   }
   
   //compareTo
   public double compareTo(Card c){
      if (this.suit.compareToIgnoreCase(c.suit)==0)
         return(decode.indexOf(this.rank)-decode.indexOf(c.rank));
      else
         return(this.suit.compareToIgnoreCase(c.suit));
      //returns 0 if same card, positive number if this is higher, negative if c is higher
      //all clubs lower than all diamonds lower than all hearts lower than all spades
      //2 is lowest within a suit, ace is highest
   }

}//Card class