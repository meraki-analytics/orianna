package com.merakianalytics.orianna.datapipeline.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Team;
import com.merakianalytics.orianna.types.data.spectator.CurrentGame;
import com.merakianalytics.orianna.types.data.spectator.FeaturedGame;
import com.merakianalytics.orianna.types.data.spectator.FeaturedGames;
import com.merakianalytics.orianna.types.data.spectator.Participant;
import com.merakianalytics.orianna.types.data.spectator.Player;
import com.merakianalytics.orianna.types.dto.spectator.BannedChampion;
import com.merakianalytics.orianna.types.dto.spectator.CurrentGameInfo;
import com.merakianalytics.orianna.types.dto.spectator.CurrentGameParticipant;
import com.merakianalytics.orianna.types.dto.spectator.FeaturedGameInfo;
import com.merakianalytics.orianna.types.dto.spectator.Mastery;
import com.merakianalytics.orianna.types.dto.spectator.Observer;
import com.merakianalytics.orianna.types.dto.spectator.Rune;

public class SpectatorTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.spectator.FeaturedGames.class, to = FeaturedGames.class)
    public FeaturedGames transform(final com.merakianalytics.orianna.types.dto.spectator.FeaturedGames item, final PipelineContext context) {
        final FeaturedGames games = new FeaturedGames(item.getGameList().size());
        for(final FeaturedGameInfo game : item.getGameList()) {
            games.add(transform(game, context));
        }
        games.setPlatform(Platform.withTag(item.getPlatform()));
        games.setRefreshInterval(Duration.millis(item.getClientRefreshInterval()));
        return games;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.spectator.Participant.class, to = Participant.class)
    public Participant transform(final com.merakianalytics.orianna.types.dto.spectator.Participant item, final PipelineContext context) {
        final Participant player = new Participant();
        player.setBot(item.isBot());
        player.setChampionId((int)item.getChampionId());
        player.setProfileIconId((int)item.getProfileIconId());
        player.setSummonerName(item.getSummonerName());
        player.setSummonerSpellDId((int)item.getSpell1Id());
        player.setSummonerSpellFId((int)item.getSpell2Id());
        player.setTeam(Team.withId((int)item.getTeamId()));
        return player;
    }

    @Transform(from = CurrentGame.class, to = CurrentGameInfo.class)
    public CurrentGameInfo transform(final CurrentGame item, final PipelineContext context) {
        final CurrentGameInfo game = new CurrentGameInfo();
        final List<BannedChampion> bans = new ArrayList<>(item.getBlueTeamBans().size() + item.getRedTeamBans().size());
        final boolean isTenBanMode = item.getBlueTeamBans().size() + item.getRedTeamBans().size() == 10;
        if(isTenBanMode) {
            int turn = 1;
            for(final Integer championId : item.getBlueTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn++);
                ban.setTeamId(Team.BLUE.getId());
                bans.add(ban);
            }
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn++);
                ban.setTeamId(Team.RED.getId());
                bans.add(ban);
            }
        } else {
            int turn = 1;
            for(final Integer championId : item.getBlueTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Team.BLUE.getId());
                bans.add(ban);
                bans.add(null);
            }
            turn = 2;
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Team.RED.getId());
                bans.set(turn - 1, ban);
            }
        }
        game.setBannedChampions(bans);
        game.setGameId(item.getId());
        game.setGameLength(item.getDuration().getMillis());
        game.setGameMode(item.getMode().toString());
        game.setGameQueueConfigId(item.getQueue().getId());
        game.setGameStartTime(item.getCreationTime().getMillis());
        game.setGameType(item.getType().toString());
        game.setMapId(item.getMap().getId());
        final Observer observer = new Observer();
        observer.setEncryptionKey(item.getObserverEncryptionKey());
        game.setObservers(observer);
        final List<CurrentGameParticipant> participants = new ArrayList<>(item.getPlayers().size());
        for(final Player player : item.getPlayers()) {
            participants.add(transform(player, context));
        }
        game.setParticipants(participants);
        game.setPlatformId(item.getPlatform().getTag());
        game.setSummonerId(item.getSummonerId());
        return game;
    }

    @Transform(from = CurrentGameInfo.class, to = CurrentGame.class)
    public CurrentGame transform(final CurrentGameInfo item, final PipelineContext context) {
        final CurrentGame game = new CurrentGame();
        final List<Integer> blueBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        final List<Integer> redBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        for(final BannedChampion ban : item.getBannedChampions()) {
            if(Team.BLUE == Team.withId((int)ban.getTeamId())) {
                blueBans.add((int)ban.getChampionId());
            } else {
                redBans.add((int)ban.getChampionId());
            }
        }
        game.setBlueTeamBans(blueBans);
        game.setRedTeamBans(redBans);
        game.setCreationTime(new DateTime(item.getGameStartTime()));
        game.setDuration(Duration.millis(item.getGameLength()));
        game.setId(item.getGameId());
        game.setMap(com.merakianalytics.orianna.types.common.Map.withId((int)item.getMapId()));
        game.setMode(GameMode.valueOf(item.getGameMode()));
        game.setObserverEncryptionKey(item.getObservers().getEncryptionKey());
        game.setPlatform(Platform.withTag(item.getPlatformId()));
        final List<Player> players = new ArrayList<>(item.getParticipants().size());
        for(final CurrentGameParticipant player : item.getParticipants()) {
            players.add(transform(player, context));
        }
        game.setPlayers(players);
        game.setQueue(Queue.withId((int)item.getGameQueueConfigId()));
        game.setSummonerId(item.getSummonerId());
        game.setType(GameType.valueOf(item.getGameType()));
        return game;
    }

    @Transform(from = CurrentGameParticipant.class, to = Player.class)
    public Player transform(final CurrentGameParticipant item, final PipelineContext context) {
        final Player player = new Player();
        player.setBot(item.isBot());
        player.setChampionId((int)item.getChampionId());
        Map<Integer, Integer> counts = new HashMap<>();
        for(final Mastery mastery : item.getMasteries()) {
            counts.put((int)mastery.getMasteryId(), mastery.getRank());
        }
        player.setMasteries(counts);
        player.setProfileIconId((int)item.getProfileIconId());
        counts = new HashMap<>();
        for(final Rune rune : item.getRunes()) {
            counts.put((int)rune.getRuneId(), rune.getCount());
        }
        player.setRunes(counts);
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        player.setSummonerSpellDId((int)item.getSpell1Id());
        player.setSummonerSpellFId((int)item.getSpell2Id());
        player.setTeam(Team.withId((int)item.getTeamId()));
        return player;
    }

    @Transform(from = FeaturedGame.class, to = FeaturedGameInfo.class)
    public FeaturedGameInfo transform(final FeaturedGame item, final PipelineContext context) {
        final FeaturedGameInfo game = new FeaturedGameInfo();
        final List<BannedChampion> bans = new ArrayList<>(item.getBlueTeamBans().size() + item.getRedTeamBans().size());
        final boolean isTenBanMode = item.getBlueTeamBans().size() + item.getRedTeamBans().size() == 10;
        if(isTenBanMode) {
            int turn = 1;
            for(final Integer championId : item.getBlueTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn++);
                ban.setTeamId(Team.BLUE.getId());
                bans.add(ban);
            }
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn++);
                ban.setTeamId(Team.RED.getId());
                bans.add(ban);
            }
        } else {
            int turn = 1;
            for(final Integer championId : item.getBlueTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Team.BLUE.getId());
                bans.add(ban);
                bans.add(null);
            }
            turn = 2;
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Team.RED.getId());
                bans.set(turn - 1, ban);
            }
        }
        game.setBannedChampions(bans);
        game.setGameId(item.getId());
        game.setGameLength(item.getDuration().getMillis());
        game.setGameMode(item.getMode().toString());
        game.setGameQueueConfigId(item.getQueue().getId());
        game.setGameStartTime(item.getCreationTime().getMillis());
        game.setGameType(item.getType().toString());
        game.setMapId(item.getMap().getId());
        final Observer observer = new Observer();
        observer.setEncryptionKey(item.getObserverEncryptionKey());
        game.setObservers(observer);
        final List<com.merakianalytics.orianna.types.dto.spectator.Participant> participants = new ArrayList<>(item.getPlayers().size());
        for(final Participant player : item.getPlayers()) {
            participants.add(transform(player, context));
        }
        game.setParticipants(participants);
        game.setPlatformId(item.getPlatform().getTag());
        return game;
    }

    @Transform(from = FeaturedGameInfo.class, to = FeaturedGame.class)
    public FeaturedGame transform(final FeaturedGameInfo item, final PipelineContext context) {
        final FeaturedGame game = new FeaturedGame();
        final List<Integer> blueBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        final List<Integer> redBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        for(final BannedChampion ban : item.getBannedChampions()) {
            if(Team.BLUE == Team.withId((int)ban.getTeamId())) {
                blueBans.add((int)ban.getChampionId());
            } else {
                redBans.add((int)ban.getChampionId());
            }
        }
        game.setBlueTeamBans(blueBans);
        game.setRedTeamBans(redBans);
        game.setCreationTime(new DateTime(item.getGameStartTime()));
        game.setDuration(Duration.millis(item.getGameLength()));
        game.setId(item.getGameId());
        game.setMap(com.merakianalytics.orianna.types.common.Map.withId((int)item.getMapId()));
        game.setMode(GameMode.valueOf(item.getGameMode()));
        game.setObserverEncryptionKey(item.getObservers().getEncryptionKey());
        game.setPlatform(Platform.withTag(item.getPlatformId()));
        final List<Participant> players = new ArrayList<>(item.getParticipants().size());
        for(final com.merakianalytics.orianna.types.dto.spectator.Participant player : item.getParticipants()) {
            players.add(transform(player, context));
        }
        game.setPlayers(players);
        game.setQueue(Queue.withId((int)item.getGameQueueConfigId()));
        game.setType(GameType.valueOf(item.getGameType()));
        return game;
    }

    @Transform(from = FeaturedGames.class, to = com.merakianalytics.orianna.types.dto.spectator.FeaturedGames.class)
    public com.merakianalytics.orianna.types.dto.spectator.FeaturedGames transform(final FeaturedGames item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.spectator.FeaturedGames games = new com.merakianalytics.orianna.types.dto.spectator.FeaturedGames();
        final List<FeaturedGameInfo> list = new ArrayList<>(item.size());
        for(final FeaturedGame game : item) {
            list.add(transform(game, context));
        }
        games.setGameList(list);
        games.setPlatform(item.getPlatform().getTag());
        games.setClientRefreshInterval(item.getRefreshInterval().getMillis());
        return games;
    }

    @Transform(from = Participant.class, to = com.merakianalytics.orianna.types.dto.spectator.Participant.class)
    public com.merakianalytics.orianna.types.dto.spectator.Participant transform(final Participant item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.spectator.Participant player = new com.merakianalytics.orianna.types.dto.spectator.Participant();
        player.setBot(item.isBot());
        player.setChampionId(item.getChampionId());
        player.setProfileIconId(item.getProfileIconId());
        player.setSummonerName(item.getSummonerName());
        player.setSpell1Id(item.getSummonerSpellDId());
        player.setSpell2Id(item.getSummonerSpellFId());
        player.setTeamId(item.getTeam().getId());
        return player;
    }

    @Transform(from = Player.class, to = CurrentGameParticipant.class)
    public CurrentGameParticipant transform(final Player item, final PipelineContext context) {
        final CurrentGameParticipant player = new CurrentGameParticipant();
        player.setBot(item.isBot());
        player.setChampionId(item.getChampionId());
        final List<Mastery> masteries = new ArrayList<>(item.getMasteries().size());
        for(final Integer id : item.getMasteries().keySet()) {
            final Mastery mastery = new Mastery();
            mastery.setMasteryId(id);
            mastery.setRank(item.getMasteries().get(id));
        }
        player.setMasteries(masteries);
        player.setProfileIconId(item.getProfileIconId());
        final List<Rune> runes = new ArrayList<>(item.getRunes().size());
        for(final Integer id : item.getRunes().keySet()) {
            final Rune rune = new Rune();
            rune.setCount(item.getRunes().get(id));
            rune.setRuneId(id);
        }
        player.setRunes(runes);
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        player.setSpell1Id(item.getSummonerSpellDId());
        player.setSpell2Id(item.getSummonerSpellFId());
        player.setTeamId(item.getTeam().getId());
        return player;
    }
}
