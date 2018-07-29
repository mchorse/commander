package mchorse.commander;

import java.text.DecimalFormat;

import mchorse.commander.math.MathBuilder;
import mchorse.commander.math.Variable;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Command handler
 * 
 * This boy right there evaluates and replaces numerical 
 * variables when used with /forin command.
 */
public class CommandHandler
{
    public MathBuilder maths;
    public Rewriter rewriter;

    public CommandHandler()
    {
        this.clearVariables();
    }

    public void clearVariables()
    {
        this.maths = new MathBuilder();
        this.rewriter = new SubRewriter("@\\{([^\\}]+)\\}", this.maths);
    }

    public void setVariable(String string, int start)
    {
        this.maths.register(new Variable(string, start));
    }

    public void unsetVariable(String string)
    {
        this.maths.variables.remove(string);
    }

    @SubscribeEvent
    public void onCommand(CommandEvent event)
    {
        if (event.getCommand() instanceof CommandForin)
        {
            return;
        }

        String command = String.join(" ", event.getParameters());

        if (command.contains("@{"))
        {
            command = this.rewriter.rewrite(command);
            event.setParameters(command.split(" "));
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
            this.formater = new DecimalFormat("0.#");
        }

        @Override
        public String replacement()
        {
            try
            {
                return this.formater.format(this.builder.parse(group(1)).get());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return "";
        }
    }
}