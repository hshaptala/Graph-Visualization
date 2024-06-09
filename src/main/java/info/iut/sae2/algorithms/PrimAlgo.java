package main.java.info.iut.sae2.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import main.java.info.iut.sae2.graphs.Edge;
import main.java.info.iut.sae2.graphs.Graph;
import main.java.info.iut.sae2.graphs.Node;

public class PrimAlgo {

    public Graph primApply(Graph G) {

        // Init
        ArrayList<Node> nodesTreated = new ArrayList<>();
        ArrayList<Edge> edgesToBuild = new ArrayList<>();
        Node startingNode = G.getNodes().get(0);
        nodesTreated.add(startingNode);

        // Initial loop : treats all existing graphs
        while (nodesTreated.size() < G.numberOfNodes() && startingNode != null) {

            Edge minimumCostEdge = getMinimumCostEdge(G, nodesTreated);

            if (minimumCostEdge != null) { // If we found a MCE

                updateListsContents(edgesToBuild, nodesTreated, minimumCostEdge);

            } else { // Current Graph exploration is over

                // Are there more Graphs to explore ? If not, startingNode = null
                startingNode = getNextStartingNode(G, nodesTreated);

            }
        }

        return buildUpMinimumSpanningTree(nodesTreated, edgesToBuild);
    }

    /**
     * Construit un arbre couvrant minimal à partir des noeuds et arêtes
     * traités.
     *
     * @param nodesTreated les noeuds traités dans le graphe
     * @param edgesToBuild les arêtes à inclure dans l'arbre couvrant minimal
     * @return un nouveau graphe représentant l'arbre couvrant minimal
     */
    private Graph buildUpMinimumSpanningTree(ArrayList<Node> nodesTreated, ArrayList<Edge> edgesToBuild) {
        Graph minimumSpanningTree = new Graph();
        for (Node n : nodesTreated) {
            minimumSpanningTree.addNode(n);
        }
        for (Edge e : edgesToBuild) {
            minimumSpanningTree.addEdge(e);
        }
        return minimumSpanningTree;
    }

    /**
     * Obtient le prochain noeud de départ pour continuer l'exploration du
     * graphe.
     *
     * @param G le graphe d'entrée
     * @param nodesTreated les noeuds déjà traités
     * @return le prochain noeud de départ ou null si tous les noeud sont
     * traités
     */
    private Node getNextStartingNode(Graph G, ArrayList<Node> nodesTreated) {
        Node startingNode = null;
        if (nodesTreated.size() < G.getNodes().size()) {
            ArrayList<Node> nodesLeft = new ArrayList<>(G.getNodes());
            nodesLeft.removeAll(nodesTreated);
            startingNode = nodesLeft.get(0);
            nodesTreated.add(startingNode);
        }
        return startingNode;
    }

    /**
     * Met à jour les listes de noeuds et d'arêtes traités avec l'arête de coût
     * minimal trouvée.
     *
     * @param edgesToBuild les arêtes à inclure dans l'arbre couvrant minimal
     * @param nodesTreated les noeuds traités dans le graphe
     * @param minimumCostEdge l'arête de coût minimal à ajouter
     */
    private void updateListsContents(ArrayList<Edge> edgesToBuild, ArrayList<Node> nodesTreated, Edge minimumCostEdge) {
        edgesToBuild.add(minimumCostEdge);
        if (nodesTreated.contains(minimumCostEdge.getSource())) {
            nodesTreated.add(minimumCostEdge.getTarget());
        } else {
            nodesTreated.add(minimumCostEdge.getSource());
        }
    }

    /**
     * Trouve l'arête de coût minimal reliant un noeuds traité à un nœud non
     * traité.
     *
     * @param G le graphe d'entrée
     * @param nodesTreated les noeuds déjà traités
     * @return l'arête de coût minimal trouvée ou null si aucune arête valide
     * n'est trouvée
     */
    private Edge getMinimumCostEdge(Graph G, ArrayList<Node> nodesTreated) {
        Edge minimumCostEdge = null;

        for (Edge e : G.getEdges()) {
            if (isCurrentEdgeValid(nodesTreated, e)) {
                if (minimumCostEdge == null) {
                    minimumCostEdge = e;
                } else {
                    double currentEdgeCost = e.getSource().getPosition().dist(e.getTarget().getPosition());
                    double minimumEdgeCost = minimumCostEdge.getSource().getPosition().dist(minimumCostEdge.getTarget().getPosition());

                    if (currentEdgeCost < minimumEdgeCost) {
                        minimumCostEdge = e;
                    }
                }
            }
        }
        return minimumCostEdge;
    }

    /**
     * Vérifie si une arête est valide pour l'inclusion dans l'arbre couvrant
     * minimal.
     *
     * @param nodesTreated les noeuds déjà traités
     * @param currentEdge l'arête à vérifier
     * @return true si l'arête est valide, false sinon
     */
    private boolean isCurrentEdgeValid(ArrayList<Node> nodesTreated, Edge currentEdge) {
        return (nodesTreated.contains(currentEdge.getSource()) && !nodesTreated.contains(currentEdge.getTarget()))
                || (!nodesTreated.contains(currentEdge.getSource()) && nodesTreated.contains(currentEdge.getTarget()));
    }

}
