package com.atherys.roleplay.facade;

import com.atherys.core.facade.MessagingFacade;
import com.atherys.roleplay.cards.Nation;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class RoleplayMessagingFacade implements MessagingFacade {
    private static final Text prefix = Text.of(
            TextColors.DARK_GREEN, "[", TextColors.GOLD, "Roleplay", TextColors.DARK_GREEN, "] "
    );

    @Override
    public Text getPrefix() {
        return prefix;
    }

    public static void nationMessage(Player player, Nation nation) {
        player.sendMessage(Text.of(prefix, TextColors.DARK_GREEN, "Nation set to ", nation.getColor(), nation.getName()));
    }
}
