package org.wollef.gameoflife.kernel;
import java.util.Random;

public class Grid {
		public int maGrille[][];
		public int maGrilleColors[][];

		Random myRandomGenerator = new Random();

		public Grid(int nRows, int nCols) {
			maGrille = new int[nRows][nCols];
			maGrilleColors = new int[nRows][nCols];
		}

		public void fillRandomly(int matrice) {
			// remplit la grille au hasard
			for (int rowx = 0; rowx < maGrille.length; rowx++) {
				for (int col = 0; col < maGrille[rowx].length; col++) {
					if (myRandomGenerator.nextInt(matrice)==0) {
						maGrille[rowx][col] = 1;
						maGrilleColors[rowx][col] = myRandomGenerator.nextInt(4);
					} else {
						maGrille[rowx][col] = 0;
					}
				}
			}
		}
		
		public void addPattern(int posX, int posY, int[][] aPattern, int aColor, boolean revertH, boolean revertV,
				int rotate) {
			int[][] thePattern = aPattern;

			if (rotate == -1) {
				thePattern = new int[aPattern[0].length][aPattern.length];
				for (int row = 0; row < aPattern.length; row++) {
					for (int col = 0; col < aPattern[0].length; col++) {
						thePattern[col][aPattern.length - 1 - row] = aPattern[row][col];
					}
				}
			} else if ((rotate == 1)) {
				thePattern = new int[aPattern[0].length][aPattern.length];
				for (int row = 0; row < aPattern.length; row++) {
					for (int col = 0; col < aPattern[0].length; col++) {
						thePattern[aPattern[0].length - 1 - col][row] = aPattern[row][col];
					}
				}
			} else if ((rotate == 2) || (rotate == -2)) {
				thePattern = new int[aPattern.length][aPattern[0].length];
				for (int row = 0; row < aPattern.length; row++) {
					for (int col = 0; col < aPattern[0].length; col++) {
						thePattern[aPattern.length - 1 - row][aPattern[0].length - 1 - col] = aPattern[row][col];
					}
				}
			}

			// remplit la grille au hasard
			for (int row = 0; row < thePattern.length; row++) {
				for (int col = 0; col < thePattern[0].length; col++) {
					int posDestX;
					int posDestY;

					if (revertV) {
						posDestY = posY + thePattern.length - row;
					} else {
						posDestY = posY + row;
					}

					if (revertH) {
						posDestX = posX + thePattern[0].length - col;
					} else {
						posDestX = posX + col;
					}

					maGrille[posDestY][posDestX] = thePattern[row][col];
					maGrilleColors[posDestY][posDestX] = aColor;
				}
			}
		}
	}