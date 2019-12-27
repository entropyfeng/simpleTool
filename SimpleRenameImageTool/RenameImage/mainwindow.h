#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QFileDialog>
#include <QMessageBox>
#include<iostream>
#include<QList>
#include<QFile>
#include"util.h"
#include<tuple>
#include<QTimer>
namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_nextGraphic_clicked();

    void on_openDir_clicked();

    void on_preGraphic_clicked();

    void on_editImageName_returnPressed();

    void on_pushButton_clicked();

private:
    void resetPos();
    void showPicture(QImage image);
    void showView(QFileInfo fileInfo);
    void loadFileInfoList();
    bool checkFileInfoList();
    void shiftMousePos();
    QList<QImage> getImageList(QString dir);
    QList<QFile> getFileList(QString dir);
    QFileInfoList fileInfoList;
    qint32 filePos=0;
    QString outPutPath="C:/Users/dell/Desktop/Verification-code-identification/pic";
    QString inPutPath="C:/Users/dell/Desktop/Verification-code-identification/pic";
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
