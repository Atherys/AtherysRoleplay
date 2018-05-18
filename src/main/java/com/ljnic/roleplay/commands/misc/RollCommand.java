package com.ljnic.roleplay.commands.misc;

import com.atherys.core.command.ParameterizedCommand;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;

public class RollCommand implements ParameterizedCommand {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        return null;
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[0];
    }
}
