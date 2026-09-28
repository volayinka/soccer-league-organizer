//This is a system/data file that holds facts about a team. This file does not do anything on its own.
// This is a stand-alone, template file used by LeagueManager.java to create Team objects

// Tells Java the folder group of where the organized classes are stored
package com.teamtreehouse.model;

// These imports bring in the Set interface and its HashSet implementation, used to hold the team's players without duplicates.
import java.util.HashSet;
import java.util.Set;

// I am creating a new type of thing called "Team" accessible from anywhere, hence the public class, to be used as a blueprint. Anything belonging to "Team" starts in this class.
public class Team {

    // Here I established three blank spots to be filled in. These are private because only code inside the Team class should be allowed to touch these spots. The String before the variable name means this spot holds text. The Set<Player> spot holds the team's players, and a Set does not allow duplicates.
    private String teamName;
    private String coachName;
    private Set<Player> players;

    //This is a constructor that runs when a new Team object is created, takes the values, and stores them in the spots created previously.
    // "this.teamName" refers to this specific Team's own spot, while "teamName" on the right is the value that was passed in
    public Team(String teamName, String coachName) {
        this.teamName = teamName;
        this.coachName = coachName;
        // Starts each new team with an empty set of players.
        players = new HashSet<>();
    }

    // Here I established getter methods that retrieve the teamName and coachName values via the "return" keyword, since those private spots cannot be accessed directly from outside the class.
    public String getTeamName() {
        return teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    // Getter that lets outside code see the team's players.
    public Set<Player> getPlayers() {
        return players;
    }
}