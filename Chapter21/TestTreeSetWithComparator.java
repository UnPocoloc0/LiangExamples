import java.util.*;

public class TestTreeSetWithComparator {
public static void main(String[] args) {


  // Create a tree set for geometric objects using a comparator
  Set<GeometricObject> set = new TreeSet<>(new GeometricObjectComparator());
  
  set.add(new Rectangle(4, 5)); 
  set.add(new Circle(40)); 
  // tämä duplikaatti unohdetaan 
  set.add(new Circle(40)); 
  set.add(new Rectangle(4, 1));
  
  // Display geometric objects in the tree set
  System.out.println("A sorted set of geometric objects"); 
  
  // TreeSet:n ansiosta saadaan tulostettua järjestyksessä
  for (GeometricObject element : set)
  System.out.println("area = " + element.getArea());  }
}

// nämä luokat määritellään ilman julkista määrettä samaan tiedostoon
abstract class GeometricObject {
  public abstract double getArea();
}

class Rectangle extends GeometricObject {
  private double width;
  private double height;
  
  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }
  
  @Override
  public double getArea() {
    return width * height;
  }
}

class Circle extends GeometricObject {
  private double radius;
  
  public Circle(double radius) {
    this.radius = radius;
  }
  
  @Override
  public double getArea() {
    return Math.PI * radius * radius;
  }
}

class GeometricObjectComparator implements Comparator<GeometricObject> {
  
  @Override
  public int compare(GeometricObject o1, GeometricObject o2) {
    return Double.compare(o1.getArea(), o2.getArea());
  }
}  
/*
  1️⃣ YLÄTASON SUUNNITELMA
  
  Tavoite:
  
  Demonstroida TreeSetin käyttöä käyttäjän määrittelemällä Comparatorilla
  
  Lisätä GeometricObject-objekteja (Rectangle ja Circle)
  
  Näyttää, että:
  
  TreeSet ei salli duplikaatteja (verrataan Comparatorin mukaan)
  
  TreeSet järjestää objektit halutulla tavalla (tässä pinta-alan mukaan)
  
  2️⃣ KÄYTETYT RAKENTEET JA RAJAPINNAT
  Osa	Selitys
  GeometricObject	Abstrakti luokka, metodilla getArea()
  Rectangle, Circle	Perivät GeometricObject ja toteuttavat getArea()
  Set<GeometricObject>	Abstrakti tietotyyppi, joka ei salli duplikaatteja
  TreeSet<GeometricObject>	Järjestetty toteutus, vaatii Comparable tai Comparator
  GeometricObjectComparator	Määrittää järjestyksen TreeSetissä (pinta-alan mukaan)
  3️⃣ OHJELMAN KULKU ASKEL ASKELELTA
  
  TreeSetin luonti Comparatorilla
  
  Set<GeometricObject> set = new TreeSet<>(new GeometricObjectComparator());
  
  
  TreeSet käyttää compare()-metodia objekteja vertaillessa
  
  Tässä vertaillaan getArea()-arvoja
  
  Objektien lisääminen
  
  set.add(new Rectangle(4,5));  // area = 20
  set.add(new Circle(40));      // area ≈ 5026.55
  set.add(new Circle(40));      // duplikaatti, ei lisäänny
  set.add(new Rectangle(4,1));  // area = 4
  
  
  Huomaa: duplikaatti Circle(40) ei lisäänny, koska Comparator palauttaa 0
  
  Tulostus järjestyksessä
  
  for (GeometricObject element: set)
    System.out.println("area = " + element.getArea());
    
  
  TreeSet tulostaa objektit pienimmästä suurimpaan pinta-alan mukaan
  
  4️⃣ PSEUDOKOODI
  set ← uusi TreeSet(GeometricObjectComparator)
  
  lisää Rectangle(4,5) settiin
  lisää Circle(40) settiin
  lisää Circle(40) settiin  # ei lisäänny
  lisää Rectangle(4,1) settiin
  
  tulosta "A sorted set of geometric objects"
  
  for element in set:
    tulosta element.getArea()
    
  5️⃣ MITÄ TAPAHTUU DUPLIKAATIN KANSSA
  
  TreeSet tarkistaa compare(obj1,obj2):
  
  Jos palauttaa 0 → objekti katsotaan duplikaatiksi
  
  Tässä:
  
  Circle(40) vs Circle(40) → compare() = 0 → ei lisätä toista
  
  6️⃣ AIKAVAATIVUUS
  
  TreeSet käyttää tasapainotettua binääripuuta (Red-Black Tree).
  
  Operaatio	Aikavaativuus
  add()	O(log n)
  remove()	O(log n)
  contains()	O(log n)
  iterointi	O(n)
  
  ➡️ Tässä esimerkissä add-operaatiot ovat O(log n), ja iterointi tulostukseen on O(n).
  
  7️⃣ ESIMERKKITULOSTUS
  
  Järjestetty pinta-ala:
  
  A sorted set of geometric objects
  area = 4.0
  area = 20.0
  area = 5026.548245743669
  
  
  Pienin → Rectangle(4,1)
  
  Keskikokoinen → Rectangle(4,5)
  
  Suurin → Circle(40)
  
  8️⃣ TRA1 / tenttimuistiinpano
  
  TreeSet + Comparator = oma järjestys objektille
  
  Duplikaatit Comparatorin mukaan
  
  Käytännössä ei tarvitse tehdä Comparablea kaikille luokille
  
  Osa TRA1-oppia: abstrakti tietotyyppi (Set) → toteutus (TreeSet) + Comparator  
  
  */