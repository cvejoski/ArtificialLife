/*
 * Ant.h
 *
 *  Created on: May 15, 2014
 *      Author: Kostadin Cvejoski
 *      		Makrooni Vooria
 */

#ifndef ANT_H_
#define ANT_H_

#include <iostream>
#include <cstdlib>
#include <fstream>

using namespace std;

/*
 * This structure is used for saving the status of the ant
 */
struct Pointer {
		int x, y;
		int orientation;
};

/*
 * This class is the main class for the algorithm
 */
class Ant {
private:
	// Status of the ant position and orientation
	Pointer position;

	//ASCII code for set cells
	static const char set = '#';

	//ASCII code for unset cells
	static const char unset = ' ';

	//Width of the gird
	static const int width = 82;

	//Height of the gird
	static const int height = 101;
	char grid[height][width];

	/**
	 * Initializing the grid. There are 5 different initialization criteria.
	 * @param init 0 all white; 1 all black; 2 checkerboard; 3 horizontal stripes; 4 random
	 */
	void initGrid(short init);

	/**
	 * One step of the Ant's algorithm
	 */
	void step();

	/**
	 * Initialize grid all white or all black
	 * @param color white or black
	 */
	void gridConfColor(char color);

	/**
	 * Initialize grid as checkerboard
	 */
	void gridConfChecker();

	/**
	 * Initialize the grid with horizontal stripes
	 */
	void gridConfStripes();

	/**
	 * Initialize the grid random
	 */
	void gridConfRand();

	/*
	 * Get the status of the current cell of the Ant
	 */
	bool scan();

	/**
	 * Turn the orientation of the Ant
	 * @param scan if is true (set cell) turn left otherwise right
	 */
	void turn(bool scan);

	/*
	 * Change the state of the cell
	 */
	void flip(bool scan);

	/**
	 * Moves the Ant one cell in the direction where is pointed
	 */
	void move();

	/**
	 * Print the grid
	 */
	void print();

	/**
	 * Modulo operation for the torus topology to be able to manage the boundaries of the grid
	 */
	int modulo(int m, int n);

	/**
	 * Get the number of set cells in the grid
	 */
	int livingCells();

	/**
	 * For better visualization in the terminal
	 */
	void sleep(unsigned int mseconds);

public:
	Ant(int x, int y, int orient, short init);
	void run(int steps);
	virtual ~Ant();
};

#endif /* ANT_H_ */
