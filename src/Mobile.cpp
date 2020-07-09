#include <iostream>
#include "Mobile.h"
using namespace std;

float Mobile::getPrice(){       //getters and setters
    return price;
}

string Mobile::getBrand(){
    return brand;
}

string Mobile::getModel(){
	return model;
}

void Mobile::setBrand(string brandToSet){
    brand=brandToSet;
}

void Mobile::setPrice(float price){
    this->price=price;
}

void Mobile::setModel(string modelToSet){
    model=modelToSet;
}

void Mobile::swapData(Mobile* b){
    string helpBrand;
    string helpModel;
    float helpPrice;
    helpBrand=brand;
    helpModel=model;
    helpPrice=price;
    brand=b->brand;
    model=b->model;
    price=b->price;
    b->brand=helpBrand;
    b->model=helpModel;
    b->price=helpPrice;
}

void Mobile::saveData(Mobile data){
    brand=data.brand;
    model=data.model;
    price=data.price;
}
