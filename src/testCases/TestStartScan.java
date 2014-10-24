package testCases;

import static org.junit.Assert.*;

import java.net.*;
import java.util.*;

import org.junit.*;

import controller.*;

public class TestStartScan {

	private iController startControllerWithNullIP;
	private iController startControllerWithLocalHostIP;
	@Before
	public void setUp() throws Exception {
		startControllerWithNullIP = new StartScan(null);
		startControllerWithLocalHostIP = new StartScan(InetAddress.getByName("localhost"));
	}

	@Test(expected=NullPointerException.class)
	public void testSendIPToModelNull() {
		startControllerWithNullIP.sendReferencesToModel();
	}

	@Test(expected=NullPointerException.class)
	public void testGetOutputFromModelNull() {
		startControllerWithNullIP.getOutputFromModel();
	}
	@Test
	public void testGetOutputFromModelLocalHost() {
		//Test if gets something
		startControllerWithLocalHostIP.sendReferencesToModel();
		ArrayList<Integer> testList = startControllerWithLocalHostIP.getOutputFromModel();
		if (testList == null) {
			fail("palautti nullin");
		}
	}

}
