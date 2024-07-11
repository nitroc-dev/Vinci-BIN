import java.util.Objects;

public class Line {

  private int lineId;
  private String lineNumber;
  private Station origin;
  private Station destination;
  private TransportType transportType;
  private int waitingTime;

  public Line(int lineId, String lineNumber, Station origin, Station destination, TransportType transportType, int waitingTime) {
    this.lineId = lineId;
    this.lineNumber = lineNumber;
    this.origin = origin;
    this.destination = destination;
    this.transportType = transportType;
    this.waitingTime = waitingTime;
  }

  public int getLineId() {
    return lineId;
  }

  public String getLineNumber() {
    return lineNumber;
  }

  public Station getOrigin() {
    return origin;
  }

  public Station getDestination() {
    return destination;
  }

  public TransportType getTransportType() {
    return transportType;
  }

  public int getWaitingTime() {
    return waitingTime;
  }

  @Override
  public String toString() {
    return "Line{" + "lineId=" + lineId + ", lineNumber=" + lineNumber + ", origin=" + origin + ", destination=" + destination + ", transportType=" + transportType + ", waitingTime=" + waitingTime + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Line line = (Line) o;
    return lineId == line.lineId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(lineId);
  }
}
