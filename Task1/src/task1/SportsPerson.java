package task1;
// Define a class named SportsPerson
public class SportsPerson {

    private String name;
    private String sport;
    private String nation;

    // Define a constructor that takes four String parameters and initializes the instance variables
    public SportsPerson(String name, String sport, String nation){
      this.name = name;
      this.sport = sport;
      this.nation = nation;
    }
    // Define three getter methods to retrieve the values of the instance variables
    public String getName(){
      return name;
    }
    
    public String getSport(){
      return sport;
    }
 
    public String getNation(){
      return nation;
    }
}
