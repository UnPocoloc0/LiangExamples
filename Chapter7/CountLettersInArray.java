public class CountLettersInArray {  
    public static void main(String[] args) {
        // Declare and create an array
        char[] chars = createArray();
        // Display the array
        System.out.println("The lowercase letters are:");
        displayArray(chars);
        int[] counts = countLetters(chars);
        // Display counts
        System.out.println();    System.out.println("The occurrences of each letter are:");     displayCounts(counts);
      }
  
  /** Create an array of characters */
    public static char[] createArray() {
        // Declare an array of characters and Array it
    char[] chars = new char[100];
    // Create lowercase letters randomly and assign them to the array
    for (int i = 0; i < chars.length; i++)
    chars[i] = RandomCharacter.getRandomLowerCaseLetter();
    return chars;
    
  }
    public static void displayArray(char[] chars) {
    // Display the characters in the array 20 on each line
    for (int i = 0; i < chars.length; i++) { 
      // rivinvaihto tulee 20:n merkin jälkeen
      if ((i + 1) % 20 == 0)
      System.out.println(chars[i]);
      else
      // muutoin tulostuu kirjaimet välilyönnillä erotettuna
      System.out.print(chars[i] + " ");
    }
  } 
    public static int[] countLetters(char[] chars) {
        // Declare and create an array of 26 int
    int[] counts = new int[26];
    // For each lowercase letter in the array, count it
    for (int i = 0; i < chars.length; i++)
    // tällä lasketaan taulukon indeksi
    counts[chars[i] - 'a']++;
    
    return counts;
  }
  
  /** Display counts */
  public static void displayCounts(int[] counts) { 
    
    for (int i = 0; i < counts.length; i++) {
      
      if ((i + 1) % 10 == 0)
      System.out.println(counts[i] + " " + (char)(i + 'a'));
      else
      System.out.print(counts[i] + " " + (char)(i + 'a') + " ");
    }
      }
  } // class

class RandomCharacter {
  
  /** Generate a random lowercase letter */
  public static char getRandomLowerCaseLetter() {
    // luku edustaa englannin kielen pienten kirjaimin määrää
    // Unicode luku muunnetaan takaisin merkiksi 
    return (char)('a' + Math.random() * 26);
  }
}

/*
🔭 Ylätason kuvaus (mitä ohjelma tekee)

Ohjelma:

luo taulukon satunnaisia pieniä kirjaimia

tulostaa kirjaimet

laskee, montako kertaa kukin kirjain esiintyy

tulostaa esiintymismäärät

🧩 Asteittainen tarkennus (miten se tehdään)
1️⃣ Taulukon luonti

varataan 100 merkin taulukko

täytetään se satunnaisilla kirjaimilla a–z

2️⃣ Taulukon tulostus

tulostetaan kirjaimet

20 merkkiä per rivi

3️⃣ Kirjainten laskenta

luodaan 26-paikkainen taulukko (a–z)

jokainen kirjain kasvattaa omaa laskuriaan

4️⃣ Tulosten tulostus

tulostetaan kunkin kirjaimen lukumäärä

10 tulosta per rivi

🧠 Keskeinen idea (yhdessä lauseessa)

Kirjain muunnetaan indeksiksi vähentämällä siitä 'a'.

✏️ Pseudokoodi (ytimekäs)
MAIN
chars ← createArray()
displayArray(chars)
counts ← countLetters(chars)
displayCounts(counts)

CREATEARRAY
chars[100]
for i = 0..99
chars[i] ← random letter a–z
return chars

DISPLAYARRAY(chars)
for each char in chars
print char (20 per line)

COUNTLETTERS(chars)
counts[26] ← 0
for each char in chars
index ← char − 'a'
counts[index]++
return counts

DISPLAYCOUNTS(counts)
for i = 0..25
print counts[i] and (i + 'a')

⏱️ Aikavaativuus (TRA1-yhteensopiva)
Osa	Aikavaativuus
createArray	O(n)
displayArray	O(n)
countLetters	O(n)
displayCounts	O(1) (26 alkiota)

Kokonaisuus:

O(n)


missä n = kirjainten määrä.

🔑 Muistisääntö tenttiin

Kun taulukko käydään kerran läpi, aikavaativuus on O(n).

Tämä on erinomainen perusesimerkki, koska siinä yhdistyy:

taulukot

metodit

viittaukset

laskentaindeksi (char - 'a')

aikavaativuus      


🔭 Ylätason idea

Luodaan satunnainen kokonaisluku väliltä 0–25 ja siirretään sitä vastaavaan kirjaimeen a–z.

🧩 Asteittainen tarkennus (rivi riviltä)
1️⃣ Math.random()
Math.random()


palauttaa liukuluvun

väli:

0.0 ≤ arvo < 1.0

2️⃣ Kerrotaan 26:lla
Math.random() * 26

väli muuttuu:

0.0 ≤ arvo < 26.0

3️⃣ Lisätään 'a'
'a' + (Math.random() * 26)

Tässä kohtaa tapahtuu tärkeä asia:

'a' on merkki, mutta

Java käsittelee sitä lukuna

ASCII / Unicode:

'a' = 97
'b' = 98
...
'z' = 122


Nyt arvoväli on:

97.0 ≤ arvo < 123.0

4️⃣ Castataan char:ksi
(char)(...)


desimaaliosa poistuu

kokonaisluku tulkitaan merkkinä

Esimerkkejä:

Lasku	Tulos
97.2 → (char)97	'a'
98.9 → (char)98	'b'
122.1 → (char)122	'z'


🔹 Ylätason idea

Lasketaan, kuinka monta kertaa kukin pieni kirjain ('a' … 'z') esiintyy taulukossa chars.

counts[0] → kuinka monta 'a'

counts[1] → kuinka monta 'b'

…

counts[25] → kuinka monta 'z'

🧩 Asteittainen tarkennus
1️⃣ Luodaan laskuritaulukko
int[] counts = new int[26];


26 alkiota → yksi kullekin kirjaimelle

Oletusarvo kaikkiin 0

2️⃣ Käydään merkit läpi
for (int i = 0; i < chars.length; i++)


Käydään taulukon jokainen merkki läpi

chars[i] = kyseinen kirjain

3️⃣ Lasketaan kirjaimen indeksi
chars[i] - 'a'


'a' = 97, 'b' = 98, …

Esim. chars[i] = 'c' → 'c' - 'a' = 99 - 97 = 2

Tämä antaa taulukon indeksin, johon kasvatetaan laskuria

4️⃣ Kasvatetaan laskuria
counts[chars[i] - 'a']++;


Kasvatetaan oikeaa kohtaa taulukosta 1:llä

Jokainen kirjain “kerää itselleen pisteen”

5️⃣ Palautetaan tulos
return counts;


counts sisältää kaikkien 26 kirjaimen esiintymät

🔹 Konkreettinen esimerkki

Oletetaan:

chars = {'a', 'b', 'a', 'c'}
counts = new int[26]


Iteraatioiden jälkeen:

kirjain	indeksi	counts[indeksi]
'a'	0	1 → 2
'b'	1	0 → 1
'a'	0	2 → 3
'c'	2	0 → 1

Tuloksena: counts[0]=2, counts[1]=1, counts[2]=1, …

🔹 Ytimekäs pseudokoodi
counts[26] ← 0
for each char in chars:
  index ← char - 'a'
  counts[index]++
return counts
  
*/