import java.io.FileNotFoundException;

public class TestFlesch
    {
      public static void main (String[] args) throws FileNotFoundException
      {
        Flesch ms = new Flesch("C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/Flesch_Readability/gettysburg.txt");   
        System.out.println( "Source text file: " + ms.getFilename()) ;
        System.out.println( "Readability score: " + ms.getLegibilityIndex()) ;
        System.out.println( "Educational level: " + ms.getEducationalLevel());
        System.out.println( "Syllables: " + ms.getNumSyllables() );
        System.out.println( "Words: " + ms.getNumWords());
        System.out.println( "Sentences: " + ms.getNumSentences() );
      }
    }

