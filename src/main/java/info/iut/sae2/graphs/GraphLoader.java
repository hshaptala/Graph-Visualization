package main.java.info.iut.sae2.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class GraphLoader {

    public static Graph loadFromFile(String nodeFileName, String edgeFileName) {
        if (nodeFileName == null) {
            return null;
        }
        Graph g = new Graph();
        HashMap<Integer, Node> nodeMap = loadNodesFromFile(g, nodeFileName);

        if (edgeFileName != null) {
            loadEdgesFromFile(g, nodeMap, edgeFileName);
        }
        return g;
    }

    private static HashMap<Integer, Node> loadNodesFromFile(Graph g, String nodeFileName) {
        Path pathToFile = Paths.get(nodeFileName);
        HashMap<Integer, Node> nodeMap = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                if (attributes.length != 2) {
                    System.err.println("Error while loading nodes : " + attributes.length + " column(s)");
                    continue;
                }
                int id = Integer.parseInt(attributes[0]);
                String[] coords = attributes[1].split(" ");
                if (coords.length != 2) {
                    System.err.println("Error while loading nodes : coordinates have " + coords.length + " dimensions");
                    continue;
                }
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);

                Node n = g.addNode();
                g.setNodePosition(n, new Coord(x, y));
                nodeMap.put(id, n);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return nodeMap;
    }

    private static void loadEdgesFromFile(Graph g, HashMap<Integer, Node> nodeMap, String edgeFileName) {
        Path pathToFile = Paths.get(edgeFileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                if (attributes.length != 2) {
                    System.err.println("Error while loading edges : " + attributes.length + " column(s)");
                    line = br.readLine();
                    continue;
                }
                int srcId = Integer.parseInt(attributes[0]);
                int tgtId = Integer.parseInt(attributes[1]);

                Node sourceNode = nodeMap.get(srcId);
                Node targetNode = nodeMap.get(tgtId);

                if (sourceNode == null || targetNode == null) {
                    System.err.println("Error: One or both nodes not found for edge: " + line);
                    line = br.readLine();
                    continue;
                }

                Edge e = new Edge(sourceNode, targetNode);
                if (!g.getEdges().contains(e)) {
                    g.addEdge(e);
                }

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
