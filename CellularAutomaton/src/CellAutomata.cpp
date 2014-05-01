/*
 * main.cpp
 *
 *  Created on: Apr 30, 2014
 *      Author: Kostadin Cvejoski
 *      		Makrooni Vooria
 */
#include "CellAutomata.h"

CellAutomata::CellAutomata(int wolfNum, int radius, char startCond) {
	this->wolfNumber = wolfNum;
	this->radius = radius;
	this->startCon = startCond;
	this->binWolfNum = decimalToBinary(wolfNum);
	initCells();
}

string CellAutomata::decimalToBinary(int decimal) {
    if (decimal == 0) return "0";
    if (decimal == 1) return "1";
	if (decimal%2 == 0)
		return decimalToBinary(decimal/2) + "0";
	else
		return decimalToBinary(decimal/2) + "1";
}

void CellAutomata::initCells() {
	if (this->startCon == 'R') {
		for (int i=2; i<82; i++)
			this->cells[i] = randNum(i);
	}
	else {
		this->cells[42] = 1;
	}
}

short int CellAutomata::randNum(int i) {
	srand(i);
	return	rand()%2;
}

void CellAutomata::stepPropagation() {
	int position;
	for (int i = 2; i<82; i++) {
		position = neighborhood(i);
		if (position >= binWolfNum.length())
			nextStep[i] = 0;
		else
			nextStep[i] = atoi(binWolfNum.substr(binWolfNum.length()-1-position, 1).c_str());
	}
	for (int i = 0; i < 84; i++)
		cells[i] = nextStep[i];
}

int CellAutomata::neighborhood(int position) {
	int result = 0;
	int* neighbor = new int[2*radius + 1];
	int counter = 0;
	for (int i = position - radius; i <= position + radius; i++) {
		neighbor[counter] = cells[i];
		counter++;
	}
	result = binaryToDecimal(neighbor, 2*radius + 1);
	delete[] neighbor;
	return result;
}

int CellAutomata::binaryToDecimal(int* binary, int size) {
	int result = 0;
	int position = size-1;
	for (int i = 0; i < size; i++) {
		result += binary[i] * pow(2, position);
		position--;
	}
	return result;
}

void CellAutomata::printStep() {
	for (int i = 0; i<84; i++) {
		if (cells[i] == 0)
			cout<<'-';
		else
			cout<<'*';
	}
	cout<<endl;
}

void CellAutomata::run(int steps) {
	for (int i = 0; i<steps; i++) {
		printStep();
		stepPropagation();
	}
}

CellAutomata::~CellAutomata() {
	// TODO Auto-generated destructor stub
}

