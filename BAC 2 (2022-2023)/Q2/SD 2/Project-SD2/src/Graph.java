import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

  private final Map<String, Station> stations; // List of stations with their id as key
  private final Map<Integer, Line> lignes; // List of lines with their id as key
  private final Map<Station, Set<Troncon>> listDAdjacences; // List of troncons with their origin as key


  public Graph(File lignes, File troncons) throws IOException {
    this.stations = new HashMap<>();
    this.lignes = new HashMap<>();

    for (Line line : readLineFile(lignes)) {
      this.lignes.put(line.getLineId(), line);
    }

    for (Troncon troncon : readTronconFile(troncons)) {
      this.stations.put(troncon.getOrigin().getNom(), troncon.getOrigin());
      this.stations.put(troncon.getDestination().getNom(), troncon.getDestination());
    }

    this.listDAdjacences = new HashMap<>();
    for (Troncon troncon : readTronconFile(troncons)) {
      if (this.listDAdjacences.containsKey(troncon.getOrigin())) {
        this.listDAdjacences.get(troncon.getOrigin()).add(troncon);
      } else {
        HashSet<Troncon> hashSet = new HashSet<>();
        this.listDAdjacences.put(troncon.getOrigin(), hashSet);
        this.listDAdjacences.get(troncon.getOrigin()).add(troncon);
      }
    }
  }


  public void calculerCheminMinimisantNombreTroncons(String origine, String destination) {
    Station origineStation = stations.get(origine);
    Station destinationStation = stations.get(destination);

    Map<Station, Troncon> troncons = bfs(origineStation, destinationStation);
    printPath(destinationStation, origineStation, troncons);
  }

  public void calculerCheminMinimisantTempsTransport(String origine, String destination) {
    Station origineStation = stations.get(origine);
    Station destinationStation = stations.get(destination);

    Map<Station, Troncon> itineraire = dijkstra(origineStation, destinationStation);
    printPath(destinationStation, origineStation, itineraire);
  }

  public List<Line> readLineFile(File file) throws IOException {
    List<Line> lineList = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;
    while ((line = br.readLine()) != null) {
      String[] parts = line.split(",");
      int lineId = Integer.parseInt(parts[0].trim());
      String lineNumber = parts[1].trim();
      Station origin = new Station(parts[2].trim());
      Station destination = new Station(parts[3].trim());
      TransportType transportType = TransportType.valueOf(parts[4].trim().toUpperCase());
      int waitingTime = Integer.parseInt(parts[5].trim());
      Line lineToAdd = new Line(lineId, lineNumber, origin, destination, transportType,
          waitingTime);
      lineList.add(lineToAdd);
    }
    br.close();
    return lineList;
  }

  public Set<Troncon> readTronconFile(File file) throws IOException {
    Set<Troncon> tronconList = new HashSet<>();
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;
    while ((line = br.readLine()) != null) {
      String[] parts = line.split(",");
      int lineId = Integer.parseInt(parts[0].trim());
      Station origin = new Station(parts[1].trim());
      Station destination = new Station(parts[2].trim());
      int duration = Integer.parseInt(parts[3].trim());
      Troncon tronconToAdd = new Troncon(lineId, origin, destination, duration);
      tronconList.add(tronconToAdd);
    }
    br.close();
    return tronconList;
  }

  private Map<Station, Troncon> bfs(Station origine, Station destination) {
    Deque<Station> queue = new ArrayDeque<>();
    Set<Station> visited = new HashSet<>();
    Map<Station, Troncon> paths = new HashMap<>();

    Station current;

    queue.add(origine);
    visited.add(origine);

    while (!visited.contains(destination) && !queue.isEmpty()) {
      current = queue.removeFirst();
      Set<Troncon> troncons = listDAdjacences.get(current);
      for (Troncon troncon : troncons) {
        if (!visited.contains(troncon.getDestination())) {
          queue.add(troncon.getDestination());
          visited.add(troncon.getDestination());
          paths.put(troncon.getDestination(), troncon);
        }
      }
    }
    return paths;
  }

  private Map<Station, Troncon> dijkstra(Station source, Station destination) {
    Map<Station, Integer> provisionalLabels = new HashMap<>();
    Map<Station, Integer> finalLabels = new HashMap<>();
    Map<Station, Troncon> paths = new HashMap<>();

    provisionalLabels.put(source, 0);

    Station current = source;
    while (!finalLabels.containsKey(destination)) {
      int min = Integer.MAX_VALUE;
      for (Station s : provisionalLabels.keySet()) {
        if (provisionalLabels.get(s) < min) {
          min = provisionalLabels.get(s);
          current = s;
        }
      }

      provisionalLabels.remove(current);
      finalLabels.put(current, min);

      Set<Troncon> troncons = listDAdjacences.get(current);
      for (Troncon troncon : troncons) {
        int distance = min + troncon.getDuration();
        if ((provisionalLabels.get(troncon.getDestination()) == null || distance < provisionalLabels.get(troncon.getDestination()))
            && !finalLabels.containsKey(troncon.getDestination())) {
          paths.put(troncon.getDestination(), troncon);
          provisionalLabels.put(troncon.getDestination(), distance);
        }
      }
    }

    return paths;
  }

  public void printPath(Station arrive, Station depart, Map<Station, Troncon> chemins) {
    Station current = arrive;
    Deque<Troncon> arrayDeque1 = new ArrayDeque<>();
    while (!current.equals(depart)) {
      Troncon troncon = chemins.get(current);
      arrayDeque1.addFirst(troncon);
      current = chemins.get(current).getOrigin();
    }
    int dureeTransport = 0;
    int dureeTotale = 0;
    int currentLine = -1;
    for (Troncon troncon : arrayDeque1) {
      dureeTransport += troncon.getDuration();
      if(currentLine != troncon.getLineId()){
        currentLine = troncon.getLineId();
        dureeTotale += lignes.get(troncon.getLineId()).getWaitingTime();
      }
      System.out.println(troncon);
    }

    dureeTotale += dureeTransport;
    System.out.println("Duree du transport : " + dureeTransport);
    System.out.println("Duree totale : " + dureeTotale);
  }
}
