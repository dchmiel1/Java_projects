#ifndef MOBILE_H
#define MOBILE_H

class Mobile{
    std::string brand;
    std::string model;
    float price;
public:
    std::string getBrand();
    float getPrice();
    std::string getModel();
    void setBrand(std::string);
    void setPrice(float);
    void setModel(std::string);
    void swapData(Mobile*);
    void saveData(Mobile);
};

#endif
