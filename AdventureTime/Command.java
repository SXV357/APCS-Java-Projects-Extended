public class Command 
{
	private static String[] commands = {
		"go", "quit", "help"
	};
	
	private String commandWord;
	private String secondWord;
	
	public Command(String first, String second)
	{
		this.commandWord = first;
		this.secondWord = second;
	}
	
	/** Returns true if str is a valid command (exists in the array) */
	public static boolean isCommand(String str)
	{
		for (int i = 0; i < commands.length; i++){
			if (commands[i].equals(str)){
				return true;
			}
		}
		return false;
	}

	/** Gets all the command words in the array, returns as a String */
	public static String getCommandsString() 
	{
		String res = "";
		for (String command: commands){
			res += " " + command;
		}
		return res;
	}

	/** 
	 * An 'unknown' command will have a null commandWord
	 * A 'known' command will have a valid command as a commandWord 
	 */
	public boolean isUnknown() 
	{
		if (this.commandWord == null || !(isCommand(this.commandWord))){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return commandWord + " " + secondWord;
	}
	
	public String getCommandWord() { return commandWord; }
	public String getSecondWord() { return secondWord; }
}
