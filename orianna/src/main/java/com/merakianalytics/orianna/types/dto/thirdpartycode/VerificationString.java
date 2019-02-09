package com.merakianalytics.orianna.types.dto.thirdpartycode;

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

@JsonDeserialize(using = VerificationString.Deserializer.class)
@JsonSerialize(using = VerificationString.Serializer.class)
public class VerificationString extends DataObject {
    public static class Deserializer extends JsonDeserializer<VerificationString> {
        @Override
        public VerificationString deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final VerificationString result = new VerificationString();
            result.string = parser.readValueAs(String.class);
            return result;
        }
    }

    public static class Serializer extends JsonSerializer<VerificationString> {
        @Override
        public void serialize(final VerificationString string, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
            generator.writeObject(string.getString());
        }
    }

    private static final long serialVersionUID = 9177660941963582527L;
    private String string, platform, summonerId;

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
        final VerificationString other = (VerificationString)obj;
        if(platform == null) {
            if(other.platform != null) {
                return false;
            }
        } else if(!platform.equals(other.platform)) {
            return false;
        }
        if(string == null) {
            if(other.string != null) {
                return false;
            }
        } else if(!string.equals(other.string)) {
            return false;
        }
        if(summonerId == null) {
            if(other.summonerId != null) {
                return false;
            }
        } else if(!summonerId.equals(other.summonerId)) {
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
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * @return the summonerId
     */
    public String getSummonerId() {
        return summonerId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (platform == null ? 0 : platform.hashCode());
        result = prime * result + (string == null ? 0 : string.hashCode());
        result = prime * result + (summonerId == null ? 0 : summonerId.hashCode());
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
     * @param string
     *        the string to set
     */
    public void setString(final String string) {
        this.string = string;
    }

    /**
     * @param summonerId
     *        the summonerId to set
     */
    public void setSummonerId(final String summonerId) {
        this.summonerId = summonerId;
    }
}
