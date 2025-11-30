package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public final class ResourceLoader {
    
    public Configuration loadFromRes (final String path) {
        final InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        Configuration.Builder builder = new Configuration.Builder();
        try(final BufferedReader buffer = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while((line = buffer.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, ":");
                String setting = token.nextToken().trim();
                String value = token.nextToken().trim();
                switch(setting) {
                    case "minimum":
                        builder.setMin(Integer.parseInt(value));
                        break;
                    case "maximum":
                        builder.setMax(Integer.parseInt(value));
                        break;
                    case "attempts":
                        builder.setAttempts(Integer.parseInt(value));
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException | IOException e) {
            e.getMessage();
        } 

        return builder.build();
    }
}
