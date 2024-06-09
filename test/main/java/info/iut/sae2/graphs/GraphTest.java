/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package main.java.info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static main.java.info.iut.sae2.graphs.GraphLoader.loadFromFile;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hretail
 */
public class GraphTest {

    public GraphTest() {
    }

    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;
    private Node node5;
    private Node node6;
    private Node node7;
    private Graph g;
    private Edge e15;
    private Edge e12;
    private Edge e16;
    private Edge e27;
    private Edge e23;
    private Edge e34;
    private Edge e45;
    private Edge e67;

    @Before
    public void setUp() {
        g = new Graph();
        node1 = new Node(new Coord(0, 0));
        node2 = new Node(new Coord(1, 2));
        node3 = new Node(new Coord(4, 4));
        node4 = new Node(new Coord(2, 2));
        node5 = new Node(new Coord(5, 3));
        node6 = new Node(new Coord(0, 5));
        node7 = new Node(new Coord(1, 4));

        e15 = new Edge(node1, node5);
        e12 = new Edge(node1, node2);
        e16 = new Edge(node1, node6);
        e27 = new Edge(node2, node7);
        e23 = new Edge(node2, node3);
        e34 = new Edge(node3, node4);
        e45 = new Edge(node4, node5);
        e67 = new Edge(node6, node7);

    }

    @Test
    public void testAddNode_Node() {
        g.addNode(node1);
        g.addNode(node2);
        assertEquals(2, g.getNodes().size());
        assertTrue(g.getNodes().contains(node1));
        assertTrue(g.getNodes().contains(node2));
    }

    @Test
    public void testAddEdge_Edge() {
        g.addEdge(e15);
        assertEquals(1, g.getEdges().size());
        assertTrue(g.getEdges().contains(e15));
    }

    @Test
    public void testAddEdge_Node_Node() {
        g.addEdge(node1, node2);
        assertEquals(1, g.getEdges().size());
        assertTrue(g.getEdges().get(0).getSource().equals(node1));
        assertTrue(g.getEdges().get(0).getTarget().equals(node2));
    }

    @Test
    public void testNumberOfNodes() {
        g.addNode(node1);
        g.addNode(node2);
        int result = g.numberOfNodes();
        assertEquals(2, result);
    }

    @Test
    public void testNumberOfEdges() {
        g.addEdge(e12);
        g.addEdge(e15);
        int result = g.numberOfEdges();
        assertEquals(2, result);
    }

    @Test
    public void testGetNeighbors() {
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addEdge(e12);
        g.addEdge(e23);
        ArrayList<Node> neighbors = g.getNeighbors(node1);
        assertTrue(neighbors.contains(node2));
        assertFalse(neighbors.contains(node3));
        assertTrue(g.getNeighbors(node2).contains(node1));
        assertTrue(g.getNeighbors(node2).contains(node3));
    }

    @Test
    public void testGetNodes() {
        g.addNode(node1);
        g.addNode(node2);
        ArrayList<Node> nodes = g.getNodes();
        assertEquals(2, nodes.size());
        assertTrue(nodes.contains(node1));
        assertTrue(nodes.contains(node2));
    }

    /**
     * Je précise que j'ai dû tester cette méthode un maximum parce que j'étais
     * persuadé que le problème venait d'elle. Ce n'était pas le cas au final !
     */
    @Test
    public void testGetEdges() {
        //ajouter deux arêtes et vérifier
        g.addEdge(e12);
        g.addEdge(e15);
        ArrayList<Edge> edges = g.getEdges();
        assertEquals(2, edges.size());
        assertTrue(edges.contains(e12));
        assertTrue(edges.contains(e15));

        //arête déjà ajoutée est ajoutée à nouveau
        g.addEdge(e12);
        edges = g.getEdges();
        assertTrue(edges.contains(e12));
        assertTrue(edges.contains(e15));

        //nouvelle arête ajoutée
        g.addEdge(e16);
        edges = g.getEdges();
        assertTrue(edges.contains(e12));
        assertTrue(edges.contains(e15));
        assertTrue(edges.contains(e16));

        //aucune arête
        Graph emptyGraph = new Graph();
        edges = emptyGraph.getEdges();
        assertEquals(0, edges.size());

        //tests suppls
        g.addEdge(e27);
        g.addEdge(e23);
        g.addEdge(e34);
        g.addEdge(e45);
        g.addEdge(e67);
        edges = g.getEdges();
        assertTrue(edges.contains(e12));
        assertTrue(edges.contains(e15));
        assertTrue(edges.contains(e16));
        assertTrue(edges.contains(e27));
        assertTrue(edges.contains(e23));
        assertTrue(edges.contains(e34));
        assertTrue(edges.contains(e45));
        assertTrue(edges.contains(e67));
    }

    @Test
    public void testSource() {
        Node source = g.source(e12);
        assertEquals(node1, source);
    }

    @Test
    public void testTarget() {
        Node target = g.target(e12);
        assertEquals(node2, target);
    }

    @Test
    public void testExistEdge() {
        g.addEdge(e12);
        assertTrue(g.existEdge(node1, node2, false));
        assertFalse(g.existEdge(node2, node3, false));
    }

    @Test
    public void testGetEdge() {
        g.addEdge(e12);
        Edge edge = g.getEdge(node1, node2, false);
        assertEquals(e12, edge);
    }

    @Test
    public void testGetNodePosition() {
        Coord position = g.getNodePosition(node1);
        assertEquals(new Coord(0, 0), position);
    }

    @Test
    public void testSetNodePosition() {
        Coord newPos = new Coord(5, 5);
        g.setNodePosition(node1, newPos);
        assertEquals(newPos, node1.getPosition());
    }

    @Test
    public void testSetEdgePosition() {
        ArrayList<Coord> newPositions = new ArrayList<>();
        newPositions.add(new Coord(1, 1));
        g.setEdgePosition(e12, newPositions);
        assertEquals(newPositions, e12.getPositions());
    }

    @Test
    public void testSetAllNodesPositions() {
        Coord newPos = new Coord(5, 5);
        g.addNode(node1);
        g.addNode(node2);
        g.setAllNodesPositions(newPos);
        assertEquals(newPos, node1.getPosition());
        assertEquals(newPos, node2.getPosition());
    }

    @Test
    public void testSetAllEdgesPositions() {
        ArrayList<Coord> newPositions = new ArrayList<>();
        newPositions.add(new Coord(1, 1));
        g.addEdge(e12);
        g.setAllEdgesPositions(newPositions);
        assertEquals(newPositions, e12.getPositions());
    }

    @Test
    public void testGetBoundingBox() {
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        ArrayList<Coord> boundingBox = g.getBoundingBox();
        assertEquals(2, boundingBox.size());
        assertEquals(new Coord(0, 0), boundingBox.get(0));
        assertEquals(new Coord(4, 4), boundingBox.get(1));
    }

    @Test
    public void testBundle() {
        System.out.println("bundle");
        Graph instance = new Graph();
        instance.bundle();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMinimumSpanningTree_singleNode() {
        // Teste le cas d'un graphe avec un seul noeuds
        Graph graph = new Graph();
        Node node = new Node(new Coord(1, 1));

        graph.addNode(node);

        Graph mst = graph.getMinimumSpanningTree();

        // Vérifie que l'arbre couvrant minimum contient exactement un noeuds et aucune arête
        assertEquals(1, mst.getNodes().size());
        assertTrue(mst.getNodes().contains(node));
        assertEquals(0, mst.getEdges().size());
    }

    @Test
    public void testGetMinimumSpanningTree_disconnectedNodes() {
        // Teste le cas de plusieurs noeuds déconnectés
        Graph graph = new Graph();
        Node node1 = new Node(new Coord(1, 1));
        Node node2 = new Node(new Coord(2, 1));
        Node node3 = new Node(new Coord(0, 4));
        graph.addNode(node1);
        graph.addNode(node2);

        Graph mst = graph.getMinimumSpanningTree();

        // Vérifie que l'arbre couvrant minimum contient exactement les noeuds ajoutés mais aucune arête
        assertEquals(2, mst.getNodes().size());
        assertTrue(mst.getNodes().contains(node1));
        assertTrue(mst.getNodes().contains(node2));
        assertEquals(0, mst.getEdges().size());
        
        // Propriété vérifiant que le nombre d'arêtes du MST est égale au nombre de noeuds du graphe moins le nombre de graphes déconnectés
        assertEquals(graph.getNodes().size()-2,mst.getEdges().size());
    }

    @Test
    public void testGetMinimumSpanningTree_simpleConnectedGraph() {
        // Teste un graphe simple avec trois noeuds connectés
        Graph graph = new Graph();
        Node node1 = new Node(new Coord(1, 1));
        Node node2 = new Node(new Coord(2, 1));
        Node node3 = new Node(new Coord(0, 4));
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        Edge edge1 = new Edge(node1, node2, 1);
        Edge edge2 = new Edge(node2, node3, 2);
        Edge edge3 = new Edge(node1, node3, 3);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);

        Graph mst = graph.getMinimumSpanningTree();

        // Vérifie que l'arbre couvrant minimum contient tous les noeuds et deux des trois arêtes
        assertEquals(3, mst.getNodes().size());
        assertTrue(mst.getNodes().contains(node1));
        assertTrue(mst.getNodes().contains(node2));
        assertTrue(mst.getNodes().contains(node3));
        assertEquals(2, mst.getEdges().size());
        assertTrue(mst.getEdges().contains(edge1));
        assertFalse(mst.getEdges().contains(edge3));
    }

    @Test
    public void testGetMinimumSpanningTree_complexGraph() {
        // Teste un graphe plus complexe avec cinq noeuds et plusieurs arêtes
        Graph graph = new Graph();
        Node node1 = new Node(new Coord(1, 1));
        Node node2 = new Node(new Coord(2, 1));
        Node node3 = new Node(new Coord(0, 4));
        Node node4 = new Node(new Coord(4, 3));
        Node node5 = new Node(new Coord(2, 5));

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node3);
        Edge edge3 = new Edge(node2, node3);
        Edge edge4 = new Edge(node2, node4);
        Edge edge5 = new Edge(node3, node5);
        Edge edge6 = new Edge(node4, node5);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);

        Graph mst = graph.getMinimumSpanningTree();

        // Vérifie que l'arbre couvrant minimum contient tous les noeuds et quatre arêtes
        assertEquals(5, mst.getNodes().size());
        assertEquals(4, mst.getEdges().size());
        List<Edge> mstEdges = mst.getEdges();
        assertTrue(mstEdges.contains(edge1));
        assertTrue(mstEdges.contains(edge4));
        assertTrue(mstEdges.contains(edge5));
    }
    
    

}
