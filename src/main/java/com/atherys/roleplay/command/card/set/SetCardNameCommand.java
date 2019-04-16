package com.atherys.roleplay.command.card.set;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.roleplay.AtherysRoleplay;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@Aliases("name")
@Description("Sets the name your character.")
public class SetCardNameCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = (Player) src;
        Optional<String> name = args.getOne("name");
        name.ifPresent(n -> {
            if(n.length() > AtherysRoleplay.getInstance().getConfig().MAXIMUM_NAME_LENGTH) {
                AtherysRoleplay.getInstance().getMessagingFacade().error(player, "Your name must be under " + AtherysRoleplay.getInstance().getConfig().MAXIMUM_NAME_LENGTH);
            } else {
                //CardManager.getInstance().getCard(player).setName(n);
                AtherysRoleplay.getInstance().getMessagingFacade().info(player, "Character name set.");
            }
        });
        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.remainingJoinedStrings(Text.of("name"))
        };
    }
}
