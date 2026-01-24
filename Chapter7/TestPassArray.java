public class TestPassArray {
  
  
  public static void main(String[] args) {
    
    int[] a = {1, 2};
    /** Swap two variables */
    
    // Swap elements using the swap method
    System.out.println("Before invoking swap"); 
    System.out.println("array is {" + a[0] + ", " + a[1] + "}"); 
    // Tämän metodin kutsuminen ei muuta alkuperästä taulukkoa
    swap(a[0], a[1]);
    
    System.out.println("After invoking swap"); 
    System.out.println("array is {" + a[0] + ", " + a[1] + "}");
    
    // Swap elements using the swapFirstTwoInArray method
    System.out.println("Before invoking swapFirstTwoInArray"); 
    System.out.println("array is {" + a[0] + ", " + a[1] + "}"); 
    swapFirstTwoInArray(a);
    
    System.out.println("After invoking swapFirstTwoInArray"); 
    System.out.println("array is {" + a[0] + ", " + a[1] + "}");
  }
  
  public static void swap(int n1, int n2) {
    int temp = n1;
    n1 = n2;
    n2 = temp;
  }
  
  // parametriksi valitetaan viittaus kopiona
  /** Swap the first two elements in the array */
  public static void swapFirstTwoInArray(int[] array) { 
    
    int temp = array[0];
    array[0] = array[1];
    array[1] = temp;
    
  }  
}

/*


🔭 Ylätason kuvaus

Ohjelma näyttää, että:

tavallisten muuttujien swap ei muuta alkuperäisiä arvoja

mutta taulukon sisällön swap toimii

👉 Syynä on mitä Java oikeasti välittää metodille.

🧠 Mitä tässä testataan?

Taulukko alussa:

a = {1, 2}


Kokeillaan kahta tapaa:

swap(a[0], a[1]) ❌

swapFirstTwoInArray(a) ✅

1️⃣ Miksi swap(a[0], a[1]) EI toimi?
Kutsu:
swap(a[0], a[1]);


Mitä tapahtuu oikeasti?

n1 on kopio arvosta 1

n2 on kopio arvosta 2

swap vaihtaa vain nämä kopiot

alkuperäinen taulukko a ei muutu

👉 Java on pass-by-value
→ arvot kopioidaan, ei sidota alkuperäisiin

Visualisointi
a[0] = 1      a[1] = 2

swap:
n1 = 1
n2 = 2


Swap tapahtuu vain n1 ja n2 välillä, ei a:ssa.

Tulostus
Before invoking swap
array is {1, 2}

After invoking swap
array is {1, 2}   ← ei muutu

2️⃣ Miksi swapFirstTwoInArray(a) TOIMII?
Kutsu:
swapFirstTwoInArray(a);

Mitä nyt tapahtuu?

array on viittaus samaan taulukkoon kuin a

kun muutat array[0], muutat suoraan taulukkoa a

siksi muutos näkyy pääohjelmassa

Visualisointi
a ──────▶ [1, 2]
array ───▶ [1, 2]

Sama taulukko, kaksi nimeä.

Swap muuttaa taulukon sisältöä, ei kopiota.

Tulostus
Before invoking swapFirstTwoInArray
array is {1, 2}

After invoking swapFirstTwoInArray
array is {2, 1}   ✅

🧩 Pseudokoodi (vertailu)
❌ Ei toimi
swap(x, y)
  temp ← x
  x ← y
  y ← temp
  

→ vaihtaa vain paikalliset kopiot

✅ Toimii
swapFirstTwoInArray(array)
  temp ← array[0]
  array[0] ← array[1]
  array[1] ← temp
  

→ muuttaa yhteistä rakennetta

🎯 Yhden lauseen tenttivastaus

Java välittää metodille arvon kopiona; taulukon tapauksessa kopio on viittaus samaan taulukkoon, joten sen sisältöä voidaan muuttaa.

🔑 Muistisääntö (tämä kannattaa painaa mieleen)

❌ Et voi vaihtaa kahta int-muuttujaa metodissa

✅ Voit vaihtaa taulukon alkioita metodissa

❌ Et voi muuttaa alkuperäistä arvoa kopion kautta

✅ Voit muuttaa olion/taulukon sisältöä viittauksen kautta

Java välittää aina arvon kopiona; primitiiveissä kopioidaan arvo, olioissa viittaus, 
jonka kautta olion sisältöä voidaan muuttaa.
Metodilla valitetaan viittaus kopiona.

Java antaa metodille aina kopion.
– joskus se kopio on luku
– joskus se kopio on viittaus

Aikavaativuus O(n), koska ei ole silmukoita, ei riippuvuutta syötön koosta ja kaikki operaatiot ovat vakioita. 
*/

