package java.lesson7;

public class Graph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Invalid label for vertex");
        }

        adjMat[startIndex][endIndex] = true;
        adjMat[endIndex][startIndex] = true;
    }

    public void addEdges(String startLabel, String secondLabel, String... others) {
        addEdge(startLabel, secondLabel);
        for (String other : others) {
            addEdge(startLabel, other);
        }
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return  i;
            }
        }
        return -1;
    }


    public int getVertexSize() {
        return vertexList.size();
    }

    public void display() {
        for (int i = 0; i < getVertexSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getVertexSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. Depth-first search, DFS
     *
     * @param startLabel
     */
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, stack);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            }
            else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    /**
     * англ. breadth-first search, BFS
     *
     * @param startLabel
     */
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, queue);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            }
            else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    public void findShortestWay(String startLabel, String endLabel) {
        List<Deque<Vertex>> ways = new ArrayList<>();
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);
        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException();
        }
        Vertex startVertex = vertexList.get(startIndex);
        Vertex endVertex = vertexList.get(endIndex);
        startVertex.setVisited(true);
        Deque<Vertex> way = new LinkedList<>();
        way.add(startVertex);
        ways.add(way);

        while (!endVertex.getVisited()) {
            int size = ways.size();
            for (int i = 0; i < size; i++) {
                Vertex current = ways.get(i).peekLast();
                Vertex near;
                if ((near = getNearUnvisitedVertex(current)) != null) {
                    ways.get(i).add(near);
                    near.setVisited(true);

                    while ((near = getNearUnvisitedVertex(current)) != null) {
                        Deque<Vertex> nextWay = new LinkedList<>(ways.get(i));
                        nextWay.removeLast();
                        near.setVisited(true);
                        nextWay.add(near);
                        ways.add(nextWay);
                    }
                }
            }
        }

        Optional<Deque<Vertex>> first = ways.stream().filter(w -> w.peekLast().equals(endVertex)).findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
            System.out.println("Number of steps = " + first.get().size());
        } else {
            System.out.println("Unreachable");
        }

    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getVertexSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).getVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        System.out.println(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }
    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        System.out.println(vertex);
        queue.add(vertex);
        vertex.setVisited(true);
    }
}
