package model;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

import controller.*;
public class Model implements Scanner,Runnable{
	private InetAddress ipAddress = null;
	private ArrayList<Model> threads;
	private Integer port = null;
	private ArrayList<Integer> openPorts;
	private iController startScan;
	private int numberOfPorts;
	public Model(InetAddress ipAddress, int numberOfPorts, iController startScan) {
		this.ipAddress = ipAddress;
		this.startScan = startScan;
		this.numberOfPorts = numberOfPorts;
	}
	private Model(InetAddress ipAddress, Integer port,ArrayList<Integer> openPorts, iController startScan2) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.openPorts = openPorts;
		this.startScan = startScan2;
	}
	@Override
	public synchronized ArrayList<Integer> testOpenPorts() throws NullPointerException{
		openPorts = new ArrayList<>();
		if (ipAddress == null) {
			throw new NullPointerException();
		}
		threads = new ArrayList<>();
		for (int i = 1; i <= numberOfPorts; i++) {
			threads.add(new Model(ipAddress,i,openPorts,startScan));
		}
		ExecutorService threadsWithExecutor = Executors.newFixedThreadPool(5);
		doWorkLoad(threadsWithExecutor);
		return this.openPorts;
	}
	/**
	 * @param threadsWithExecutor
	 */
	private void doWorkLoad(ExecutorService threadsWithExecutor) {
		for (Iterator<Model> iterator = threads.iterator(); iterator.hasNext();) {
			Integer numberOfExecutions = 0;
			while (numberOfExecutions < 5 && iterator.hasNext()) {
				threadsWithExecutor.execute(iterator.next());
				numberOfExecutions++;
			}
			threadsWithExecutor.shutdown();
			try {
				threadsWithExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadsWithExecutor = Executors.newFixedThreadPool(5);
		}
		threadsWithExecutor.shutdown();
	}
	@Override
	public void run() {
		try {
			Socket testSocket = new Socket(ipAddress,port);
			//sendConsoleOutputOpenToGUI() laita toimimaan ei tarvitse
			//välttämättä arrayListiä!
			openPorts.add(port);
			startScan.sendConsoleOutputOpenToGUI("Port "+port+" is open\n");
		} catch (IOException e) {
			startScan.sendConsoleOutputClosedToGUI("Port "+port+" is closed\n");
			return;
		}
	}
}
