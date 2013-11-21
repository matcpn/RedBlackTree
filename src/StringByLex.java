import java.util.Comparator;

/**
 * The class to represent an alphabetical string comparator
 * @author Matt Capone
 * @version 10/05/13
 */
class StringByLex implements Comparator<String> {

    /**
     * Compare the two strings
     * @param o1 The first string to compare
     * @param o2 The second string to compare
     * @return int the value of the comparison of the two strings
     */
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
    
    /**Does this StringByLex equal the given object?
     * @param o Object to compare this to
     * @return boolean true if the two objects are equal
     */
    public boolean equals(Object o) {
        return o instanceof StringByLex;
    }
    
    /** The hashCode of this StringByLex 
     * @return int the hashCode of this
     */
    public int hashCode() {
        return 1;
    }
    
    /**
     * The toString for this StringByLex
     * @return String the string of this
     */
    public String toString() {
        return "StringByLex";
    }
}