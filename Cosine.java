/*
  Ann Soong
  Faye Lin
*/

// This class produces the final similarity score between two texts using both n-grams and counts
public class Cosine extends Object implements Similarity{

  // Constructor
  public Cosine(){
  }

  // Returns the cosine similarity measure between two WordMap.
  public double score(WordMap a, WordMap b){
    if(a == null || b == null){
      throw new NullPointerException("A parameter is null.");
    }

    if(a.size() == 0 || b.size() == 0){
      throw new IllegalArgumentException("A WordMap has size 0.");
    }

    String[] sA = a.keys();
    Integer[] iA = a.counts();
    String[] sB = b.keys();
    Integer[] iB = b.counts();

    // dot product / (magnitudeA * magnitudeB)
    //when there is none of a word, it is 0
    int i, j, result;
    i = j = 0;
    double dotproduct, magA, magB;
    dotproduct = magA = magB = 0.0;

    while(i < a.size() && j < b.size()){
      result = sA[i].compareTo(sB[j]);
      if(result == 0){ // n-gram exists in both
        dotproduct += iA[i] * iB[j];
        magA += iA[i]*iA[i];
        magB += iB[j]*iB[j];
        i++;
        j++;
      }
      else if(result < 0){ // A less than B, need to catch up
        magA += iA[i]*iA[i];
        // magB += 0;
        // dotproduct += 0;
        i++;
      }
      else{ // B less than A, need to catch up
        magB += iB[j]*iB[j];
        // magA += 0;
        // dotproduct += 0;
        j++;
      }
    }

    while(i < a.size()){ // catch leftovers
      magA += iA[i]*iA[i];
      // magB += 0;
      // dotproduct += 0;
      i++;
    }

    while(j < b.size()){ // catch leftovers
      magB += iB[j]*iB[j];
      // magA += 0;
      // dotproduct += 0;
      j++;
    }

    return dotproduct/(Math.sqrt(magA) * Math.sqrt(magB));
  }
}
