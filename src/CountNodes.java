
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import rbtree.RBTree;
import rbtree.RBTreeVisitor;

/**
 * CountNodes visitor
 * @author Matt Capone
 * @version 11/7/2013
 * @param <T> the type of the rbtree
 */
public class CountNodes<T> implements RBTreeVisitor<T, ArrayList<Integer>> {
    
    /**
     * visit the empty node
     * @param comp the comparator of the node
     * @param color the color of the node
     * @return ArrayList<Integer> the node cout of the empty
     */
    public ArrayList<Integer> visitEmpty(Comparator<T> comp, String color) {
        return new ArrayList<Integer>(Arrays.asList(0, 0, 0));
    }
    
    /**
     * visit the node
     * @param comp the comparator of the tree
     * @param color the color of the node
     * @param data the data of the node
     * @param left the left of the node
     * @param right the right of the node
     * @return ArrayList<Integer> the node count
     */
    public ArrayList<Integer> visitNode(Comparator<T> comp, String color,
            T data, RBTree<T> left, RBTree<T> right) {
        ArrayList<Integer> temp = new ArrayList<Integer>(
                Arrays.asList(0, 0, 0));
        
        temp.set(0, temp.get(0) + 1);
        if (color.equals("BLACK")) {
            temp.set(1, temp.get(1) + 1);
        }
        else {
            temp.set(2, temp.get(2) + 1);
        }
        return addTogether(temp, left.accept(this), right.accept(this));
    }
    
    /**
     * adds the first value of the first array list to the first value
     * of the second array list and third array list, and the same for the
     * second and third values
     * @param a1 the first array
     * @param a2 the second array
     * @param a3 the third array
     * @return ArrayList<Integer> the three combined lists
     */
    private ArrayList<Integer> addTogether(ArrayList<Integer> a1,
            ArrayList<Integer> a2, ArrayList<Integer> a3) {
        for (int i = 0; i < a1.size(); i++) {
            a1.set(i, a1.get(i) + a2.get(i) + a3.get(i));
        }
        return a1;
    }
}