package com.github.entropyfeng.catmock;

import java.util.HashSet;
import java.util.Set;

class Block {


    Set<Cat> catSet;

    BlockColor color;

    public Block(BlockColor color) {
        this.color = color;
        this.catSet = new HashSet<>();
    }

    public void addCat(Cat cat) {
        catSet.add(cat);
    }

    public void removeCat(Cat cat) {
        catSet.remove(cat);
    }

    public BlockColor getColor() {
        return color;
    }

    public void setColor(BlockColor color) {
        this.color = color;
    }



}
