package main.java.info.iut.sae2.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import main.java.info.iut.sae2.graphs.Coord;
import main.java.info.iut.sae2.graphs.Edge;
import main.java.info.iut.sae2.graphs.Graph;
import main.java.info.iut.sae2.graphs.Node;

/**
 *
 * @author hretail
 */
public class EdgeBuilding {

    /**
     * Finds the path of nodes taken from the source node to the destination
     * node in the given Minimum Spanning Tree (MST) using Breadth-First Search
     * (BFS).
     *
     * @param source the starting node of the path.
     * @param destination the target node of the path.
     * @param mst the graph representing the Minimum Spanning Tree (MST).
     * @return an ArrayList of nodes representing the path from the source to
     * the destination, or null if no path is found.
     */
    public static ArrayList<Node> getNodesTaken(Node source, Node destination, Graph mst) {
        // Init
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> treated = new HashSet<>();
        HashMap<Node, Node> route = new HashMap<>();
        boolean found = false;
        treated.add(source);
        queue.add(source);
        route.put(source, null);

        // Loop
        while (!queue.isEmpty() && !found) {
            Node current = queue.poll();
            if (current.equals(destination)) {
                found = true;
            } else {
                for (Node neighbor : mst.getNeighbors(current)) {
                    if (!treated.contains(neighbor)) {
                        route.put(neighbor, current);
                        queue.add(neighbor);
                        treated.add(neighbor);
                    }
                }
            }

        }
        if (found) {
            return buildPath(destination, route); // updates route
        } else {
            return null;
        }
    }

    /**
     * Helper method to build the path from source to destination using the
     * parents map.
     *
     * @param destination the destination node
     * @param routeMap the map containing parent references for each node
     * @return the path from source to destination as an ArrayList of Nodes
     */
    private static ArrayList<Node> buildPath(Node destination, HashMap<Node, Node> routeMap) {
        Stack<Node> stack = new Stack<>();
        for (Node ns = destination; ns != null; ns = routeMap.get(ns)) {
            stack.push(ns);
        }

        ArrayList<Node> route = new ArrayList<>();
        while (!stack.isEmpty()) {
            route.add(stack.pop());
        }

        return route;
    }

    /**
     * #########################################################
     *
     * RECURSIVE CODE DOWN THERE. NOT USED, feel free to ignore.
     *
     * #########################################################
     */
    /**
     * A partir du MinimumSpanningTree, parcourir pour chaque arrête, le plus
     * court chemin entre ses deux extrémités.
     *
     * @param mainGraph The default
     */
    public void buildRecursive(Graph mainGraph) {
        Node source;
        Node destination;
        Stack<Node> nodesTaken = new Stack();
        HashSet<Node> visited = new HashSet();
        Stack<Node> nodesTakenRecursive = new Stack();
        ArrayList<Coord> positions;
        Node origin;

        Graph minimumSpanningTree = mainGraph.getMinimumSpanningTree(); // OK

        for (Edge e : mainGraph.getEdges()) {
            source = e.getSource();
            destination = e.getTarget();
            origin = e.getSource();

            positions = new ArrayList();

            nodesTakenRecursive = getNodesTakenRecursive(nodesTaken, visited, minimumSpanningTree, source, destination, origin);

            for (Node n : nodesTakenRecursive) {
                positions.add(n.getPosition());
            }

            e.setPositions(positions);
        }

        // ok
    }

    private Stack<Node> getNodesTakenRecursive(Stack<Node> nodesTaken,
            HashSet<Node> visited, Graph minimumSpanningTree, Node source,
            Node destination, Node origin) {
        ArrayList<Node> neighboors = minimumSpanningTree.getNeighbors(source);
        for (Node n : neighboors) {
            if (source.equals(origin)) {
                visited.add(n);
                if (n.equals(destination)) {
                    nodesTaken.push(source);
                    return nodesTaken;
                } else {
                    source = n;
                    return getNodesTakenRecursive(nodesTaken, visited, minimumSpanningTree,
                            source, destination, origin);
                }
            } else if (!n.equals(source) && !visited.contains(n)) {
                visited.add(n);
                if (n.equals(destination)) {
                    nodesTaken.push(source);
                    return nodesTaken;
                } else if (isDeadEnd(visited, minimumSpanningTree, source)) {
                    return getNodesTakenRecursive(nodesTaken, visited,
                            minimumSpanningTree, nodesTaken.pop(), source, origin);
                } else {
                    nodesTaken.push(n);
                    source = n;
                    return getNodesTakenRecursive(nodesTaken, visited, minimumSpanningTree,
                            source, destination, origin);
                }
            }
        }

        return nodesTaken;
    }

    /**
     * Used for the recursion method.
     *
     * @param visited
     * @param minimumSpanningTree
     * @param source
     * @return
     */
    private boolean isDeadEnd(HashSet<Node> visited, Graph minimumSpanningTree,
            Node source) {
        boolean isBadEnding;
        int neighborsLeftToVisit = minimumSpanningTree.getNeighbors(source).size();

        for (Node n : minimumSpanningTree.getNeighbors(source)) {
            if (visited.contains(n)) {
                neighborsLeftToVisit -= 1;
            }
        }

        isBadEnding = neighborsLeftToVisit > 0;

        return isBadEnding;
    }

}
