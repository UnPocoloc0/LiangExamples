import java.util.*;
public class TestCollection {
	
	public static void main(String[] args) {
		
		// huonoa suunnittelua 
		ArrayList<String> collection1 = new ArrayList<>();
		collection1.add("New York");
		collection1.add("Atlanta");
		collection1.add("Dallas");
		collection1.add("Madison");
		
		System.out.println("A list of cities in collection1:");
		System.out.println(collection1);
		
		System.out.println("\nIs Dallas in collection1? "
			+ collection1.contains("Dallas"));
		
		collection1.remove("Dallas");
		System.out.println("\n" + collection1.size() +
			" cities are in collection1 now");
		
		// joustavampi tapa suunnitella
		Collection<String> collection2 = new ArrayList<>();
		collection2.add("Seattle");
		collection2.add("Portland");
		collection2.add("Los Angeles");
		collection2.add("Atlanta");
		
		System.out.println("\nA list of cities in collection2:");
		System.out.println(collection2);
		
		// Kloonaus palauttaa objektin, joten tämä pitää muuttaa listaksi 
		ArrayList<String> c1 = (ArrayList<String>)(collection1.clone());
		// Yhdiste 
		c1.addAll(collection2);
		System.out.println("\nCities in collection1 or collection2: ");
		System.out.println(c1);
		
		// käytetään samaa muuttujaa, joten ei tarvitse määritellä uudestaan 
		c1 = (ArrayList<String>)(collection1.clone());
		// Leikkaus
		c1.retainAll(collection2);
		System.out.print("\nCities in collection1 and collection2: ");
		System.out.println(c1);
		
		c1 = (ArrayList<String>)(collection1.clone());
		// Erotus
		c1.removeAll(collection2);
		System.out.print("\nCities in collection1, but not in 2: ");
		System.out.println(c1);
	}
}

/*
🔹 Ylätason kuvaus (mitä ohjelma tekee)

Ohjelma demonstroi Collection / ArrayList -rajapinnan perusoperaatioita:

luo kaksi kaupunkikokoelmaa

tarkistaa sisältöä

poistaa alkioita

yhdistää kokoelmia

laskee yhdisteen, leikkauksen ja erotuksen

Tämä on käytännössä joukko-opin perusoperaatioita Java-kokoelmilla.

🔹 Asteittainen tarkennus (miten se etenee)
1️⃣ Luodaan ensimmäinen kokoelma

collection1 on ArrayList<String>

Lisätään neljä kaupunkia

Tulostetaan lista

Tarkistetaan, onko "Dallas" mukana

Poistetaan "Dallas"

Tulostetaan kokoelman koko

2️⃣ Luodaan toinen kokoelma

collection2 on Collection<String> (rajapinta!)

Toteutus on silti ArrayList

Lisätään neljä kaupunkia

Tulostetaan lista

Tässä näet: ohjelmoidaan rajapintaa vasten, ei konkreettista luokkaa

3️⃣ Yhdiste (union)
c1 = clone(collection1)
c1.addAll(collection2)


➡️ kaikki kaupungit, jotka ovat jommassakummassa

4️⃣ Leikkaus (intersection)
c1 = clone(collection1)
c1.retainAll(collection2)


➡️ vain kaupungit, jotka ovat molemmissa

5️⃣ Erotus (difference)
c1 = clone(collection1)
c1.removeAll(collection2)


➡️ kaupungit, jotka ovat collection1:ssä mutta eivät collection2:ssa

🔹 Pseudokoodi (ytimekäs)
luo lista collection1
lisää kaupunkeja
tulosta collection1

tarkista sisältääkö "Dallas"
poista "Dallas"
tulosta koko

luo collection2
lisää kaupunkeja
tulosta collection2

c1 = kopio collection1
c1 = c1 ∪ collection2
tulosta c1

c1 = kopio collection1
c1 = c1 ∩ collection2
tulosta c1

c1 = kopio collection1
c1 = collection1 − collection2
tulosta c1

🧠 TRA1 / ajattelumalli

clone() → estää alkuperäisen listan muuttumisen

addAll → yhdiste

retainAll → leikkaus

removeAll → erotus

Collection-rajapinta → joustava ja hyvä suunnittelutapa

Tämä koodi ei ole vain Javaa, vaan:

joukko-opin peruskäsitteitä ohjelmointina

🔹 Mitä tarkoittaa

“ohjelmoidaan rajapintaa vasten, ei konkreettista luokkaa vastaan”?

Se tarkoittaa:

Muuttujan tyyppi on rajapinta (interface), mutta olio on jonkin luokan instanssi.

1️⃣ Konkreettinen esimerkki koodistasi
Collection<String> collection2 = new ArrayList<>();


Rajapinta: Collection

Konkreettinen luokka: ArrayList

Viittauksen tyyppi: Collection<String>

Olion tyyppi: ArrayList<String>

2️⃣ Miksi tämä tehdään?

Koska näin koodi on:

✅ joustavampaa
✅ helpommin muutettavaa
✅ paremmin suunniteltua

3️⃣ Mitä tämä mahdollistaa?

Tämän rivin voi vaihtaa:

Collection<String> collection2 = new ArrayList<>();


→ ilman muuta koodia koskematta:

Collection<String> collection2 = new LinkedList<>();


TAI:

Collection<String> collection2 = new HashSet<>();


Ja kaikki tämä toimii edelleen, jos käytät vain Collection-metodeja.

4️⃣ Vertaus (hyvin tärkeä)

Ajattele näin:

Rajapinta = sopimus / rooli

Luokka = toteutus

“Minulla on lista, joka osaa lisätä, poistaa ja sisältää alkioita”


→ ei kiinnosta miten se on toteutettu.


Rajapinta kertoo mitä tarvitaan, konkreettinen luokka valitaan tilanteen mukaan.

🔹 Mitä “tilanteen mukaan” tarkoittaa?

Se tarkoittaa yleensä jotakin näistä:

1️⃣ Suorituskyky

ArrayList → nopea satunnainen haku (get(i))

LinkedList → nopea lisäys/poisto alussa tai keskellä

HashSet → nopea sisältääkö (contains), ei järjestystä

2️⃣ Tarvitsetko järjestystä?

Kyllä → List / ArrayList

Ei → Set / HashSet

3️⃣ Tarvitsetko uniikkeja alkioita?

Kyllä → Set

Ei → List

4️⃣ Muuttuuko vaatimus myöhemmin?

Jos mahdollisesti:

ohjelmoi rajapintaa vasten

vaihda toteutus myöhemmin ilman refaktorointia

🔹 Esimerkki hyvästä suunnittelusta
List<String> cities = new ArrayList<>();


Myöhemmin:

cities = new LinkedList<>();


➡️ muu koodi ei muutu

🔹 Huono tapa (liian tiukka sidonta)
ArrayList<String> cities = new ArrayList<>();


➡️ sitoo koodin tiettyyn toteutukseen

🔹 Tämä on polymorfismin ydin

Sama rajapinta

Eri toteutukset

Sama koodi toimii eri tavoin
*/
