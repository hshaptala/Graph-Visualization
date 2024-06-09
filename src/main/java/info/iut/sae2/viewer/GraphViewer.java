/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main.java.info.iut.sae2.viewer;

import main.java.info.iut.sae2.graphs.GraphLoader;
import main.java.info.iut.sae2.graphs.IGraph;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class GraphViewer extends JFrame {

    final int FRAME_WIDTH = 800;
    final int FRAME_HEIGHT = 800;

    final int SLIDER_WIDTH = 100;
    final int BUTTON_HEIGHT = 40;

    IGraph currentGraph;
    IGraph graph;
    IGraph spanningTree;

    GraphCanvas gc;

    public GraphViewer() {
        super("A simplistic graph viewer!");
        graph = null;
        currentGraph = null;
        spanningTree = null;
        buildUI();
    }

    private void buildUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel filenameLabel = new JLabel("Graph Filename:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 20, 0, 0);
        contentPane.add(filenameLabel, c);

        String filenames[] = {"Hollywood", "US Migration", "UBC CS website",
            "Air traffic 2000", "Example", "Test CJ", "Test EB", "Test EBB", "MyGraph"};
        JComboBox fileChooser = new JComboBox(filenames);
        fileChooser.setSelectedIndex(-1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 3, 0, 0);
        contentPane.add(fileChooser, c);

        JLabel infos = new JLabel(String.format("%d Nodes and %d Edges", 0, 0));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1.;
        c.insets = new Insets(0, 3, 0, 0);
        contentPane.add(infos, c);

        JButton treeButton = new JButton();
        treeButton.setText("Spanning Tree");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        contentPane.add(treeButton, c);

        JButton graphButton = new JButton();
        graphButton.setText("Original Graph");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        contentPane.add(graphButton, c);

        JButton bundleButton = new JButton();
        bundleButton.setText("Bundle");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        contentPane.add(bundleButton, c);

        JLabel nodeLabel = new JLabel("Node Size");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(0, 10, 0, 0);
        contentPane.add(nodeLabel, c);

        JSlider nodeSize = new JSlider(JSlider.HORIZONTAL, 1, 100, 5);
        nodeSize.setMinimumSize(new Dimension(SLIDER_WIDTH, BUTTON_HEIGHT));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 1;
        contentPane.add(nodeSize, c);

        JLabel edgeLabel = new JLabel("Edge transparency");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 1;
        contentPane.add(edgeLabel, c);

        JSlider edgeTransp = new JSlider(JSlider.HORIZONTAL, 0, 255, 60);
        edgeTransp.setMinimumSize(new Dimension(SLIDER_WIDTH, BUTTON_HEIGHT));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 1;
        contentPane.add(edgeTransp, c);

        JCheckBox smooth = new JCheckBox("Smooth", true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 7;
        c.gridy = 1;
        contentPane.add(smooth, c);

        gc = new GraphCanvas();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.;
        c.weighty = 1.;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 0, 0);
        contentPane.add(gc, c);

        fileChooser.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) e.getItem();
                loadGraph(item);
                infos.setText(String.format("%d Nodes and %d Edges", currentGraph.numberOfNodes(), currentGraph.numberOfEdges()));
            }
        });

        treeButton.addActionListener((ActionEvent e) -> {
            spanningTree();
        });
        graphButton.addActionListener((ActionEvent e) -> {
            clearGraph();
        });

        bundleButton.addActionListener((ActionEvent e) -> {
            bundleGraph();
        });

        smooth.addItemListener((ItemEvent e) -> {
            gc.setSmooth(e.getStateChange() == ItemEvent.SELECTED);
            gc.repaint();
        });

        edgeTransp.addChangeListener((ChangeEvent e) -> {
            gc.setEdgeTransparency(edgeTransp.getValue());
            gc.repaint();
        });

        nodeSize.addChangeListener((ChangeEvent e) -> {
            gc.setRadius(nodeSize.getValue());
            gc.repaint();
        });

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                updateGraphView();
            }
        });
    }

    public void bundleGraph() {
        if (graph == null) {
            return;
        }
        currentGraph = graph;
        graph.bundle();
        updateGraphView();
    }

    public void spanningTree() {
        if (graph == null) {
            return;
        }
        spanningTree = graph.getMinimumSpanningTree();
        currentGraph = spanningTree;
        updateGraphView();
    }

    public void clearGraph() {
        if (graph == null) {
            return;
        }
        currentGraph = graph;
        currentGraph.setAllEdgesPositions(new ArrayList<>());
        updateGraphView();
    }

    public void loadGraph(String filename) {
        spanningTree = null;
        if (filename.equals("Hollywood")) {
            graph = GraphLoader.loadFromFile("hollywood_nodes.csv", "hollywood_edges.csv");
        } else if (filename.equals("US Migration")) {
            graph = GraphLoader.loadFromFile("us_migrations_nodes.csv", "us_migrations_edges.csv");
        } else if (filename.equals("UBC CS website")) {
            graph = GraphLoader.loadFromFile("ubc2K_nodes.csv", "ubc2K_edges.csv");
        } else if (filename.equals("Air Traffic")) {
            graph = GraphLoader.loadFromFile("air_traffic_nodes.csv", "air_traffic_edges.csv");
        } else if (filename.equals("Example")) {
            graph = GraphLoader.loadFromFile("example_nodes.csv", "example_edges.csv");

        } else if (filename.equals("Test CJ")) {
            graph = GraphLoader.loadFromFile("testCJ_nodes.csv", "testCJ_edges.csv");

        } else if (filename.equals("Test EB")) {
            graph = GraphLoader.loadFromFile("testEB_nodes.csv", "testEB_edges.csv");

        } else if (filename.equals("Test EBB")) {
            graph = GraphLoader.loadFromFile("testEBB_nodes.csv", "testEBB_edges.csv");
        } else if (filename.equals("MyGraph")) {
            graph = GraphLoader.loadFromFile("myGraph_nodes.csv", "myGraph_edges.csv");
        }

        currentGraph = graph;

        updateGraphView();
    }

    private void updateGraphView() {
        gc.setGraph(currentGraph);
        gc.repaint();
    }
}
