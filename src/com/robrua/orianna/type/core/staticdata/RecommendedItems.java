package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.GameMap;
import com.robrua.orianna.type.core.common.GameMode;

public class RecommendedItems extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Recommended> {
    private static final long serialVersionUID = 2135519499374549702L;
    private List<Block> blocks;

    /**
     * @param data
     *            the underlying dto
     */
    public RecommendedItems(final com.robrua.orianna.type.dto.staticdata.Recommended data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Recommended.class);
    }

    /**
     * Blocks
     *
     * @return blocks
     */
    public List<Block> getBlocks() {
        if(blocks == null) {
            blocks = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.staticdata.Block block : data.getBlocks()) {
                blocks.add(new Block(block));
            }
        }

        return Collections.unmodifiableList(blocks);
    }

    /**
     * Champion
     *
     * @return champion
     */
    public String getChampionName() {
        return super.getString(data.getChampion());
    }

    /**
     * Map
     *
     * @return map
     */
    public GameMap getMap() {
        return GameMap.forID(Long.parseLong(super.getString(data.getMap())));
    }

    /**
     * Mode
     *
     * @return mode
     */
    public GameMode getMode() {
        return GameMode.valueOf(super.getString(data.getMode()));
    }

    /**
     * Priority
     *
     * @return priority
     */
    public boolean getPriority() {
        return super.getBoolean(data.getPriority());
    }

    /**
     * Title
     *
     * @return title
     */
    public String getTitle() {
        return super.getString(data.getTitle());
    }

    /**
     * Type
     *
     * @return type
     */
    public String getType() {
        return super.getString(data.getType());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecommendedItems (" + getChampionName() + " on " + getMap() + " " + getMode() + ")";
    }
}
