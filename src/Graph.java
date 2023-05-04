import java.util.*;

class Graph<T> {
    //https://progressivecoder.com/graph-implementation-in-java-using-hashmap/
    private Map<T, List<T>  > graph = new HashMap<>();

    public void addEdge(T src, T dest) {

        if (!graph.containsKey(src)) {
            addVertex(src);
        }

        if (!graph.containsKey(dest)) {
            addVertex(dest);
        }
        //gets the vertices and adds the edge to the destination
        graph.get(src).add(dest);

    }
    private void addVertex(T vertex) {
        graph.put(vertex, new LinkedList<T>());
    }

    public void hasVertex(T vertex) {
        if(graph.containsKey(vertex)) {
            System.out.println("The Graph contains " + vertex + " as a vertex");
        }else {
            System.out.println("The Graph does not contain " + vertex + " as a vertex");
        }
    }
            //graphObject.hasVertex(3);
            //graphObject.hasEdge(0,1);
    public void hasEdge(T source, T destination) {
        if(graph.get(source).contains(destination)) {
            System.out.println("The Graph has an edge between " + source + " and " + destination);
        }else {
            System.out.println("The Graph has no edge between " + source + " and " + destination);
        }
    }


    public String printGraph() {
        StringBuilder builder = new StringBuilder();

        for(T vertex : graph.keySet()) {
            builder.append(vertex.toString() + ": ");
            for(T node: graph.get(vertex)) {
                builder.append(node.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean isAcyclic() {
        // Create a set to keep track of visited vertices
        Set<Integer> visited = new HashSet<>();

        while (!graph.isEmpty()) {
            // Find a sink (a vertex with no outgoing edges)
            int sink = -1;
            for (T vertex : graph.keySet()) {
                if (graph.get(vertex).isEmpty()) {
                    sink = (int) vertex;
                    System.out.println("hhh"+sink);
                    break;
                }
            }

            // If no sink is found, the graph has a cycle
            if (sink == -1) {
                return false;
            }

            // Remove the sink and mark it as visited
            graph.remove(sink);
            visited.add(sink);

            // Remove the sink from the adjacency list of its neighbors
            for (List<T> neighbors : graph.values()) {
                neighbors.remove(Integer.valueOf(sink));
            }
        }

        // If all vertices have been visited, the graph is acyclic
        return visited.size() == graph.size();
    }


}