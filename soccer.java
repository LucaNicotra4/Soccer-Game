import java.util.Scanner;
import java.lang.*;

class Main {

  //public static final String ANSI_BOLD = "\u001b[1m";
  public static final String ANSI_RESET = "\u001B[0m"; 
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\033[0;34m"; //\u001B[34m
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static int playerScore = 0;
  public static int opposerScore = 0;
  public static int time = 1;
  public static int difficulty = 1;
  public static int ballAt = 0;
  public static int error = 0;


  public static void main(String[] args) {

    //intial setup
    clear();
    System.out.print(ANSI_RESET);
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome to Soccer!\nWould you like to play? (y or n): ");
    String response = input.nextLine();
    if(response.equals("y")){
      clear();
      System.out.println("Enter -1 at any time to quit");
      System.out.print("Each turn, decide whether to pass or shoot by pressing p or s. ");
      System.out.print("After pressing s, choose which player to pass to by typing in the number of that player without the preceeding zero. ");
      System.out.print("To shoot, select s when prompted. Then type in where you would like your player to aim from the options given. ");
      System.out.print("Your number 8 has very good chance of scoring, your 7 and 9 have decent chances, your 6 and 10 have somewhat decent chances, your 5 has a terrible chance, and all other players have near impossible chances. ");
      System.out.print("The difficulty will decide how strong your opponent is on the counter attack.");
      System.out.print("Additionally, every three turns, a new player on the opponents team will be out of position (shown by being highlighted black instead of red), ");
      System.out.println("and any pass to a teammate being marked to an out of position player has a 2x better chance of being completed.");
    }else{
      System.exit(0);
    }
    difficulty = -1;
    while(difficulty == -1){
      System.out.println("What difficutly? (1, 2, or 3): ");
      difficulty = input.nextInt();
      if(!(difficulty == 1 || difficulty == 2 || difficulty == 3)){
        difficulty = -1;
      }
    }
    
    //player's team
    Positions[] players = new Positions[11];
    players = buildTeam(true);
    players[0].setBall(true);
    //opposing team
    Positions[] opposers = new Positions[11];
    opposers = buildTeam(false);
    clear();
    Field0(players, opposers);


    //game input starts here
    int counter = 0;
    ballAt = 0;
    int ballTo = 0;
    error = 0;
    String answer = "";
    //GAMEPLAY GAMEPLAY GAMEPLAY GAMEPLAY
    while(!(answer.equals("-1"))){
      if(counter == 0){
        error = error(opposers);
        counter = 3;
      }
      counter--;
      answer = "";
      System.out.println("Would you like to pass or shoot? (p or s): " + ANSI_RESET);
      while(answer.equals("")){
        answer = input.nextLine();
      }

      //input for passing, shooting, etc.
      if(answer.equals("p")){
        int num = 11;
        System.out.println("To which player? (no need to enter preceding zero): ");
        System.out.println(odds(ballAt));
        while(num == 11){
          num = input.nextInt();
          ballTo = num;
          if(num == ballAt){
            System.out.println("Ball already at player " + ballAt + "\nTo which player? ");
            num = 11;
          }
        }
        if(pass(ballAt, ballTo) == true){
          ballAt = ballTo;
          updatePositions(num, players);
          clear();
          if(num == 0){
            Field0(players, opposers);
          }else if(num == 1){
            Field1(players, opposers);
          }else if(num == 2){
            Field2(players, opposers);
          }else if(num == 3){
            Field3(players, opposers);
          }else if(num == 4){
            Field4(players, opposers);
          }else if(num == 5){
            Field5(players, opposers);
          }else if(num == 6){
            Field6(players, opposers);
          }else if(num == 7){
            Field7(players, opposers);
          }else if(num == 8){
            Field8(players, opposers);
          }else if(num == 9){
            Field9(players, opposers);
          }else if(num ==10){
            Field10(players,opposers);
          }else if(num ==-1){
            System.exit(0);
          }//end of pass succeed
        }else{
          boolean again = false;
          if(ballTo == 1 || ballTo == 2){
            if(error == 8){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 3){
            if(error == 6){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 4){
            if(error == 10){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 5){
            if(error == 5 || error == 7 || error == 9){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 6){
            if(error == 4){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 7){
            if(error == 9){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 8){
            if(error == 1 || error == 2){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 9){
            if(error == 7){
              again = pass(ballAt, ballTo);
            }
          }else if(ballTo == 10){
            if(error == 3){
              again = pass(ballAt, ballTo);
            }
          }
          if(again == false){
            System.out.println("Pass Blocked");
            counterAttack(players, opposers);
          }else{
            ballAt = ballTo;
            updatePositions(num, players);
            clear();
            if(num == 0){
              Field0(players, opposers);
            }else if(num == 1){
              Field1(players, opposers);
            }else if(num == 2){
              Field2(players, opposers);
            }else if(num == 3){
              Field3(players, opposers);
            }else if(num == 4){
              Field4(players, opposers);
            }else if(num == 5){
              Field5(players, opposers);
            }else if(num == 6){
              Field6(players, opposers);
            }else if(num == 7){
              Field7(players, opposers);
            }else if(num == 8){
              Field8(players, opposers);
            }else if(num == 9){
              Field9(players, opposers);
            }else if(num ==10){
              Field10(players,opposers);
            }else if(num ==-1){
              System.exit(0);
            }
          }
        }//end of pass fail;
        time++;
      }else if(answer.equals("s")){

        String shot = "";
        while(shot.equals("")){
          System.out.println("Shoot Where?\n(Top Right (TR) || Bottom Right (BR) || Top Left (TL) || Bottom Left (BL) || Middle (M))");
          shot = input.nextLine();
          if(!(shot.equals("TR") || shot.equals("BR") || shot.equals("TL") || shot.equals("BL") || shot.equals("M"))){
            System.out.println("Unrecognized input");
            shot = "";
          }else{
            if(shoot(shot)){
              golazo();
              ballAt = 0;
              updatePositions(ballAt, players);
              clear();
              Field0(players, opposers);
              System.out.println("BEAUTIFUL GOALLL");
              counterAttack(players, opposers);
            }else{
              System.out.println("Shot Saved!");
              time++;
              counterAttack(players, opposers);
            }
          }
        }
        time++;
      }else{
        System.out.println("Unrecognized input");
      }//end of input evaluation
      if(time >= 91){
        System.out.println("Game Over");
        System.out.println("FINAL: PLAYER1 " + playerScore + " || COMPUTER " + opposerScore);
        System.exit(0);
      }
    }//end of while loop

  }//end of main



//ballAt - where ball is currently at || ballTo - where user is trying to pass to
  public static boolean pass(int ballAt, int ballTo){
    if(ballAt == 0){
      switch(ballTo){
        //(01-100%||02-100%||03-90%||04-90%||05-75%||06-25%||07-55%||08-20%||09-55%||10-25%)
        case 1: return true;
        case 2: return true;
        case 3: if(Math.random()*100 > 10){return true;}else{return false;}
        case 4: if(Math.random()*100 > 10){return true;}else{return false;}
        case 5: if(Math.random()*100 > 25){return true;}else{return false;}
        case 6: if(Math.random()*100 > 75){return true;}else{return false;}
        case 7: if(Math.random()*100 > 45){return true;}else{return false;}
        case 8: if(Math.random()*100 > 80){return true;}else{return false;}
        case 9: if(Math.random()*100 > 45){return true;}else{return false;}
        case 10: if(Math.random()*100 > 75){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 1){
      switch(ballTo){
        //(00-100%||02-95%||03-90%||04-80%||05-75%||06-50%||07-70%||08-25%||09-50%||10-25%)
        case 0: return true;
        case 2: if(Math.random()*100 > 5){return true;}else{return false;}
        case 3: if(Math.random()*100 > 10){return true;}else{return false;}
        case 4: if(Math.random()*100 > 20){return true;}else{return false;}
        case 5: if(Math.random()*100 > 25){return true;}else{return false;}
        case 6: if(Math.random()*100 > 50){return true;}else{return false;}
        case 7: if(Math.random()*100 > 30){return true;}else{return false;}
        case 8: if(Math.random()*100 > 75){return true;}else{return false;}
        case 9: if(Math.random()*100 > 50){return true;}else{return false;}
        case 10: if(Math.random()*100 > 75){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 2){
      switch(ballTo){
        //(00-100%||01-95%||03-80%||04-90%||05-75%||06-25%||07-50%||08-25%||09-70%||10-50%)
        case 0: return true;
        case 1: if(Math.random()*100 > 5){return true;}else{return false;}
        case 3: if(Math.random()*100 > 20){return true;}else{return false;}
        case 4: if(Math.random()*100 > 10){return true;}else{return false;}
        case 5: if(Math.random()*100 > 25){return true;}else{return false;}
        case 6: if(Math.random()*100 > 75){return true;}else{return false;}
        case 7: if(Math.random()*100 > 50){return true;}else{return false;}
        case 8: if(Math.random()*100 > 75){return true;}else{return false;}
        case 9: if(Math.random()*100 > 30){return true;}else{return false;}
        case 10: if(Math.random()*100 > 50){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 3){
      switch(ballTo){
        //(00-100%||01-100%||02-90%||04-85%||05-80%||06-30%||07-65%||08-30%||09-75%||10-70%)
        case 0: return true;
        case 1: return true;
        case 2: if(Math.random()*100 > 10){return true;}else{return false;}
        case 4: if(Math.random()*100 > 15){return true;}else{return false;}
        case 5: if(Math.random()*100 > 20){return true;}else{return false;}
        case 6: if(Math.random()*100 > 70){return true;}else{return false;}
        case 7: if(Math.random()*100 > 35){return true;}else{return false;}
        case 8: if(Math.random()*100 > 70){return true;}else{return false;}
        case 9: if(Math.random()*100 > 25){return true;}else{return false;}
        case 10: if(Math.random()*100 > 30){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 4){
      switch(ballTo){
        //(00-100%||01-90%||02-100%||03-85%||05-80%||06-70%||07-75%||08-30%||09-65%||10-30%)
        case 0: return true;
        case 1: if(Math.random()*100 > 10){return true;}else{return false;}
        case 2: return true;
        case 3: if(Math.random()*100 > 15){return true;}else{return false;}
        case 5: if(Math.random()*100 > 20){return true;}else{return false;}
        case 6: if(Math.random()*100 > 30){return true;}else{return false;}
        case 7: if(Math.random()*100 > 25){return true;}else{return false;}
        case 8: if(Math.random()*100 > 70){return true;}else{return false;}
        case 9: if(Math.random()*100 > 35){return true;}else{return false;}
        case 10: if(Math.random()*100 > 70){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 5){
      switch(ballTo){
        //(00-100%||01-95%||02-95%||03-95%||04-95%||06-75%||07-80%||08-70%||09-80%||10-75%)
        case 0: return true;
        case 1: if(Math.random()*100 > 5){return true;}else{return false;}
        case 2: if(Math.random()*100 > 5){return true;}else{return false;}
        case 3: if(Math.random()*100 > 5){return true;}else{return false;}
        case 4: if(Math.random()*100 > 5){return true;}else{return false;}
        case 6: if(Math.random()*100 > 25){return true;}else{return false;}
        case 7: if(Math.random()*100 > 20){return true;}else{return false;}
        case 8: if(Math.random()*100 > 30){return true;}else{return false;}
        case 9: if(Math.random()*100 > 20){return true;}else{return false;}
        case 10: if(Math.random()*100 > 25){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 6){
      switch(ballTo){
        //(00-100%||01-85%||02-85%||03-85%||04-95%||05-60%||07-90%||08-75%||09-70%||10-80%)
        case 0: return true;
        case 1: if(Math.random()*100 > 15){return true;}else{return false;}
        case 2: if(Math.random()*100 > 15){return true;}else{return false;}
        case 3: if(Math.random()*100 > 15){return true;}else{return false;}
        case 4: if(Math.random()*100 > 5){return true;}else{return false;}
        case 5: if(Math.random()*100 > 40){return true;}else{return false;}
        case 7: if(Math.random()*100 > 10){return true;}else{return false;}
        case 8: if(Math.random()*100 > 25){return true;}else{return false;}
        case 9: if(Math.random()*100 > 30){return true;}else{return false;}
        case 10: if(Math.random()*100 > 20){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 7){
      switch(ballTo){
        //(00-100%||01-90%||02-90%||03-90%||04-85%||05-85%||06-85%||08-70%||09-85%||10-80%)
        case 0: return true;
        case 1: if(Math.random()*100 > 10){return true;}else{return false;}
        case 2: if(Math.random()*100 > 10){return true;}else{return false;}
        case 3: if(Math.random()*100 > 10){return true;}else{return false;}
        case 4: if(Math.random()*100 > 15){return true;}else{return false;}
        case 5: if(Math.random()*100 > 15){return true;}else{return false;}
        case 6: if(Math.random()*100 > 15){return true;}else{return false;}
        case 8: if(Math.random()*100 > 30){return true;}else{return false;}
        case 9: if(Math.random()*100 > 15){return true;}else{return false;}
        case 10: if(Math.random()*100 > 20){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 8){
      switch(ballTo){
        //(00-100%||01-90%||02-90%||03-85%||04-85%||05-80%||06-75%||07-80%||09-80%||10-75%)
        case 0: return true;
        case 1: if(Math.random()*100 > 10){return true;}else{return false;}
        case 2: if(Math.random()*100 > 10){return true;}else{return false;}
        case 3: if(Math.random()*100 > 15){return true;}else{return false;}
        case 4: if(Math.random()*100 > 15){return true;}else{return false;}
        case 5: if(Math.random()*100 > 20){return true;}else{return false;}
        case 6: if(Math.random()*100 > 25){return true;}else{return false;}
        case 7: if(Math.random()*100 > 20){return true;}else{return false;}
        case 9: if(Math.random()*100 > 20){return true;}else{return false;}
        case 10: if(Math.random()*100 > 25){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 9){
      switch(ballTo){
        //(00-100%||01-90%||02-90%||03-85%||04-90%||05-85%||06-80%||07-85%||08-70%||10-85%)
        case 0: return true;
        case 1: if(Math.random()*100 > 10){return true;}else{return false;}
        case 2: if(Math.random()*100 > 10){return true;}else{return false;}
        case 3: if(Math.random()*100 > 15){return true;}else{return false;}
        case 4: if(Math.random()*100 > 10){return true;}else{return false;}
        case 5: if(Math.random()*100 > 15){return true;}else{return false;}
        case 6: if(Math.random()*100 > 20){return true;}else{return false;}
        case 7: if(Math.random()*100 > 15){return true;}else{return false;}
        case 8: if(Math.random()*100 > 30){return true;}else{return false;}
        case 10: if(Math.random()*100 > 15){return true;}else{return false;}
        default: return true;
      }
    }else if(ballAt == 10){
      switch(ballTo){
        //(00-100%||01-85%||02-85%||03-95%||04-85%||05-60%||06-80%||07-70%||08-75%||09-90%)
        case 0: return true;
        case 1: if(Math.random()*100 > 15){return true;}else{return false;}
        case 2: if(Math.random()*100 > 15){return true;}else{return false;}
        case 3: if(Math.random()*100 > 5){return true;}else{return false;}
        case 4: if(Math.random()*100 > 15){return true;}else{return false;}
        case 5: if(Math.random()*100 > 40){return true;}else{return false;}
        case 6: if(Math.random()*100 > 20){return true;}else{return false;}
        case 7: if(Math.random()*100 > 30){return true;}else{return false;}
        case 8: if(Math.random()*100 > 25){return true;}else{return false;}
        case 9: if(Math.random()*100 > 10){return true;}else{return false;}
        default: return true;
      }
    }
    return true;
  }//end of passing function


  public static String odds(int ballAt){
    switch(ballAt){
      case 0: return "(01-100%||02-100%||03-90%||04-90%||05-75%||06-25%||07-55%||08-20%||09-55%||10-25%)";
      case 1: return "(00-100%||02-95%||03-90%||04-80%||05-75%||06-50%||07-70%||08-25%||09-50%||10-25%)";
      case 2: return "(00-100%||01-95%||03-80%||04-90%||05-75%||06-25%||07-50%||08-25%||09-70%||10-50%)";
      case 3: return "(00-100%||01-100%||02-90%||04-85%||05-80%||06-30%||07-65%||08-30%||09-75%||10-70%)";
      case 4: return "(00-100%||01-90%||02-100%||03-85%||05-80%||06-70%||07-75%||08-30%||09-65%||10-30%)";
      case 5: return "(00-100%||01-95%||02-95%||03-95%||04-95%||06-75%||07-80%||08-70%||09-80%||10-75%)";
      case 6: return "(00-100%||01-85%||02-85%||03-85%||04-95%||05-60%||07-90%||08-75%||09-70%||10-80%)";
      case 7: return "(00-100%||01-90%||02-90%||03-90%||04-85%||05-85%||06-85%||08-70%||09-85%||10-80%)";
      case 8: return "(00-100%||01-90%||02-90%||03-85%||04-85%||05-80%||06-75%||07-80%||09-80%||10-75%)";
      case 9: return "(00-100%||01-90%||02-90%||03-85%||04-90%||05-85%||06-80%||07-85%||08-70%||10-85%)";
      case 10: return "(00-100%||01-85%||02-85%||03-95%||04-85%||05-60%||06-80%||07-70%||08-75%||09-90%)";
      default: return "" + ballAt;
    }
  }


  public static boolean shoot(String shot){
    int x;
    if(ballAt == 8){
      x = 2;
    }else if(ballAt == 9 || ballAt == 7){
      x = 6;
    }else if(ballAt == 10 || ballAt == 6){
      x = 9;
    }else if(ballAt == 5){
      x = 25;
    }else{
      x = 100;
    }

    for(int i = 0; i < x; i++){
      int random = (int)(Math.random() * 5);
      String save = "";
      if(random == 0){
        save = "TR";
      }else if(random == 1){
        save = "BR";
      }else if(random == 2){
        save = "TL";
      }else if(random == 3){
        save = "BL";
      }else if(random == 4){
        save = "M";
      }

      if(shot.equals(save)){
        return false;
      }
    }
    return true;
  }


  public static void golazo(){
    playerScore++;
    double millis = 0;
    for(int i = 0; i < 5; i++){
      millis = System.currentTimeMillis();
      clear();
      Field11();
      while((System.currentTimeMillis() - millis) < 500){

      }
      clear();
      Field12();
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 500)){

      }
    }
    System.out.println("Beautiful Goal");
  }


  public static void counterAttack(Positions[] players, Positions[] opposers){
    String[] phrases1 = new String[4];
    phrases1[0] = "Opponent switches the field...";
    phrases1[1] = "Opponent passes around the back...";
    phrases1[2] = "Opponent makes the pass...";
    phrases1[3] = "Opponent moves the ball around...";
    String[] phrases2 = new String[4];
    phrases2[0] = "Opponent moves the ball up the field...";
    phrases2[1] = "Opponent dribbles up the field...";
    phrases2[2] = "Opponent plays the ball foward...";
    phrases2[3] = "Opponent drives downfield...";
    String[] phrases3 = new String[4];
    phrases3[0] = "Opponent is on the attack...";
    phrases3[1] = "Opponent drives towards goal...";
    phrases3[2] = "Opponent crosses the ball in...";
    phrases3[3] = "Opponent moves into the attacking third...";
    String[] phrases4 = new String[4];
    phrases4[0] = "Opponent loses the ball...";
    phrases4[1] = "Opponent makes a poor pass...";
    phrases4[2] = "Opponent gives up possesion...";
    phrases4[3] = "Opponent fails to connect the pass...";

    double millis = 0;
    millis = System.currentTimeMillis();
    while((System.currentTimeMillis() - millis < 1500)){

    }
    System.out.println("Opponent has ball...");
    millis = System.currentTimeMillis();
    while((System.currentTimeMillis() - millis < 1500)){

    }

    boolean bang = false;
    if(difficulty == 1){
      if(Math.random()*101 > 80){
        bang = true;
      }
    }else if(difficulty == 2){
      if(Math.random()*101 > 70){
        bang = true;
      }
    }else if(difficulty == 3){
      if(Math.random()*101 > 65){
        bang = true;
      }
    }

    if(bang == true){
      System.out.println(phrases1[r()]);
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      System.out.println(phrases2[r()]);
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      System.out.println(phrases3[r()]);
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      System.out.println("Opponent takes the shot...");
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      System.out.println("Opponent scores!!");
      opposerScore++;
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      ballAt = 0;
      updatePositions(ballAt, players);
      clear();
      Field0(players, opposers);
    }else{
      System.out.println(phrases1[r()]);
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      System.out.println(phrases2[r()]);
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      System.out.println(phrases3[r()]);
      millis = System.currentTimeMillis();
      while((System.currentTimeMillis() - millis < 1500)){

      }
      time++;
      if(Math.random() > .5){
        System.out.println(phrases4[r()]);
        ballAt = (int)((Math.random()*4)+1);
        millis = System.currentTimeMillis();
        while((System.currentTimeMillis() - millis < 1500)){

        }
        clear();
        updatePositions(ballAt, players);
        clear();
        if(ballAt == 1){
          Field1(players, opposers);
        }else if(ballAt == 2){
          Field2(players, opposers);
        }else if(ballAt == 3){
          Field3(players, opposers);
        }else if(ballAt == 4){
          Field4(players, opposers);
        }else{
          Field1(players, opposers);
        }

      }else{
        System.out.println("Opponent takes the shot...");
        millis = System.currentTimeMillis();
        while((System.currentTimeMillis() - millis < 1500)){

        }
        time++;
        System.out.println("Shot misses.");
        ballAt = 0;
        millis = System.currentTimeMillis();
        while((System.currentTimeMillis() - millis < 1500)){

        }
        updatePositions(ballAt, players);
        clear();
        Field0(players, opposers);
      }
    }

  }


  public static int r(){
    return (int)(Math.random()*4);
  }
  
  
  public static void clear() { 
    System.out.print("\033[H\033[2J"); 
    System.out.flush(); 
  }

  public static String t(){
    if(time < 10){
      return "0" + time + ":00";
    }else{
      return time + ":00";
    }
  }


  //start of fields
  public static void Field0(Positions[] p, Positions[] o){
    //31, then center line, then 31 (63 total)
    String ball = "⚽️"; // takes up two slots
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.print("┌──────────────────────────────┬──────────────────────────────┐");System.out.print("\n");//63X13
    System.out.print("│                 "+a(p[3])+"           │        "+a(p[a])+"                    │");System.out.print("\n");
    System.out.print("│       "+a(p[1])+"               "+a(o[7])+"     │            "+a(o[3])+"                 │");System.out.print("\n");
    System.out.print("├────────┐      "+a(o[6])+"              │                     ┌────────┤");System.out.print("\n");//8(not incl corner)
    System.out.print("│        │                   "+a(p[9])+"│                 "+a(o[1])+"   │        │");System.out.print("\n");
    System.out.print("├───┐    │                     │                     │    ┌───┤");System.out.print("\n");//3(not incl corner)
    System.out.print("│   │"+a(p[0])+"  │"+a(o[8])+"     "+a(p[5])+"     "+a(o[5])+"       │              "+a(p[8])+"     │   "+a(o[0])+"│   │");System.out.print("\n");//center
    System.out.print("├───┘    │                     │                     │    └───┤");System.out.print("\n");
    System.out.print("│        │                   "+a(p[7])+"│                 "+a(o[2])+"   │        │");System.out.print("\n");
    System.out.print("├────────┘      "+a(o[a])+"              │                     └────────┤");System.out.print("\n");
    System.out.print("│       "+a(p[2])+"               "+a(o[9])+"     │            "+a(o[4])+"                 │");System.out.print("\n");
    System.out.print("│                 "+a(p[4])+"           │        "+a(p[6])+"                    │");System.out.print("\n");
    System.out.print("└──────────────────────────────┴──────────────────────────────┘");System.out.print("\n");
    System.out.print(ANSI_RESET);
    
  }

  public static void Field1(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│               "+a(p[3])+"             │          "+a(p[10])+"                  │");
    System.out.println("│       "+a(p[1])+"   "+a(o[6])+"                 │         "+a(o[3])+"                    │");
    System.out.println("├────────┐         "+a(o[8])+"           │"+a(p[9])+"                 "+a(o[1])+" ┌────────┤");
    System.out.println("│        │      "+a(p[5])+"             │   "+a(o[5])+"        "+a(p[8])+"       │        │");
    System.out.println("├───┐    │    "+a(o[9])+"                │                     │    ┌───┤");
    System.out.println("│   │"+a(p[0])+"  │              "+a(o[7])+"      │                     │   "+a(o[0])+"│   │");
    System.out.println("├───┘    │       "+a(o[a])+"       "+a(p[7])+"    │                   "+a(o[2])+" │    └───┤");
    System.out.println("│        │"+a(p[2])+"                   │                     │        │");
    System.out.println("├────────┘                     │ "+a(p[6])+"    "+a(o[4])+"             └────────┤");
    System.out.println("│              "+a(p[4])+"              │                              │");
    System.out.println("│                              │                              │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  public static void Field2(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                              │                              │");
    System.out.println("│              "+a(p[3])+"              │                              │");
    System.out.println("├────────┐                     │ "+a(p[a])+"    "+a(o[3])+"             ┌────────┤");
    System.out.println("│        │"+a(p[1])+"                   │                     │        │");
    System.out.println("├───┐    │       "+a(o[6])+"       "+a(p[9])+"    │                     │    ┌───┤");
    System.out.println("│   │"+a(p[0])+"  │              "+a(o[5])+"      │                     │   "+a(o[0])+"│   │");
    System.out.println("├───┘    │    "+a(o[8])+"                │                  "+a(o[1])+"  │    └───┤");
    System.out.println("│        │      "+a(p[5])+"             │            "+a(p[8])+"       │        │");
    System.out.println("├────────┘         "+a(o[7])+"           │"+a(p[7])+"              "+a(o[2])+"    └────────┤");
    System.out.println("│       "+a(p[2])+"   "+a(o[a])+"                 │ "+a(o[9])+"      "+a(o[4])+"                     │");
    System.out.println("│               "+a(p[4])+"             │          "+a(p[6])+"                  │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  public static void Field3(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                           "+a(p[3])+" │                     "+a(p[a])+"       │");
    System.out.println("│                   "+a(p[1])+"         │  "+a(o[6])+"      "+a(p[9])+""+a(o[7])+"           "+a(o[3])+"      │");
    System.out.println("├────────┐                     │                     ┌────────┤");
    System.out.println("│        │                 "+a(o[8])+"   │"+a(p[5])+"                  "+a(o[1])+"│        │");
    System.out.println("├───┐    │                     │        "+a(o[5])+"         "+a(p[8])+" │    ┌───┤");
    System.out.println("│   │"+a(p[0])+"  │         "+a(p[2])+"          │                     │   "+a(o[0])+"│   │");
    System.out.println("├───┘    │                     │       "+a(p[7])+"            │    └───┤");
    System.out.println("│        │                     │  "+a(o[a])+"        "+a(o[9])+"        "+a(o[2])+"│        │");
    System.out.println("├────────┘               "+a(p[4])+"    │                     └────────┤");
    System.out.println("│                              │                 "+a(o[4])+"            │");
    System.out.println("│                              │        "+a(p[6])+"                    │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  public static void Field4(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                              │        "+a(p[a])+"                    │");
    System.out.println("│                              │                 "+a(o[3])+"            │");
    System.out.println("├────────┐               "+a(p[3])+"    │                     ┌────────┤");
    System.out.println("│        │                     │  "+a(o[6])+"        "+a(o[7])+"        "+a(o[1])+"│        │");
    System.out.println("├───┐    │                     │       "+a(p[9])+"            │    ┌───┤");
    System.out.println("│   │"+a(p[0])+"  │         "+a(p[1])+"          │                     │   "+a(o[0])+"│   │");
    System.out.println("├───┘    │                     │        "+a(o[5])+"         "+a(p[8])+" │    └───┤");
    System.out.println("│        │                 "+a(o[8])+"   │"+a(p[5])+"                  "+a(o[2])+"│        │");
    System.out.println("├────────┘                     │                     └────────┤");
    System.out.println("│                   "+a(p[2])+"         │  "+a(o[a])+"      "+a(p[7])+""+a(o[7])+"           "+a(o[4])+"      │");
    System.out.println("│                           "+a(p[4])+" │                     "+a(p[6])+"       │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  public static void Field5(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.print("┌──────────────────────────────┬──────────────────────────────┐");System.out.print("\n");//63X13
    System.out.print("│                         "+a(p[3])+"   │                   "+a(p[a])+"         │");System.out.print("\n");
    System.out.print("│                              │    "+a(o[6])+"                  "+a(o[3])+"      │");System.out.print("\n");
    System.out.print("├────────┐                     │         "+a(p[9])+"          ┌────────┤");System.out.print("\n");//8(not incl corner)
    System.out.print("│        │       "+a(p[1])+"            │            "+a(o[7])+"       "+a(o[1])+"│        │");System.out.print("\n");
    System.out.print("├───┐    │                     │                     │    ┌───┤");System.out.print("\n");//3(not incl corner)
    System.out.print("│   │"+a(p[0])+"  │           "+a(o[8])+"       "+a(p[5])+"│ "+a(o[5])+"                "+a(p[8])+" │   "+a(o[0])+"│   │");System.out.print("\n");//center
    System.out.print("├───┘    │                     │                     │    └───┤");System.out.print("\n");
    System.out.print("│        │       "+a(p[2])+"            │            "+a(o[9])+"       "+a(o[2])+"│        │");System.out.print("\n");
    System.out.print("├────────┘                     │         "+a(p[7])+"          └────────┤");System.out.print("\n");
    System.out.print("│                              │    "+a(o[a])+"                  "+a(o[4])+"      │");System.out.print("\n");
    System.out.print("│                         "+a(p[4])+"   │                   "+a(p[6])+"         │");System.out.print("\n");
    System.out.print("└──────────────────────────────┴──────────────────────────────┘");System.out.print("\n");
    System.out.print(ANSI_RESET);
  }

  public static void Field6(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                              │              "+a(p[3])+"              │");
    System.out.println("│                              │                          "+a(o[3])+"   │");
    System.out.println("├────────┐                     │             "+a(o[6])+"       ┌────────┤");
    System.out.println("│        │                   "+a(p[1])+"│                     │"+a(p[a])+"      │");
    System.out.println("├───┐    │                     │              "+a(p[9])+"     │   "+a(o[1])+"┌───┤");
    System.out.println("│   │"+a(p[0])+"  │                     │"+a(o[8])+"            "+a(o[7])+"       │"+a(p[8])+"  │"+a(o[0])+"  │");
    System.out.println("├───┘    │                     │            "+a(p[5])+" "+a(o[5])+"     │   "+a(o[2])+"└───┤");
    System.out.println("│        │                   "+a(p[2])+"│                    "+a(o[9])+"│        │");
    System.out.println("├────────┘                     │                "+a(o[a])+"  "+a(p[7])+"└────────┤");
    System.out.println("│                              │                          "+a(o[4])+"   │");
    System.out.println("│                              │               "+a(p[4])+"       "+a(p[6])+"    │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }


  public static void Field7(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                              │                              │");
    System.out.println("│                            "+a(p[3])+"│                "+a(p[a])+"            │");
    System.out.println("├────────┐                     │      "+a(o[6])+"             "+a(o[3])+"┌────────┤");
    System.out.println("│        │                     │        "+a(p[9])+"           │        │");
    System.out.println("├───┐    │         "+a(p[1])+"          │              "+a(o[7])+"      │    ┌───┤");
    System.out.println("│   │"+a(p[0])+"  │                     │  "+a(p[5])+"   "+a(o[5])+"           "+a(o[1])+" │   "+a(o[0])+"│   │");
    System.out.println("├───┘    │                    "+a(o[8])+"│                  "+a(p[8])+" │    └───┤");
    System.out.println("│        │                     │            "+a(p[7])+"   "+a(o[2])+"   │        │");
    System.out.println("├────────┘         "+a(p[2])+"          │        "+a(o[9])+"            └────────┤");
    System.out.println("│                              │ "+a(o[a])+"                     "+a(o[4])+"      │");
    System.out.println("│                          "+a(p[4])+"  │                "+a(p[6])+"            │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  
  public static void Field8(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.print("┌──────────────────────────────┬──────────────────────────────┐");System.out.print("\n");//63X13
    System.out.print("│                              │              "+a(p[3])+"              │");System.out.print("\n");
    System.out.print("│                              │                  "+a(o[6])+"           │");System.out.print("\n");
    System.out.print("├────────┐                     │                     ┌────────┤");System.out.print("\n");//8(not incl corner)
    System.out.print("│        │                   "+a(p[1])+"│                   "+a(o[3])+" │   "+a(p[a])+"   │");System.out.print("\n");
    System.out.print("├───┐    │                     │                   "+a(p[9])+"│"+a(o[7])+"  "+a(o[1])+"┌───┤");System.out.print("\n");//3(not incl corner)
    System.out.print("│   │"+a(p[0])+"  │                     │   "+a(o[8])+"   "+a(p[5])+"        "+a(o[5])+"   │"+a(p[8])+"  │ "+a(o[0])+" │");System.out.print("\n");//center
    System.out.print("├───┘    │                     │                   "+a(p[7])+"│"+a(o[9])+"  "+a(o[2])+"└───┤");System.out.print("\n");
    System.out.print("│        │                   "+a(p[2])+"│                   "+a(o[4])+" │   "+a(p[6])+"   │");System.out.print("\n");
    System.out.print("├────────┘                     │                     └────────┤");System.out.print("\n");
    System.out.print("│                              │                  "+a(o[a])+"           │");System.out.print("\n");
    System.out.print("│                              │              "+a(p[4])+"              │");System.out.print("\n");
    System.out.print("└──────────────────────────────┴──────────────────────────────┘");System.out.print("\n");
    System.out.print(ANSI_RESET);

  }

  public static void Field9(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                          "+a(p[3])+"  │                "+a(p[a])+"            │");
    System.out.println("│                              │ "+a(o[6])+"                     "+a(o[3])+"      │");
    System.out.println("├────────┐          "+a(p[1])+"         │         "+a(o[7])+"           ┌────────┤");
    System.out.println("│        │                     │             "+a(p[9])+"   "+a(o[1])+"  │        │");
    System.out.println("├───┐    │                    "+a(o[8])+"│                  "+a(p[8])+" │    ┌───┤");
    System.out.println("│   │"+a(p[0])+"  │                     │   "+a(p[5])+"  "+a(o[5])+"           "+a(o[2])+" │   "+a(o[0])+"│   │");
    System.out.println("├───┘    │          "+a(p[2])+"         │              "+a(o[9])+"      │    └───┤");
    System.out.println("│        │                     │         "+a(p[7])+"          │        │");
    System.out.println("├────────┘                     │      "+a(o[a])+"             "+a(o[4])+"└────────┤");
    System.out.println("│                            "+a(p[4])+"│                "+a(p[6])+"            │");
    System.out.println("│                              │                              │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }


  public static void Field10(Positions[] p, Positions[] o){
    int a = 10;
    System.out.println(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                              │               "+a(p[3])+"       "+a(p[a])+"    │");
    System.out.println("│                              │                          "+a(o[3])+"   │");
    System.out.println("├────────┐                     │                "+a(o[6])+"  "+a(p[9])+"┌────────┤");
    System.out.println("│        │                   "+a(p[1])+"│                    "+a(o[7])+"│        │");
    System.out.println("├───┐    │                     │            "+a(p[5])+" "+a(o[5])+"     │   "+a(o[1])+"┌───┤");
    System.out.println("│   │"+a(p[0])+"  │                     │"+a(o[8])+"            "+a(o[9])+"       │"+a(p[8])+"  │"+a(o[0])+"  │");
    System.out.println("├───┘    │                     │              "+a(p[7])+"     │   "+a(o[2])+"└───┤");
    System.out.println("│        │                   "+a(p[2])+"│                     │"+a(p[6])+"      │");
    System.out.println("├────────┘                     │             "+a(o[a])+"       └────────┤");
    System.out.println("│                              │                          "+a(o[4])+"   │");
    System.out.println("│                              │              "+a(p[4])+"              │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  public static void Field11(){
    System.out.print(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");
    System.out.println("│                              │                              │");
    System.out.println("│      █████████   ███████████ │ ██████████   █         ██    │");
    System.out.println("├──────█─┐         █         █ │ █        █   █      ┌──██────┤");
    System.out.println("│      █ │         █         █ │ █        █   █      │  ██    │");
    System.out.println("├───┐  █ │         █         █ │ ██████████   █      │  ██┌───┤");
    System.out.println("│   │  █ │ █████   █         █ │ █        █   █      │  ██│   │");
    System.out.println("├───┘  █ │     █   █         █ │ █        █   █      │  ██└───┤");
    System.out.println("│      █ │     █   █         █ │ █        █   █      │        │");
    System.out.println("├──────█████████   ███████████ │ █        █   █████████─██────┤");
    System.out.println("│                              │                              │");
    System.out.println("│                              │                              │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }

  public static void Field12(){
    System.out.print(ANSI_GREEN_BACKGROUND);
    System.out.print(ANSI_WHITE);
    System.out.print("                "+playerScore+" <- PLAYER1|"+t()+"|COMPUTER -> "+opposerScore+"               ");System.out.print("\n");
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");//63X13
    System.out.println("│                              │                              │");
    System.out.println("│                              │                              │");
    System.out.println("├────────┐                     │                     ┌────────┤");//8 (not incl corner)
    System.out.println("│        │                     │                     │        │");
    System.out.println("├───┐    │                     │                     │    ┌───┤");//3 (not incl corner)
    System.out.println("│   │    │                     │                     │    │   │");//center
    System.out.println("├───┘    │                     │                     │    └───┤");
    System.out.println("│        │                     │                     │        │");
    System.out.println("├────────┘                     │                     └────────┤");
    System.out.println("│                              │                              │");
    System.out.println("│                              │                              │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
    System.out.print(ANSI_RESET);
  }


//end of Fields


  public static int error(Positions[] opposers){
    int random = (int)(Math.random() * 11);
    for(int i = 0; i < opposers.length; i++){
      opposers[i].setError(random);
    }
    return random;
  }

  public static String a(Positions player){
    return player.getIcon();
  }
   

  public static Positions[] updatePositions(int ball, Positions[] players){
    for(int i = 0; i < players.length; i++){
      if(ball == i){
        players[i].setBall(true);
      }else{
        players[i].setBall(false);
      }
    }
    return players;
  }

  public static Positions[] buildTeam(boolean player){
    Positions[] players = new Positions[11];
    for(int i = 1; i <= 11; i++){
      players[i-1] = new Positions(i, player);
    }
    return players;
  }



}//end of class main


//possible arraylist of players as part of each team object?
//2D array of player coordinates?
//if(from formation number _) go to position number _ (11+) ???
//object class for each line of field???
/* original field:
    //31, then center line, then 31 (63 total)
    System.out.println("┌──────────────────────────────┬──────────────────────────────┐");//63X13
    System.out.println("│                              │                              │");
    System.out.println("│                              │                              │");
    System.out.println("├────────┐                     │                     ┌────────┤");//8 (not incl corner)
    System.out.println("│        │                     │                     │        │");
    System.out.println("├───┐    │                     │                     │    ┌───┤");//3 (not incl corner)
    System.out.println("│   │    │                     │                     │    │   │");//center
    System.out.println("├───┘    │                     │                     │    └───┤");
    System.out.println("│        │                     │                     │        │");
    System.out.println("├────────┘                     │                     └────────┤");
    System.out.println("│                              │                              │");
    System.out.println("│                              │                              │");
    System.out.println("└──────────────────────────────┴──────────────────────────────┘");
*/