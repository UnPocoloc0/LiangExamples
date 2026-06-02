public class GenericSort {
  
  public static void main(String[] args) {
    
    // Create an Integer array
    Integer[] intArray = {new Integer(2), new Integer(4), new Integer(3)};
    // Create a Double array
    Double[] doubleArray = {new Double(3.4), new Double(1.3), new Double(-22.1)};
    // Create a String array
    String[] stringArray = {"Tom", "Susan", "Kim"};
    // Create a Character array
    Character[] charArray = {new Character('a'), new Character('J'), new Character('r')};
    
    // Sort the arrays
    sort(intArray);
    sort(doubleArray);
    sort(charArray);
    sort(stringArray);
    
    // Display the sorted arrays
    System.out.print("Sorted Integer objects: "); 
    printList(intArray);
    
    System.out.print("Sorted Double objects: "); 
    printList(doubleArray); System.out.print("Sorted Character objects: "); 
    printList(charArray);
    
    System.out.print("Sorted String objects: "); 
    printList(stringArray);
    
  } // main
  
  /** Sort an array of comparable objects */
  // Geneerinen tyyppi, eli mikä tahansa luokka joka toteuttaa Comparable-rajapinnan
  // parametrina taulukko, jonka alkiot voidaan vertailla compareTo-metodilla
  public static <E extends Comparable<E>> void sort(E[] list) { 
    
    // jäljellä olevan listan pienin alkio 
    E currentMin;
    // tämän pienemmän alkion indeksi 
    int currentMinIndex;
    
    // Ulompi silmukka, eli valitaan paikka taulukosta, johon laitetaan pienin jäljellä oleva alkio 
    // Tämä silmukka käy kaikki indeksit läpi ja jokaisella kierroksella lukitaan yksi alkio oikeaan paikkaan 
    // Viimeinen silmukka ei tarvi omaa kierrosta, koska viimeinen alkion jo oikeassa paikassa
    for (int i = 0; i < list.length - 1; i++) {
      // Find the minimum in the list[i+1..list.length-2] 
      // Pienin tähän asti löydetty alkio 
      currentMin = list[i];
      // indeksi, josta tämä pienin alkio löydettiin 
      currentMinIndex = i;
      
      // Sisempi silmukka 
      for (int j = i + 1; j < list.length; j++) {
        // tällä vertailulla löydetään uusi pienin alkio
        // Nykyinen pienin on suurempi kuin käsittelyssä oleva alkio 
        // tässä on tärkeä järjestyksen suunta: jos lukuun positiivinen, niin vasemmalla on suurempi, ja alkiot vaihdetaan 
        if (currentMin.compareTo(list[j]) > 0) {
          
          // päivitetään uusin pienin alkio ja sen indeksi
          currentMin = list[j];
          currentMinIndex = j; 
        }        
      }
      // Swap list[i] with list[currentMinIndex] if necessary;
      // jos jäljellä olevan taulukon pienin alkio ei ole paikallaan, niin se vaihdetaan oikeaan paikkaan
      // Seuraava luupin kierros siirtyy seuraavaan indeksiin 
      
      if (currentMinIndex != i) { 
        list[currentMinIndex] = list[i]; 
        list[i] = currentMin;
      }
      
    }
  } // sort
  
  /** Print an array of objects */
  public static void printList(Object[] list) { 
    
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " "); 
      System.out.println();
    
  }
}


/*
1️⃣ Ylätason kuvaus

Koodi järjestää taulukot eri tyyppisille olioille (Integer, Double, Character, String)

Käytetään geneeristä sort-metodia <E extends Comparable<E>>

Metodi toimii kaikilla luokilla, jotka toteuttavat Comparable-rajapinnan → ne voidaan vertailla

Käytetty algoritmi: selection sort (valintajärjestys)

2️⃣ Asteittainen tarkennus
2a. Main-metodi

Luodaan eri tyyppisiä taulukoita: Integer[], Double[], Character[], String[]

Kutsutaan sort-metodia kullekin taulukolle

Tulostetaan järjestetyt taulukot printList-metodilla

2b. Geneerinen sort-metodi
public static <E extends Comparable<E>> void sort(E[] list)


E extends Comparable<E> → tyyppi E voidaan vertailla compareTo-metodilla

Algoritmi:

Käy läpi jokainen indeksi i taulukossa

Etsi pienin alkio jäljellä olevasta taulukon osasta (i+1 .. n-1)

Jos löydetty pienin alkio on eri paikassa kuin i, vaihda ne keskenään

Toista seuraavalle indeksille

Tämä on klassinen Selection Sort

2c. printList-metodi

Käy taulukon läpi ja tulostaa kaikki alkiot yhdelle riville

3️⃣ Pseudokoodi
function sort(list: array of Comparable):
for i from 0 to list.length - 2:
currentMin = list[i]
currentMinIndex = i
for j from i+1 to list.length - 1:
if currentMin.compareTo(list[j]) > 0:
currentMin = list[j]
currentMinIndex = j
if currentMinIndex != i:
swap list[i] with list[currentMinIndex]

function printList(list):
for each element in list:
print element

4️⃣ Aikavaativuus

Selection Sort:

Ulompi silmukka: n-1 kertaa

Sisempi silmukka: keskimäärin n/2 kertaa

Aikavaativuus:

O(n²) vertailuja aina

O(n) vaihtoja (swap) → yksi per indeksi

Geneerisyys (<E extends Comparable<E>>) ei vaikuta aikavaativuuteen

Se vain varmistaa, että kaikki alkiot voidaan vertailla

💡 TRA1-muistilappu:

Geneerinen tyyppi E extends Comparable<E> → mikä tahansa luokka, jolla on compareTo

Selection Sort: valitaan pienin jäljellä olevasta → vaihdetaan alkuun → toistetaan

Aikavaativuus aina O(n²), vaikka olioiden tyyppi olisi eri


1️⃣ Ulompi silmukka – “valitaan paikka”
for (int i = 0; i < list.length - 1; i++) {
  currentMin = list[i];
  currentMinIndex = i;
  

i on paikka, johon seuraava pienin alkio asetetaan

currentMin alkaa oletuksena list[i] → “nykyinen pienin”

2️⃣ Sisempi silmukka – etsitään pienin jäljellä
for (int j = i + 1; j < list.length; j++) {
  if (currentMin.compareTo(list[j]) > 0) {
    currentMin = list[j];
    currentMinIndex = j;
  }
}


j käy läpi jäljellä olevan osan (i+1 .. n-1)

Vertaa jokaista alkioa currentMin-arvoon

Jos löydetään pienempi alkio → päivitetään currentMin ja sen indeksi

Eli joka kierroksella “nykyinen pienin” voi muuttua, kun etsitään koko jäljellä olevaa osaa

3️⃣ Lopuksi swap

Kun sisempi silmukka on valmis → pienin jäljellä oleva alkio tiedossa

Vaihdetaan se indeksiin i, jos se ei ole jo siellä

4️⃣ TRA1-ajatus

Ulompi silmukka: “mihin paikkaan pienin tulee”

Sisempi silmukka: “etsi pienin jäljellä oleva”

currentMin muuttuu vain, jos löytyy pienempi → näin aina tiedetään pienin alkio, joka tulee seuraavalle paikalle
*/
