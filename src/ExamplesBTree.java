import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;


/** 
 * Test BTree<T>
 * @author Matt Capone
 * @version 10/30/2013
 */
public class ExamplesBTree {
    private BTree<String> t1; // empty tree
    private BTree<String> t2; // ta1 by len
    private BTree<String> t3; // ta1 by lex
    private BTree<String> t4; // ta2 by len
    private BTree<String> t5; // ta2 by lex
    private BTree<String> t6; // ta3 by len
    private BTree<String> t7; // ta3 by lex
    private BTree<String> t8; // ta4 by len
    private BTree<String> t9; // ta4 by lex
    private BTree<String> t10; // ta5 by lex
    private BTree<String> t11; //2nd build test
    private BTree<String> t12; //2nd build test
    private BTree<String> t13; //2nd build test
    
    private BTree<String> longTree;
    private BTree<String> longTree2;
    
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
        t1 = BTree.binTree(lex);
        t2 = BTree.binTree(len);
        t3 = BTree.binTree(lex);
        t4 = BTree.binTree(len);
        t5 = BTree.binTree(lex);
        t6 = BTree.binTree(len);
        t7 = BTree.binTree(lex);
        t8 = BTree.binTree(len);
        t9 = BTree.binTree(lex);
        t10 = BTree.binTree(lex);
        t11 = BTree.binTree(lex);
        t12 = BTree.binTree(lex);
        t13 = BTree.binTree(lex);
        
        longTree = BTree.binTree(lex);
        longTree2 = BTree.binTree(lex);
        
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
    
    /** test RepOK */
    @Test
    public void testRepOK() {
        createTrees();
        
        assertFalse(t1.repOK());
        assertTrue(t2.repOK());
        assertTrue(t13.repOK());
        assertTrue(t9.repOK());
        assertTrue(longTree.repOK());
    }
    
    /** Test Black Height */
    @Test
    public void testBlackHeight() {
        createTrees();
        BlackHeight<String> bh = new BlackHeight<String>();
        
        assertEquals(t1.accept(bh), new Integer(0));
        assertEquals(t3.accept(bh), new Integer(1));
        assertEquals(longTree.accept(bh), new Integer(4));
    }
    
    /** Test Height */
    @Test
    public void testHeight() {
        createTrees();
        Height<String> h = new Height<String>();
        
        assertEquals(t1.accept(h), new Integer(0));
        assertEquals(t3.accept(h), new Integer(1));
        assertEquals(longTree.accept(h), new Integer(7));
    }
    
    /** Test Count Nodes */
    @Test
    public void testCountNodes() {
        createTrees();
        CountNodes<String> cn = new CountNodes<String>();
        ArrayList<Integer> t1List = new ArrayList<Integer>();
        ArrayList<Integer> t2List = new ArrayList<Integer>();
        ArrayList<Integer> t3List = new ArrayList<Integer>();
        
        t1List.add(0);
        t1List.add(0);
        t1List.add(0);
        t2List.add(1);
        t2List.add(1);
        t2List.add(0);
        t3List.add(4);
        t3List.add(3);
        t3List.add(1);
        
        assertEquals(t1.accept(cn), t1List);
        assertEquals(t3.accept(cn), t2List);
        assertEquals(t9.accept(cn), t3List);
    }
    
    /** Test Path Lengths */
    @Test
    public void testPathLengths() {
        createTrees();
        PathLengths<String> pl = new PathLengths<String>();
        ArrayList<Integer> t1List = new ArrayList<Integer>();
        ArrayList<Integer> t2List = new ArrayList<Integer>();
        ArrayList<Integer> t3List = new ArrayList<Integer>();
        
        t1List.add(0);
        t2List.add(1);
        t2List.add(1);
        t3List.add(2);
        t3List.add(2);
        t3List.add(2);
        t3List.add(3);
        t3List.add(3);
        
        assertEquals(t1.accept(pl), t1List);
        assertEquals(t3.accept(pl), t2List);
        assertEquals(t9.accept(pl), t3List);
    }
}
