import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    private static Scanner scanner;
    private static int userInput;
    static Graph<Integer> graphObject = new Graph<>();
    public static void main(String[] args) {

        System.out.println("Reading from file ");
        parseFile(graphObject);
    }

    private static void menu(){
        scanner = new Scanner(System.in);
        while (userInput != 3){
            System.out.println(
                    """
                            \n===================================================
                            Wanna check if graph is cyclic or acyclic ?
                            Press 1 : Use Sink algorithm method 
                            Press 2 : Use Depth First Search Algorithm
                            Press 3 : Quit 
                            """
            );
            System.out.println("Enter a Number  : ");
            userInput = scanner.nextInt();
            if (userInput == 1){
                //boolean isAcyclic = isAcyclicBySink();
                System.out.println();
            } else if (userInput == 3 ) {
                graphObject.testf();
                break;

            }


        }
        scanner.close();
    }


    private static void parseFile(Graph<Integer> graph) {

        try {
            File inputFile = new File("input.txt");
            scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arrayFromFile = line.split(" ");
                int src = Integer.parseInt(arrayFromFile[0]);
                int dest = Integer.parseInt(arrayFromFile[1]);

                graph.addEdge(src, dest);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        System.out.println("Graph:\n" + graph.printGraph());
        boolean isAcyclic = graph.isAcyclicBySink();
        System.out.println("=======================\n" +
                "Graph:\n" + graph.printGraph());
        if (isAcyclic){
            System.out.println("The graph is Acyclic");
        }else {
            System.out.println("The graph is a cyclic");
        }


        //2 methods to detect cyclic -> dfs and sink algo


    }

}