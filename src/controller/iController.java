package controller;

import java.util.*;

public interface iController {
	public void sendReferencesToModel() throws NullPointerException;
	public ArrayList<Integer> getOutputFromModel() throws NullPointerException;
	public void sendConsoleOutputClosedToGUI(String consoleOutput);
	public void sendConsoleOutputOpenToGUI(String consoleOutput);
}
