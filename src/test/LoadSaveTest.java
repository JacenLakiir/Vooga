/*
 * @author Siyang Chen
 */

package test;

import java.util.LinkedList;
import com.google.gson.Gson;


public class LoadSaveTest
{
    public static void main (String[] args)
    {
        new LoadSaveTest().run();
    }


    void run ()
    {
        Node a = new Node("a"), b = new Node("b"), c = new Node("c");
        a.neighbors.add(b);
        a.neighbors.add(c);
        b.neighbors.add(c);

        Graph g = new Graph();
        g.nodes.add(a);
        g.nodes.add(b);
        g.nodes.add(c);

        Gson gson = new Gson();
        String serialized = gson.toJson(g);
        System.out.println(serialized);

        Graph h = gson.fromJson(serialized, Graph.class);
        System.out.println(h);
        h.nodes.get(2).name = "d";
        System.out.println(h);
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
