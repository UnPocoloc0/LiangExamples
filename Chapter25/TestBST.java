import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import javax.xml.transform.*;

// Asiakasluokka
public class TestBST {
	
	public static void main(String[] args) {
		
		// Create a BST
		BST<String> tree = new BST<>(); 
		
		// puuhun lisääminen 
		tree.insert("George"); 
		tree.insert("Michael"); 
		tree.insert("Tom"); 
		tree.insert("Adam"); 
		tree.insert("Jones"); 
		tree.insert("Peter"); 
		tree.insert("Daniel");
		
		// Traversointi
		System.out.print("Inorder (sorted): ");
		tree.inorder();
		
		System.out.print("\nPostorder: ");
		tree.postorder();
		
		System.out.print("\nPreorder: ");
		tree.preorder();
		
		System.out.print("\nThe number of nodes is " + tree.getSize());
		// Search for an element
		System.out.print("\nIs Peter in the tree? " + tree.search("Peter"));
		
		// Get a path from the root to Peter
		System.out.print("\nA path from the root to Peter is: "); 
		
		ArrayList<BST.TreeNode<String>> path = tree.path("Peter");
		
		// Vain yksi rivikoodia, niin sulut voidaan jättää pois
		for (int i = 0; path != null && i < path.size(); i++)
		System.out.print(path.get(i).element + " ");
		
		Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7}; 
		// toisenlainen tietotyyppi puulle 
		// alustetaan puu taulukon alkioilla
		BST<Integer> intTree = new BST<>(numbers); 
		
		System.out.print("\nInorder (sorted): "); 
		intTree.inorder();
		
		System.out.print("\nPreorder: "); 
		intTree.preorder();
		
		System.out.print("\nPostorder: "); 
		intTree.postorder();
	} 
}
/*

1️⃣ main-metodi (TestBST)

Rooli: asiakas / testipenkki
Ei toteuta puita – vain käyttää niitä

🔹 Mitä tämä luokka EI tee

❌ Ei tiedä miten puu toimii sisäisesti

❌ Ei käsittele solmuja (left, right)

❌ Ei vertaile alkioita

👉 Tämä on tarkoituksella tyhmä käyttäjä

🔹 Mitä tämä luokka TEKEE

Luo BST-olion

Kutsuu sen metodeja

Tulostaa tulokset

Tämä vastaa tentissä kysymykseen:
“Miten BST:tä käytetään?”

🧱 Rivi riviltä – selkeät sävelet
🔸 public static void main(String[] args)
public static void main(String[] args)


Java-ohjelman käynnistyspiste

static → ei vaadi olion luontia

String[] args → komentoriviparametrit (ei käytössä tässä)

🔸 BST:n luonti
BST<String> tree = new BST<>();


Mitä tapahtuu:

Luodaan tyhjä binäärinen hakupuu
Geneerinen tyyppi: String

Vaatimus:
E extends Comparable<E>


✔ String toteuttaa Comparable<String>
👉 Tämä rivi sitoo koko muun koodin tyyppiin String

🔸 Alkioiden lisäys
tree.insert("George");
tree.insert("Michael");
...


Oleellinen idea:
Jokainen insert:
Vertailee compareTo
menee vasemmalle (<)
tai oikealle (>)

👉 Puu rakentaa itsensä järjestykseen automaattisesti

🔸 Inorder-traversointi
System.out.print("Inorder (sorted): ");
tree.inorder();


Inorder =
vasen → juuri → oikea


👉 BST:ssä tämä tarkoittaa:

Tulostus aina nousevassa järjestyksessä
Tämä on yksi tärkeimmistä tentti-ideoista.

🔸 Postorder
tree.postorder();

vasen → oikea → juuri


Käytetään usein:
puun poistamiseen
resurssien vapauttamiseen

🔸 Preorder
tree.preorder();

juuri → vasen → oikea

Käytetään:
puun kopiointiin
rakenteen tallennukseen

🔸 Koko
tree.getSize()


palauttaa solmujen määrän
ei traversal
vain laskuri (size)

🔸 Haku
tree.search("Peter")


käyttää samaa logiikkaa kuin insert
ei käy koko puuta läpi
tehokas: O(h)

👉 BST:n ydinidea

🔸 Polku juuresta alkioon
ArrayList<BST.TreeNode<String>> path = tree.path("Peter");


TÄRKEÄ:

Tämä paljastaa puun rakenteen
Palauttaa solmut:
root → ... → Peter


Tämä on diagnostiikkaa, ei normaalia käyttöä
Siksi:
BST.TreeNode<String>


→ TreeNode on public static nested class

🔸 Polun tulostus
path.get(i).element


Huomaa:
TestBST pääsee elementtiin suoraan

Ei gettereitä
pedagoginen ratkaisu

🔸 Integer-puu
Integer[] numbers = {2,4,3,1,8,5,6,7};
BST<Integer> intTree = new BST<>(numbers);


Tämä todistaa:

BST on geneerinen
toimii mille tahansa Comparable-tyypille
🎯 Miksi tämä luokka on tärkeä kurssilla
TestBST näyttää:
miten geneeristä luokkaa käytetään
miten rajapinta ohjaa käyttöä
ettei käyttäjän tarvitse tietää toteutuksesta mitään

Täydellinen esimerkki OOP:n ideasta

🧠 Yhteenveto (tenttiin)

TestBST on asiakasluokka, joka käyttää BST-luokkaa sen julkisen rajapinnan kautta. Se ei käsittele puun sisäistä rakennetta, vaan testaa ja demonstroi puuoperaatioita kuten lisäys, haku ja eri traversoinnit.

🟢 Key takeaway

Insert-järjestys vaikuttaa puun muotoon.
Sama joukko lukuja → eri järjestys → eri BST-muoto → eri traversointi "polut"
Inorder traversal tuottaa aina nousevan järjestyksen BST:ssä
Preorder ja Postorder näyttävät puun rakenteen eri tavoin
Traversoinnit eivät muuta puun rakennetta, ne vain lukevat solmut eri järjestyksessä
Insert-järjestys vaikuttaa puun muotoon. Sama joukkue lukuja → eri puu → eri polut.

Inorder traversal → aina nouseva järjestys BST:ssä
Preorder → näyttää “miten puu rakentuu” alkaen juuresta
Postorder → käy kaikki lapset ennen juurta, hyvä esim. poistoon tai laskentaan bottom-up
*/

interface Tree<E> extends Iterable<E> {
	
	/** Return true if the element is in the tree */ 
	public boolean search(E e);
	
	/** Insert element e into the binary search tree.
	* Return true if the element is inserted successfully. */
	public boolean insert(E e);
	
	/** Delete the specified element from the tree.
	
	* Return true if the element is deleted successfully. */
	
	public boolean delete(E e);
	
	/** Inorder traversal from the root*/
	
	public void inorder();
	
	/** Postorder traversal from the root */
	
	public void postorder();
	
	/** Preorder traversal from the root */
	
	public void preorder();
	
	/** Get the number of nodes in the tree */
	
	public int getSize();
	
	/** Return true if the tree is empty */
	
	public boolean isEmpty();
	
}
/*
Ylätason suunnitelma

Rajapinta määrittelee, mitä toimintaa kaikki puutyyppiset luokat (esim. BST) joutuvat tarjoamaan, ilman että se kertoo miten ne toteutetaan.

Rajapinta on kuin sopimus: mikä tahansa luokka, joka toteuttaa Tree<E>-rajapinnan, täytyy tarjota nämä metodit.
Iterable<E>-perintä tarkoittaa, että puuta voidaan käydä läpi for-each -silmukalla.

Metodit ja niiden tarkoitus luonnollisella kielellä

search(E e)
Tarkistaa, löytyykö elementti e puusta.
Palauttaa true jos löytyy, muuten false.

insert(E e)
Lisää elementin e puuhun.
Palauttaa true, jos lisäys onnistui, false jos elementti on jo puussa.

delete(E e)
Poistaa elementin e puusta.
Palauttaa true, jos poisto onnistui, false jos elementtiä ei ollut.

inorder()
Käy puun läpi vasen → juuri → oikea (inorder).
Tuottaa elementit järjestyksessä pienimmästä suurimpaan (BST:ssä).

postorder()
Käy puun läpi vasen → oikea → juuri (postorder).
Käytetään usein, kun halutaan käsitellä alipuuta ennen juuri-solmua.

preorder()

Käy puun läpi juuri → vasen → oikea (preorder).
Käytetään usein puun kopioimiseen tai rakennepolkujen rakentamiseen.

getSize()
Palauttaa puussa olevien solmujen lukumäärän.

isEmpty()
Palauttaa true, jos puu on tyhjä (getSize() == 0), muuten false.

Pseudokoodi (ytimekkäästi)
interface Tree<E> {
	// Onko elementti puussa?
	function search(element) -> boolean
	
	// Lisää elementti puuhun
	function insert(element) -> boolean
	
	// Poista elementti puusta
	function delete(element) -> boolean
	
	// Traversointi: vasen → juuri → oikea
	function inorder()
	
	// Traversointi: vasen → oikea → juuri
	function postorder()
	
	// Traversointi: juuri → vasen → oikea
	function preorder()
	
	// Montako solmua puussa?
	function getSize() -> int
	
	// Onko puu tyhjä?
	function isEmpty() -> boolean
}

Aikavaativuus (keskiarvo, BST-esimerkissä)
Metodi	Keskiarvoaikavaativuus	Huomio
search	O(log n)	BST:ssä puun korkeus vaikuttaa
insert	O(log n)	Sama kuin search
delete	O(log n)	Etsintä + mahdollinen uudelleenliittäminen
Traversoinnit (inorder, preorder, postorder)	O(n)	Käy kaikki solmut läpi
getSize	O(1)	Tallennetaan size-muuttujassa
isEmpty	O(1)	Riippumaton puun koosta
Lyhyt selitys, miksi tämä on tärkeä

Tree<E> rajapinta abstrahoi puun logiikan: käyttäjän ei tarvitse tietää solmujen linkityksistä.

Kaikki puut, jotka toteuttavat tämän rajapinnan (esim. BST, AVLTree) voidaan käsitellä saman rajapinnan kautta, esim. for (E e : puu) { ... }.
Huomaa: getSize(), search(), insert(), delete() puuttuvat konkreettisesta toteutuksesta.

Tämä on sallittua, koska luokka on abstract → sitä ei voi luoda suoraan.

Konkreettinen aliluokka, kuten BST<E>, täytyy toteuttaa puuttuvat metodit.

2️⃣ Miksi abstrakti luokka voi olla "puutteellinen"

AbstractTree<E> toteuttaa rajapinnan, mutta ei anna konkreettista toteutusta kaikille metodeille
3️⃣ Miksi tämä on hyödyllistä

Voi antaa yhteisiä toteutuksia, joita kaikki puut käyttävät (esim. isEmpty()).
Voi jättää metodit, joiden toteutus riippuu tietorakenteesta aliluokan vastuulle (esim. BST).
Tällä tavoin vältetään koodin toistoa eri puutyypeissä.

🔹 Yhteenveto luonnollisella kielellä

Rajapinta kertoo mitä metodeja puulla täytyy olla.
Abstrakti luokka voi tarjota osan toteutuksista.
Puuttuvat metodit jäävät abstrakteiksi, ja ne toteutetaan vasta konkreettisessa luokassa.
Tämä tekee koodista modulaarista ja uudelleenkäytettävää.
*/



abstract class AbstractTree<E>

implements Tree<E> {
	
	@Override /** Inorder traversal from the root*/
	
	public void inorder() {
		
	}
	
	@Override /** Postorder traversal from the root */ 
	public void postorder() {
		
	}
	
	@Override /** Preorder traversal from the root */ 
	public void preorder() {
		
	}
	
	@Override /** Return true if the tree is empty */
	

	public boolean isEmpty() {
		
		return getSize() == 0;
	} 
}

/*
Ylätason suunnitelma

AbstractTree<E> on abstrakti luokka, joka toteuttaa osan Tree<E>-rajapinnasta.

Se tarjoaa valmiin isEmpty()-metodin ja tyhjät alustukset traversointimetodeille.
Käytännössä tämä tarkoittaa, että luokat kuten BST<E> voivat periytyä tästä ja toteuttaa vain puun varsinaisen logiikan.

Mitä kukin osa tekee luonnollisella kielellä

abstract class AbstractTree<E> implements Tree<E>
Luokka on abstrakti → ei voida luoda suoraan, mutta voidaan periyttää.
Toteuttaa Tree<E>-rajapinnan → kaikki rajapinnan metodit löytyvät, mutta osa voi olla tyhjiä / abstrakteja.
public void inorder() { }
Tyhjä toteutus inorder-traversoinnille.

Aliluokat voivat ylikirjoittaa tämän ja toteuttaa todellisen käynnin puussa.
public void postorder() { }

Sama idea kuin inorder(), mutta postorder-traversointi.

Tyhjä runko, tarkoitus antaa perityille luokille mahdollisuus toteuttaa.
public void preorder() { }

Sama idea kuin muut traversoinnit: vain runko, toteutus aliluokassa.
public boolean isEmpty() { return getSize() == 0; }

Valmiiksi toteutettu apumetodi.
Palauttaa true, jos puu on tyhjä (size = 0).

Ei tarvitse ylikirjoittaa aliluokassa, koska kaikki puut voivat käyttää tätä.

Pseudokoodi
abstract class AbstractTree<E> implements Tree<E>:

	method inorder():
		// Tyhjä runko, toteutetaan aliluokassa
		
	method postorder():
		// Tyhjä runko, toteutetaan aliluokassa
		
	method preorder():
		// Tyhjä runko, toteutetaan aliluokassa
		
	method isEmpty() -> boolean:
		return getSize() == 0
		
Tärkeää huomioida

AbstractTree ei tietää puun solmuista, siksi traversoinnit ovat tyhjiä.

isEmpty() toimii koska se luottaa getSize()-metodiin, joka on pakollinen rajapinnassa ja pitää toteuttaa aliluokassa.

Tämä tekee koodista modulaarisen: yhteiset metodit voidaan kirjoittaa kerran abstraktiin luokkaan, ja spesifinen logiikka BST:lle tai AVL:lle menee aliluokkaan.
*/

class BST<E extends Comparable<E>> extends AbstractTree<E> {
	
	protected TreeNode<E> root;
	protected int size = 0;
	// tyhjä konstruktori
	public BST() { }
	// konstruktori parametrilla
	public BST(E[] objects) {
		for (E e : objects) {
			insert(e);
		}
	}
	
	@Override
	public boolean search(E e) {
		
		TreeNode<E> current = root;
		
		while (current != null) {
			// liikutaan vasemmalle tai oikealle 
			if (e.compareTo(current.element) < 0)
				current = current.left;
			else if (e.compareTo(current.element) > 0)
				current = current.right;
				// tässä on nykyinen solmuja verrattava solmu yhtä suuret, eli löydettiin alkio 
			else
				return true;
		}
		return false;
	}
	
	@Override
	public boolean insert(E e) {
		
		if (root == null) {
			root = new TreeNode<>(e);
			
		} else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false; // duplicate
				}
			}
			// tässä tapahtuu solmun lisäys
			if (e.compareTo(parent.element) < 0)
				parent.left = new TreeNode<>(e);
			else
				parent.right = new TreeNode<>(e);
		}
		size++;
		return true;
	}
	// Traversaalit ovat rekursiivisia
	// käynnistetään Rekursio puun juuresta 
	@Override
	public void inorder() {
		inorder(root);
	}
	// rekursiivinen apumetodi
	protected void inorder(TreeNode<E> root) {
		if (root == null) return;
		inorder(root.left);
		// tämä rivi hoitaa solmukohtaisen tulostuksen
		System.out.print(root.element + " ");
		inorder(root.right);
	}
	
	@Override
	public void preorder() {
		preorder(root);
	}
	// käy solmussa heti, ennen kuin menet mihinkään 
	protected void preorder(TreeNode<E> root) {
		if (root == null) return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	@Override
	public void postorder() {
		postorder(root);
	}
	// Postorderissa solmu tulostetaan vasta kun siitä ei ole enää minnekään mennä
	protected void postorder(TreeNode<E> root) {
		if (root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	public ArrayList<TreeNode<E>> path(E e) {
		
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;
		
		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0)
				current = current.left;
			else if (e.compareTo(current.element) > 0)
				current = current.right;
			else
				break;
		}
		return list;
	}
	
//	@Override
//	public boolean delete(E e) {
//		// (delete ei ole pakollinen TestBST:lle, mutta rajapinta vaatii)
//		return false;
//	}
	
	// ❗ Java:ssa et “poista” solmua
	// 👉 katkaiset viitteen siihen
	
	/*
	Delete-metodi (BST)
	
	Etsi poistettava solmu (current) ja sen vanhempi (parent)
	
	Käy puu läpi vertaamalla arvoja.
	
	Jos solmua ei löydy → palauta false.
	
	Tilanne 1: Poistettavalla solmulla ei ole lapsia (lehtisolmu)
	
	Jos solmu on juuri → root = null.
	
	Jos solmu on vanhemman vasen lapsi → parent.left = null.
	
	Jos solmu on vanhemman oikea lapsi → parent.right = null.
	
	Tilanne 2: Poistettavalla solmulla on yksi lapsi
	
	Jos oikea lapsi puuttuu → nosta vasen lapsi solmun paikalle.
	
	Jos vasen lapsi puuttuu → nosta oikea lapsi solmun paikalle.
	
	Päivitä vanhemman viittaus lapsiin (parent.left/right = current.left/right).
	
	Jos poistettava on juuri → nosta lapsi juureksi.
	
	Tilanne 3: Poistettavalla solmulla on kaksi lasta
	
	Etsi oikean alipuun pienin solmu (smallest) ja sen vanhempi (smallestParent).
	
	Pienin solmu on aina oikean alipuun vasemman reunan "suurin" solmu.
	
	Kopioi smallest.element poistettavan solmun paikalle → poistettava arvo "korvataan".
	
	Poista pienin solmu alkuperäisestä paikastaan:
	
	Jos smallest on vanhempansa vasen lapsi → smallestParent.left = smallest.right.
	
	Jos smallest on vanhempansa oikea lapsi → smallestParent.right = smallest.right.
	
	Näin jäljelle jäävät lapset pysyvät järjestyksessä.
	
	Päivitä puun koko (size--)
	
	Palauta true, jos poistaminen onnistui.
	*/
	@Override
	public boolean delete(E e) {
		
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		
		// 1️⃣ Etsi poistettava solmu liikkumalla oikealle tai vasemmalle
		while (current != null && !current.element.equals(e)) {
			parent = current;
			if (e.compareTo(current.element) < 0)
				current = current.left;
			else
				current = current.right;
		}
		
		if (current == null) return false; // ei löytynyt
		
		// 2️⃣ Tilanne 1: ei lapsia
		if (current.left == null && current.right == null) {
			if (current == root) root = null; // juuren poistaminen, tämä pitää tarkistaa erikseen 
			// poistetaan vasemman ja oikean lapsen linkki, jolloin lehti häviää puusta
			else if (parent.left == current) parent.left = null;
			else parent.right = null;
		}
		/*
		Tällä koodilla siirretään solmun lapsi suoraan sen paikalle, kun poistettavalla solmulla on vain yksi lapsi.
		Näin puun rakenne pysyy oikeana ilman ylimääräisiä liikkeitä.
		Kaikki kolme rakoa (vain oikea, vain vasen, juuri) käsitellään erikseen.
		*/
		// 3️⃣ Tilanne 2: yksi lapsi
		else if (current.left == null) { // vain oikea lapsi
			if (current == root) root = current.right;
			else if (parent.left == current) parent.left = current.right;
			else parent.right = current.right;
		}
		else if (current.right == null) { // vain vasen lapsi
			if (current == root) root = current.left;
			else if (parent.left == current) parent.left = current.left;
			else parent.right = current.left;
		}
		// 4️⃣ Tilanne 3: kaksi lasta
		else {
			TreeNode<E> smallestParent = current;
			// Etsi oikean alipuun pienin solmu, jotta puun järjestys säilyy
			TreeNode<E> smallest = current.right;
			/*
			➡️ mennään vasemmalle kunnes ei enää voi
			➡️ löydetään pienin
			*/
			while (smallest.left != null) {
				smallestParent = smallest;
				smallest = smallest.left;
			}
			
			// Kopioidaan arvo poistettavaan solmuun
			current.element = smallest.element;
			
			// Poistetaan pienin solmu
			if (smallestParent.left == smallest)
				smallestParent.left = smallest.right;
			else
				smallestParent.right = smallest.right;
		}
		
		size--;
		return true;
	}
	
	/*
	🔹 Babystepseillä selitettynä
	
	Etsitään poistettava solmu puusta.
	
	Tarkistetaan lapsien määrä:
	
	0 lasta → poistetaan solmu.
	
	1 lapsi → liitetään lapsi suoraan vanhempaan.
	
	2 lasta → etsitään oikean alipuun pienin, kopioidaan sen arvo ja poistetaan se pienin-solmu.
	
	Päivitetään size.
	
	Palautetaan true, jos poisto onnistui, false jos ei löytynyt.
	
	1️⃣ iterator()-metodi
	
	Mahdollistaa for-each -loopin puun läpi kulkemiseen.
	
	Luo ensin ArrayListin, johon kerätään kaikki puun arvot in-order -järjestyksessä.
	
	Palauttaa ArrayListin Iterator-olion, jota for-each voi käyttää.
	
	Näin voit esim. kirjoittaa:
	
	for (Integer x : intTree) {
		System.out.print(x + " ");
	}
	
	
	ja saat puun luvut järjestyksessä.
	
	2️⃣ inorderCollect(TreeNode<E> root, ArrayList<E> list)-apumetodi
	
	Rekursiivinen metodi, joka käy puun vasen-solmu → juuri → oikea-solmu -järjestyksessä (in-order).
	
	Lisää jokaisen solmun arvon listaan.
	
	Käytetään juuri iterator()-metodissa keräämään kaikki solmut järjestettyyn listaan.
	
	3️⃣ Sisäluokka TreeNode<E>
	
	Jokainen puun solmu on TreeNode, jossa on:
	
	element → varsinainen arvo (data).
	
	left → viittaus vasempaan lapseen.
	
	right → viittaus oikeaan lapseen.
	
	public static → solmu-luokka on staattinen sisäluokka, koska se ei tarvitse viitata ulkopuolen BST-instanssiin.
	
	Tämä rakenne mahdollistaa puun rakentamisen ja linkittämisen solmujen kautta.
	*/
	
	@Override
	public Iterator<E> iterator() {
		ArrayList<E> list = new ArrayList<>();
		inorderCollect(root, list);
		return list.iterator();
	}
	
	private void inorderCollect(TreeNode<E> root, ArrayList<E> list) {
		
		if (root == null) return;
		inorderCollect(root.left, list);
		list.add(root.element);
		inorderCollect(root.right, list);
	}
	
	// tämä on sisäluokka, joten tätä ei koske rajoitus
	public static class TreeNode<E> {
		
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;
		
		public TreeNode(E e) {
			element = e;
		}
	}
}

/*
1️⃣ Luokan tarkoitus

BST<E> = Binary Search Tree, eli binäärinen hakupuu.

Säilyttää elementit järjestyksessä, jotta haku, lisäys ja polun löytäminen ovat tehokkaita.

Generics: <E extends Comparable<E>> → puu voi sisältää mitä tahansa elementtejä, joita voidaan verrata (compareTo).

2️⃣ Kentät
protected TreeNode<E> root;
protected int size = 0;


root → puun juuri.

size → solmujen määrä puussa.

3️⃣ Konstruktorit
public BST() { }


Luo tyhjän puun.

public BST(E[] objects) {
	for (E e : objects) {
		insert(e);
	}
}


Luo puun, johon lisätään aluksi kaikki taulukon elementit kutsumalla insert.

4️⃣ Haku (search)
@Override
public boolean search(E e) {
	TreeNode<E> current = root;
	while (current != null) {
		if (e.compareTo(current.element) < 0)
			current = current.left;
		else if (e.compareTo(current.element) > 0)
			current = current.right;
		else
			return true; // löytyi
	}
	return false; // ei löytynyt
}


Aloitetaan juuresta.

Liikutaan vasemmalle, jos etsittävä on pienempi, oikealle, jos suurempi.

Palauttaa true, jos elementti löytyy, muuten false.

5️⃣ Lisäys (insert)
@Override
public boolean insert(E e) {
	if (root == null) {
		root = new TreeNode<>(e); // uusi juuri
	} else {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else {
				return false; // duplicate
			}
		}
		
		if (e.compareTo(parent.element) < 0)
			parent.left = new TreeNode<>(e);
		else
			parent.right = new TreeNode<>(e);
	}
	size++;
	return true;
}


Tyhjä puu → uusi juuri.

Muuten liikutaan solmuja läpi, kunnes löytyy sopiva paikka.

Ei lisää duplikaatteja.

Päivittää size.

6️⃣ Traversoinnit
a) Inorder
@Override
public void inorder() { inorder(root); }

protected void inorder(TreeNode<E> root) {
	if (root == null) return;
	inorder(root.left);
	System.out.print(root.element + " ");
	inorder(root.right);
}


Vasemmalle alipuu → juuri → oikealle alipuu

Tuottaa lajitellun järjestyksen.

b) Preorder
@Override
public void preorder() { preorder(root); }

protected void preorder(TreeNode<E> root) {
	if (root == null) return;
	System.out.print(root.element + " ");
	preorder(root.left);
	preorder(root.right);
}


Juuri → vasen alipuu → oikea alipuu

Käytetään usein puun kopiointiin tai rakenteen tallennukseen.

c) Postorder
@Override
public void postorder() { postorder(root); }

protected void postorder(TreeNode<E> root) {
	if (root == null) return;
	postorder(root.left);
	postorder(root.right);
	System.out.print(root.element + " ");
}


Vasemmalle → oikealle → juuri

Käytetään usein solmujen poistamiseen.

7️⃣ Polku solmuun (path)
public ArrayList<TreeNode<E>> path(E e) {
	ArrayList<TreeNode<E>> list = new ArrayList<>();
	TreeNode<E> current = root;
	
	while (current != null) {
		list.add(current);
		if (e.compareTo(current.element) < 0)
			current = current.left;
		else if (e.compareTo(current.element) > 0)
			current = current.right;
		else
			break;
	}
	return list;
}


Palauttaa listan solmuista juuresta solmuun e.

Hyvä debuggaamiseen ja visualisointiin.

8️⃣ Delete (ei toteutettu tässä)
@Override
public boolean delete(E e) {
	return false; // rajapinta vaatii, mutta TestBST ei käytä
}

9️⃣ Iterator (inorder)
@Override
public Iterator<E> iterator() {
	ArrayList<E> list = new ArrayList<>();
	inorderCollect(root, list);
	return list.iterator();
}

private void inorderCollect(TreeNode<E> root, ArrayList<E> list) {
	if (root == null) return;
	inorderCollect(root.left, list);
	list.add(root.element);
	inorderCollect(root.right, list);
}


Tarjoaa Iterable-rajapinnan tuen.

Iteroi puun inorder-järjestyksessä.

🔟 Sisäluokka TreeNode
public static class TreeNode<E> {
	protected E element;
	protected TreeNode<E> left;
	protected TreeNode<E> right;
	
	public TreeNode(E e) { element = e; }
}


Solmu puussa.

Sisältää elementin ja osoittimet vasempaan/oikeaan lapseen.

static → ei tarvitse ulkopuolen olion instanssia.

1️⃣1️⃣ Yhteenveto luonnollisella kielellä

BST on konkreettinen binäärinen hakupuu, joka:

Toteuttaa rajapinnan Tree<E>.

Tallentaa juuren ja koon.

Mahdollistaa lisäyksen, haun, polun löytämisen ja traversoinnit.

Ei salli duplikaatteja.

Tarjoaa Iterable-tuen inorder-iterointiin.

Traversoinnit (inorder, preorder, postorder) antavat eri järjestykset, joilla puun solmuja voidaan käsitellä.


*/
//*****************************************************************************************************************
/*
1️⃣ YLÄTASON IDEA (mikä tämä kokonaisuus on?)

Tämä ohjelma toteuttaa ja käyttää binäärihakupuuta (Binary Search Tree).

BST on tietorakenne, jossa:

jokaisessa solmussa on yksi arvo

vasemmassa alipuussa on pienemmät

oikeassa alipuussa on suuremmat

puu on dynaaminen (ei kiinteän kokoinen kuten taulukko)

👉 Tämä mahdollistaa:

nopean haun

järjestetyn läpikäynnin

hierarkkisen rakenteen

2️⃣ ARKKITEHTUURI (TRA:n abstraktiomalli)
Tree (rajapinta, ADT)
	↑
AbstractTree (osittainen toteutus)
	↑
BST (konkreettinen tietorakenne)

Miksi näin?

Tree kertoo mitä puu osaa

AbstractTree antaa yhteistä perustoimintaa

BST kertoo miten binäärihakupuu toimii

➡️ Tämä on klassinen TRA-tenttirakenne

3️⃣ Tree-rajapinta = abstrakti tietotyyppi
interface Tree<E> extends Iterable<E>


Luonnollisesti:

“Puu on kokoelma alkioita, joille on määritelty tietyt operaatiot.”

Rajapinta määrittelee:

insert, search, delete

traversal-metodit

koon ja tyhjyyden tarkistuksen

❗ Ei sisällä yhtään toteutusta

4️⃣ AbstractTree = osittainen toteutus
abstract class AbstractTree<E> implements Tree<E>


Tässä:

annetaan oletustoteutus isEmpty()

traversal-metodit jätetään tyhjiksi

pakottaa BST:n toteuttamaan ytimen

👉 malli: “älä toista samaa koodia”

5️⃣ BST-luokka = varsinainen tietorakenne
class BST<E extends Comparable<E>>

Miksi extends Comparable<E>?

BST tarvitsee vertailua

ilman tätä ei tiedetä, mennäänkö vasemmalle vai oikealle

6️⃣ Solmun rakenne (TreeNode)
public static class TreeNode<E> {
	E element;
	TreeNode<E> left;
	TreeNode<E> right;
}


Luonnollisesti:

“Solmu sisältää arvon ja viitteet vasempaan ja oikeaan lapseen.”

7️⃣ Insert-operaatio (puun rakentaminen)
Mitä tapahtuu?

aloitetaan juuresta

verrataan uutta arvoa nykyiseen

mennään vasemmalle tai oikealle

lisätään lehdeksi

Ydinkoodi
if (e.compareTo(current.element) < 0)
	current = current.left;
else if (e.compareTo(current.element) > 0)
	current = current.right;
	
Pseudokoodi
jos puu tyhjä:
	uusi alkio juureksi
muuten:
	aloita juuresta
	toista:
		jos pienempi → vasemmalle
		jos suurempi → oikealle
		jos sama → keskeytä
		
8️⃣ Search-operaatio
Idea:

sama reitti kuin insertissä

mutta ei lisätä mitään

Pseudokoodi
aloita juuresta
kunnes solmu on null:
	jos haettava < solmu → vasemmalle
	jos > → oikealle
	jos = → löytyi
ei löytynyt

9️⃣ Traversoinnit (ERITTÄIN TÄRKEÄ TENTISSÄ)
Inorder (VASEN – JUURI – OIKEA)
inorder(root.left);
print(root.element);
inorder(root.right);


➡️ Tulostaa arvot järjestyksessä

Preorder (JUURI – VASEN – OIKEA)
print(root.element);
preorder(root.left);
preorder(root.right);


➡️ Käytetään esim. puun kopiointiin

Postorder (VASEN – OIKEA – JUURI)
postorder(root.left);
postorder(root.right);
print(root.element);


➡️ Käytetään esim. puun vapautukseen

🔟 Path-metodi
ArrayList<TreeNode<E>> path(E e)


Luonnollisesti:

“Palauttaa polun juuresta haettavaan solmuun.”

Esim:

George → Michael → Peter

1️⃣1️⃣ Iterator (Iterable)
public Iterator<E> iterator()


palauttaa inorder-järjestyksessä

mahdollistaa:

for (E e : tree) { ... }

1️⃣2️⃣ Aikavaativuudet (TRA-kultaa)
Operaatio	Keskimäärin	Pahin tapaus
insert	O(log n)	O(n)
search	O(log n)	O(n)
delete	O(log n)	O(n)
inorder	O(n)	O(n)

🔴 Pahin tapaus = puu degeneroituu listaksi
🟢 Tasapainoinen puu = logaritminen

1️⃣3️⃣ TestBST – mitä se todistaa?

että insert toimii

että traversal-järjestykset ovat oikein

että search toimii

että path toimii

että BST toimii myös Integer-tyypillä

➡️ Geneerisyys + polymorfia käytännössä

1️⃣4️⃣ Tenttivastaus 1 lauseella

Binäärihakupuu on hierarkkinen tietorakenne, jossa vasemmassa alipuussa on pienemmät ja oikeassa alipuussa suuremmat alkiot, 
mikä mahdollistaa tehokkaan haun ja järjestetyn traversoinnin.

🎯 Miksi tämä on yksi kurssin tärkeimmistä asioista?

Koska tässä yhdistyvät:

abstrakti tietotyyppi

rekursio

aikavaativuus

geneerisyys

perintä ja rajapinnat

tietorakenteiden ajattelu

👉 Tämä on TRA tiivistettynä yhteen esimerkkiin.

*/
					
					
					
					
					
