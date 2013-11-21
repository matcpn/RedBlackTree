package rbtree;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

/** 
 * Test RBTree
 * @author Matt Capone
 * @version 10/30/2013
 */
public class ExamplesRBTree {
    private RBTree<String> t1; // empty tree
    private RBTree<String> t2; // ta1 by len
    private RBTree<String> t3; // ta1 by lex
    private RBTree<String> t4; // ta2 by len
    private RBTree<String> t5; // ta2 by lex
    private RBTree<String> t6; // ta3 by len
    private RBTree<String> t7; // ta3 by lex
    private RBTree<String> t8; // ta4 by len
    private RBTree<String> t9; // ta4 by lex
    private RBTree<String> t10; // ta5 by lex
    private RBTree<String> t11; //2nd build test
    private RBTree<String> t12; //2nd build test
    private RBTree<String> t13; //2nd build test
    
    private RBTree<String> longTree;
    private RBTree<String> longTree2;
    
    private Comparator<String> lex = new StringByLex();
    private Comparator<String> len = new StringByLength();
    
    private ArrayList<String> ta1 = new ArrayList<String>(
            Arrays.asList("abc"));
    private ArrayList<String> ta2 = new ArrayList<String>(
            Arrays.asList("abc", "def"));
    private ArrayList<String> ta3 = new ArrayList<String>(
            Arrays.asList("abc", "def", "ghi"));
    private ArrayList<String> ta4 = new ArrayList<String>(
            Arrays.asList("abc", "def", "ghi", "jkl"));
    private ArrayList<String> ta5 = new ArrayList<String>(
            Arrays.asList("abc", "ghi", "def", "jkl"));
    private ArrayList<String> longList = new ArrayList<String>(
            Arrays.asList("ts", "ix", "bd", "kf", "vc", "bw", "vl", "ra", "te",
                    "cw", "pj", "de", "gi", "io", "ff", "ja", "qh", "im", "op",
                    "um", "wl", "rt", "md", "db", "pc", "ly", "hb", "sb", "uk",
                    "pe", "uj", "vb", "rj", "xa", "fr", "ug", "vk", "gx", "kx",
                    "jg", "pa", "oj", "di", "gm", "gw", "yw", "aq", "kc", "pb",
                    "lk"));
    private ArrayList<String> longList2 = new ArrayList<String>(
            Arrays.asList("dr", "ex", "sh", "fb", "gc", "ds", "en", "id", "le",
                    "fq", "we", "rh", "lw", "fo", "en", "cq", "qb", "sh", "et",
                    "ww", "nm", "us", "op", "hm", "ay", "wg", "xv", "tf", "he",
                    "id", "fy", "pl", "je", "tl", "wd", "dy", "ku", "pt", "hq",
                    "ot", "yt", "kv", "fa", "ny", "mf", "tf", "kf", "rf", "xh",
                    "ye"));
    
    /** Create RBtrees */
    void createTrees() {
        t1 = RBTree.binTree(lex);
        t2 = RBTree.binTree(len);
        t3 = RBTree.binTree(lex);
        t4 = RBTree.binTree(len);
        t5 = RBTree.binTree(lex);
        t6 = RBTree.binTree(len);
        t7 = RBTree.binTree(lex);
        t8 = RBTree.binTree(len);
        t9 = RBTree.binTree(lex);
        t10 = RBTree.binTree(lex);
        t11 = RBTree.binTree(lex);
        t12 = RBTree.binTree(lex);
        t13 = RBTree.binTree(lex);
        
        longTree = RBTree.binTree(lex);
        longTree2 = RBTree.binTree(lex);
        
        t2.build(ta1);
        t3.build(ta1);
        t4.build(ta2);
        t5.build(ta2);
        t6.build(ta3);
        t7.build(ta3);
        t8.build(ta4);
        t9.build(ta4);
        t10.build(ta5);
        t11.build(ta5, 10);
        t12.build(ta5, -1);
        t13.build(ta5, 1);
        
        longTree.build(longList);
        longTree2.build(longList2);
        
    }
    
    /** test the toString method */
    @Test
    public void testToString() {
        createTrees();
        
        assertEquals("", t1.toString());
        assertEquals("abc", t2.toString());
        assertEquals("abc", t8.toString());
        assertEquals("abc, def", t5.toString());
        assertEquals("abc, def, ghi", t7.toString());
        assertEquals("abc, def, ghi, jkl", t9.toString());
        assertEquals("abc, def, ghi, jkl", t10.toString());
    }
    
    /** test the hashCode method */
    @Test
    public void testHashCode() {
        createTrees();
        
        assertEquals(t1.hashCode(), 0);
        assertEquals(t2.hashCode(), t8.hashCode());
        assertEquals(t9.hashCode(), t9.hashCode());
        assertEquals(t3.hashCode(), t3.hashCode());
        assertEquals(t6.hashCode(), t6.hashCode());
        assertEquals(longTree.hashCode(), longTree.hashCode());
        assertEquals(longTree2.hashCode(), longTree2.hashCode());
    }
    
    /** test the equals method */
    @Test
    public void testEquals() {
        createTrees();
        
        assertFalse(t1.equals(t2));
        assertTrue(t1.equals(t1));
        assertFalse(t1.equals("hi"));
        assertTrue(t9.equals(t10));
        assertTrue(t2.equals(t8));
        assertTrue(t4.equals(t4));
        assertTrue(longTree.equals(longTree));
        assertTrue(longTree2.equals(longTree2));
        assertTrue(t12.equals(t10));
        assertFalse(t11.equals(t13));
        assertFalse(t13.equals(t2));
    }
    
    /** test the contains method */
    @Test
    public void testContains() {
        createTrees();
        
        assertFalse(t1.contains("def"));
        assertTrue(t9.contains("def"));
        assertTrue(longTree.contains("im"));
        assertFalse(longTree2.contains("zz"));
        
        assertFalse(longTree.contains("ll"));
        assertFalse(longTree.contains("ri"));
        
        assertTrue(longTree.contains("aq"));
        assertTrue(longTree.contains("db"));
        
        assertTrue(longTree.contains("gm"));
        assertTrue(longTree.contains("rt"));
    }
    
    /** test the iterator */
    @Test
    public void testIterator() {
        createTrees();
        
        Iterator<String> i1 = t9.iterator();
        Iterator<String> i2 = t9.iterator();
        Iterator<String> i3 = longTree.iterator();
        Iterator<String> i4 = t1.iterator();
        Iterator<String> i5 = t2.iterator();
        
        assertFalse(i4.hasNext());
        while (i3.hasNext()) {
            i3.next();
        }
        i5.next();
        assertEquals(i1.next(), "abc");
        assertEquals(i2.next(), "abc");
        try {
            t9.build(ta1);
            i4.next();
        } 
        catch (Exception e) {
            assertNotNull(e);
        }
        try {
            t9.build(ta1, 5);
        } 
        catch (Exception e) {
            assertNotNull(e);
        }
        try {
            i2.remove();
        }
        catch (Exception e) {
            assertNotNull(e);
        }
        assertEquals(i1.next(), "def");
        assertEquals(i2.next(), "def");
        i2.next();
        i2.next();
        try {
            i2.next();
        }
        catch (Exception e) {
            assertNotNull(e);
        }
    }
    
    /** test comparators */
    @Test
    public void testComparators() {
        assertEquals(lex.toString(), "StringByLex");
        assertEquals(lex.hashCode(), lex.hashCode());
        assertEquals(len.toString(), "StringByLength");
        assertEquals(len.hashCode(), len.hashCode());
    }
    
    /** test repOK */
    @Test
    public void testRepOK() {
        createTrees();
        assertTrue(t3.repOK());
    }
}
