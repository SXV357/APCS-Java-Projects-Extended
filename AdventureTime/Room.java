import java.util.*;

public class Room
{
	private String description;

	private HashMap<String, Room> exits;

	public Room(String desc)
	{
		this.description = desc;
		this.exits = new HashMap<String, Room>();
	}

	/** add an exit to this room in the supplied direction */
	public void setExit(String direction, Room exit)
	{
		this.exits.put(direction, exit);
	}

	/** get the exit mapped in a particular direction */
	public Room getExit(String direction)
	{
		return this.exits.get(direction);
	}

	/** Return a String showing all the possible exits a Room has */
	public String getExitString()
	{
		String result = "";

		for (String exit : exits.keySet()) //a for-each loop, looping through a Set of String keys
			 result += " " + exit;

		return result;
	}

	public String getDescription() { return description; }
}