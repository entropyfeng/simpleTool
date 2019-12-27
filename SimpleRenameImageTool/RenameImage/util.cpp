#include"util.h"
QStringList Util::getImageSuffix(){
    QStringList qStringList;
    qStringList.push_back("jpg");
    qStringList.push_back("jpeg");
    qStringList.push_back("png");

    return qStringList;

}
