package mchorse.commander.math.functions;

import mchorse.commander.math.IValue;
import net.minecraft.util.math.MathHelper;

public class Clamp extends Function
{
    public Clamp(IValue[] values) throws Exception
    {
        super(values);
    }

    @Override
    public String getName()
    {
        return "clamp";
    }

    @Override
    public int getRequiredArguments()
    {
        return 3;
    }

    @Override
    public double get()
    {
        return MathHelper.clamp(this.args[0].get(), this.args[1].get(), this.args[2].get());
    }
}