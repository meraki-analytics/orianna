package com.robrua.orianna.type.core.staticdata;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.exception.MissingDataException;

public class BlockItem extends OriannaObject<com.robrua.orianna.type.dto.staticdata.BlockItem> {
    private static final long serialVersionUID = -966690596010664123L;
    private Item item;

    /**
     * @param data
     *            the underlying dto
     */
    public BlockItem(final com.robrua.orianna.type.dto.staticdata.BlockItem data) {
        super(data, com.robrua.orianna.type.dto.staticdata.BlockItem.class);
    }

    /**
     * Count
     *
     * @return count
     */
    public int getCount() {
        return super.getInteger(data.getCount());
    }

    /**
     * Item
     *
     * @return item
     */
    public Item getItem() {
        if(item != null) {
            return item;
        }

        final Integer i = data.getId();
        if(i == null) {
            throw new MissingDataException("Item ID is null.");
        }

        item = RiotAPI.getItem(i.longValue());
        return item;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BlockItem (" + getItem() + " x " + getCount() + ")";
    }
}
