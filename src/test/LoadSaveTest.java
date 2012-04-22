/*
 * @author Siyang Chen
 */

package test;

import java.io.*;
import java.util.*;
import org.yaml.snakeyaml.Yaml;


public class LoadSaveTest implements Serializable
{
    public static void main (String[] args)
    {
        new LoadSaveTest().run();
    }


    void run ()
    {
        Graph g = buildGraph();
        System.out.println(g);
        String s = serialise(g);
        System.out.println(s);
        Graph h = (Graph) deserialise(s);
        System.out.println(h);
        System.out.println(g);
    }


    String serialise (Graph g)
    {
        return new Yaml().dump(g);
    }


    Object deserialise (String s)
    {
        return new Yaml().load(s);
    }


    Graph buildGraph ()
    {
        Node a = new Node("a"), b = new Node("b"), c = new Node("c");
        a.neighbors.add(b);
        a.neighbors.add(c);
        b.neighbors.add(c);

        Graph g = new Graph();
        g.nodes.add(a);
        g.nodes.add(b);
        g.nodes.add(c);

        return g;
    }

    class Node
    {
        LinkedList<Node> neighbors = new LinkedList<Node>();
        String name;


        Node (String _name)
        {
            name = _name;
        }


        public String toString ()
        {
            return name;
        }
    }

    class Graph
    {
        LinkedList<Node> nodes = new LinkedList<Node>();


        public String toString ()
        {
            String ans = "[ ";
            for (Node node : nodes)
            {
                ans += node.toString() + ":" + node.neighbors.toString() + " ";
            }
            ans += "]";
            return ans;
        }
    }
}
