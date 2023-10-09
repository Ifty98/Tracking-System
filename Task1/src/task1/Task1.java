package task1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;


public class Task1 {

    //method to read the file and create objects to store all the sportsaward entries info
    public static SportsAward[] getInfo(){
      SportsAward[] history = new SportsAward[27];
      int i = 0;
      try {
      File sportFile = new File("sports-personalities.txt");
      Scanner line = new Scanner(sportFile);
      //reads file line by line
      while (line.hasNextLine()) {
          String data = line.nextLine();
          //split each line into an array of strings based on the '|' character
          String[] container = data.split("\\|");
          String year = container[0];
          String dat1 = container[1];
          String dat2 = container[2];
          String dat3 = container[3];
          String dat4 = container[4];
          //get data for the winner
          String[] winnerData = dat1.split(",");
          String[] winnerName = winnerData[0].split("\\(");
          String winnerNation = winnerData[0].substring(winnerData[0].indexOf("(")+1, winnerData[0].indexOf(")"));
          SportsPerson winner = new SportsPerson(winnerName[0], winnerData[1], winnerNation);
          //get data for the second
          String[] secondData = dat2.split(",");
          String[] secondName = secondData[0].split("\\(");
          String secondNation = secondData[0].substring(secondData[0].indexOf("(")+1, secondData[0].indexOf(")"));
          SportsPerson second = new SportsPerson(secondName[0], secondData[1], secondNation);
          //get data for the third
          String[] thirdData = dat3.split(",");
          String[] thirdName = thirdData[0].split("\\(");
          String thirdNation = thirdData[0].substring(thirdData[0].indexOf("(")+1, thirdData[0].indexOf(")"));
          SportsPerson third = new SportsPerson(thirdName[0], thirdData[1], thirdNation);
          //get data for the sport team
          String[] teamData = dat4.split(",");
          String[] teamName = teamData[0].split("\\(");
          String teamNation = teamData[0].substring(teamData[0].indexOf("(")+1, teamData[0].indexOf(")"));
          SportsTeam sportsTeam = new SportsTeam(teamName[0], teamData[1], teamNation, teamData[2]);
          //create a new SportsAward object with the extracted data
          SportsAward record = new SportsAward(year, winner, second, third, sportsTeam);
          //adds this object to the array
          history[i] = record;
          i++;
      }
      line.close();
      //returns the array of objects
      return history;
    } catch (FileNotFoundException e) {
      //print a message if the file is not found
      System.out.println("File not found!!!");
      e.printStackTrace();
      return history;
    }

    }   

    //creates  a scanner and ask to the user for integer input
    public static int optSelector() {
      Scanner input = new Scanner(System.in);
      System.out.print("Select one option >> ");
      
      try {
        //if the entered input is 1, 2, 3 or 0 then returns the input
        int selectedOpt = input.nextInt();
        if (selectedOpt == 1 || selectedOpt == 2 || selectedOpt == 3 || selectedOpt == 0) {
         return selectedOpt;
        }
        else {
         //if not returns number 4
         System.out.println("Incorrect input. Please try again!!");
         return 4;
        }
      } catch (Exception e) {
      //if the input is not an integer then displays an error message
      System.out.println("Incorrect input. Please try again!!");
      return 4;
      }         
    }

    //creates  a scanner and ask to the user for integer input to select a year
    public static int yearSelector() {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter year of award >> ");
      
      try {
       //if the selected year is between 1995 and 2021 then returns the year
        int selectedOpt = input.nextInt();
        if (selectedOpt < 1995 || selectedOpt > 2021) {
         System.out.println("Incorrect year. Please try again!!");
         return 4;
        }
        else {
         return selectedOpt;
        }
      } catch (Exception e) {
      //if the input is not an integer then displays an error message
      System.out.println("Incorrect year. Please try again!!");
      return 4;
      }         
    }

    //creates  a scanner and ask to the user for integer input
    public static int sortSelector() {
      Scanner input =  new Scanner(System.in);
      System.out.print("Enter choice >> ");
      
      try {
        int selectedOpt = input.nextInt();
        if (selectedOpt == 1 || selectedOpt == 2 || selectedOpt == 0) {
         return selectedOpt; 
        }
        else {
         System.out.println("Incorrect input. Please try again!!");
         return 4;
        }
      } catch (Exception e) {
      System.out.println("Incorrect input. Please try again!!");
      return 4;
      }     
    }

    //takes an array of SportsAward objects and stores the names of all sport names in an array
    public static String[] allSports(SportsAward[] history) {
      HashSet<String> allSports = new HashSet<>();
      
      for (int i = 0; i < history.length; i++) {
        allSports.add(history[i].getWinner().getSport());
        allSports.add(history[i].getSecond().getSport());
        allSports.add(history[i].getThird().getSport());
      }
      
      String[] finalSports = allSports.toArray(new String[allSports.size()]);
      return finalSports;
    }

    //returns a hashmap with the number of wins of each sport
    public static HashMap<String, ArrayList<Integer>> sportsWins(String[] finalSports, SportsAward[] history) {
      //set a hashmap using the sport name as a key and storing the wins inside an array list
      HashMap<String, ArrayList<Integer>> getWins = new HashMap<>(); 
      
      for (int i = 0; i < finalSports.length; i++) {
        int first = 0;
        int second = 0;
        int third = 0;
        for (int k = 0; k < history.length; k++) {
          if (finalSports[i].equals(history[k].getWinner().getSport())) {
             first += 1;
          }
          if (finalSports[i].equals(history[k].getSecond().getSport())) {
             second++;
          }
          if (finalSports[i].equals(history[k].getThird().getSport())) {
             third++;
          }
        }
        ArrayList<Integer> wins = new ArrayList<>();
        wins.add(first);
        wins.add(second);
        wins.add(third);
        getWins.put(finalSports[i], wins); 
      }
      return getWins;
    }

    //takes an array of SportsAward objects and stores the names of all sport person in an array
    public static String[] allNames(SportsAward[] history) {
      String[] names = new String[history.length];
      HashSet<String> allNames = new HashSet<>();
      
      for (int i = 0; i < history.length; i++) {
        allNames.add(history[i].getWinner().getName());
        allNames.add(history[i].getSecond().getName());
        allNames.add(history[i].getThird().getName());
      }
      
      String[] finalNames = allNames.toArray(new String[allNames.size()]);
      return finalNames;
    }

    //returns a hashmap with the number of wins of each person
    public static HashMap<String, ArrayList<Integer>> namesWins(String[] finalNames, SportsAward[] history) {
      //set a hashmap using the person name as a key and storing the wins inside an array list
      HashMap<String, ArrayList<Integer>> getWins = new HashMap<>(); 
      
      for (int i = 0; i < finalNames.length; i++) {
        int first = 0;
        int second = 0;
        int third = 0;
        for (int k = 0; k < history.length; k++) {
          if (finalNames[i].equals(history[k].getWinner().getName())) {
             first += 1;
          }
          if (finalNames[i].equals(history[k].getSecond().getName())) {
             second++;
          }
          if (finalNames[i].equals(history[k].getThird().getName())) {
             third++;
          }
        }
        ArrayList<Integer> wins = new ArrayList<>();
        wins.add(first);
        wins.add(second);
        wins.add(third);
        getWins.put(finalNames[i], wins); 
      }
      return getWins;
    }

    public static void main(String[] args) {
      int sortNumber = -1;
      do {
      //displays the main menu 
      System.out.println("----------------------");
      System.out.println("Sports award menu");
      System.out.println("----------------------");
      System.out.println("List ................1");
      System.out.println("Select ..............2");
      System.out.println("Sort.................3");
      System.out.println("Exit.................0");
      System.out.println("----------------------");
      //calls a method to ask to the user to select an option
      int optNumber = optSelector();
      //while input is not valid keeps calling this method
      while (optNumber == 4) {
        optNumber = optSelector();
      }
      //depending on user selection the program displays the following
      switch (optNumber) {
        //if the selected option is 0 then displays a message and exits the program
        case 0:
          System.out.println("Sport system exited successfuly!!");
          sortNumber = -1;
          break;
        //if the selected option is 1 then displays a table for all sport awards entries (year, winner, team)
        case 1:
          System.out.printf("-----------------------------------------------------------------------------------------------%n");
          System.out.printf("|  %-10s  |  %-30s  | %-40s  |%n", "Year", "Individual Award", "Team Award");
          System.out.printf("-----------------------------------------------------------------------------------------------%n");
          SportsAward[] history = getInfo();

          for (int i = 0; i < history.length; i++) {
             System.out.printf("|  %-10s  |  %-30s  | %-40s  |%n", history[i].getYear(), history[i].getWinner().getName(), history[i].getTeam().getName());
             System.out.printf("-----------------------------------------------------------------------------------------------%n");
          }
          sortNumber = -1;
          break;
        //if the selected option is 2 then asks the user to enter a year
        case 2:
          int yearNumber = yearSelector();
          while (yearNumber == 4) {
            yearNumber = yearSelector();  
          }
          history = getInfo();
          //checks the selected year in the array of SportAwards objects and if there is a match displays a table with all the selected year data
          for (int i = 0; i < history.length; i++) {
            int year = Integer.parseInt(history[i].getYear());
            if (year == yearNumber) {
              System.out.printf("-----------------------------------------------------------------------------------------------------------------------------%n");
              System.out.printf("|  %-84s  |  %-30s  |%n", "Individual Sports Person", "Team");
              System.out.printf("-----------------------------------------------------------------------------------------------------------------------------%n");
              System.out.printf("|  %-10s  |  %-30s  | %-15s  |  %-15s  |  %-30s  |%n", "Winner", history[i].getWinner().getName(), history[i].getWinner().getSport(), history[i].getWinner().getNation(), history[i].getTeam().getName());
              System.out.printf("|  %-10s  |  %-30s  | %-15s  |  %-15s  |  %-30s  |%n", "Second", history[i].getSecond().getName(), history[i].getSecond().getSport(), history[i].getSecond().getNation(), "Captain: " + history[i].getTeam().getCaptain());
              System.out.printf("|  %-10s  |  %-30s  | %-15s  |  %-15s  |  %-30s  |%n", "Third", history[i].getThird().getName(), history[i].getThird().getSport(), history[i].getThird().getNation(), history[i].getTeam().getNation());
              System.out.printf("-----------------------------------------------------------------------------------------------------------------------------%n");
            }
          }
          sortNumber = -1;
          break;
        //if the selected option is 3 then displays another menu with the sort options
        case 3:
          System.out.println("------------------------------------");
          System.out.println("Sort options");
          System.out.println("------------------------------------");
          System.out.println("Sort awards won by sport...........1");
          System.out.println("Sort awards won by sports person...2");
          System.out.println("Back to main menu..................0");
          System.out.println("------------------------------------");
          //asks user to select an option
          sortNumber = sortSelector();
          
          while (sortNumber == 4) {
            sortNumber = sortSelector();
          }

          history = getInfo();
          //depending on the user sort selecting option the program do the following
          switch (sortNumber) {
            //if sort selection is 1 then displays a table with all the number of wins for each sport
            case 1:
              //method to get all sports name
              String[] finalSports = allSports(history);
              //method to get a hashmap with all sports number of wins
              HashMap<String, ArrayList<Integer>> getWins = sportsWins(finalSports, history);

              ArrayList<HashMap.Entry<String, ArrayList<Integer>>> sportsArray = new ArrayList<>(getWins.entrySet());

              //sort list in descending order
              Collections.sort(sportsArray, (a1, a2) -> {
                ArrayList<Integer> list1 = a1.getValue();
                ArrayList<Integer> list2 = a2.getValue();

                //compare first integers
                int compare = list2.get(0).compareTo(list1.get(0));
                if (compare != 0) {
                  return compare;
                }

                //if first integers are equal, compare second integers in descending order
                compare = list2.get(1).compareTo(list1.get(1));
                if (compare != 0) {
                  return compare;
                }

                //if second integers are equal, compare third integers in descending order
                compare = list2.get(2).compareTo(list1.get(2));
                return compare;
              });

              System.out.println("--------------------------------------------------------------------------------------");
              System.out.printf("|  %-17s  |  %-10s  | %-10s  |  %-10s  |  %-8s  |%n", "Sport", "1st Place(s)", "2nd Place(s)", "3rd Place(s)", "Total");
              System.out.println("--------------------------------------------------------------------------------------");
              //print sorted map entries
              for (HashMap.Entry<String, ArrayList<Integer>> entry : sportsArray) {
                System.out.printf("|  %-17s  |  %-10s    | %-10s    |  %-10s    |  %-8s  |%n", entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2), entry.getValue().get(0) + entry.getValue().get(1) + entry.getValue().get(2));
              }
              System.out.println("--------------------------------------------------------------------------------------");
              break;
            //if sort selection is 2 then displays a table with all the number of wins for each sport person
            case 2:
              //method to get all sport person name
              String[] finalNames = allNames(history);
              //method to get a hashmap with all sport person number of wins
              HashMap<String, ArrayList<Integer>> getWins2 = namesWins(finalNames, history);

              ArrayList<HashMap.Entry<String, ArrayList<Integer>>> namesArray = new ArrayList<>(getWins2.entrySet());

              //sort list in descending order
              Collections.sort(namesArray, (a1, a2) -> {
                ArrayList<Integer> list1 = a1.getValue();
                ArrayList<Integer> list2 = a2.getValue();

                //compare first integers
                int compare = list2.get(0).compareTo(list1.get(0));
                if (compare != 0) {
                  return compare;
                }

                //if first integers are equal, compare second integers in descending order
                compare = list2.get(1).compareTo(list1.get(1));
                if (compare != 0) {
                  return compare;
                }

                //if second integers are equal, compare third integers in descending order
                compare = list2.get(2).compareTo(list1.get(2));
                return compare;
              });

              System.out.println("-----------------------------------------------------------------------------------------");
              System.out.printf("|  %-20s  |  %-10s  | %-10s  |  %-10s  |  %-8s  |%n", "Name", "1st Place(s)", "2nd Place(s)", "3rd Place(s)", "Total");
              System.out.println("-----------------------------------------------------------------------------------------");
              //print sorted map entries
              for (HashMap.Entry<String, ArrayList<Integer>> entry : namesArray) {
                System.out.printf("|  %-20s  |  %-10s    | %-10s    |  %-10s    |  %-8s  |%n", entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2), entry.getValue().get(0) + entry.getValue().get(1) + entry.getValue().get(2));
              }
              System.out.println("-----------------------------------------------------------------------------------------");
              break;
          }
     
          break;
      } //if sort selection is 0 then the program goes back to the main menu
    } while(sortNumber == 0);
    } 
}
