public class BoundedTypeDemo {
  
  public static void main(String[] args ) { 
    
    Rectangle rectangle = new Rectangle(2, 2); 
    Circle circle = new Circle(2);
    System.out.println("Same area? " + equalArea(rectangle, circle));
    
  }
    public static <E extends GeometricObject> boolean equalArea(E object1, E object2) {
    return object1.getArea() == object2.getArea();
    
  }
}
// Abstraktista luokasta ei voida luoda ilmentymiä
// tämä toimii yhteisenä rajapintana kaikille geometrisille objekteille
abstract class GeometricObject {
  public abstract double getArea();
}

// Nämä luokat perivät abstraktin luokan 
class Rectangle extends GeometricObject {
  private double width, height;
  
  public Rectangle(double w, double h) { 
    width = w; 
    height = h; 
  }
  // Aliluokkien pitää toteuttaa tämä metodi
  public double getArea() { 
    return width * height; 
  }
}

class Circle extends GeometricObject {
  private double radius;
  
  public Circle(double r) { 
    // Olion luomissahetkellä parametrina annettu arvo laitetaan vastaamaan luokan ominaisuutta 
    radius = r; 
  }
  public double getArea() { 
    return Math.PI * radius * radius; 
  }
}



/*
1️⃣ Ylätason kuvaus

Koodi vertaa kahden geometrisen objektin pinta-aloja.

Käyttää geneeristä metodia <E extends GeometricObject> (rajoittaa tyyppiä GeometricObjectiin).

Demo luo Rectangle- ja Circle-objektit ja kutsuu equalArea-metodia.

2️⃣ Asteittainen tarkennus
2a. Luokat

GeometricObject – abstrakti luokka, määrittelee metodin getArea().

Rectangle – perii GeometricObjectin, pinta-ala = width * height

Circle – perii GeometricObjectin, pinta-ala = π * radius²

2b. Main-metodi

Luo rectangle (2×2) ja circle (radius=2)

Kutsuu geneeristä metodia equalArea(rectangle, circle)

Tulostaa tuloksen

2c. Geneerinen metodi
public static <E extends GeometricObject> boolean equalArea(E object1, E object2)


E extends GeometricObject → molempien parametrien täytyy olla samaa tyyppiä, joka perii GeometricObjectin

Vertaa object1.getArea() == object2.getArea()

Palauttaa true, jos pinta-alat ovat samat

Huomio TRA1: tällä hetkellä koodi ei salli eri tyyppejä (Circle vs Rectangle) geneerisen tyypin E vuoksi → kääntäjä antaa virheen.

2d. Ratkaisu geneerisyysongelmaan

Jos halutaan vertailla eri aliluokkia:

public static boolean equalArea(GeometricObject object1, GeometricObject object2) {
  return object1.getArea() == object2.getArea();
}


Ei tarvita geneeristä tyyppiä → molemmat voivat olla mitä tahansa GeometricObjectin aliluokkaa.

3️⃣ Pseudokoodi
abstract class GeometricObject
  method getArea() -> double
  
class Rectangle extends GeometricObject
  width, height
  getArea() = width * height
  
class Circle extends GeometricObject
  radius
  getArea() = π * radius^2
  
function equalArea(object1: GeometricObject, object2: GeometricObject) -> boolean
  return object1.getArea() == object2.getArea()
  
main:
  rectangle = new Rectangle(2, 2)
  circle = new Circle(2)
  print "Same area? " + equalArea(rectangle, circle)
  
4️⃣ Aikavaativuus

equalArea:

Laskee pinta-alan kahdelle objektille

O(1) – vakioaika

Koko main-metodin aikavaativuus = O(1)

Ei silmukoita tai rekursiota

💡 TRA1-muistisääntö:

Geneerinen <E extends GeometricObject> → molempien parametrien täytyy olla samaa tyyppiä

Jos haluat vertailla eri geometrisia objekteja → käytä yläluokan tyyppiä suoraan

*/