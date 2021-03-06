package mchorse.commander;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandForinc extends CommandForin
{
    @Override
    public String getName()
    {
        return "forinc";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "commander.forinc.help";
    }

    @Override
    protected void executeNestedCommand(MinecraftServer server, ICommandSender sender, String command)
    {
        Commander.proxy.executeClientCommand(sender, command);
    }
}