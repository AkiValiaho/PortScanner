package testCases;

import static org.junit.Assert.*;

import java.net.*;

import model.*;

import org.junit.*;
public class TestForOpenPorts {
	private Scanner tester;
	private Scanner testerWithIPLocalHost;
	@Before
	public void setUp() throws Exception {
		tester = new Model(null);
		testerWithIPLocalHost = new Model(InetAddress.getByName("127.0.0.1"));
	}
	@Test(expected=NullPointerException.class)
	public void testTestOpenPortsNullIP() {
		tester.testOpenPorts();
	}
	@Test
	public void testTestOpenPortsLocalHOst() {
		testerWithIPLocalHost.testOpenPorts();
	}
}
