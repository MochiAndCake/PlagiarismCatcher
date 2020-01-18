/*
  Ann Soong
  Faye Lin
*/

public interface Similarity{

  /**
   * Calculates a similarity measure between two documents represented
   * by their WordMap objects.
   *
   * @param a the WordMap of documnet A
   * @param b the WordMap of document B
   * @return the similarity measure of the two frequency vectors
   * @throws NullPointerException if any of the two parameters is null
   * @throws IllegalArgumentException if any of the two WordMap objects has size 0
   */
  public abstract double score(WordMap a, WordMap b);
}
