package mchorse.commander;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
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

    public static CommandHandler handler;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        handler = new CommandHandler();

        MinecraftForge.EVENT_BUS.register(handler);
    }

    @EventHandler
    public void startServer(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandForin());
        handler.clearVariables();
    }
}