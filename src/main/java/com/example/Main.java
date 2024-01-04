package com.example;

import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    static Board b;
    static String player1;
    static String player2;
    static String currentPlayer = "";
    static int turn = 0;

    public static void gameSetUp() {
      System.out.println("How wide do you want the board?");
      int wide = scan.nextInt();

      System.out.println("How deep do you want the board");
      int deep = scan.nextInt();

      b = new Board(wide, deep);
      b.printBoard();

      System.out.println("\nWill player 1 be x or o");
      player1 = scan.next();

      if(player1.equals("x"))
      {
        player2 = "o";
      } else {
        player2 = "x";
      }
    }

    public static void testSetUp1() {
      b = new Board(5, 6);

      player1 = "x";
      player2 = "o";

      b.placePiece(2, player1);
      b.placePiece(3, player2);
      b.placePiece(3, player1);
      b.placePiece(4, player2);
      b.placePiece(5, player1);
      b.placePiece(4, player2);

      b.printBoard();

      turn = 6;
    }

    public static void testSetUp2() {
      b = new Board(5, 6);
      b.printBoard();

      player1 = "x";
      player2 = "o";
    }

    public static void main(String[] args) {

      gameSetUp();
      //testSetUp1();
      //testSetUp2();

      while(!b.hasWinner())
      {
        if(turn%2==0)
        {
          System.out.println("It's player 1's turn\nEnter a column to place piece");
          currentPlayer = player1;
        } else {
          System.out.println("It's player 2's turn\nEnter a column to place piece");
          currentPlayer = player2;
        }

        int chosenColumn = scan.nextInt();

        b.placePiece(chosenColumn, currentPlayer);
        b.printBoard();

        turn++;
      }

      if((turn-1)%2 == 0)
      {
        System.out.println("Player 1 Wins");
      } else {
        System.out.println("Player 2 Wins");
      }
    }
}
