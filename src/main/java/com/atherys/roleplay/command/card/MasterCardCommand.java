package com.atherys.roleplay.command.card;

import com.atherys.core.command.PlayerCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Children;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.command.annotation.HelpCommand;
import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.command.card.set.*;
import com.atherys.roleplay.menu.Menus;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@Description("Displays list of command.")
@Children({ShowCardCommand.class,
        SetCardDescriptionCommand.class,
        SetCardAgeCommand.class,
        SetCardNameCommand.class,
        SetCardNationCommand.class,
        SetCardNicknameCommand.class,
        ResetCardCommand.class})
@Aliases("card")
@HelpCommand(title = "Card Help", command = "help")
public class MasterCardCommand implements PlayerCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException {
        AtherysRoleplay.getInstance().getCardFacade().openCardMenu(source);
        return CommandResult.success();
    }
}
