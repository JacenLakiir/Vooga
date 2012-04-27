/*
 * @author Siyang Chen
 */

package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;


@SuppressWarnings("serial")
public class LoadSaveTest implements Serializable
{
    public static void main (String[] args) throws Exception
    {
        new LoadSaveTest().run();
    }


    void run () throws Exception
    {
        Graph g = buildGraph();
        System.out.println(g);

        serialise(g);

        Graph h = (Graph) deserialise();
        System.out.println(h);
    }


    void serialise (Object o) throws Exception
    {
        ObjectOutputStream out =
            new ObjectOutputStream(new FileOutputStream("tmp"));
        out.writeObject(o);
        out.close();
    }


    Object deserialise () throws Exception
    {
        ObjectInputStream in =
            new ObjectInputStream(new FileInputStream("tmp"));
        Object ret = in.readObject();
        in.close();
        return ret;
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

    class Blah
    {
        Blah (int x)
        {}
    }

    class Node implements Serializable
    {
        LinkedList<Node> neighbors = new LinkedList<Node>();
        String name;
        Blah blah = new Blah(5);


        Node (String _name)
        {
            name = _name;
        }


        public String toString ()
        {
            return name;
        }


        private void writeObject (ObjectOutputStream stream) throws IOException
        {
            stream.defaultWriteObject();
            stream.writeObject("lol");
            System.out.println("left cookie");
            System.out.printf("writing node %s\n", name);
        }


        private void readObject (ObjectInputStream stream)
            throws ClassNotFoundException,
                IOException
        {
            stream.defaultReadObject();
            System.out.printf("read cookie: %s\n", (String) stream.readObject());
            System.out.printf("reading node %s\n", name);
        }
    }

    class Graph implements Serializable
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
