import java.util.Comparator;
import java.util.Iterator;

import rbtree.RBTree;
import rbtree.RBTreeVisitor;

/**
 * The class to represent a BTree
 * @author Matt Capone
 * @version 10/30/2013
 * @param <T> the type of this tree
 */
public class BTree<T> implements Iterable<T> {
    private RBTree<T> tree;
    private Comparator<T> comp;
    
    //Rep invarient
    //No red node has a red child
    //Every path to an empty node has the same number of blacks
    /** The repOK method 
     * @return true is rep is ok
     */
    public boolean repOK() {
        return tree.repOK();
    }
    
    /**
     * The constructor for the tree
     * @param comp the tree's comparator
     */
    private BTree(Comparator<T> comp) {
        this.comp = comp;
        tree = RBTree.binTree(comp);
    }
    
    /**
     * Factory method to generate 
     * an empty binary search tree
     * with the given <code>Comparator</code>
     *
     * @param comp the given <code>Comparator</code>
     * @param <T> the type of the tree
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    public static <T> BTree<T> binTree(Comparator<T> comp) {
        return new BTree<T>(comp);
    }

    /**
     * Modifies: 
     * this binary search tree by inserting the 
     * <code>String</code>s from the given 
     * <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     */
    public void build(Iterable<T> iter) {
        tree.build(iter);
    }
    
    /**
     * Accept the visitor
     * @param visitor the visitor to accept
     * @param <R> the return type of the visitor
     * @return R whatever the visitor outputs
     */
    public <R> R accept(RBTreeVisitor<T, R> visitor) {
        return tree.accept(visitor);
    }
    /**
     * Modifies: 
     * this binary search tree by inserting the 
     * first numStrings <code>String</code>s from 
     * the given <code>Iterable</code> collection
     * The tree will not have any duplicates 
     * - if an item to be added equals an item
     * that is already in the tree, it will not be added.
     *
     * @param iter the given <code>Iterable</code> collection
     * @param numStrings number of <code>String</code>s
     *        to iterate through and add to BTree
     *        If numStrings is negative or larger than the number of 
     *        <code>String</code>s in iter then all <code>String</code>s 
     *        in iter should be inserted into the tree 
     */
    public void build(Iterable<T> iter, int numStrings) {
        tree.build(iter, numStrings);
    }


    /**
     * Effect: 
     * Produces a <code>String</code> that consists of 
     * all <code>String</code>s in this tree 
     * separated by comma and a space, 
     * generated in the order defined by this tree's 
     * <code>Comparator</code>.
     * So for a tree with <code>Strings</code> 
     * "hello" "bye" and "aloha" 
     * ordered lexicographically, 
     * the result would be "aloha, bye, hello"
     * @return String this BTree as a string
     */
    public String toString() {
        return tree.toString();
    }


    /**
     * Effect: 
     * Produces false if o is not an instance of BTree.
     * Produces true if this tree and the given BTree 
     * contain the same <code>String</code>s and
     * are ordered by the same <code>Comparator</code>.
     * So if the first tree was built with Strings 
     * "hello" "bye" and "aloha" ordered
     * lexicographically,  and the second tree was built 
     * with <code>String</code>s "aloha" "hello" and "bye"  
     * and ordered lexicographically, 
     * the result would be true.
     *
     * @param o the object to compare with this
     * @return boolean true if the trees are equal
     */
    public boolean equals(Object o) {
        if (o instanceof BTree<?>) {
            BTree<?> treeArg = (BTree<?>) o;
            if (treeArg.comp.equals(comp)) {
                return this.toString().equals(treeArg.toString());
            } 
        }
        return false;
    }


    /**
     * Effect: 
     * Produces an integer that is compatible 
     * with the implemented  equals method 
     * and is likely to be different 
     * for objects that are not equal.
     * 
     * @return int the tree's hashcode
     */
    public int hashCode() {
        return tree.hashCode();
    }
    
    /** Create a new iterator for this tree
     * @return Iterator<String> a new iterator for the tree
     */
    public Iterator<T> iterator() {
        return tree.iterator();
    }
    
    /**
     * Effect:
     * Checks to see if this BTree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public boolean contains(T s) {
        return tree.contains(s);
    }
}