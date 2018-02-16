package com.merakianalytics.orianna.types.dto.championgg;

import java.util.Map;

public class ChampionGGRolePredictionResults
{
    private float probability;
    private float conficende;
    
    private Map<ChampionGGRole, Integer> bestRoles;
    private Map<ChampionGGRole, Integer> secondRoles;
    
    public ChampionGGRolePredictionResults(float metric, float conficende, Map<ChampionGGRole, Integer> bestRoles, Map<ChampionGGRole, Integer> secondRoles)
    {
        this.probability = metric;
        this.conficende = conficende;
        this.bestRoles = bestRoles;
        this.secondRoles = secondRoles;
    }
    
    public float getProbability()
    {
        return probability;
    }
    
    public float getConficende()
    {
        return conficende;
    }
    
    public Map<ChampionGGRole, Integer> getBestRoles()
    {
        return bestRoles;
    }
    
    public Map<ChampionGGRole, Integer> getSecondRoles()
    {
        return secondRoles;
    }
}
