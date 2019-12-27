# -*- coding:utf-8 -*-
import requests
import os
import Pillow


code_url = 'http://202.193.80.58:81/academic/getCaptcha.do?0.23611265529353553'  # 验证码URL
pic_url = 'C:/Users/dell/Desktop/Verification-code-identification/pic/'  # 验证码路径
pic_order_url = 'C:/Users/dell/Desktop/Verification-code-identification/pic/inOrder'  # 有序验证码路径
def get_img(val,localPath):
    for i in range(val,10000) :
        ir=requests.get(code_url)
        str1=localPath+'%d.jpg'%i
        print(str1)
        print(ir.content)
        open(str1 ,'wb').write(ir.content)


def read_captcha(path):
    image_array = []
    image_label = []
    file_list = os.listdir(path)
    for file in file_list:
        image = Image.open(path+'/'+file) #打开图片



if __name__=='__main__':
    pass

