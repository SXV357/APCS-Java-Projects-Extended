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

    void drawBombs(){
        for (int i = 0; i < bombs.size(); i++){
            bombs.get(i).draw();
        }
    }

	void fire(){
        StdDraw.setPenColor(new Color(255, 0, 0));
        for (int i = 0; i < bombs.size(); i++){
            if (bombs.get(i).intersects(StdDraw.mouseX(), StdDraw.mouseY())){
                if (bombs.get(i).isSplitter()){
                    Bomb referenceBomb = bombs.get(i);
                    StdDraw.line(0.0, 0.0, StdDraw.mouseX(), StdDraw.mouseY());
                    bombs.get(i).attack(); // make sure strength decreases and the bomb color changes
                    bombs.remove(i);
                    this.score++;
                    Bomb bomblet1 = new Bomb(referenceBomb, bombs.get(i).getRadius());
                    Bomb bomblet2 = new Bomb(referenceBomb, -1 * bombs.get(i).getRadius());
                    bombs.add(bomblet1); bombs.add(bomblet2);
                }
                else {
                    StdDraw.line(0.0, 0.0, StdDraw.mouseX(), StdDraw.mouseY());
                    bombs.get(i).attack();
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
        String stats = "Score: " + String.valueOf(this.score) + (this.score == 10 ? "*" : "") + " " + "Lives: " + String.valueOf(this.lives);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
        StdDraw.setPenColor(new Color(255, 0, 0));
        StdDraw.textLeft(0, 1, stats);
    }

    void play(){ 
        while (this.lives > 0){
            update();
        } 
    }

    void update(){
            StdDraw.clear();
            StdDraw.picture(0.5, 0.5, "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/BombsAway/mcommand.jpg");  
            updateScore();
            if (Math.random() < 0.01){
                bombs.add(new Bomb());
            }
            for (int i = 0; i < bombs.size(); i++){
                if (bombs.get(i).getY() < 0.0){
                    this.lives--;
                    bombs.remove(i);
                }
                else {
                    bombs.get(i).updatePos();
                }
            }
            drawBombs();
            fire();
            nuke();
            StdDraw.show(10);
    }
}

