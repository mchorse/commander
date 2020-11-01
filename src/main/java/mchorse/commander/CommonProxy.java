package mchorse.commander;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public void executeClientCommand(ICommandSender sender, String command)
	{}

	public void preLoad(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(CommandHandler.instance);
	}
}
