Ann Soong,    Faye Lin
Assignment #4

This program calculates how similar 2 files are using two different methods. Jaccard only compares how many
similar n-grams there are between two documents. Cosine compares how similar two documents are using both
n-grams and the frequency of the grams.

To run the program, you need to run Pastiche while providing the folder containing the texts that are
to be tested, the data structure to test with (either LinkedWordMap or TreeWordMap), and the method of
testing (either Cosine or Jaccard).

    java Pastiche data/folder_with_texts Data_structure Method_of_testing

For example: java Pastiche data/pride_and_prejudice/data-1 TreeWordMap Jaccard
             java Pastiche data/alice/data-1 LinkedWordMap Cosine



* Academic Integrity

By submitting this assignment, I acknowledge:

1. I have read the academic regulations regarding academic fraud.

2. I understand the consequences of plagiarism.

3. With the exception of the source code provided by the instructors
   for this course, all the source code is mine.

4. I did not collaborate with any other person, with the exception of
   my partner in the case of team work.

   If you did collaborate with others or obtained source code from the
   Web, then please list the names of your collaborators or the source
   of the information, as well as the nature of the collaboration. Put
   this information in the submitted README.txt file. Marks will be
   deducted proportional to the level of help provided (from 0 to
   100%).
