//This is a logic/behavior file where the program's action occurs. Think of this file as the engine that runs the application and manipulates the data.
// This file is dependent on the objects created in the Team.java to perform.

// These imports tell Java to go look inside the com.teamtreehouse.model package (folder) and bring in the Player and Players classes so I can reference them in this file.
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;

// These imports bring in the HasMap class and the Map interface. HashMap is an implementation of the Map interface. These imports both live in Java's tool kit.
import java.util.HashMap;
import java.util.Map;

//These imports bring in tools for reading input from the console (the keyboard). BufferedReader and InputStreamReader help convert and read what someone types, and IOException is an error type Java requires me to handle whenever I'm reading input, since something could go wrong.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.teamtreehouse.model.Team;
import java.util.ArrayList;
import java.util.List;

public class LeagueManager {

  public static void main(String[] args) {
    // Calls the pre-built Players.load() method to get the fixed, locked list of registered players as an array.
    Player[] players = Players.load();

    // This sets up the tool that will read what the organizer types into the console. System.in connects to the keyboard, InputStreamReader converts that raw input into readable characters, and BufferedReader wraps around that to let me read one whole line at a time using .readLine().
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Prints out how many players were loaded, using %d as a placeholder for the whole number (players.length).
    System.out.printf("There are currently %d registered players.%n", players.length);

    // Creates a new empty Map to hold the menu options. Each entry will pair a short command (the key) with a description (the value).
    Map<String, String> menu = new HashMap<>();
    menu.put("createTeam", "Create a new team");
    menu.put("addPlayer", "Add a player to a team");
    menu.put("quit", "Exit the program");

    // Creates an empty List to hold every Team object that gets created while the program runs.
    List<Team> teams = new ArrayList<>();

    // This variable will hold whatever the organizer types each time through the loop. Starts blank so the loop below has something to check against.
    String choice = "";

    // Keeps looping - showing the menu, reading input, and reacting to it - until the organizer types "quit".
    do {
      // Loops through every entry in the menu Map and prints each command alongside its description, so the organizer can see their options.
      for (Map.Entry<String, String> option : menu.entrySet()) {
        System.out.printf("%s - %s %n", option.getKey(), option.getValue());
      }
      System.out.print("What do you want to do:  ");

      try {
        // Reads the line the organizer typed and stores it in choice, trimming extra spaces and lowercasing it so matching is more forgiving.
        choice = reader.readLine().trim().toLowerCase();

        // Checks choice against each possible menu option and runs the matching code.
        switch (choice) {
          case "createteam":
            System.out.print("Enter the team name:  ");
            String teamName = reader.readLine();
            System.out.print("Enter the coach's name:  ");
            String coachName = reader.readLine();
            Team team = new Team(teamName, coachName);
            teams.add(team);
            System.out.printf("%s created!%n%n", teamName);
            break;
          case "quit":
            System.out.println("Goodbye!");
            break;
          default:
            System.out.printf("Unknown choice: '%s'. Try again.%n%n", choice);
        }
      } catch (IOException ioe) {
        System.out.println("Problem with input");
        ioe.printStackTrace();
      }//This is a logic/behavior file where the program's action occurs. Think of this file as the engine that runs the application and manipulates the data.
// This file is dependent on the objects created in the Team.java to perform.

// These imports tell Java to go look inside the com.teamtreehouse.model package (folder) and bring in the Player and Players classes so I can reference them in this file.
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;

// These imports bring in the HasMap class and the Map interface. HashMap is an implementation of the Map interface. These imports both live in Java's tool kit.
import java.util.HashMap;
import java.util.Map;

//These imports bring in tools for reading input from the console (the keyboard). BufferedReader and InputStreamReader help convert and read what someone types, and IOException is an error type Java requires me to handle whenever I'm reading input, since something could go wrong.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.teamtreehouse.model.Team;
import java.util.ArrayList;
import java.util.List;

      public class LeagueManager {

        public static void main(String[] args) {
          // Calls the pre-built Players.load() method to get the fixed, locked list of registered players as an array.
          Player[] players = Players.load();

          // This sets up the tool that will read what the organizer types into the console. System.in connects to the keyboard, InputStreamReader converts that raw input into readable characters, and BufferedReader wraps around that to let me read one whole line at a time using .readLine().
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

          // Prints out how many players were loaded, using %d as a placeholder for the whole number (players.length).
          System.out.printf("There are currently %d registered players.%n", players.length);

          // Creates a new empty Map to hold the menu options. Each entry will pair a short command (the key) with a description (the value).
          Map<String, String> menu = new HashMap<>();
          menu.put("createTeam", "Create a new team");
          menu.put("addPlayer", "Add a player to a team");
          menu.put("quit", "Exit the program");

          // Creates an empty List to hold every Team object that gets created while the program runs.
          List<Team> teams = new ArrayList<>();

          // This variable will hold whatever the organizer types each time through the loop. Starts blank so the loop below has something to check against.
          String choice = "";

          // Keeps looping - showing the menu, reading input, and reacting to it - until the organizer types "quit".
          do {
            // Loops through every entry in the menu Map and prints each command alongside its description, so the organizer can see their options.
            for (Map.Entry<String, String> option : menu.entrySet()) {
              System.out.printf("%s - %s %n", option.getKey(), option.getValue());
            }
            System.out.print("What do you want to do:  ");

            try {
              // Reads the line the organizer typed and stores it in choice, trimming extra spaces and lowercasing it so matching is more forgiving.
              choice = reader.readLine().trim().toLowerCase();

              // Checks choice against each possible menu option and runs the matching code.
              switch (choice) {
                case "createteam":
                  System.out.print("Enter the team name:  ");
                  String teamName = reader.readLine();
                  System.out.print("Enter the coach's name:  ");
                  String coachName = reader.readLine();
                  Team team = new Team(teamName, coachName);
                  teams.add(team);
                  System.out.printf("%s created!%n%n", teamName);
                  break;
                case "quit":
                  System.out.println("Goodbye!");
                  break;
                default:
                  System.out.printf("Unknown choice: '%s'. Try again.%n%n", choice);
              }
            } catch (IOException ioe) {
              System.out.println("Problem with input");
              ioe.printStackTrace();
            }
          } while (!choice.equals("quit"));
        }

      }



    } while (!choice.equals("quit"));
  }

  }


