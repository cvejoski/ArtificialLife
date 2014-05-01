/*
 * main.cpp
 *
 *  Created on: Apr 30, 2014
 *      Author: Kostadin Cvejoski
 *      		Makrooni Vooria
 */

#ifndef CELLAUTOMATA_H_
#define CELLAUTOMATA_H_

#include <iostream>
#include <string>
#include <algorithm>
#include <math.h>

using namespace std;

class CellAutomata {
private:
	int cells[84] = {};
	int nextStep[84] = {};
	int radius;
	char startCon;
	int wolfNumber;
	string binWolfNum;
	string decimalToBinary(int decimal);
	int binaryToDecimal(int*, int);
	void initCells();
	short int randNum(int);
	void stepPropagation();
	int neighborhood(int);
	void printStep();
public:
	CellAutomata(int wolfNum, int radius, char startCond);
	void run(int);
	virtual ~CellAutomata();
};

#endif /* CELLAUTOMATA_H_ */
