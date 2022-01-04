# Dijkstra's Flight Planner ✈️ 
A **Java** implementation of Dijkstra's shortest path algorithm.
Given a list of airports and flights, it calculates the fastest path from a source airport to a destination airport.
- A `Planner` is initialized with LinkedLists of `Airport`s and `Flight`s.
- A `Planner`'s `Schedule(String start, String end, String departure)` method returns an `Itinerary` from a `start` airport to an `end` airport, starting at the time `departure`.
- If an itinerary is found, the `Itinerary`'s `print()` method will print a series of flights in the following format:  
`[StartAirport->TransitAirport:DepartureTime->ArrivalTime][TransitAirport->DestinationAirport:DepartureTime->ArrivalTime]`
- If no flight path exists, the `Itinerary`'s `print()` method will print `No Flight Schedule Found.`
- Military time is used, and it supports incorrect usage of military time such as `2430`(00:30 AM)
- This program assumes that the world shares a single time zone, and that no flight takes longer than 24 hours.
