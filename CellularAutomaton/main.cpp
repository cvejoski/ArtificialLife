/*
 * main.cpp
 *
 *  Created on: Apr 30, 2014
 *      Author: Kostadin Cvejoski
 *      		Makrooni Vooria
 */
#include "src/CellAutomata.h"

#include <iostream>
#include <algorithm>


using namespace std;

int main() {
	int wn;
	int radius;
	char sc;
	cout<<"Enter the Wolfram number: ";
	cin>>wn;
	cout<<endl;
	if (!cin) {
		cerr<<"Wrong input! Please enter integer number!"<<endl;
		return 1;
	}
	cout<<"Enter the radius (1 or 2): ";
	cin>>radius;
	if (!cin || (radius < 1 || radius > 2)) {
		cerr<<"Wrong input! Please enter integer number!"<<endl;
		return 1;
	}
	cout<<endl;
	cout<<"Enter the starting condition (S or R):";
	cin>>sc;
	if (!cin || (sc != 'R' && sc != 'S')) {
		cerr<<"Wrong input! Please enter correct input!"<<endl;
		return 1;
	}
	cout<<endl;
	CellAutomata g(wn, radius, sc);
	g.run(100);
	return 0;

}
