package com.ljnic.roleplay.util;

import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.ArrayList;
import java.util.List;

final public class CommandList {
    private static PaginationList commandList = getCardPagination();

    public static PaginationList getCommandList(){
        return commandList;
    }

    private static PaginationList getCardPagination(){
        List<Text> cardCommandList = new ArrayList<>();
        cardCommandList.add(formatHelp("/card", "Displays this menu."));
        cardCommandList.add(formatHelp("/card create", "Creates your character card."));
        cardCommandList.add(formatHelp("/card show", "Displays your own card."));
        cardCommandList.add(formatHelp("/card reset", "Resets all card fields."));
        cardCommandList.add(formatHelp("/card name <name>", "Sets the character name of your card."));
        cardCommandList.add(formatHelp("/card nickname <nickname>", "Sets your character nickname. This name" +
                "is displayed in RP chat."));
        cardCommandList.add(formatHelp("/card nation <nation>", "Sets the nationality of your card. Options: " +
                "Atvoria, Daidama, Dalkun-Tir, Gennaia, Kilnholdt."));
        cardCommandList.add(formatHelp("/card description <description>", "Sets the description of your card." +
                " Subsequent uses will add to the description."));
        return PaginationList.builder()
                .contents(cardCommandList)
                .build();
    }

    private static Text formatHelp(String command, String description){
        return Text.builder(command)
                .color(TextColors.GOLD)
                .append(Text.of(TextColors.DARK_GREEN, " - ", description))
                .build();
    }
}
