package com.ljnic.roleplay.commands.misc;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

@Aliases("roll")
public class RollCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
       if(!(src instanceof Player)) return CommandResult.empty();

       Player player = (Player) src;
       Optional<Integer> dice = args.getOne("dice");
       dice.ifPresent(d ->{
           Random rand = new Random();
           int die = rand.nextInt(d) + 1;
           MessageChannel playerChannel = player.getMessageChannel();
           playerChannel.send(Text.of("You rolled a " + die + " out of " + d));
       });
       return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.integer(Text.of("dice"))
        };
    }
}
