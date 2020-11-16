package com.github.entropyfeng.catmock;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

class CatUtil {


    public static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Position generateCatLocation(MapPanel mapPanel) {

        int catX = random.nextInt(mapPanel.mapX);
        int catY = random.nextInt(mapPanel.mapY);
        Block tempBlock = mapPanel.map[catX][catY];
        if (tempBlock.catSet.isEmpty()) {
            return new Position(catX, catY);
        } else {
            return null;
        }
    }

    public static boolean leftAble(MapPanel mapPanel, Position position) {
        int tempX = position.x;
        int tempY = position.y;
        Block[][] blocks = mapPanel.map;
        return tempX != 0 && !blocks[tempX - 1][tempY].color.equals(BlockColor.BLACK);
    }

    public static boolean rightAble(MapPanel mapPanel, Position position) {
        int tempX = position.x;
        int tempY = position.y;
        Block[][] blocks = mapPanel.map;
        return tempX != mapPanel.mapX - 1 && !blocks[tempX + 1][tempY].color.equals(BlockColor.BLACK);
    }

    public static boolean upAble(MapPanel mapPanel, Position position) {

        int tempX = position.x;
        int tempY = position.y;
        Block[][] blocks = mapPanel.map;
        return tempY != 0 && !blocks[tempX][tempY - 1].color.equals(BlockColor.BLACK);
    }

    public static boolean downAble(MapPanel mapPanel, Position position) {
        int tempX = position.x;
        int tempY = position.y;
        Block[][] blocks = mapPanel.map;
        return tempY != mapPanel.mapY - 1 && !blocks[tempX][tempY + 1].color.equals(BlockColor.BLACK);
    }

    public static boolean[] generateDirectionAble(MapPanel mapPanel, Position position) {
        boolean[] res = new boolean[4];
        res[0] = leftAble(mapPanel, position);
        res[1] = rightAble(mapPanel, position);
        res[2] = upAble(mapPanel, position);
        res[3] = downAble(mapPanel, position);
        return res;
    }

    public static boolean hasEmptyPosition(MapPanel mapPanel) {


        for (int i = 0; i < mapPanel.mapX; i++) {
            for (int j = 0; j < mapPanel.mapY; j++) {
                BlockColor color = mapPanel.map[i][j].color;
                if (BlockColor.WHITE.equals(color) || BlockColor.YELLOW.equals(color)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static Direction convertToDirection(int pos) {
        assert pos >= 0 && pos <= 3;
        switch (pos) {
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            default:
                return Direction.DOWN;
        }
    }

    public static Direction randomDirection(boolean[] directionAble) {

        assert directionAble.length == 4;

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (directionAble[i]) {
                count++;
            }
        }
        //if all four direction can not take
        if (count == 0) {
            return null;
        }

        int pos = random.nextInt(count);

        for (int i = 0; i < 4; i++) {
            if (directionAble[i]) {
                if (pos == 0) {
                    return convertToDirection(i);
                } else {
                    pos--;
                }
            }
        }
        throw new Error("you are not except access !");
    }

    /**
     * put new cat into panel,multi cats not allowed placed in  a same block when init
     *
     * @param mapPanel the panel store the background info of this scenario
     * @param catName  the cat's name
     * @param speed    the cat's speeds
     * @return true->success add the cat
     * false->fail add the cat
     */
    public static boolean addCat(MapPanel mapPanel, String catName, int speed) {

        Position position = generateCatLocation(mapPanel);

        boolean res = false;
        if (position != null) {
            boolean[] directionAble = generateDirectionAble(mapPanel, position);
            Direction direction = randomDirection(directionAble);
            if (direction != null) {
                Cat cat = new Cat(catName, position, speed, direction);
                mapPanel.catList.add(cat);
                mapPanel.getBlock(position).addCat(cat);
                res = true;
            }

        }

        return res;
    }

    public static void changeDirection(MapPanel mapPanel, Cat cat) {
        boolean[] directionAble = generateDirectionAble(mapPanel, cat.position);
        Direction direction = randomDirection(directionAble);
        if (direction != null) {
            cat.direction = direction;
        }
    }

    public static boolean leftMoveOneStep(MapPanel mapPanel, Cat cat) {
        boolean res = false;
        Position prePosition = cat.position;
        if (CatUtil.leftAble(mapPanel, prePosition)) {
            mapPanel.getBlock(prePosition).removeCat(cat);
            prePosition = new Position(prePosition.x - 1, prePosition.y);
            mapPanel.getBlock(prePosition).addCat(cat);
            cat.position = prePosition;

            res = true;
        }
        return res;
    }

    public static boolean rightMoveOneStep(MapPanel mapPanel, Cat cat) {
        boolean res = false;
        Position prePosition = cat.position;
        if (CatUtil.rightAble(mapPanel, prePosition)) {
            mapPanel.getBlock(prePosition).removeCat(cat);
            prePosition = new Position(prePosition.x + 1, prePosition.y);
            mapPanel.getBlock(prePosition).addCat(cat);
            cat.position = prePosition;
            res = true;
        }
        return res;
    }

    public static boolean upMoveOneStep(MapPanel mapPanel, Cat cat) {
        boolean res = false;
        Position prePosition = cat.position;
        if (CatUtil.upAble(mapPanel, prePosition)) {
            mapPanel.getBlock(prePosition).removeCat(cat);
            prePosition = new Position(prePosition.x, prePosition.y - 1);
            mapPanel.getBlock(prePosition).addCat(cat);
            cat.position = prePosition;
            res = true;
        }
        return res;
    }

    public static boolean downMoveOneStep(MapPanel mapPanel, Cat cat) {
        boolean res = false;
        Position prePosition = cat.position;
        if (CatUtil.downAble(mapPanel, prePosition)) {
            mapPanel.getBlock(prePosition).removeCat(cat);
            prePosition = new Position(prePosition.x, prePosition.y + 1);
            mapPanel.getBlock(prePosition).addCat(cat);
            cat.position = prePosition;
            res = true;
        }
        return res;
    }

    public static void moveOnce(MapPanel mapPanel, Cat cat) {

        Position beginPosition = cat.position;
        Direction direction = cat.direction;
        if (direction == null) {
            System.out.println("cat:" + cat.name + "track in wrap !");
            throw new Error("you are not excepted to access it ");
        } else {
            if (Direction.LEFT.equals(direction)) {
                for (int i = 0; i < cat.speed; i++) {
                    boolean res = leftMoveOneStep(mapPanel, cat);
                    if (!res) {
                        break;
                    }
                }
            } else if (Direction.RIGHT.equals(direction)) {
                for (int i = 0; i < cat.speed; i++) {
                    boolean res = rightMoveOneStep(mapPanel, cat);
                    if (!res) {
                        break;
                    }
                }
            } else if (Direction.UP.equals(direction)) {
                for (int i = 0; i < cat.speed; i++) {
                    boolean res = upMoveOneStep(mapPanel, cat);
                    if (!res) {
                        break;
                    }
                }
            } else {
                for (int i = 0; i < cat.speed; i++) {
                    boolean res = downMoveOneStep(mapPanel, cat);
                    if (!res) {
                        break;
                    }
                }
            }

            Position endPosition = cat.position;
            if (Config.showCatMove){
                System.out.println(cat.name + " move from " + beginPosition + " to " + endPosition);
            }

            changeDirection(mapPanel, cat);

        }
    }

    public static void checkCatOverlap(MapPanel mapPanel) {
        Map<Position, List<Cat>> map = mapPanel.catList.stream().collect(Collectors.groupingBy(Cat::getPosition));


        if (map.size() < mapPanel.catList.size()) {

            map.forEach(((position, cats) -> {
                if (cats.size() >= 4) {
                    List<String> catNames = cats.stream().map(Cat::getName).collect(Collectors.toList());

                    System.out.println(catNames + " overlap at " + position);
                }
            }));
        }
    }
}
