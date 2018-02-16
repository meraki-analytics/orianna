package orianna.test;

import com.google.common.base.Predicate;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.datapipeline.ChampionGG;
import com.merakianalytics.orianna.types.common.*;
import com.merakianalytics.orianna.types.core.match.*;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.dto.championgg.ChampionGGRolePredictionResults;
import org.junit.Test;

public class RolePredictionTest
{
    
    @Test
    public void testAll()
    {
        Orianna.setDefaultPlatform(Platform.EUROPE_WEST);
        final Summoner     sum  = Summoner.named("stelar7").get();
        final MatchHistory refs = sum.matchHistory().get();
        
        
        for (Match match : refs)
        {
            Participant self = match.getParticipants().find(new Predicate<Participant>()
            {
                @Override
                public boolean apply(Participant p)
                {
                    return p.getSummoner().getId() == sum.getId();
                }
            });
            
            Side                        team    = self.getTeam().getSide();
            SearchableList<Participant> ownTeam = match.getParticipants().filter(isSameTeam(team));
            
            ChampionGGRolePredictionResults results = ChampionGG.predictRoles(ownTeam);
            ChampionGG.printResults(match, ownTeam, team, results);
        }
    }
    
    private static Predicate<Participant> isSameTeam(final Side team)
    {
        return new Predicate<Participant>()
        {
            @Override
            public boolean apply(Participant input)
            {
                return input.getTeam().getSide() == team;
            }
        };
    }
}