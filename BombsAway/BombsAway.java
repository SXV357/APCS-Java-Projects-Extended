import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class BombsAway
{
    private ArrayList<Bomb> bombs;
    private int lives;
    private int score;

    public BombsAway(){
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
                    StdDraw.setPenColor(new Color(255, 0, 0));  
                    StdDraw.filledSquare(0.5, 0.5, 256.0);  
                    StdDraw.show(100);
                    score += bombs.size();
                    bombs.clear();   
                }
            }
        }
    }
    void updateScore(){}
    void play(){}
    void update(){}
}

