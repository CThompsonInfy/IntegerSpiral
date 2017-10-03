package com.infosys.cameronthompson.integerspiral;

/**
 * Object that can make an integer spiral String
 * 
 * @author Cameron Thompson
 *
 */
public class SpiralWorker {
	
	private enum CursorDir {
		NONE,
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	private boolean isRight;
	private int targetValue;
	private String spiral;
	
	public SpiralWorker (String targetValue, boolean isRight) {
		this.isRight = isRight;
		this.targetValue = validateAndParseInput(targetValue);
	}
	
	private void createSpiral() {
		
		//find size of spiral
		int spiralSize = (int)Math.ceil(Math.sqrt(targetValue + 1)); // ceil returns integers
		String[][] grid = new String[spiralSize][spiralSize];
		int[] colWidth = new int[spiralSize];
		
		//find center /location of 0
		int cursorX = (int)Math.ceil(spiralSize / 2.0)-1; //find middle, or left of middle (if even)
		int cursorY = cursorX;
		if (spiralSize%2 == 0 && !isRight) {//if even and spiraling to the left
			cursorX += 1; //right of middle
		}
		
		CursorDir cursorDir = CursorDir.NONE;
		for (int i = 0; i<=targetValue; i++) {
			
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
				int colPadding = 1; //padding between columns
				if (iX == 0) { //prevent padding before first column
					colPadding = 0;
				}
				String cell = grid[iX][iY] != null ? grid[iX][iY] : "";
				for (int i=0; i<colWidth[iX]-cell.length()+colPadding;i++) {
					spiralBuilder.append(" ");
				}
				spiralBuilder.append(cell);
			}
			spiralBuilder.append("\r\n");
		}
		
		
		spiral = spiralBuilder.toString();
	}
	
	private int validateAndParseInput(String targetValueInput) {
		int parsedTargetValue;
		if (targetValueInput == null) {
			throw new IllegalArgumentException(Constants.ERR_MSG_NULL);
		}
		
		String trimmedInput = targetValueInput.trim();
		
		if (trimmedInput.length() == 0) {
			throw new IllegalArgumentException(Constants.ERR_MSG_EMPTY);
		}
		
		try {
			parsedTargetValue = Integer.parseInt(trimmedInput); 
		} catch(NumberFormatException ex) {
			throw new IllegalArgumentException(Constants.ERR_MSG_NOT_INTEGER, ex);
		} catch (Exception ex) {
			throw ex;
		}
		
		if (parsedTargetValue < 0) {
			throw new IllegalArgumentException(Constants.ERR_MSG_NEGATIVE);
		}
		return parsedTargetValue;
	}
	
	@Override
	public String toString() {
		if (spiral == null) {
			createSpiral();
		}
		return spiral;
	}
}
