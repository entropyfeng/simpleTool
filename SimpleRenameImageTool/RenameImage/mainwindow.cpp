#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::showPicture(QImage image){
    QPixmap qPixMap=QPixmap::fromImage(image);
    qint32 width=ui->showGraphic->width();
    qint32 height=ui->showGraphic->height();
    QPixmap temp=  qPixMap.scaled(width,height,Qt::KeepAspectRatio);
    ui->showGraphic->setPixmap(temp);
}

void MainWindow::showView(QFileInfo fileInfo)
{
    if(fileInfoList.size()!=0){
        QImage *image=new QImage(fileInfo.absoluteFilePath());
        showPicture(*image);
        ui->editImageName->setText(fileInfo.fileName().split(".")[0]);
    }else{

        QMessageBox::warning(this,"文件中图片为空","Don't exist picture!");
    }
}



//加载目录包含的图片文件
void MainWindow::loadFileInfoList(){
    QFileDialog qFileDialog;
    qFileDialog.setDirectory(inPutPath);//设置默认选择目录
    QDir *dir=new QDir(inPutPath);//创建目录
    for(auto temp: dir->entryInfoList(QDir::Files)){
        if(Util::getImageSuffix().contains(temp.suffix())){
            fileInfoList.append(temp);
        }
    }
    filePos=0;
    delete dir;
}


void MainWindow::on_nextGraphic_clicked()
{

    if(checkFileInfoList()){
        filePos++;
        resetPos();
        showView(fileInfoList.at(filePos));

    }
}

void MainWindow::on_openDir_clicked()
{
    inPutPath=  QFileDialog::getExistingDirectory(this);
    loadFileInfoList();
}


bool MainWindow::checkFileInfoList()
{
    QString chinese="请先选择目录或该目录不存在文件";
    QString english="Please choice Dir or the dir is empty";
    bool result=true;
    if(fileInfoList.isEmpty()){
        QMessageBox::warning(this,english,chinese);
        result= false;
    }
    return result;
}



void MainWindow::on_preGraphic_clicked()
{

    if(checkFileInfoList()){
        MainWindow::filePos--;
        resetPos();
        showView(MainWindow::fileInfoList.at(MainWindow::filePos));

    }
}

void MainWindow::resetPos()
{

    qint32 counts= MainWindow::fileInfoList.size();
    if(MainWindow::filePos>=counts){
        MainWindow::filePos=counts-1;
    }else if (MainWindow::filePos<0) {
        MainWindow::filePos=0;
    }

}


void MainWindow::on_editImageName_returnPressed()
{
    QFileInfo fileinfo= fileInfoList.at(filePos);
    std::cout<<fileinfo.fileName().toStdString()<<std::endl;

    QFile *file=new QFile(fileinfo.absoluteFilePath());
    QString string= ui->editImageName->text().trimmed();
    string+=".";
    string+=fileinfo.suffix();
    file->rename(outPutPath+"/"+string);
    fileInfoList.removeOne(fileinfo);
    filePos--;
    resetPos();
    if(fileInfoList.size()!=0){
        showView(fileInfoList.at(filePos));
    }else{
        QMessageBox::warning(this,"处理结束","process end");
        this->close();
    }

}

void MainWindow::on_pushButton_clicked()
{
    QFileDialog qFileDialog;
    qFileDialog.setDirectory(outPutPath);//设置默认目录
    outPutPath=qFileDialog.getExistingDirectory(this);//选择输出路径
}
