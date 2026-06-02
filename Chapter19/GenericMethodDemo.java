public class GenericMethodDemo {


  public static void main(String[] args ) {
    
    Integer[] integers = {1, 2, 3, 4, 5};
    String[] strings = {"London", "Paris", "New York", "Austin"};
    
    // Voidaan tulostaa erityyppisiä taulukoita samalla metodilla 
    GenericMethodDemo.<Integer>print(integers); 
    GenericMethodDemo.<String>print(strings);
  }
  
  // Tämä on parametroitu geneerisellä tyypillä
  // Metodi kuuluu luokalle, ei millekään oliolle
  // Tämä on staattinen, koska tässä ei tarvita olion tilaa
  
  // Olion tila tarkoittaa kaikkia sen kenttiä ja attribuutteja tietyllä hetkellä
  // Parametrina olevat alkiot pitää olla samaa tyyppiä 
  public static <E> void print(E[] list) { 
    
    for (int i = 0; i < list.length; i++)
    // kaikki listan alkio tulostetaan samalle riville 
    
    System.out.print(list[i] + " "); 
    // Seuraava metodikutsun alkiot menevät seuraavalle riville
    System.out.println();
    
  } 
  
}

/*
🔭 Ylätason yleiskuvaus

Ohjelma demonstroi geneeristä metodia, joka voi tulostaa minkä tahansa tyyppisen taulukon sisällön.

Sama print-metodi toimii:

Integer[]

String[]

ja kaikille muille viitetyypeille

Tyyppi päätellään metodikutsun yhteydessä, ei luokan luonnissa.

🧩 Asteittainen tarkennus
1️⃣ Taulukot main-metodissa
Integer[] integers = {1, 2, 3, 4, 5};
String[] strings = {"London", "Paris", "New York", "Austin"};

Kaksi eri tyyppistä taulukkoa

Ilman genericsia tarvittaisiin kaksi eri tulostusmetodia

2️⃣ Geneerisen metodin kutsu
GenericMethodDemo.<Integer>print(integers);
GenericMethodDemo.<String>print(strings);


Tässä määritellään:

E = Integer
E = String

Java voisi päätellä tämän myös itse, mutta tämä muoto tekee asian eksplisiittiseksi ja opetusmielessä selkeäksi

3️⃣ Geneerinen metodi
public static <E> void print(E[] list)

<E> määrittelee metodikohtaisen geneerisen tyypin

E[] list → taulukko mitä tahansa tyyppiä

Metodi ei välitä, mikä E oikeasti on

4️⃣ Tulostussilmukka
for (int i = 0; i < list.length; i++)
  System.out.print(list[i] + " ");
  

Käy taulukon läpi alusta loppuun

Tulostaa jokaisen alkion

Lopuksi rivinvaihto

✏️ Pseudokoodi (ytimekäs)
PRINT(list)
  for each element in list
    print element
  print newline
  

Geneerinen ajatus:

E voi olla mikä tahansa tyyppi

⏱️ Aikavaativuus
Operaatio	Aikavaativuus
print	O(n)

n = taulukon pituus

Jokainen alkio käsitellään tasan kerran

Ei sisäkkäisiä silmukoita

🔑 Tenttimuistisäännöt

<E> metodin edessä → geneerinen metodi

Geneerinen metodi:

ei vaadi geneeristä luokkaa

toimii riippumatta luokan tyypistä

Tyyppi päätetään metodikutsussa

Sama koodi → monta tyyppiä

Aikavaativuus O(n)

🧠 Yksi tärkeä oivallus

Geneerinen luokka kuvaa olion rakennetta
Geneerinen metodi kuvaa toiminnon, joka ei riipu tyypistä
Generics tarkoittaa generis syyttä, yleisyyttä tai tyyppiparametria
*/
