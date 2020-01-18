/*
  Ann Soong
  Faye Lin
*/

/**
 * A Binary Search Tree implementation of the interface WordMap.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */
public class TreeWordMap implements WordMap {

    private static class Elem {

        private String key;
        private int count;
        private Elem left, right;

        private Elem(String key) {
            this.key = key;
            count = 1;
        }
    }

    private Elem root;
    private int size;

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified key
     * @throws NullPointerException if the value of the parameter is null
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new NullPointerException();
        }

        boolean found = false;
        Elem current = root;
        while (! found && current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {  //key is equal to current
                found = true;
            } else if (test < 0) {  //key is less than current
                current = current.left;
            } else {  //key is greater than current
                current = current.right;
            }
        }
        return found;
    }

    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param key the specified word
     * @throws NullPointerException if the value of the parameter is null
     */
    public void update(String key) {
      if(key == null){
        throw new NullPointerException("Key is null.");
      }

      if(size == 0){ // if there is none yet, just make a new one
        root = new Elem(key);
        size++;
      }
      else{
        Elem current = root;
        boolean found = false;
        while(!found){
          int comparison = key.compareTo(current.key); // -1, 0, 1
          if(comparison == 0){ // equal, key is found.
            current.count++;  // need to increase value
            found = true;
          }
          else if(comparison < 0){ // key is less than current, travel left
            if(current.left == null){ // no words are less than key and key is not currently in tree
              current.left = new Elem(key); // add key to tree.
              size++;
              found = true;
            }
            else{ // position for key has not been found, keep travel left
              current = current.left;
            }

          }
          else{ // greater than, travel right
            if(current.right == null){ // no other words are greater than key and key is not currently in tree
              current.right = new Elem(key);  //add key to tree.
              size++;
              found = true;
            }
            else{
              current = current.right;
            }
          }
        }
      }
    }

    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param key the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */
    public int get(String key) {

        if (key == null) {
            throw new NullPointerException();
        }

        boolean found = false;
        Elem current = root;
        while (current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {  //found
                return current.count;
            } else if (test < 0) {  //key is less than current, move left
                current = current.left;
            } else {  //key is greater than current, move right
                current = current.right;
            }
        }

        // exit the loop without returning anything -> key not found
        return 0;
    }

    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */
    public int size() {
        return size;
    }

    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */
    public String[] keys() {
      //if this is empty, method returns empty array instead of throwing exception.

      //Array method
      String[] output = new String[size()];
      addStringArray(output, root, 0);
      return output;

    }

    //Recursive method that uses the provided linked list implementation
    private void addStringList(LinkedList<String> store, Elem current){
      // all keys should be in increasing alphabetical order, so tree is accessed
      // in order of left-current-right.
      if( current != null){
        addStringList(store, current.left); // keys that are less than current
        store.addLast(current.key);
        addStringList(store, current.right);  // keys that are greater than current
      }
    }

    //Recursive method that uses an array of string without merging.
    private int addStringArray(String[] store, Elem current, int index) {
      if (current != null) {
        index = addStringArray(store, current.left, index);
        store[index++] = current.key;
        index = addStringArray(store, current.right, index);
      }

      return index;
    }

    /**
     * Returns all the counts associated with keys in this
     * WordMap. The counts are in the same order as that of the keys
     * returned by the method keys().
     *
     * @return all the counts
     */
    public Integer[] counts() {

        //throw new UnsupportedOperationException("not implemented yet!");
        String[] aKeys = keys();
        Integer[] aCount = new Integer[size];

        for(int i = 0; i < aKeys.length; i++){
          aCount[i] = get(aKeys[i]);
        }

        return aCount;
    }

    /**
     * Returns a String representation of this WordMap.
     *
     * @return a String representation of this WordMap
     */
    public String toString() {

        String[] keys = this.keys();
        Integer[] counts = this.counts();

        String strHold = "[";

        for(int i = 0; i < keys.length; i++){
          strHold += keys[i] + ":" + counts[i];
          if(i < keys.length-1){
            strHold += ", ";
          }
        }

        strHold += "]";
        return strHold;
    }
}
