package com.atherys.roleplay.command.card.set;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.PlayerCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.command.annotation.Permission;
import com.atherys.roleplay.AtherysRoleplay;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;

@Aliases("name")
@Permission("atherysroleplay.card.name")
@Description("Sets the name your character.")
public class SetCardNameCommand implements ParameterizedCommand, PlayerCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException {
        AtherysRoleplay.getInstance().getCardFacade().setCardName(source, args.<String>getOne("name").orElse(""));
        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.remainingJoinedStrings(Text.of("name"))
        };
    }
}
