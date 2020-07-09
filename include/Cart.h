#ifndef CART_H
#define CART_H
#define MAX_LENGTH 20

class Mobile;
class Contract;
class DataPacket;
template<typename T> class List;

class Cart{
    float cost;
    Mobile* mob[MAX_LENGTH];
    Contract* con[MAX_LENGTH];
    DataPacket* pac[MAX_LENGTH];
public:
    Cart();
    ~Cart();
    void showCart();
    void addM(List<Mobile>*);
    void addC(List<Contract>*);
    void addP(List<DataPacket>*);
};

#endif
