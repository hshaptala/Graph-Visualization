/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.info.iut.sae2.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static java.util.Optional.empty;
import static main.java.info.iut.sae2.graphs.GraphLoader.loadFromFile;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author hretail
 */
public class GraphBundleTests {

    private Graph g;

    @Before
    public void setUp() {
        g = GraphLoader.loadFromFile("myGraph_nodes.csv", "myGraph_edges.csv");
    }

    @Test
    public void testBundle() {
        Graph mst = g.getMinimumSpanningTree();
        g.bundle();

        // each edge that has positions contains at least one bend
        // each edge can't have more positions than the amount of edges in the mst
        for (Edge e : g.getEdges()) {
            if (!e.getPositions().isEmpty()) {
                assertTrue(e.getPositions().size() >= 1);
                assertTrue(e.getPositions().size() <= mst.getEdges().size());
            }
        }
    }

    @Test
    public void testBundleSize() {
        
        // both graphs still has the same number of edges and nodes
        Graph gcopy = GraphLoader.loadFromFile("myGraph_nodes.csv", "myGraph_edges.csv");
        g.bundle();
        assertEquals(g.getNodes().size(), gcopy.getNodes().size());
        assertEquals(g.getEdges().size(),gcopy.getEdges().size());
    }
    
    @Test
    public void testBundleNumberOfBends() {
        g.bundle();
        int cpt = 0;
        for (Edge e: g.getEdges()) {
            if (!e.getPositions().isEmpty()) {
                cpt += 1;
            }
        }
        assertEquals(2, cpt);
    }
    
    @Test
    public void testBundlePositionsOfBends() {
        g.bundle();
        int cptOfPositionsStored = 0;
        for (Edge e: g.getEdges()) {
            if (!e.getPositions().isEmpty()) {
                for (Coord c : e.getPositions()) {
                    cptOfPositionsStored += 1;
                }
            }
        }
        assertEquals(5, cptOfPositionsStored);
    }
    
    @Test
    public void testBundlePositions() {
        g.bundle();
        for (Edge e: g.getEdges()) {
            
            if (!e.getPositions().isEmpty()) {
                System.out.println("<------- New Edge ------->");
                System.out.println(e);
                for (Coord c : e.getPositions()) {
                    System.out.println(c);
                }
                
            }
        }
        Node node1 = g.getNodeFromPosition(new Coord(0,0));
        Node node5 = g.getNodeFromPosition(new Coord(5,3));
        Node node6 = g.getNodeFromPosition(new Coord(0,5));
        Edge edge15 = g.getEdge(node1, node5, false);
        Edge edge16 = g.getEdge(node1, node6, false);
        //edge 1-5 has 3 positions to bend
        assertEquals(3, edge15.getPositions().size());
        Coord c2 = new Coord(1,2);
        Coord c4 = new Coord(2,2);
        Coord c3 = new Coord(4,4);
        assertTrue(edge15.getPositions().contains(c2));
        assertTrue(edge15.getPositions().contains(c4));
        assertTrue(edge15.getPositions().contains(c3));
        
        //edge 1-6 only has 2
        assertEquals(2, edge16.getPositions().size());
        Coord c7 = new Coord(1,4);
        assertTrue(edge16.getPositions().contains(c2));
        assertTrue(edge16.getPositions().contains(c7));
    }

}
