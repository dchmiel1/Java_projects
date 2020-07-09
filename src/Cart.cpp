#include <iostream>
#include <iomanip>
#include "Cart.h"
#include "List.h"
#include "Mobile.h"
#include "functions.h"
#include "Contract.h"
#include "DataPacket.h"
using namespace std;

Cart::Cart(){
    for(int i=0;i<MAX_LENGTH;i++){
        mob[i]=NULL;
        con[i]=NULL;
        pac[i]=NULL;
        }
    cost=0;
}

Cart::~Cart(){
    int i=0;
    while(mob[i]!=NULL){
        delete mob[i];
        i++;
    }
    i=0;
    while(pac[i]!=NULL){
        delete pac[i];
        i++;
    }
    i=0;
    while(con[i]!=NULL){
        delete con[i];
        i++;
    }
}

void Cart::showCart(){
    int i=0;
    if(!mob[0]&&!con[0]&&!pac[0])
        cout<<"Your cart is empty";
    else{
        cout<<"Products in your cart:  "<<endl;
        while(mob[i]){                                                                       //writing down all mobile phones in the cart
            cout<<" - "<<left<<setw(20)<<mob[i]->getBrand()+" "+mob[i]->getModel();
            cout<<right<<setw(20)<<mob[i]->getPrice()<<"zl"<<endl;
            i++;
        }
        i=0;
        while(con[i]){                                                                        //writing down all contracts in the cart
            if(con[i]->getType()==typeOfContract::SUB)
                cout<<" - "<<"Subscription  ";
            else
                cout<<" - "<<"Pay-as-you-go ";
            cout<<setw(3)<<con[i]->getExpiry1()<<" ";
            cout<<left<<setw(10)<<con[i]->getExpiry2();
            cout<<right<<setw(12)<<con[i]->getPrice()<<"zl"<<endl;
            i++;
        }
        i=0;
        while(pac[i]){                                                                        //writing down all data packets in the cart
            cout<<" - "<<setw(2)<<pac[i]->getIntAmount()<<"GB ";
            cout<<setw(12)<<pac[i]->getExpiry1()<<" ";
            cout<<left<<setw(10)<<pac[i]->getExpiry2();
            cout<<right<<setw(12)<<pac[i]->getPrice()<<"zl"<<endl;
            i++;
        }
        cout<<endl<<"To pay: "<<cost<<"zl"<<endl;
    }
        cin.get();
}

void Cart::addM(List<Mobile>* pointer){
    int choice=0;
    int error=0;
    int i=1;
    cout<<"Choose mobile which you want to add to your cart"<<endl;
    cin>>choice;
    while(i!=choice){                                               //searching for mobile with right number - which the user has written down
        if(pointer->getNextNode()==NULL){                                //when the number is too high - there are not so many records
            cout<<"There are not so many records"<<endl;
            cleanBuffer();
            cin.get();
            error=1;
            break;
        }
        pointer=pointer->getNextNode();
        i++;
    }
    if(!error){                                                     //if the given number wasn't too high - inserting mobile into the cart
        i=0;
        Mobile* dataPointer=new Mobile;
        (*dataPointer).saveData(pointer->getData());
        while(mob[i])
            i++;
        mob[i]=dataPointer;
        cost=cost+(*dataPointer);
    }
}

void Cart::addP(List<DataPacket>* pointer){
    int choice=0;
    int i=1;
    int error=0;
    cout<<"Choose packet which you want to add to your cart"<<endl;
    cin>>choice;                                                //searching for packet with right number - which the user has written down
    while(i!=choice){                                           //when the number is too high - there are not so many records
        if(pointer->getNextNode()==NULL){
            cout<<"There are not so many records"<<endl;
            cleanBuffer();
            cin.get();
            error=1;
            break;
        }
        pointer=pointer->getNextNode();
        i++;
    }
    if(!error){                                                 //if the given number wasn't too high - inserting packet into the cart
        i=0;
        DataPacket* dataPointer=new DataPacket;
        (*dataPointer).saveData(pointer->getData());
        while(pac[i])
            i++;
        pac[i]=dataPointer;
        cost=cost+(*dataPointer);
    }
}

void Cart::addC(List<Contract>* pointer){
    int choice=0;
    int i=1;
    int error=0;
    cout<<"Choose contract which you want to add to your cart"<<endl;
    cin>>choice;
    while(i!=choice){                                           //searching for contract with right number - which the user has written down
            if(pointer->getNextNode()==NULL){                        //when the number is too high - there are not so many records
                cout<<"There are not so many records"<<endl;
            cleanBuffer();
            cin.get();
            error=1;
            break;
        }
        pointer=pointer->getNextNode();
        i++;
    }
    if(!error){                                                 //if the given number wasn't too high - inserting packet into the cart
        i=0;
        Contract* dataPointer=new Contract;
        (*dataPointer).saveData(pointer->getData());
        while(con[i])
            i++;
        con[i]=dataPointer;
        cost=cost+(*dataPointer);
    }
}
