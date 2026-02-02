import java.util.*;

// Pääluokka, joka ajaa koodin
public class TestGraph {
    
    public static void main(String[] args) {
        
        // solmut ovat taulukko kaupungeista 
        // tässä tehdään kolme asiaa kerralla: väritetään taulukon tyyppi, alustetaan oikeaan kokoon ja täytetään alkioilla
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
            "Denver", "Kansas City", "Chicago", "Boston", "New York",
            "Atlanta", "Miami", "Dallas", "Houston"};

        // kaupunkien välisiä kaaria mallinnetaan 2D-taulukolla
        int[][] edges = {
            {0, 1}, {0, 3}, {0, 5}, {1, 0}, {1, 2}, {1, 3},
            {2, 1}, {2, 3}, {2, 4}, {2, 10}, {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
            {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10}, {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
            {6, 5}, {6, 7}, {7, 4}, {7, 5}, {7, 6}, {7, 8}, {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
            {9, 8}, {9, 11}, {10, 2}, {10, 4}, {10, 8}, {10, 11}, {11, 8}, {11, 9}, {11, 10}
        };
        // annetaan solut ja kaaret parametrina. Varsinaiset oliot luodaan konkreettisen luokan perusteella 
        Graph<String> graph1 = new UnweightedGraph<>(vertices, edges);
        // Verkon koko
        System.out.println("The number of vertices in graph1: " + graph1.getSize());
        // solmun indeksi
        System.out.println("The index for Miami is " + graph1.getIndex("Miami"));
        // tämä tulostaa ensin kaupungin ja siihen liittyvät toiset kaupungit
        System.out.println("\nThe edges for graph1:");
        graph1.printEdges(); 
        
    } // main
} // class

/*

1. Rajapinta (Interface): Sopimus
Tämä on hierarkian huippu ja vastaa kysymykseen: "Mitä ominaisuuksia verkolla pitää olla?"
Se ei tiedä mitään toteutustavasta.
Se vain listaa metodien nimet ja parametrit.
Se on puhdas sopimus: "Jos luokka väittää olevansa verkko, sen on tarjottava nämä työkalut."

2. Abstrakti luokka: Yhteinen perusta

Tämä on keskivaihe. Se vastaa kysymykseen: "Mitä yhteistä kaikilla verkoilla on?"
Se toteuttaa rajapinnan metodit, jotka toimivat samalla tavalla riippumatta verkon tyypistä (esim. solmujen määrän laskeminen tai DFS-haku).
Se jättää "aukoiksi" ne osat, jotka riippuvat verkon tyypistä (esim. kaarten painotus).
Se säästää koodia: et halua kirjoittaa hakualgoritmeja uudestaan jokaista uutta verkkotyyppiä varten.

3. Konkreettinen luokka: Käytännön toteutus

Tämä on pohjimmainen taso. Se vastaa kysymykseen: "Miten tämä nimenomainen verkko rakennetaan?"
Se täyttää abstraktin luokan jättämät "aukot".
Tästä luokasta luodaan varsinaiset oliot (new UnweightedGraph(...)).
Se on se työkalu, jota käytät sovelluksessasi (kuten siinä kaupunkiesimerkissäsi).
Järjestys tiedostossa vs. muistissa

Vaikka Java-kääntäjä ei yleensä välitä siitä, missä järjestyksessä kirjoitat nämä yhteen tiedostoon (kunhan public-luokka on oikein nimetty), ihmiselle loogisin lukujärjestys on juuri tuo:

Määrittely (Interface)
Yleinen logiikka (Abstract Class)
Erityistapaus (Concrete Class)
Tämä noudattaa "Top-Down" -ajattelua, jossa kokonaiskuva hahmotetaan ennen yksityiskohtia.

*/
interface Graph<V> {
    
    int getSize();
    List<V> getVertices();
    V getVertex(int index);
    int getIndex(V v);
    int getDegree(int v);
    void printEdges();
    void clear();
    boolean addVertex(V vertex); // Korjattu void -> boolean
    boolean addEdge(int u, int v);
}


// --- ABSTRAKTI LUOKKA, joka toteuttaa rajapinnan
abstract class AbstractGraph<V> implements Graph<V> {
    
    // luokan ominaisuudet
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();
    
    // taulukkototeutus
    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++) 
        addVertex(vertices[i]);
        createAdjacencyLists(edges, vertices.length);
    }
    // listatoteutus
    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) 
        addVertex(vertices.get(i));
        createAdjacencyLists(edges, vertices.size());
    }
    // vieruslistan taulukkototeutus
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) 
        addEdge(edges[i][0], edges[i][1]);
    }
    
    // vieruslistan listatoteutus
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges) 
        addEdge(edge.u, edge.v);
    }
    // tässä ylikirjoitetaan rajapinnan metodeita, tässä on muutakin kuin metodin runko 
    @Override 
    public int getSize() { 
        return vertices.size(); 
    }
    
    @Override 
    public V getVertex(int index) { 
        return vertices.get(index); 
    }
    
    @Override 
    public int getIndex(V v) { 
        return vertices.indexOf(v); 
    }
    
    @Override 
    public List<V> getVertices() { 
        return vertices; 
    }
    
    @Override 
    public void printEdges() {
        
        for (int u = 0; u < neighbors.size(); u++) {
            
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
            System.out.println();
        }
    }
    
    @Override 
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        return false;
    }
    
    @Override 
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }
    
    protected boolean addEdge(Edge e) {
        
        if (!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        }
        return false;
    }
    
    @Override
    public int getDegree(int v) { 
        return neighbors.get(v).size(); 
    }
    
    @Override 
    public void clear() { 
        vertices.clear(); 
        neighbors.clear(); 
    }
    // apuluokka
    public static class Edge {
        
        public int u, v;
        
        public Edge(int u, int v) { 
            
            this.u = u; this.v = v; 
        }
        
        public boolean equals(Object o) {
            return u == ((Edge) o).u && v == ((Edge) o).v;
        }
    }
}

/*
1. Ylätason kuvaus: Rakenteellinen selkäranka
AbstractGraph määrittelee, miten verkko tallennetaan muistiin ja miten yleiset haut (DFS/BFS) suoritetaan. 
Se ei ole täydellinen verkko itsessään, vaan se tarjoaa valmiit työkalut aliluokille, jotka määrittelevät verkon spesifit ominaisuudet 
(kutenkaarten painot).
Datan tallennus: Käyttää naapuruuslista-mallia (Adjacency List), joka on muistitehokas harvoille verkoille.
Algoritmien isännöinti: Sisältää monimutkaiset hakualgoritmit (DFS ja BFS), jotka toimivat kaikilla verkoilla, 
jotka noudattavat tämän luokan tallennusmuotoa.

2. Tekninen erittely

A. Tietorakenteet

Luokka käyttää kahta keskeistä dynaamista listaa:
List<V> vertices: Tallentaa solmujen arvot (esim. String-nimet). Indeksi tässä listassa määrittää solmun tunnisteen.
List<List<Edge>> neighbors: Naapuruuslista. Jokainen indeksi i vastaa solmua i, ja se sisältää listan Edge-olioita, jotka kuvaavat solmusta lähtevät kaaret.

B. Konstruktorit ja dynaaminen sidonta

Luokka tarjoaa useita suojattuja (protected) konstruktoreita, jotka mahdollistavat verkon rakentamisen:
Kokonaislukupohjaisista taulukoista (indeksit).
Geneerisistä olio-listoista. Tämä joustavuus mahdollistaa datan tuomisen eri lähteistä ilman esikäsittelyä.

C. Hakualgoritmien toteutus

AbstractGraph toteuttaa kaksi keskeistä verkon läpikäyntialgoritmiä:
DFS (Depth-First Search): Käyttää rekursiota ja isVisited-taulukkoa syvyyssuuntaiseen hakuun. Tuloksena saadaan Tree-olio, joka kuvaa hakupolun.
BFS (Breadth-First Search): Käyttää jonoa (Queue) tasosuuntaiseen hakuun. Hyödyllinen lyhimmän reitin etsimiseen painottamattomassa verkossa.

D. Sisäluokat: Edge ja Tree

Luokka kapseloi kaksi apuluokkaa:
Edge (static): Yksinkertainen tietue, joka sisältää lähtösolmun u ja kohdesolmun v. Se on staattinen, koska se ei tarvitse pääsyä AbstractGraph-olion muuttujiin.
Tree: Sisältää haun tulokset: juuren, vanhemmat (parent[]) ja hakujärjestyksen. Se tarjoaa metodit kuten getPath(index), jolla voidaan rekonstruoida reitti juuresta tiettyyn solmuun.

3. Keskeiset metodit ja niiden rooli
Metodi	Kuvaus
createAdjacencyLists	Alustaa neighbors-listat ja täyttää ne annetuilla kaarilla.
addEdge(Edge e)	Tarkistaa indeksien validiteetin ja lisää kaaren oikeaan naapuruuslistaan.
printEdges()	Iteroi läpi neighbors-listan ja tulostaa verkon rakenteen ihmisluettavassa muodossa.
Seuraavaksi voimme tarkastella AbstractGraph.Tree -luokkaa ja sitä, miten getPath-metodi hyödyntää parent-taulukkoa reitin muodostamiseen. Haluatko teknisen analyysin tästä takaisinpäin suuntautuvasta algoritmista?

Tässä on tekninen erittely AbstractGraph<V>-luokan ja sen sisäluokan Tree kaikista metodeista. Luokan metodit on jaoteltu niiden funktionaalisen roolin mukaan.

1. Konstruktorit ja alustus

Nämä metodit vastaavat olion tilan alustamisesta ja verkon rakentamisesta eri syötemuodoista.
AbstractGraph(): Oletusarvoinen suojattu konstruktori tyhjän verkon luomiseen.
AbstractGraph(V[] vertices, int[][] edges): Rakentaa verkon solmujen taulukosta ja kaksiulotteisesta kaaritaulukosta.
AbstractGraph(List<V> vertices, List<Edge> edges): Rakentaa verkon listoista.
AbstractGraph(List<Edge> edges, int numberOfVertices): Luo verkon, jossa solmut ovat kokonaislukuja 0…n−1 ja kaaret toimitetaan listana.
AbstractGraph(int[][] edges, int numberOfVertices): Kuten edellinen, mutta kaaret toimitetaan taulukkona.
createAdjacencyLists(int[][] edges, int n): Sisäinen apumetodi, joka alustaa naapuruuslistat (neighbors) ja täyttää ne taulukkomuotoisesta datasta.
createAdjacencyLists(List<Edge> edges, int n): Sama kuin edellä, mutta käsittelee Edge-olioiden listaa.

2. Tiedonhaku ja tilakyselyt

Nämä metodit toteuttavat Graph-rajapinnan määrittelemät lukuoperaatiot.

getSize(): Palauttaa solmujen lukumäärän.
getVertices(): Palauttaa solmu-oliot sisältävän listan.
getVertex(int index): Palauttaa solmun arvon (tyyppi V) annetulla indeksillä.
getIndex(V v): Palauttaa solmu-olion indeksin (käyttää List.indexOf-metodia).
getNeighbors(int index): Palauttaa listan kokonaislukuja, jotka ovat annetun solmun naapureiden indeksejä.
getDegree(int v): Palauttaa solmun asteen (lähtevien kaarten määrä).
printEdges(): Tulostaa verkon rakenteen konsoliin: solmu, sen indeksi ja kaikki siitä lähtevät kaaret.

3. Verkon muokkaaminen

Metodit, jotka sallivat dynaamiset muutokset verkon rakenteeseen.

addVertex(V vertex): Lisää uuden solmun listaan ja alustaa sille tyhjän naapuruuslistan. Palauttaa false, jos solmu on jo olemassa.
addEdge(int u, int v): Käyttöliittymämetodi kaaren lisäämiseksi kahden indeksin välille. Luo uuden Edge-olion.
addEdge(Edge e): Suojattu metodi, joka suorittaa varsinaisen lisäyksen. Tarkistaa ensin indeksien rajat ja ettei kaari ole jo olemassa.
clear(): Tyhjentää sekä solmulistan että naapuruuslistat.

4. Haku- ja algoritmi-metodit

Verkon läpikäyntiin tarkoitetut metodit, jotka palauttavat Tree-olion.
dfs(int v): Suorittaa syvyyssuuntaisen haun (DFS) lähtöpisteestä v. Alustaa tilat (visited-taulukko, parent-taulukko) ja kutsuu rekursiivista apumetodia.
dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited): Rekursiivinen DFS-toteutus. Merkitsee solmun vierailluksi, lisää sen hakujärjestykseen ja etenee syvemmälle naapureihin.
bfs(int v): Suorittaa leveyssuuntaisen haun (BFS) lähtöpisteestä v. Käyttää jonoa (LinkedList) vieraillakseen solmuissa taso kerrallaan.

5. Sisäluokka: AbstractGraph.Tree

Tämä luokka säilöö haku-algoritmien tulokset ja tarjoaa metodit niiden analysointiin.
getRoot(): Palauttaa hakupuun juurisolmun indeksin.
getParent(int v): Palauttaa solmun v vanhemman indeksin hakupuussa.
getSearchOrder(): Palauttaa listan solmuista siinä järjestyksessä, jossa ne löydettiin.
getNumberOfVerticesFound(): Palauttaa haussa löydettyjen solmujen kokonaismäärän.
getPath(int index): Muodostaa listan (polun) solmusta index takaisin juureen seuraamalla parent-viittauksia.
printPath(int index): Tulostaa polun juuresta kohdesolmuun luettavassa muodossa.
printTree(): Tulostaa koko hakupuun rakenteen (juuri ja kaikki muodostuneet kaaret).
*/


// Konkreettinen toteutus kahdella erilaisella konstruktorilla. Tämä puolestaan laajentaa abstraktia luokkaa 
class UnweightedGraph<V> extends AbstractGraph<V> {
    
    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
    
    public UnweightedGraph(List<V> vertices, List<AbstractGraph.Edge> edges) {
        super(vertices, edges);
    }
} // UnweightedGraph

/*
1. Ylätason kuvaus: "Erikoistuminen ja toteutus"
Jos AbstractGraph on yleinen suunnitelma siitä, miten mikä tahansa verkko toimii, UnweightedGraph on sen yksinkertaisin mahdollinen toteutus.

Painottamaton (Unweighted): Se tarkoittaa, että verkon kaarilla ei ole painoa. Jos kaupunkien välillä on yhteys, se on olemassa (1) tai ei (0). Emme välitä tässä vaiheessa kilometrimääristä tai lipun hinnoista.

Välittäjä (Delegator): tämä pääasiallinen tehtävä on vain ottaa tiedot vastaan ja huutaa yläkerran AbstractGraphille: "Hei, tässä on dataa, hoida sinä loput!"

2. Tarkempi tekninen selitys
Katsotaanpa koodia rivi riviltä:

Generics: <V>
UnweightedGraph<V> käyttää geneeristä tyyppiä. Tämä tarkoittaa, että voit luoda verkon, jonka solmut ovat mitä tahansa:
UnweightedGraph<String> (Kaupungit)
UnweightedGraph<Integer> (Tunnusnumerot)
UnweightedGraph<User> (Sosiaalisen median profiilit)

Perintä: extends AbstractGraph<V>
Tämä rivi tekee UnweightedGraphista AbstractGraphin lapsen. Se tarkoittaa, että se saa automaattisesti käyttöönsä kaikki ne metodit, joita tutkimme aiemmin, kuten getIndex(), getSize() ja printEdges().

Konstruktorit ja super
Luokassa on kaksi konstruktoria, jotka mahdollistavat verkon luomisen eri tavoin:
Taulukko-versio: public UnweightedGraph(V[] vertices, int[][] edges)
Käytetään, kun tiedät datan määrän etukäteen (kuten antamassasi esimerkissä kaupunkilistan kanssa).
super(vertices, edges); kutsuu suoraan AbstractGraphin vastaavaa rakentajaa.
Lista-versio: public UnweightedGraph(List<V> vertices, List<AbstractGraph.Edge> edges)
Käytetään, kun data on dynaamisempaa ja tallennettu ArrayList-muotoon.
Huomaa viittaus AbstractGraph.Edge – se kertoo, että käytämme yläluokan sisälle määriteltyä Edge-luokkaa.
Miksi luokka on niin tyhjä?

Saatat miettiä: Miksi tämä on edes olemassa, jos se vain kutsuu superia? Syynä on arkkitehtuuri. Liang haluaa erottaa painottamattomat verkot painotetuista verkoista (WeightedGraph). Jos haluaisit myöhemmin lisätä painotettuun verkkoon vaikkapa etäisyydet, tekisit uuden luokan, joka perii saman AbstractGraphin, mutta toteuttaa asiat eri tavalla.

*/

/*

1. Ylätason kuvaus: Abstraktiokuvio
Olio-ohjelmoinnin arkkitehtuurissa rajapinta toimii rajapintakerroksena algoritmien ja datarakenteen välillä.
Geneerisyys (<V>): Mahdollistaa verkon solmujen tyyppiturvallisuuden. Solmut voivat olla merkkijonoja, kokonaislukuja tai monimutkaisia olioita ilman tyypinmuunnosvirheitä.
Irrotettavuus: Algoritmi (esim. lyhimmän reitin haku) voidaan kirjoittaa käyttämään Graph-tyyppiä. Tällöin algoritmi toimii sellaisenaan sekä painottamattomien (UnweightedGraph) että painotettujen (WeightedGraph) toteutusten kanssa.

2. Tekninen metodierittely
Rajapinnan metodit voidaan jakaa kolmeen kategorian niiden tarkoituksen mukaan:

A. Rakenteelliset kyselyt (Katselu)

getSize(): Palauttaa solmujen kokonaismäärän ∣V∣.
getVertices(): Palauttaa listan kaikista solmuista.
getVertex(int index): Palauttaa solmu-olion sen sisäisen indeksin perusteella.
getIndex(V v): Palauttaa annetun olion indeksin. Olennainen, koska useimmat graafialgoritmit (kuten DFS/BFS) operoivat tehokkuussyistä indekseillä, eivät olioilla.
getDegree(int v): Palauttaa solmun asteen, eli siitä lähtevien kaarten lukumäärän.

B. Modifiointi (Muokkaus)

addVertex(V vertex): Lisää uuden solmun. Palauttaa boolean-arvon merkiksi onnistumisesta (esim. false, jos solmu on jo olemassa).
addEdge(int u, int v): Luo kaaren kahden solmuindeksin välille. Painottamattomassa verkossa tämä on binäärinen yhteys.
clear(): Tuhoaa kaikki solmut ja kaaret, palauttaen verkon alkutilaan.

C. Tulostus ja Debuggaus

printEdges(): Tulostaa verkon naapuruuslistat standardiulostuloon. Tämä on tyypillinen apumetodi kehitysvaiheeseen.

3. Rajapinnan merkitys kääntäjälle
Kun luokka AbstractGraph ilmoittaa implements Graph<V>, kääntäjä pakottaa toteuttamaan jokaisen näistä metodeista. Jos yksikin puuttuu tai sen allekirjoitus (kuten palautustyyppi) poikkeaa rajapinnasta, koodi ei käänny.
Tämä takaa, että jos sinulla on muuttuja Graph<String> myGraph, voit kutsua sille metodia myGraph.getSize() murehtimatta siitä, mikä konkreettinen luokka muuttujan taustalla on.

*/

