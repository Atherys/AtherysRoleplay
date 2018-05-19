package com.atherys.roleplay.commands.card;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.roleplay.CardManager;
import com.atherys.roleplay.cards.CharacterCard;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nonnull;

@Aliases("reset")
public class ResetCard implements CommandExecutor {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = (Player) src;
        CharacterCard card = CardManager.getInstance().getCard(player);
        card.setNationality("");
        card.setName("");
        card.setAge("");
        card.setDescription("");
        card.setNickname("");
        player.sendMessage(Text.of(TextColors.DARK_GREEN, "Character card reset."));
        return CommandResult.empty();
    }
}
