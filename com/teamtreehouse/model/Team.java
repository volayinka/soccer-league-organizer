// This is a stand-alone, template file used by LeagueManager.java to create Team objects

 package com.teamtreehouse.model;
// Tells Java the folder group of where the organized classes are stored

// I am creating a new type of thing called "Team" accessible from anywhere, hence the public class, to be used as a blueprint. Anything belonging to "Team" starts in this class.
 public class Team {

    // Here I established two blank spots to be filled in. These are private because only code inside the Team class should be allowed to touch these spots. The String before the variable name means this spot holds text.
    private String teamName;
    private String coachName;

    //This is a constructor that runs when a newTeam object is created, takes the value, and stores it in the spot created previously.
    // "this.teamName" refers to this specific Team's own spot, while "teamName" on the right is the value that was passed in
    public Team(String teamName, String coachName) {
        this.teamName = teamName;
        this.coachName = coachName;
    }
    // Here I established getter methods that retrieve the teamName and coachName values via the "return" keyword, since those private spots cannot be accessed directly from outside the class.
    public String getTeamName() {
        return teamName;
    }

    public String getCoachName() {
        return coachName;
    }
}
