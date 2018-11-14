package com.atherys.roleplay;

import com.atherys.roleplay.cards.Nation;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class RoleplayMsg {
    private static Text prefix = Text.of(TextColors.DARK_GREEN, "[", TextColors.GOLD, "Roleplay", TextColors.DARK_GREEN, "] ");

    public static void nationMessage(Player player, Nation nation) {
        player.sendMessage(Text.of(prefix, TextColors.DARK_GREEN, "Nation set to ", nation.getColor(), nation.getName()));
    }

    public static void info(Player player, String message) {
        player.sendMessage(Text.of(prefix, TextColors.DARK_GREEN, message));
    }

    public static void error(Player player, Text message) {
        player.sendMessage(Text.of(prefix, TextColors.RED, message));
    }

    public static void error(Player player, String message) {
        error(player, Text.of(message));
    }

    public static Text info(Text message) {
        return Text.of(prefix, message);
    }
}
