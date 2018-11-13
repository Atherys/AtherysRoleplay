package com.atherys.roleplay.commands.card;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.roleplay.CardManager;
import com.atherys.roleplay.RoleplayMsg;
import com.atherys.roleplay.cards.CharacterCard;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@Aliases("reset")
@Description("Resets all fields of your character card.")
public class ResetCardCommand implements CommandExecutor {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = (Player) src;
        CharacterCard card = CardManager.getInstance().getCard(player);
        card.resetCard();
        RoleplayMsg.info(player, "Character card reset.");
        return CommandResult.empty();
    }
}
