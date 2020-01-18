/*
  Ann Soong
  Faye Lin
*/

/**
 * An implementation of the interface WordMap using linked elements.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */
public class LinkedWordMap implements WordMap {

    private static class Node{
      private String key;
      private int count;
      private Node previous, next;
      public Node(String k){ // for all other nodes
        key = k;
        count = 1;
        previous = null;
        next = null;
      }

      public Node(){ // for the head
        key = null;
        count = 0;
        previous = null;
        next = null;
      }
    }

    private Node head;
    private int size;

    public LinkedWordMap(){
      head = new Node();
      head.previous = head.next = head;
      size = 0;
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
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */
    public boolean contains(String key) {

        if(key == null){
          throw new NullPointerException("Key is null.");
        }

        int value = get(key);
        return value != 0;
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
        if(key == null){
          throw new NullPointerException("Key is null.");
        }

        Node current = head.next;
        boolean found = false;
        while(!found && current != head){
          if(key.equals(current.key)){
            found = true;
          }
          else{
            current = current.next;
          }
        }
        return current.count;
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

        if(size == 0){ // if empty, add key
          Node newN = new Node(key);
          head.next = head.previous = newN;
          newN.next = newN.previous = head;
          size++;
        }
        else{
          Node current = head.next;
          int result;
          boolean update = false;

          while(!update && current != head){
            result = current.key.compareTo(key);
            if(result == 0){ // key is found
              current.count++;
              update = true;
            }
            else if(result > 0){ // if current.key is greater, add key before current
              addAfter(current.previous, key);
              update = true;
            }
            else if(result < 0 && current.next == head){ // if whole list is smaller than key
              addAfter(current, key); //add at very end
              update = true;
            }
            else{ // if key is still greater than current.key and has not reached the end
              current = current.next;
            }
          }
        }
    }

    private void addAfter(Node current, String key){
      Node newN = new Node(key);

      newN.next = current.next;
      current.next.previous = newN;
      current.next = newN;
      newN.previous = current;
      size++;
    }

    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */
    public String[] keys() {

        String[] keys = new String[size];
        Node current = head.next;
        int i = 0;
        while(current != head){
          keys[i] = current.key;
          current = current.next;
          i++;
        }
        return keys;
    }

    /**
     * Returns all the counts associated with keys in this
     * WordMap. The counts are in the same order as that of the keys
     * returned by the method keys().
     *
     * @return all the counts
     */
    public Integer[] counts() {

        Integer[] count = new Integer[size];
        Node current = head.next;
        int i = 0;
        while(current != head){
          count[i] = current.count;
          current = current.next;
          i++;
        }
        return count;
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
