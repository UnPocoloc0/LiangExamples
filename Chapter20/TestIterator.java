import java.util.*;

public class TestIterator {
	
	public static void main(String[] args) {
		
		Collection<String> collection = new ArrayList<>();
		
		collection.add("New York");
		collection.add("Atlanta");
		collection.add("Dallas");
		collection.add("Madison");
		
		Iterator<String> iterator = collection.iterator();
		
		while (iterator.hasNext()) {
			
			System.out.print(iterator.next().toUpperCase() + " ");
		}
		System.out.println();
	}
}

/*
🔹 Ylätason kuvaus (mitä ohjelma tekee)

Ohjelma:

luo kokoelman merkkijonoja

hankkii kokoelmalle iteraattorin

käy kokoelman alkiot läpi yksi kerrallaan

tulostaa jokaisen alkion isoilla kirjaimilla

Keskeinen idea:
👉 kokoelman läpikäynti rajapinnan (Iterator) avulla, ei indeksien.

🔹 Asteittainen tarkennus (miten se etenee)
1️⃣ Kokoelman luonti
Collection<String> collection = new ArrayList<>();


Ohjelmoidaan Collection-rajapintaa vasten

Toteutus on ArrayList

2️⃣ Alkioiden lisääminen
collection.add("New York");
...


Lisätään neljä kaupunkia kokoelmaan

3️⃣ Iteraattorin hankinta
Iterator<String> iterator = collection.iterator();


Pyydetään kokoelmalta iteraattori

Iteraattori on olio, joka:

tietää missä kohtaa kokoelmaa ollaan

osaa siirtyä seuraavaan alkioon

4️⃣ Läpikäynti while-silmukalla
while (iterator.hasNext()) {
	System.out.print(iterator.next().toUpperCase() + " ");
}


hasNext() → onko vielä alkioita?

next() → palauttaa seuraavan alkion ja siirtyy eteenpäin

toUpperCase() → muuntaa merkkijonon isoiksi kirjaimiksi

Tulostus tapahtuu yksi alkio kerrallaan

🔹 Pseudokoodi (ytimekäs)
luo kokoelma
lisää merkkijonoja

hae iteraattori kokoelmasta

niin kauan kuin on seuraava alkio:
	hae seuraava alkio
	muuta se isoiksi kirjaimiksi
	tulosta
	
🧠 TRA1-ajatus

Iteraattori mahdollistaa:

kokoelman läpikäynnin riippumatta toteutuksesta

turvallisen poistamisen (iterator.remove)

Ei käytetä indeksejä → toimii myös kokoelmille, joilla ei ole indeksejä (esim. Set)

🔑 Muistisääntö

Iterator on olio, joka kulkee kokoelman läpi yksi alkio kerrallaan.
hasNext() kysyy, next() siirtyy.

1️⃣ Mitä tarkoittaa “toteutuksesta riippumatta”?

Ajatus on tämä:

❌ Ilman iteraattoria (toteutuksesta riippuvainen)

Jos käyttäisit indeksejä:

for (int i = 0; i < list.size(); i++) {
	System.out.println(list.get(i));
}


Tämä toimii vain, jos:

kokoelmalla on indeksit (get(i))

kokoelman tyyppi on esim. ArrayList

👉 Tämä ei toimi:

Set-kokoelmilla

LinkedList-tyylisillä rakenteilla samalla tavalla

yleisesti Collection-rajapinnalla

✅ Iteraattorin kanssa (toteutuksesta riippumaton)
Iterator<String> it = collection.iterator();
while (it.hasNext()) {
	System.out.println(it.next());
}


Tämä toimii kaikille:

ArrayList

LinkedList

HashSet

TreeSet

mille tahansa Collection-toteutukselle

💡 Et välitä siitä, miten kokoelma on toteutettu sisäisesti
(et tiedä, onko se taulukko, linkitetty lista vai puu).

➡️ Tämä on se “toteutuksesta riippumatta”.

2️⃣ Iteraattori pienellä vs isolla i: mitä eroa?

Tämä on tärkeä erotus 👇

🔹 Iterator (iso I)

Rajapinta (interface)

Määrittelee metodit:

boolean hasNext();
E next();
void remove();


👉 Tämä on sopimus:
“Jos joku on iteraattori, sen täytyy osata nämä metodit.”

🔹 iterator (pieni i)
Iterator<String> iterator = collection.iterator();


Tämä on muuttuja

Se viittaa konkreettiseen iteraattori-olioon

Olio on jonkin luokan toteutus, esim.:

ArrayList$Itr

HashMap$KeyIterator
(näitä ei yleensä näe)

👉 Sama kuin:

List vs list

Collection vs collection

3️⃣ Miten nämä liittyvät toisiinsa?

Ajattele näin:

Iterator      ← rajapinta (mitä osataan)
	↑
konkreettinen iteraattori ← miten se on toteutettu
	↑
iterator-muuttuja ← viittaa siihen olioon


Sinä:

ohjelmoit rajapintaa vasten (Iterator)

et välitä, mikä konkreettinen luokka sen toteuttaa

4️⃣ Miksi tämä on hyvä asia?
✅ Edut

Koodi toimii kaikille kokoelmille

Vähemmän virheitä

Parempi laajennettavuus

Sama ajattelumalli kuin oikeassa tiimikehityksessä

Tämä on täsmälleen sama idea kuin:

Collection<String> c = new ArrayList<>();


etkä:

ArrayList<String> c = new ArrayList<>();

5️⃣ Lyhyt muistisääntö 🧠

Iterator (iso I) = rajapinta
iterator (pieni i) = muuttuja

Iteraattori = tapa kulkea kokoelman läpi
riippumatta siitä, miten kokoelma on toteutettu

iterator (pieni i) = muuttuja, johon tallennetaan palautettu olio

.iterator() = metodi, joka luo iteraattorin olion

Iterator (iso I) = rajapinta, jonka olio toteuttaa

*/
