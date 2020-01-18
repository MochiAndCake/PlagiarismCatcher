/*
  Ann Soong
  Faye Lin
*/


// This class produces the final similarity score between two texts based on similar n-grams but not counts
public class Jaccard extends Object implements Similarity{

  // Constructor
  public Jaccard(){
  }

  // Returns the percentage similarity
  public double score(WordMap a, WordMap b){
    if(a == null || b == null){
      throw new NullPointerException("A parameter is null.");
    }

    if(a.size() == 0 || b.size() == 0){
      throw new IllegalArgumentException("A WordMap has size 0.");
    }

    // Need to loop through both WordMaps to find overlap
    // Each key is like a coordinate
    // Each count is like a value at the coordinate
    // Like a 2D array where AxB
    // M00 M10
    // M01 M11
    // M00 - total number of attributes where A and B both have 0
    // M11 - total number of attributes where A and B both have 1
    // J = M11 / (M01 + M10 + M11)

    String[] wmA = a.keys();
    String[] wmB = b.keys();
    double common, total;
    common = total = 0.0;
    int result, v, u;
    v = u = 0;  //indices

    while(v < a.size() && u < b.size()){
      result = wmA[v].compareTo(wmB[u]);
      if(result == 0){ // n-gram appears in both
        common++;
        v++;
        u++;
      }
      else if(result < 0){ // n-gram from a is part of an n-gram from b
        v++;
      }
      else{
        u++;
      }
      total++;
    }

    while(v < a.size()){ // catch leftovers unique to a
      total++;
      v++;
    }

    while(u < b.size()){ // catch leftovers unique to b
      total++;
      u++;
    }

    return common/total;
  }
}
