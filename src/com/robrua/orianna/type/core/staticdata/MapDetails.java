package com.robrua.orianna.type.core.staticdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.OriannaObject;
import com.robrua.orianna.type.core.common.GameMap;

public class MapDetails extends OriannaObject<com.robrua.orianna.type.dto.staticdata.MapDetails> {
    private static final long serialVersionUID = -1589775562057319474L;
    private Image image;
    private List<Long> unpurchasableItemIDs;
    private List<Item> unpurchasableItems;

    /**
     * @param data
     *            the underlying dto
     */
    public MapDetails(final com.robrua.orianna.type.dto.staticdata.MapDetails data) {
        super(data, com.robrua.orianna.type.dto.staticdata.MapDetails.class);
    }

    /**
     * The image
     *
     * @return the image
     */
    public Image getImage() {
        if(image == null) {
            image = new Image(data.getImage());
        }

        return image;
    }

    /**
     * The map
     *
     * @return the map
     */
    public GameMap getMap() {
        return GameMap.forID(super.getInteger(data.getMapId()));
    }

    /**
     * The map's name
     *
     * @return the map's name
     */
    public String getMapName() {
        return super.getString(data.getMapName());
    }

    /**
     * The IDs of the unpurchasable items on this map
     *
     * @return the IDs of the unpurchasable items on this map
     */
    public List<Long> getUnpurchasableItemIDs() {
        if(unpurchasableItemIDs == null) {
            unpurchasableItemIDs = new ArrayList<>();
            unpurchasableItemIDs.addAll(data.getUnpurchasableItemList());
        }

        return Collections.unmodifiableList(unpurchasableItemIDs);
    }

    /**
     * The unpurchasable items on this map
     *
     * @return the unpurchasable items on this map
     */
    public List<Item> getUnpurchasableItems() {
        if(unpurchasableItems == null) {
            unpurchasableItems = RiotAPI.getItems(data.getUnpurchasableItemList());
        }

        return Collections.unmodifiableList(unpurchasableItems);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MapInfo (" + getMapName() + ")";
    }
}
