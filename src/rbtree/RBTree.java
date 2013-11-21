package rbtree;


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The class to represent an RBTree
 * @author Matt Capone
 * @version 10/30/2013
 * @param <T>
 */
public class RBTree<T> implements Iterable<T> {
    /** the top of the tree */
    Node top;
    /** the comparator to organize the tree*/
    private Comparator<T> comp;
    /** true if any active iterators are running*/
    private int active;
    
    //Rep invarient
    //No red node has a red child
    //Every path to an empty node has the same number of blacks
    //Every data is a string
    /** The repOK method 
     * @return true is rep is ok
     */
    public boolean repOK() {
        return (top != null) ? top.repOK() : false;
    }
    
    /**
     * the RBTree constructor
     * @param comp the comparator to build the tree with
     */
    private RBTree(Comparator<T> comp) {
        this.comp = comp;
        this.active = 0; 
    }
    
    /**
     * the RBTree constructor
     * @param comp the comparator to build the tree with
     */
    private RBTree(Comparator<T> comp, Node top) {
        this.comp = comp;
        this.active = 0; 
        this.top = top;
    }
    
    /** Create a new iterator for this tree
     * @return Iterator<String> a new iterator for the tree
     */
    public Iterator<T> iterator() {
        return new BTreeIterator();     
    }
    
    /**
     * Factory method to generate 
     * an empty binary search tree
     * with the given <code>Comparator</code>
     * @param <T> the type of the RBTree
     * @param comp the given <code>Comparator</code>
     * @return new empty binary search tree that uses the 
     *         given <code>Comparator</code> for ordering
     */
    public static <T> RBTree<T> binTree(Comparator<T> comp) {
        return new RBTree<T>(comp);
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
     * @param numString number of <code>String</code>s
     *        to iterate through and add to BTree
     *        If numStrings is negative or larger than the number of 
     *        <code>String</code>s in iter then all <code>String</code>s 
     *        in iter should be inserted into the tree
     */
    public void build(Iterable<T> iter, int numString) {
        int i = 0;
        if (active >= 1) {
            throw new ConcurrentModificationException("Iterators running");
        }
        else {
            if (numString < 0) {
                build(iter);
            }
            else {
                Iterator<T> iterator = iter.iterator();
                while (iterator.hasNext() && i < numString) {
                    T s = iterator.next();
                    if (top == null) {
                        top = new Node(s, new Node(null));
                        top.red = false;
                    } 
                    else {
                        top.add(s);
                    }
                    i++;
                }
            }
            resetTop();
        }
    }
    
    /**
     * reset the top of the tree to the correct thing
     */
    void resetTop() {
        while (!top.parent.empty) {
            top = top.parent;
        }
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
     */
    public void build(Iterable<T> iter) {
        if (active >= 1) {
            throw new ConcurrentModificationException("Iterators running");
        }
        else {
            for (T s : iter) {
                if (top == null) {
                    top = new Node(s, new Node((Node)null));
                    top.red = false;
                }
                else {
                    top.add(s);
                }
            }
            resetTop();
        }
    }
    
    /**
     * Effect:
     * Checks to see if this BTree contains s
     * @param s <code>String</code> to look for in this
     * @return whether this contains s
     */
    public boolean contains(T s) {
        if (top != null) {
            return top.contains(s);
        }
        return false;
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
        if (top != null) {
            return top.toString();
        }
        return "";
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
        if (o instanceof RBTree<?>) {
            RBTree<?> treeArg = (RBTree<?>) o;
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
        if (top != null) {
            return top.hashCode();
        }
        return 0;
    }
    
    /**
     * accept the visitor into the class
     * @param visitor the visitor to accept
     * @param <R> the type that the visitor returns
     * @return R the return of the visitor
     */
    public <R> R accept(RBTreeVisitor<T, R> visitor) { 
        return (top == null) ? visitor.visitEmpty(comp, "BLACK") : 
            top.accept(visitor);
    }
    
    /**
     * The node class
     * @author Matt Capone
     * @version 10/30/2013
     */
    public class Node {
        /** the parent of this node */
        private Node parent;
        private Node left;
        private Node right;
        private T data;
        private boolean red;
        private boolean empty;
        
        /** empty constructor
         * @param parent the parent of the new Node
         */
        Node(Node parent) {
            red = false;
            empty = true;
            this.parent = parent;
        }
        
        /** constructor with given data
         * @param s the string data of the new node
         * @param parent the parent of the new node
         */
        Node(T s, Node parent) {
            this.parent = parent;
            this.data = s;
            red = true;
            left = new Node(this);
            right = new Node(this);
            empty = false;
        }
        
        /**
         * repOK method for the node
         * @return boolean true is rep is ok
         */
        public boolean repOK() {
            return true;
        }
        

        /**
         * accept a visitor into the class
         * @param visitor the visitor to accept
         * @param <R> the return value of the visitor
         * @return R the return of the visitor
         */
        public <R> R accept(RBTreeVisitor<T, R> visitor) {
            String color;
            if (red) {
                color = "RED";
            }
            else {
                color = "BLACK";
            }
            if (empty) {
                return visitor.visitEmpty(comp, color);
            }
            else {
                RBTree<T> newLeft = new RBTree<T>(comp, this.left);
                RBTree<T> newRight = new RBTree<T>(comp, this.right);
                return visitor.visitNode(comp, color, data, newLeft, newRight);
            }
        }
        
        /** turn this node into a string 
         * @return String this node as a string
         */
        public String toString() {
            if (left.empty && right.empty) {
                return this.data.toString();
            }
            else if (left.empty) {
                return this.data.toString() + ", " + this.right.toString();
            }
            else if (right.empty) {
                return this.left.toString() + ", " + this.data.toString();
            }
            else {
                return this.left.toString() + ", " + 
                        this.data.toString() + ", " + 
                        this.right.toString();
            }
        }
        
        /** this node's hashcode
         * @return int the hashcode of this node
         */
        public int hashCode() {
            if (left.empty && right.empty) {
                return this.data.hashCode();
            }
            else if (left.empty) {
                return this.data.hashCode() + this.right.hashCode(); 
            }
            else if (right.empty) {
                return this.data.hashCode() + this.left.hashCode();
            }
            else {
                return this.data.hashCode() + this.left.hashCode() + 
                        this.right.hashCode();
            }
        }
        
        /** does this node contain s?
         * @param s the string to check if this node contains
         * @return boolean true if the node contains s
         */
        boolean contains(T s) {
            if (left.empty && right.empty) {
                return this.data.equals(s);
            }
            else if (left.empty) {
                return this.data.equals(s) || this.right.contains(s);
            }
            else if (right.empty) {
                return this.left.contains(s) || this.data.equals(s);
            }
            else {
                return this.left.contains(s) || 
                        this.data.equals(s) || 
                        this.right.contains(s);
            }
        }
        
        /**
         * add the string to this node
         * @param s the string to add to this node
         */
        void add(T s) {
            int comparison = comp.compare(s, data);
            if (comparison > 0) {
                if (this.right.empty) {
                    this.right = new Node(s, this);
                    adjustTree(this.right);
                }
                else { // right is a node, add to that node
                    this.right.add(s);
                }
            }
            else if (comparison < 0) {
                if (this.left.empty) {
                    this.left = new Node(s, this);
                    adjustTree(this.left);
                }
                else { // left is a node, add to that node
                    this.left.add(s);
                }
            }
        }
        
        /**
         * Okay, this is a little bit confusing, but this is how it works:
         * In Okisaka's Single rotation pictures, n is the node a when rotating
         * right and c when rotating left
         * I do all color flips pre-rotation
         * In the double rotation pictures, n is the node y
         * Since i wrote my single rotation functions based around the top 
         * pictures, in order to achieve a double rotation, I call single 
         * rotation twice, but since the pictures have n in different places,
         * I call the child of y in order to make the two single rotations
         * combine properly
         * 
         * @param n the node to adjust around
         */
        void adjustTree(Node n) {
            //If the node given is not null, the parent is not null,
            //and the parent is red
            if (n != top && n.parent.red) {
                //if the uncle is red (since n is not null, it must be red)
                //Okasaki's COLOR FLIP
                if ((findSibling(n.parent)).red) {
                    n.parent.red = false;
                    Node sibNParent = findSibling(n.parent);
                    Node gp = findGrandparent(n);
                    sibNParent.red = false;
                    gp.red = true;
                    adjustTree(findGrandparent(n));
                }
                
                //If parent is left of grandparent
                else if (n.parent == findGrandparent(n).left) {
                    //if n is left of parent, Okasaki single rotation
                    if (n == n.parent.left) {
                        n.parent.red = false;
                        n.parent.parent.red = true;
                        singleRotationRight(n);
                    }
                    //n is right of parent, Okasaki double rotation (2 singles)
                    else {
                        n.parent.parent.red = true;
                        n.red = false;
                        singleRotationLeft(n.right);
                        singleRotationRight(n.left);
                    }
                }
                
                //If parent is right of grandparent
                else {
                    //if n is right of parent, single rotation
                    if (n == n.parent.right) {
                        n.parent.red = false;
                        n.parent.parent.red = true;
                        singleRotationLeft(n);
                    }
                    //if n if left of parent, double rotation (2 singles)
                    else {
                        n.parent.parent.red = true;
                        n.red = false;
                        singleRotationRight(n.left);
                        singleRotationLeft(n.right);
                    }
                }
            }
            resetTop();
            //top isn't red anymore
            top.red = false;
        }
        
        /** get the grandparent node
         * @param n the node to get the grandparent of
         * @return Node the grandparent of n
         */
        Node findGrandparent(Node n) {
            return n.parent.parent;
        }
        
        /**
         * get the sibling of n
         * @param n the node to get the sibling of
         * @return Node the sibling of n
         */
        Node findSibling(Node n) {
            return (n.parent.right == n) ? n.parent.left : n.parent.right;
        }
        
        /**
         * rotate n left
         * @param n the node to rotate around
         */
        void singleRotationLeft(Node n) {
            Node ggp = findGrandparent(n).parent;
            Node b = findSibling(n);
            n.parent.left = n.parent.parent;
            Node sibN = findSibling(n);
            sibN.parent = n.parent;
            sibN.right = b;
            b.parent = findSibling(n);
            n.parent.parent = ggp;
            
            if (ggp.right == findSibling(n)) {
                ggp.right = n.parent;
            }
            else {
                ggp.left = n.parent;
            }
        }
        
        /**
         * rotate n right
         * @param n the node to rotate around
         */
        void singleRotationRight(Node n) {
            Node ggp = findGrandparent(n).parent;
            Node b = findSibling(n);
            n.parent.right = n.parent.parent;
            Node sibN = findSibling(n);
            sibN.parent = n.parent;
            sibN.left = b;
            b.parent = findSibling(n);
            n.parent.parent = ggp;
            
            if (ggp.right == findSibling(n)) {
                ggp.right = n.parent;
            }
            else {
                ggp.left = n.parent;
            }
        }  
    }

    /**
     * The class to represent a binary tree iterator
     * @author Matt Capone
     * @version 10/05/13
     */
    class BTreeIterator implements Iterator<T> {
        /**The current element in the iterator*/
        Node current;
        
        /**
         * The NodeIterator constructor. Starts at the leftmost node
         */
        BTreeIterator() {
            this.current = top;
            if (current != null) {
                active = active + 1;
                while (!current.left.empty) {
                    current = current.left;
                }
            }
        }
        
        /**
         * Does the tree have a next element?
         * @return boolean true if the tree has a next element
         */
        public boolean hasNext() {
            boolean flag = (current != null && !current.empty);
            if (!flag) {
                active = active - 1;
            }
            return flag;
        }

        /**
         * Return the next element in this tree
         * @return String the next element
         */
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Cant call next when the " +
                        "tree doesn't have a next");
            }
            
            T result = current.data;
            if (!current.right.empty) {
                current = current.right;
                while (!current.left.empty) {
                    current = current.left;
                }
                return result;
            }
            
            else { //current.right is empty
                while (true) {
                    if (current.parent.empty) {
                        current.empty = true;
                        return result;
                    }
                    if (current.parent.left == current) {
                        current = current.parent;
                        return result;
                    }
                    current = current.parent;
                }
            }
        }
            
        /**Remove this element from the tree */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

