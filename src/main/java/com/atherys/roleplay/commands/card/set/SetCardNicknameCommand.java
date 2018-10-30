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

@Aliases("nickname")
@Description("Sets the nickname of your character.")
public class SetCardNicknameCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = (Player) src;
        Optional<String> nickname = args.getOne("nick");
        nickname.ifPresent(nick -> {
            if(nick.length() > 16){
                player.sendMessage(Text.of(TextColors.RED, "Your character's nickname cannot be more than 16 characters."));
            }
            player.sendMessage(Text.of(TextColors.DARK_GREEN, "Character nickname set. This name will appear in RP chat."));
            CardManager.getInstance().getCard(player).setNickname(nick);
        });
        return CommandResult.success();

    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.remainingJoinedStrings(Text.of("nick"))
        };
    }
}
