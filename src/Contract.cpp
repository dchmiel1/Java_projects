#include <iostream>
#include "Contract.h"
using namespace std;

float Contract::getPrice(){                 //getters and setters
    return price;
}

enum typeOfContract Contract::getType(){
	return type;
}

short int Contract::getExpiry1(){
	return expiryTime1;
}

string Contract::getExpiry2(){
	return expiryTime2;
}

void Contract::setPrice(float priceToSet){
    price=priceToSet;
}

void Contract::setExpiry1(short int expiryToSet1){
    expiryTime1=expiryToSet1;
}

void Contract::setExpiry2(std::string expiryToSet2){
    expiryTime2=expiryToSet2;
}

void Contract::setType(enum typeOfContract typeToSet){
    type=typeToSet;
}

void Contract::swapData(Contract* b){
    int helpExpiry1;
    string helpExpiry2;
    enum typeOfContract helpType;
    float helpPrice;
    helpExpiry1=expiryTime1;
    helpExpiry2=expiryTime2;
    helpPrice=price;
    helpType=type;
    expiryTime1=b->expiryTime1;
    expiryTime2=b->expiryTime2;
    price=b->price;
    type=b->type;
    b->expiryTime1=helpExpiry1;
    b->expiryTime2=helpExpiry2;
    b->price=helpPrice;
    b->type=helpType;
}

void Contract::saveData(Contract data){
    expiryTime1=data.expiryTime1;
    expiryTime2=data.expiryTime2;
    price=data.price;
    type=data.type;
}
