package mchorse.commander;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void executeClientCommand(ICommandSender sender, String command)
	{
		ClientCommandHandler.instance.executeCommand(sender, command);
	}

	@Override
	public void preLoad(FMLPreInitializationEvent event)
	{
		super.preLoad(event);

		ClientCommandHandler.instance.registerCommand(new CommandForinc());
	}
}