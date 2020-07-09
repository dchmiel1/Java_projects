#ifndef DATAPACKET_H
#define DATAPACKET_H

class DataPacket{
    float price;
    short int expiryTime1;
    std::string expiryTime2;
    short int internetAmount;
public:
    float getPrice();
    short int getExpiry1();
    std::string getExpiry2();
    short int getIntAmount();
    void setPrice(float);
    void setExpiry1(short int);
    void setExpiry2(std::string);
    void setIntAmount(short int);
    void swapData(DataPacket*);
    void saveData(DataPacket);
};

#endif
