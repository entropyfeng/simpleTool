package com.github.entropyfeng.catmock;

class Martix {


    public static void main(String[] args) {


        MapPanel mapPanel = new MapPanel();

        do {
            mapPanel.catList.clear();
            CatUtil.addCat(mapPanel, "cat1", 1);
            CatUtil.addCat(mapPanel, "cat2", 1);
            CatUtil.addCat(mapPanel, "cat3", 1);

            CatUtil.addCat(mapPanel,"cat3",1);
        } while (mapPanel.catList.size() != 4);

        Cat catOne=mapPanel.catList.get(0);
        Cat catTwo=mapPanel.catList.get(1);
        Cat catThree=mapPanel.catList.get(2);
        Cat catFour=mapPanel.catList.get(3);


        for (int i = 0; i < 100000000; i++) {

            CatUtil.moveOnce(mapPanel,catOne);
            CatUtil.moveOnce(mapPanel,catTwo);
            CatUtil.moveOnce(mapPanel,catThree);
            CatUtil.moveOnce(mapPanel,catFour);
            CatUtil.checkCatOverlap(mapPanel);
        }



    }

   }
