package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findAllBlocks().stream()
                .filter(b -> b.getColor().equalsIgnoreCase(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findAllBlocks().stream()
                .filter(b -> b.getMaterial().equalsIgnoreCase(material))
                .toList();
    }

    @Override
    public int count() {
        return findAllBlocks().size();
    }

    private List<Block> findAllBlocks() {
        List<Block> b = new ArrayList<>();

        for (Block block : blocks) {
            if (isCompositeBlock(block)) {
                b.addAll(unpackCompositeBlock(block));
            } else {
                b.add(block);
            }
        }
        return b;
    }

    private List<Block> unpackCompositeBlock(Block compositeBlock) {
        CompositeBlock b = (CompositeBlock) compositeBlock;
        return b.getBlocks();
    }

    private boolean isCompositeBlock(Block block) {
        return block instanceof CompositeBlock;
    }


}
