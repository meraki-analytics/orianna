package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.type.core.OriannaObject;

public class Block extends OriannaObject<com.robrua.orianna.type.dto.staticdata.Block> {
    private static final long serialVersionUID = 9137827019045747915L;
    private List<BlockItem> items;

    /**
     * @param data
     *            the underlying dto
     */
    public Block(final com.robrua.orianna.type.dto.staticdata.Block data) {
        super(data, com.robrua.orianna.type.dto.staticdata.Block.class);
    }

    /**
     * Items
     *
     * @return items
     */
    public List<BlockItem> getItems() {
        if(items == null) {
            items = new ArrayList<>();
            for(final com.robrua.orianna.type.dto.staticdata.BlockItem item : data.getItems()) {
                items.add(new BlockItem(item));
            }
        }

        return Collections.unmodifiableList(items);
    }

    /**
     * Rec math
     *
     * @return rec math
     */
    public boolean getRecMath() {
        return super.getBoolean(data.getRecMath());
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
        return "Block (" + getType() + ")";
    }
}
