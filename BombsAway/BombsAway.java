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
                StdDraw.line(0.0, 0.0, StdDraw.mouseX(), StdDraw.mouseY());
                bombs.remove(i);
                this.score++;
            }
        }
    }
    void nuke(){
        if (this.score == 10){
            if (StdDraw.hasNextKeyTyped()){
                if (StdDraw.nextKeyTyped() == ' '){
                    StdDraw.setPenColor(new Color(255, 114, 118));  
                    StdDraw.filledSquare(0.5, 0.5, 256.0);  
                    StdDraw.show(100);
                    score += bombs.size();
                    bombs.clear();   
                }
            }
        }
    }
    void updateScore(){
        String score = "Score:" + String.valueOf(this.score) + (this.score == 10 ? "*" : "");
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
        StdDraw.setPenColor(new Color(255, 0, 0));
        StdDraw.textLeft(0, 1, score);
    }
    void play(){}
    void update(){}
}

