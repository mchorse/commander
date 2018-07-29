package mchorse.commander;

import java.util.Arrays;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

public class CommandForin extends CommandBase
{
    @Override
    public String getName()
    {
        return "forin";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "commander.forin.help";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 3)
        {
            throw new WrongUsageException(this.getUsage(sender));
        }

        int start = CommandBase.parseInt(args[0]);
        int end = CommandBase.parseInt(args[1]);
        String command = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

        int step = end - start;

        if (step == 0)
        {
            throw new CommandException("commander.error.zero", start, end);
        }

        step /= Math.abs(step);

        for (; start != end + step; start += step)
        {
            Commander.handler.setVariable("i", start);

            server.getCommandManager().executeCommand(sender, command);
        }

        Commander.handler.unsetVariable("i");
    }
}