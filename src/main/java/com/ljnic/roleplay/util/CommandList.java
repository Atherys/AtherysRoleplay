package com.ljnic.roleplay.util;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    private static List<Text> cardCommandList = new ArrayList<>();

    private static void createList(){
        Sponge.getCommandManager().getAll("card");
        cardCommandList.add(Text.builder()
                .color(TextColors.DARK_GREEN)
                .append(Text.of("/card "))
                .build());
    }
}
