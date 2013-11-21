import java.util.Comparator;


/**
 * The class to represent a length string comparator
 * @author Matt Capone
 * @version 10/05/13
 */
class StringByLength implements Comparator<String> {

    /**
     * Compare the two strings
     * @param o1 The first string to compare
     * @param o2 The second string to compare
     * @return int the value of the comparison of the two strings
     */
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
    
    /**Does this StringByLength equal the given object?
     * @param o Object to compare this to
     * @return boolean true if the two objects are equal
     */
    public boolean equals(Object o) {
        return o instanceof StringByLength;
    }
    
    /** The hashCode of this StringByLength 
     * @return in the hashcode of this
     */
    public int hashCode() {
        return 1;
    }
    /** returns this class as a string
     * @return string this as a string
     */
    public String toString() {
        return "StringByLength";
    }
}