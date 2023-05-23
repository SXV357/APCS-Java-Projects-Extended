import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class BombsAway
{
    private ArrayList<Bomb> bombs;
    private int lives;
    private int score;

    public BombsAway(){
        StdDraw.setCanvasSize();
        StdDraw.setXscale();
        StdDraw.setYscale();
        StdDraw.picture(0.5, 0.5, "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/BombsAway/mcommand.jpg");  
        this.bombs = new ArrayList<Bomb>();
        this.lives = 3;
        this.score = 0;
    }

	void fire(){
        StdDraw.setPenColor(new Color(255, 0, 0));
        for (int i = 0; i < bombs.size(); i++){
            if (bombs.get(i).intersects(StdDraw.mouseX(), StdDraw.mouseY())){
                if (bombs.get(i).isSplitter()){
                    StdDraw.line(0.0, 0.0, StdDraw.mouseX(), StdDraw.mouseY());
                    bombs.remove(i);
                    Bomb bomblet1 = new Bomb(bombs.get(i), bombs.get(i).getRadius());
                    Bomb bomblet2 = new Bomb(bombs.get(i), -1 * bombs.get(i).getRadius());
                    bombs.add(bomblet1); bombs.add(bomblet2);
                    this.score++;
                }
                else {
                    StdDraw.line(0.0, 0.0, StdDraw.mouseX(), StdDraw.mouseY());
                    bombs.remove(i);
                    this.score++;
                }
            }
        }
    }
    void nuke(){
        if (this.score == 10){
            if (StdDraw.hasNextKeyTyped()){
                if (StdDraw.nextKeyTyped() == ' '){
                    StdDraw.setPenColor(new Color(255, 114, 118));  
                    StdDraw.filledSquare(0.5, 0.5, 256.0); // takes up entire canvas width
                    StdDraw.show(100);
                    for (int j = 0; j < bombs.size(); j++){
                        this.score += bombs.get(j).isSplitter() ? 3 : 1; // if the bomb is a splitter, there's one point for destroying the first one and two more for the two bomblets
                    }
                    bombs.clear();   
                }
            }
        }
    }
    void updateScore(){
        String stats = "Score:" + String.valueOf(this.score) + (this.score == 10 ? "*" : "") + "," + "Lives: " + String.valueOf(this.lives);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
        StdDraw.setPenColor(new Color(255, 0, 0));
        StdDraw.textLeft(0, 1, stats);
    }
    void play(){
    }
    void update(){}
}

