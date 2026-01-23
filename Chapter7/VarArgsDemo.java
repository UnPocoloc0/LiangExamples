public class VarArgsDemo {
    public static void main(String[] args) { 
    // Metodin kutsuminen yksittäisillä argumenteilla
    printMax(34, 3, 3, 2, 56.5); 
    // Varargs:n käy myös taulukko, taulukko luodaan suoraan metodikutsussa. Tästä tulee anonyymi taulukko 
    printMax(new double[]{1, 2, 3});
    // metodikutsu ilman argumentteja
    printMax();
      } // main
  
  // Metodi vastaanottaa nollasta useaan double-arvoa, tämä sallimme Tonille minkä tahansa määrän argumentteja 
  public static void printMax(double... numbers) { 
    
    if (numbers.length == 0) {
      System.out.println("No argument passed");       
      return;
    }
    // Oletetaan aluksi, että listan ensimmäinen on suurin
    double result = numbers[0];
    // metodi kääntyy taulukoksi, jolloin voidaan taulukon indeksiä ja pituutta käyttää normaalisti
    // Varargs:n avulla voidaan kutsua metodia ilman, että tarvitse luoda taulukkoa 
    for (int i = 1; i < numbers.length; i++) 
    if (numbers[i] > result)
    result = numbers[i];
    System.out.println("The max value is " + result);
      } 
} // class

/*
🔭 Ylätason idea

Ohjelma laskee ja tulostaa suurimman arvon annetusta joukosta lukuja.

voi antaa määrittelemättömän määrän lukuja (varargs)

tai taulukon.

🧩 Asteittainen tarkennus
1️⃣ main-metodi
printMax(34, 3, 3, 2, 56.5); 
printMax(new double[]{1, 2, 3});


Kutsuu printMax-metodia kahdella tavalla:

erilliset argumentit → varargs

taulukko → varargs toimii myös taulukon kanssa

2️⃣ printMax(double... numbers)

double... numbers → voi vastaanottaa minkä tahansa määrän double-lukuja

Javassa tämä muuntuu sisäisesti taulukoksi numbers[]

Vaiheet:

Tarkistetaan, onko yhtään lukua:

if (numbers.length == 0) { 
System.out.println("No argument passed"); 
return; 
}
Jos ei arvoja → ilmoitetaan ja lopetetaan

Oletetaan suurin luku ensimmäiseksi:

double result = numbers[0];


Käydään loput läpi ja etsitään maksimi:

for (int i = 1; i < numbers.length; i++)
if (numbers[i] > result)
result = numbers[i];


Jos uusi luku on suurempi kuin nykyinen result, päivitetään se

Tulostetaan suurin:

System.out.println("The max value is " + result);

✏️ Pseudokoodi ytimekkäästi
MAIN
printMax(34, 3, 3, 2, 56.5)
printMax([1,2,3])

PRINTMAX(numbers...)
if numbers.length == 0
print "No argument passed"
return
result ← numbers[0]
for i = 1..numbers.length-1
if numbers[i] > result
result ← numbers[i]
print "The max value is " + result

⏱️ Aikavaativuus
Osa	Aikavaativuus
printMax	O(n) (käy kaikki arvot läpi)
main	O(n1 + n2) (kaikkien kutsujen summana)

n = numbers.length

Tämä on lineaarinen, koska jokainen luku tarkastetaan kerran

🔑 Muistisäännöt tenttiin

double... numbers → varargs → taulukko

Maksimi haetaan lineaarisesti: O(n)

Taulukko- tai erilliset argumentit → molemmat toimivat

Tyhjä argumenttilista → tarkistus numbers.length == 0

*/
