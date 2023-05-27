import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Flesch{

    private int syllables;
    private int words;
    private int sentences;
    private String fileName;

    public Flesch(String fileName) throws FileNotFoundException{
        this.fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        this.words = 0;
        this.sentences = 0;
        this.syllables = 0;
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNext()){
            String word = file.next();
            if (word != null){
                this.words++;
                char lastChar = word.charAt(word.length() - 1);
                if (lastChar == '.' || lastChar == ':' || lastChar == ';' || lastChar == '?' || lastChar == '!'){
                    this.sentences++;
                }
                for (int i = 0; i < word.length(); i++){
                    if (i != word.length() - 1 && (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' || word.charAt(i) == 'y')){
                        this.syllables++;
                    }
                }
            }
        }
    }

    public double getLegibilityIndex(){
        double averageSyllablesPerWord = (double) this.syllables / this.words;
        double averageWordsPerSentence = (double) this.words / this.sentences;
        return Math.round(Math.abs(((averageWordsPerSentence * 1.015) + (averageSyllablesPerWord * 84.6)) - 206.835));
    }

    public String getEducationalLevel(){
        int score = (int) getLegibilityIndex();
        if (score >= 0 && score <= 30){
            return "College Graduate";
        }
        else if (score >= 31 && score <= 50){
            return "College Student";
        }
        else if (score >= 51 && score <= 60){
            return "10th to 12th Grade";
        }
        else if (score >= 61 && score <= 70){
            return "8th and 9th Grade";
        }
        else if (score >= 71 && score <= 80){
            return "7th Grade";
        }
        else if (score >= 81 && score <= 90){
            return "6th Grade";
        }
        return "5th Grade";
    }

    public String getFilename(){return this.fileName;}
    public int getNumSyllables(){return this.syllables;}
    public int getNumSentences(){return this.sentences;}
    public int getNumWords(){return this.words;}
}