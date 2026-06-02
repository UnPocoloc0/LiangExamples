public class BinarySearchTree<E extends Comparable<E>> {
	
	// Testiesimerkki
	public static void main(String[] args) {
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.insert(5);
		tree.insert(3);
		tree.insert(7);
		
		System.out.println(tree.search(7)); // true
		System.out.println(tree.search(4)); // false
	}
	
	private static class TreeNode<E> {
		E element;
		TreeNode<E> left;
		TreeNode<E> right;
		
		TreeNode(E element) {
			
			this.element = element;
			left = null;
			right = null;
		}
	}
	
	private TreeNode<E> root;
	
	public boolean search(E element) {
		
		TreeNode<E> current = root; // Start from the root
		
		while (current != null) {
			int cmp = element.compareTo(current.element);
			if (cmp < 0) {
				// Go left
				current = current.left; 
			} else if (cmp > 0) {
				// Go right
				current = current.right; 
			} else {
				// Element matches current.element
				return true; 
			}
		}
		return false; // Element is not in the tree
	}
	
	// Lisäesimerkkinä puun lisääminen
	public void insert(E element) {
		root = insertRec(root, element);
	}
	
	private TreeNode<E> insertRec(TreeNode<E> node, E element) {
		
		if (node == null) {
			return new TreeNode<>(element);
		}
		int cmp = element.compareTo(node.element);
		if (cmp < 0) {
			node.left = insertRec(node.left, element);
		} else if (cmp > 0) {
			node.right = insertRec(node.right, element);
		}
		return node; // tämä on välttämätön, koska se varmistaa, että solmu kiinnitetään oikein lapseksi
		// puu pysyy oikein linkitettynä??
	} // Rekursio
	
} // class
	
/*
	1️⃣ Ylätason kuvaus
	
	Koodi toteuttaa binäärisen hakupuun (BinarySearchTree) geneeriselle tyypille E, joka toteuttaa Comparable<E>-rajapinnan.
	Sen tärkeimmät osat:
	
	TreeNode-luokka: Puu koostuu solmuista, joilla on element, left ja right.
	
	insert(element): Lisää uuden alkion puuhun oikeaan kohtaan binäärisen hakupuun sääntöjen mukaan.
	
	search(element): Etsii puusta tietyn alkion ja palauttaa true jos se löytyy, muuten false.
	
	main(): Testaa puun toimintaa lisäämällä alkioita ja hakemalla niitä.
	
	Tärkein logiikka on vertailu solmun elementin kanssa:
	
	Jos etsitty elementti on pienempi → mennään vasemmalle.
	
	Jos etsitty elementti on suurempi → mennään oikealle.
	
	Jos elementti täsmää → löytyi.
	
	2️⃣ Asteittainen tarkistus (search-metodi)
	
	Käydään search-metodi läpi vaihe vaiheelta:
	
	TreeNode<E> current = root;
	
	
	Aloitetaan puun juuressa.
	
	while (current != null) {
	
	
	Käydään puu läpi niin kauan kuin solmuja on jäljellä.
	
	int cmp = element.compareTo(current.element);
	
	
	Verrataan etsittävää elementtiä nykyiseen solmuun (compareTo antaa:
	
	<0 → etsittävä pienempi
	
	0 → täsmää
	
	>0 → etsittävä suurempi)
	
	if (cmp < 0) {
		current = current.left;
	} else if (cmp > 0) {
		current = current.right;
	} else {
		return true;
	}
	
	
	Siirrytään vasemmalle, oikealle, tai palautetaan true jos löytyi.
	
	return false;
	
	
	Jos päädytään null-solmuun, elementtiä ei löydy.
	
	Tarkistettu: Logiikka on oikein ja toimii kaikille Comparable-tyypeille.
	
	3️⃣ Pseudokoodi
	FUNCTION search(element):
		current ← root
		WHILE current ≠ null:
			cmp ← compare element with current.element
			IF cmp < 0:
				current ← current.left
			ELSE IF cmp > 0:
				current ← current.right
			ELSE:
				RETURN true
		END WHILE
		RETURN false
	END FUNCTIO	
	

	1️⃣ Ylätason kuvaus
	
	insert-metodi lisää uuden alkion binääriseen hakupuuhun (BinarySearchTree) oikeaan paikkaan siten, että puun binäärisen hakupuun ominaisuus säilyy:
	
	Vasemman alipuolen solmut ovat pienempiä kuin juurisolmu.
	
	Oikean alipuolen solmut ovat suurempia kuin juurisolmu.
	
	Solmuja ei lisätä, jos elementti on jo puussa (tässä yksinkertaistetussa versiossa).
	
	Metodi koostuu kahdesta osasta:
	
	Public insert(E element) – julkinen kutsu, joka aloittaa rekursion juuresta.
	
	Private insertRec(TreeNode<E> node, E element) – rekursiivinen apufunktio, joka tekee varsinaisen sijoituksen.
	
	2️⃣ Asteittainen tarkistus (insertRec)
	
	Käydään insertRec-metodi läpi vaihe vaiheelta:
	
	if (node == null) {
			return new TreeNode<>(element);
	}
	
	
	Jos nykyinen solmu on tyhjä, luodaan uusi solmu ja palataan se ylös.
	
	Tämä on rekursion pohja.
	
	int cmp = element.compareTo(node.element);
	
	
	Verrataan lisättävää elementtiä nykyiseen solmuun.
	
	<0 → lisättävä pienempi
	
	0 → täsmää (ei lisätä)
	
	>0 → lisättävä suurempi
	
	if (cmp < 0) {
			node.left = insertRec(node.left, element);
	} else if (cmp > 0) {
			node.right = insertRec(node.right, element);
	}
	
	
	Jos elementti on pienempi → käydään vasemmalla alipuun solmulla.
	
	Jos elementti on suurempi → käydään oikealla alipuun solmulla.
	
	Jos elementti täsmää → ei tehdä mitään (estää duplikaatit).
	
	return node;
	
	
	Palautetaan nykyinen solmu ylös rekursion ketjuun, jotta puun rakenne säilyy.
	
	Tarkistettu: Tämä on tyypillinen rekursiivinen binäärisen hakupuun insert-logiikka. Se pitää puun järjestyksen oikein.
	
	3️⃣ Pseudokoodi
	FUNCTION insert(element):
			root ← insertRec(root, element)
	END FUNCTION
	
	FUNCTION insertRec(node, element):
			IF node = null THEN
					RETURN new TreeNode(element)
			END IF
			
			cmp ← compare element with node.element
			IF cmp < 0 THEN
					node.left ← insertRec(node.left, element)
			ELSE IF cmp > 0 THEN
					node.right ← insertRec(node.right, element)
			ELSE
					// element already exists, do nothing
			END IF
			
			RETURN node
	END FUNCTION
	
	
	Jos haluat, voin tehdä seuraavaksi yhdistetyn pseudokoodin koko puusta, jossa näkyy sekä insert että search selkeästi yhdessä, niin siitä tulee suoraan Lian-esimerkin kaltainen “täydellinen malli”.
	
	Haluatko, että teen sen?
	
	Tee Insertista vähän visuaalisempi selitys pienellä esimerkkipuulla
	
	Selvä! Tehdään insert-metodista visuaalisempi selitys pienellä esimerkkipuulla. Otetaan yksinkertainen sarja lukuja:
	
	Lisättävät luvut järjestyksessä: 5, 3, 7, 4
	
	1️⃣ Vaihe 1: Lisätään 5
	
	Puu on tyhjä → 5 tulee juureksi.
	
			5
			/ \
		null null
		
	2️⃣ Vaihe 2: Lisätään 3
	
	Verrataan 3:ta juureen (5) → 3 < 5 → mennään vasemmalle.
	
	Vasemmalla on null → lisätään 3 sinne.
	
			5
			/ \
		3  null
		/ \
	null null
	
	3️⃣ Vaihe 3: Lisätään 7
	
	Verrataan 7:ää juureen (5) → 7 > 5 → mennään oikealle.
	
	Oikealla on null → lisätään 7 sinne.
	
			5
			/ \
		3   7
		/ \ / \
	null null null null
	
	4️⃣ Vaihe 4: Lisätään 4
	
	Verrataan 4:ää juureen (5) → 4 < 5 → mennään vasemmalle.
	
	Vasemmalla on 3 → verrataan 4:ää 3:een → 4 > 3 → mennään oikealle.
	
	Oikealla on null → lisätään 4 sinne.
	
			5
			/ \
		3   7
		/ \
	null 4
				/ \
			null null
			
	5️⃣ Mitä insert tekee tässä
	
	Käy puun läpi vertailu kerrallaan.
	
	Valitsee aina vasemman tai oikean alipuun.
	
	Kun päätyy tyhjään solmuun → luodaan uusi solmu.
	
	Binäärisen hakupuun ominaisuus säilyy: vasemmalla pienemmät, oikealla suuremmat.
	
	1️⃣ Koodi vs puu
	
	Katsotaan insertRec-koodin päälogiikkaa:
	
	if (node == null) {
		return new TreeNode<>(element);
	}
	
	
	Tämä vastaa puun visualisessa esimerkissä kohtaa, jossa löydämme tyhjän solmun, johon uusi luku lisätään.
	
	Esimerkissä se on esimerkiksi, kun lisätään 3 vasemmalle juuresta 5, ja siellä on null → luodaan uusi solmu.
	
	int cmp = element.compareTo(node.element);
	if (cmp < 0) {
		node.left = insertRec(node.left, element);
	} else if (cmp > 0) {
		node.right = insertRec(node.right, element);
	}
	
	
	Tämä on puuesimerkissä vasemmalle vai oikealle meneminen.
	
	Esimerkki: lisätään 4 → verrataan ensin 5 (4 < 5 → vasemmalle) → sitten 3 (4 > 3 → oikealle) → lisätään oikealle.
	
	Koodi tekee tämän rekursiivisesti, visuaalisessa esimerkissä tämä näkyy askel askeleelta solmuina.
	
	return node;
	
	
	Tämä on puun palauttaminen takaisin ylätasolle, jotta puun rakenne säilyy.
	
	Käytännössä “sitoo” uuden solmun takaisin puuhun.
	
	Visualisessa esimerkissä tämä on se, että kun lisätään 4, se pysyy oikealla solmussa 3 → koko puu päivittyy.
	
	2️⃣ Yhteenveto: miksi visuaalinen esimerkki auttaa
	
	Koodi on abstraktia: se käyttää node.left ja node.right ja rekursiota, mikä voi tuntua etäiseltä.
	
	Puu-esimerkissä näet konkreettisesti, minne jokainen luku menee.
	
	CompareTo ja </> -vertailut näkyvät selkeästi solmuvalintana: “mennään vasemmalle” tai “mennään oikealle”.
	
	Rekursio vastaa puun “askel askeleelta” läpikäyntiä, jota visuaalisesti seuraamme solmu solmulta.
	
	3️⃣ Käytännön nyrkkisääntö koodin ja puun yhdistämiseen
	
	Koodi = säännöt, joita tietokone seuraa.
	
	Puu-esimerkki = ihmisen näkymä, joka näyttää säännöt konkreettisesti.
	
	💡 Kun hahmotat ensin puun visuaalisesti, koodi muuttuu paljon helpommin ymmärrettäväksi: jokainen if (cmp < 0) vastaa yhtä “vasemmalle”-päätöstä puussa, ja return new TreeNode on uuden solmun luominen juuri siihen kohtaan.
	
	2️⃣ Miksi return node; tarvitaan?
	
	Kun rekursio käy läpi puun, se siirtyy alas puuhun vasemmalle tai oikealle.
	
	Kun uusi solmu luodaan, se palautetaan ylöspäin rekursion ketjua, jotta vanhempi solmu voi asettaa sen oikeaan paikkaan:
	
	node.left = insertRec(node.left, element);
	node.right = insertRec(node.right, element);
	
	
	return node; palauttaa vanhemman solmun ylöspäin, jotta puu pysyy oikein linkitettynä.
	
	Konkreettinen esimerkki:
	
	Lisätään 4 puuhun:
	
			5
			/ \
		3   7
		
	
	Rekursio menee näin:
	
	insertRec(5, 4) → 4 < 5 → rekursio vasemmalle: insertRec(3, 4)
	
	insertRec(3, 4) → 4 > 3 → rekursio oikealle: insertRec(null, 4)
	
	insertRec(null, 4) → luo solmun 4 ja palauttaa sen
	
	Palautusketju:
	
	node.right = 4 asettaa solmun 4 3:n oikeaksi lapseksi
	
	return node palauttaa solmun 3 ylöspäin
	
	node.left = 3 (juuressa 5) linkittää koko vasemman alipuun takaisin
	
	3️⃣ Yksinkertainen tapa ajatella:
	
	return new TreeNode<>(element) = “tässä luodaan uusi solmu”
	
	node.left = insertRec(...) = “asetan tämän solmun vasemmalle lapseksi”
	
	return node = “palautan vanhemman solmun ylätasolle, jotta koko puun rakenne pysyy koossa”
	
	💡 Ilman return node; juurisolmu ei pysy linkitettynä, ja puu voisi katketa.
	
	4️⃣ Analogia
	
	Ajattele puuta kuin ketjua käsiin sidottuja renkaista:
	
	Jokainen node on rengas.
	
	Kun lisäät uuden rengas-solmun (new TreeNode), sinun pitää palata takaisin ja kiinnittää se oikeaan paikkaan edelliseen renkaaseen.
	
	return node; on se, joka sanoo: “tässä on vanha rengas, johon uusi rengas on nyt kiinnitetty, jatka ylöspäin”.
	3️⃣ Yhteenveto
	
	return node; on välttämätön, koska:
	
	Se palauttaa vanhemman solmun ylöspäin rekursioon.
	
	Se varmistaa, että uusi solmu kiinnitetään oikein lapseksi.
	
	Ilman sitä insert ei toimi, puu pysyy muuttumattomana ja uudet solmut katoavat.
*/
