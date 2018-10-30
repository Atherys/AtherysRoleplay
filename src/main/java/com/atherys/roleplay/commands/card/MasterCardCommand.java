package com.atherys.roleplay.commands.card;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Children;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.utils.PaginationUtils;
import com.atherys.roleplay.commands.card.set.*;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;

import javax.annotation.Nonnull;
import java.util.Arrays;

@Description("Displays list of commands.")
@Children({ CreateCardCommand.class,
            ShowCardCommand.class,
            SetCardDescriptionCommand.class,
            SetCardAgeCommand.class,
            SetCardNameCommand.class,
            SetCardNationCommand.class,
            SetCardNicknameCommand.class,
            ResetCardCommand.class })
@Aliases("card")
public class MasterCardCommand implements CommandExecutor {

    private static PaginationList commandList = PaginationUtils.paginate(
        "Character Cards",
        Arrays.asList(MasterCardCommand.class.getAnnotation(Children.class).value()),
        Arrays.asList(
            "/card create",
            "/card show",
            "/card description <desc>",
            "/card age <age>",
            "/card name <name>",
            "/card nation <nation>",
            "/card nickname <nick>",
            "/card reset"
        )
    );

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws CommandException {
        if(!(src instanceof Player)) return CommandResult.empty();
        commandList.sendTo(src);
        return CommandResult.success();
    }
}
