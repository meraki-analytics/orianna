package com.merakianalytics.orianna.types.data.match;

import java.util.List;

import com.merakianalytics.orianna.types.data.CoreData;

public class RuneStats extends CoreData {
    private static final long serialVersionUID = -461218494339044648L;
    private int id;
    private List<Integer> variables;

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
        final RuneStats other = (RuneStats)obj;
        if(id != other.id) {
            return false;
        }
        if(variables == null) {
            if(other.variables != null) {
                return false;
            }
        } else if(!variables.equals(other.variables)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the variables
     */
    public List<Integer> getVariables() {
        return variables;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (variables == null ? 0 : variables.hashCode());
        return result;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param variables
     *        the variables to set
     */
    public void setVariables(final List<Integer> variables) {
        this.variables = variables;
    }
}
