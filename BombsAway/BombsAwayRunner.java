public class BombsAwayRunner {
    public static void main(String[] args){
        StdDraw.setCanvasSize();
        StdDraw.picture(0.5, 0.5, "C:/Users/14058/OneDrive/Desktop/APCS-Projects-Extended/BombsAway/mcommand.jpg");
        BombsAway game = new BombsAway();
        game.play();
    }
}
