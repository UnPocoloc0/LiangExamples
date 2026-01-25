import java.util.*;

public class CountOccurrenceOfWords {
	
	public static void main(String[] args) {
		
		// Set text in a string
		String text = "Good morning. Have a good class. " +
		"Have a good visit. Have fun!";
		
		// Create a TreeMap to hold words as key and count as value
		Map<String, Integer> map = new TreeMap<>();
		
		String[] words = text.split("[ \n\t\r.,;:!?(){]");
		
		for (int i = 0; i < words.length; i++) {
			
			String key = words[i].toLowerCase();
			
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		
		// Get all entries into a set
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		
		for (Map.Entry<String, Integer> entry: entrySet)
		// Get key and value from each entry
		System.out.println(entry.getValue() + "\t" + entry.getKey());
	}
}

/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:

Laskea, kuinka monta kertaa kukin sana esiintyy tekstissä
Tallentaa tulos Mapiin, jossa:

avain = sana
arvo = esiintymiskertojen määrä

Tulostaa sanat aakkosjärjestyksessä

2️⃣ KESKEISET RAKENTEET

Osa	Rooli
String text	Käsiteltävä teksti
split(regex)	Jakaa tekstin sanoiksi
Map<String,Integer>	Avain–arvo-parit (sana → lukumäärä)
TreeMap	Järjestää avaimet aakkosjärjestykseen
entrySet()	Mahdollistaa avain–arvo-parien iteroinnin

3️⃣ OHJELMAN KULKU ASKEL ASKELELTA

🔹 Tekstin määrittely
String text = "Good morning. Have a good class. " +
				"Have a good visit. Have fun!";
				
Yksi merkkijono, jossa useita lauseita
Sisältää välimerkkejä ja isoja kirjaimia

🔹 Mapin luonti
Map<String, Integer> map = new TreeMap<>();

TreeMap:

järjestää sanat aakkosjärjestykseen
avaimet = sanat
arvot = lukumäärät

🔹 Tekstin pilkkominen sanoiksi
String[] words = text.split("[ \n\t\r.,;:!?(){}]");

Jakaa tekstin aina, kun vastaan tulee:

välilyönti
rivinvaihto
välimerkit

Tuloksena taulukko sanoja

🔹 Sanojen käsittely
for (int i = 0; i < words.length; i++) {
	String key = words[i].toLowerCase();
	
Käydään sanat läpi yksi kerrallaan
Muutetaan pieniksi kirjaimiksi:

"Good" ja "good" käsitellään samana sanana

🔹 Laskentalogiikka
if (key.length() > 0) {
	if (!map.containsKey(key)) {
		map.put(key, 1);
	} else {
		int value = map.get(key);
		value++;
		map.put(key, value);
	}
}

Jos sana ei ole vielä mapissa → lisää arvolla 1

Muuten:
hae vanha arvo
kasvata yhdellä
päivitä map

🔹 Tulostus entrySetillä
for (Map.Entry<String, Integer> entry : map.entrySet())
	System.out.println(entry.getValue() + "\t" + entry.getKey());
	
entrySet() antaa avain–arvo-parit

Tulostetaan:

ensin lukumäärä
sitten sana

4️⃣ PSEUDOKOODI

teksti ← annettu merkkijono
map ← uusi TreeMap

sanat ← jaa teksti välilyöntien ja välimerkkien kohdalta

for sana jokaisessa sanat:
	sana ← pieniksi kirjaimiksi
	if sana ei ole tyhjä:
		if sana ei ole mapissa:
			map[sana] ← 1
		else:
			map[sana] ← map[sana] + 1
			
for (sana, määrä) mapissa:
	tulosta määrä ja sana
	
5️⃣ AIKAVAATIVUUS (TRA1-TÄRKEÄ)

Oletetaan:

n = sanojen määrä tekstissä
m = eri sanojen määrä

Jokaiselle sanalle:

containsKey() → O(log m)
get() → O(log m)
put() → O(log m)

➡️ Kokonaisaika:

O(n log m)
Koska käytössä on TreeMap.

🔁 Jos olisi HashMap, keskimäärin:

O(n)

6️⃣ MIKSI TREE MAP?

Järjestetty tulostus ilman erillistä sorttia
Avaimet automaattisesti aakkosjärjestyksessä

7️⃣ TRA1-TENTTIYDIN

Map sopii laskureihin (frequency count)
TreeMap = järjestetty Map
entrySet() = paras tapa käsitellä avain–arvo-parit
Aikavaativuus riippuu Map-toteutuksesta

8️⃣ YHDEN LAUSEEN YDIN

Ohjelma laskee sanojen esiintymiskerrat tekstissä käyttäen TreeMapia, jolloin sanat tulostuvat automaattisesti aakkosjärjestyksessä.

🧠 Mitä regex tässä tarkoittaa?
"[ \n\t\r.,;:!?(){}]"

Luonnollisella kielellä:

Jaa teksti osiin aina, kun vastaan tulee
välilyönti
rivinvaihto
tabulaattori
piste, pilkku, huutomerkki
sulut tai aaltosulut

Eli sanat erotellaan välimerkeistä.

🔧 Vinkki: vielä selkeämpi regex

Usein käytetään tätä TRA1-kirjoissa:

text.split("[^a-zA-Z]+")

Tämä tarkoittaa:
jaa aina, kun vastaan tulee jotain muuta kuin kirjaimia

🔍 Rivi kokonaisuudessaan
Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

1️⃣ Mitä map.entrySet() tekee?

map on tyyppiä Map<String, Integer>

entrySet() palauttaa joukon (Set) olioita
Jokainen alkio on tyyppiä:
Map.Entry<String, Integer>

👉 Yksi Entry = yksi avain–arvo-pari

Esim:
"good" → 3

2️⃣ Miksi palautustyyppi on Set?

Mapissa avaimet ovat uniikkeja
Siksi avain–arvo-parien kokoelma on:

Set, ei List
ei voi olla kahta samaa Entryä samalla avaimella

3️⃣ Mitä Map.Entry<String, Integer> tarkoittaa?

Se on sisärajapinta, joka edustaa yhtä riviä Mapissa.
Sisältää metodit:

getKey()    // palauttaa avaimen (String)
getValue()  // palauttaa arvon (Integer)

4️⃣ Luonnollinen kieli (suomeksi)

“Hae Mapista kaikki avain–arvo-parit ja tallenna ne joukkona muuttujaan entrySet.”

Tai vielä arkisemmin:
“Ota Map sisällöstä lista (joukko) rivejä, joissa jokaisessa on sana ja sen lukumäärä.”

5️⃣ Miksi tätä käytetään?

Koska tämä on:

selkein
tehokkain
tenttikelpoisin

tapa käsitellä Mapia, kun tarvitaan sekä avain että arvo.

6️⃣ Vertailu muihin tapoihin
❌ Vähemmän hyvä
for (String key : map.keySet()) {
	System.out.println(map.get(key));
}

Tarvitsee erillisen get()
Hitaampi

Vähemmän selkeä

✅ Paras
for (Map.Entry<String, Integer> entry : map.entrySet()) {
	...
}

7️⃣ Yhden lauseen tenttivastaus

entrySet() palauttaa Mapin avain–arvo-parit Set-kokoelmana, jossa jokainen alkio on tyyppiä Map.Entry.

8️⃣ Muistisääntö
Map → entrySet() → Set<Entry<K,V>>
*/