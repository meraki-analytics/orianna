package com.merakianalytics.orianna.types.dto.championgg;

import com.merakianalytics.orianna.types.dto.DataObject;

/*
    TODO: this class only contains the data needed to generate a role mapping,
    it should be extended to the full dataset returned by the api (http://api.champion.gg/docs/) (or replaced by the champion.gg plugin)
*/
public class ChampionData extends DataObject
{
    
    private static final long serialVersionUID = 684097011300360286L;
    
    private int    championId;
    private String role;
    private float  playRate;
    
    public int getChampionId()
    {
        return championId;
    }
    
    public String getRole()
    {
        return role;
    }
    
    public float getPlayRate()
    {
        return playRate;
    }
}
