package com.example;

import java.util.ArrayList;

public class Board {

  int altWide;
  int altDeep;

  boolean winner = false;

  String[][] board;

  ArrayList<Integer> lastPos = new ArrayList<>();

  public Board(int wide, int deep)
  {
    board = new String[wide][deep];

    for(int i = 0; i < deep; i++)
    {
      for(int j = 0; j < wide; j++)
      {
        board[j][i] = "-";
      }
    }

    this.altDeep = deep - 1;
    this.altWide = wide - 1;
  }

  private boolean horizontal(int x, int y)
  {
    int count = 0;

    for(int i = 0; i < altWide + 1; i++)
    {
      if(count==3)
      {
        return true;
      } else if (board[i][y] == Main.currentPlayer) {
        count++;
      } else if (board[i][y] != Main.currentPlayer) {
        count = 0;
      }
    }

    return false;
  }

  private boolean vertical(int x, int y)
  {
    int count = 0;

    for(int i = 0; i < altDeep + 1; i++)
    {
      if(count==3)
      {
        return true;
      } else if (board[x][i] == Main.currentPlayer) {
        count++;
      } else if (board[x][i] != Main.currentPlayer) {
        count = 0;
      }
    }

    return false;
  }

  private boolean leftDiagonal(int x, int y)
  {
    int startx, endx = 0;
    int starty, endy = 0;

    //for initial starting point for left to right
    if(x+y < altDeep)
    {
      startx = 0;
      starty = x+y;

      if(y <= altWide)
      {
        endx = x+y;
        endy = 0;
      } else {
        endx = altWide;
        endy = (x+y) - altWide;
      }
    } else {
      startx = (x+y) - altDeep;
      starty = altDeep;

      endx = altWide;
      endy = altWide - hold - altDeep;
     }

    int count = 0;

    //check left to right, bottom up

    while(startx <= endx)
    {
      if (count==4) {
        return true;
      } else if (board[startx][starty].equals(Main.currentPlayer)) {
        count++;
      } else if (!board[startx][starty].equals(Main.currentPlayer)) {
        count = 0;
      }

      startx++;
      starty--;
    }

    return count==4;
  }

  private boolean rightDiagonal(int x, int y)
  {
    int startx, starty = 0;
    int endx, endy = 0;
    int count = 0;

    //for initial starting point for right to left
    if(x > y)
    {
      startx = x-y;
      starty = 0;

      endx = altWide;
      endy = altWide - startx;
    } else if (x < y) {
      startx = 0;
      starty = y-x;

      endx = altDeep - starty;
      endy = altDeep;
    } else {
      startx = 0;
      starty = 0;

      endx = altWide;
      endy = altWide;
    }

    while(startx <= endx)
    {
      if (count==4) {
        return true;
      } else if (board[startx][starty].equals(Main.currentPlayer)) {
        count++;
      } else if (!board[startx][starty].equals(Main.currentPlayer)) {
        count = 0;
      }

      startx++;
      starty++;
    }

    return count==4;
  }

  public boolean hasWinner()
  {
    if(Main.turn < 7)
    {
      return false;
    } else {
      if(horizontal(lastPos.get(0), lastPos.get(1)))
      {
        return true;
      } else if (vertical(lastPos.get(0), lastPos.get(1))) {
        return true;
      } else if (leftDiagonal(lastPos.get(0), lastPos.get(1))) {
        return true;
      } else if (rightDiagonal(lastPos.get(0), lastPos.get(1))) {
        return true;
      } else {
        return false;
      }
    }
  }

  public void placePiece(int col, String player)
  {
    //make sure that adding another to column doesn't cause out of bounds error
    int deepPosition = altDeep;

    /*
     * In connect four, to place a piece, a piece is dropped
     * from the top of the board. The piece then settles where ever there's
     * the first empty space in that column
     */
    while(!board[col-1][deepPosition].equals("-"))
    {
      deepPosition--;
    }

    board[col-1][deepPosition] = player;

    lastPos.clear();
    lastPos.add(col-1);
    lastPos.add(deepPosition);
  }

  public void printBoard()
  {
    //println statements at the beginning and end are just for aesthetics
    System.out.println();

    for(int i = 0; i < altdeep + 1; i++)
    {
      for(int j = 0; j < altwide + 1; j++)
      {
        if(j == altWide)
        {
          System.out.print(board[j][i] + "\n");
        } else {
          System.out.print(board[j][i] + " ");
        }
      }
    }

    System.out.println();
  }
}
