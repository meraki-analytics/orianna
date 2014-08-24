package lib.orianna.type.game;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lib.orianna.type.match.MatchMap;
import lib.orianna.type.match.Side;
import lib.orianna.type.staticdata.Champion;
import lib.orianna.type.staticdata.SummonerSpell;

public class Game implements Serializable {
    private static final long serialVersionUID = -8516946488824820992L;
    public final Champion champion;
    public final LocalDateTime createDate;
    public final List<GamePlayer> fellowPlayers;
    public final GameMode gameMode;
    public final GameType gameType;
    public final Long ID;
    public final Boolean invalid;
    public final Integer IPEarned, level;
    public final MatchMap map;
    public final SummonerSpell spell1, spell2;
    public final RawStats stats;
    public final SubType subType;
    public final Side team;

    public Game(final Champion champion, final LocalDateTime createDate, final List<GamePlayer> fellowPlayers, final GameMode gameMode,
            final GameType gameType, final Long ID, final Boolean invalid, final Integer IPEarned, final Integer level, final MatchMap map,
            final SummonerSpell spell1, final SummonerSpell spell2, final RawStats stats, final SubType subType, final Side team) {
        this.champion = champion;
        this.createDate = createDate;
        this.fellowPlayers = fellowPlayers;
        this.gameMode = gameMode;
        this.gameType = gameType;
        this.ID = ID;
        this.invalid = invalid;
        this.IPEarned = IPEarned;
        this.level = level;
        this.map = map;
        this.spell1 = spell1;
        this.spell2 = spell2;
        this.stats = stats;
        this.subType = subType;
        this.team = team;
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Game)) {
            return false;
        }
        final Game other = (Game)obj;
        if(ID == null) {
            if(other.ID != null) {
                return false;
            }
        }
        else if(!ID.equals(other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (ID == null ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Game [champion=" + champion + ", createDate=" + createDate + "]";
    }
}
