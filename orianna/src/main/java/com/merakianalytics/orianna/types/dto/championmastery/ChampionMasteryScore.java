package com.merakianalytics.orianna.types.dto.championmastery;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.merakianalytics.orianna.types.dto.DataObject;

@JsonDeserialize(using = ChampionMasteryScore.Deserializer.class)
@JsonSerialize(using = ChampionMasteryScore.Serializer.class)
public class ChampionMasteryScore extends DataObject {
    public static class Deserializer extends JsonDeserializer<ChampionMasteryScore> {
        @Override
        public ChampionMasteryScore deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final ChampionMasteryScore result = new ChampionMasteryScore();
            result.score = parser.readValueAs(Integer.class);
            return result;
        }
    }

    public static class Serializer extends JsonSerializer<ChampionMasteryScore> {
        @Override
        public void serialize(final ChampionMasteryScore score, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
            generator.writeObject(score.score);
        }
    }

    private static final long serialVersionUID = -5431839360536213388L;
    private String platform, puuid;
    private int score;

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
        final ChampionMasteryScore other = (ChampionMasteryScore)obj;
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(score != other.score) {
            return false;
        }
        if(puuid == null) {
            if(other.puuid != null) {
                return false;
            }
        } else if(!puuid.equals(other.puuid)) {
            return false;
        }
        return true;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the puuid
     */
    public String getPuuid() {
        return puuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + score;
        result = prime * result + (puuid == null ? 0 : puuid.hashCode());
        return result;
    }

    /**
     * @param platform
     *        the platform to set
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    /**
     * @param score
     *        the score to set
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * @param puuid
     *        the puuid to set
     */
    public void setPuuid(final String puuid) {
        this.puuid = puuid;
    }
}
