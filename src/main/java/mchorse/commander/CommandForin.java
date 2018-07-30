package mchorse.commander;

import java.util.Arrays;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

public class CommandForin extends CommandBase
{
    public int index;

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
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 3)
        {
            throw new WrongUsageException(this.getUsage(sender));
        }

        /* What do you think you're doing... */
        if (this.index > 3)
        {
            return;
        }

        int start = CommandBase.parseInt(args[0]);
        int end = CommandBase.parseInt(args[1]);
        String command = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

        int step = end - start;

        if (step == 0)
        {
            throw new CommandException("commander.error.zero", start, end);
        }

        CommandHandler handler = Commander.handler;

        handler.setVariable("x", sender.getPosition().getX());
        handler.setVariable("y", sender.getPosition().getY());
        handler.setVariable("z", sender.getPosition().getZ());

        this.index++;

        String index = this.getIndexName();

        handler.setVariable(index + "_c", Math.abs(step) + 1);
        handler.setVariable(index + "_s", start);
        step /= Math.abs(step);

        for (; start != end + step; start += step)
        {
            handler.setVariable(index, start);

            server.getCommandManager().executeCommand(sender, command);
        }

        this.index--;
        handler.unsetVariable(index, index + "_c", index + "_s", "x", "y", "z");
    }

    /**
     * Get index variable name based on current index 
     */
    private String getIndexName()
    {
        if (this.index == 1)
        {
            return "i";
        }
        else if (this.index == 2)
        {
            return "j";
        }

        return "k";
    }
}