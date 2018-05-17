package com.ljnic.roleplay.util;

import com.ljnic.roleplay.Roleplay;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.persistence.DataFormats;

import java.io.IOException;
import java.util.Optional;

public class Json {

    public static Optional<String> write(DataContainer container){
        try {
            return Optional.of(DataFormats.JSON.write(container));
        } catch(IOException e) {
            Roleplay.getInstance().logger().error("Error writing to json.");
        }
        return Optional.empty();
    }

    public static Optional<DataContainer> read(String json){
        try{
            return Optional.of(DataFormats.JSON.read(json));
        } catch(IOException e) {
            Roleplay.getInstance().logger().error("Error reading from json.");
        }
        return Optional.empty();
    }
}
