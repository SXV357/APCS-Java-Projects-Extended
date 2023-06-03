import java.util.Scanner;

public class Game
{
	private Room currentRoom;
	private Scanner console;

	public Game()
	{
		this.createRooms();
		this.console = new Scanner(System.in);
	}

	/** Creates Room objects and sets exits to create the game world */
	private void createRooms()
	{
		//create Rooms
		Room rotunda = new Room("A large, echoing rotunda");
		Room principalsOffice = new Room("The principal's office");
		Room entryWay = new Room("School entryway");
		Room outside = new Room("Outside the school");

		//set exits for Rooms
		outside.setExit("north", entryWay);
		entryWay.setExit("north", rotunda);
		entryWay.setExit("south", outside);
		entryWay.setExit("east", principalsOffice);
		principalsOffice.setExit("west", entryWay);
		rotunda.setExit("south", entryWay);

		//initialize game's starting room
		this.currentRoom = outside;
	}

	private void printWelcome()
	{
		System.out.println("Welcome to my text-based adventure!");
		System.out.println("At the moment, it's a very boring adventure");
		System.out.println("Type 'help' if you need help\n");
		System.out.println("You are here >>> " + currentRoom.getDescription());
	}

	private void printHelp()
	{
		System.out.println("You are alone, you are lost.");
		System.out.println("You wander around an emtpy school.");
		System.out.println("Your commands are >>> " + Command.getCommandsString());
	}

	/** Takes a Command object and attempts to process it as a game command */
	private boolean processCommand(Command command)
	{
		boolean finished = false;
		if (command.isUnknown()){
			System.out.println("I don't know what you mean...");
			return false;
		}
		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")){
			this.printHelp();
		}
		else if (commandWord.equals("go")){
			this.goRoom(command);
		}
		else if (commandWord.equals("quit")){
			finished = quit(command);
		}
		return finished;
	}

	/** Attempts to move the player to another Room based on their Command */
	private void goRoom(Command command)
	{
		if (command.getSecondWord() == null){
			System.out.println("Go where?");
			return;
		}
		String direction = command.getSecondWord();
		Room nextRoom = null;
		nextRoom = this.currentRoom.getExit(direction);
		if (nextRoom != null){
			this.currentRoom = nextRoom;
			System.out.println("You are here >>> " + this.currentRoom.getDescription());
		}
		else{
			System.out.println("There is no door!");
		}
	}

	/** Returns true if the user is wanting to quit */
	private boolean quit(Command command)
	{
		if (!(command.getSecondWord() == null)){
			System.out.println("Quit what?");
			return false;
		}
		else {
			System.out.println("Thanks for playing, goodbye.");
			return true;
		}
	}

	/** This method starts the game, continuing until the user wants to quit */
	public void play()
	{
		printWelcome();
		boolean finished = false;
		while (!(finished)){
			System.out.println("Exits >>> " + currentRoom.getExitString());
			System.out.print("> ");
			String line = console.nextLine();
			Scanner tokenizer = new Scanner(line);
			String command = null;
			String second = null;
			if (tokenizer.hasNext()){
				command = tokenizer.next();
			}
			if (tokenizer.hasNext()){
				second = tokenizer.next();
			}
			Command c = new Command(command, second);
			finished = processCommand(c);
		}
	}
}