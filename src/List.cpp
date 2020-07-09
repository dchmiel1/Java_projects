#include <iostream>
#include <fstream>
#include <iomanip>
#include "Mobile.h"
#include "Contract.h"
#include "DataPacket.h"
#include "Cart.h"
#include "List.h"
using namespace std;

template<typename T>
void List<T>::deleteList(List* pointer){
    List<T>* help=NULL;
    while(pointer->nextNode){
        help=pointer;
        pointer=pointer->nextNode;
        delete help;
    }
    delete pointer;
}
template<>
void List<Mobile>::deleteLists(List<Mobile>* m, List<DataPacket>* d, List<Contract>* c){
    (*m).deleteList(m);
    (*d).deleteList(d);
    (*c).deleteList(c);
}

template<>
void List<Mobile>::addData(List** pointer){         //adding mobiles to list
        string dataString;
        fstream file;
        float dataFloat;
        file.open("offer.txt", ios::in);
        if(!file.good())
            cout<<"ERROR";
        while(file>>dataString){
            if(dataString=="mobile"){
                List<Mobile> *object=new List<Mobile>;
                file>>dataString;
                object->data.setBrand(dataString);
                file>>dataString;
                object->data.setModel(dataString);
                file>>dataFloat;
                object->data.setPrice(dataFloat);
                object->nextNode=(*pointer);
                *pointer=object;
            }
        }
}

template<>
void List<DataPacket>::addData(List** pointer){     //adding data packets to list
        string dataString;
        float dataFloat;
        short int dataShInt;
        fstream file;
        file.open("offer.txt", ios::in);
        if(!file.good())
            cout<<"ERROR";
        while(file>>dataString){
            if(dataString=="packet"){
                List<DataPacket> *object=new List<DataPacket>;
                file>>dataFloat;
                object->data.setPrice(dataFloat);
                file>>dataShInt;
                object->data.setExpiry1(dataShInt);
                file>>dataString;
                object->data.setExpiry2(dataString);
                file>>dataShInt;
                object->data.setIntAmount(dataShInt);
                object->nextNode=(*pointer);
                *pointer=object;
            }
        }
}

template<>
void List<Contract>::addData(List** pointer){       //adding contracts to list
        string dataString;
        short int dataShInt;
        float dataFloat;
        fstream file;
        file.open("offer.txt", ios::in);
        if(!file.good())
            cout<<"ERROR";
        while(file>>dataString){
            if(dataString=="contract"){
                List<Contract> *object=new List<Contract>;
                file>>dataString;
                if(dataString=="sub")
                    object->data.setType(typeOfContract::SUB);
                if(dataString=="pay")
                    object->data.setType(typeOfContract::PAY);
                file>>dataFloat;
                object->data.setPrice(dataFloat);
                file>>dataShInt;
                object->data.setExpiry1(dataShInt);
                file>>dataString;
                object->data.setExpiry2(dataString);
                object->nextNode=(*pointer);
                *pointer=object;
            }
        }
}

template<>
void List<Mobile>::showOffer(List* pointer){
	int i=0;
    cout<<"Mobile phones' offer:"<<endl;
    while(pointer){
		i++;
        cout<<" "<<right<<setw(2)<<i<<". "<<left<<setw(26)<<pointer->data.getBrand()+" "+pointer->data.getModel();
        cout<<pointer->data.getPrice()<<"zl"<<endl;
        pointer=pointer->nextNode;
    }
    cout<<endl;
}

template<>
void List<DataPacket>::showOffer(List* pointer){
	int i=0;
    cout<<"Data packets' offer:"<<endl;
    cout<<right<<setw(20)<<"Internet"<<setw(21)<<"Expiry time"<<setw(22)<<"Price"<<endl;
    while(pointer){
		i++;
        cout<<" "<<right<<setw(2)<<i<<". "<<right<<setw(13)<<pointer->data.getIntAmount()<<"GB"<<setw(15)<<pointer->data.getExpiry1()<<" "<<left<<setw(10)<<pointer->data.getExpiry2()<<right<<setw(15)<<pointer->data.getPrice()<<"zl per month"<<endl;
        pointer=pointer->nextNode;
    }
    cout<<endl;
}
template<>
void List<Contract>::showOffer(List* pointer){
	int i=0;
    cout<<"Contracts' offer:"<<endl;
    cout<<right<<setw(18)<<"Contract type"<<setw(21)<<"Expiry time"<<setw(22)<<"Price"<<endl;
    while(pointer){
		i++;
        if(pointer->data.getType()==typeOfContract::SUB){
            cout<<" "<<right<<setw(2)<<i<<". "<<left<<setw(13)<<"Subscription"<<right<<setw(15)<<pointer->data.getExpiry1()<<" "<<left<<setw(10)<<pointer->data.getExpiry2()<<right<<setw(15)<<pointer->data.getPrice()<<"zl per month"<<endl;
        }
        else
            cout<<" "<<right<<setw(2)<<i<<". "<<left<<setw(13)<<"Pay-as-you-go"<<right<<setw(15)<<pointer->data.getExpiry1()<<" "<<left<<setw(10)<<pointer->data.getExpiry2()<<right<<setw(15)<<pointer->data.getPrice()<<"zl (one-time payment)"<<endl;
        pointer=pointer->nextNode;
    }
    cout<<endl;

}

template<typename T>
void List<T>::swapData(List* a, List* b){
    (a->data).swapData(&(b->data));
}

template<>
void List<Mobile>::sortData(List* pointer, int choice){         //sorting data by 'choice'
    List* helpPointer=NULL;
    for(;pointer!=NULL;pointer=pointer->nextNode)
        for(helpPointer=pointer;helpPointer!=NULL;helpPointer=helpPointer->nextNode)
        {
            if(choice==1){
                if(pointer->data.getBrand()>helpPointer->data.getBrand()){
                    (*pointer).swapData(pointer, helpPointer);
                }
            }
            else
                if(pointer->data.getPrice()>helpPointer->data.getPrice())
                    (*pointer).swapData(pointer, helpPointer);
        }
}

template<>
void List<Contract>::sortData(List* pointer, int choice){           //sorting data by 'choice'
    List* helpPointer=NULL;
    for(;pointer!=NULL;pointer=pointer->nextNode)
        for(helpPointer=pointer;helpPointer!=NULL;helpPointer=helpPointer->nextNode)
        {
            if(choice==1){
                if(pointer->data.getType()>helpPointer->data.getType()){
                    (*pointer).swapData(pointer, helpPointer);
                }
            }
            else
                if(pointer->data.getPrice()>helpPointer->data.getPrice())
                    (*pointer).swapData(pointer, helpPointer);
        }
}


template<>
void List<DataPacket>::sortData(List* pointer, int choice){             //sorting data by 'choice'
    List* helpPointer=NULL;
    for(;pointer!=NULL;pointer=pointer->nextNode)
        for(helpPointer=pointer;helpPointer!=NULL;helpPointer=helpPointer->nextNode)
        {
            if(choice==1){
                if((pointer)->data.getIntAmount()>helpPointer->data.getIntAmount()){
                    (*pointer).swapData(pointer, helpPointer);
                }
            }
            else
                if(pointer->data.getPrice()>helpPointer->data.getPrice())
                    (*pointer).swapData(pointer, helpPointer);
        }
}

template<>
List<Mobile>* List<Mobile>::getNextNode(){
    return nextNode;
}

template<>
List<Contract>* List<Contract>::getNextNode(){
    return nextNode;
}

template<>
List<DataPacket>* List<DataPacket>::getNextNode(){
    return nextNode;
}

template<>
Mobile List<Mobile>::getData(){
    return data;
}

template<>
DataPacket List<DataPacket>::getData(){
    return data;
}

template<>
Contract List<Contract>::getData(){
    return data;
}
