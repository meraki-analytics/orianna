package com.merakianalytics.orianna.types.dto.championgg;

import com.merakianalytics.orianna.types.dto.DataObject;

import java.util.List;

public class ChampionDataList extends DataObject
{
    private static final long serialVersionUID = 7381442893886061101L;
    
    private List<ChampionData> data;
    
    public List<ChampionData> getData()
    {
        return data;
    }
}
