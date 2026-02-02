import java.util.*;

public class TestDFS {
    
    public static void main(String[] args) {
        
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
            "Denver", "Kansas City", "Chicago", "Boston", "New York", 
            "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
            {0, 1}, {0, 3}, {0, 5}, {1, 0}, {1, 2}, {1, 3},
            {2, 1}, {2, 3}, {2, 4}, {2, 10}, {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
            {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10}, {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
            {6, 5}, {6, 7}, {7, 4}, {7, 5}, {7, 6}, {7, 8}, {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
            {9, 8}, {9, 11}, {10, 2}, {10, 4}, {10, 8}, {10, 11}, {11, 8}, {11, 9}, {11, 10}
        };

        Graph<String> graph = new UnweightedGraph<>(vertices, edges);

        // Suoritetaan haku Chicagosta
        AbstractGraph<String>.Tree dfs = graph.dfs(graph.getIndex("Chicago"));

        List<Integer> searchOrders = dfs.getSearchOrder();
        System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");

        for (int i = 0; i < searchOrders.size(); i++)
            System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
        System.out.println();

        for (int i = 0; i < searchOrders.size(); i++) {
            int v = searchOrders.get(i);
            if (dfs.getParent(v) != -1) {
                System.out.println("parent of " + graph.getVertex(v) + 
                    " is " + graph.getVertex(dfs.getParent(v)));
            }
        }
    }
}

interface Graph<V> {
    int getSize();
    List<V> getVertices();
    V getVertex(int index);
    int getIndex(V v);
    int getDegree(int v);
    void printEdges();
    void clear();
    boolean addVertex(V vertex);
    boolean addEdge(int u, int v);
    AbstractGraph<V>.Tree dfs(int v);
    AbstractGraph<V>.Tree bfs(int v);
}

abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph() { }

    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (V vertex : vertices) addVertex(vertex);
        createAdjacencyLists(edges, vertices.length);
    }

    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (V vertex : vertices) addVertex(vertex);
        for (Edge edge : edges) addEdge(edge.u, edge.v);
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) addVertex((V)new Integer(i));
        for (Edge edge : edges) addEdge(edge.u, edge.v);
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) addVertex((V)new Integer(i));
        createAdjacencyLists(edges, numberOfVertices);
    }

    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) addEdge(edges[i][0], edges[i][1]);
    }

    @Override public int getSize() { return vertices.size(); }
    @Override public V getVertex(int index) { return vertices.get(index); }
    @Override public int getIndex(V v) { return vertices.indexOf(v); }
    @Override public List<V> getVertices() { return vertices; }
    @Override public int getDegree(int v) { return neighbors.get(v).size(); }
    @Override public void clear() { vertices.clear(); neighbors.clear(); }

    @Override public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
            System.out.println();
        }
    }

    @Override public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        return false;
    }

    @Override public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    protected boolean addEdge(Edge e) {
        if (!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        }
        return false;
    }

    public static class Edge {
        public int u, v;
        public Edge(int u, int v) { this.u = u; this.v = v; }
        public boolean equals(Object o) {
            return u == ((Edge) o).u && v == ((Edge) o).v;
        }
    }

    // syvyyssuuntainen läpikäynti
    @Override
    public Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[vertices.size()];
        dfs(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(u);
        isVisited[u] = true;
        for (Edge e : neighbors.get(u)) {
            if (!isVisited[e.v]) {
                parent[e.v] = u;
                dfs(e.v, parent, searchOrder, isVisited);
            }
        }
    }
    // leveyssuuntainen läpikäynti
    @Override
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        Arrays.fill(parent, -1);
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];

        queue.add(v);
        isVisited[v] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for (Edge e : neighbors.get(u)) {
                if (!isVisited[e.v]) {
                    isVisited[e.v] = true;
                    parent[e.v] = u;
                    queue.add(e.v);
                }
            }
        }
        return new Tree(v, parent, searchOrder);
    }

    public class Tree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }
        public int getParent(int v) { return parent[v]; }
        public List<Integer> getSearchOrder() { return searchOrder; }
        public int getNumberOfVerticesFound() { return searchOrder.size(); }
    }
}

class UnweightedGraph<V> extends AbstractGraph<V> {
    public UnweightedGraph() { 
    }
    public UnweightedGraph(V[] vertices, int[][] edges) { 
        super(vertices, edges); 
    }
    public UnweightedGraph(List<V> vertices, List<Edge> edges) { 
        super(vertices, edges); 
    }
    public UnweightedGraph(List<Edge> edges, int numberOfVertices) { 
        super(edges, numberOfVertices); 
    }
    public UnweightedGraph(int[][] edges, int numberOfVertices) { 
        super(edges, numberOfVertices); 
    }
}

/*

1. Ylätason kuvaus: "Hakupuun rakentaminen"
Tämä koodi ei ainoastaan mallinna kaupunkien välistä verkkoa, vaan se toteuttaa syvyyssuuntaisen haun (DFS). Haun tarkoituksena on vierailla jokaisessa verkon solmussa kulkemalla mahdollisimman syvälle yhtä polkua pitkin ennen palaamista takaisin (backtracking).

Tavoite: Muodostaa alkusolmusta (tässä Chicago) alkava "hakupuu".

Tulos: Saadaan selville, mitkä solmut ovat saavutettavissa ja mitä reittiä pitkin niihin päädyttiin.

Rakenne: Koodi erottaa toisistaan verkon tallennuksen (AbstractGraph), haun suorituksen (metodi dfs) ja haun tulokset (Tree).

2. Pseudokoodi: DFS-algoritmi
DFS toimii rekursiivisesti. Tässä on sen toimintalogiikka yksinkertaistettuna:

Plaintext

ALGORITMI DFS(lähtösolmu u):
    1. Merkitse solmu u vierailluksi.
    2. Lisää u hakujärjestys-listaan.
    3. JOKAISELLE solmun u naapurille v:
        JOS v ei ole vielä vieraillut:
            Aseta u solmun v vanhemmaksi (parent[v] = u).
            KUTSU DFS(v) rekursiivisesti.
            
LOPPUTULOS:
    Palauta Tree-olio, joka sisältää:
    - Hakujärjestyksen (missä järjestyksessä solmut löytyivät)
    - Vanhemmuussuhteet (mitä polkua pitkin solmuun tultiin)
3. Tarkka analyysi
A. Tilanhallinta haun aikana
Algoritmi käyttää kolmea tietorakennetta haun tilan seuraamiseen:

boolean[] isVisited: Estää algoritmin juuttumisen ikuiseen silmukkaan. Jokainen kaupunki käsitellään vain kerran.

List<Integer> searchOrder: Tallentaa aikajärjestyksen. Chicagosta alkavassa haussa Chicago on aina ensimmäinen alkio.

int[] parent: Tämä on kriittinen reitin tallentamiseen. Jos Chicagosta (5) mennään Denveriin (3), asetetaan parent[3] = 5.

B. Sisäluokka Tree (Data Transfer Object)
DFS-metodi palauttaa AbstractGraph<V>.Tree-olion. Tämä on teknisesti DTO (Data Transfer Object). Sen sijaan, että metodi palauttaisi useita erillisiä taulukoita, se paketoi kaiken oleellisen tiedon yhteen olioon.

Se tarjoaa getParent(v)-metodin, jolla voidaan rakentaa reitti takaisinpäin mistä tahansa solmusta juureen.

C. Polymorfismi ja dynaaminen sidonta
main-metodissa käytetään muuttujaa Graph<String> graph.

Vaikka kutsumme graph.dfs(), Java suorittaa AbstractGraph-luokassa olevan koodin.

Vaikka UnweightedGraph on "tyhjä" haun osalta, se pystyy suorittamaan sen, koska se perii kaiken logiikan.

D. Tulostuslogiikan analyysi
Java

int v = searchOrders.get(i);
if (dfs.getParent(v) != -1) { ... }
Tässä kohdassa koodi iteroi hakujärjestystä. Jos solmulla on vanhempi (parent ei ole -1), se tarkoittaa, että solmu löydettiin osana hakupuuta. Esimerkiksi jos Seattle löytyi Chicagon kautta, koodi osaa hakea Seattlen indeksin ja katsoa parent-taulukosta, kuka sen sinne "toi".



Tämä on teknisesti puhdas tapa toimia:

Interface sanoo: "Verkolla on DFS-haku".

AbstractGraph toteuttaa haun, koska se on sama kaikille verkoille.

Tree-sisäluokka on olemassa siksi, että haku tuottaa paljon tietoa (järjestys, vanhemmat, juuri), ja on helpompaa palauttaa yksi "paketti" (olio) kuin monta erillistä taulukkoa.


Se tunne on täysin oikeutettu. Liangin kirjan (Introduction to Java Programming) esimerkit on suunniteltu opettamaan ohjelmistoarkkitehtuuria, mutta ne on kirjoitettu tavalla, joka olettaa lukijan rakentavan kokonaisen kirjaston kansion sisälle.Tässä on kolme pääsyytä siihen, miksi ne vaativat "taikoja" toimiakseen CodeRunnerissa tai yksittäisissä tiedostoissa:

1. Modulaarisuus vs. Yksi tiedostoLiangin koodit on jaettu useisiin tiedostoihin (Graph.java, AbstractGraph.java, UnweightedGraph.java).Kirjan oletus: Kaikki tiedostot ovat samassa kansiossa (paketti), jolloin Java löytää ne automaattisesti.Todellisuus (esim. CodeRunner): Sinulla on usein käytössä vain yksi tekstikenttä. Jotta koodi toimii, kaikki nuo tiedostot on "liimattava" yhteen. Tästä syntyy vaikeuksia sulkeiden {} ja public-määreiden kanssa, koska yhdessä Java-tiedostossa saa olla vain yksi public class.

2. Abstraktiotasojen kerrostaminenLiang käyttää "Interface -> Abstract Class -> Concrete Class" -ketjua. Se on ammattimainen tapa, mutta se on hauras:Jos kopioit vain UnweightedGraphin, se ei toimi ilman AbstractGraphia.Jos kopioit AbstractGraphin, se ei toimi ilman Graph-rajapintaa.Se on kuin yrittäisi käyttää sähköauton akkua ilman itse autoa ja laturia – tarvitset koko ekosysteemin kerralla.

3. Sisäluokkien (Inner Classes) monimutkaisuusLiang on sijoittanut Edge- ja Tree-luokat AbstractGraphin sisälle.Tämä on siistiä arkkitehtuuria, mutta se tekee tyyppiviittauksista hankalia. Esimerkiksi Tree ei ole vain Tree, vaan se on AbstractGraph<V>.Tree.Pienikin virhe siinä, mihin kohtaan luokkaa kirjoitat public class Tree {, rikkoo koko rakenteen, koska se saattaa päätyä väärän luokan sisälle (kuten sinun koodissasi kävi).Miten näitä pitäisi "lukea"? Kun katsot Liangin esimerkkejä, älä ajattele niitä itsenäisinä ohjelmina, vaan palasina isoa konetta.Liangin osaVertauskuvaMiksi vaikea?Graph (Interface)RakennuspiirustusEi tee mitään, kertoo vain mitä pitää löytyä.AbstractGraphAuton alusta ja moottoriSisältää 90% koodista, mutta ei voi ajaa ilman koria.UnweightedGraphAuton kori ja renkaatPieni määrä koodia, mutta vaatii moottorin toimiakseen.Vinkki jatkoon:Jos jatkat Liangin esimerkkien parissa, helpoin tapa on luoda itselleen yksi "pohjatiedosto", joka sisältää nuo rajapinnat ja abstraktit luokat. Sitten vain vaihdat main-metodia ja mahdollista konkreettista luokkaa sen mukaan, mitä harjoitusta teet


Mitä kannattaa pyytää (Prompt-vinkit):
"Yksi tiedosto" (Self-contained): Pyydä aina, että koodi on yhdessä tiedostossa. Mainitse, että siinä pitää olla rajapinta, abstrakti luokka ja konkreettinen toteutus samassa paketissa.

"Ei paketteja" (No package declarations): Pyydä jättämään package-rivit pois, koska ne aiheuttavat virheitä, jos tiedostorakenne ei vastaa paketin nimeä.

"Kaikki riippuvuudet mukaan": Jos haluat testata esimerkiksi DFS-haun, muista sanoa: "Sisällytä myös Tree-sisäluokka ja Edge-luokka AbstractGraphiin."

2. Miten ohitat metodit, joita et jaksa kirjoittaa?
Jos joudut käyttämään Liangin valmista Graph-rajapintaa (esim. harjoitustyössä), mutta et tarvitse kaikkia metodeja, voit käyttää "tyhjiä" toteutuksia. Tämä on yleinen kikka ohjelmoinnissa:

Java

@Override
public void clear() {
    // Jätetään tyhjäksi tai heitetään virhe
    throw new UnsupportedOperationException("Ei toteutettu vielä.");
}
Näin kääntäjä pysyy tyytyväisenä (koska metodi on olemassa), mutta sinun ei tarvitse tuhlata aikaa sen koodaamiseen.

3. Tenttinäkökulma: Mitä metodit oikeasti tekevät?
Tentissä sinua pyydetään usein toteuttamaan vain yksi tietty algoritmi. Silloin unohdat kaiken muun ja keskityt vain siihen.

Esimerkki: "Toteuta metodi, joka palauttaa solmun naapurit" Sinun ei tarvitse välittää siitä, miten verkko on luotu. Vastaat vain:

Java

public List<Integer> getNeighbors(int index) {
    return neighbors.get(index);
}
Tämä on se "minimalistinen" ajattelutapa, jota kannattaa suosia.

Yhteenveto
Liangin koodi: "Sveitsin armeijan linkkuveitsi" (kaikki mahdollinen mukana).

Oma sovellus: "Keittiöveitsi" (vain ne työkalut, joilla aiot leikata).

Minimalistinen harjoitus: "Pelkkä terä" (vain solmut ja kaaret).

*/