package com.atherys.roleplay.commands.card.set;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.roleplay.CardManager;
import com.atherys.roleplay.RoleplayMsg;
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

@Aliases("description")
@Description("Sets the description of your character. Subsequent uses will add to your description.")
public class SetCardDescriptionCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();
        Player player = (Player) src;
        Optional<String> description = args.getOne("description");
        description.ifPresent(desc -> {
            RoleplayMsg.info(player, "Character description updated.");
            CardManager.getInstance().getCard(player).addDescription(" " + desc);
        });

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.remainingJoinedStrings(Text.of("description"))
        };
    }
}
