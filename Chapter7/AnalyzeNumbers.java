import java.util.*;

public class AnalyzeNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
    // Syötteen avulla taulukon alustus
        System.out.print("Enter the number of items: ");
    // Int, koska tällä ei lasketa mitään, tämä on vain alkioiden määrä 
        int n = input.nextInt();    double [] numbers = new double[n];    double sum = 0;
    
    // Varsinaiset int-syötteet välilyönnillä erotettuna
        System.out.print("Enter the numbers: ");
    for (int i = 0; i < n; i++) { 
      // Ottaa taulukon koon verran syötteitä vastaan ja lisää ne summa-muuttujjaan
      // Täytetään edellisessä vaiheessa alustettu taulukko syötteillä
      numbers[i] = input.nextDouble();
      // Syötteet summataan
      sum += numbers[i];
    }
    // Tässä vaiheessa summa on selvillä
    double average = sum / n;
    // The number of elements above average 
    int count = 0;   
    
    // Loopataan alkioiden määrän verran
    
    for (int i = 0; i < n; i++) {
      // Taulukossa oleva alkio verrataan numeroiden keskiarvoon
      if (numbers[i] > average) 
      count++;
      
    }
        System.out.println("Average is " + average); 
    System.out.println("Number of elements above the average is "
      + count);
  }
  }

/*

🔭 Ylätason idea 

🧠 Algoritmin vaiheet algoritminotaationa

🧩 Tarkennettu pseudokoodi 

1️⃣ Ylätason kuvaus (”mitä tämä tekee?”)

Ohjelman tehtävä:

Lukee käyttäjältä joukon lukuja,
laskee niiden keskiarvon,
ja laskee kuinka monta lukua on keskiarvon yläpuolella.

Avainidea yhdellä rivillä:

Lue luvut → laske keskiarvo → vertaa jokaista keskiarvoon → laske ylittävät


Tenttivilkaisulla:

🧮 summa

📊 keskiarvo

🔁 kaksi silmukkaa

🔢 laskuri


2️⃣ Algoritmin vaiheet (algoritminotaatiolla)

“Luonnollinen algoritmikuvaus”

Algoritmi: AnalyzeNumbers

Lue lukumäärä n

Alusta taulukko kooltaan n

Alusta summa nollaksi

Toista n kertaa:

Lue luku

Tallenna taulukkoon

Lisää summaan

Laske keskiarvo

Alusta laskuri nollaksi

Käy taulukko läpi:

Jos alkio > keskiarvo, kasvata laskuria

Tulosta keskiarvo ja laskuri


3️⃣ Pseudokoodi (asteittain tarkennettu)
🔹 Versio A: hyvin tiivis (tenttivastaus-tyyli)
read n
create array numbers of size n
sum ← 0

for i = 0 to n-1
  read numbers[i]
  sum ← sum + numbers[i] Luetaan: aseta muuttujaan arvo. Tämä tarkoittaa sijoitusta
  
average ← sum / n
count ← 0

for i = 0 to n-1
  if numbers[i] > average
    count ← count + 1
    
print average
print count

🔹 Versio B: kommentoitu (reverse engineering -tuki)
read n                           // montako lukua
numbers ← new array of size n
sum ← 0                          // summa laskentaa varten

for each index i in numbers
  read value
  numbers[i] ← value           // tallennus. 
  sum ← sum + value            // kertyvä summa
  
average ← sum / n                // keskiarvo

count ← 0                        // keskiarvon ylittävien lukumäärä

for each index i in numbers
  if numbers[i] > average
    count ← count + 1
    
output average
output count

4️⃣ Rakenteellinen havainto

💡 Miksi kaksi silmukkaa?

1. silmukka: tiedon keruu + summa

2. silmukka: analyysi (vertailu keskiarvoon)

👉 Keskiarvoa ei voi käyttää ennen kuin kaikki luvut on luettu.

Tämä on klassinen kahden vaiheen algoritmi:

Kerää data → analysoi data

5️⃣ Aikavaativuus

// Taulukon täyttö: O(n)

// Keskiarvon ylittävien laskeminen: O(n)

Kokonaisuus: O(n)

Muistivaativuus:

Taulukko n alkiota → O(n)

6️⃣ Yhden lauseen tenttiselitys 

Ohjelma lukee n lukua taulukkoon, laskee niiden keskiarvon ja laskee kuinka moni luvuista ylittää keskiarvon.


*/


