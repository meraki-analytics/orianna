package com.robrua.orianna.type.dto.staticdata;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.robrua.orianna.type.dto.OriannaDto;

@Entity
@Table(name = "mapdetails")
public class MapDetails extends OriannaDto {
    private static final long serialVersionUID = 7442359340416835068L;
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @Id
    private Integer mapId;

    private String mapName;

    @ElementCollection
    @CollectionTable(name = "mapdetails_unpurchasable", joinColumns = @JoinColumn(name = "map_id") )
    private List<Long> unpurchasableItemList;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof MapDetails)) {
            return false;
        }
        final MapDetails other = (MapDetails)obj;
        if(image == null) {
            if(other.image != null) {
                return false;
            }
        }
        else if(!image.equals(other.image)) {
            return false;
        }
        if(mapId == null) {
            if(other.mapId != null) {
                return false;
            }
        }
        else if(!mapId.equals(other.mapId)) {
            return false;
        }
        if(mapName == null) {
            if(other.mapName != null) {
                return false;
            }
        }
        else if(!mapName.equals(other.mapName)) {
            return false;
        }
        if(unpurchasableItemList == null) {
            if(other.unpurchasableItemList != null) {
                return false;
            }
        }
        else if(!unpurchasableItemList.equals(other.unpurchasableItemList)) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataStoreIndexField(final Class<?> keyType) {
        if(keyType.equals(Integer.class)) {
            return "mapId";
        }
        if(keyType.equals(String.class)) {
            return "mapName";
        }
        return null;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the mapId
     */
    public Integer getMapId() {
        return mapId;
    }

    /**
     * @return the mapName
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * @return the unpurchasableItemList
     */
    public List<Long> getUnpurchasableItemList() {
        return unpurchasableItemList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (image == null ? 0 : image.hashCode());
        result = prime * result + (mapId == null ? 0 : mapId.hashCode());
        result = prime * result + (mapName == null ? 0 : mapName.hashCode());
        result = prime * result + (unpurchasableItemList == null ? 0 : unpurchasableItemList.hashCode());
        return result;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * @param mapId
     *            the mapId to set
     */
    public void setMapId(final Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * @param mapName
     *            the mapName to set
     */
    public void setMapName(final String mapName) {
        this.mapName = mapName;
    }

    /**
     * @param unpurchasableItemList
     *            the unpurchasableItemList to set
     */
    public void setUnpurchasableItemList(final List<Long> unpurchasableItemList) {
        this.unpurchasableItemList = unpurchasableItemList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MapInfo [mapName=" + mapName + ", mapId=" + mapId + ", unpurchasableItemList=" + unpurchasableItemList + ", image=" + image + "]";
    }
}
