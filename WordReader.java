/*
  Ann Soong
  Faye Lin
*/

/**
 * An objet that reads and stores the content of file. It posses a
 * method that returns an iterator on the content (a String).
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WordReader {

    // The content of the file that was read

    private String content;

    /**
     * When an object of the class WordReader is created, this
     * constructor reads the content the file specified by the
     * parameter fileName.
     *
     * @param fileName the specified file
     * @throws FileNotFoundException if the file could not be found
     * @throws IOException if there is an error reading the content of the file
     */
    public WordReader(String fileName) throws FileNotFoundException, IOException {
        this(fileName, true);
    }

    /**
     * When an object of the class WordReader is created, this
     * constructor reads the content the file specified by the
     * parameter fileName.
     *
     * @param fileName the specified file
     * @param caseSensitive if the value is false, the content is transformed to lower case letters
     * @throws FileNotFoundException if the file could not be found
     * @throws IOException if there is an error reading the content of the file
     */
    public WordReader(String fileName, boolean caseSensitive) throws FileNotFoundException, IOException {

        if (fileName == null) {
            throw new NullPointerException();
        }

        InputStreamReader input;
        input =  new InputStreamReader(new FileInputStream(fileName), "UTF-8");

        BufferedReader reader;
        reader = new BufferedReader(input);

        StringBuilder buffer;
        buffer = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            if (! caseSensitive) {
                line = line.toLowerCase();
            }
            buffer.append(line);
        }

        reader.close();

        content = buffer.toString();
    }

    /**
     * Removes all the blank spaces from the content of the text.
     */
    public void removeAllBlankCharacters() {
        content = content.replaceAll("\\p{Blank}","");
    }

    /**
     * Returns an iterator over the content in the text.
     *
     * @param size the size of the n-grams to be returned by the method of the iterator
     * @return an iterator over the content in the text
     */
    public Iterator<String> iterator(int size) {

        //throw new UnsupportedOperationException("not implemented yet!");
        return new MyIterator(size);
    }

    private class MyIterator implements Iterator<String>{ // inner class, nonstatic
      private int index;
      private int size;
      // private T Elem;

      public MyIterator(int s){
        index = 0;
        size = s;
      }

      public boolean hasNext(){
        return (index + size)-1 < content.length();
      }

      public String next(){
        String gram = content.substring(index, index + size);
        index++;
        return gram;
      }
    }
}
