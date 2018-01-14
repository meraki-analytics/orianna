package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.data.spectator.CurrentGame;
import com.merakianalytics.orianna.types.data.spectator.FeaturedGame;
import com.merakianalytics.orianna.types.data.spectator.FeaturedGames;
import com.merakianalytics.orianna.types.data.spectator.GameCustomizationObject;
import com.merakianalytics.orianna.types.data.spectator.Participant;
import com.merakianalytics.orianna.types.data.spectator.Player;
import com.merakianalytics.orianna.types.data.spectator.Runes;
import com.merakianalytics.orianna.types.dto.spectator.BannedChampion;
import com.merakianalytics.orianna.types.dto.spectator.CurrentGameInfo;
import com.merakianalytics.orianna.types.dto.spectator.CurrentGameParticipant;
import com.merakianalytics.orianna.types.dto.spectator.FeaturedGameInfo;
import com.merakianalytics.orianna.types.dto.spectator.Observer;
import com.merakianalytics.orianna.types.dto.spectator.Perks;

public class SpectatorTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.spectator.FeaturedGames.class, to = FeaturedGames.class)
    public FeaturedGames transform(final com.merakianalytics.orianna.types.dto.spectator.FeaturedGames item, final PipelineContext context) {
        final FeaturedGames games = new FeaturedGames(item.getGameList().size());
        for(final FeaturedGameInfo game : item.getGameList()) {
            games.add(transform(game, context));
        }
        games.setPlatform(item.getPlatform());
        games.setRefreshInterval(Duration.millis(item.getClientRefreshInterval()));
        return games;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject.class, to = GameCustomizationObject.class)
    public GameCustomizationObject transform(final com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject item,
        final PipelineContext context) {
        final GameCustomizationObject object = new GameCustomizationObject();
        object.setCategory(item.getCategory());
        object.setContent(item.getContent());
        return object;
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
        player.setTeam((int)item.getTeamId());
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
                ban.setTeamId(Side.BLUE.getId());
                bans.add(ban);
            }
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn++);
                ban.setTeamId(Side.RED.getId());
                bans.add(ban);
            }
        } else {
            int turn = 1;
            for(final Integer championId : item.getBlueTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Side.BLUE.getId());
                bans.add(ban);
                bans.add(null);
            }
            turn = 2;
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Side.RED.getId());
                bans.set(turn - 1, ban);
            }
        }
        game.setBannedChampions(bans);
        game.setGameId(item.getId());
        game.setGameLength(item.getDuration().getMillis());
        game.setGameMode(item.getMode().toString());
        game.setGameQueueConfigId(item.getQueue());
        game.setGameStartTime(item.getCreationTime().getMillis());
        game.setGameType(item.getType().toString());
        game.setMapId(item.getMap());
        final Observer observer = new Observer();
        observer.setEncryptionKey(item.getObserverEncryptionKey());
        game.setObservers(observer);
        final List<CurrentGameParticipant> participants = new ArrayList<>(item.getPlayers().size());
        for(final Player player : item.getPlayers()) {
            participants.add(transform(player, context));
        }
        game.setParticipants(participants);
        game.setPlatformId(item.getPlatform());
        game.setSummonerId(item.getSummonerId());
        return game;
    }

    @Transform(from = CurrentGameInfo.class, to = CurrentGame.class)
    public CurrentGame transform(final CurrentGameInfo item, final PipelineContext context) {
        final Object previous = context.put("platform", item.getPlatformId());
        final CurrentGame game = new CurrentGame();
        final List<Integer> blueBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        final List<Integer> redBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        for(final BannedChampion ban : item.getBannedChampions()) {
            if(Side.BLUE.getId() == ban.getTeamId()) {
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
        game.setMap((int)item.getMapId());
        game.setMode(item.getGameMode());
        game.setObserverEncryptionKey(item.getObservers().getEncryptionKey());
        game.setPlatform(item.getPlatformId());
        final List<Player> players = new ArrayList<>(item.getParticipants().size());
        for(final CurrentGameParticipant player : item.getParticipants()) {
            players.add(transform(player, context));
        }
        game.setPlayers(players);
        game.setQueue((int)item.getGameQueueConfigId());
        game.setSummonerId(item.getSummonerId());
        game.setType(item.getGameType());
        context.put("platform", previous);
        return game;
    }

    @Transform(from = CurrentGameParticipant.class, to = Player.class)
    public Player transform(final CurrentGameParticipant item, final PipelineContext context) {
        final Player player = new Player();
        player.setPlatform((String)context.get("platform"));
        player.setBot(item.isBot());
        player.setChampionId((int)item.getChampionId());
        player.setProfileIconId((int)item.getProfileIconId());
        player.setRunes(transform(item.getPerks(), context));
        final List<GameCustomizationObject> objects = new ArrayList<>();
        for(final com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject object : item.getGameCustomizationObjects()) {
            objects.add(transform(object, context));
        }
        player.setCustomizationObjects(objects);
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        player.setSummonerSpellDId((int)item.getSpell1Id());
        player.setSummonerSpellFId((int)item.getSpell2Id());
        player.setTeam((int)item.getTeamId());
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
                ban.setTeamId(Side.BLUE.getId());
                bans.add(ban);
            }
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn++);
                ban.setTeamId(Side.RED.getId());
                bans.add(ban);
            }
        } else {
            int turn = 1;
            for(final Integer championId : item.getBlueTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Side.BLUE.getId());
                bans.add(ban);
                bans.add(null);
            }
            turn = 2;
            for(final Integer championId : item.getRedTeamBans()) {
                final BannedChampion ban = new BannedChampion();
                ban.setChampionId(championId);
                ban.setPickTurn(turn);
                turn += 2;
                ban.setTeamId(Side.RED.getId());
                bans.set(turn - 1, ban);
            }
        }
        game.setBannedChampions(bans);
        game.setGameId(item.getId());
        game.setGameLength(item.getDuration().getMillis());
        game.setGameMode(item.getMode().toString());
        game.setGameQueueConfigId(item.getQueue());
        game.setGameStartTime(item.getCreationTime().getMillis());
        game.setGameType(item.getType().toString());
        game.setMapId(item.getMap());
        final Observer observer = new Observer();
        observer.setEncryptionKey(item.getObserverEncryptionKey());
        game.setObservers(observer);
        final List<com.merakianalytics.orianna.types.dto.spectator.Participant> participants = new ArrayList<>(item.getPlayers().size());
        for(final Participant player : item.getPlayers()) {
            participants.add(transform(player, context));
        }
        game.setParticipants(participants);
        game.setPlatformId(item.getPlatform());
        return game;
    }

    @Transform(from = FeaturedGameInfo.class, to = FeaturedGame.class)
    public FeaturedGame transform(final FeaturedGameInfo item, final PipelineContext context) {
        final Object previous = context.put("platform", item.getPlatformId());
        final FeaturedGame game = new FeaturedGame();
        final List<Integer> blueBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        final List<Integer> redBans = new ArrayList<>(item.getBannedChampions().size() / 2);
        for(final BannedChampion ban : item.getBannedChampions()) {
            if(Side.BLUE.getId() == ban.getTeamId()) {
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
        game.setMap((int)item.getMapId());
        game.setMode(item.getGameMode());
        game.setObserverEncryptionKey(item.getObservers().getEncryptionKey());
        game.setPlatform(item.getPlatformId());
        final List<Participant> players = new ArrayList<>(item.getParticipants().size());
        for(final com.merakianalytics.orianna.types.dto.spectator.Participant player : item.getParticipants()) {
            players.add(transform(player, context));
        }
        game.setPlayers(players);
        game.setQueue((int)item.getGameQueueConfigId());
        game.setType(item.getGameType());
        context.put("platform", previous);
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
        games.setPlatform(item.getPlatform());
        games.setClientRefreshInterval(item.getRefreshInterval().getMillis());
        return games;
    }

    @Transform(from = GameCustomizationObject.class, to = com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject.class)
    public com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject transform(final GameCustomizationObject item,
        final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject object =
            new com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject();
        object.setCategory(item.getCategory());
        object.setContent(item.getContent());
        return object;
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
        player.setTeamId(item.getTeam());
        return player;
    }

    @Transform(from = Perks.class, to = Runes.class)
    public Runes transform(final Perks item, final PipelineContext context) {
        final Runes runes = new Runes(item.getPerkIds().size());
        for(final Long id : item.getPerkIds()) {
            runes.add(id.intValue());
        }
        runes.setPrimaryPath((int)item.getPerkStyle());
        runes.setSecondaryPath((int)item.getPerkStyle());
        return runes;
    }

    @Transform(from = Player.class, to = CurrentGameParticipant.class)
    public CurrentGameParticipant transform(final Player item, final PipelineContext context) {
        final CurrentGameParticipant player = new CurrentGameParticipant();
        player.setBot(item.isBot());
        player.setChampionId(item.getChampionId());
        player.setProfileIconId(item.getProfileIconId());
        player.setPerks(transform(item.getRunes(), context));
        final List<com.merakianalytics.orianna.types.dto.spectator.GameCustomizationObject> objects = new ArrayList<>();
        for(final GameCustomizationObject object : item.getCustomizationObjects()) {
            objects.add(transform(object, context));
        }
        player.setGameCustomizationObjects(objects);
        player.setSummonerId(item.getSummonerId());
        player.setSummonerName(item.getSummonerName());
        player.setSpell1Id(item.getSummonerSpellDId());
        player.setSpell2Id(item.getSummonerSpellFId());
        player.setTeamId(item.getTeam());
        return player;
    }

    @Transform(from = Runes.class, to = Perks.class)
    public Perks transform(final Runes item, final PipelineContext context) {
        final Perks perks = new Perks();
        final List<Long> perkIds = new ArrayList<>(item.size());
        for(final Integer id : item) {
            perkIds.add(id.longValue());
        }
        perks.setPerkIds(perkIds);
        perks.setPerkStyle(item.getPrimaryPath());
        perks.setPerkSubStyle(item.getSecondaryPath());
        return perks;
    }
}
