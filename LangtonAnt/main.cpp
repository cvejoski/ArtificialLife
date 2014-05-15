/*
 * main.cpp
 *
 *  Created on: May 15, 2014
 *      Author: Kostadin Cvejoski
 *      		Makrooni Vooria
 */
#include "src/Ant.h"

int main(){
	int x;
	cout<<"Enter the x coordinate: ";
	cin>>x;
	if (!cin) {
		cerr<<"Enter integer number!\n";
		return 1;
	}

	int y;
	cout<<"Enter the y coordinate: ";
	cin>>y;
	if (!cin) {
		cerr<<"Enter integer number!\n";
		return 1;
	}

	int orientation;
	cout<<"0 - Up\n1 - Right\n2 - Down\n3 - Left\n";
	cout<<"Enter the orientation (number) of the Ant: ";
	cin>>orientation;
	if (!cin || orientation < 0 || orientation > 3) {
		cerr<<"Enter integer number between 0 and 3!\n";
		return 1;
	}

	short init;
	cout<<"0 - all white\n1 - all black\n2 - checkersboard\n3 - horizontal stripes\n4 - random\n";
	cout<<"Enter the type of initialization of the grid: ";
	cin>>init;
	if (!cin || orientation < 0 || orientation > 4) {
		cerr<<"Enter integer number between 0 and 4!\n";
		return 1;
	}

	int n_steps = 0 ;
	cout<<"Enter the number of steps: ";
	cin>>n_steps;
	if (!cin || orientation < 0) {
		cerr<<"Enter integer number larger than 0!\n";
		return 1;
	}

	Ant ant(y, x, orientation, init);
	ant.run(n_steps);
	return 0;
}


