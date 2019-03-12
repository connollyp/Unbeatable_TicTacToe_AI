package fun;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	private int[][] board;

	public TicTacToe() {
		board = new int[3][3];
	}

	/**
	 * Populates the TicTacToe board matrix to its empty state
	 * 
	 * @param game
	 *            the given instance of TicTacToe
	 * @return none
	 */
	public static void initializeGame(TicTacToe game) {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				game.board[i][k] = 0;
			}
		}
	}

	/**
	 * Prints the TicTacToe board
	 * 
	 * @param game
	 *            the given instance of TicTacToe
	 * @return none
	 */
	public static void printBoard(TicTacToe game) {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if (game.board[i][k] == 0) {
					System.out.print("-  ");
				} else if (game.board[i][k] == 1) {
					System.out.print("x  ");
				} else if (game.board[i][k] == 2) {
					System.out.print("o  ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Prints the losing message to the console and exits the program
	 * 
	 * @param game
	 *            the given instance of TicTacToe
	 * @return none
	 */
	public static void printLoss(TicTacToe game) {
		printBoard(game);
		System.out.println();
		System.out.println("You lose!");
		System.exit(0);
	}

	/**
	 * Prints the tie message to the console and exits the program
	 * 
	 * @param game
	 *            the given instance of TicTacToe
	 * @return none
	 */
	public static void printTie(TicTacToe game) {
		printBoard(game);
		System.out.println();
		System.out.println("Tie!");
		System.exit(0);
	}

	/**
	 * Chooses which player will make the first move
	 * 
	 * @param none
	 * @return returns true if the player will go first, false if the AI will go
	 *         first
	 */
	public static boolean chooseFirstPlayer() {
		Random rand = new Random();

		int n = rand.nextInt(10) + 1;

		if (n < 5) {
			return true;
		}
		if (5 <= n) {
			return false;
		}
		return false;
	}

	/**
	 * Gets the row the player wishes to play in
	 * 
	 * @param game
	 *            the given instance of TicTacToe
	 * @return the row the player wishes to play in
	 */
	public static int getPlayerRow(TicTacToe game) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		int playerRow;

		System.out.println();
		System.out.print("What row would you like to select?: ");
		playerRow = input.nextInt();
		System.out.println();

		return playerRow;
	}

	/**
	 * Gets the column the player wishes to play in
	 * 
	 * @param game
	 *            the given instance of TicTacToe
	 * @return the column the player wishes to play in
	 */
	public static int getPlayerCol(TicTacToe game) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		int playerCol;

		System.out.print("What column would you like to select?: ");
		playerCol = input.nextInt();
		System.out.println();

		return playerCol;
	}

	/**
	 * Gets the player's next move
	 * 
	 * @param game
	 *            current instance of game
	 * @return The player move as an array
	 */
	public static int[] getPlayerMove(TicTacToe game) {
		int playerRow;

		int playerCol;

		int[] move = new int[2];

		do {
			playerRow = getPlayerRow(game) - 1;

			playerCol = getPlayerCol(game) - 1;

			if (game.board[playerRow][playerCol] != 0) {
				System.out.println("Invalid move");
			}
		} while (game.board[playerRow][playerCol] != 0);

		game.board[playerRow][playerCol] = 2;

		move[0] = playerRow;

		move[1] = playerCol;

		return move;

	}

	/**
	 * Determines whether the previous player move was in a corner
	 * 
	 * @param playerMove
	 *            the previous move made by the player as an array
	 * @return true if the previous move was in a corner, false otherwise
	 */
	public static boolean previousMoveInCorner(int[] playerMove) {
		if (playerMove[0] == playerMove[1] || (playerMove[0] == 0 && playerMove[1] == 2)
				|| (playerMove[0] == 2 && playerMove[1] == 0)) {
			return true;
		}
		return false;
	}

	public static void firstPlayerMoveInCenterBotFirst(TicTacToe game, int row, int column) {
		int botMove[] = new int[2];

		int playerMove[] = new int[2];

		if (row == 0) {
			if (column == 0) {
				game.board[2][2] = 1;
				botMove[0] = 2;
				botMove[1] = 2;
			} else if (column == 2) {
				game.board[2][0] = 1;
				botMove[0] = 2;
				botMove[1] = 0;
			}
		}
		if (row == 2) {
			if (column == 0) {
				game.board[0][2] = 1;
				botMove[0] = 0;
				botMove[1] = 2;
			} else if (column == 2) {
				game.board[0][0] = 1;
				botMove[0] = 0;
				botMove[1] = 0;
			}
		}

		printBoard(game);

		playerMove = getPlayerMove(game);

		if (previousMoveInCorner(playerMove) == true) {
			if (row == column) {
				if (game.board[0][2] != 0) {
					game.board[2][0] = 1;
					botMove[0] = 2;
					botMove[1] = 0;
				} else if (game.board[2][0] != 0) {
					game.board[0][2] = 1;
					botMove[0] = 0;
					botMove[1] = 2;
				}
			} else if (row != column) {
				if (game.board[0][0] != 0) {
					game.board[2][2] = 1;
					botMove[0] = 2;
					botMove[1] = 2;
				} else if (game.board[2][2] != 0) {
					game.board[0][0] = 1;
					botMove[0] = 0;
					botMove[1] = 0;
				}
			}
			printBoard(game);

			playerMove = getPlayerMove(game);

			System.out.println();

			if (botMove[0] == botMove[1]) {
				if (botMove[0] == 0) {
					if (game.board[0][1] == 0) {
						game.board[0][1] = 1;
						printLoss(game);
					} else if (game.board[1][0] == 0) {
						game.board[1][0] = 1;
						printLoss(game);
					}
				} else if (botMove[0] != 0) {
					if (game.board[1][2] == 0) {
						game.board[1][2] = 1;
						printLoss(game);
					} else if (game.board[2][1] == 0) {
						game.board[2][1] = 1;
						printLoss(game);
					}
				}
			} else if (botMove[0] != botMove[1]) {
				if (botMove[0] == 0) {
					if (game.board[0][1] == 0) {
						game.board[0][1] = 1;
						printLoss(game);
					} else if (game.board[1][2] == 0) {
						game.board[1][2] = 1;
						printLoss(game);
					}
				} else if (botMove[0] != 0) {
					if (game.board[1][0] == 0) {
						game.board[1][0] = 1;
						printLoss(game);
					} else if (game.board[2][1] == 0) {
						game.board[2][1] = 1;
						printLoss(game);
					}
				}
			}
		} else if (previousMoveInCorner(playerMove) == false) {
			if (playerMove[0] == 0) {
				game.board[2][1] = 1;
				botMove[0] = 2;
				botMove[1] = 1;

				printBoard(game);

				playerMove = getPlayerMove(game);

				if (previousMoveInCorner(playerMove) == true) {
					if (row == column) {
						if (playerMove[0] == 0) {
							game.board[2][0] = 1;
							printLoss(game);

						} else if (playerMove[0] == 2) {
							game.board[0][2] = 1;
							botMove[0] = 0;
							botMove[1] = 2;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] == 2) {
								game.board[1][0] = 1;
								printTie(game);
							} else if (playerMove[1] == 0) {
								game.board[1][2] = 1;
								printLoss(game);
							}
						}
					} else if (row != column) {
						if (playerMove[0] == 0) {
							game.board[2][2] = 1;
							printLoss(game);
						} else if (playerMove[0] == 2) {
							game.board[0][0] = 1;
							botMove[0] = 0;
							botMove[1] = 0;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] == 0) {
								game.board[1][2] = 1;
								printTie(game);
							} else if (playerMove[1] == 2) {
								game.board[1][0] = 1;
								printLoss(game);
							}
						}
					}
				} else if (previousMoveInCorner(playerMove) == false) {
					if (row == column) {
						game.board[2][0] = 1;
						printLoss(game);
					} else if (row != column) {
						game.board[2][2] = 1;
						printLoss(game);
					}

				}

			} else if (playerMove[0] == 1) {
				if (playerMove[1] == 0) {
					game.board[1][2] = 1;
					botMove[0] = 1;
					botMove[1] = 2;

					printBoard(game);

					playerMove = getPlayerMove(game);

					if (previousMoveInCorner(playerMove) == true) {
						if (row == column) {
							if (playerMove[0] == 2) {
								game.board[0][2] = 1;
								printLoss(game);
							} else if (playerMove[0] == 0) {
								game.board[2][0] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 0) {
									game.board[2][1] = 1;
									printLoss(game);
								} else if (playerMove[0] == 2) {
									game.board[0][1] = 1;
									printTie(game);
								}
							}
						} else if (row != column) {
							if (playerMove[0] == 0) {
								game.board[2][2] = 1;
								printLoss(game);

							} else if (playerMove[0] == 2) {
								game.board[0][0] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 0) {
									game.board[2][1] = 1;
									printTie(game);
								} else if (playerMove[0] == 2) {
									game.board[0][1] = 1;
									printLoss(game);
								}
							}
						}
					} else if (previousMoveInCorner(playerMove) == false) {
						if (row == column) {
							game.board[0][2] = 1;
							printLoss(game);
						} else if (row != column) {
							game.board[2][2] = 1;
							printLoss(game);
						}
					}

				} else if (playerMove[1] == 2) {
					game.board[1][0] = 1;
					botMove[0] = 1;
					botMove[1] = 0;

					printBoard(game);

					playerMove = getPlayerMove(game);

					if (previousMoveInCorner(playerMove) == true) {
						if (row == column) {
							if (playerMove[0] == 2) {
								game.board[0][2] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 0) {
									game.board[2][1] = 1;

									printTie(game);
								} else if (playerMove[0] == 2) {
									game.board[0][1] = 1;

									printLoss(game);
								}

							} else if (playerMove[0] == 0) {
								game.board[2][0] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 0) {
									game.board[2][1] = 1;
									printLoss(game);
								} else if (playerMove[0] == 2) {
									game.board[0][1] = 1;
									printTie(game);
								}
							}
						} else if (row != column) {
							if (playerMove[0] == 2) {
								game.board[0][0] = 1;
								printLoss(game);
							} else if (playerMove[0] == 0) {
								game.board[2][2] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 0) {
									game.board[2][1] = 1;
									printLoss(game);
								} else if (playerMove[0] == 2) {
									game.board[0][1] = 1;
									printTie(game);
								}

							}
						}
					} else if (previousMoveInCorner(playerMove) == false) {
						if (row == column) {
							game.board[2][0] = 1;
							printLoss(game);
						} else if (row != column) {
							game.board[0][0] = 1;
							printLoss(game);
						}
					}

				}
			} else if (playerMove[0] == 2) {
				game.board[0][1] = 1;
				botMove[0] = 0;
				botMove[1] = 1;

				printBoard(game);

				playerMove = getPlayerMove(game);

				if (previousMoveInCorner(playerMove) == true) {
					if (row == column) {
						if (playerMove[0] == 2) {
							game.board[0][2] = 1;
							printLoss(game);
						} else if (playerMove[0] == 0) {
							game.board[2][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] == 0) {
								game.board[1][2] = 1;

								printTie(game);
							} else if (playerMove[1] == 2) {
								game.board[1][0] = 2;
								printLoss(game);
							}
						}
					} else if (row != column) {
						if (playerMove[0] == 2) {
							game.board[0][0] = 1;
							printLoss(game);
						} else if (playerMove[0] == 0) {
							game.board[2][2] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] == 0) {
								game.board[1][2] = 1;
								printLoss(game);
							} else if (playerMove[1] == 2) {
								game.board[1][0] = 1;
								printTie(game);
							}
						}
					}
				} else if (previousMoveInCorner(playerMove) == false) {
					if (row == column) {
						game.board[0][2] = 1;
						printLoss(game);
					} else if (row != column) {
						game.board[0][0] = 1;
						printLoss(game);
					}
				}
			}
		}
	}

	public static void firstPlayerMoveInCornerBotFirst(TicTacToe game, int row, int column, int[] playerMove) {
		Random rand = new Random();

		int moveDecision;

		if (row == column) {
			if (row == 0) {
				if (playerMove[0] == 0) {
					moveDecision = rand.nextInt(2) + 1;

					if (moveDecision == 1) {
						game.board[2][0] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[0] == 1 && playerMove[1] == 0) {
							game.board[2][2] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] == playerMove[1]) {
								game.board[2][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 2) {
								game.board[1][1] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[1][1] = 1;

									printLoss(game);
								} else if (moveDecision == 2) {
									game.board[2][1] = 1;

									printLoss(game);
								}
							}

						} else if (game.board[1][0] == 0) {
							game.board[1][0] = 1;
							printLoss(game);
						}
					} else if (moveDecision == 2) {
						game.board[2][2] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[0] != playerMove[1]) {
							game.board[1][1] = 1;

							printLoss(game);
						} else if (playerMove[0] == playerMove[1]) {
							game.board[2][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] == 0) {
								game.board[2][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 2) {
								game.board[1][0] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[1][0] = 1;

									printLoss(game);
								} else if (moveDecision == 2) {
									game.board[2][1] = 1;

									printLoss(game);
								}
							}
						}
					}
				} else if (playerMove[0] == 2) {
					if (playerMove[1] == 0) {
						game.board[2][2] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[0] == playerMove[1]) {
							game.board[0][2] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] == 0) {
								game.board[1][2] = 1;

								printLoss(game);
							} else if (playerMove[1] == 2) {
								game.board[0][1] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[0][1] = 1;

									printLoss(game);
								} else if (moveDecision == 2) {
									game.board[1][2] = 1;

									printLoss(game);
								}
							}
						} else if (playerMove[0] != playerMove[1]) {
							game.board[1][1] = 1;

							printLoss(game);
						}
					} else if (playerMove[1] == 2) {
						moveDecision = rand.nextInt(2) + 1;

						if (moveDecision == 1) {
							game.board[0][2] = 1;

							playerMove = getPlayerMove(game);

							if (playerMove[0] != 0) {
								game.board[0][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 0) {
								game.board[2][0] = 1;

								playerMove = getPlayerMove(game);

								if (playerMove[1] == 0) {
									game.board[1][1] = 1;

									printLoss(game);
								} else if (playerMove[0] == playerMove[1]) {
									game.board[1][0] = 1;

									printLoss(game);
								} else {
									moveDecision = rand.nextInt(2) + 1;

									if (moveDecision == 1) {
										game.board[1][0] = 1;

										printLoss(game);
									} else if (moveDecision == 2) {
										game.board[1][1] = 1;

										printLoss(game);
									}
								}
							}
						} else if (moveDecision == 2) {
							game.board[2][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] != 0) {
								game.board[1][0] = 1;

								printLoss(game);
							} else if (playerMove[1] == 0) {
								game.board[0][2] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 0) {
									game.board[1][1] = 1;

									printLoss(game);
								} else if (playerMove[0] == playerMove[1]) {
									game.board[0][1] = 1;

									printLoss(game);
								} else {
									moveDecision = rand.nextInt(2) + 1;

									if (moveDecision == 1) {
										game.board[0][1] = 1;

										printLoss(game);
									} else if (moveDecision == 2) {
										game.board[1][1] = 1;

										printLoss(game);
									}
								}
							}
						}
					}
				}
			} else if (row == 2) { // AI first move bottom right
				if (playerMove[0] == 2) {

					moveDecision = rand.nextInt(2) + 1;

					if (moveDecision == 1) {
						game.board[0][0] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[0] != playerMove[1]) {
							game.board[1][1] = 1;

							printLoss(game);
						} else if (playerMove[0] == playerMove[1]) {
							game.board[0][2] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] == 0) {
								game.board[1][2] = 1;

								printLoss(game);
							} else if (playerMove[1] == 2) {
								game.board[0][1] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[0][1] = 1;

									printLoss(game);
								} else if (moveDecision == 1) {
									game.board[1][2] = 1;

									printLoss(game);
								}
							}
						}

					} else if (moveDecision == 2) {
						game.board[0][2] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[1] != 2) {
							game.board[1][2] = 1;

							printLoss(game);
						} else if (playerMove[1] != 2) {
							game.board[0][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] == 0) {
								game.board[1][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == playerMove[1]) {
								game.board[0][1] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[0][1] = 1;

									printLoss(game);
								} else if (moveDecision == 2) {
									game.board[1][1] = 1;

									printLoss(game);
								}
							}
						}
					}
				} else if (playerMove[0] == 0) {
					if (playerMove[1] == 0) {

						moveDecision = rand.nextInt(2) + 1;

						if (moveDecision == 1) {
							game.board[0][2] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] != 2) {
								game.board[1][2] = 1;

								printLoss(game);
							} else if (playerMove[1] == 2) {
								game.board[2][0] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == playerMove[1]) {
									game.board[2][1] = 1;

									printLoss(game);
								} else if (playerMove[0] == 2) {
									game.board[1][1] = 1;

									printLoss(game);
								} else {
									moveDecision = rand.nextInt(2) + 1;

									if (moveDecision == 1) {
										game.board[1][1] = 1;

										printBoard(game);
									} else if (moveDecision == 2) {
										game.board[2][1] = 1;

										printBoard(game);
									}
								}
							}

						} else if (moveDecision == 2) {
							game.board[2][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] != 2) {
								game.board[2][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 2) {
								game.board[0][2] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == playerMove[1]) {
									game.board[1][2] = 1;

									printLoss(game);
								} else if (playerMove[1] == 2) {
									game.board[1][1] = 1;

									printLoss(game);
								} else {
									moveDecision = rand.nextInt(2) + 1;

									if (moveDecision == 1) {
										game.board[1][1] = 1;

										printLoss(game);
									} else if (moveDecision == 2) {
										game.board[1][2] = 1;

										printLoss(game);
									}
								}
							}
						}
					} else if (playerMove[1] == 2) {
						moveDecision = rand.nextInt(2) + 1;

						if (moveDecision == 1) {
							game.board[0][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] != playerMove[1]) {
								game.board[1][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == playerMove[1]) {
								game.board[2][1] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[0] == 2) {
									game.board[1][0] = 1;

									printLoss(game);
								} else if (playerMove[1] == 0) {
									game.board[2][1] = 1;

									printLoss(game);
								} else {
									moveDecision = rand.nextInt(2) + 1;

									if (moveDecision == 1) {
										game.board[1][0] = 1;

										printLoss(game);
									} else if (moveDecision == 2) {
										game.board[2][1] = 1;

										printLoss(game);
									}
								}
							}
						} else if (moveDecision == 2) {
							game.board[2][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] != 2) {
								game.board[2][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 2) {
								game.board[0][0] = 1;

								printBoard(game);

								playerMove = getPlayerMove(game);

								if (playerMove[1] == 0) {
									game.board[1][1] = 1;

									printLoss(game);
								} else if (playerMove[0] == playerMove[1]) {
									game.board[1][0] = 1;

									printLoss(game);
								} else {
									moveDecision = rand.nextInt(2) + 1;

									if (moveDecision == 1) {
										game.board[1][0] = 1;

										printLoss(game);
									} else if (moveDecision == 2) {
										game.board[1][1] = 1;

										printLoss(game);
									}
								}
							}
						}
					}
				}
			}
		} else if (row != column) {
			if (row == 0) {
				if (playerMove[0] == 0) {
					moveDecision = rand.nextInt(2) + 1;

					if (moveDecision == 1) {
						game.board[2][0] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[0] != playerMove[1]) {
							game.board[1][1] = 1;

							printLoss(game);
						} else if (playerMove[0] == playerMove[1]) {
							game.board[2][2] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[1] == 2) {
								game.board[2][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 1) {
								game.board[1][2] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[1][2] = 1;

									printLoss(game);
								} else if (moveDecision == 2) {
									game.board[2][1] = 1;

									printLoss(game);
								}
							}
						}
					} else if (moveDecision == 2) {
						game.board[2][2] = 1;

						printBoard(game);

						playerMove = getPlayerMove(game);

						if (playerMove[1] != 2) {
							game.board[1][2] = 1;

							printLoss(game);
						} else if (playerMove[1] == 2) {
							game.board[2][0] = 1;

							printBoard(game);

							playerMove = getPlayerMove(game);

							if (playerMove[0] == playerMove[1]) {
								game.board[2][1] = 1;

								printLoss(game);
							} else if (playerMove[0] == 2) {
								game.board[1][1] = 1;

								printLoss(game);
							} else {
								moveDecision = rand.nextInt(2) + 1;

								if (moveDecision == 1) {
									game.board[1][1] = 1;

									printLoss(game);
								} else if (moveDecision == 2) {
									game.board[2][1] = 1;

									printLoss(game);
								}
							}
						}
					}
				} // playerMove on bottom row
			} // AI move on bottom row
		}

	}

	public static void botFirst(TicTacToe game) {
		Random rand = new Random();

		int row = rand.nextInt(2) + 1;

		int column = rand.nextInt(2) + 1;

		int playerMove[];

		if (row == 1) {
			if (column == 1) {
				game.board[0][0] = 1;
				row = 0;
				column = 0;
			} else if (column == 2) {
				game.board[0][2] = 1;
				row = 0;
				column = 2;
			}
		}
		if (row == 2) {
			if (column == 1) {
				game.board[2][0] = 1;
				row = 2;
				column = 0;
			} else if (column == 2) {
				game.board[2][2] = 1;
				row = 2;
				column = 2;
			}
		}

		printBoard(game);

		playerMove = getPlayerMove(game);

		if (game.board[1][1] != 0) {

			firstPlayerMoveInCenterBotFirst(game, row, column);

		} else if (game.board[1][1] == 0) {
			if (previousMoveInCorner(playerMove) == true) {

				firstPlayerMoveInCornerBotFirst(game, row, column, playerMove);
			} // first player move not in corner
		}

	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();

		initializeGame(game);

		botFirst(game);

	}

}
