import java.util.*;

public class Airport
{

  String port;  // Airport name
  Time connectTime;  // Minimum time needed to transit
  HashMap<String, ArrayList<Flight>> flights;  // Stores all outbound flights

  Flight prev;  // The previous flight on the shortest path
  int elapsed_time;  // Time elapsed from the start time (from source airport)
  boolean visited;  // true if traversed during Dijkstra's algorithm iterations

  // constructor
  public Airport(String port, String connectTime) {
    this.port = port;
    this.connectTime = new Time(connectTime);
    this.flights = new HashMap<>();
    this.prev = null;
    this.elapsed_time = Integer.MAX_VALUE;
    this.visited = false;
  }
}

class AirportComparator implements Comparator<Airport> {
  @Override
  public int compare(Airport a1, Airport a2) {
    return a1.elapsed_time - a2.elapsed_time;
  }
}