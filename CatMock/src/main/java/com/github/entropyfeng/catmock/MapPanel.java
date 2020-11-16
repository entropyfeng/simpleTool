package com.github.entropyfeng.catmock;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class MapPanel {

    int mapX;
    int mapY;
    Block[][] map;
    ArrayList<Cat> catList;

    public Block getBlock(Position position) {
        return map[position.x][position.y];
    }

    public MapPanel() {
        this.catList = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int x = random.nextInt(10, 11);
        int y = random.nextInt(10, 11);
        this.map = new Block[x][y];
        this.mapX = x;
        this.mapY = y;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                BlockColor obj;
                switch (random.nextInt(0, 10000)) {
                    case 0:
                        obj = BlockColor.BLACK;
                        break;
                    case 1:
                        obj = BlockColor.YELLOW;
                        break;
                    default:
                        obj = BlockColor.WHITE;
                }
                this.map[i][j] = new Block(obj);
            }
        }

    }
}
