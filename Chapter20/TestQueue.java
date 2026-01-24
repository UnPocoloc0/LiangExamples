import java.util.*;

public class TestQueue {
	
	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<>(); 
		
		queue.offer("Oklahoma");
		queue.offer("Indiana");
		queue.offer("Georgia");
		queue.offer("Texas"); 
		
		while (queue.size() > 0)
		// metodi poistaa jonon ensimmäisen alkion ja palauttaa sen
		System.out.print(queue.remove() + " ");
		
	} 
}

/*
1️⃣ YLÄTASON SUUNNITELMA (mitä ohjelma tekee)

Tavoite:
Ohjelma demonstroi jonon (Queue) perustoimintaa:
alkioiden lisäämistä jonoon ja niiden poistamista FIFO-periaatteella.

Keskeiset ideat (TRA1):

Rajapinta (Queue)

Konkreettinen toteutus (LinkedList)

FIFO (First In, First Out)

Perusoperaatiot: offer, remove, size

2️⃣ ENSIMMÄINEN TARKENNUS (rakenne ja vastuut)
📦 Käytetyt rakenteet
Queue<String>

Abstrakti tietotyyppi (ADT)

Määrittelee jonon toiminnot

Ei kerro, miten ne on toteutettu

LinkedList

Linkitetty lista

Toteuttaa Queue-rajapinnan

Sopii jonoksi, koska:

lisääminen loppuun on tehokasta

poistaminen alusta on tehokasta

3️⃣ TOINEN TARKENNUS (ohjelman kulku)
Pääohjelman logiikka

Luodaan tyhjä jono

Lisätään neljä merkkijonoa jonon perälle

Poistetaan ja tulostetaan alkiot yksi kerrallaan

Lopetus, kun jono on tyhjä

4️⃣ YDINLOGIIKKA PSEUDOKOODINA
Pääidea
luo tyhjä jono
lisää alkioita jonoon
niin kauan kuin jono ei ole tyhjä:
	poista jonon ensimmäinen alkio
	tulosta se
	
Tarkempi pseudokoodi
queue ← uusi tyhjä jono

queue.lisää("Oklahoma")
queue.lisää("Indiana")
queue.lisää("Georgia")
queue.lisää("Texas")

while queue.koko > 0:
	alkio ← queue.poista()
	tulosta alkio
	
5️⃣ TULOSTUSJÄRJESTYS (FIFO)

Lisäysjärjestys:

Oklahoma → Indiana → Georgia → Texas


Poistojärjestys:

Oklahoma Indiana Georgia Texas

6️⃣ AIKAVAATIVUUS (ERITTÄIN TÄRKEÄ TENTISSÄ)
Yksittäisten operaatioiden aikavaativuus

(LinkedList-toteutus)

Operaatio	Metodi	Aikavaativuus
Lisää jonon loppuun	offer	O(1)
Poista jonon alusta	remove	O(1)
Koon kysely	size	O(1)
Silmukan aikavaativuus

Silmukka suoritetaan n kertaa

Jokaisella kierroksella:

size() → O(1)

remove() → O(1)

print → O(1)

➡️ Koko while-silmukka:

O(n)

Koko ohjelman aikavaativuus
Osa	Aikavaativuus
Lisäykset (4 kpl)	O(1)
Poistot (n kpl)	O(n)
Yhteensä	O(n)
7️⃣ TYÖPÖYTÄVERTAUS (TRA1-muistisääntö)
Queue + LinkedList:
	lisää loppuun → nopea
	poista alusta → nopea
	

Jos toteutus olisi:

ArrayList → poisto alusta O(n) ❌

LinkedList → poisto alusta O(1) ✅

8️⃣ TENTTIVASTAUS (tiivis malli)

Ohjelma käyttää Queue-rajapintaa ja LinkedList-toteutusta FIFO-periaatteella.
Alkioiden lisääminen ja poistaminen ovat vakioaikaisia, ja koko ohjelman aikavaativuus on O(n).f


Ylätason vastaus

Jono (Queue) vaatii erityisesti:

lisäämistä loppuun

poistamista alusta

➡️ LinkedList tukee molempia tehokkaasti
➡️ ArrayList ei tue poistamista alusta tehokkaasti

2️⃣ Operaatiovertailu (ydintaulukko tenttiin)
Operaatio jonossa	LinkedList	ArrayList
Lisää loppuun	O(1)         	O(1) (amortisoitu)
Poista alusta	O(1)	            O(n) ❌
FIFO-käyttö	 ✅ optimaalinen	❌ tehoton

Keskeinen ero:
ArrayListissa alusta poistaminen vaatii kaikkien muiden alkioiden siirtämistä vasemmalle.

3️⃣ Miksi LinkedList toimii hyvin jonona?

LinkedList on:

linkitetty rakenne

jokainen alkio viittaa seuraavaan (ja edelliseen)

Kun poistetaan alusta:

head → head.next


➡️ ei siirtoja
➡️ vakioaikainen operaatio

4️⃣ Miksi ArrayList on huono jono?

ArrayList on:

taulukon päällä

indeksipohjainen

Kun poistetaan indeksistä 0:

[ A, B, C, D ] → poista A
[ B, C, D, _ ]


➡️ B, C ja D siirretään
➡️ O(n)

5️⃣ Mutta tärkeä tenttivivahde ⚠️

LinkedList ei ole aina parempi kuin ArrayList

Käyttötapa	Parempi
Jono (Queue)	✅ LinkedList
Pino (Stack)	✅ LinkedList
Satunnaishaku (get(i))	❌ ArrayList
Iterointi	usein ArrayList
Välimuistiystävällisyys	ArrayList

TRA1:ssä kysytään oikea rakenne oikeaan käyttöön.

6️⃣ Tenttivastaus (mallimuoto)

LinkedList soveltuu jonon toteutukseen paremmin kuin ArrayList, koska alkion lisääminen jonon loppuun ja poistaminen alusta ovat vakioaikaisia, kun taas ArrayListissa alusta poistaminen on lineaarista.
*/
