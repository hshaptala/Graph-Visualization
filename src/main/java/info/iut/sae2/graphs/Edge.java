package main.java.info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Edge {

    private double cost;
    private Node source;
    private Node target;
    private ArrayList<Coord> positions;

    public Edge(Edge e) {
        this.source = e.source;
        this.target = e.target;

    }

    public Edge(Node src, Node dest) {
        this.source = src;
        this.target = dest;
    }

    public Edge(Node src, Node dest, int cost) {
        this.source = src;
        this.target = dest;
        this.cost = cost;
    }

    public Node getSource() {
        return this.source;
    }

    public Node getTarget() {
        return this.target;
    }

    public ArrayList<Coord> getPositions() {
        return this.positions;
    }

    public void setPositions(ArrayList<Coord> coords) {
        this.positions = new ArrayList<Coord>();
        this.positions.addAll(coords);
    }

    public void setCost(double c) {
        this.cost = c;
    }

    public double getCost() {
        return this.cost;
    }

    public void setSource(Node n) {
        this.source = n;
    }

    public void setTarget(Node n) {
        this.target = n;
    }

    /**
     * Not used.
     *
     * @param n The node.
     * @return The other node.
     */
    public Node getOtherNode(Node n) {
        if (!n.equals(source)) {
            return source;
        } else {
            return target;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return source.equals(edge.source) && target.equals(edge.target)
                || source.equals(edge.target) && target.equals(edge.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString() {
        return String.format("Edge(source=%s, target=%s)",
                source, target);
    }
}
