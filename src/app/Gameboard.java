package app;

public class Gameboard {
	private int columns;
	private int rows;
	private int nrships;
	private int nrofshipparts;
	private static char[][] board;
	public final Ship[] myShips = new Ship[10];

	public Gameboard(int width, int height, int numberofships) {
		this.columns = width;
		this.rows = height;
		this.nrships = numberofships;

		generateBoard();
		generateShipPositions();
	}
	
	/* Text display of the GameBoard
	* On Console/Terminal using System.out.print's
	*/
	public void showBoard() {
		System.out.println();
		System.out.print("    ");
		for(int i = 0; i < board.length; i++){
		System.out.print(i+ " ");
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print(Character.toString((char)('A'+i))+ "   ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private void generateShipPositions() {

		for (int i = 0; i < this.nrships; i++) {
			int x = generateX();
			int y = generateY();
			int d = generateDirection();
			int l = generateLength();

			boolean free = checkIfFree(x, y, d, l);
			if (free) {
				this.nrofshipparts += l;
				Ship ship = new Ship(x, y, d, l, (char) ('A' + i));
				myShips[i] = ship;
			} else {
				i--;
			}
		}

	}

	/* Sets a ship on a certain spot facing a specified direction that was generated on generateShipPositions()
	* @param x : x coord
	* @param y : y coord
	* @param direction : direction ship is facing 1-up 2-right 3-down 4-left
	* @param length : length of the ship
	* @param c : Character id of the ship (ex: Ship 'A')
	*/
	public static void setShips(int x, int y, int direction, int length, char c) {

		for (int i = 0; i < length; i++) {
			if (direction == 1) {
				board[x - i][y] = c;
			} else if (direction == 2) {
				board[x][y + i] = c;
			} else if (direction == 3) {
				board[x + i][y] = c;
			} else if (direction == 4) {
				board[x][y - i] = c;
			}
		}
	}

	private boolean checkIfFree(int x, int y, int d, int l) {

		try {
			for (int i = 0; i < l; i++) {
				if (d == 1) {
					if (board[x - i][y] != '0') {
						return false;
					}
				} else if (d == 2) {
					if (board[x][y + i] != '0') {
						return false;
					}
				} else if (d == 3) {
					if (board[x + i][y] != '0') {
						return false;
					}
				} else if (d == 4) {
					if (board[x][y - i] != '0') {
						return false;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	// 3-6 length
	private int generateLength() {
		return (int) (Math.random() * 3) + 3;
	}

	// 1-up 2-right 3-down 4-left
	private int generateDirection() {
		return (int) (Math.random() * 4) + 1;
	}

	// 0-9 posY
	private int generateY() {
		return (int) (Math.random() * this.rows);
	}

	// 0-9 posX
	private int generateX() {
		return (int) (Math.random() * this.columns);
	}

	private void generateBoard() {
		board = new char[this.columns][this.rows];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '0';

			}
		}

	}
	/* Getter of the board (GameBoard char[][] matrix)
	* returns board
	*/
	public char[][] getBoard(){
		return board;
	}

	/* Getter of the Ship Length
	* returns nrofshipparts
	*/
	public int getNrShipParts() {
		return this.nrofshipparts;
	}

	// Conditionals for ships hit. Could be more efficient...

	public void hitShip(int row, int column) {
		if(this.board[row][column] == 'A'){
			myShips[0].gotShot();
		}else if(this.board[row][column] == 'B'){
			myShips[1].gotShot();
		}else if(this.board[row][column] == 'C'){
			myShips[2].gotShot();
		}else 
		this.board[row][column] = 'X';
		this.nrofshipparts--;
		
		
		
	}


}
