# Dijkstra's Flight Planner ✈️ 
A JAVA implementation of Dijkstra's shortest path algorithm.
Given a list of airports and flights, it calculates the fastest path from a source airport to a destination airport.
- A `Planner` is initialized with LinkedLists of `Airport`s and `Flight`s.
- A `Planner`'s `Schedule(String start, String end, String departure)` method returns an `Itinerary` from a `start` airport to an `end` airport, starting at the time `departure`.
