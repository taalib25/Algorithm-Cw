import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Graph<Integer> graphObject = new Graph<>();
        parseFile(graphObject);

       /* graphObject.addEdge(1, 2);
        graphObject.addEdge(2, 3);
        graphObject.addEdge(2, 4);
        graphObject.addEdge(2, 5);
        graphObject.addEdge(3, 1);
        graphObject.addEdge(3, 6);
        graphObject.addEdge(4, 5);
        graphObject.addEdge(5, 6);
*/



    }

    private static void parseFile(Graph<Integer> graphObject) {

        try {
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int src = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);

                graphObject.addEdge(src, dest);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        System.out.println("Graph:\n" + graphObject.printGraph());
        boolean isacyclic = graphObject.isAcyclic();
        System.out.println("The graph is "+ isacyclic);

        //2 methods to detect cyclic -> dfs and sink algo


    }
}