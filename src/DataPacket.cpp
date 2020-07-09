#include <iostream>
#include "DataPacket.h"
using namespace std;

float DataPacket::getPrice(){           //getters and setters
    return price;
}

short int DataPacket::getExpiry1(){
	return expiryTime1;
}

string DataPacket::getExpiry2(){
	return expiryTime2;
}

short int DataPacket::getIntAmount(){
	return internetAmount;
}

void DataPacket::setPrice(float priceToSet){
    price=priceToSet;
}

void DataPacket::setExpiry1(short int expiryToSet1){
    expiryTime1=expiryToSet1;
}

void DataPacket::setExpiry2(string expiryToSet2){
    expiryTime2=expiryToSet2;
}

void DataPacket::setIntAmount(short int intToSet){
    internetAmount=intToSet;
}

void DataPacket::swapData(DataPacket* b){
    short int helpExpiry1;
    string helpExpiry2;
    float helpPrice;
    short int helpInternet;
    helpExpiry1=expiryTime1;
    helpExpiry2=expiryTime2;
    helpPrice=price;
    helpInternet=internetAmount;
    expiryTime1=b->expiryTime1;
    expiryTime2=b->expiryTime2;
    price=b->price;
    internetAmount=b->internetAmount;
    b->expiryTime1=helpExpiry1;
    b->expiryTime2=helpExpiry2;
    b->price=helpPrice;
    b->internetAmount=helpInternet;
}

void DataPacket::saveData(DataPacket data){
    expiryTime1=data.expiryTime1;
    expiryTime2=data.expiryTime2;
    price=data.price;
    internetAmount=data.internetAmount;
}
