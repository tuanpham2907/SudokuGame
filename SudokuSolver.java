import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 
 * Soduku consists of a 9x9 grid, broken down into a 3x3 set of smaller
 * 3x3 grids, with each individual cell of the grid to eventually
 * contain a digit between 1 and 9. 
 * 
 * This Program automatically solve Sodoku puzzles. 
 * Prompt the user to enter a file name
 * Open that file and read in the Sudoku puzzle
 * Display the initial problem you have read in.
 * Display the final solution
 * 
 * 
 * @author Tuan Pham
 */

public class SudokuSolver
{
  public static void main(String[] args) throws Exception
  {
    System.out.println("Welcome to the Game of Sudoku");
    System.out.println("*****************************");
    try (Scanner keyboard = new Scanner(System.in)) {
      System.out.println("Please enter a valid filename: ");
      String filename = keyboard.nextLine();
      
      File file = new File(filename);
      System.out.println("The initial problem: ");
      BufferedReader in = new BufferedReader(new FileReader(file));
      String fileOutput;
      while((fileOutput = in.readLine()) != null)
      {
          System.out.println(fileOutput);
      }
      in.close();
      try (Scanner fileScanner = new Scanner(file)) {
        System.out.println("*****************************");
        System.out.println("*****************************");
        System.out.println("The final Solution:");

            // Create a 2d array called board to store the puzzle
            int[][] board = new int[9][9]; 
        
String line = fileScanner.nextLine();
 line = line.replace(" ", "");
            //Prase all digits to board array looping through rows and cols
for (int x = 0; x < 9; x++)
{
            for (int y = 0; y < 9; y++)
            {
              board[x][y] = Character.getNumericValue(line.charAt(y));
              if (y == 8 && fileScanner.hasNextLine())
            {
              line = fileScanner.nextLine();
              line = line.replace(" ", "");
            }
            } 
            } 

            // Use a recursive funtion to solve to puzzle
            solve(board, 0, 0);
      }
    }

  } 

  // A recursive function to go thourgh every cell in the array
  // It replaces 0 as a new number to solve the puzzle
  private static void solve(int[][] board, int cellX, int cellY)
  {
    //The game is finished when y is 9
    if(cellY >= 9)
    {
      printSolution(board);
      System.out.println();
    }
    else
    {
      //Identify the next digit to solve the puzzle
      int nextX = cellX;
      int nextY = cellY;
      if(cellX == 8)
      {
        //Go thourgh the next row and reset the col to 0
        nextX = 0;
        nextY++;
      }
      else
      {
        nextX++;
      }

      //If the number is already existed, we move to the next one
      if(board[cellY][cellX] != 0)
      {
        solve(board, nextX, nextY);
      }
      else
      {
        //check if the number is legal
        //replace that number and move to the next one
        for(int checkNum = 1; checkNum <= 9; checkNum++)
        {
          if(isLegalSquare(board, cellX, cellY, checkNum)
             && isLegalRow(board, cellY, checkNum)
             && isLegalCol(board, cellX, checkNum))
          {
            board[cellY][cellX] = checkNum;
            solve(board, nextX, nextY);
          }
        }
        //there is an incorrect number in the puzzle
        board[cellY][cellX] = 0;
      }
    }
  }

  // a function to check if the number is legal in a square
  private static boolean isLegalSquare(int[][] board, int reqX, int reqY, int checking)
  {
    int rowY;
    int colX;

    //Identify which column of the square
    if(reqX < 3)
    {
      colX = 0;
    }
    else if (reqX < 6)
    {
      colX = 3;
    }
    else
    {
      colX = 6;
    }

    //Identify which row of the square. 
    if(reqY < 3)
    {
      rowY = 0;
    }
    else if (reqY < 6)
    {
      rowY = 3;
    }
    else
    {
      rowY = 6;
    }

    //Check every digit in the square 
    //Return fasle if it has already existed
    for(int y = rowY; y < rowY + 3; y++)
    {
      for(int x = colX; x < colX + 3; x++)
      {
        if(board[y][x] == checking)
          {
            return false;
          }
      }
    }

    return true; // if it is a legal digit

  }
  
   //Checks if a given number is in a given column and returns false if it is.
   private static boolean isLegalCol(int[][] board, int colX, int checking)
   {
     //loops every number in the column
     for(int y = 0; y < 9; y++)
     {
       //Checks if the current digit is the given digit and returns false if so.
       if (checking == board[y][colX])
       {
         return false;
       }
     }
     return true; //if it is a legal digit
   }  
 

  //Checks if a given digit is in a given row and returns false if it is.
  private static boolean isLegalRow(int[][] board, int rowY, int toCheck)
  {
    //loops every number in the row
    for(int x = 0; x < 9; x++)
    {
      //Checks if the given number is the same as the current digit
      //and returns false if so.
      if (toCheck == board[rowY][x])
      {
        return false;
      }
    }
    return true; //if it is a legal digit
  }

 
  //Prints the final solution
  private static void printSolution(int board[][])
  {
    //Loops round each digit and prints it.
    for(int y = 0; y < 9; y++)
	{
	  for(int x = 0; x < 9; x++)
	  {
	    System.out.print(board[y][x] + " ");
	    //Starts a new line when at the end of a row.
        if(x == 8)
		{
		  System.out.println();
		}
	  } 
	} 
  } 
} 