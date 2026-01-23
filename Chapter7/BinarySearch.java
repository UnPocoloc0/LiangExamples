public class BinarySearch {
	
	public static void main(String[] args) {
		
		int[] list = {2, 4, 7, 10, 11, 45, 50, 59, 60, 66, 69, 70, 79}; 
		
		int i = BinarySearch.binarySearch(list, 2); // Returns 0
		int j = BinarySearch.binarySearch(list, 11); // Returns 4
		int k = BinarySearch.binarySearch(list, 12); // Returns –6
		int l = BinarySearch.binarySearch(list, 1); // Returns –1 
		int m = BinarySearch.binarySearch(list, 3); // Returns –2
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(l);
	} // main
	
	// Binäärihaussa alotan tietoa mihin kohtaan taulkkoa viitataan
	public static int binarySearch(int[] list, int key) { 
		// taulukon alku ja loppuindeksit omissa muuttujissa
		int low = 0;
		int high = list.length - 1;
	
		while (high >= low) {
			// Keskimmäinen indeksi. Tämä pyöristyy alaspäin, jos ei tule kokonaisluku
			int mid = (low + high) / 2; 
			// jos key on pienempi, niin rajataan oikea puoli pois 
			if (key < list[mid])
				high = mid - 1;
				
			else if (key == list[mid])
			return mid; 
			// jos key on suurempi, raja vasen puoli pois 
			else
				low = mid + 1;		}
		// Nyt low > high
				return -low -1; // Now high < low, key not found 
			}
	} // class

/*
🔭 Ylätason idea

Ohjelma etsii taulukosta tietyn arvon binäärisellä haulla.

Taulukko täytyy olla lajiteltu kasvavaan järjestykseen

Palauttaa joko:

löydetyn arvon indeksin

tai negatiivisen arvon jos ei löydy (-(insertion point) - 1), jotta voidaan tietää mihin kohtaan lisätä

🧩 Asteittainen tarkennus

Alustetaan rajat

int low = 0;
int high = list.length - 1;

low = taulukon alku
high = taulukon loppu

Iteroidaan niin kauan kuin alue on kelvollinen

while (high >= low) {
	int mid = (low + high) / 2;  // keskipiste
	

Lasketaan keskimmäinen indeksi mid

Vertailu ja rajojen supistaminen

if (key < list[mid])
	high = mid - 1;  // etsitään vasemmalta
else if (key == list[mid])
	return mid;      // löytyi
else
	low = mid + 1;   // etsitään oikealta
	

Jos key pienempi → rajaa oikea puoli pois

Jos key suurempi → rajaa vasen puoli pois

Jos key löytyy → palautetaan indeksi

Ei löytynyt

return -low - 1;


Palautetaan negatiivinen arvo, joka kertoo minne lisäys pitäisi tapahtua

✏️ Pseudokoodi ytimekkäästi
BINARYSEARCH(list, key)
	low ← 0
	high ← length(list) - 1
	
	while high ≥ low
		mid ← (low + high) / 2
		if key < list[mid]
			high ← mid - 1
		else if key == list[mid]
			return mid
		else
			low ← mid + 1
			
	return -low - 1  // key not found
	
⏱️ Aikavaativuus
Tapaus	Aikavaativuus
Paras tapaus	O(1) → löytyy heti keskeltä
Keskimääräinen tapaus	O(log n) → hakualuetta puolitetaan jokaisella stepillä
Pahin tapaus	O(log n) → ei löydy tai löytyy lopusta

n = taulukon pituus

Huom: Binäärinen haku on paljon nopeampi kuin lineaarinen O(n) suurille taulukoille

🔑 Tenttimuistisäännöt

Taulukko täytyy olla järjestetty

Rajat supistuvat aina puolittamalla hakualuetta

Palauttaa indeksin, jos löytyy

Palauttaa -(insertion point) - 1, jos ei löydy

Aikavaativuus O(log n)
*/