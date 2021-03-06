//code by Eliot Stanton

import java.util.*;

class MSDriver{
   public static void main(String argv[]){

      //stuff
      Scanner input = new Scanner(System.in);
      MSModel ms = new MSModel();

      //give instructions
      System.out.println("Let's play Minesweeper!");
      System.out.println("There are " + ms.getTotalBombs() + " bombs in the grid. \n " +
                         "Don't click a bomb! Use the numbers showing how many bombs a square \n" +
                         "has as its neighbors to decide which spots are safe to show. \n" +
                         "Mark all bombs and you win!");
      System.out.println("Choose a position 1-16 and what you'd like to do there, \n" +
                        "reveal the space (0) or mark it as a bomb (1).");


      ms.display();

      do{

      System.out.println("\nPosition? 1-16");
      int p = (input.nextInt())-1;

      System.out.println("Reveal or mark? 0/1");
      int c = input.nextInt();

      ms.takeTurn(p,c);
      ms.display();


      }while(!ms.gameOverStatus());//do-while

      //respond if won or lost
      String result = ms.reportWinner();
      if (result.equals("W"))
         System.out.println("You won!");
      else if (result.equals("L"))
         System.out.println("You lose.");


   }//main()
}//MSDriver class
