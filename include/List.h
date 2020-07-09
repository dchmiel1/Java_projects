#ifndef LIST_H
#define LIST_H

template <class T> class List{
    T data;
    class List* nextNode;
public:
    void addData(List**);
    void showOffer(List*);
    void sortData(List*, int);
    void swapData(List*, List*);
    List* getNextNode();
    T getData();
    void deleteList(List*);
    void deleteLists(List<Mobile>*, List<DataPacket>*, List<Contract>*);
};

#endif
