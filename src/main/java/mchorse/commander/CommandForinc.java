package mchorse.commander;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandForinс extends CommandForin
{
    public int index;

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