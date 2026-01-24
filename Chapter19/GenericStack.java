// E on luokan oma tyyppi parametri 
// tämä ei ole oikea tyyppi vielä, mutta se on paikka merkki, joka täytetään kun olio luodaan
public class GenericStack<E> {
  
  // Tämä on olion kenttä, jokaisella GenericStack-oli jolla on oma listansa 
  private java.util.ArrayList<E> list = new java.util.ArrayList<>();
  
  public static void main(String[] args) {
    // E tyyppimuuttuja ei saa käyttää tässä
    // Nyt E = String
    GenericStack<String> stack1 = new GenericStack<>(); 
    GenericStack<String> stack3 = new GenericStack<String>();
    
    // Alkioiden lisääminen pinoon
    stack1.push("London");
    stack1.push("Paris");
    stack1.push("Berlin");
    stack1.push("Montreal");
    
    System.out.println(stack1);
    // Pinon koko
    System.out.println(stack1.getSize());
    // Tarkistetaan ylin alkio
    System.out.println(stack1.peek());
    // Tarkistetaan, onko pino tyhjä
    System.out.println(stack3.isEmpty());
    // Poistaminen 
    stack1.pop();
    System.out.println(stack1);
    
    // Nyt E = Integer
    GenericStack<Integer> stack2 = new GenericStack<>(); 
    stack2.push(1); // autoboxing 1 to new Integer(1) stack2.push(2);
    stack2.push(3);
  
    
    System.out.println(stack2);
  }
  
  // Apumetodit
  
  public int getSize() {
    // Tämä metodi kuuluu vain ArrayList-luokalle
    
    return list.size();
    
  }
  
  public E peek() {
    // Kutsutaan listan omaa metodia
    // tämä kertoo pinon koon ja kuuluukin Generic Stack-luokalle
    
    return list.get(getSize() - 1);
  }
  
  public void push(E o) {
    
    list.add(o);
  }
  
  
  public boolean isEmpty() {
    
    return list.isEmpty();
  }
  
  
  public E pop() {
    
    E o = list.get(getSize() - 1); 
    list.remove(getSize() - 1); 
    return o;
    
  }
  // Metodi yli kirjoitetaan jotta oli on sisältä voidaan tulostaa ymmärrettävässä muodossa 
  @Override
  public String toString() {
    return "stack: " + list.toString();
  }
}



/*
🔭 Ylätason yleiskuvaus

GenericStack toteuttaa pinon (stack) yleiskäyttöiselle tietotyypille (E).

Alkioita voi lisätä pinon päälle (push)

Ylimmän alkion voi katsoa (peek)

Ylimmän alkion voi poistaa (pop)

Pino toimii LIFO-periaatteella (Last In, First Out)

Toteutus käyttää sisäisesti ArrayListiä

🧩 Asteittainen tarkennus
1️⃣ Tietorakenne
private ArrayList<E> list;


list säilyttää pinon alkiot

Geneerinen tyyppi E → sama luokka toimii esim. String, Integer, Double, …

Pinon ylin alkio = listin viimeinen alkio

2️⃣ Pinon perusoperaatiot
🔹 push(E o)
list.add(o);


Lisää alkion pinon päälle

Käytännössä: lisätään listin loppuun

🔹 pop()
E o = list.get(size - 1);
list.remove(size - 1);
return o;


Ottaa viimeisen alkion

Poistaa sen listasta

Palauttaa poistetun alkion

🔹 peek()
return list.get(size - 1);


Palauttaa ylimmän alkion

Ei poista sitä

🔹 getSize()
return list.size();


Palauttaa alkioiden määrän

🔹 isEmpty()
return list.isEmpty();


Tarkistaa onko pino tyhjä

3️⃣ toString()
return "stack: " + list.toString();


Mahdollistaa pinon tulostamisen suoraan

Käyttää ArrayListin omaa toString()-metodia

✏️ Pseudokoodi (ytimekäs)
CLASS GenericStack<E>
  list ← empty ArrayList
  
  PUSH(element)
    add element to list
    
  POP()
    top ← last element of list
    remove last element from list
    return top
    
  PEEK()
    return last element of list
    
  SIZE()
    return number of elements in list
    
  ISEMPTY()
    return true if list is empty
    
⏱️ Aikavaativuus (TRA1-tasolla)
Operaatio	Aikavaativuus	Perustelu
push	O(1)	Lisäys listin loppuun
pop	O(1)	Poisto listin lopusta
peek	O(1)	Suora indeksointi
getSize	O(1)	Valmis tieto
isEmpty	O(1)	Valmis tieto
toString	O(n)	Käy kaikki alkiot läpi

n = pinon koko

🔑 Tenttimuistisäännöt

Stack = LIFO

Ylin alkio = listin viimeinen alkio

Generics (<E>) → sama rakenne toimii monelle tyypille

ArrayList + pino = helppo toteutus

Kaikki perusoperaatiot O(1)

*/
