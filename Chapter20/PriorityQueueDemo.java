import java.util.*;
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<String> queue1 = new PriorityQueue<>();
        queue1.offer("Oklahoma");    queue1.offer("Indiana");    queue1.offer("Georgia");    queue1.offer("Texas");
        System.out.println("Priority queue using Comparable:");
        while (queue1.size() > 0) {
      System.out.print(queue1.remove() + " ");
          } 
        PriorityQueue<String> queue2 = new PriorityQueue(4, Collections.reverseOrder());
        queue2.offer("Oklahoma");        queue2.offer("Indiana");    queue2.offer("Georgia");    queue2.offer("Texas");
        System.out.println("\nPriority queue using Comparator:");
        while (queue2.size() > 0) {
      System.out.print(queue2.remove() + " ");
          }   }
}

/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:

Demonstroida PriorityQueue-luokan toimintaa Java-kielessä.

Näytetään kahdenlaista järjestystä:

luonnollinen järjestys (Comparable)

käänteinen järjestys (Comparator)

Poistetaan ja tulostetaan alkiot prioriteetin mukaisessa järjestyksessä.

Keskeiset ideat TRA1-tasolla:

Abstraktio: Queue-rajapinta + prioriteetti

Comparable vs Comparator

FIFO vs prioriteettijärjestys

Perusoperaatiot: offer, remove, size

2️⃣ TARKENNUS: luokat ja vastuut
Käytetty luokka	Rooli
PriorityQueue<String>	Prioriteettijono, joka järjestää alkiot automaattisesti
Collections.reverseOrder()	Comparator, käänteinen järjestys
offer()	lisää alkion jonoon
remove()	poistaa jonon alkion korkeimman prioriteetin mukaan
size()	palauttaa jonon koon

3️⃣ OHJELMAN KULKU ASKEL ASKELELTA

Luodaan queue1 (PriorityQueue) luonnollisessa järjestyksessä.

Lisätään neljä osavaltiota (offer).

Poistetaan ja tulostetaan alkiot prioriteetin mukaisessa järjestyksessä.

Luodaan queue2 PriorityQueue, Comparatorilla käänteisessä järjestyksessä.

Lisätään samat alkiot (offer).

Poistetaan ja tulostetaan alkiot käänteisessä järjestyksessä.

4️⃣ YDINLOGIIKKA PSEUDOKOODINA
queue1 ← uusi PriorityQueue()
queue1.lisää("Oklahoma")
queue1.lisää("Indiana")
queue1.lisää("Georgia")
queue1.lisää("Texas")

while queue1 ei ole tyhjä:
  tulosta queue1.remove()
  
queue2 ← uusi PriorityQueue(käänteinen järjestys)
queue2.lisää("Oklahoma")
queue2.lisää("Indiana")
queue2.lisää("Georgia")
queue2.lisää("Texas")

while queue2 ei ole tyhjä:
  tulosta queue2.remove()
  
5️⃣ TULOSTUSJÄRJESTYS
queue1 (luonnollinen järjestys, aakkosjärjestys)
Georgia Indiana Oklahoma Texas

queue2 (käänteinen järjestys)
Texas Oklahoma Indiana Georgia

6️⃣ AIKAVAATIVUUS
Operaatio	PriorityQueue (heap-toteutus)	Kommentti
offer()	O(log n)	Lisää alkion ja säilyttää heap-ominaisuuden
remove()	O(log n)	Poistaa juuren ja heapää uudelleen
size()	O(1)	Koko tallennetaan muuttujassa
Kokonaisajan arvio

4 lisäystä + 4 poistoa → 8 × O(log n)

→ Koko ohjelman aikavaativuus ≈ O(n log n)

7️⃣ TENTTIVASTAUS (tiivistetty)

PriorityQueue järjestää alkiot automaattisesti prioriteetin mukaan.
Lisäys ja poisto ovat O(log n), ja koko ohjelman aikavaativuus on O(n log n).
Luonnollinen järjestys tulee Comparable-rajapinnasta ja käänteinen järjestys Comparator-oliosta.

8️⃣ Muistisääntö TRA1-tenttiin
PriorityQueue:
- automaattisesti järjestetty jono
- offer/remove → O(log n)
- size → O(1)
- default = Comparable
- Comparator = custom järjestys

1️⃣ Perusrakenne: PriorityQueue

Java PriorityQueue on toteutettu binäärikeko (binary heap) -rakenteella.

Heap on täydellinen binääripuu, joka täyttää ehdon:

vanhempi ≤ lapset (min-heap)
vanhempi ≥ lapset (max-heap)


Juuri (root) on aina pienin (tai suurin, riippuen heap-tyypistä).

2️⃣ Lisäys (offer) – miksi O(log n)

Kun lisätään alkio heap:iin:

Alkio lisätään taulukon loppuun

Sitten se “korjataan ylös” (bubble-up / heapify-up), jotta heap-ominaisuus säilyy

Binääripuun korkeus on logaritminen:

korkeus = log2(n)


koska binääripuu on lähes täydellinen.

Yläoikeaan asti korjaaminen:

Jokaisessa tasossa vertailu + mahdollinen vaihto vanhemman kanssa

Enintään log2(n) tasoa → O(log n)

3️⃣ Poisto (remove) – miksi O(log n)

Poistetaan juuri (root):

Juuri on pienin alkio (min-heap)

Viimeinen alkio siirretään juuriin

Sitten heapataan alaspäin (bubble-down / heapify-down):

Verrataan lapsiin

Vaihdetaan pienimmän kanssa, kunnes heap-ominaisuus säilyy

Myös tässä korkeus on log2(n) → O(log n)

4️⃣ Koko ohjelman aikavaativuus

Katsotaan koodisi:

queue1.offer("Oklahoma");
queue1.offer("Indiana");
queue1.offer("Georgia");
queue1.offer("Texas");


4 lisäystä → 4 × O(log n)

n = alkioiden määrä lisäyksen hetkellä (1..4), mutta asymptoottisesti → O(n log n)

Sitten:

while (queue1.size() > 0) {
  System.out.print(queue1.remove() + " ");
}


4 poistoa → 4 × O(log n)

Yhteensä:

n lisäystä + n poistoa → 2n × O(log n) ≈ O(n log n)

5️⃣ Vertailu tavalliseen jonoon (LinkedList)
Operaatio	LinkedList Queue	PriorityQueue
lisäys loppuun	O(1)	O(log n)
poisto alusta	O(1)	O(log n)
järjestys	ei	automaattinen prioriteetti

➡️ Jos tarvitset järjestetyn poiston, PriorityQueue on parempi, mutta vakioaikainen FIFO on LinkedListissä tehokkaampi.

6️⃣ Ytimekäs muistisääntö
PriorityQueue = binäärikeko
offer/remove → O(log n)
koko ohjelma (n alkioita) → O(n log n)
LinkedList Queue → offer/remove → O(1)

Rivi koodissasi on osa PriorityQueue-konstruktorin kutsua:

PriorityQueue<String> queue2 = new PriorityQueue(
  4, Collections.reverseOrder());
  
1️⃣ Ensimmäinen argumentti: 4

Tämä on alkuperäinen kapasiteetti (initial capacity).

PriorityQueue on heapin päällä oleva taulukko, ja tämä kertoo:

kuinka suuri sisäinen taulukko varataan aluksi

ei rajoita alkioiden määrää – jos lisäät enemmän alkioita, taulukko kasvaa automaattisesti

Käytännössä:

new PriorityQueue<>(4)


→ alustaa heapin taulukon koolle 4

Tämä ei vaikuta log n aikavaativuuteen, se on vain optimointia muistin käytölle.

2️⃣ Toinen argumentti: Collections.reverseOrder()

Tämä on Comparator, joka kertoo heapille miten järjestää alkiot.

Collections.reverseOrder():

Palauttaa Comparatorin, joka kääntää luonnollisen järjestyksen
(eli pienimmästä → suurimpaan muuttuu suurimmasta → pienimpään)

Esimerkki luonnollinen aakkosjärjestys (Comparable):

Georgia < Indiana < Oklahoma < Texas


ReverseOrder() kääntää sen:

Texas > Oklahoma > Indiana > Georgia


Käytännössä tämä tekee PriorityQueue:stä max-heapin, kun default on min-heap.

3️⃣ Yhdessä: mitä koko rivi tekee
PriorityQueue<String> queue2 = new PriorityQueue(4, Collections.reverseOrder());


Luo PriorityQueue:n, jonka:

Sisäinen taulukko alkaa koolla 4

Prioriteetti määräytyy käänteisessä luonnollisessa järjestyksessä

Kun lisäät alkioita ja kutsut remove(), jokainen poisto palauttaa suurimman alkion.

4️⃣ Vertailu queue1 vs queue2
PriorityQueue<String> queue1 = new PriorityQueue<>();


Luonnollinen järjestys (Comparable) → min-heap

Poisto palauttaa pienimmän alkion

PriorityQueue<String> queue2 = new PriorityQueue(4, Collections.reverseOrder());


Comparator = reverseOrder() → max-heap

Poisto palauttaa suurimman alkion

5️⃣ Tenttimuistisääntö
PriorityQueue(kapasiteetti, Comparator):
- kapasiteetti = taulukon alkuperäinen koko
- Comparator = prioriteettijärjestys
- Collections.reverseOrder() = käänteinen luonnollinen järjestys

*/