import java.util.Comparator;

public class TestComparator {
   
   public static void main(String[] args) {
            
      GeometricObject g1 = new Rectangle(5, 5);
      GeometricObject g2 = new Circle(5); 
      
      GeometricObject g = max(g1, g2, new GeometricObjectComparator()); 
      System.out.println("The area of the larger object is " + g.getArea());
   } // main
   
   // Esimerkkidatalla ympyrän pinta-ala on suurempi
   
   public static GeometricObject max(GeometricObject g1, GeometricObject g2, Comparator<GeometricObject> c) {
      if (c.compare(g1, g2) > 0) 
         return g1;
      else
         return g2;
   }   
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
1️⃣ YLÄTASON SUUNNITELMA (mitä ohjelma tekee)

Tavoite:
Ohjelma vertailee kahden eri geometrisen olion pinta-aloja ja tulostaa suuremman.

Keskeiset ideat (TRA1-kurssin ydin):

Abstrakti luokka (GeometricObject)

Perintä (Rectangle, Circle)

Rajapinta (Comparator)

Geneerinen metodi (max)

Dynaaminen sidonta (metodin valinta ajonaikana)

Ohjelman rakenne:

Luodaan kaksi erilaista oliota (suorakulmio ja ympyrä)

Verrataan niitä pinta-alan perusteella

Valitaan suurempi

Tulostetaan tulos

2️⃣ ENSIMMÄINEN TARKENNUS (luokat ja vastuut)
📦 Luokat ja niiden roolit
GeometricObject (abstrakti yliluokka)

Määrittelee yhteisen rajapinnan

Pakottaa aliluokat toteuttamaan getArea()

Rectangle ja Circle

Toteuttavat getArea() omalla tavallaan

Edustavat konkreettisia geometrisia olioita

GeometricObjectComparator

Vertaa kahta GeometricObject-oliota

Vertailuperuste: pinta-ala

TestComparator

Sisältää main-metodin

Kutsuu vertailua ja tulostaa tuloksen

3️⃣ TOINEN TARKENNUS (ohjelman kulku askel askeleelta)
main-metodin logiikka

Luo suorakulmio (5 × 5)

Luo ympyrä (säde 5)

Kutsu max-metodia:

Syötteinä kaksi oliota + vertailija

max palauttaa suuremman olion

Tulostetaan sen pinta-ala

max-metodin idea

Ei tiedä minkä tyyppisiä oliot ovat

Luottaa Comparator-rajapintaan

Toimii kaikille GeometricObject-olioille

4️⃣ YDINLOGIIKKA PSEUDOKOODINA (tenttiystävällinen)
Yleinen ajatus
luo kaksi geometriaoliota
vertaa niitä comparatorilla
palauta suurempi
tulosta sen pinta-ala

Tarkempi pseudokoodi
Pääohjelma
g1 ← uusi suorakulmio (5,5)
g2 ← uusi ympyrä (5)

suurempi ← max(g1, g2, geometriaVertailija)

tulosta suurempi.pintaAla

max-metodi
funktio max(a, b, vertailija):
   jos vertailija.vertailu(a, b) > 0:
      palauta a
   muuten:
      palauta b
      
Comparator
funktio vertailu(o1, o2):
   palauta vertaa(o1.pintaAla, o2.pintaAla)
   
Abstrakti luokka
abstrakti luokka GeometricObject:
   abstrakti funktio pintaAla()
   
5️⃣ MITÄ TENTISSÄ HALUTAAN TÄSTÄ ESIMERKISTÄ

Jos tämä tulisi tenttiin, opettaja testaa että ymmärrät:

✅ Perinnän ja abstraktion
✅ Rajapinnan käytön vertailuun
✅ Dynaamisen sidonnan (getArea() kutsutaan oikeasta luokasta)
✅ Geneerisen ohjelmoinnin idean
✅ Comparator-mallin

.

🔹 extends vs implements – ydinero
Avainsana	Käytetään	Mitä peritään
extends	luokka → luokka tai luokka → abstrakti luokka	toteutus + rakenne
implements	luokka → rajapinta (interface)	vain lupaus metodeista
1️⃣ extends – ”on erikoistapaus”

extends = perii koodia ja käyttäytymistä

Esimerkki 
class Rectangle extends GeometricObject


Tämä tarkoittaa:

Rectangle on GeometricObject

Perii kaiken ei-yksityisen (protected, public)

Voi käyttää tai yliajaa metodeja

Saa rakenteen (kentät, metodit)

Ajatusmalli

Suorakulmio on geometrinen olio

2️⃣ implements – ”osaa käyttäytyä kuin”

implements = sitoutuu toteuttamaan rajapinnan

Esimerkki
class GeometricObjectComparator
   implements Comparator<GeometricObject>
   

Tämä tarkoittaa:

Luokka lupaa toteuttaa kaikki Comparator-rajapinnan metodit

Ei peri valmista koodia

Pakko toteuttaa:

int compare(T o1, T o2);

Ajatusmalli

Tämä luokka osaa vertailla geometrisia olioita

3️⃣ Kriittinen ero yhdellä lauseella (tenttiin!)

extends perii toteutuksen, implements perii sopimuksen

4️⃣ Säännöt, jotka kannattaa muistaa
🔹 Luokka voi:

extends vain yhtä luokkaa

implements useita rajapintoja

class A extends B implements C, D

🔹 Rajapinta:

ei sisällä tilaa (kenttiä)

ei sisällä konstruktorieita

metodit ovat oletuksena public abstract

5️⃣ Pseudokoodi-ajatus (TRA1-tyylinen)
extends:
   "olen aliluokka"
   "saan valmiin toiminnallisuuden"
   
implements:
   "lupaan toteuttaa nämä metodit"
   "miten → minä päätän"
   
6️⃣ Tenttivastaus (supertiivis)

extends-avainsanaa käytetään perintään, jossa aliluokka saa yliluokan toteutuksen.
implements-avainsanaa käytetään rajapinnan toteuttamiseen, jossa luokka sitoutuu määriteltyihin metodeihin ilman valmista toteutusta.
*/
