import java.util.*;

public class TestBFS {
    public static void main(String[] args) {
        String[] vertices = {"Seattle", "San Francisco", "Los Angeles", 
            "Denver", "Kansas City", "Chicago", "Boston", "New York",
            "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
            {0, 1}, {0, 3}, {0, 5}, {1, 0}, {1, 2}, {1, 3},
            {2, 1}, {2, 3}, {2, 4}, {2, 10}, {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
            {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
            {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7}, {6, 5}, {6, 7},
            {7, 4}, {7, 5}, {7, 6}, {7, 8}, {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
            {9, 8}, {9, 11}, {10, 2}, {10, 4}, {10, 8}, {10, 11},
            {11, 8}, {11, 9}, {11, 10}
        };

        Graph<String> graph = new UnweightedGraph<>(vertices, edges); 
        AbstractGraph<String>.Tree bfs = graph.bfs(graph.getIndex("Chicago"));

        java.util.List<Integer> searchOrders = bfs.getSearchOrder();
        
        System.out.println(bfs.getNumberOfVerticesFound() + " vertices are searched in this order:");
        for (int i = 0; i < searchOrders.size(); i++) 
            System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
        
        System.out.println("\n");

        for (int i = 0; i < searchOrders.size(); i++) {
            int v = searchOrders.get(i);
            if (bfs.getParent(v) != -1)
                System.out.println("parent of " + graph.getVertex(v) + " is " + graph.getVertex(bfs.getParent(v)));
        }
    }
}

interface Graph<V> {
    int getSize();
    V getVertex(int index);
    int getIndex(V v);
    List<Integer> getNeighbors(int index);
    AbstractGraph<V>.Tree bfs(int v);
}

abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++) {
            this.vertices.add(vertices[i]);
            neighbors.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    public void addEdge(int u, int v) {
        neighbors.get(u).add(new Edge(u, v));
    }

    @Override public int getSize() { return vertices.size(); }
    @Override public V getVertex(int index) { return vertices.get(index); }
    @Override public int getIndex(V v) { return vertices.indexOf(v); }
    @Override public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index)) result.add(e.v);
        return result;
    }

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

    public static class Edge {
        public int u, v;
        public Edge(int u, int v) { this.u = u; this.v = v; }
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
    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
}

/*
1. Ylätason kuvaus: "Aalto etenee"Leveyssuuntainen haku vierailee ensin kaikissa lähtösolmun lähimmissä naapureissa, sitten näiden naapurien naapureissa ja niin edelleen.Tavoite: Käydä läpi kaikki saavutettavissa olevat solmut mahdollisimman lyhyttä reittiä pitkin.Tärkein ominaisuus: Painottamattomassa verkossa BFS löytää aina lyhimmän reitin (vähiten kaaria) alkusolmun ja minkä tahansa muun solmun välillä.Vertauskuva: Jos DFS on kuin tutkimusmatkailija, joka seuraa yhtä polkua luolan perälle asti, BFS on kuin hitaasti leviävä sumu, joka täyttää kaikki käytävät tasaisesti.2. Pseudokoodi: BFS-algoritmiBFS:n ydin on Jono (Queue), joka noudattaa FIFO-periaatetta (First-In, First-Out).PlaintextALGORITMI BFS(lähtösolmu v):

    1. Luo tyhjä Jono (Queue).
    2. Merkitse v vierailluksi ja lisää se Jonoon.
    3. NIIN KAUAN KUIN Jono ei ole tyhjä:
        a. Poista Jonon ensimmäinen solmu u.
        b. Lisää u hakujärjestys-listaan.
        c. JOKAISELLE solmun u naapurille n:
            JOS n ei ole vielä vieraillut:
                i. Merkitse n vierailluksi.
                ii. Aseta u solmun n vanhemmaksi (parent[n] = u).
                iii. Lisää n Jonon perälle.
                
                
LOPPUTULOS:
    Tree-olio, joka sisältää reitit ja hakujärjestyksen.
3. Tarkka analyysi
    A. Miksi Jono (Queue)?Jono varmistaa, että kaikki etäisyydellä $d$ olevat solmut käsitellään ennen kuin siirrytään etäisyydelle $d+1$. Kun löydämme uuden solmun, se laitetaan jonon loppuun odottamaan vuoroaan, kun taas nykyisen tason solmut poistetaan jonon alusta.
   B. Parent-taulukon merkitysBFS:ssä parent[v] kertoo, mistä solmusta tultiin, kun solmu v löydettiin ensimmäisen kerran. Koska BFS etenee tasoittain, ensimmäinen kerta, kun solmu kohdataan, on takuulla lyhin reitti sinne.Jos haluat tietää reitin Chicagosta Miamiin, katsot: Miami $\rightarrow$ Miamin vanhempi $\rightarrow$ sen vanhempi... kunnes päädyt Chicagoon.
    
    C. Tilavaatimus ja tehokkuusAika: $O(V + E)$, missä $V$ on solmut ja $E$ on kaaret. Jokainen solmu ja kaari tarkistetaan kerran.Tila: $O(V)$. Pahimmassa tapauksessa joudumme tallentamaan suuren määrän solmuja jonoon (esim. tähden muotoisessa verkossa kaikki naapurit kerralla).
    
    D. Ero DFS-hakuun (Tenttiklassikko)OminaisuusBFS (Leveys)DFS (Syvyys)TietorakenneJono (Queue)Pino (Stack) / RekursioEtenemistapaKerroksittainMahdollisimman syvälleLyhin reittiKyllä (kaarien määrässä)Ei välttämättäKäyttökohdeGPS-navigointi, sosiaaliset verkostotPelien tilapuut, syklin tunnistus

*/