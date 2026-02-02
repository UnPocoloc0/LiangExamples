import java.util.*;

// 1. PÄÄLUOKKA 
class testGraph {
    
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

        // Luodaan verkko
        // muuttuja on tyypiltään verkko, ja konkreettinen toteutus on painottamaton verkko 
        Graph<String> graph1 = new UnweightedGraph<>(vertices, edges);
        System.out.println("The number of vertices in graph1: " + graph1.getSize());
        System.out.println("The vertex with index 1 is " + graph1.getVertex(1));
        System.out.println("The index for Miami is " + graph1.getIndex("Miami"));
        System.out.println("\nThe edges for graph1:");
        graph1.printEdges();
    }
}

// 2. RAJAPINTA
interface Graph<V> {
    int getSize();
    List<V> getVertices();
    V getVertex(int index);
    int getIndex(V v);
    List<Integer> getNeighbors(int index);
    int getDegree(int v);
    void printEdges();
    void clear();
    boolean addVertex(V vertex);
    boolean addEdge(int u, int v);
}

// 3. ABSTRAKTI LUOKKA
abstract class AbstractGraph<V> implements Graph<V> {
    
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph() { 
        
    }

    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++)
            addVertex(vertices[i]);
        createAdjacencyLists(edges, vertices.length);
    }

    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++)
            addVertex(vertices.get(i));
        createAdjacencyLists(edges, vertices.size());
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V)(new Integer(i)));
        createAdjacencyLists(edges, numberOfVertices);
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++)
            addVertex((V)(new Integer(i)));
        createAdjacencyLists(edges, numberOfVertices);
    }

    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge: edges) {
            addEdge(edge.u, edge.v);
        }
    }

    @Override 
    public int getSize() { 
        return vertices.size(); 
    }
    @Override 
    public List<V> getVertices() {
        return vertices; 
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
    public List<Integer> getNeighbors(int index) 
    {
        List<Integer> result = new ArrayList<>();
        for (Edge e: neighbors.get(index))
            result.add(e.v);
        return result;
    }

    @Override 
    public int getDegree(int v) { 
        return neighbors.get(v).size(); 
    }

    @Override 
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e: neighbors.get(u)) {
                System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }

    @Override public void clear() { 
        vertices.clear(); neighbors.clear(); 
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
        if (e.u < 0 || e.u > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.u);
        if (e.v < 0 || e.v > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.v);
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
            return u == ((Edge)o).u && v == ((Edge)o).v;
        }
    }
}

// 4. KONKREETTINEN LUOKKA
public class UnweightedGraph<V> extends AbstractGraph<V> {
    
    public UnweightedGraph() { 
    }

    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List<V> vertices, List<AbstractGraph.Edge> edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List<AbstractGraph.Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
}

/*
1. Ylätason kuvaus: "Erikoistunut toteuttaja"
UnweightedGraph on konkreettinen aliluokka, jonka tehtävänä on mahdollistaa verkon luominen (instansiointi). Se on teknisesti "ohut" luokka, koska se ei sisällä omaa algoritmillista logiikkaa, vaan se luottaa täysin perimäänsä AbstractGraph-luokkaan.

Sen olemassaolon tarkoitus on:

Tyypittäminen: Se ilmoittaa olevansa nimenomaan painottamaton verkko (erotuksena myöhemmin tulevasta WeightedGraphista).

Rajapinta ulkomaailmaan: Se tarjoaa julkiset konstruktorit, joilla ohjelmoija voi syöttää dataa sisään (taulukkoina tai listoina).

2. Pseudokoodi (Toimintaperiaate)
Vaikka koodi koostuu pääasiassa konstruktoreista, sen toimintalogiikka voidaan kuvata näin:

LUOKKA UnweightedGraph PERII AbstractGraph:

    KONSTRUKTORI (solmut, kaaret):
        KUTSU yläluokan (AbstractGraph) konstruktoria samoilla parametreilla
        
    KONSTRUKTORI (kaaret, solmujen_määrä):
        LUO solmut automaattisesti indekseinä 0...n
        KUTSU yläluokan konstruktoria
        
    // Luokka ei toteuta uusia metodeja, koska se perii 
    // kaiken toiminnallisuuden (getSize, getNeighbors, DFS, BFS)
    
    
3. Tarkempi analyysi

A. Konstruktorien monimuotoisuus (Overloading)
Luokka tarjoaa useita tapoja rakentaa verkko, mikä tekee siitä joustavan:

Objektipohjainen: Voit antaa listan kaupunki-olioita ja listan kaari-olioita.

Indeksipohjainen: Voit antaa vain tiedon solmujen määrästä (esim. 5) ja kaksiulotteisen taulukon yhteyksistä. Tällöin koodi luo solmut automaattisesti numeroina.

B. super-avainsanan rooli
Jokainen konstruktori alkaa sanalla super(...). Tämä on kriittistä, koska:

UnweightedGraph ei itse omista vertices- tai neighbors-muuttujia; ne asuvat yläluokassa.

super siirtää datan yläluokan hallintaan, missä createAdjacencyLists-metodi rakentaa verkon sisäisen muistirakenteen.

C. Geneerisyys <V>
Luokka säilyttää geneerisyyden, mikä tarkoittaa, että se ei ota kantaa siihen, mitä solmut ovat. Tämä mahdollistaa saman luokan käytön niin lentoreittien (String), tieverkostojen (Integer) kuin sosiaalisen median käyttäjien (User-objektit) mallintamiseen.

D. Miksi se on "tyhjä"?
Luokan "tyhjyys" on merkki hyvästä olio-ohjelmointisuunnittelusta (DRY - Don't Repeat Yourself). Kaikki verkon perusominaisuudet on keskitetty yhteen paikkaan (AbstractGraph), jolloin UnweightedGraph toimii vain helposti lähestyttävänä käyttöliittymänä kyseiseen logiikkaan.

1. Ylätason kuvaus: "Erikoistunut toteuttaja"
UnweightedGraph on konkreettinen aliluokka, jonka tehtävänä on mahdollistaa verkon luominen (instansiointi). Se on teknisesti "ohut" luokka, koska se ei sisällä omaa algoritmillista logiikkaa, vaan se luottaa täysin perimäänsä AbstractGraph-luokkaan.

Sen olemassaolon tarkoitus on:

Tyypittäminen: Se ilmoittaa olevansa nimenomaan painottamaton verkko (erotuksena myöhemmin tulevasta WeightedGraphista).

Rajapinta ulkomaailmaan: Se tarjoaa julkiset konstruktorit, joilla ohjelmoija voi syöttää dataa sisään (taulukkoina tai listoina).

2. Pseudokoodi (Toimintaperiaate)
Vaikka koodi koostuu pääasiassa konstruktoreista, sen toimintalogiikka voidaan kuvata näin:

Plaintext

LUOKKA UnweightedGraph PERII AbstractGraph:

    KONSTRUKTORI (solmut, kaaret):
        KUTSU yläluokan (AbstractGraph) konstruktoria samoilla parametreilla
        
    KONSTRUKTORI (kaaret, solmujen_määrä):
        LUO solmut automaattisesti indekseinä 0...n
        KUTSU yläluokan konstruktoria
        
    // Luokka ei toteuta uusia metodeja, koska se perii 
    // kaiken toiminnallisuuden (getSize, getNeighbors, DFS, BFS)
3. Tarkempi analyysi
A. Konstruktorien monimuotoisuus (Overloading)
Luokka tarjoaa useita tapoja rakentaa verkko, mikä tekee siitä joustavan:

Objektipohjainen: Voit antaa listan kaupunki-olioita ja listan kaari-olioita.

Indeksipohjainen: Voit antaa vain tiedon solmujen määrästä (esim. 5) ja kaksiulotteisen taulukon yhteyksistä. Tällöin koodi luo solmut automaattisesti numeroina.

B. super-avainsanan rooli
Jokainen konstruktori alkaa sanalla super(...). Tämä on kriittistä, koska:

UnweightedGraph ei itse omista vertices- tai neighbors-muuttujia; ne asuvat yläluokassa.

super siirtää datan yläluokan hallintaan, missä createAdjacencyLists-metodi rakentaa verkon sisäisen muistirakenteen.

C. Geneerisyys <V>
Luokka säilyttää geneerisyyden, mikä tarkoittaa, että se ei ota kantaa siihen, mitä solmut ovat. Tämä mahdollistaa saman luokan käytön niin lentoreittien (String), tieverkostojen (Integer) kuin sosiaalisen median käyttäjien (User-objektit) mallintamiseen.

D. Miksi se on "tyhjä"?
Luokan "tyhjyys" on merkki hyvästä olio-ohjelmointisuunnittelusta (DRY - Don't Repeat Yourself). Kaikki verkon perusominaisuudet on keskitetty yhteen paikkaan (AbstractGraph), jolloin UnweightedGraph toimii vain helposti lähestyttävänä käyttöliittymänä kyseiseen logiikkaan.
*/