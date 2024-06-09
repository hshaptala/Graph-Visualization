/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.info.iut.sae2.graphs;

import java.util.ArrayList;

/**
 *
 * @author rbourqui
 */
public interface IGraph {

    /**
     * Adds and returnq a node to the graph
     *
     * @return the added node
     */
    public Node addNode();

    /**
     * Adds a given node to the graph and returns it
     *
     * @param n the node to add in the graph
     * @return the added node
     */
    public Node addNode(Node n);

    /**
     * Adds a given edge and returns an edge to the graph
     *
     * @param e the edge to add
     * @return the added edge
     */
    public Edge addEdge(Edge e);

    /**
     * Adds an edge between the two given nodes and returns it
     *
     * @param src source of the edge to add &param tgt target of the edge to add
     * @param tgt
     * @return the added edge
     */
    public Edge addEdge(Node src, Node tgt);

    /**
     * Deletes a given node from the graph
     *
     * @param n the node to be deleted
     */
    public void delNode(Node n);

    /**
     * Deletes a given edge from the graph
     *
     * @param e the edge to be deleted
     */
    public void delEdge(Edge e);

    /**
     * Returns the number of nodes in the graph
     *
     * @return the number of nodes
     */
    public int numberOfNodes();

    /**
     * Returns the number of edges in the graph
     *
     * @return the number of edges
     */
    public int numberOfEdges();

    /**
     * Returns the neighbors of a given node in the graph
     *
     * @param n the node whose neighborhood is queried
     * @return the neighbors of the node
     */
    public ArrayList<Node> getNeighbors(Node n);

    /**
     * Returns the sucessors of a given node in the graph
     *
     * @param n the node whose successors are queried
     * @return the successors of the node
     */
    public ArrayList<Node> getSuccesors(Node n);

    /**
     * Returns the predecessors of a given node in the graph
     *
     * @param n the node whose predecessors are queried
     * @return the predecessors of the node
     */
    public ArrayList<Node> getPredecessors(Node n);

    /**
     * Returns the indicent edges of a given node
     *
     * @param n the node whose incident edges are queried
     * @return the incident edges of the node
     */
    public ArrayList<Edge> getInOutEdges(Node n);

    /**
     * Returns the in-going edges of a given node in the graph
     *
     * @param n the node whose in-going are queried
     * @return the in-going edges of the node
     */
    public ArrayList<Edge> getInEdges(Node n);

    /**
     * Returns the out-going edges of a given node in the graph
     *
     * @param n the node whose out-going are queried
     * @return the out-going edges of the node
     */
    public ArrayList<Edge> getOutEdges(Node n);

    /**
     * Returns the nodes of the graph
     *
     * @return the nodes of the graph
     */
    public ArrayList<Node> getNodes();

    /**
     * Returns the edges of the graph
     *
     * @return the edges of the graph
     */
    public ArrayList<Edge> getEdges();

    /**
     * Returns the source node of a given edge in the graph
     *
     * @param e the edge whose source is queried
     * @return the source of the edge
     */
    public Node source(Edge e);

    /**
     * Returns the target node of a given edge in the graph
     *
     * @param e the edge whose target is queried
     * @return the target of the edge
     */
    public Node target(Edge e);

    /**
     * Returns the in-degree (number of predecessors) of a given node in the
     * graph
     *
     * @param n the node whose in-degree is queried
     * @return the in-degree of the node
     */
    public int inDegree(Node n);

    /**
     * Returns the out-degree (number of successors) of a given node in the
     * graph
     *
     * @param n the node whose out-degree is queried
     * @return the out-degree of the node
     */
    public int outDegree(Node n);

    /**
     * Returns the degree (number of neighbors) of a given node in the graph
     *
     * @param n the node whose degree is queried
     * @return the degree of the node
     */
    public int degree(Node n);

    /**
     * Returns true if there exists an edge between the two given nodes,
     * considering the orientation of the edges or not.
     *
     * @param src the source node of the putative edge
     * @param tgt the target node of the putative edge
     * @param oriented if true, the function consider the edge as directed
     * (arc), and undirected otherwise
     * @return true if the edge exists, false otherwise
     */
    public boolean existEdge(Node src, Node tgt, boolean oriented);

    /**
     * Returns an existing edge between the source and target nodes while
     * considering the orientation of not.
     *
     * @param src the source node of the putative edge
     * @param tgt the target node of the putative edge
     * @param oriented if true, the function consider the edge as directed
     * (arc), and undirected otherwise
     * @return the edge if the edge exists, null otherwise
     */
    public Edge getEdge(Node src, Node tgt, boolean oriented);

    /**
     * Returns the coordinates of a given node in the graph
     *
     * @param n the node of interest
     * @return the coordinates of the node
     */
    public Coord getNodePosition(Node n);

    /**
     * Returns the coordinates of a given edge (ie the coordinates of its bends)
     * in the graph
     *
     * @param e the edge of interest
     * @return the coordinates of the bends of the edge
     */
    public ArrayList<Coord> getEdgePosition(Edge e);

    /**
     * Set the coordinates of a given node in the graph
     *
     * @param n the node of interest
     * @param c new coordinates of the node
     */
    public void setNodePosition(Node n, Coord c);

    /**
     * Set the coordinates of a given edge (ie the coordinates of its bends) in
     * the graph
     *
     * @param e the edge of interest
     * @param bends the bends of the edge
     */
    public void setEdgePosition(Edge e, ArrayList<Coord> bends);

    /**
     * Set the coordinates of all nodes in the graph
     *
     * @param c new coordinates of the nodes
     */
    public void setAllNodesPositions(Coord c);

    /**
     * Set the coordinates of all edges (ie the coordinates of its bends) in the
     * graph
     *
     * @param bends the bends of the edges
     */
    public void setAllEdgesPositions(ArrayList<Coord> bends);

    /**
     * Returns the coordinates of the bouding box of the graph drawing. The
     * bouding box of a drawing of a graph is the minimum rectangle such that
     * all nodes and edges of the graph are inside the rectangle. Please remind
     * that the y-axis is oriented toward the bottom.
     *
     * @return an ArrayList containing the coordinates of the top-left corner of
     * the bonding box (at the first index in the ArrayList) and the coordinates
     * of the bottom-right corner of the bounding box (second index)
     *
     */
    public ArrayList<Coord> getBoundingBox();

    /**
     * Returns a minimum spanning tree computed with the Prim algorithm.
     *
     * @return the resulting spanning tree
     *
     */
    public Graph getMinimumSpanningTree();

    /**
     * Bundle the edges of the graph··
     */
    public void bundle();

}
