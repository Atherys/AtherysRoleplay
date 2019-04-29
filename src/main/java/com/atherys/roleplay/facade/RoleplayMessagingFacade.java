package com.atherys.roleplay.facade;

import com.atherys.core.facade.MessagingFacade;
import com.atherys.roleplay.card.Nation;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import static org.spongepowered.api.text.format.TextColors.DARK_GREEN;
import static org.spongepowered.api.text.format.TextColors.GOLD;

public class RoleplayMessagingFacade implements MessagingFacade {
    private static final Text prefix = Text.of(
            DARK_GREEN, "[", GOLD, "Roleplay", DARK_GREEN, "] "
    );

    @Override
    public Text getPrefix() {
        return prefix;
    }

    public static void nationMessage(Player player, Nation nation) {
        player.sendMessage(Text.of(prefix, DARK_GREEN, "Nation set to ", nation.getColor(), nation.getName(), DARK_GREEN, "."));
    }
}
