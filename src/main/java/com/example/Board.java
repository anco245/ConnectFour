package com.example;

import java.util.ArrayList;

public class Board {

  int wide;
  int deep;

  boolean winner = false;

  String[][] board;

  ArrayList<Integer> lastPos = new ArrayList<>();

  public Board(int wide, int deep)
  {
    this.wide = wide;
    this.deep = deep;

    board = new String[wide][deep];

    for(int i = 0; i < deep; i++)
    {
      for(int j = 0; j < wide; j++)
      {
        board[j][i] = "-";
      }
    }
  }

  // example of comment

  private boolean horizontal(int x, int y)
  {
    int count = 0;

    for(int i = 0; i < wide; i++)
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

    for(int i = 0; i < deep; i++)
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
    if(x+y < deep-1)
    {
      starty = x+y;
      startx = 0;

      endx = wide-1;
      //endy = (startx + starty) - x;
    } else {
      starty = deep-1;
      startx = (x+y) - (deep-1);

      endx = wide-1;
      endy = (startx + starty) - endx;
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

      endx = wide - 1;
      endy = (wide-1)-startx;
    } else if (x < y) {
      startx = 0;
      starty = y-x;

      endx = (deep-1) - starty;
      endy = deep - 1;
    } else {
      startx = 0;
      starty = 0;

      endx = wide - 1;
      endy = deep - 1;
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
      starty--;
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
    int alteredDeep = deep - 1;

    /*
     * In connect four, to place a piece, a piece is dropped
     * from the top of the board. The piece then settles where ever there's
     * the first empty space in that column
     */
    while(!board[col-1][alteredDeep].equals("-"))
    {
      alteredDeep--;
    }

    board[col-1][alteredDeep] = player;

    lastPos.clear();
    lastPos.add(col-1);
    lastPos.add(alteredDeep);
  }

  public void printBoard()
  {
    //println statements at the beginning and end are just for aesthetics
    System.out.println();

    for(int i = 0; i < deep; i++)
    {
      for(int j = 0; j < wide; j++)
      {
        if(j == wide - 1)
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
