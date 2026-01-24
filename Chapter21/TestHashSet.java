import java.util.*;

public class TestHashSet {
    
    public static void main(String[] args) {
        
        // Create a hash set
        
        Set<String> set = new HashSet<>();
        
        // Add strings to the set
        
        set.add("London");        
        set.add("Paris");        
        set.add("New York");        
        set.add("San Francisco");        
        set.add("Beijing");
        
        // Duplikaatti
        set.add("New York");
        
        // Duplikaatti ei näy tulostuksessa, alkiot tulostuvat satunnaisessa järjestyksessä 
        System.out.println(set);
        
        // Display the elements in the hash set
        
        for (String s: set) { 
        // Samalle riville tulostus, erottimena välilyönti 
            System.out.print(s.toUpperCase() + " ");
            
        } 
    } 
}
/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:

Demonstroida HashSetin toimintaa Java-kielessä.

Lisätään merkkijonoja settiin → automaattisesti poistuu duplikaatit.

Tulostetaan set ja sen jälkeen kaikki alkiot isoilla kirjaimilla.

Keskeiset ideat TRA1-tasolla:

HashSet ei säilytä järjestystä → alkioiden järjestys on satunnainen

Set ei salli duplikaatteja

Iterointi toimii for-each -silmukalla

HashSet perustuu hash-taulukkoon → vakioaikaiset perusoperaatiot

2️⃣ TARKENNUS: luokat ja vastuut

Käytetty luokka	Rooli
HashSet<String>	Tietorakenne, joka tallentaa uniikkeja alkioita
Set<String>	Rajapinta, HashSet toteuttaa sen
add()	Lisää alkion settiin (jos ei ole jo olemassa)
for-each	Iteroi kaikki alkioiden yli
toUpperCase()	Muuttaa merkkijonon isoiksi kirjaimiksi
3️⃣ OHJELMAN KULKU ASKEL ASKELELTA

Luodaan HashSet set

Lisätään alkioita:

"London", "Paris", "New York", "San Francisco", "Beijing", "New York"

Huomaa: toinen "New York" ei lisäänny → set säilyttää vain uniikit

Tulostetaan set → järjestys satunnainen

Iteroidaan for (String s : set):

Tulostetaan jokainen alkio isoilla kirjaimilla

4️⃣ PSEUDOKOODI
set ← uusi HashSet // tässä nuoli tarkoittaa asettajaa, eli tallennetaan oikealla oleva arvo vasempaan muuttujan 

set.add("London")
set.add("Paris")
set.add("New York")
set.add("San Francisco")
set.add("Beijing")
set.add("New York")  // ei lisäänny, duplikaatti

tulosta set  // järjestys satunnainen

for s in set:
    tulosta s.toUpperCase()
    
5️⃣ TULOSTUS

Ensimmäinen tulostus (set):

[Paris, San Francisco, Beijing, London, New York]


Järjestys voi vaihdella

Iterointi isoilla kirjaimilla:

PARIS SAN FRANCISCO BEIJING LONDON NEW YORK


Sama järjestys kuin HashSetin sisäinen järjestys (satunnainen)

6️⃣ AIKAVAATIVUUS
Operaatio	HashSet (hash-taulukko)	Kommentti
add()	O(1) keskimäärin, O(n) pahimmassa	Hash-laskenta + tarkistus duplikaatista
contains()	O(1) keskimäärin	HashSet tarkistaa uniikkiuden
for-each	O(n)	Iterointi kaikkien alkioiden yli
toUpperCase()	O(m)	m = merkkijonon pituus, yleensä pieni

Koko ohjelman aikavaativuus (n alkioita, m keskim. merkkijonipituus):

O(n) + O(n * m) ≈ O(n)


Pahimmassa tapauksessa (hash-konfliktit) lisäys voi olla O(n), mutta käytännössä keskimäärin O(1) per lisäys

7️⃣ TENTTIVASTAUS (tiivistetty)

HashSet tallentaa uniikit alkiot, järjestys on satunnainen.
Lisäys ja duplikaattien tarkistus ovat keskimäärin O(1).
Iterointi kaikille n alkioille on O(n).

8️⃣ TRA1-muistisääntö
HashSet:
- uniikit alkioita, satunnainen järjestys
- add / contains → O(1) keskimäärin
- duplikaatit eivät lisäänny
- iterointi for-each → O(n)
- hyvä valinta, kun ei tarvitse järjestystä

Set on Java-raja­pinta (interface), ja voidaan ajatella sitä abstraktina tietotyyppinä (ADT, Abstract Data Type). Käydään tämä tarkasti läpi:

1️⃣ Mitä tarkoittaa “abstrakti tietotyyppi”?

Abstrakti tietotyyppi (ADT) määrittelee MITÄ operaatioita voidaan tehdä, EI MITÄ TOTEUTUS TEKEE.

Esimerkkejä ADT:stä:

List

Queue

Stack

Set

ADT kertoo:

mitä metodeja on käytettävissä (add, remove, contains)

minkä käyttäytymisen odotetaan (esim. Set ei salli duplikaatteja)

Mutta se ei kerro, miten data tallennetaan
→ käytännössä toteutus voi olla HashSet, TreeSet, LinkedHashSet

2️⃣ Set rajapintana
Set<String> set = new HashSet<>();

Set määrittelee abstraktin sopimuksen:

add(E e) → lisää alkion, jos ei ole jo olemassa

remove(Object o) → poistaa alkion

contains(Object o) → tarkistaa onko alkio olemassa

Toteutus (HashSet) huolehtii:

miten tiedot tallennetaan (hash-taulukko)

miten järjestys ja haku tehdään

3️⃣ Yhteys TRA1 ja tentti

Kun kysytään tentissä:

“Onko Set abstrakti tietotyyppi vai konkreettinen luokka?”

Oikea vastaus:

Set = abstrakti tietotyyppi (ADT), toteutetaan esim. HashSetillä, TreeSetillä

Set määrittelee käyttäytymisen, toteutus (HashSet) määrittelee rakenteen.

4️⃣ Visualisointi
Abstrakti tietotyyppi: Set
    |
    |-- HashSet (hash-taulukko, O(1) perusoperaatiot)
    |-- TreeSet (puu, O(log n) perusoperaatiot)
    |-- LinkedHashSet (säilyttää lisäysjärjestyksen)
    

Kaikki toteutukset noudattavat samaa Set-rajapinnan sopimusta

ADT = rajapinta, Toteutus = luokka

1️⃣ HashSet

Hash = hajautus, hajautustaulu (hash table)

Set = joukko (uniikit alkiot, ei duplikaatteja)

Suomeksi:

HashSet ≈ Hajautustaulujoukko


Idea:

Tallentaa alkioita taulukkoon hajautuksen perusteella

Lisäys, poisto ja haku ovat keskimäärin vakioaikaisia O(1)

2️⃣ TreeSet

Tree = puu (tässä binääripuu / punamusta-puu)

Set = joukko

Suomeksi:

TreeSet ≈ Puujoukko


Idea:

Tallentaa alkioita järjestettyyn puurakenteeseen

Lisäys, poisto ja haku ovat O(log n)

Järjestys on luonnollinen (Comparable) tai Comparatorin määrittelemä

3️⃣ Yhteenveto (suomeksi)
Java-luokka	Suomeksi	Ominaisuudet
HashSet	Hajautustaulujoukko	Ei järjestystä, uniikit alkiot, O(1) keskimäärin
TreeSet	Puujoukko	Järjestetty, uniikit alkiot, O(log n)

4️⃣ Muistisääntö TRA1:ssä

Set = joukko

HashSet = tehokas, ei järjestystä

TreeSet = järjestetty joukko

Molemmat toteuttavat Set-rajapinnan / abstraktin tietotyypin

1️⃣ Perinteinen tapa (Java 6 ja aiemmat)

Ennen Java 7:ää kirjoitettiin aina täsmällinen generics-tietotyyppi molemmille puolille:

Set<String> set = new HashSet<String>();


Vasemmalla: rajapinta / muuttujan tyyppi → Set<String>

Oikealla: luokan instanssi → new HashSet<String>()

Generics-tyyppi täsmäytetään molemmille puolille

2️⃣ Diamond operator (Java 7+)

Java 7 toi “diamond operator” < >, jolloin kääntäjä päättelee tyypin oikealta automaattisesti:

Set<String> set = new HashSet<>();


< > kertoo: käytetään samaa tietotyyppiä kuin vasemmalla

Et siis tarvitse kirjoittaa uudelleen <String> oikealle puolelle

Koodi on lyhyempää ja helpommin luettavaa

3️⃣ Miksi tämä toimii?

Kääntäjä tietää, että set on Set<String>

Kun kutsut esimerkiksi add("London"), kääntäjä tarkistaa tyyppiturvallisuuden automaattisesti

Eli generics-tyyppi säilyy, mutta kirjoittaminen on vähemmän toisteista

4️⃣ Yhteenveto
Set<String> set = new HashSet<String>();  // ennen Java 7
Set<String> set = new HashSet<>();        // Java 7+, diamond operator

Molemmat toimivat identtisesti

Diamond operator = tyyppiturvallinen + vähemmän kirjoittamista

Hyvä tapa TRA1- ja käytännön koodauksessa
*/
