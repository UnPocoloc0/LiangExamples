import java.util.*;

public class TestArrayAndLinkedList {
	
	public static void main(String[] args) {
		
		List<Integer> arrayList = new ArrayList<>();
		
		// 1 is autoboxed to new Integer(1)
		arrayList.add(1); 
		arrayList.add(2);
		arrayList.add(3);		
		arrayList.add(1);
		arrayList.add(4);
		arrayList.add(0, 10);
		arrayList.add(3, 30);
		
		System.out.println("A list of integers in the array list:");
		System.out.println(arrayList);
		
		LinkedList<Object> linkedList = new LinkedList<>(arrayList);
		linkedList.add(1, "red");
		linkedList.removeLast();
		linkedList.addFirst("green");
		
		System.out.println("Display the linked list forward:");
		
		ListIterator<Object> listIterator = linkedList.listIterator();
		
		while (listIterator.hasNext()) {
			
			System.out.print(listIterator.next() + " ");
		}
		
		System.out.println(); 
		System.out.println("Display the linked list backward:");
		//  Tämä määrittää mistä ite roottori alkaa, halutaan aloittaa iteraatio listan lopusta 
		
		listIterator = linkedList.listIterator(linkedList.size());
		
		while (listIterator.hasPrevious()) {
						
			System.out.print(listIterator.previous() + " ");
		} 
	} 
}
/*
🎼 Yleiskuva (mitä ohjelma tekee)

Ohjelma:

Luo ArrayList<Integer>

Lisää siihen kokonaislukuja (eri kohtiin)

Luo LinkedList<Object>, joka kopioidaan ArrayListista

Muokkaa LinkedListiä (lisäys alkuun, lisäys väliin, poisto lopusta)

Käy LinkedListin läpi:

eteenpäin

taaksepäin

👉 Tärkein oivallus:

LinkedList ei synny tyhjästä – se kopioidaan ArrayListista.

🎯 Kohta, joka hämmentää sinua

“mistä nämä alkiot ilmestyvät linkitettyyn listaan?”

Tämä rivi on avain:

LinkedList<Object> linkedList = new LinkedList<>(arrayList);

🔑 Mitä tässä oikeasti tapahtuu?

arrayList sisältää jo arvoja

LinkedList-konstruktori saa Collectionin

Java kopioi kaikki alkiot arrayListista linkedListiin

👉 LinkedList EI jaa samaa muistia, vaan:

saa oman versionsa samoista alkioista

🧩 Puretaan koko ohjelma askel askeleelta
1️⃣ ArrayListin sisältö
List<Integer> arrayList = new ArrayList<>();


Lisäykset:

arrayList.add(1);   // [1]
arrayList.add(2);   // [1, 2]
arrayList.add(3);   // [1, 2, 3]
arrayList.add(1);   // [1, 2, 3, 1]
arrayList.add(4);   // [1, 2, 3, 1, 4]
arrayList.add(0, 10); // [10, 1, 2, 3, 1, 4]
arrayList.add(3, 30); // [10, 1, 2, 30, 3, 1, 4]


📌 Tulostus:

[10, 1, 2, 30, 3, 1, 4]

2️⃣ LinkedList syntyy ArrayListista
LinkedList<Object> linkedList = new LinkedList<>(arrayList);


Nyt linkedList sisältää:

[10, 1, 2, 30, 3, 1, 4]


👉 TÄSSÄ NE “ILMESTYVÄT”

3️⃣ LinkedListiä muokataan
linkedList.add(1, "red");


Lisää indeksille 1:

[10, red, 1, 2, 30, 3, 1, 4]

linkedList.removeLast();


Poistaa viimeisen (4):

[10, red, 1, 2, 30, 3, 1]

linkedList.addFirst("green");


Lisää alkuun:

[green, 10, red, 1, 2, 30, 3, 1]

🔁 ListIterator – miksi Object?
LinkedList<Object>


Koska listassa on nyt:

Integer

String ("red", "green")

➡️ yhteinen ylätyyppi = Object

▶️ Eteenpäin läpikäynti
ListIterator<Object> listIterator = linkedList.listIterator();


Tulostaa:

green 10 red 1 2 30 3 1

◀️ Taaksepäin läpikäynti
listIterator = linkedList.listIterator(linkedList.size());


Aloitetaan listan lopusta:

Tulostaa:

1 3 30 2 1 red 10 green

🧠 Tärkeimmät oivallukset
✅ 1. LinkedList EI ole tyhjä

Se saa sisältönsä tästä:

new LinkedList<>(arrayList)

✅ 2. Kokoelmat kopioidaan, eivät linkity toisiinsa

ArrayList ja LinkedList ovat eri kokoelmia

Muutos toisessa ei vaikuta toiseen

✅ 3. ListIterator osaa kulkea molempiin suuntiin

hasNext() / next()

hasPrevious() / previous()

🎼 Yksi lause koko ohjelmasta

Ohjelma demonstroi, miten ArrayListin sisältö voidaan kopioida LinkedListiin ja miten LinkedListiä voidaan muokata ja käydä läpi molempiin suuntiin iteraattorin avulla.

🔹 Iteraattori ≠ vakioaikaisuus automaattisesti

Iteraattori ei tee algoritmista O(1).
Iteraattori vain määrittelee tavan käydä kokoelma läpi ilman että tiedetään sen sisäistä rakennetta.

🔹 Katsotaan tätä koodia
while (listIterator.hasNext()) {
	System.out.print(listIterator.next() + " ");
}

Mitä tapahtuu?

hasNext() → O(1)

next() → O(1)

Silmukka suoritetaan n kertaa, missä n = listan koko

👉 Kokonaisaika:

O(1) + O(1) + ... n kertaa = O(n)

🔹 Entä taaksepäin iteraatio?
while (listIterator.hasPrevious()) {
	System.out.print(listIterator.previous() + " ");
}


Sama logiikka:

Jokainen askel on O(1)

Askeleita n kappaletta

👉 O(n)

🔹 Mikä sitten on vakioaikaista?
LinkedListissä:
Operaatio	Aikavaativuus
addFirst()	O(1)
removeLast()	O(1)
iterator.next()	O(1)
iterator.previous()	O(1)

Mutta:

Kun O(1)-operaatio tehdään n kertaa → O(n)

🔹 Miksi iteraattoria silti käytetään?

Koska ilman iteraattoria tämä voisi olla paljon hitaampaa:

for (int i = 0; i < linkedList.size(); i++) {
	linkedList.get(i);
}

LinkedListissä:

get(i) = O(n)

silmukka = n kertaa

👉 O(n²) 😱

Iteraattori:

ei hae indekseillä

liikkuu solmusta solmuun

👉 O(n) ❤️

🔹 Yhteenveto (tämä kannattaa muistaa tentissä)

❌ Iteraattori ei tee algoritmista vakioaikaista
✅ Iteraattori tekee läpikäynnistä lineaarisen ja tehokkaan
✅ Yksittäinen iteraattorin askel on O(1)
✅ Koko listan läpikäynti on O(n)

🔑 Kultainen lause

Iteraattori ei pienennä läpikäynnin aikavaativuutta O(n):stä, mutta se estää sen kasvamisen O(n²):een.
*/
