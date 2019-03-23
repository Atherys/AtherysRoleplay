package com.atherys.roleplay.command.card.set;

import com.atherys.core.command.ParameterizedCommand;
import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Description;
import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.RoleplayMsg;
import com.atherys.roleplay.cards.Nation;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Optional;

@Aliases("nation")
@Description("Sets the nation of your character.")
public class SetCardNationCommand implements ParameterizedCommand {

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();

        Player player = (Player) src;
        Optional<Nation> name = args.getOne("nation");
        name.ifPresent(n -> {
            //CardManager.getInstance().getCard(player).setNationality(n.getName());
            RoleplayMsg.nationMessage(player, n);
        });
        return CommandResult.success();
    }

    private static HashMap<String, Nation> getChoices(){
        HashMap<String, Nation> choices = new HashMap<>();
        AtherysRoleplay.getConfig().NATIONS.forEach(nation -> choices.put(nation.getName(), nation));
        return choices;
    }

    private HashMap<String, Nation> choices = getChoices();

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.choices(Text.of("nation"), choices)
        };
    }
}


