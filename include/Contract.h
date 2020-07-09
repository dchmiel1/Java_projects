#ifndef CONTRACT_H
#define CONTRACT_H

enum class typeOfContract{SUB, PAY};

class Contract{
    enum typeOfContract type;
    float price;
    short int expiryTime1;
    std::string expiryTime2;
public:
    float getPrice();
    enum typeOfContract getType();
    short int getExpiry1();
    std::string getExpiry2();
    void setPrice(float);
    void setExpiry1(short int);
    void setExpiry2(std::string);
    void setType(enum typeOfContract);
    void swapData(Contract*);
    void saveData(Contract);
};

#endif
