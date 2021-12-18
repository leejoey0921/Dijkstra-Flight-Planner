import java.util.*;

public class Planner {

  LinkedList<Airport> portList;  // list of all airports
  LinkedList<Flight> fltList;  // list of all flights
  HashMap<String, Airport> portMap = new HashMap<>();  // Map to access an airport with its name

  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {

    // initializing portMap
    Iterator<Airport> itPort = portList.iterator();
    while (itPort.hasNext()) {
      Airport port = itPort.next();
      this.portMap.putIfAbsent(port.port, port);
    }

    // adding all flights to its departing airport
    Iterator<Flight> itFlt = fltList.iterator();
    while (itFlt.hasNext()) {
      Flight flt = itFlt.next();
      Airport port = portMap.get(flt.src);
      if (port != null) {
        port.flights.putIfAbsent(flt.dest, new ArrayList<Flight>());
        port.flights.get(flt.dest).add(flt);
      }
    }
    this.portList = portList;
    this.fltList = fltList;
  }


  // Schedule a fastest itinerary from {start} to {end}, with minimum departure time {departure}
  public Itinerary Schedule(String start, String end, String departure) {
    Itinerary ticket = new Itinerary(true);
    Airport startPort = portMap.get(start);  // source port of itinerary
    Airport endPort = portMap.get(end);  // destination port of itinerary
    Time startTime = new Time(departure);
    PriorityQueue<Airport> portQueue = new PriorityQueue<>(new AirportComparator());

    // invalid airport name
    if (startPort == null || endPort == null) {
      ticket.isFound = false;
      return ticket;
    }

    // initialization
    setPorts(startPort, portQueue);

    // first iteration
    firstDijkstraIt(startPort, startTime, portQueue);

    // next iterations
    while (!portQueue.isEmpty() && portQueue.peek().elapsed_time < Integer.MAX_VALUE) {
      Airport departPort = portQueue.poll();

      if (departPort.visited) { continue; }
      departPort.visited = true;

      Time currentTime = Time.add(startTime, new Time(0, departPort.elapsed_time)); // when it arrived here
      Time minDeptTime = Time.add(currentTime, departPort.connectTime); // earliest time it can depart

      dijkstraIt(departPort, currentTime, minDeptTime, portQueue);
    }

    // print path to ticket
    setTicket(endPort, ticket);

    return ticket;
  }


  // initialize values of all Airports and portQueue
  private void setPorts(Airport startPort, PriorityQueue<Airport> portQueue) {
    Iterator<Airport> itPort = portList.iterator();
    while (itPort.hasNext()) {
      Airport port = itPort.next();
      if (port.equals(startPort)) { continue; }
      port.prev = null;
      port.elapsed_time = Integer.MAX_VALUE;
      port.visited = false;
      portQueue.add(port);
    }
    startPort.prev = null;
    startPort.elapsed_time = 0;
    startPort.visited = true;
  }


  // first iteration of Dijkstra's algorithm
  private void firstDijkstraIt(Airport startPort, Time startTime, PriorityQueue<Airport> portQueue) {
    Iterator<String> keys = startPort.flights.keySet().iterator();
    while (keys.hasNext()) {
      String key = keys.next();
      ArrayList<Flight> flightsTo = startPort.flights.get(key);
      Airport destPort = portMap.get(key);

      for (int j = 0; j < flightsTo.size(); j++) {
        Flight f = flightsTo.get(j);

        int elapsed_time = Time.elapsed(startTime, f.stime) + Time.elapsed(f.stime, f.dtime);

        if (elapsed_time < destPort.elapsed_time) {
          destPort.elapsed_time = elapsed_time;
          destPort.prev = f;
          portQueue.remove(destPort);
          portQueue.add(destPort);
        }
      }
    }
  }


  // next iterations of Dijkstra's algorithm
  private void dijkstraIt(Airport departPort, Time currentTime, Time minDeptTime, PriorityQueue<Airport> portQueue) {
    Iterator<String> keys = departPort.flights.keySet().iterator();
    while (keys.hasNext()) {
      String key = keys.next();
      ArrayList<Flight> flightsTo = departPort.flights.get(key);
      Airport destPort = portMap.get(key);

      for (int j = 0; j < flightsTo.size(); j++) {
        Flight f = flightsTo.get(j);
        int elapsed_time = departPort.elapsed_time +
                Time.elapsed(currentTime, minDeptTime) +
                Time.elapsed(minDeptTime, f.stime) +
                Time.elapsed(f.stime, f.dtime);
        if (elapsed_time < destPort.elapsed_time) {
          destPort.elapsed_time = elapsed_time;
          destPort.prev = f;
          portQueue.remove(destPort);
          portQueue.add(destPort);
        }
      }
    }
  }


  // print the resulting shortest path to the ticket
  private void setTicket(Airport endPort, Itinerary ticket) {
    Flight path = endPort.prev;
    while (path != null) {
      Airport prevPort = portMap.get(path.src);
      ticket.path.add(path);
      path = prevPort.prev;
    }
    if (!endPort.visited) { ticket.isFound = false; }
    ticket.minsTaken = endPort.elapsed_time;
  }
}



