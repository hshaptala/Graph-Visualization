    package main.java.info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import main.java.info.iut.sae2.algorithms.EdgeBuilding;
import main.java.info.iut.sae2.algorithms.PrimAlgo;

public class Graph implements IGraph {

    private final ArrayList<Node> nodes;
    private final ArrayList<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public Node addNode() {
        Node node = new Node();
        nodes.add(node);
        return node;
    }

    @Override
    public Node addNode(Node n) {
        nodes.add(n);
        return n;
    }

    @Override
    public Edge addEdge(Edge e) {
        edges.add(e);
        return e;
    }

    @Override
    public Edge addEdge(Node src, Node tgt) {
        Edge edge = new Edge(src, tgt);
        edges.add(edge);
        return edge;
    }

    @Override
    public void delNode(Node n) {

        for (Edge edge : new ArrayList<>(n.getInEdges())) {
            delEdge(edge);
        }
        for (Edge edge : new ArrayList<>(n.getOutEdges())) {
            delEdge(edge);
        }

        for (Edge edge : new ArrayList<>(n.getEdges())) {
            delEdge(edge);
        }
        nodes.remove(n);
    }

    @Override
    public void delEdge(Edge e) {
        e.getSource().getOutEdges().remove(e);
        e.getTarget().getInEdges().remove(e);

        e.getSource().getEdges().remove(e);
        e.getTarget().getEdges().remove(e);
        edges.remove(e);
    }

    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    @Override
    public int numberOfEdges() {
        return edges.size();
    }

    @Override
    public ArrayList<Node> getNeighbors(Node n) {
        ArrayList<Node> neighbors = new ArrayList<Node>();
        for (Edge e : edges) {
            if (e.getSource().equals(n)) {
                neighbors.add(e.getTarget());
            } else if (e.getTarget().equals(n)) {
                neighbors.add(e.getSource());
            }
        }

        return neighbors;
    }

    @Override
    public ArrayList<Node> getSuccesors(Node n) {
        ArrayList<Node> successors = new ArrayList<>();
        for (Edge edge : edges) {
            successors.add(edge.getTarget());
        }
        return successors;
    }

    @Override
    public ArrayList<Node> getPredecessors(Node n) {
        ArrayList<Node> predecessors = new ArrayList<>();
        for (Edge edge : n.getEdges()) {
            if (edge.getTarget() == n) {
                predecessors.add(edge.getSource());
            }
        }
        return predecessors;
    }

    @Override
    public ArrayList<Edge> getInOutEdges(Node n) {

        ArrayList<Edge> inOutEdges = new ArrayList<>(n.getInEdges());
        inOutEdges.addAll(n.getOutEdges());
        return inOutEdges;
    }

    @Override
    public ArrayList<Edge> getInEdges(Node n) {
        return n.getInEdges();
    }

    @Override
    public ArrayList<Edge> getOutEdges(Node n) {
        return n.getOutEdges();
    }

    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    @Override
    public Node source(Edge e) {
        return e.getSource();
    }

    @Override
    public Node target(Edge e) {
        return e.getTarget();
    }

    @Override
    public int inDegree(Node n) {
        return n.getInEdges().size();
    }

    @Override
    public int outDegree(Node n) {
        return n.getOutEdges().size();
    }

    @Override
    public int degree(Node n) {
        return getNeighbors(n).size();
    }

    @Override
    public boolean existEdge(Node src, Node tgt, boolean oriented) {
        for (Edge edge : src.getOutEdges()) {
            if (edge.getTarget().equals(tgt) && edge.getSource().equals(src)) {
                return true;
            }
        }
        boolean exists = false;
        if (!oriented) {
            for (Edge edge : edges) {
                if (edge.getTarget().equals(tgt) && edge.getSource().equals(src)) {
                    exists = true;
                }
            }
        }
        return exists;
    }

    @Override
    public Edge getEdge(Node src, Node tgt, boolean oriented) {
        if (oriented) {
            for (Edge edge : src.getOutEdges()) {
                if (edge.getTarget().equals(tgt)) {
                    return edge;
                }
            }
        } else {
            for (Edge edge : edges) {
                if (edge.getSource().equals(src) && edge.getTarget().equals(tgt)) {
                    return edge;
                }
            }
        }
        return null;

    }

    @Override
    public Coord getNodePosition(Node n) {
        return n.getPosition();
    }

    @Override
    public ArrayList<Coord> getEdgePosition(Edge e) {
        ArrayList<Coord> coord = new ArrayList<>();
        coord.add(e.getSource().getPosition());
        if (e.getPositions() != null) {
            for (Coord coo : e.getPositions()) {
                coord.add(coo);
            }
        }
        coord.add(e.getTarget().getPosition());
        return coord;
    }

    @Override
    public void setNodePosition(Node n, Coord c) {
        n.setPosition(c);
    }

    @Override
    public void setEdgePosition(Edge edge, ArrayList<Coord> positions) {
        edge.setPositions(positions);
    }

    @Override
    public void setAllNodesPositions(Coord c) {
        for (Node node : nodes) {
            node.setPosition(c);
        }
    }

    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
        for (Edge edge : edges) {
            edge.setPositions(bends);
        }
    }

    public Node getNodeFromPosition(Coord position) {
        for (Node node : nodes) {
            if (node.getPosition().equals(position)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Coord> getBoundingBox() {
        if (nodes.isEmpty()) {
            return new ArrayList<>();
        }

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Node node : nodes) {
            Coord pos = node.getPosition();
            if (pos.getX() < minX) {
                minX = pos.getX();
            }
            if (pos.getY() < minY) {
                minY = pos.getY();
            }
            if (pos.getX() > maxX) {
                maxX = pos.getX();
            }
            if (pos.getY() > maxY) {
                maxY = pos.getY();
            }
        }

        ArrayList<Coord> boundingBox = new ArrayList<>();
        boundingBox.add(new Coord(minX, minY));
        boundingBox.add(new Coord(maxX, maxY));
        return boundingBox;
    }

    /**
     * This method computes the Minimum Spanning Tree (MST) of the current graph
     * using Prim's algorithm.
     *
     * @return the MST of the graph.
     */
    @Override
    public Graph getMinimumSpanningTree() {
        PrimAlgo primAlgo = new PrimAlgo();
        return primAlgo.primApply(this);
    }

    /**
     * This method adjusts the positions of the edges in the graph for better
     * visualization. It uses the Minimum Spanning Tree (MST) to determine
     * intermediate nodes for each edge, creating bends at these intermediate
     * nodes.
     */
    @Override
    public void bundle() {
        Graph minimumSpanningTree = getMinimumSpanningTree();
        for (Edge e : this.edges) {
            ArrayList<Node> nodesTaken = findPath(e, minimumSpanningTree);
            ArrayList<Coord> positions = getPositions(nodesTaken);
            setEdgePosition(e, positions);
        }
    }

    /**
     * Finds the path between the source and target nodes of the given edge in
     * the provided Minimum Spanning Tree (MST).
     *
     * @param edge the edge whose path is to be found.
     * @param minimumSpanningTree the MST used to find the path.
     * @return a list of nodes representing the path between the source and
     * target nodes of the edge.
     */
    private ArrayList<Node> findPath(Edge e, Graph minimumSpanningTree) {
        return EdgeBuilding.getNodesTaken(e.getSource(), e.getTarget(), minimumSpanningTree);
    }

    /**
     * Extracts the intermediate positions (bends) from the given path of nodes.
     *
     * @param nodesTaken the path of nodes from which bends are to be extracted.
     * @return a list of coordinates representing the bends in the path.
     */
    private ArrayList<Coord> getPositions(ArrayList<Node> nodesTaken) {
        ArrayList<Coord> positions = new ArrayList<>();
        if (nodesTaken != null && nodesTaken.size() > 2) {
            for (int i = 1; i < nodesTaken.size() - 1; i++) {
                positions.add(nodesTaken.get(i).getPosition());
            }
        }
        return positions;
    }

}
