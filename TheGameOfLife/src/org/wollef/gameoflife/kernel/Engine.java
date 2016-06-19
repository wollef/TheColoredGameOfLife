package org.wollef.gameoflife.kernel;
import java.util.Random;

public class Engine {
	static int myCountCols;
	static int myCountRows;
	
	static int myMutationRate;

	public Grid myCurrentGrid;
	public Grid myPreviousGrid;
	public Grid myAnteriorGrid;
	
	Random myRandomGenerator = new Random();

	public Engine(int l, int h) {
		myCountCols = l;
		myCountRows = h;
	
		myAnteriorGrid 	= new Grid(myCountRows, myCountCols);
		myPreviousGrid 	= new Grid(myCountRows, myCountCols);
		myCurrentGrid	= new Grid(myCountRows, myCountCols);
	}
	
	public void fillRandomly(int matrice) {
		myCurrentGrid.fillRandomly(matrice);
	}
	
	public void addPattern(int posX, int posY, int[][] aPattern, int aColor, boolean revertH, boolean revertV, int rotate) {
		myCurrentGrid.addPattern( posX,  posY, aPattern,  aColor,  revertH,  revertV,  rotate);
	}

	public void setMutationRate(int mr) {
		myMutationRate = mr;
	}
	
	public Grid applyMutations(Grid grille1) {
		Grid grille2 = new Grid(grille1.maGrille.length, grille1.maGrille[0].length);

		// applique mutation
		// calcul des vivantes mortes
		for (int row = 0; row < grille1.maGrille.length; row++) {

			// je comptre le nombre de vivantes autours
			for (int col = 0; col < grille1.maGrille[row].length; col++) {
				int rnd = myRandomGenerator.nextInt(myMutationRate);
				if (rnd == 0) {
					if (grille1.maGrille[row][col] == 1) {
						grille2.maGrille[row][col] = 0;
					} else {
						grille2.maGrille[row][col] = 1;
						grille2.maGrilleColors[row][col] = myRandomGenerator.nextInt(4);
					}
				} else {
					grille2.maGrille[row][col] = grille1.maGrille[row][col];
					grille2.maGrilleColors[row][col] = grille1.maGrilleColors[row][col];
				}
			}
		}
		return grille2;
	}
	
	public Grid computeNextGrille(Grid grille1) {
		Grid grille2 = new Grid(grille1.maGrille.length, grille1.maGrille[0].length);

		// calcul des vivantes mortes
		for (int row = 0; row < grille1.maGrille.length; row++) {

			// je comptre le nombre de vivantes autours
			for (int col = 0; col < grille1.maGrille[row].length; col++) {

				int prevCol = (col == 0) ? grille1.maGrille[row].length - 1 : col - 1;
				int prevRow = (row == 0) ? grille1.maGrille.length - 1 : row - 1;

				int nextCol = (col == grille1.maGrille[row].length - 1) ? 0 : col + 1;
				int nextRow = (row == (grille1.maGrille.length - 1)) ? 0 : row + 1;

				int nombrevivantes = grille1.maGrille[prevRow][prevCol] + grille1.maGrille[prevRow][col]
						+ grille1.maGrille[prevRow][nextCol] +

						grille1.maGrille[row][prevCol] + grille1.maGrille[row][nextCol] +

						grille1.maGrille[nextRow][prevCol] + grille1.maGrille[nextRow][col]
						+ grille1.maGrille[nextRow][nextCol];

				if (grille1.maGrille[row][col] == 0) {
					// si elle est morte
					if (nombrevivantes == 3) {
						grille2.maGrille[row][col] = 1;

						int[] compteurCouleur = new int[4];
						compteurCouleur[grille1.maGrilleColors[prevRow][prevCol]] += grille1.maGrille[prevRow][prevCol];
						compteurCouleur[grille1.maGrilleColors[prevRow][col]] += grille1.maGrille[prevRow][col];
						compteurCouleur[grille1.maGrilleColors[prevRow][nextCol]] += grille1.maGrille[prevRow][nextCol];

						compteurCouleur[grille1.maGrilleColors[row][prevCol]] += grille1.maGrille[row][prevCol];
						compteurCouleur[grille1.maGrilleColors[row][nextCol]] += grille1.maGrille[row][nextCol];

						compteurCouleur[grille1.maGrilleColors[nextRow][prevCol]] += grille1.maGrille[nextRow][prevCol];
						compteurCouleur[grille1.maGrilleColors[nextRow][col]] += grille1.maGrille[nextRow][col];
						compteurCouleur[grille1.maGrilleColors[nextRow][nextCol]] += grille1.maGrille[nextRow][nextCol];
						int color = -1;
						for (int i = 0; i < 4; i++) {
							if (compteurCouleur[i] >= 2) {
								color = i;
								break;
							}
						}
						if (color == -1) {
							for (int i = 0; i < 4; i++) {
								if (compteurCouleur[i] == 0) {
									color = i;
									break;
								}
							}
						}
						grille2.maGrilleColors[row][col] = color;

					} else {
						grille2.maGrille[row][col] = 0;
					}

				} else {
					// elle est vivante
					if ((nombrevivantes == 2) || (nombrevivantes == 3)) {
						grille2.maGrille[row][col] = 1;
						grille2.maGrilleColors[row][col] = grille1.maGrilleColors[row][col];
					} else {
						grille2.maGrille[row][col] = 0;
					}
				}
				
			}
		}
		return grille2;
	}
	
	public void doNextCycle() {
		if (myMutationRate !=0) {
			myCurrentGrid = applyMutations(myCurrentGrid);
		}
		myAnteriorGrid = myPreviousGrid;
		myPreviousGrid = myCurrentGrid;
		myCurrentGrid = computeNextGrille(myCurrentGrid);
	}

	
}
