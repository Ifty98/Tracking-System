package task1;
// Define a class named SportsAward
public class SportsAward {

  private String year;
  private SportsPerson winner;
  private SportsPerson second;
  private SportsPerson third;
  private SportsTeam sportsTeam;
  // Define a constructor that takes four String parameters and initializes the instance variables
  public SportsAward(String year, SportsPerson winner, SportsPerson second, SportsPerson third, SportsTeam sportsTeam){
    this.year = year;
    this.winner = winner;
    this.second = second;
    this.third = third;
    this.sportsTeam = sportsTeam;
  }
  // Define a constructor that takes five parameters and initializes the instance variables
  public String getYear(){
    return year;
  }

  public SportsPerson getWinner(){
    return winner;
  }

  public SportsPerson getSecond(){
    return second;
  }

  public SportsPerson getThird(){
    return third;
  }

  public SportsTeam getTeam(){
    return sportsTeam;
  }

  @Override
  public String toString() {
      return "SportsAward{" + "year='" + year + '\'' + ", winner=" + winner + ", second=" + second + ", third=" + third + ", sportsTeam=" + sportsTeam + '}';
  }
}

