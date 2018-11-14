package com.atherys.roleplay.commands.misc;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.roleplay.RoleplayMsg;
import com.google.common.collect.ImmutableMap;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

import static org.spongepowered.api.text.TextTemplate.arg;

@Aliases("roll")
public class RollCommand implements ParameterizedCommand {

    private static TextTemplate rollMessage = TextTemplate.of(
            arg("player"), " rolled a ",
            arg("roll").color(TextColors.GOLD).style(TextStyles.BOLD), ". (", arg("maximum"), ")"
    );

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
       if(!(src instanceof Player)) return CommandResult.empty();

       Player player = (Player) src;
       Optional<String> dice = args.getOne("dice");
       dice.ifPresent(d ->{
           Random rand = new Random();
           int roll = 0;
           String[] numbers = d.split("d");

           // If the argument is in the format XdY
           if (numbers.length == 2 && ! numbers[0].equals("")) {
               int times = Integer.parseInt(numbers[0]);
               int die = Integer.parseInt(numbers[1]);

               for (int i = 0; i < times; i++) {
                   roll += rand.nextInt(die) + 1;
               }

           // If the argument is in the format dX
           } else if(numbers.length == 2) {
               roll = rand.nextInt(Integer.parseInt(d.substring(1))) + 1;

           // Otherwise, check if it's just a number.
           } else {
               try {
                   roll = rand.nextInt(Integer.parseInt(d)) + 1;
               } catch (NumberFormatException e) {
                   RoleplayMsg.error(player, "Roll argument must be a number, or in the format 'dX or YdX.");
                   return;
               }
           }

           MessageChannel playerChannel = player.getMessageChannel();
           playerChannel.send(RoleplayMsg.info(rollMessage.apply(ImmutableMap.of(
                   "player", Text.of(player.getName()),
                   "roll", Text.of(roll),
                   "maximum", Text.of(d)
           )).build()));
       });
       return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("dice"))
        };
    }
}
