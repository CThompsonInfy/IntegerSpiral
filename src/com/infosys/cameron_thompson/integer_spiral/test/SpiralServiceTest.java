package com.infosys.cameron_thompson.integer_spiral.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.infosys.cameron_thompson.integer_spiral.SpiralService;

import junit.framework.Assert;

public class SpiralServiceTest {
	private SpiralService spiralService;

	@Before
	public void setUp() throws Exception {
		spiralService = new SpiralService();
	}

	@Test
	public void createSpiralTest_Success_SimpleRight() {
		String expected =
				" 6 7 8\r\n" +
				" 5 0 1\r\n" +
				" 4 3 2\r\n";
		
		String result = spiralService.createSpiral("8", true);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Success_SimpleLeft() {
		String expected = 
				" 8 7 6\r\n" + 
				" 1 0 5\r\n" + 
				" 2 3 4\r\n";
		
		String result = spiralService.createSpiral("8", false);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Fail_Null() {
		String errorMessage = null;
		
		try {
			spiralService.createSpiral(null, true);
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}
		
		assertNotNull("Did not throw an exception", errorMessage);
		assertEquals("Exception did not contain the expected message", SpiralService.ERR_MSG_NULL, errorMessage);
	}

	@Test
	public void createSpiralTest_Fail_Empty() {
		String errorMessage = null;
		
		try {
			spiralService.createSpiral("", true);
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}

		assertNotNull("Did not throw an exception", errorMessage);
		assertEquals("Exception did not contain the expected message", SpiralService.ERR_MSG_EMPTY, errorMessage);
	}

	@Test
	public void createSpiralTest_Fail_Negative() {
		String errorMessage = null;
		
		try {
			spiralService.createSpiral("-8", true);
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}

		assertNotNull("Did not throw an exception", errorMessage);
		assertEquals("Exception did not contain the expected message", SpiralService.ERR_MSG_NEGATIVE, errorMessage);
	}

	@Test
	public void createSpiralTest_Fail_NonInteger() {
		String errorMessage = null;
		
		try {
			spiralService.createSpiral("8c", true);
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}

		assertNotNull("Did not throw an exception", errorMessage);
		assertEquals("Exception did not contain the expected message", SpiralService.ERR_MSG_NOT_INTEGER, errorMessage);
	}

	@Test
	public void createSpiralTest_Success_SmallRight() {
		String expected = 
				" 0 1\r\n" + 
				" 3 2\r\n";
		
		String result = spiralService.createSpiral("3", true);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Success_SmallLeft() {
		String expected = 
				" 1 0\r\n" + 
				" 2 3\r\n";
		
		String result = spiralService.createSpiral("3", false);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Success_Smaller() {
		String expected = 
				" 0 1\r\n" + 
				"   2\r\n";
		
		String result = spiralService.createSpiral("2", true);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Success_EvenSmaller() {
		String expected = 
				" 0 1\r\n" + 
				"    \r\n";
		
		String result = spiralService.createSpiral("1", true);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Success_Smallest() {
		String expected = 
				" 0\r\n";
		
		String result = spiralService.createSpiral("0", true);
		
		assertEquals(expected, result);
	}

	@Test
	public void createSpiralTest_Success_UnevenColumns() {
		String expected = 
				"               \r\n" + 
				" 19  6  7  8  9\r\n" + 
				" 18  5  0  1 10\r\n" + 
				" 17  4  3  2 11\r\n" + 
				" 16 15 14 13 12\r\n";
		
		String result = spiralService.createSpiral("19", true);
		
		assertEquals(expected, result);
	}
	 

}
