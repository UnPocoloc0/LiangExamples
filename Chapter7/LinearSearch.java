public class LinearSearch {
	
	public static void main(String[] args) {
		
		int[] list = {1, 4, 4, 2, 5, -3, 6, 2};
		
		int i = linearSearch(list, 4); // Returns 1
		int j = linearSearch(list, -4); // Returns -1 
		int k = linearSearch(list, -3); // Returns 5
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
	} // main
	
	/** The method for finding a key in the list */
	public static int linearSearch(int[] list, int key) { 
		// Palautetaan luvun ensimmäisen esiintymän indeksi
		for (int i = 0; i < list.length; i++) {			if (key == list[i])
			return i;
		}
		// Jos numeroa ei löydy listasta
		return -1;
	}
} // class


/*
🔭 Ylätason idea

Ohjelma etsii taulukosta tietyn arvon (key) ja palauttaa sen ensimmäisen esiintymän indeksin.

Jos arvoa ei löydy → palauttaa -1

Käytetään lineaarista etsintää (linear search)

🧩 Asteittainen tarkennus
1️⃣ main-metodi
int[] list = {1, 4, 4, 2, 5, -3, 6, 2};

Taulukko, josta etsitään arvoja

Kutsut:

int i = linearSearch(list, 4);   // löytyy ensimmäinen 4 → index 1
int j = linearSearch(list, -4);  // ei löydy → -1
int k = linearSearch(list, -3);  // löytyy → index 5


Tulostus:

System.out.println(i);
System.out.println(j);
System.out.println(k);

2️⃣ linearSearch-metodi
for (int i = 0; i < list.length; i++) {
		if (key == list[i])
				return i;
}
return -1;


Vaiheet:

Käy taulukon läpi alusta loppuun (i = 0..list.length-1)

Jos list[i] == key → palauttaa indeksin i heti

Jos koko taulukkoa käyty läpi eikä löydy → palauttaa -1

🔹 Esimerkki (ensimmäinen kutsu)

Taulukko: {1, 4, 4, 2, 5, -3, 6, 2}
Etsitään 4:

i	list[i]	key==list[i]?
0	1	ei
1	4	kyllä → return 1
🔹 Esimerkki (toinen kutsu)

Etsitään -4:

Käy koko taulukon läpi, ei löydy → palauttaa -1

✏️ Pseudokoodi ytimekkäästi
LINEARSEARCH(list, key)
	for i = 0..length(list)-1
			if list[i] == key
					return i
	return -1
	
⏱️ Aikavaativuus
Tapaus	Aikavaativuus
Paras tapaus	O(1) → löytyy heti alusta
Keskimääräinen tapaus	O(n/2) → lineaarinen, löytyy puolivälistä
Pahin tapaus	O(n) → ei löydy tai löytyy viimeisenä

Huom: n = list.length

Tämä on lineaarinen algoritmi, koska jokaista elementtiä voidaan joutua tarkistamaan

🔑 Tenttimuistisäännöt

Lineaarinen haku: käy taulukon läpi yksi kerrallaan

Palauttaa ensimmäisen esiintymän indeksin

Jos ei löydy → -1

Aikavaativuus O(n)

*/