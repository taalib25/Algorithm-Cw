import java.util.*;

class Graph<node> {
    //References
    //https://progressivecoder.com/graph-implementation-in-java-using-hashmap/
    private Map<node, List<node>  > graph = new HashMap<>();
    private node sink;
    private ArrayList<node> arraySink; //stores all the sinks

    public void addEdge(node src, node dest) {

        if (!graph.containsKey(src)) {
            addVertex(src);
        }

        if (!graph.containsKey(dest)) {
            addVertex(dest);
        }
        //gets the vertices and adds the edge to the destination
        graph.get(src).add(dest);

    }
    private void addVertex(node vertex) {
        graph.put(vertex, new LinkedList<node>());
    }

    public String printGraph() {
        StringBuilder builder = new StringBuilder();

        for(node vertex : graph.keySet()) {
            builder.append(vertex.toString() + ": ");
            for(node node: graph.get(vertex)) {
                builder.append(node.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean isAcyclicBySink() {
        arraySink = new ArrayList<node>();
        System.out.println("========ELIMINATION PROCESS =========");
         while (!graph.isEmpty()) {

        // Find a sink in the graph
             int checkSink = -1;
             if (findSink() != null) {
                  sink = findSink();
                 arraySink.add(sink);
                 System.out.println("Found Sink -> "+sink);
                 checkSink = (int) sink;
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

        }

    }

         System.out.println("Removed sinks: " + arraySink.toString().substring(1, arraySink.toString().length()-1));
         System.out.println("=====================================");
        return true;
}

    //function to find the sink in a graph
    private node findSink() {

        for (node vertex : graph.keySet()) {
            // A sink has no outgoing edges
            if (graph.get(vertex).isEmpty()) {
                return vertex;
            }

        }
        // If there are no sinks, return null
        return null;
    }

    //Removes  vertexes and its edges
    private void removeVertex(node vertex) {
        graph.remove(vertex);
        ArrayList vertix = new ArrayList<>();
        for (List<node> edges : graph.values()) {
           edges.removeIf(edge -> edge == vertex);

             vertix.add(edges);
        }

    }

    public void findCycles() {
        //https://www.geeksforgeeks.org/detect-cycle-in-a-graph/

        Set<node> visited = new HashSet<>();
        Stack<node> stack = new Stack<>();
        List<List<node>> cycles = new ArrayList<>();


        for (node vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {

                //traversing through the graph
                depthFirstSearch(vertex, visited, stack, cycles); //recurring function
            }
        }

        System.out.println("Number of cycles: " + cycles.size());
        for (List<node> cycle : cycles) {
            System.out.println("Cycle: " + cycle);
        }
    }

    private void depthFirstSearch(node vertex, Set<node> visited, Stack<node> stack, List<List<node>> cycles) {
        visited.add(vertex);
        stack.push(vertex);

        for (node neighbor : graph.get(vertex)) {
            if (!visited.contains(neighbor)) {
                depthFirstSearch(neighbor, visited, stack, cycles);
            } else if (stack.contains(neighbor)) {
                List<node> cycle = new ArrayList<>();
                while (!stack.peek().equals(neighbor)) {
                    cycle.add(stack.pop());
                }
                cycle.add(stack.pop());
                cycles.add(cycle);
            }
        }
        if(!stack.isEmpty()){
            stack.pop();
        }

    }



}