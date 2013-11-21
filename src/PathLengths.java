


import java.util.ArrayList;
import java.util.Comparator;

import rbtree.RBTree;
import rbtree.RBTreeVisitor;



/**
 * PathLengths visitor
 * @author Matt Capone
 * @version 11/7/2013
 * @param <T> the type of the rbtree
 */
public class PathLengths<T> implements RBTreeVisitor<T, ArrayList<Integer>> {
    
    @Override
    /**
     * visit the empty node
     * @param comp the comparator of the node
     * @param color the color of the node
     * @return ArrayList<Integer> the path legnths of the empty
     */
    public ArrayList<Integer> visitEmpty(Comparator<T> comp, String color) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0);
        return temp;
    }

    @Override
    /**
     * visit the node
     * @param comp the comparator of the tree
     * @param color the color of the node
     * @param T the data of the node
     * @param left the left of the node
     * @param right the right of the node
     * @return ArrayList<Integer> the path lengths
     */
    public ArrayList<Integer> visitNode(Comparator<T> comp, String color, 
            T data, RBTree<T> left, RBTree<T> right) {
        ArrayList<Integer> temp = left.accept(this);
        temp.addAll(right.accept(this));
        return oneForAll(temp);
    }

    /**
     * add 1 to add every element in the list
     * @param al the array list to add 1 to
     * @return ArrayList<Integer> the list with 1 added to everything
     */
    private ArrayList<Integer> oneForAll(ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i++) {
            al.set(i, al.get(i) + 1);
        }
        return al;
    }
}
