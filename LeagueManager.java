//This is a logic/behavior file where the program's action occurs. This file is the engine that runs the application and manipulates the data.
// This file is dependent on the objects created in the Team.java to perform.

// These imports tell Java to go look inside the com.teamtreehouse.model package (folder) and bring in the Player, Players and Team classes so I can reference them in this file.

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// These imports bring in the HashMap class and the Map interface. HashMap is an implementation of the Map interface. These imports both live in Java's tool kit.
//These imports bring in tools for reading input from the console (the keyboard). BufferedReader and InputStreamReader help convert and read what someone types, and IOException is an error type Java requires me to handle whenever I'm reading input, since something could go wrong.
// These imports bring in ArrayList and the List interface, used to hold all the teams.

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
        menu.put("removePlayer", "Remove a player from a team");
        menu.put("roster", "Print a team roster");
        menu.put("heightreport", "View a team height report");
        menu.put("balancereport", "View the league balance report");


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
                    case "addplayer":
                        Team chosen = promptForTeam(teams, reader);
                        if (chosen == null) {
                            break;
                        }
                        // Enforces the 11-player maximum before showing any players.
                        if (chosen.getPlayers().size() >= 11) {
                            System.out.printf("%s is full (11 players).%n%n", chosen.getTeamName());
                            break;
                        }
                        Player chosenPlayer = promptForPlayer(players, teams, reader);
                        if (chosenPlayer == null) {
                            break;
                        }
                        chosen.getPlayers().add(chosenPlayer);
                        System.out.printf("%s %s added to %s!%n%n",
                                chosenPlayer.getFirstName(), chosenPlayer.getLastName(), chosen.getTeamName());
                        break;
                    case "removeplayer":
                        Team fromTeam = promptForTeam(teams, reader);
                        if (fromTeam == null) {
                            break;
                        }
                        Player toRemove = promptForTeamPlayer(fromTeam, reader);
                        if (toRemove == null) {
                            break;
                        }
                        fromTeam.getPlayers().remove(toRemove);
                        System.out.printf("%s %s removed from %s.%n%n",
                                toRemove.getFirstName(), toRemove.getLastName(), fromTeam.getTeamName());
                        break;
                    case "roster":
                        Team rosterTeam = promptForTeam(teams, reader);
                        if (rosterTeam == null) {
                            break;
                        }
                        printRoster(rosterTeam);
                        break;
                    case "heightreport":
                        Team heightTeam = promptForTeam(teams, reader);
                        if (heightTeam == null) {
                            break;
                        }
                        printHeightReport(heightTeam);
                        break;
                    case "balancereport":
                        printBalanceReport(teams);
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

    // Shows the players on one team in alphabetical order and returns the one the organizer picks. Returns null if the team is empty or the pick isn't valid.
    private static Player promptForTeamPlayer(Team team, BufferedReader reader) throws IOException {
        if (team.getPlayers().isEmpty()) {
            System.out.println("That team has no players yet.");
            return null;
        }
        List<Player> onTeam = new ArrayList<>(team.getPlayers());
        Collections.sort(onTeam);
        for (int i = 0; i < onTeam.size(); i++) {
            Player p = onTeam.get(i);
            System.out.printf("%d.) %s %s (%d inches, experienced: %s)%n",
                    i + 1, p.getFirstName(), p.getLastName(), p.getHeightInInches(),
                    p.isPreviousExperience() ? "yes" : "no");
        }
        System.out.print("Pick a player number to remove:  ");
        try {
            int number = Integer.parseInt(reader.readLine().trim());
            if (number < 1 || number > onTeam.size()) {
                System.out.println("That number isn't on the list.");
                return null;
            }
            return onTeam.get(number - 1);
        } catch (NumberFormatException nfe) {
            System.out.println("Please type a number.");
            return null;
        }
    }

    // Sorts the teams alphabetically by name, prints them as a numbered list, and returns the one the organizer picks. Returns null if there are no teams or the pick isn't valid.
    private static Team promptForTeam(List<Team> teams, BufferedReader reader) throws IOException {
        // Guards against the empty list that caused the crash.
        if (teams.isEmpty()) {
            System.out.println("There are no teams yet. Create a team first.");
            return null;
        }
        List<Team> sortedTeams = new ArrayList<>(teams);
        sortedTeams.sort((a, b) -> a.getTeamName().compareToIgnoreCase(b.getTeamName()));
        for (int i = 0; i < sortedTeams.size(); i++) {
            System.out.printf("%d.) %s (coach: %s)%n", i + 1, sortedTeams.get(i).getTeamName(), sortedTeams.get(i).getCoachName());
        }
        System.out.print("Pick a team number:  ");
        try {
            int number = Integer.parseInt(reader.readLine().trim());
            if (number < 1 || number > sortedTeams.size()) {
                System.out.println("That number isn't on the list.");
                return null;
            }
            return sortedTeams.get(number - 1);
        } catch (NumberFormatException nfe) {
            System.out.println("Please type a number.");
            return null;
        }
    }

    // Builds an alphabetical numbered list of players not yet on any team, and returns the one the organizer picks. Returns null if none are available or the pick isn't valid.
    private static Player promptForPlayer(Player[] players, List<Team> teams, BufferedReader reader) throws IOException {
        List<Player> available = new ArrayList<>();
        for (Player player : players) {
            boolean taken = false;
            for (Team team : teams) {
                if (team.getPlayers().contains(player)) {
                    taken = true;
                }
            }
            if (!taken) {
                available.add(player);
            }
        }
        if (available.isEmpty()) {
            System.out.println("Every player is already on a team.");
            return null;
        }
        // Uses the compareTo you finished in Player.java (last name, then first name).
        Collections.sort(available);
        for (int i = 0; i < available.size(); i++) {
            Player p = available.get(i);
            System.out.printf("%d.) %s %s (%d inches, experienced: %s)%n",
                    i + 1, p.getFirstName(), p.getLastName(), p.getHeightInInches(),
                    p.isPreviousExperience() ? "yes" : "no");
        }
        System.out.print("Pick a player number:  ");
        try {
            int number = Integer.parseInt(reader.readLine().trim());
            if (number < 1 || number > available.size()) {
                System.out.println("That number isn't on the list.");
                return null;
            }
            return available.get(number - 1);
        } catch (NumberFormatException nfe) {
            System.out.println("Please type a number.");
            return null;
        }
    }

    // Prints every player on one team, alphabetically, with their stats.
    private static void printRoster(Team team) {
        System.out.printf("%n%s roster (coach: %s)%n", team.getTeamName(), team.getCoachName());
        if (team.getPlayers().isEmpty()) {
            System.out.println("No players yet.");
            return;
        }
        List<Player> roster = new ArrayList<>(team.getPlayers());
        Collections.sort(roster);
        for (Player p : roster) {
            System.out.printf("%s %s - %d inches - experienced: %s%n",
                    p.getFirstName(), p.getLastName(), p.getHeightInInches(),
                    p.isPreviousExperience() ? "yes" : "no");
        }

        System.out.println();
    }

    // Groups a team's players into 5-inch height ranges and prints a count for each range.
    private static void printHeightReport(Team team) {
        System.out.printf("%n%s height report%n", team.getTeamName());
        if (team.getPlayers().isEmpty()) {
            System.out.println("No players yet.");
            return;
        }
        // Each range's label maps to how many players fall in it.
        Map<String, Integer> ranges = new TreeMap<>();
        for (Player p : team.getPlayers()) {
            int height = p.getHeightInInches();
            // Rounds down to the nearest 5, so 42 -> "40-44", 47 -> "45-49".
            int rangeStart = (height / 5) * 5;
            String label = rangeStart + "-" + (rangeStart + 4);
            ranges.put(label, ranges.getOrDefault(label, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : ranges.entrySet()) {
            System.out.printf("%s inches: %d player(s)%n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    // For every team, counts experienced vs. inexperienced players and prints the totals.
    private static void printBalanceReport(List<Team> teams) {
        System.out.println();
        if (teams.isEmpty()) {
            System.out.println("There are no teams yet.");
            return;
        }
        List<Team> sortedTeams = new ArrayList<>(teams);
        sortedTeams.sort((a, b) -> a.getTeamName().compareToIgnoreCase(b.getTeamName()));
        for (Team t : sortedTeams) {
            int experienced = 0;
            int inexperienced = 0;
            for (Player p : t.getPlayers()) {
                if (p.isPreviousExperience()) {
                    experienced++;
                } else {
                    inexperienced++;
                }
            }
            System.out.printf("%s - experienced: %d, inexperienced: %d%n", t.getTeamName(), experienced, inexperienced);
        }
        System.out.println();
    }

}