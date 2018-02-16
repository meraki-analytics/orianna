package com.merakianalytics.orianna.types.dto.championgg;

import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.merakianalytics.orianna.datapipeline.ChampionGG;
import com.merakianalytics.orianna.types.core.searchable.SearchableLists;
import com.merakianalytics.orianna.types.core.staticdata.Champion;

import java.util.*;
import java.util.Map.Entry;

import static sun.awt.FontConfiguration.*;

public final class ChampionGGRolePredictor
{
    private ChampionGGRolePredictor()
    {
        // Hide public constructor
    }
    
    private static final Map<Integer, EnumMap<ChampionGGRole, Float>> roleData = new HashMap<>();
    
    public static void prepareDataset(ChampionDataList list)
    {
        if (!roleData.isEmpty())
        {
            return;
        }
        
        for (ChampionData cgg : list.getData())
        {
            Integer        champion = cgg.getChampionId();
            ChampionGGRole role     = ChampionGGRole.valueOf(cgg.getRole());
            float          playRate = cgg.getPlayRate();
            
            EnumMap<ChampionGGRole, Float> inner = roleData.get(champion);
            if (inner == null)
            {
                inner = new EnumMap<>(ChampionGGRole.class);
            }
            
            inner.put(role, playRate);
            roleData.put(champion, inner);
        }
        
        for (Entry<Integer, EnumMap<ChampionGGRole, Float>> returnEntry : roleData.entrySet())
        {
            int                            key   = returnEntry.getKey();
            EnumMap<ChampionGGRole, Float> value = returnEntry.getValue();
            
            int   countMissing = 0;
            float totalMissing = 1;
            
            for (ChampionGGRole role : ChampionGGRole.values())
            {
                if (!value.containsKey(role))
                {
                    countMissing++;
                } else
                {
                    totalMissing -= value.get(role);
                }
            }
            
            // countMissing should never be 0 here, but just in case;
            if (0 == countMissing)
            {
                countMissing = 1;
            }
            
            float missingPerUnCountedRole = totalMissing / countMissing;
            
            for (ChampionGGRole role : ChampionGGRole.values())
            {
                if (!value.containsKey(role))
                {
                    roleData.get(key).put(role, -1 + missingPerUnCountedRole);
                }
            }
        }
    }
    
    public static ChampionGGRolePredictionResults iterativeGetRoles(List<Integer> champions, ChampionGGRoleAssignment defaults)
    {
        Map<ChampionGGRole, Integer>                       fixed         = defaults.getFields();
        final Map<Integer, EnumMap<ChampionGGRole, Float>> roleDataClone = new HashMap<>(roleData);
        
        Map<ChampionGGRole, Integer> roles           = new EnumMap<>(ChampionGGRole.class);
        Map<ChampionGGRole, Integer> secondBestRoles = null;
        
        float prob           = Float.NEGATIVE_INFINITY;
        float secondBestProb = Float.NEGATIVE_INFINITY;
        
        while (fixed.keySet().size() < 4)
        {
            for (ChampionGGRole roleType : fixed.keySet())
            {
                for (Entry<Integer, EnumMap<ChampionGGRole, Float>> entry : roleDataClone.entrySet())
                {
                    Integer                    k = entry.getKey();
                    Map<ChampionGGRole, Float> v = entry.getValue();
                    
                    if (fixed.values().contains(k))
                    {
                        continue;
                    }
                    
                    float playRate = v.get(roleType);
                    v.put(roleType, (float) -1);
                    
                    if (playRate > 0)
                    {
                        int   rolesLeft    = SearchableLists.from(Lists.newArrayList(v.values())).filter(isGreaterThanZero).size();
                        float toDistribute = 0;
                        if (rolesLeft > 0)
                        {
                            toDistribute = playRate / rolesLeft;
                        }
                        
                        for (Entry<ChampionGGRole, Float> playrates : v.entrySet())
                        {
                            if (playrates.getValue() < 0)
                            {
                                continue;
                            }
                            
                            playrates.setValue(playrates.getValue() + toDistribute);
                        }
                    }
                }
            }
            
            
            ChampionGGRolePredictionResults rpcs = getRoles(roleDataClone, champions, ChampionGGRoleAssignment.fromMap(fixed), verbose);
            roles = rpcs.getBestRoles();
            prob = rpcs.getProbability();
            
            ChampionGGRolePredictionResults rpcsClone;
            if (rpcs.getSecondRoles() != null)
            {
                rpcsClone = getRoles(roleDataClone, champions, ChampionGGRoleAssignment.fromMap(rpcs.getSecondRoles()), verbose);
                
                if (prob > rpcsClone.getProbability() && rpcsClone.getProbability() > secondBestProb)
                {
                    secondBestProb = rpcsClone.getProbability();
                    secondBestRoles = rpcs.getSecondRoles();
                }
            }
            
            List<Entry<ChampionGGRole, Integer>> best = SearchableLists.from(new ArrayList<>(roles.entrySet())).filter(isNotInResult(fixed));
            Collections.sort(best, new Comparator<Entry<ChampionGGRole, Integer>>()
            {
                @Override
                public int compare(Entry<ChampionGGRole, Integer> a, Entry<ChampionGGRole, Integer> b)
                {
                    return getSortOrder(roleData, a, b);
                }
            });
            
            Entry<ChampionGGRole, Integer> bbest = best.get(0);
            fixed.put(bbest.getKey(), bbest.getValue());
        }
        
        float confidence = (prob - secondBestProb) / prob;
        if (verbose)
        {
            for (ChampionGGRole role : ChampionGGRole.values())
            {
                System.out.format("%s: %s  (%s%%)%n", role, Champion.withId(roles.get(role)).get().getName(), ChampionGG.DECIMAL_FORMAT.format(100 * roleData.get(roles.get(role)).get(role)));
            }
            
            System.out.format("Probability: %s%n", ChampionGG.DECIMAL_FORMAT.format(100 * prob));
            
            if (secondBestRoles == null)
            {
                System.out.format("Confidence: %s%n", ChampionGG.DECIMAL_FORMAT.format(100 * confidence));
            } else
            {
                StringBuilder alternative = new StringBuilder();
                for (ChampionGGRole role : ChampionGGRole.values())
                {
                    if (!roles.get(role).equals(secondBestRoles.get(role)))
                    {
                        alternative.append(String.format("%s: %s, ", role, Champion.withId(secondBestRoles.get(role)).get().getName()));
                    }
                }
                System.out.format("Confidence: %s (Alternative is %s)%n", ChampionGG.DECIMAL_FORMAT.format(100 * confidence), alternative);
            }
        }
        
        return new ChampionGGRolePredictionResults(prob, confidence, roles, secondBestRoles);
    }
    
    private static ChampionGGRolePredictionResults getRoles(Map<Integer, EnumMap<ChampionGGRole, Float>> roleData, List<Integer> champions, ChampionGGRoleAssignment positions, boolean verbose)
    {
        EnumMap<ChampionGGRole, Integer> bestRoles       = new EnumMap<>(ChampionGGRole.class);
        EnumMap<ChampionGGRole, Integer> secondBestRoles = null;
        
        EnumMap<ChampionGGRole, Float> bestPlay       = new EnumMap<>(ChampionGGRole.class);
        EnumMap<ChampionGGRole, Float> secondBestPlay = null;
        
        float bestMetric;
        float secondBestMetric = Float.NEGATIVE_INFINITY;
        
        if (!positions.getFields().containsValue(null) && positions.getFields().size() == 5)
        {
            bestRoles.put(ChampionGGRole.TOP, positions.getTop());
            bestRoles.put(ChampionGGRole.JUNGLE, positions.getJungle());
            bestRoles.put(ChampionGGRole.MIDDLE, positions.getMid());
            bestRoles.put(ChampionGGRole.DUO_CARRY, positions.getAdc());
            bestRoles.put(ChampionGGRole.DUO_SUPPORT, positions.getSup());
            
            bestPlay.put(ChampionGGRole.TOP, roleData.get(positions.getTop()).get(ChampionGGRole.TOP));
            bestPlay.put(ChampionGGRole.JUNGLE, roleData.get(positions.getJungle()).get(ChampionGGRole.JUNGLE));
            bestPlay.put(ChampionGGRole.MIDDLE, roleData.get(positions.getMid()).get(ChampionGGRole.MIDDLE));
            bestPlay.put(ChampionGGRole.DUO_CARRY, roleData.get(positions.getAdc()).get(ChampionGGRole.DUO_CARRY));
            bestPlay.put(ChampionGGRole.DUO_SUPPORT, roleData.get(positions.getSup()).get(ChampionGGRole.DUO_SUPPORT));
            
            List<Float> metricList3 = SearchableLists.from(Lists.newArrayList(bestPlay.values()));
            float       sum3        = 0;
            for (Float f : metricList3)
            {
                sum3 += f;
            }
            
            bestMetric = sum3 / 5;
        } else
        {
            bestRoles.put(ChampionGGRole.TOP, champions.get(0));
            bestRoles.put(ChampionGGRole.JUNGLE, champions.get(1));
            bestRoles.put(ChampionGGRole.MIDDLE, champions.get(2));
            bestRoles.put(ChampionGGRole.DUO_CARRY, champions.get(3));
            bestRoles.put(ChampionGGRole.DUO_SUPPORT, champions.get(4));
            
            bestPlay.put(ChampionGGRole.TOP, roleData.get(champions.get(0)).get(ChampionGGRole.TOP));
            bestPlay.put(ChampionGGRole.JUNGLE, roleData.get(champions.get(1)).get(ChampionGGRole.JUNGLE));
            bestPlay.put(ChampionGGRole.MIDDLE, roleData.get(champions.get(2)).get(ChampionGGRole.MIDDLE));
            bestPlay.put(ChampionGGRole.DUO_CARRY, roleData.get(champions.get(3)).get(ChampionGGRole.DUO_CARRY));
            bestPlay.put(ChampionGGRole.DUO_SUPPORT, roleData.get(champions.get(4)).get(ChampionGGRole.DUO_SUPPORT));
            
            
            List<Float> metricList3 = SearchableLists.from(Lists.newArrayList(bestPlay.values()));
            float       sum3        = 0;
            for (Float f : metricList3)
            {
                sum3 += f;
            }
            
            bestMetric = sum3 / 5;
            
            secondBestRoles = new EnumMap<>(ChampionGGRole.class);
            secondBestRoles.put(ChampionGGRole.TOP, champions.get(0));
            secondBestRoles.put(ChampionGGRole.JUNGLE, champions.get(1));
            secondBestRoles.put(ChampionGGRole.MIDDLE, champions.get(2));
            secondBestRoles.put(ChampionGGRole.DUO_CARRY, champions.get(3));
            secondBestRoles.put(ChampionGGRole.DUO_SUPPORT, champions.get(4));
            
            secondBestPlay = new EnumMap<>(ChampionGGRole.class);
            secondBestPlay.put(ChampionGGRole.TOP, roleData.get(champions.get(0)).get(ChampionGGRole.TOP));
            secondBestPlay.put(ChampionGGRole.JUNGLE, roleData.get(champions.get(1)).get(ChampionGGRole.JUNGLE));
            secondBestPlay.put(ChampionGGRole.MIDDLE, roleData.get(champions.get(2)).get(ChampionGGRole.MIDDLE));
            secondBestPlay.put(ChampionGGRole.DUO_CARRY, roleData.get(champions.get(3)).get(ChampionGGRole.DUO_CARRY));
            secondBestPlay.put(ChampionGGRole.DUO_SUPPORT, roleData.get(champions.get(4)).get(ChampionGGRole.DUO_SUPPORT));
            
            
            List<Float> metricList2 = SearchableLists.from(Lists.newArrayList(secondBestPlay.values()));
            float       sum2        = 0;
            for (Float f : metricList2)
            {
                sum2 += f;
            }
            
            secondBestMetric = sum2 / 5;
            List<List<Integer>> championPermutations = Lists.newArrayList(Collections2.permutations(champions));
            
            
            for (List<Integer> champs : championPermutations)
            {
                Map<ChampionGGRole, Float> roles = new EnumMap<>(ChampionGGRole.class);
                roles.put(ChampionGGRole.TOP, roleData.get(champs.get(0)).get(ChampionGGRole.TOP));
                roles.put(ChampionGGRole.JUNGLE, roleData.get(champs.get(1)).get(ChampionGGRole.JUNGLE));
                roles.put(ChampionGGRole.MIDDLE, roleData.get(champs.get(2)).get(ChampionGGRole.MIDDLE));
                roles.put(ChampionGGRole.DUO_CARRY, roleData.get(champs.get(3)).get(ChampionGGRole.DUO_CARRY));
                roles.put(ChampionGGRole.DUO_SUPPORT, roleData.get(champs.get(4)).get(ChampionGGRole.DUO_SUPPORT));
                
                
                if (positions.getTop() != null && !champs.get(0).equals(positions.getTop()))
                {
                    continue;
                }
                if (positions.getJungle() != null && !champs.get(1).equals(positions.getJungle()))
                {
                    continue;
                }
                if (positions.getMid() != null && !champs.get(2).equals(positions.getMid()))
                {
                    continue;
                }
                if (positions.getAdc() != null && !champs.get(3).equals(positions.getAdc()))
                {
                    continue;
                }
                if (positions.getSup() != null && !champs.get(4).equals(positions.getSup()))
                {
                    continue;
                }
                
                // .map or.reduce please T_T
                List<Float> metricList = SearchableLists.from(Lists.newArrayList(roles.values()));
                float       sum        = 0;
                for (Float f : metricList)
                {
                    sum += f;
                }
                float metric = sum / 5;
                
                if (metric > bestMetric)
                {
                    secondBestMetric = bestMetric;
                    secondBestRoles = bestRoles;
                    bestMetric = metric;
                    
                    bestRoles = new EnumMap<>(ChampionGGRole.class);
                    bestRoles.put(ChampionGGRole.TOP, champs.get(0));
                    bestRoles.put(ChampionGGRole.JUNGLE, champs.get(1));
                    bestRoles.put(ChampionGGRole.MIDDLE, champs.get(2));
                    bestRoles.put(ChampionGGRole.DUO_CARRY, champs.get(3));
                    bestRoles.put(ChampionGGRole.DUO_SUPPORT, champs.get(4));
                    
                    bestPlay = new EnumMap<>(ChampionGGRole.class);
                    bestPlay.put(ChampionGGRole.TOP, roleData.get(champs.get(0)).get(ChampionGGRole.TOP));
                    bestPlay.put(ChampionGGRole.JUNGLE, roleData.get(champs.get(1)).get(ChampionGGRole.JUNGLE));
                    bestPlay.put(ChampionGGRole.MIDDLE, roleData.get(champs.get(2)).get(ChampionGGRole.MIDDLE));
                    bestPlay.put(ChampionGGRole.DUO_CARRY, roleData.get(champs.get(3)).get(ChampionGGRole.DUO_CARRY));
                    bestPlay.put(ChampionGGRole.DUO_SUPPORT, roleData.get(champs.get(4)).get(ChampionGGRole.DUO_SUPPORT));
                }
                
                if (bestMetric > metric && metric > secondBestMetric)
                {
                    secondBestMetric = metric;
                    
                    secondBestRoles = new EnumMap<>(ChampionGGRole.class);
                    secondBestRoles.put(ChampionGGRole.TOP, champs.get(0));
                    secondBestRoles.put(ChampionGGRole.JUNGLE, champs.get(1));
                    secondBestRoles.put(ChampionGGRole.MIDDLE, champs.get(2));
                    secondBestRoles.put(ChampionGGRole.DUO_CARRY, champs.get(3));
                    secondBestRoles.put(ChampionGGRole.DUO_SUPPORT, champs.get(4));
                    
                    secondBestPlay = new EnumMap<>(ChampionGGRole.class);
                    secondBestPlay.put(ChampionGGRole.TOP, roleData.get(champs.get(0)).get(ChampionGGRole.TOP));
                    secondBestPlay.put(ChampionGGRole.JUNGLE, roleData.get(champs.get(1)).get(ChampionGGRole.JUNGLE));
                    secondBestPlay.put(ChampionGGRole.MIDDLE, roleData.get(champs.get(2)).get(ChampionGGRole.MIDDLE));
                    secondBestPlay.put(ChampionGGRole.DUO_CARRY, roleData.get(champs.get(3)).get(ChampionGGRole.DUO_CARRY));
                    secondBestPlay.put(ChampionGGRole.DUO_SUPPORT, roleData.get(champs.get(4)).get(ChampionGGRole.DUO_SUPPORT));
                }
            }
        }
        
        if (secondBestRoles == null || secondBestRoles.equals(bestRoles))
        {
            secondBestRoles = null;
            secondBestPlay = null;
            secondBestMetric = Float.NEGATIVE_INFINITY;
        }
        
        boolean foundAlt = secondBestPlay != null;
        
        StringBuilder alternative = new StringBuilder();
        
        // bestMetric should never be 0 here, but just in case
        if (0 == bestMetric)
        {
            bestMetric = 1;
        }
        
        float confidence = (bestMetric - secondBestMetric) / bestMetric;
        if (foundAlt)
        {
            for (ChampionGGRole role : ChampionGGRole.values())
            {
                if (!bestRoles.get(role).equals(secondBestRoles.get(role)))
                {
                    alternative.append(String.format("%s: %s", role, Champion.withId(secondBestRoles.get(role)).get().getName()));
                }
            }
        }
        
        if (verbose)
        {
            for (ChampionGGRole role : ChampionGGRole.values())
            {
                System.out.format("%s: %s  (%s%%)%n", role, Champion.withId(bestRoles.get(role)).get().getName(), ChampionGG.DECIMAL_FORMAT.format(100 * roleData.get(bestRoles.get(role)).get(role)));
            }
            
            System.out.format("Probability: %s%n", ChampionGG.DECIMAL_FORMAT.format(100 * bestMetric));
            if (!foundAlt)
            {
                System.out.format("Confidence: %s%n", ChampionGG.DECIMAL_FORMAT.format(100 * confidence));
            } else
            {
                System.out.format("Confidence: %s (Alternative is %s)%n", ChampionGG.DECIMAL_FORMAT.format(100 * confidence), alternative);
            }
        }
        
        return new ChampionGGRolePredictionResults(bestMetric, confidence, bestRoles, secondBestRoles);
    }
    
    private static int getSortOrder(Map<Integer, EnumMap<ChampionGGRole, Float>> roleData, Entry<ChampionGGRole, Integer> a, Entry<ChampionGGRole, Integer> b)
    {
        return roleData.get(a.getValue()).get(a.getKey()) > roleData.get(b.getValue()).get(b.getKey()) ? 1 : -1;
    }
    
    private static Predicate<Entry<ChampionGGRole, Integer>> isNotInResult(final Map<ChampionGGRole, Integer> fixed)
    {
        return new Predicate<Entry<ChampionGGRole, Integer>>()
        {
            @Override
            public boolean apply(Entry<ChampionGGRole, Integer> input)
            {
                return !fixed.containsKey(input.getKey());
            }
        };
    }
    
    private static Predicate<Float> isGreaterThanZero = new Predicate<Float>()
    {
        @Override
        public boolean apply(Float input)
        {
            return input > 0;
        }
    };
}
