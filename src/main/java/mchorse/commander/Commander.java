package mchorse.commander;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Commander mod main entry
 * 
 * This mod provides a new command called /forin
 */
@Mod(modid = Commander.MODID, name = Commander.MODNAME, version = Commander.VERSION, dependencies = "required-after:mclib@[%MCLIB%,)", updateJSON = "https://raw.githubusercontent.com/mchorse/commander/master/version.json")
public class Commander
{
    /* Mod info */
    public static final String MODID = "commander";
    public static final String MODNAME = "Commander";
    public static final String VERSION = "%VERSION%";

    /* Forge stuff */
    @Mod.Instance
    public static Commander instance;

    @SidedProxy(serverSide = "mchorse.commander.CommonProxy", clientSide = "mchorse.commander.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        proxy.preLoad(event);
    }

    @EventHandler
    public void startServer(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandForin());
        CommandHandler.instance.clearVariables();
    }
}