
import java.util.Comparator;

import rbtree.RBTree;
import rbtree.RBTreeVisitor;


/**
 * BlackHeight visitor
 * @author Matt Capone
 * @version 11/7/2013
 * @param <T> the type of the rbtree
 */
public class BlackHeight<T> implements RBTreeVisitor<T, Integer> {

    @Override
    /**
     * visit the empty node
     * @param comp the comparator of the node
     * @param color the color of the node
     * @return Integer the black height of the empty
     */
    public Integer visitEmpty(Comparator<T> comp, String color) {
        return 0;
    }

    @Override
    /**
     * visit the node
     * @param comp the comparator of the tree
     * @param color the color of the node
     * @param T the data of the node
     * @param left the left of the node
     * @param right the right of the node
     * @return Integer the black height of the node
     */
    public Integer visitNode(Comparator<T> comp, String color,
            T data, RBTree<T> left, RBTree<T> right) {
        int max = Math.max(left.accept(this), right.accept(this));
        return (color.equals("BLACK")) ? 1 + max : max;
    }
    
}
