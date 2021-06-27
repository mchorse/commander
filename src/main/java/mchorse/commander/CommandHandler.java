package mchorse.commander;

import mchorse.mclib.math.IValue;
import mchorse.mclib.math.MathBuilder;
import mchorse.mclib.math.Variable;
import mchorse.mclib.utils.Rewriter;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.text.DecimalFormat;

/**
 * Command handler
 * 
 * This boy right there evaluates and replaces numerical 
 * variables when used with /forin command.
 */
public class CommandHandler
{
    public static final CommandHandler instance = new CommandHandler();

    public MathBuilder maths;
    public Rewriter rewriter;

    private boolean executingClient;

    private CommandHandler()
    {
        this.clearVariables();
    }

    public void clearVariables()
    {
        this.maths = new MathBuilder();
        this.rewriter = new SubRewriter("@\\{([^\\}]+)\\}", this.maths);
    }

    public void setVariable(String string, double value)
    {
        Variable var = this.maths.variables.get(string);

        if (var == null)
        {
            this.maths.register(new Variable(string, value));
        }
        else
        {
            var.set(value);
        }
    }

    public void unsetVariables(String... string)
    {
        for (String s : string)
        {
            this.maths.variables.remove(s);
        }
    }

    /**
     * This handler is responsible for processing commands. It does two 
     * things: 
     * 
     * 1. Evaluates expressions via @{} tags
     * 2. Breaks down multiple commands into separate in between || symbols
     */
    @SubscribeEvent
    public void onCommand(CommandEvent event)
    {
        if (this.executingClient)
        {
            this.executingClient = false;

            return;
        }

        if (event.getCommand() instanceof CommandForin || event.getCommand() instanceof CommandForinc)
        {
            return;
        }

        String command = String.join(" ", event.getParameters());
        ICommandSender sender = event.getSender();
        boolean remote = sender.getEntityWorld().isRemote;

        if (command.contains(" || "))
        {
            /* Handle multiple commands */
            String[] commands = command.split(" \\|\\| ");

            commands[0] = event.getCommand().getName() + " " + commands[0];

            if (remote)
            {
                this.executeClient(commands, sender);
            }
            else
            {
                this.executeCommands(commands, sender, sender.getServer().commandManager);
            }

            event.setCanceled(true);
        }
        else if (command.contains("@{"))
        {
            /* Handle substitution */
            command = this.rewriter.rewrite(command);

            if (remote)
            {
                this.executingClient = true;

                event.setCanceled(true);
                ClientCommandHandler.instance.executeCommand(sender, event.getCommand().getName() + " " + command);
            }
            else
            {
                event.setParameters(command.split(" "));
            }
        }
    }

    /**
     * Client side only command execution 
     */
    @SideOnly(Side.CLIENT)
    private void executeClient(String[] commands, ICommandSender sender)
    {
        this.executeCommands(commands, sender, ClientCommandHandler.instance);
    }

    /**
     * Execute commands with given sender and command manager
     */
    private void executeCommands(String[] commands, ICommandSender sender, ICommandManager handler)
    {
        for (String command : commands)
        {
            command = command.trim();

            if (command.isEmpty())
            {
                continue;
            }

            handler.executeCommand(sender, command);
        }
    }

    /**
     * This class is responsible for replacing placeholders 
     * within commands
     */
    public static class SubRewriter extends Rewriter
    {
        public MathBuilder builder;
        public DecimalFormat formater;

        public SubRewriter(String regex, MathBuilder builder)
        {
            super(regex);

            this.builder = builder;
            this.formater = new DecimalFormat("0.######");
        }

        @Override
        public String replacement()
        {
            try
            {
                IValue value = this.builder.parse(group(1)).get();

                return value.isNumber() ? this.formater.format(value.doubleValue()) : value.stringValue();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return "";
        }
    }
}