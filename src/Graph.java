import java.util.*;

class Graph<T> {
    //References
    //https://progressivecoder.com/graph-implementation-in-java-using-hashmap/
    private Map<T, List<T>  > graph = new HashMap<>();
    private T sink;
    private ArrayList<T> arraySink; //stores all the sinks

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

    public boolean isAcyclicBySink() {
         while (!graph.isEmpty()) {

        // Find a sink in the graph
             int checkSink = -1;
             if (findSink() != null) {
                  sink = findSink();
                 checkSink = (int) sink;
                 // access or modify bannan here
             }else {
                 checkSink = -1;
             }

        if (checkSink == -1) {

            // If there are no sinks, the graph contains a cycle
            return false;
        } else {

            // Remove the sink from the graph
            removeVertex(sink);
            // Print the sink that was removed
            System.out.println("Removed sinks: " + arraySink.toString());
        }

    }
        return true;
}

    //function to find the sink in a graph
    private T findSink() {
        arraySink = new ArrayList<T>();
        for (T vertex : graph.keySet()) {

            // A sink has no outgoing edges
            if (graph.get(vertex).isEmpty()) {
                arraySink.add(vertex);
               // System.out.println(arraySink);
                System.out.println("sink is "+vertex);
                return vertex;
            }
        }
        // If there are no sinks, return null
        return null;
    }

    // Helper method to remove a vertex and its edges from the graph
    private void removeVertex(T vertex) {
        graph.remove(vertex);
        ArrayList stuff = new ArrayList<>();
        for (List<T> edges : graph.values()) {
           edges.removeIf(edge -> edge == vertex);

             stuff.add(edges);
        }
        System.out.println("After Removing stuff");
        System.out.println(stuff);
    }

}