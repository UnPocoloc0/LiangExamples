import java.util.*;


// Toteutetaan verkkoluokka
class GraphSandBox {
	public static void main(String[] args) {
		
		// Verkon solmut
		String [] vertices = {
			
			"Relaxin", 
			"Kind of Blue", 
			"Steaming", 
			"Milestones", 
			"'Round About Midnight"}; 
		
		// Verkon kaaret
		Edge[] edges = {
			
			new Edge(0, 2, "Red Garland"),
			new Edge(1, 1, "Bill Evans"),
			new Edge(0, 3, "Red Garland"),
			new Edge(0, 4, "Red Garland")
			
		};
		// Konkreettisen verkon olio
		Graph<String> jazzGraph = new UnweightedGraph(vertices, edges);
		
		System.out.println("Verkon solmut: " + jazzGraph.getVertices());
		System.out.println("Solmujen määrä: " + jazzGraph.getSize());
		
		System.out.println("Red Garland soittaa levyillä: " + jazzGraph.getAlbumsByMusician("Red Garland"));
		System.out.println("Bill Evans soittaa levyillä: " + jazzGraph.getAlbumsByMusician("Bill Evans"));
		
		
	}
}

// Rajapinnan metodit
interface Graph<V> {
	
	int getSize();
	List<V> getVertices(); 
	List<V> getAlbumsByMusician(String musician);
}

abstract class AbstractGraph<V> implements Graph<V> {
	
	protected List<V> vertices = new ArrayList<>();
	protected List<Edge> edges = new ArrayList<>();


	
	// Nämä metodit periytyvät konkreettiseen luokkaan 
	@Override
	public int getSize() {
		
		return vertices.size(); 
	}
	
	@Override 
	public List<V> getVertices() { 
		return vertices; 
	}
	
	
	@Override
	public List<V> getAlbumsByMusician(String musician) {
		
		Set<V> result = new HashSet<>();
		
		for (Edge e : edges) {
			if (e.musician.equals(musician)) {
				result.add(vertices.get(e.from));
				result.add(vertices.get(e.to));
			}
		}
		return new ArrayList<>(result);
	}
	
}

// Konkreettinen luokka
class UnweightedGraph<V> extends AbstractGraph<V> {
	
	public UnweightedGraph(V[] vertices, Edge[] edges) {
		
		this.vertices.addAll(Arrays.asList(vertices));
		this.edges.addAll(Arrays.asList(edges));
	}
	
}

class Edge {
	
	int from;
	int to;
	String musician;
	
	public Edge(int from, int to, String musician) {
		
		this.from = from;
		this.to = to;
		this.musician = musician; 
		
	}
}


