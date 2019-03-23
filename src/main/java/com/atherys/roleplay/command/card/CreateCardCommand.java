package com.atherys.roleplay.command.card;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

@Aliases("create")
@Description("Creates your character card.")
public class CreateCardCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = ((Player) src).getPlayer().get();

        //if(CardManager.getInstance().hasCard(player)){
            player.sendMessage(Text.of("You've already created a card."));
            return CommandResult.empty();
        //}
        //CardManager.getInstance().createCard(player);

        //player.sendMessage(Text.of("Card created."));
        //return CommandResult.success();

    }
}
