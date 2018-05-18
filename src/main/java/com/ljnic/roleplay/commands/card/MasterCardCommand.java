package com.ljnic.roleplay.commands.card;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Children;
import com.atherys.core.command.annotation.Description;
import com.ljnic.roleplay.commands.card.set.*;
import com.ljnic.roleplay.util.CommandList;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@Description("Master quest command. Displays list of options.")
@Children({ CreateCard.class,
            ShowCard.class,
            SetCardDescription.class,
            SetCardAge.class,
            SetCardName.class,
            SetCardNation.class,
            SetCardNickname.class,
            ResetCard.class })
@Aliases("card")
public class MasterCardCommand implements CommandExecutor {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();
        CommandList.getCommandList().sendTo(src);
        return CommandResult.success();
    }
}
