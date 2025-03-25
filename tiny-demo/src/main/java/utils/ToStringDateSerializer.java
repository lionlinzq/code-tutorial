package utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * For efficiency, we will serialize Dates as longs, instead of
 * potentially more readable Strings.
 */
@JacksonStdImpl
@SuppressWarnings("serial")
public class ToStringDateSerializer
        extends DateTimeSerializerBase<Date>
{
    /**
     * Default instance that is used when no contextual configuration
     * is needed.
     */
    public static final com.fasterxml.jackson.databind.ser.std.DateSerializer instance = new com.fasterxml.jackson.databind.ser.std.DateSerializer();

    public ToStringDateSerializer() {
        this(null, null);
    }

    public ToStringDateSerializer(Boolean useTimestamp, DateFormat customFormat) {
        super(Date.class, useTimestamp, customFormat);
    }

    @Override
    public com.fasterxml.jackson.databind.ser.std.DateSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
        return new com.fasterxml.jackson.databind.ser.std.DateSerializer(timestamp, customFormat);
    }

    @Override
    protected long _timestamp(Date value) {
        return (value == null) ? 0L : value.getTime();
    }

    protected String timestampToString(Date value) {
        return (value == null) ? "0": String.valueOf(value.getTime());
    }


    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        if (_asTimestamp(provider)) {
            gen.writeString(timestampToString(value));
        } else if (_customFormat != null) {
            // 21-Feb-2011, tatu: not optimal, but better than alternatives:
            synchronized (_customFormat) {
                gen.writeString(_customFormat.format(value));
            }
        } else {
            provider.defaultSerializeDateValue(value, gen);
        }
    }
}





