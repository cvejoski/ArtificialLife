/*
 * Ant.cpp
 *
 *  Created on: May 15, 2014
 *      Author: Kostadin Cvejoski
 *      		Makrooni Vooria
 */

#include "Ant.h"

Ant::Ant(int x, int y, int orient, short init) {
	this->position.x = x;
	this->position.y = y;
	this->position.orientation = orient;
	initGrid(init);
}

void Ant::initGrid(short init){
	switch (init) {
		case 0:
			gridConfColor(this->unset);
			cout<<"Grid configuration: All white\n";
			print();
			break;
		case 1:
			gridConfColor(this->set);
			cout<<"All black\n";
			print();
			break;
		case 2:
			gridConfChecker();
			cout<<"Grid configuration: Checkerboard\n";
			print();
			break;
		case 3:
			gridConfStripes();
			cout<<"Grid configuration: Horizontal Stripes\n";
			print();
			break;
		case 4:
			gridConfRand();
			cout<<"Grid configuration: Random\n";
			print();
			break;
		default:
			throw "Unknown grid configuration";
	}
}

void Ant::gridConfChecker() {
	for (int i = 0; i < this->height; i++)
		for (int j = 0; j < this->width; j++)
			((i + j) % 2 == 0) ? this->grid[i][j] = this->set : this->grid[i][j] = this->unset;
}

void Ant::gridConfStripes() {
	for (int i = 0; i < this->height; i++)
		for (int j = 0; j < this->width; j++)
			(i % 2 == 0) ? this->grid[i][j] = this->set : this->grid[i][j] = this->unset;;
}

void Ant::gridConfColor(char color) {
	for (int i = 0; i < this->height; i++)
		for (int j = 0; j < this->width; j++)
			this->grid[i][j] = color;
}

void Ant::gridConfRand() {
	srand(time(NULL));
	for (int i = 0; i < this->height; i++)
		for (int j = 0; j < this->width; j++)
			(rand() % 2 == 0) ? this->grid[i][j] = this->set : this->grid[i][j] = this->unset;
}

void Ant::print() {
	for (int i = 0; i < this->height; i++) {
		for (int j = 0; j < this->width; j++)
			cout<<this->grid[i][j];
		cout<<endl;
	}
}

bool Ant::scan() {
	if (this->grid[this->position.y][this->position.x] == this->set)
		return true;
	else
		return false;
}

void Ant::turn(bool scan) {
	if (scan)
		position.orientation = modulo(position.orientation - 1, 4);
	else
		position.orientation = modulo(position.orientation + 1, 4);
}

void Ant::flip(bool scan) {
	if (scan)
		this->grid[modulo(this->position.y, this->height)][modulo(this->position.x, this->width)] = this->unset;
	else
		this->grid[modulo(this->position.y, this->height)][modulo(this->position.x, this->width)] = this->set;
}

void Ant::move() {
	switch (this->position.orientation) {
		case 0:
			position.y = modulo(this->position.y - 1, this->height);
			break;
		case 1:
			position.x = modulo(this->position.x + 1, this->width);
			break;
		case 2:
			position.y = modulo(this->position.y + 1, this->height);
			break;
		case 3:
			position.x = modulo(this->position.x - 1, this->width);
			break;
		default:
			throw "invalid orientation";
	}
}

int Ant::modulo (int m, int n) {
	return m >= 0 ? m % n : ( n - abs ( m % n ) ) % n;
}

void Ant::step() {
	turn(scan());
	flip(scan());
	move();
}

void Ant::run(int steps) {
	ofstream file("livingCells.dat");
	for (int i = 0; i < steps; i++) {
		cout<<"STEP #"<<i<<endl;
		step();
		print();
		sleep(100);
		file<<i<<" "<<livingCells()<<endl;
	}
	file.close();
}

int Ant::livingCells() {
	int result = 0;
	for (int i = 0; i < this->height; i++)
		for (int j = 0; j < this->width; j++)
			if (this->grid[i][j] == this->set) result++;
	return result;
}

void Ant::sleep(unsigned int mseconds) {
    clock_t goal = mseconds + clock();
    while (goal > clock());
}

Ant::~Ant() {
	// TODO Auto-generated destructor stub
}

