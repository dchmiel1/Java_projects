#ifndef FUNCTIONS_H
#define FUNCTIONS_H

class Cart;
class Mobile;
class DataPacket;
class Contract;
template<typename T> class List;

void cleanBuffer();
void showMenu();
float operator+(float, Mobile);
float operator+(float, Contract);
float operator+(float, DataPacket);
template<typename T> int chooseAction(T);
template <typename T> void sortChoosing(T, std::string, std::string);
void contracts(List<Contract>*, Cart*);
void packets(List<DataPacket>*, Cart*);
void mobiles(List<Mobile>*, Cart*);
void showCart(Cart *cart);
void chooseAction();
void addData(List<Mobile>**, List<DataPacket>**, List<Contract>**);


#endif
