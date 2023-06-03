public class Runner {
    public static void main(String[] args){
        // Room room = new Room("test");
	    // System.out.println(room.getDescription());
	    // System.out.println("Exits: " + room.getExitString());
	    // room.setExit("north", new Room("another"));
	    // System.out.println("Exits: " + room.getExitString());
	    // System.out.println(room.getExit("north").getDescription());
        // System.out.println(room.getExit("south"));

        Game game = new Game();
        game.play();
    }
}
