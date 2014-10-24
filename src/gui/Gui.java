package gui;

import java.awt.*;

import javax.swing.*;

import controller.*;
import net.miginfocom.swing.MigLayout;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.util.*;

public class Gui {

	private JFrame frmPortScanner;
	private JTextField txtIpToProbe;
	private iController controller;
	private Gui gui;
	private JScrollPane scrollPane;
	public JTextArea consoleOutput;
	private JScrollPane scrollPane_1;
	public JTextArea textArea;
	private JTextField txtNumberOfPorts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmPortScanner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.gui = this;
		frmPortScanner = new JFrame();
		frmPortScanner.setTitle("Port Scanner");
		frmPortScanner.setBounds(100, 100, 923, 438);
		frmPortScanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPortScanner.getContentPane().setLayout(new MigLayout("", "[:305.00:300.00,grow][grow][grow]", "[grow][grow]"));
		
		txtNumberOfPorts = new JTextField();
		txtNumberOfPorts.setText("Number of ports (1-65535)");
		frmPortScanner.getContentPane().add(txtNumberOfPorts, "flowx,cell 0 1,growx");
		txtNumberOfPorts.setColumns(10);
		JButton btnNewButton = new JButton("Probe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					InetAddress inetAddress = InetAddress.getByName(txtIpToProbe.getText());
					Thread controllerLocal = new Thread(new StartScan(inetAddress,Integer.parseInt(txtNumberOfPorts.getText()),gui));
					controllerLocal.start();
				} catch (UnknownHostException e) {
					//Hoida tämä loppuun! Exceptioni jos hostia ei ole olemassa! Mitä tapahtuu?
					//UnitTestin paikka
					e.printStackTrace();
				}
			}
		});
		frmPortScanner.getContentPane().add(btnNewButton, "cell 0 1 1 2");
		txtIpToProbe = new JTextField();
		txtIpToProbe.setText("IP to Probe");
		frmPortScanner.getContentPane().add(txtIpToProbe, "cell 0 0 1 2");
		txtIpToProbe.setColumns(22);
		scrollPane = new JScrollPane();
		frmPortScanner.getContentPane().add(scrollPane, "flowy,cell 2 0 1 2,grow");
		consoleOutput = new JTextArea();
		consoleOutput.setEditable(false);
		scrollPane.setViewportView(consoleOutput);
		scrollPane_1 = new JScrollPane();
		frmPortScanner.getContentPane().add(scrollPane_1, "cell 1 0 1 2,grow");
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
	}

}
