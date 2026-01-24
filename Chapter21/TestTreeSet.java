import java.util.*;
public class TestTreeSet {
    public static void main(String[] args) {
    
    // Create a hash set
    Set<String> set = new HashSet<>();
    // Add strings to the set
    set.add("London"); 
    set.add("Paris"); 
    set.add("New York"); 
    set.add("San Francisco"); 
    set.add("Beijing"); 
    // dublikaatti ei lisäänny
    set.add("New York");
    
    TreeSet<String> treeSet = new TreeSet<>(set); 
    
    System.out.println("Sorted tree set: " + treeSet);
    
    // Use the methods in SortedSet interface
    System.out.println("first(): " + treeSet.first()); 
    System.out.println("last(): " + treeSet.last()); 
    System.out.println("headSet(\"New York\"): " +
      treeSet.headSet("New York")); 
    System.out.println("tailSet(\"New York\"): " +
        treeSet.tailSet("New York"));
      
    // Use the methods in NavigableSet interface
    System.out.println("lower(\"P\"): " + treeSet.lower("P")); 
    System.out.println("higher(\"P\"): " + treeSet.higher("P")); 
    System.out.println("floor(\"P\"): " + treeSet.floor("P")); 
    System.out.println("ceiling(\"P\"): " + treeSet.ceiling("P")); 
    System.out.println("pollFirst(): " + treeSet.pollFirst()); 
    System.out.println("pollLast(): " + treeSet.pollLast()); 
    System.out.println("New tree set: " + treeSet);  }
  }

/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:
Demonstroida TreeSetin toimintaa

Näyttää:
miten epäjärjestetty HashSet muutetaan järjestetyksi joukoksi
miten SortedSet- ja NavigableSet-rajapintojen metodit toimivat

Keskeinen idea TRA1:ssä:
TreeSet = järjestetty joukko, joka perustuu puurakenteeseen.

2️⃣ TARKENNUS: käytetyt rakenteet ja rajapinnat
Osa	Selitys
Set<String>	Abstrakti tietotyyppi (ADT)
HashSet	Epäjärjestetty toteutus
TreeSet	Järjestetty toteutus
SortedSet	Rajapinta: järjestys + rajaus
NavigableSet	Rajapinta: lähimmät alkiot

TreeSet toteuttaa:
Set → SortedSet → NavigableSet

3️⃣ OHJELMAN KULKU ASKEL ASKELELTA
① HashSetin luonti

Lisätään kaupungit
Duplikaatti "New York" ei lisäänny
järjestys satunnainen

② TreeSetin luonti
TreeSet<String> treeSet = new TreeSet<>(set);


Kopioi HashSetin alkiot
Lajittelee ne automaattisesti
Käyttää String-luokan luonnollista järjestystä (aakkosjärjestys)

4️⃣ PSEUDOKOODI
set ← uusi HashSet
lisää kaupungit settiin

treeSet ← uusi TreeSet(set)
tulosta treeSet (järjestetty)

tulosta ensimmäinen alkio
tulosta viimeinen alkio

tulosta kaikki < "New York"
tulosta kaikki ≥ "New York"

tulosta lähin pienempi kuin "P"
tulosta lähin suurempi kuin "P"
tulosta suurin ≤ "P"
tulosta pienin ≥ "P"

poista ja palauta ensimmäinen
poista ja palauta viimeinen

tulosta treeSet

5️⃣ MITÄ METODIT TARKOITTAVAT

SortedSet-metodit
Metodi	Merkitys
first()	Pienin alkio
last()	Suurin alkio
headSet(x)	Kaikki alkiot < x
tailSet(x)	Kaikki alkiot ≥ x

NavigableSet-metodit
Metodi	Merkitys
lower(x)	Suurin alkio < x
higher(x)	Pienin alkio > x
floor(x)	Suurin alkio ≤ x
ceiling(x)	Pienin alkio ≥ x
pollFirst()	Poistaa ja palauttaa pienimmän
pollLast()	Poistaa ja palauttaa suurimman

6️⃣ ESIMERKKI TULOSTUKSESTA
Sorted tree set: [Beijing, London, New York, Paris, San Francisco]

first(): Beijing
last(): San Francisco

headSet("New York"): [Beijing, London]
tailSet("New York"): [New York, Paris, San Francisco]

lower("P"): New York
higher("P"): Paris
floor("P"): New York
ceiling("P"): Paris

pollFirst(): Beijing
pollLast(): San Francisco

New tree set: [London, New York, Paris]

7️⃣ AIKAVAATIVUUS

TreeSet perustuu tasapainotettuun binääripuuhun (Red-Black Tree).

Operaatio	Aikavaativuus
add()	O(log n)
remove()	O(log n)
contains()	O(log n)
first / last	O(log n)
headSet / tailSet	O(log n)
lower / higher / floor / ceiling	O(log n)
Iterointi	O(n)

➡️ Syynä:

puun korkeus ≈ log n

jokainen haku kulkee juuresta lehteen

8️⃣ VERTAILU (tentissä kullanarvoinen)
Rakenne	Järjestys	Aikavaativuus
HashSet	❌	O(1)
LinkedHashSet	Lisäysjärjestys	O(1)
TreeSet	Lajiteltu	O(log n)

9️⃣ TENTTIVASTAUS (malli)

TreeSet on Set-rajapinnan toteutus, joka säilyttää alkiot järjestyksessä.
Se perustuu tasapainotettuun binääripuuhun, minkä vuoksi perusoperaatiot ovat O(log n).

🔟 TRA1-MUISTISÄÄNTÖ
TreeSet =
- ei duplikaatteja
- järjestetty
- O(log n)
- tukee hakuja "lähin alkio"
1️⃣ Yleinen idea (luonnollisesti)

Nämä metodit vastaavat kysymykseen:

“Mikä alkio on lähinnä haettua arvoa vasemmalta tai oikealta?”

2️⃣ lower("P")
treeSet.lower("P")


Merkitys:

Palauttaa suurimman alkion, joka on pienempi kuin "P"
"P" ei tarvitse olla setissä

Etsii vasemmalta puolelta

Tässä esimerkissä:
Alkiot ennen "P":
Beijing, London, New York
Näistä suurin (lähin): "New York"

Tulos:
New York

3️⃣ higher("P")
treeSet.higher("P")


Merkitys:
Palauttaa pienimmän alkion, joka on suurempi kuin "P"
Etsii oikealta puolelta
Tiukka vertailu (>)
Tässä:
Ensimmäinen "P":tä suurempi alkio on "Paris"

Tulos:
Paris

4️⃣ floor("P")
treeSet.floor("P")


Merkitys:
Palauttaa suurimman alkion, joka on pienempi TAI YHTÄ SUURI kuin "P"
Sallii yhtäsuuruuden (≤)
Jos "P" olisi setissä, se palautettaisiin

Tässä:
"P" ei ole setissä
Sama kuin lower("P")

Tulos:
New York

5️⃣ ceiling("P")
treeSet.ceiling("P")

Merkitys:
Palauttaa pienimmän alkion, joka on suurempi TAI YHTÄ SUURI kuin "P"
Sallii yhtäsuuruuden (≥)

Jos "P" olisi setissä, se palautettaisiin
Tässä:
Sama kuin higher("P")

Tulos:
Paris

6️⃣ VISUAALINEN AJATTELU
Beijing ─ London ─ New York ─ Paris ─ San Francisco
              ↑        ↑
            lower     higher
            floor     ceiling
            

Hakuarvo: "P"

7️⃣ Taulukko (tenttiin täydellinen)
Metodi	Selitys	Sisältää yhtäsuuruuden
lower(x)	suurin < x	❌
higher(x)	pienin > x	❌
floor(x)	suurin ≤ x	✅
ceiling(x)	pienin ≥ x	✅
8️⃣ Muistisääntö (helppo!)
lower / higher = tiukka (<, >)
floor / ceiling = salliva (≤, ≥)


Tai vielä lyhyemmin:

floor = lattia → saa koskea

ceiling = katto → saa koskea

9️⃣ TRA1-tenttivastaus (malli)

NavigableSetin metodit lower, higher, floor ja ceiling palauttavat haettua arvoa lähimmän alkion järjestetyssä joukossa.
lower ja higher käyttävät tiukkaa vertailua, kun taas floor ja ceiling sallivat yhtäsuuruuden.*/