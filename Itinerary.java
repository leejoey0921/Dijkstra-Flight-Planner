import java.util.ArrayList;

public class Itinerary {

  ArrayList<Flight> path;  // list of flights in itinerary
  boolean isFound;  // true if itinerary is found
  int minsTaken;  // the total time of the itinerary in minutes

  // constructor
  Itinerary(boolean isFound) {
    this.isFound = isFound;
    this.path = new ArrayList<>();
  }

  public boolean isFound() {return isFound;}

  public void print() {
    if (isFound) {
      for (int i = path.size()-1; i > -1; i--) {
        Flight f = path.get(i);
        f.print();
      }
    } else {
      System.out.print("No Flight Schedule Found.");
    }
    System.out.println();
  }

}
