package task1;

// Define a class named SportsTeam
public class SportsTeam {

    private String name;
    private String sport;
    private String nation;
    private String teamCaptain;

    // Define a constructor that takes four String parameters and initializes the instance variables
    public SportsTeam(String name, String sport, String nation, String teamCaptain){
      this.name = name;
      this.sport = sport;
      this.nation = nation;
      this.teamCaptain = teamCaptain;
    }
    // Define four getter methods to retrieve the values of the instance variables
    public String getName(){
      return name;
    }
    
    public String getSport(){
      return sport;
    }
 
    public String getNation(){
      return nation;
    }

    public String getCaptain(){
      return teamCaptain;
    }
}
