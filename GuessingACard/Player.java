class Player{
   
   //attributes
   private String name;
   private int gamesWon;
   private Card guess;
   
   //constructor
   Player(String n){
      name = n;
      gamesWon = 0;
      guess = null;
   }
   
   //toString
   public String toString(){
      return("Name: " + name + "\nGames won: " + gamesWon);
   }
   
   //setCard
   public void setCard(Card c){
      guess = c;
   }
   
   //getName
   public String getName(){
      return(name);
   }
   
   //getCard
   public Card getCard(){
      return(guess);
   }
   
   //getGamesWon
   public int getGamesWon(){
      return(gamesWon);
   }
   
   //compareTo
   public double compareTo(Card other){
   
      //comparing Player's Card guess to another Card
      double num = this.guess.compareTo(other);
      
      //adds to gamesWon if Cards are the same
      if (num == 0)
         gamesWon += 1;
      return(num);
   }
   
}