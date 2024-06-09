/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.info.iut.sae2.properties;

import main.java.info.iut.sae2.graphs.Coord;
import main.java.info.iut.sae2.graphs.Node;
import main.java.info.iut.sae2.graphs.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author romai
 */
public class LayoutProperty implements IProperty<Coord, ArrayList<Coord>> {

    final public static Coord DEFAULT_NODE_POS = new Coord();
    final public static ArrayList<Coord> DEFAULT_EDGE_POS = new ArrayList<>();

    Map<Node, Coord> nodesCoordinates = new HashMap<>();
    Map<Edge, ArrayList<Coord>> edgesCoordinates = new HashMap<>();

    @Override
    public Coord getNodeValue(Node n) {
        return nodesCoordinates.get(n);
    }

    @Override
    public ArrayList<Coord> getEdgeValue(Edge e) {
        return edgesCoordinates.get(e);
    }

    @Override
    public void setNodeValue(Node n, Coord val) {
        nodesCoordinates.put(n, val);
    }

    @Override
    public void setEdgeValue(Edge e, ArrayList<Coord> val) {
        edgesCoordinates.put(e, val);
    }

    @Override
    public void setAllNodesValues(Coord val) {
        for (Node n : nodesCoordinates.keySet()) {
            nodesCoordinates.put(n, val);
        }
    }

    @Override
    public void setAllEdgesValues(ArrayList<Coord> val) {
        for (Edge e : edgesCoordinates.keySet()) {
            edgesCoordinates.put(e, val);
        }
    }

    @Override
    public void delNode(Node n) {
        assert nodesCoordinates.containsKey(n);
        nodesCoordinates.remove(n);
    }

    @Override
    public void delEdge(Edge e) {
        assert edgesCoordinates.containsKey(e);
        edgesCoordinates.remove(e);
    }
}
