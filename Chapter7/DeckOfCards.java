public class DeckOfCards {
  
  public static void main(String[] args) {
    
    // Pakan koko
    
    int[] deck = new int[52];
    
    // Maat alustettuna
    
    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    
    // Korttien arvot
    
    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
      
      "10", "Jack", "Queen", "King"};
    
    // Täytetään pakka kaikilla 52 eri kortilla järjestyksessä
    
    for (int i = 0; i < deck.length; i++) {
      
      deck[i] = i;
      
    }
    
    // Sekoituslooppi. Tässä muuttuja i on nykyinen kortti ja index satunnainen kortti.
    // Nämä kortit vaihdetaan keskenään ja tämä tehdään kaikille 52:lle kortille 
    
    for (int i = 0; i < deck.length; i++) {
      
      // palauta liukuluku 0-1 ja skaalaa sen välille, jonka jälkeen tyyppimuutos 
      int index = (int)(Math.random() * deck.length); 
      // ilman vaihtoa tulostuisi aina sama neljän kortin nelikko 
      int temp = deck[i];  // tallenna alkuperäinen kortti
      deck[i] = deck[index]; // // pakassa[i] saa satunnaisen kortin arvon
      deck[index] = temp; // satunnainen kortti saa alkuperäisen kortin arvon
      
    }
    
    // Display the first four cards
    for (int i = 0; i < 4; i++) {
      // Maa
      String suit = suits[deck[i] / 13];
      // Arvo
      String rank = ranks[deck[i] % 13]; 
      System.out.println("Card number " + deck[i] + ": "
        + rank + " of " + suit);
    }
  }
}

/*
🔭 Ylätason kuvaus

Ohjelma muodostaa 52 kortin pakan, sekoittaa sen satunnaisesti ja tulostaa neljä ensimmäistä korttia.

🧠 Asteittainen tarkennus (vaiheet)

Luo pakkaa vastaava taulukko (0–51)

Alusta kortit järjestyksessä

Sekoita pakka vaihtamalla alkioita satunnaisesti

Muunna kortin numero maaksi ja arvoksi

Tulosta neljä ensimmäistä korttia

🧩 Pseudokoodi 
create array deck[52]

for i = 0 to 51
deck[i] ← i

for i = 0 to 51
index ← random integer [0, 51]
swap deck[i] and deck[index]

for i = 0 to 3
suit ← suits[deck[i] / 13]
rank ← ranks[deck[i] mod 13]
print rank + " of " + suit

💡 Rakennehavainto (yhden rivin oivallus)

Kortti esitetään kokonaislukuna, jossa:

jako 13:lla → maa

jakojäännös 13:lla → arvo

⏱️ Aikavaativuus (jos kysytään)

Alustus: O(n)

Sekoittaminen: O(n)

Tulostus: O(1)

Kokonaisuus: O(n), missä n = 52

0–12   → Spades
13–25  → Hearts
26–38  → Diamonds
39–51  → Clubs
  
  Kortin numero	/13	Maa
  0–12	0	Spades
  13–25	1	Hearts
  26–38	2	Diamonds
  39–51	3	Clubs

*/
