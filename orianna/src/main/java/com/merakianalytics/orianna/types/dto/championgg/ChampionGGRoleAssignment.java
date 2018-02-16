package com.merakianalytics.orianna.types.dto.championgg;

import java.util.*;

public class ChampionGGRoleAssignment
{
    public Integer getTop()
    {
        return top;
    }
    
    public Integer getJungle()
    {
        return jungle;
    }
    
    public Integer getMid()
    {
        return mid;
    }
    
    public Integer getAdc()
    {
        return adc;
    }
    
    public Integer getSup()
    {
        return sup;
    }
    
    public ChampionGGRoleAssignment(Integer top, Integer jungle, Integer mid, Integer adc, Integer sup)
    {
        this.top = top;
        this.jungle = jungle;
        this.mid = mid;
        this.adc = adc;
        this.sup = sup;
    }
    
    private Integer top;
    private Integer jungle;
    private Integer mid;
    private Integer adc;
    private Integer sup;
    
    public Map<ChampionGGRole, Integer> getFields()
    {
        
        Map<ChampionGGRole, Integer> data = new EnumMap<>(ChampionGGRole.class);
        data.put(ChampionGGRole.TOP, top);
        data.put(ChampionGGRole.JUNGLE, jungle);
        data.put(ChampionGGRole.MIDDLE, mid);
        data.put(ChampionGGRole.DUO_CARRY, adc);
        data.put(ChampionGGRole.DUO_SUPPORT, sup);
        
        data.values().removeAll(Collections.singleton(null));
        
        return data;
        
        /*
        would be nice if this worked, but immutablemap doesnt clean nulls by default
        return ImmutableMap.of(ChampionGGRole.TOP, top,
                               ChampionGGRole.JUNGLE, jungle,
                               ChampionGGRole.MIDDLE, mid,
                               ChampionGGRole.DUO_CARRY, adc,
                               ChampionGGRole.DUO_SUPPORT, sup);
                               */
        
    }
    
    
    public static ChampionGGRoleAssignment fromMap(Map<ChampionGGRole, Integer> fixed)
    {
        return new ChampionGGRoleAssignment(fixed.get(ChampionGGRole.TOP), fixed.get(ChampionGGRole.JUNGLE), fixed.get(ChampionGGRole.MIDDLE), fixed.get(ChampionGGRole.DUO_CARRY), fixed.get(ChampionGGRole.DUO_SUPPORT));
    }
}
