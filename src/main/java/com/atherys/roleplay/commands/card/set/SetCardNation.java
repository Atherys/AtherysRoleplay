package com.atherys.roleplay.commands.card.set;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
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
import java.util.HashMap;
import java.util.Optional;

@Aliases("nation")
public class SetCardNation implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = (Player) src;
        Optional<String> name = args.getOne("nation");
        name.ifPresent(n -> {
            CardManager.getInstance().getCard(player).setNationality(n);
            player.sendMessage(Text.of(TextColors.DARK_GREEN, "Character nationality set."));
        });
        return CommandResult.success();
    }

    private static HashMap<String, String> getChoices(){
        HashMap<String, String> choices = new HashMap<>();
        choices.put("Atvoria", "Atvoria");
        choices.put("Daidama", "Daidama");
        choices.put("Dalkun-Tir", "Dalkun-Tir");
        choices.put("Gennaian Isles", "Gennaian Isles");
        choices.put("Kilnholdt", "Kilnholdt");
        return choices;
    }

    private HashMap<String, String> choices = getChoices();

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.choices(Text.of("nation"), choices)
        };
    }
}


