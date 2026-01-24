import java.util.*; 

public class TestMethodsInCollection {
		public static void main(String[] args) { 		// Create set1
				Set<String> set1 = new HashSet<>(); 		// Add strings to set1 
				set1.add("London");				set1.add("Paris");				set1.add("New York");		set1.add("San Francisco");		set1.add("Beijing");
				System.out.println("set1 is " + set1);		System.out.println(set1.size() + " elements in set1"); 
				// Delete a string from set1 
		set1.remove("London");
				System.out.println("\nset1 is " + set1);		System.out.println(set1.size() + " elements in set1"); 
				// Create set2
		Set<String> set2 = new HashSet<>(); 		// Add strings to set2 
				set2.add("London");		set2.add("Shanghai");				set2.add("Paris");
				System.out.println("\nset2 is " + set2);		System.out.println(set2.size() + " elements in set2");
		
		System.out.println("\nIs Taipei in set2? " + set2.contains("Taipei"));
		
		set1.addAll(set2);
		System.out.println("\nAfter adding set2 to set1, set1 is " + set1);		
		set1.removeAll(set2);
		System.out.println("After removing set2 from set1, set1 is " 			+ set1);
		
		set1.retainAll(set2);
		System.out.println("After removing common elements in set2 " 			+ set1);
	}
	}

/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:

Demonstroida tärkeimmät Collection / Set -metodit Javassa:

add(), remove(), contains(), addAll(), removeAll(), retainAll()

Käytetään HashSet-toteutusta (ei järjestystä, uniikit alkiot)

Näytetään myös koko (size())

Keskeiset ideat TRA1-tasolla:

Set tallentaa uniikit alkiot → duplikaatit poistetaan automaattisesti

Set-metodit mahdollistavat joukko-operaatiot:

yhdistäminen (addAll)

erotus (removeAll)

leikkaus (retainAll)

Iterointi ei säilytä lisäysjärjestystä

2️⃣ TARKENNUS: luokat ja vastuut
Käytetty luokka / metodi	Rooli
Set<String> / HashSet<>	Tallentaa uniikkeja alkioita
add(E e)	Lisää alkion jos ei ole jo setissä
remove(Object o)	Poistaa tietyn alkion
contains(Object o)	Tarkistaa, onko alkio setissä
addAll(Collection c)	Lisää kaikki toisen joukon alkiot settiin
removeAll(Collection c)	Poistaa kaikki toisen joukon alkiot setistä
retainAll(Collection c)	Säilyttää vain toisen joukon kanssa yhteiset alkiot
size()	Palauttaa alkioiden määrän
3️⃣ OHJELMAN KULKU ASKEL ASKELELTA

Luo set1 → lisää 5 kaupunkia

Tulosta set1 ja koko (size())

Poista "London" set1:stä

Tulosta set1 ja koko

Luo set2 → lisää 3 kaupunkia (osa sama kuin set1)

Tulosta set2 ja koko

Tarkista, onko "Taipei" set2:ssa (contains)

Yhdistä set2 set1:een (addAll) → kaikki set2:n alkiot set1:een

Tulosta set1

Poista set2:n alkiot set1:stä (removeAll)

Tulosta set1

Säilytä vain yhteiset alkiot (retainAll)

Tulosta set1

4️⃣ PSEUDOKOODI
set1 ← uusi HashSet
set1.add("London")
set1.add("Paris")
set1.add("New York")
set1.add("San Francisco")
set1.add("Beijing")
tulosta set1, set1.size()

set1.remove("London")
tulosta set1, set1.size()

set2 ← uusi HashSet
set2.add("London")
set2.add("Shanghai")
set2.add("Paris")
tulosta set2, set2.size()

tulosta set2.contains("Taipei")

set1.addAll(set2)        // yhdistää set2:n set1:een
tulosta set1

set1.removeAll(set2)     // poistaa set2:n alkiot set1:stä
tulosta set1

set1.retainAll(set2)     // säilyttää vain yhteiset alkiot
tulosta set1

5️⃣ AIKAVAATIVUUS
Operaatio	HashSet	Kommentti
add(E e)	O(1) keskimäärin	hash-taulun ansiosta
remove(Object o)	 O(1) keskimäärin	
contains(Object o)	O(1) keskimäärin	
addAll(Collection c)	O(m)	m = lisättävien alkioiden määrä
removeAll(Collection c)	O(m)	m = poistettavien alkioiden määrä
retainAll(Collection c)	O(n)	n = alkuperäisen setin koko, tarvittaessa vertailu kaikkiin

Koko ohjelma: keskimäärin O(n + m) kaikille joukoille, koska operoidaan setin alkioilla hash-taulukon avulla

6️⃣ TENTTIVASTAUS (tiivistetty)

Set = uniikkien alkioiden kokoelma

HashSet = ei järjestystä, keskimääräinen O(1) perusoperaatiot

addAll = liittää toisen joukon

removeAll = poistaa toisen joukon alkiot

retainAll = säilyttää vain yhteiset alkiot

contains = tarkistaa alkion olemassaolon

7️⃣ TRA1-muistisääntö
Set-metodit:
- add(e) → lisää uniikin alkion
- remove(e) → poistaa alkion
- contains(e) → tarkistaa alkion
- addAll(c) → yhdistää toisen joukon
- removeAll(c) → poistaa toisen joukon alkiot
- retainAll(c) → säilyttää vain yhteiset
- size() → alkioiden määrä


HashSet → nopea, ei järjestystä

TreeSet → järjestetty, O(log n) operaatiot

Set = abstrakti tietotyyppi, toteutuksia useita

1️⃣ Mikä tekee HashSetistä “vakioaikaisen”?

Java HashSet perustuu hash-taulukkoon. Tämä on avain siihen, miksi useimmat operaatiot ovat O(1) keskimäärin.

2️⃣ HashSetin idea

Jokaiselle alkioille lasketaan hash-koodi (hashCode())

Hash-koodi kertoo mihin “koriin” (bucket) alkio sijoitetaan taulukossa

Kun lisätään, poistetaan tai tarkistetaan alkio:

Lasketaan hashCode → suuntaa oikeaan buckettiin

Tarkistetaan vain kyseisen bucketin sisältö

Koska bucketin koko on keskimäärin pieni (tasainen hajautus), operaatio vaatii vain muutaman vertailun

3️⃣ Esimerkki: add()
set1.add("Paris");


HashSet laskee "Paris".hashCode()

Menee oikeaan buckettiin

Tarkistaa, onko alkio siellä jo → jos ei, lisää

Koska bucketin koko keskimäärin vakio → add() on O(1) keskimäärin

4️⃣ Sama remove() ja contains()

Poisto tai tarkistus toimii samalla tavalla:

Lasketaan hashCode → bucket

Käydään vain bucket läpi

Keskiarvoisesti bucket ei kasva → O(1) per operaatio

5️⃣ Milloin aikavaativuus kasvaa?

Pahimmassa tapauksessa kaikki alkiot menevät samaan bucketiin (hash-konflikti)

Tällöin HashSet muuttuu pitkän listan tavoin → O(n) per operaatio

Käytännössä Java:n hash-funktiot ovat hyviä → pahimmassa tapauksessa harvinainen

6️⃣ Mitä tämä tarkoittaa koko ohjelmalle

Yksittäiset operaatiot (add, remove, contains) → O(1) keskimäärin

Jos toistetaan addAll tai removeAll m alkiolle → O(m)

Iterointi koko setin yli → O(n)

Näin saadaan “vakioaikaiset” perusoperaatiot keskimäärin

7️⃣ Yhteenveto
HashSet:
- perusoperaatiot (add, remove, contains) → O(1) keskimäärin
- perustuu hash-taulukkoon → bucketit jakavat alkiot tasaisesti
- pahimmassa tapauksessa → O(n), jos kaikki alkiot sama bucket
- iterointi koko setin yli → O(n)


Lyhyesti: HashSet on vakioaikainen keskimäärin, koska se hyödyntää hash-taulukkoa, joka jakaa alkiot tehokkaasti pieniin “koreihin”.

*/