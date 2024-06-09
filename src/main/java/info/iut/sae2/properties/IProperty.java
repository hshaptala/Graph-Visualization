/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.info.iut.sae2.properties;

import main.java.info.iut.sae2.graphs.Edge;
import main.java.info.iut.sae2.graphs.Node;

/**
 *
 * @author romai
 */
public interface IProperty<T, U> {
    public T getNodeValue(Node n);
    public U getEdgeValue(Edge e);
    
    public void setNodeValue(Node n, T val);
    public void setEdgeValue(Edge e, U val);
    
    public void setAllNodesValues(T val);
    public void setAllEdgesValues(U val);
    
    public void delNode(Node n);
    public void delEdge(Edge e);
}
