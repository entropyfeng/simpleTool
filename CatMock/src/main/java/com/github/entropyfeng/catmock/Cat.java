package com.github.entropyfeng.catmock;

import java.util.Objects;


/**
 * @author entropyfeng
 */
public class Cat {
    public Cat(String name, Position position, int speed, Direction direction) {
        this.name = name;
        this.speed = speed;
        this.position = position;
        this.direction = direction;
    }

    String name;

    Position position;
    int speed;
    Direction direction;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cat)) {
            return false;
        }
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}


