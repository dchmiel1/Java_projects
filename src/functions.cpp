#include <iostream>
#include "Contract.h"
#include "DataPacket.h"
#include "Mobile.h"
#include "functions.h"
#include "Cart.h"
#include "List.h"
using namespace std;

void cleanBuffer(){
    cin.clear();
    cin.ignore();
}

void showMenu(){                                                                    //function showing menu options
    cout<<"Welcome to online service of your telecomunication operator"<<endl;
    cout<<"Choose option: "<<endl;
    cout<<"--------------------------"<<endl;
    cout<<"1. Contracts"<<endl;
    cout<<"2. Mobile phones"<<endl;
    cout<<"3. Data packets"<<endl;
    cout<<"4. Show cart"<<endl;
    cout<<"5. Exit"<<endl;
    cout<<"--------------------------"<<endl;
}

float operator+(float cost, Mobile data){
    return cost+data.getPrice();
}

float operator+(float cost, Contract data){
    return cost+data.getPrice();
}

float operator+(float cost, DataPacket data){
    return cost+data.getPrice();
}

template<typename T>
int chooseAction(T pointer){                                //function operating choosing option in short menu, it has to be '1', '2' or '3' only
    int choice=0;
    while(!cin||(choice!=1&&choice!=2&&choice!=3)){
            system("clear");
            (*pointer).showOffer(pointer);
            cout<<"1.To cart"<<endl;
            cout<<"2.Sort"<<endl;
            cout<<"3.Back"<<endl;
            cin>>choice;
            cleanBuffer();
    }
    return choice;
}

template <typename T>
void sortChoosing(T pointer, string option1, string option2){       //choosing type of sorting
    int choice=0;
    do{
        system("clear");
        (*pointer).showOffer(pointer);
        cout<<"Chooose type of sorting: "<<endl;
        cout<<"1. By "+option1<<endl;
        cout<<"2. By "+option2<<endl;
        cin>>choice;
        cleanBuffer();
    }while(!cin||(choice!=1&&choice!=2));
    (*pointer).sortData(pointer, choice);
}

void contracts(List<Contract>* pointer, Cart* cart){                //function operating contracts offer
    switch(chooseAction(pointer)){
    case 1:
        system("clear");
        (*pointer).showOffer(pointer);
        (*cart).addC(pointer);
        break;
    case 2:
        sortChoosing(pointer, "type of contract", "price");
        contracts(pointer, cart);
        break;
    case 3:
        break;
    }
}

void packets(List<DataPacket>* pointer, Cart* cart){                //function operating data packets offer
    switch(chooseAction(pointer)){
    case 1:
        system("clear");
        (*pointer).showOffer(pointer);
        (*cart).addP(pointer);
        break;
    case 2:
        sortChoosing(pointer, "amount of Internet", "price");
        packets(pointer, cart);
        break;
    case 3:
        break;
    }
}

void mobiles(List<Mobile>* pointer, Cart* cart){                    //function operating mobiles offer
    switch(chooseAction(pointer)){
    case 1:
        system("clear");
        (*pointer).showOffer(pointer);
        (*cart).addM(pointer);
        break;
    case 2:
        sortChoosing(pointer, "brand", "price");
        mobiles(pointer, cart);
        break;
    case 3:
        break;
    }

}

void showCart(Cart *cart){
    system("clear");
    (*cart).showCart();
}

void chooseAction(){                                    //main menu, choosing option from 1 to 5
    List<Mobile>* mPointer=NULL;
    List<DataPacket>* dPointer=NULL;
    List<Contract>* cPointer=NULL;
    Cart cart;

    addData(&mPointer, &dPointer, &cPointer);
    int choice=0;
    do{
        do{
            system("clear");
            showMenu();
            cin>>choice;
            cleanBuffer();
        }while(!cin);
        switch(choice){
            case 1:
                contracts(cPointer, &cart);
                break;
            case 2:
                mobiles(mPointer, &cart);
                break;
            case 3:
                packets(dPointer, &cart);
                break;
            case 4:
                showCart(&cart);
                break;
            case 5:
                break;
        }
    }while(choice!=5);
    (*mPointer).deleteLists(mPointer, dPointer, cPointer);
}
void addData(List<Mobile>** m, List<DataPacket>** d, List<Contract>** c){       //adding data
    (**m).addData(m);
    (**d).addData(d);
    (**c).addData(c);
}
