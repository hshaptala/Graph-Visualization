package main.java.info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.Objects;

public class Node {

    private ArrayList<Edge> inEdges;
    private ArrayList<Edge> outEdges;
    private Coord position;
    private final ArrayList<Edge> edges;

    public Node() {
        this.edges = new ArrayList<>();
    }
    
    public Node(Coord position) {
        this.position = position;
        this.edges = new ArrayList<>();
        this.inEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
    }

    public Coord getPosition() {
        return position;
    }

    public void setPosition(Coord position) {
        if (position != null) {
            this.position = position;
        }
    }

    public ArrayList<Edge> getInEdges() {
        return inEdges;
    }

    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }

    public ArrayList<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public void addInEdge(Edge edge) {
        this.inEdges.add(edge);
    }

    public void addOutEdge(Edge edge) {
        this.outEdges.add(edge);
    }

    public void addEdge(Edge edge) {
        if (!edges.contains(edge) && edge != null) {
            edges.add(edge);
        }
    }

    @Override
    public boolean equals(Object o) { // TRY
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return this.position.equals(node.position);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.position);
        return hash;
    }

    @Override
    public String toString() {
        return "Node : X=" + this.position.getX() + " | Y=" + this.position.getY();
    }
}
