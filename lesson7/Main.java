package java.lesson7;

public class Main {

    public static void main(String[] args) {
        Graph graph = createGraph();
        graph.findShortestWay("Рязань", "Курск");
    }

    private static Graph createGraph() {
        Graph graph = new Graph(10);

        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Липецк");
        graph.addVertex("Воронеж");
        graph.addVertex("Рязань");
        graph.addVertex("Тамбов");
        graph.addVertex("Саратов");
        graph.addVertex("Калуга");
        graph.addVertex("Орёл");
        graph.addVertex("Курск");

        graph.addEdges("Москва", "Тула", "Рязань", "Калуга");
        graph.addEdges("Воронеж", "Липецк", "Саратов", "Курск");
        graph.addEdges("Тамбов", "Рязань", "Саратов");
        graph.addEdges("Орёл", "Калуга", "Курск");
        graph.addEdge("Тула", "Липецк");

        return graph;
    }
}
