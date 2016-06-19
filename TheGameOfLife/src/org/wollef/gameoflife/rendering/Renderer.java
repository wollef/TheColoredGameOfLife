package org.wollef.gameoflife.rendering;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.wollef.gameoflife.kernel.Engine;

public class Renderer {
	int largeur;
	int hauteur;
	
	public final static Color lightGreen = new Color(175, 255, 175);
	public final static Color lightBlue = new Color(175, 175, 255);
	public final static Color lightYellow = new Color(255, 255, 175);

	static Color[][][] colorMapRed = new Color[2][2][2];
	static Color[][][] colorMapGreen = new Color[2][2][2];
	static Color[][][] colorMapBlue = new Color[2][2][2];
	static Color[][][] colorMapYellow = new Color[2][2][2];
	
	static void fillColorMap() {
		colorMapRed[0][0][0] = Color.black;
		colorMapRed[0][0][1] = Color.red;
		colorMapRed[0][1][0] = Color.lightGray;
		colorMapRed[0][1][1] = Color.pink;
		colorMapRed[1][0][0] = Color.darkGray;
		colorMapRed[1][0][1] = Color.gray;
		colorMapRed[1][1][0] = Color.lightGray;
		colorMapRed[1][1][1] = Color.white;

		colorMapGreen[0][0][0] = Color.black;
		colorMapGreen[0][0][1] = Color.green;
		colorMapGreen[0][1][0] = Color.lightGray;
		colorMapGreen[0][1][1] = lightGreen;
		colorMapGreen[1][0][0] = Color.darkGray;
		colorMapGreen[1][0][1] = Color.gray;
		colorMapGreen[1][1][0] = Color.lightGray;
		colorMapGreen[1][1][1] = Color.white;

		colorMapBlue[0][0][0] = Color.black;
		colorMapBlue[0][0][1] = Color.blue;
		colorMapBlue[0][1][0] = Color.lightGray;
		colorMapBlue[0][1][1] = lightBlue;
		colorMapBlue[1][0][0] = Color.darkGray;
		colorMapBlue[1][0][1] = Color.gray;
		colorMapBlue[1][1][0] = Color.lightGray;
		colorMapBlue[1][1][1] = Color.white;

		colorMapYellow[0][0][0] = Color.black;
		colorMapYellow[0][0][1] = Color.yellow;
		colorMapYellow[0][1][0] = Color.lightGray;
		colorMapYellow[0][1][1] = lightYellow;
		colorMapYellow[1][0][0] = Color.darkGray;
		colorMapYellow[1][0][1] = Color.gray;
		colorMapYellow[1][1][0] = Color.lightGray;
		colorMapYellow[1][1][1] = Color.white;
	}
	
	public Renderer(int l, int h) {
		fillColorMap();
		largeur = l;
		hauteur = h;
	}
	

	public  BufferedImage renderGrid(Engine e) {
		BufferedImage myImage = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = (Graphics2D) myImage.getGraphics();
		g2d.clearRect(0, 0, largeur, hauteur);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, largeur, hauteur);

		double stepX = (double) largeur / (double) e.myCurrentGrid.maGrille[0].length;
		double stepY = (double) hauteur / (double) e.myCurrentGrid.maGrille.length;

		for (int row = 0; row < e.myCurrentGrid.maGrille.length; row++) {
			for (int col = 0; col < e.myCurrentGrid.maGrille[0].length; col++) {
				Color[][][] usedMap = null;
				switch (e.myCurrentGrid.maGrilleColors[row][col]) {
				case 0:
					usedMap = colorMapRed;
					break;
				case 1:
					usedMap = colorMapGreen;
					break;
				case 2:
					usedMap = colorMapBlue;
					break;
				case 3:
					usedMap = colorMapYellow;
					break;
				}

				g2d.setColor(
						usedMap
						[e.myAnteriorGrid.maGrille[row][col]]
						[e.myPreviousGrid.maGrille[row][col]]
						[e.myCurrentGrid.maGrille[row][col]]
				);

				double startX = stepX * col;
				double startY = stepY * row;
				g2d.fillRect((int) startX, (int) startY, (int) stepX, (int) stepY);
			}
		}

		return myImage;
	}

}
