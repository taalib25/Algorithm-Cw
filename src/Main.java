import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Name : Taalib Asaf
//IIT Number : 20210075
public class Main {


    private static Scanner scanner;
    private static int userInput;
    static Graph<Integer> graphObject = new Graph<>();
    public static void main(String[] args) {

        System.out.println("Reading from file ");
        parseFile(graphObject);
        graphObject.findCycles();
    }


    private static void parseFile(Graph<Integer> graph) {

        String textFiles = "input1.txt";
        //String textFiles = "input2.txt";

        try {
            File inputFile = new File(textFiles);
            scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] arrayFromFile = line.split(" ");
                    int src = Integer.parseInt(arrayFromFile[0]);
                    int dest = Integer.parseInt(arrayFromFile[1]);
                    graph.addEdge(src, dest);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }catch (NumberFormatException e){
            System.out.println("Invalid Input .Unable To Parse!");
            System.exit(0);
        }
        System.out.println("=======================================");
        System.out.println("GRAPH REPRESENTATION USING ADJACENT LISTS ");
        System.out.println("=======================================");


        System.out.println("Graph:\n" + graph.printGraph());
        boolean isAcyclic = graph.isAcyclicBySink();
        if (isAcyclic){
            System.out.println("Graph is Empty ");
            System.out.println("=======================");
            System.out.println("Therefore ,The graph is Acyclic");
        }else {
            System.out.println("Graph after removing Sinks");
            graph.printGraph();
            System.out.println("=======================");
            System.out.println("The graph is a cyclic");
        }



    }

}