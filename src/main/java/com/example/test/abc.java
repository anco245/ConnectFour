package com.example.test;

public class abc {
  public static void main(String[] args)
  {
    String str = "Chocolate";

    StringBuilder app = new StringBuilder();
    app.append(str.charAt(0));
    //Congrr

    int i = 1;

    do {
      //app.append(str.charAt(x));
      app.append(str.charAt(i));

      if(i%2==0)
      {
        i++;
      } else {
        i+=3;
      }

    } while(i < str.length());


    //0, 2, 4, 6, 8, 10, 12, 14

    //0, 1, 4, 5, 8, 9, 12, 13, 16
    //  1, 3, 1, 3, 1, 3,  1,  3,

    System.out.println(app);
  }
}
