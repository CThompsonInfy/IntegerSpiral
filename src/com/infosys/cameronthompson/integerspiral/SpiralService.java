package com.infosys.cameronthompson.integerspiral;

/**
 * Business Layer Service to contain the logic for the servlet
 * 
 * @author Cameron Thompson
 *
 */
public class SpiralService {
	
	public String createSpiral(String input, boolean isRight) {
		String spiral;
		SpiralWorker spiralWorker = new SpiralWorker(input, isRight);
		spiral = spiralWorker.toString();
		System.out.println(spiral);
		return spiral;
	}
	
}
