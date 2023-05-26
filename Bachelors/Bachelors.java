import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Bachelors {
    private int numLadies;
    private int numBachelors;
    private int[] ladiesHeights;
    private int[] bachelorsHeights;

    public Bachelors(String fileName) throws FileNotFoundException{
        Scanner file = new Scanner(new File(fileName));
        this.numLadies = file.nextInt();
        this.ladiesHeights = new int[numLadies];
        for (int i = 0; i < this.numLadies; i++){
            this.ladiesHeights[i] = file.nextInt();
        }
        this.numBachelors = file.nextInt();
        this.bachelorsHeights = new int[numBachelors];
        for (int j = 0; j < this.numBachelors; j++){
            this.bachelorsHeights[j] = file.nextInt();
        }
    }

    public int getNumLadies(){return this.numLadies;}
    public int getNumBachelors(){return this.numBachelors;}
    public String getLadiesHeights(){return Arrays.toString(this.ladiesHeights);}
    public String getBachelorsHeights(){return Arrays.toString(this.bachelorsHeights);}

    public void solve(){
        ArrayList<Integer> maxShorter = new ArrayList<Integer>();
        ArrayList<Integer> minTaller = new ArrayList<Integer>();
        // To find the tallest girl shorter than each guy
        for (int i = 0; i < bachelorsHeights.length; i++){
            int max = -1;
            for (int j = 0; j < ladiesHeights.length; j++){
                if (this.ladiesHeights[j] < this.bachelorsHeights[i] && this.ladiesHeights[j] > max){
                    max = this.ladiesHeights[j];
                }
            }
            maxShorter.add(max);
        }
        // To find the shortest girl taller than each guy
        for (int j = 0; j < bachelorsHeights.length; j++){
            int min = this.ladiesHeights[this.ladiesHeights.length - 1];
            for (int k = ladiesHeights.length -1; k >= 0; k--){
                if (this.ladiesHeights[k] > this.bachelorsHeights[j] && this.ladiesHeights[k] < min){
                    min = this.ladiesHeights[k];
                }
                else if (min < this.bachelorsHeights[j]){
                    min = -1;
                }
            }
            minTaller.add(min);
        }
        for (int n = 0; n < maxShorter.size(); n++){
            System.out.println(maxShorter.get(n) + " " + minTaller.get(n));
        }
    }
}
