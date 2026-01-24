import java.util.*;

public class TestMap {
	
	public static void main(String[] args) {
		
		// Create a HashMap
		Map<String, Integer> hashMap = new HashMap<>();
		
		hashMap.put("Smith", 30);
		hashMap.put("Anderson", 31);
		hashMap.put("Lewis", 29);
		hashMap.put("Cook", 29);
		
		System.out.println("Display entries in HashMap");
		System.out.println(hashMap + "\n");
		
		// Create a TreeMap from the preceding HashMap
		Map<String, Integer> treeMap = new TreeMap<>(hashMap);
		System.out.println("Display entries in ascending order of key");
		System.out.println(treeMap);
		
		// Create a LinkedHashMap
		Map<String, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
		
		linkedHashMap.put("Smith", 30);
		linkedHashMap.put("Anderson", 31);
		linkedHashMap.put("Lewis", 29);
		linkedHashMap.put("Cook", 29);
		
		// Display the age for Lewis
		System.out.println("\nThe age for " + "Lewis is " +
			linkedHashMap.get("Lewis"));
		
		System.out.println("Display entries in LinkedHashMap");
		System.out.println(linkedHashMap);
	}
}

/*
1️⃣ YLÄTASON SUUNNITELMA

Tavoite:

Havainnollistaa Map-rajapintaa ja kolmea eri toteutusta:

HashMap
TreeMap
LinkedHashMap

Näyttää, miten:

avain–arvo-parit tallennetaan
järjestys riippuu Map-toteutuksesta
LinkedHashMap voi säilyttää käyttöjärjestyksen

2️⃣ KESKEISET KÄSITTEET

Käsite	Selitys
Map<K,V>	Abstrakti tietotyyppi avain–arvo-pareille
Avain (key)	Yksilöllinen, ei duplikaatteja
Arvo (value)	Voi olla sama usealla avaimella
put(key, value)	Lisää tai korvaa arvon
get(key)	Hakee arvon avaimella

3️⃣ OHJELMAN RAKENNE JA KULKU

🔹 OSA 1: HashMap
Map<String, Integer> hashMap = new HashMap<>();

Mitä tapahtuu:
Ei säilytä järjestystä
Nopea (hajautustaulu)

hashMap.put("Smith", 30);
hashMap.put("Anderson", 31);
hashMap.put("Lewis", 29);
hashMap.put("Cook", 29);


Avain = nimi
Arvo = ikä
29 voi esiintyä useasti → arvot eivät ole yksilöllisiä

Tulostus:
System.out.println(hashMap);


➡️ Tulostusjärjestys on satunnainen

🔹 OSA 2: TreeMap
Map<String, Integer> treeMap = new TreeMap<>(hashMap);

Mitä tapahtuu:

TreeMap järjestää avaimet nousevaan järjestykseen
Käyttää avainten luonnollista järjestystä (String → aakkosjärjestys)

Tulostus:
{Anderson=31, Cook=29, Lewis=29, Smith=30}

🔹 OSA 3: LinkedHashMap (access-order)

Map<String, Integer> linkedHashMap =
	new LinkedHashMap<>(16, 0.75f, true);
	
Parametrien merkitys:
Parametri	Selitys
16	Alkukapasiteetti
0.75f	Load factor
true	Käyttöjärjestys (access order)
Avaimen käyttö muuttaa järjestystä
linkedHashMap.get("Lewis");


➡️ "Lewis" siirtyy viimeiseksi, koska sitä käytettiin viimeksi.

Tulostus näyttää:

{Smith=30, Anderson=31, Cook=29, Lewis=29}

4️⃣ PSEUDOKOODI
hashMap ← uusi HashMap
lisää (Smith, 30)
lisää (Anderson, 31)
lisää (Lewis, 29)
lisää (Cook, 29)
tulosta hashMap

treeMap ← uusi TreeMap(hashMap)
tulosta treeMap (avaimet järjestetty)

linkedHashMap ← uusi LinkedHashMap(accessOrder=true)
lisää samat avain–arvo-parit
hae arvo avaimella "Lewis"
tulosta linkedHashMap (käyttöjärjestyksessä)

5️⃣ AIKAVAATIVUUS (TRA1-TÄRKEÄ)
Map	put()	get()	containsKey()
HashMap	O(1) keskim.	O(1)	O(1)
TreeMap	O(log n)	O(log n)	O(log n)
LinkedHashMap	O(1)	O(1)	O(1)

⚠️ HashMap & LinkedHashMap → keskimäärin vakioaikaisia
⚠️ TreeMap → logaritminen (puurakenne)

6️⃣ DUPLIKAATIT MAPISSA

Avaimet:

eivät saa olla duplikaatteja

put() korvaa vanhan arvon

Arvot:

voivat olla samoja

7️⃣ TRA1-MUISTILISTA

Map ≠ Collection

Map ei tue for-each suoraan → iterointi:

keySet()

values()

entrySet()

TreeMap = järjestetty

LinkedHashMap(accessOrder=true) = LRU-tyylinen käyttäytyminen

8️⃣ YHDEN LAUSEEN YDIN

Tämä ohjelma vertailee HashMapin, TreeMapin ja LinkedHashMapin eroja avain–arvo-parejen tallennuksessa, järjestyksessä ja aikavaativuudessa.

🔑 Miksi entrySet()?

Map ei ole Collection, joten et voi tehdä suoraan:

for (...) : map


Jos haluat käsitellä avain–arvo-pareja yhdessä, oikea tapa on:

map.entrySet()

✅ Oikea tapa: avain + arvo samalla kierroksella
for (Map.Entry<String, Integer> entry : map.entrySet()) {
	System.out.println(
		"Key = " + entry.getKey() +
		", Value = " + entry.getValue()
	);
}

Mitä tässä tapahtuu?

entrySet() palauttaa joukon (Set) alkioita

Jokainen alkio on tyyppiä Map.Entry<K,V>

Entry sisältää:

getKey()

getValue()

❌ Miksi ei keySet() tässä?
for (String key : map.keySet()) {
	System.out.println(key + " -> " + map.get(key));
}


Toimii kyllä

Mutta:

hitaampi (ylimääräinen get)

kömpelömpi

ei paras käytäntö

👉 Tentissä ja oikeassa koodissa: entrySet on parempi

🧠 Muistisääntö (tentti!)
Haluan:
- vain avaimet → keySet()
- vain arvot → values()
- avain + arvo yhdessä → entrySet()

📌 TRA1-tyylinen vastaus yhdellä lauseella

Kun Mapista halutaan käsitellä avain–arvo-pareja yhdessä, käytetään entrySet()-metodia, joka palauttaa joukon Map.Entry-olioita.

🔍 Rivi kokonaisuudessaan
new LinkedHashMap<>(16, 0.75f, true);


Tämä kutsuu LinkedHashMapin konstruktorin, jossa annetaan kolme asetusta.

1️⃣ Ensimmäinen parametri: 16 → alkukapasiteetti
16

Mitä tarkoittaa:

HashMap / LinkedHashMap käyttää hajautustaulua

Tämä on kuinka monta "bucketia" aluksi varataan

Miksi 16?

Se on Java HashMapin oletusarvo

Hyvä yleiskäyttöinen aloituskoko

👉 Ei vaikuta logiikkaan, vain tehokkuuteen

2️⃣ Toinen parametri: 0.75f → load factor
0.75f

Mitä tarkoittaa:

Kuinka täynnä taulu saa olla ennen kuin se kasvatetaan

Kaava:

resize when size > capacity × loadFactor


Esim:

16 × 0.75 = 12


→ Kun 13. alkio lisätään, taulukko laajenee.

Miksi 0.75?

Hyvä kompromissi:

vähän törmäyksiä

ei liikaa muistia

👉 Tämäkin on Java HashMapin oletusarvo

3️⃣ Kolmas parametri: true → access order
true


TÄMÄ on tärkein tässä esimerkissä.

Vaihtoehdot:
Arvo	Järjestys
false	Lisäysjärjestys (insertion order)
true	Käyttöjärjestys (access order)
🔄 Mitä “access order” tarkoittaa?

Kun avainta:

haetaan (get)

päivitetään (put)

kyseinen alkio siirtyy jonon loppuun

Esimerkki:
map.put("A", 1);
map.put("B", 2);
map.put("C", 3);

map.get("A");


Tulostus:

B, C, A


👉 Viimeksi käytetty → viimeiseksi

4️⃣ Miksi tätä käytetään?

Tämä mahdollistaa mm.:

LRU-välimuistin (Least Recently Used)

Käytetyimpien alkioiden seurannan

Vanhojen alkioiden automaattisen poiston

(LinkedHashMapia voi jopa yliajaa removeEldestEntry())

5️⃣ PSEUDOKOODI
luo LinkedHashMap
	alkukapasiteetti = 16
	load factor = 0.75
	järjestys = käyttöjärjestys
	
6️⃣ YHDEN LAUSEEN TENTTIVASTAUS

Tämä konstruktori luo LinkedHashMapin, jossa hajautustaulun alkukoko on 16, täyttöaste 75 %, ja alkiot järjestetään käyttöjärjestyksen mukaan eikä lisäysjärjestyksen.

7️⃣ Nopea muistisääntö
LinkedHashMap(16, 0.75f, true)
= HashMap + linkitetty lista + käyttöjärjestys

*/