package com.atherys.roleplay.facade;

import com.atherys.roleplay.AtherysRoleplay;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.Random;

import static org.spongepowered.api.text.TextTemplate.arg;

@Singleton
public class RollFacade {

    private TextTemplate rollMessage = TextTemplate.of(
            arg("player"), " rolled a ",
            arg("roll").color(TextColors.GOLD).style(TextStyles.BOLD), ". (", arg("maximum"), ")"
    );

    public void rollDice(CommandSource src, String dice) {
        Random rand = new Random();
        int roll = 0;
        String[] numbers = dice.split("d");

        // If the argument is in the format XdY
        if (numbers.length == 2 && !numbers[0].equals("")) {
            int times = Integer.parseInt(numbers[0]);
            int die = Integer.parseInt(numbers[1]);

            for (int i = 0; i < times; i++) {
                roll += rand.nextInt(die) + 1;
            }

        // If the argument is in the format dX
        } else if (numbers.length == 2) {
            roll = rand.nextInt(Integer.parseInt(dice.substring(1))) + 1;

        // Otherwise, check if it's just a number.
        } else {
            try {
                roll = rand.nextInt(Integer.parseInt(dice)) + 1;
            } catch (NumberFormatException e) {
                AtherysRoleplay.getInstance().getMessagingFacade().error(src, "Roll argument must be a number, or in the format dX or YdX.");
                return;
            }
        }

        MessageChannel playerChannel = src.getMessageChannel();
        playerChannel.send(AtherysRoleplay.getInstance().getMessagingFacade().formatInfo(rollMessage.apply(ImmutableMap.of(
                "player", Text.of(src.getName()),
                "roll", Text.of(roll),
                "maximum", Text.of(dice)
        )).build()));
    }
}
