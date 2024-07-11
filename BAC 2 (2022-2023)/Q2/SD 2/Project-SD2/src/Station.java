import java.util.Objects;

public class Station {
  String nom;

  public Station(String nom){
    this.nom = nom;
  }

  public String getNom() {
    return nom;
  }

  @Override
  public String toString() {
    return nom;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Station other = (Station) obj;
    if (!Objects.equals(this.nom, other.nom)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (this.nom != null ? this.nom.hashCode() : 0);
    return hash;
  }
}
