package org.wollef.gameoflife.gui;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.wollef.gameoflife.kernel.Engine;
import org.wollef.gameoflife.library.Scenarii;
import org.wollef.gameoflife.rendering.Renderer;

public class GameOfLife extends JFrame {
	private static final long serialVersionUID = -983890039611549480L;
	
	// both the engine and renderer collaborates for final result
	Engine myEngine;
	Renderer myRenderer;

	public GameOfLife() {
		super("Lines Drawing Demo");

		myEngine = new Engine(500,250);		
		Scenarii.prepareRandomGrid(myEngine,2,0);
		
		setUndecorated(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());

		setSize(xSize, ySize);

		myRenderer = new Renderer(xSize, ySize);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		new Runner().start();

	}

	Random myRandomGenerator = new Random();
	BufferedImage myImage;
	
	public void paint(Graphics g) {

		// super.paint(g);
		try {
			g = this.getGraphics();
			if ((g != null) && (myImage != null)) {
				g.drawImage(myImage, 0, 0, null);
			}
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		} catch (Exception e) {
			System.out.println("Error blitting: " + e);
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameOfLife().setVisible(true);
			}
		});

	}

	/** Thread running the animation. */
	private class Runner extends Thread {
		/** Wether or not thread should stop. */
		private boolean shouldStop = false;
		/** Pause or resume. */
		private boolean pause = false;

		/** Invoke to stop animation. */
		public void shouldStop() {
			this.shouldStop = true;
		}// met

		/** Invoke to pause. */
		public void pauseGame() {
			pause = true;
		}// met

		/** Invoke to resume. */
		public void resumeGame() {
			pause = false;
		}// met

		/**
		 * Main runner method : sleeps and invokes engine.play().
		 * 
		 * @see Engine#play
		 */
		public void run() {
			while (!shouldStop) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // catch
				if (!pause) {
					// computeNextCycle
					myEngine.doNextCycle();
					// computeImage
					myImage = myRenderer.renderGrid(myEngine);
					// affiche image
					repaint();
				} // if
			} // while
		}// met

		/** Wether or not we are paused. */
		public boolean isPaused() {
			return pause;
		}// met
	}// class Runner (inner)

}
