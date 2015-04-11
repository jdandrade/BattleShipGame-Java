package app;

import java.util.Scanner;

public class BattleShips {
	public static final int WIDTH = 10, HEIGHT = 10, NUMBEROFSHIPS = 3;
	private static char[][] hiddenboard;

	private int nrofplays;

	public static void main(String[] args) {
		new BattleShips();
	}

	private boolean gameEnd;
	Scanner s = new Scanner(System.in);

	public BattleShips() {

		System.out.println(">>> BATTLESHIP GAME by jdandrade <<<");
		Gameboard gboard = new Gameboard(WIDTH, HEIGHT, NUMBEROFSHIPS);
		generateHiddenBoard();

		//Ciclo do jogo, quando saír do ciclo é porque o jogo acabou

		while (!gameEnd) {
			showHiddenBoard();
			boolean wrongInput = true;
			String input = null;

			while (wrongInput) {
				System.out.print("Insert play [A-Z][0-9] (no spaces): ");
				input = s.nextLine();
				if (input.length() == 2 && input.charAt(0) >= 'a'
						&& input.charAt(0) <= ('a' + this.WIDTH)
						&& input.charAt(1) >= '0' && input.charAt(1) <= '9') {
					wrongInput = false;
				}
				
				if(wrongInput)
					System.out.println("Input is wrong.");
			}
			char rowc = input.charAt(0);
			int column = Character.getNumericValue(input.charAt(1));
			int row = Character.getNumericValue(rowc) - 10;

			if (gboard.getBoard()[row][column] != '0') {
				gboard.hitShip(row, column);
				System.out.println("HIT");
				for (int i = 0; i < NUMBEROFSHIPS; i++) {
					if ((gboard.getBoard()[row][column] == (char) ('A' + i) && !(gboard.myShips[i]
							.isAlive()))) {
						System.out.println(">> SHIP DOWN!");
						break;
					}
				}

			}

			hiddenboard[row][column] = gboard.getBoard()[row][column];
			nrofplays++;
			if (gboard.getNrShipParts() == 0)
				gameEnd = true;

		}
		System.out.println("CONGRATULATIONS, YOU WON IN: " + nrofplays
				+ " PLAYS!");
		gboard.showBoard();
	}

	private void showHiddenBoard() {
		System.out.println();
		System.out.print("    ");
		for (int i = 0; i < hiddenboard.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < hiddenboard.length; i++) {
			System.out.print(Character.toString((char) ('A' + i)) + "   ");
			for (int j = 0; j < hiddenboard[i].length; j++) {
				System.out.print(hiddenboard[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	private void generateHiddenBoard() {
		hiddenboard = new char[this.WIDTH][this.HEIGHT];

		for (int i = 0; i < hiddenboard.length; i++) {
			for (int j = 0; j < hiddenboard[i].length; j++) {
				hiddenboard[i][j] = '-';

			}
		}

	}
}
