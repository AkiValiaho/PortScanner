package controller;

import gui.*;

import java.net.*;
import java.util.*;

import javax.swing.*;

import model.*;
import model.Scanner;

public class StartScan implements iController,Runnable{
	InetAddress thisAddress = null;
	Scanner thisScanner = null;
	private Gui gui;
	private ArrayList<Integer> openPorts;
	private int numberOfPorts;
	public StartScan(InetAddress ipToProbe,int numOfPorts, Gui gui) {
		thisAddress = ipToProbe;
		this.gui = gui;
		this.numberOfPorts = numOfPorts;
	}
	public StartScan(InetAddress ipToProbe) {
		thisAddress = ipToProbe;
	}
	@Override
	public void sendReferencesToModel() throws NullPointerException {
		if (thisAddress == null) {
			throw new NullPointerException();
		}
		thisScanner = new Model(thisAddress,numberOfPorts,this);
	}
	@Override
	public ArrayList<Integer> getOutputFromModel() throws NullPointerException {
		if (thisScanner == null) {
			throw new NullPointerException();
		}
		return thisScanner.testOpenPorts();
	}
	@Override
	public void sendConsoleOutputClosedToGUI(String consoleOutput) {
		gui.consoleOutput.append(consoleOutput);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendReferencesToModel();
		openPorts = getOutputFromModel();
	}
	@Override
	public void sendConsoleOutputOpenToGUI(String consoleOutput) {
		gui.textArea.append(consoleOutput);
	}
	
}
