import java.util.Arrays;
import java.util.Scanner;

public class GraphColoring {

    private static int[][] graph;
    private static int[] colors;
    private static int numVertices;
    private static int numColors;
    private static int[] bestSolution;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of vertices
        System.out.print("Enter the number of vertices in the graph: ");
        numVertices = scanner.nextInt();

        // Create the adjacency matrix representation of the graph
        graph = new int[numVertices][numVertices];
        System.out.println("Enter the adjacency matrix of the graph:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        // Get the number of colors
        System.out.print("Enter the number of colors available: ");
        numColors = scanner.nextInt();

        // Initialize the colors array
        colors = new int[numVertices];
        Arrays.fill(colors, -1);

        // Initialize the best solution array
        bestSolution = new int[numVertices];
        Arrays.fill(bestSolution, -1);

        // Solve the graph coloring problem using backtracking with branch and bound
        graphColoring(0);

        // Print the best solution
        System.out.println("Best coloring:");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + ": Color " + bestSolution[i]);
        }
    }

    private static void graphColoring(int vertex) {
        if (vertex == numVertices) {
            // All vertices are colored, check if it's the best solution
            if (isValidSolution()) {
                bestSolution = Arrays.copyOf(colors, numVertices);
            }
            return;
        }

        for (int color = 0; color < numColors; color++) {
            colors[vertex] = color;
            if (isValidPartialSolution(vertex)) {
                graphColoring(vertex + 1);
            }
            colors[vertex] = -1;
        }
    }

    private static boolean isValidPartialSolution(int vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && colors[i] == colors[vertex]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSolution() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (graph[i][j] == 1 && colors[i] == colors[j]) {
                    return false;
                }
            }
        }
        return true;
    }

}