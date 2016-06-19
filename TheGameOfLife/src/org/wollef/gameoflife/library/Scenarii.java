package org.wollef.gameoflife.library;

import org.wollef.gameoflife.kernel.Engine;

public class Scenarii {
	
	public static void prepareRandomGrid(Engine e, int initialRate, int mutationRate) {
		e.fillRandomly(initialRate);		
		e.setMutationRate(mutationRate);
	}

	public static void prepareGliderGunFight(Engine e) {
        e.addPattern(10, 10, Patterns.gosperGliderGun, 0, false, false, 0);
		e.addPattern(50, 10, Patterns.gosperGliderGun, 0, false, false, 0);
		e.addPattern(90, 10, Patterns.gosperGliderGun, 0, false, false, 0);
		e.addPattern(130, 10, Patterns.gosperGliderGun, 0, false, false, 0);
		e.addPattern(170, 10, Patterns.gosperGliderGun, 0, false, false, 0);

		e.addPattern(10, 220, Patterns.gosperGliderGun, 1, false, true, 0);
		e.addPattern(50, 220, Patterns.gosperGliderGun, 1, false, true, 0);
		e.addPattern(90, 220, Patterns.gosperGliderGun, 1, false, true, 0);
		e.addPattern(130, 220, Patterns.gosperGliderGun, 1, false, true, 0);
		e.addPattern(170, 220, Patterns.gosperGliderGun, 1, false, true, 0);

		e.addPattern(290, 10, Patterns.gosperGliderGun, 2, true, false, 0);
		e.addPattern(330, 10, Patterns.gosperGliderGun, 2, true, false, 0);
		e.addPattern(370, 10, Patterns.gosperGliderGun, 2, true, false, 0);
		e.addPattern(410, 10, Patterns.gosperGliderGun, 2, true, false, 0);
		e.addPattern(450, 10, Patterns.gosperGliderGun, 2, true, false, 0);

		e.addPattern(290, 220, Patterns.gosperGliderGun, 3, true, true, 0);
		e.addPattern(330, 220, Patterns.gosperGliderGun, 3, true, true, 0);
		e.addPattern(370, 220, Patterns.gosperGliderGun, 3, true, true, 0);
		e.addPattern(410, 220, Patterns.gosperGliderGun, 3, true, true, 0);
		e.addPattern(450, 220, Patterns.gosperGliderGun, 3, true, true, 0);
	}
	
	public static void prepareCopperheadTravel(Engine e) {
		e.addPattern(250, 120, Patterns.Copperhead, 3, false, false, 0);
		e.addPattern(100, 50, Patterns.Copperhead, 2, false, false, 1);
	}
	
	public static void prepareBlockLayingSwitchEngine1(Engine e) {
		e.addPattern(250, 125, Patterns.blockLayingSwitchEngine1, 3, false, false, 0);
	}
		
	public static void prepareBlockLayingSwitchEngine2(Engine e) {
		e.addPattern(250, 125, Patterns.blockLayingSwitchEngine2, 3, false, false, 0);
	}
		
	public static void prepareBlockLayingSwitchEngine3(Engine e) {
		e.addPattern(250, 125, Patterns.blockLayingSwitchEngine3, 3, false, false, 0);
	}
		
	
		
	
}
