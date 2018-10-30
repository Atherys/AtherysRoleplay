package com.atherys.roleplay.commands.card.set;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.roleplay.CardManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

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
            if(n.length() < 33) {
                CardManager.getInstance().getCard(player).setName(n);
                player.sendMessage(Text.of(TextColors.DARK_GREEN, "Character name set."));
            } else {
                player.sendMessage(Text.of(TextColors.RED, "Your name must be under 32 characters."));
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
