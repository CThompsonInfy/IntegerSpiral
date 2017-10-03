package com.infosys.cameron_thompson.integer_spiral;

public class SpiralService {
	
	public static final String ERR_MSG_NULL = "Target value cannot be null";
	public static final String ERR_MSG_EMPTY = "Target value is required";
	public static final String ERR_MSG_NOT_INTEGER = "Target value must be an integer";
	public static final String ERR_MSG_NEGATIVE = "Target value must be a positive integer";
	
	private enum CursorDir {
		NONE,
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	public String createSpiral(String input, boolean isRight) {
		String spiralVal;
		int targetVal = validateAndParseInput(input);
		
		//find size of spiral
		int spiralSize = (int)Math.ceil(Math.sqrt(targetVal + 1)); // ceil returns integers
		String[][] grid = new String[spiralSize][spiralSize];
		int[] colWidth = new int[spiralSize];
		
		//find center /location of 0
		int cursorX = (int)Math.ceil(spiralSize / 2.0)-1; //find middle, or left of middle (if even)
		int cursorY = cursorX;
		if (spiralSize%2 == 0 && !isRight) {//if even and spiraling to the left
			cursorX += 1; //right of middle
		}
		
		CursorDir cursorDir = CursorDir.NONE;
		for (int i = 0; i<=targetVal; i++) {
			
			//Move Cursor
			//and change direction if necessary
			switch (cursorDir) { //based on the last direction
				case UP:
					cursorY -= 1;
					if (cursorY < 0) {
						//index now out of bounds, ignore next direction
					} else if (isRight && grid[cursorX+1][cursorY] == null) {
						cursorDir = CursorDir.RIGHT;
					} else if (!isRight && grid[cursorX-1][cursorY] == null) {
						cursorDir = CursorDir.LEFT;
					}
					break;
				case DOWN:
					cursorY += 1;
					if (cursorY > spiralSize) {
						//index now out of bounds, ignore next direction
					} else if (isRight && grid[cursorX-1][cursorY] == null) {
						cursorDir = CursorDir.LEFT;
					} else if (!isRight && grid[cursorX+1][cursorY] == null) {
						cursorDir = CursorDir.RIGHT;
					}
					break;
				case LEFT:
					cursorX -= 1;
					if (cursorX < 0) {
						//index now out of bounds, ignore next direction
					} else if (isRight && grid[cursorX][cursorY-1] == null) {
						cursorDir = CursorDir.UP;
					} else if (!isRight && grid[cursorX][cursorY+1] == null) {
						cursorDir = CursorDir.DOWN;
					}
					break;
				case RIGHT:
					cursorX += 1;
					if (cursorX > spiralSize) {
						//index now out of bounds, ignore next direction
					} else if (isRight && grid[cursorX][cursorY+1] == null) {
						cursorDir = CursorDir.DOWN;
					} else if (!isRight && grid[cursorX][cursorY-1] == null) {
						cursorDir = CursorDir.UP;
					}
					break;
				case NONE:
					//no movement
					//always change direction
					if (isRight) {
						cursorDir = CursorDir.RIGHT;
					} else {
						cursorDir = CursorDir.LEFT;
					}
					break;
			}
			
			//insert integers
			grid[cursorX][cursorY] = Integer.toString(i);
			colWidth[cursorX] = grid[cursorX][cursorY].length();
		}
		
		StringBuilder spiralBuilder = new StringBuilder();
		for (int iY = 0; iY < spiralSize; iY++) { //rows
			for (int iX = 0; iX < spiralSize; iX++) { //columns
				String cell = grid[iX][iY] != null ? grid[iX][iY] : "";
				for (int i=0; i<colWidth[iX]-cell.length()+1;i++) {
					spiralBuilder.append(" ");
				}
				spiralBuilder.append(cell);
			}
			spiralBuilder.append("\r\n");
		}
		
		
		spiralVal = spiralBuilder.toString();
		
		//spiralVal = "123\r\n456\r\n789";
		return spiralVal;
	}
	
	private int validateAndParseInput(String input) {
		int inputVal;

		if (input == null) {
			throw new IllegalArgumentException(ERR_MSG_NULL);
		}
		
		String trimmedInput = input.trim();
		
		if (trimmedInput.length() == 0) {
			throw new IllegalArgumentException(ERR_MSG_EMPTY);
		}
		
		try {
			inputVal = Integer.parseInt(trimmedInput); 
		} catch(NumberFormatException ex) {
			throw new IllegalArgumentException(ERR_MSG_NOT_INTEGER, ex);
		} catch (Exception ex) {
			throw ex;
		}
		
		if (inputVal < 0) {
			throw new IllegalArgumentException(ERR_MSG_NEGATIVE);
		}
		
		return inputVal;
	}
}
