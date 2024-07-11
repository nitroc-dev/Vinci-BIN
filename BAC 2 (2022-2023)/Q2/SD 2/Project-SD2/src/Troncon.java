import java.util.Objects;

public class Troncon {

  private int lineId;
  private Station  origin;
  private Station  destination;
  private int duration;

  public Troncon(int lineId, Station  origin, Station destination, int duration) {
    this.lineId = lineId;
    this.origin = origin;
    this.destination = destination;
    this.duration = duration;
  }

  public int getLineId() {
    return lineId;
  }

  public Station  getOrigin() {
    return origin;
  }

  public Station getDestination() {
    return destination;
  }

  public int getDuration() {
    return duration;
  }

  @Override
  public String toString() {
    return "Troncon{" + "lineId=" + lineId + ", origin=" + origin + ", destination=" + destination + ", duration=" + duration + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Troncon troncon = (Troncon) o;
    return lineId == troncon.lineId && duration == troncon.duration && Objects.equals(
        origin, troncon.origin) && Objects.equals(destination, troncon.destination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lineId, origin, destination, duration);
  }
}
