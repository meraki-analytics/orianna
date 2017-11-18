package com.merakianalytics.orianna.types.data.spectator;

import com.merakianalytics.orianna.types.data.CoreData;

public class GameCustomizationObject extends CoreData {
    private static final long serialVersionUID = 1530217080261396957L;
    private String category, content;

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final GameCustomizationObject other = (GameCustomizationObject)obj;
        if(category == null) {
            if(other.category != null) {
                return false;
            }
        } else if(!category.equals(other.category)) {
            return false;
        }
        if(content == null) {
            if(other.content != null) {
                return false;
            }
        } else if(!content.equals(other.content)) {
            return false;
        }
        return true;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (category == null ? 0 : category.hashCode());
        result = prime * result + (content == null ? 0 : content.hashCode());
        return result;
    }

    /**
     * @param category
     *        the category to set
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * @param content
     *        the content to set
     */
    public void setContent(final String content) {
        this.content = content;
    }
}
