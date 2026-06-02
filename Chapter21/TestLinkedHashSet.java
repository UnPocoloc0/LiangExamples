import java.util.*;

public class TestLinkedHashSet {
	
	public static void main(String[] args) {
		
		// Create a hash set
		Set<String> set = new LinkedHashSet<>();
		
		// Add strings to the set
		set.add("London"); 
		set.add("Paris"); 
		set.add("New York"); 
		set.add("San Francisco"); 
		set.add("Beijing"); 
		// dublikaatti
		set.add("New York");
		
		// lisäysjärjestys säilyy
		System.out.println(set);
		
		// Display the elements in the hash set
		for (String element: set) 
		System.out.print(element.toLowerCase() + " ");
	} 
}

/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:

Demonstroida LinkedHashSetin toimintaa

Näyttää ero HashSetiin:

säilyttää lisäysjärjestyksen
mutta ei salli duplikaatteja
Tulostaa kaikki alkiot pienillä kirjaimilla

Keskeinen TRA1-ajatus:
LinkedHashSet = HashSet + linkitetty lista, joka säilyttää järjestyksen.

2️⃣ TARKENNUS: käytetyt rakenteet
Osa	Selitys
Set<String>	Abstrakti tietotyyppi (ADT)
LinkedHashSet<>	Konkreettinen toteutus
add()	Lisää alkion, jos ei ole jo joukossa
for-each	Iteroi alkiot lisäysjärjestyksessä
toLowerCase()	String-metodi

3️⃣ OHJELMAN KULKU ASKEL ASKELELTA

Luodaan tyhjä LinkedHashSet<String>

Lisätään kaupungit:
"London", "Paris", "New York", "San Francisco", "Beijing", "New York"
Toinen "New York" ei lisäänny (Set-sääntö)
Tulostetaan koko set → näkyy lisäysjärjestyksessä
Iteroidaan set for-each -silmukalla
Tulostetaan jokainen alkio pienillä kirjaimilla

4️⃣ PSEUDOKOODI (luonnollinen)
set ← uusi LinkedHashSet

lisää "London"
lisää "Paris"
lisää "New York"
lisää "San Francisco"
lisää "Beijing"
lisää "New York"   // ei lisäänny

tulosta set

for jokainen elementti setissä:
	tulosta elementti pienillä kirjaimilla
	
5️⃣ TULOSTUS (tyypillinen)
[London, Paris, New York, San Francisco, Beijing]
london paris new york san francisco beijing


👉 Huomaa:

järjestys säilyy
ei duplikaatteja

6️⃣ AIKAVAATIVUUS

LinkedHashSet perustuu:

hash-taulukkoon → nopeus

linkitettyyn listaan → järjestys

Operaatio	Aikavaativuus	Miksi
add()	O(1) keskimäärin	hash-taulukko
remove()	O(1) keskimäärin	hash + linkitys
contains()	O(1) keskimäärin	hash
Iterointi	O(n)	käydään kaikki alkiot läpi
Muistin käyttö	suurempi järjestyksen säilytys

➡️ Verrattuna HashSetiin:

hieman enemmän muistia
lähes sama suorituskyky

7️⃣ VERTAILU (TRA1-kultaa)
Rakenne	Järjestys	Aikavaativuus
HashSet	❌ ei	O(1)
LinkedHashSet	✅ lisäysjärjestys	O(1)
TreeSet	✅ järjestetty	O(log n)

8️⃣ TENTTIVASTAUS (tiivistetty)

LinkedHashSet on Set-toteutus, joka säilyttää alkioiden lisäysjärjestyksen.
Se ei salli duplikaatteja ja tarjoaa keskimäärin vakioaikaiset operaatiot hash-taulukon ansiosta.

9️⃣ TRA1-MUISTISÄÄNTÖ
LinkedHashSet =
HashSet + järjestys

- ei duplikaatteja
- lisäysjärjestys säilyy
- O(1) keskimäärin


Lyhyt vastaus

Kyllä, rajapinta (interface) vastaa abstraktia tietotyyppiä (ADT)
kun se kuvaa tietorakenteen käyttäytymisen eikä toteutusta.

Set on tästä oppikirjaesimerkki.

1️⃣ Mitä abstrakti tietotyyppi (ADT) tarkoittaa?

ADT määrittelee:
mitä operaatioita on olemassa
mitä ne tekevät loogisesti
ei miten ne on toteutettu

Esim. Set:

add
remove
contains
ei duplikaatteja

👉 ei kerro, onko toteutus hash-taulukko, puu tai lista

2️⃣ Rajapinta Javassa

Java interface:

määrittelee metodit

ei sisällä tietorakenteen toteutusta

ei sisällä tilaa (kenttiä) merkityksellisessä mielessä

Esimerkki:

public interface Set<E> {
	boolean add(E e);
	boolean remove(Object o);
	boolean contains(Object o);
}


👉 Tämä on täsmälleen ADT:n määritelmä:

käyttäytyminen
ei toteutusta

3️⃣ Miksi juuri Set on ADT
Set<String> set = new HashSet<>();


Set = abstrakti tietotyyppi
HashSet = konkreettinen toteutus
Koodi ei riipu siitä, miten Set on toteutettu

Voit vaihtaa:
Set<String> set = new TreeSet<>();
…ilman, että muu koodi muuttuu.

➡️ Tämä on ADT-ajattelun ydin.

4️⃣ Mutta: kaikki rajapinnat eivät ole ADT:itä
Tämä on se tarkennus.
Rajapinta voi olla ADT, jos:
se kuvaa tietorakennetta
sillä on loogiset perusoperaatiot
Esimerkkejä ADT-rajapinnoista:

Set
List
Queue
Map

Esimerkkejä rajapinnoista, jotka eivät ole ADT:
Runnable
Comparable
Comparator

👉 Nämä kuvaavat käyttäytymistä, eivät tietorakennetta.

5️⃣ Yhteenvetotaulukko (tärkeä tenttiin)
Käsite	Onko ADT?	Miksi
Set	✅ kyllä	Kuvaa joukon toiminnan
List	✅ kyllä	Kuvaa listan toiminnan
Queue	✅ kyllä	Kuvaa jonon toiminnan
Map	✅ kyllä	Kuvaa avain–arvo-rakenteen
Comparator	❌ ei	Strategia, ei tietorakenne
Runnable	❌ ei	Käyttäytymisrajapinta

6️⃣ Tenttivastaus (mallimuotoinen)

Java-rajapinta vastaa abstraktia tietotyyppiä silloin, kun se määrittelee tietorakenteen loogisen käyttäytymisen ilman toteutusta. Esimerkiksi Set on abstrakti tietotyyppi, jonka konkreettisia toteutuksia ovat HashSet, TreeSet ja LinkedHashSet.

7️⃣ Muistisääntö

ADT = MITÄ voi tehdä
Toteutus = MITEN se tehdään
Set = ADT
HashSet = toteutus
*/
