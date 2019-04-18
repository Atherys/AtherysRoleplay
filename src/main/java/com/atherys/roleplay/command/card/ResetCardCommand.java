package com.atherys.roleplay.command.card;

import com.atherys.core.command.PlayerCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Children;
import com.atherys.core.command.annotation.Description;
import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.command.card.set.ResetCardDescriptionCommand;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@Aliases("reset")
@Description("Resets all fields of your character card.")
@Children(ResetCardDescriptionCommand.class)
public class ResetCardCommand implements PlayerCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) {
        AtherysRoleplay.getInstance().getCardFacade().resetCard(source);
        return CommandResult.success();
    }
}
