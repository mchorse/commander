package mchorse.commander.math.functions;

import mchorse.commander.math.IValue;

public class Sin extends Function
{
    public Sin(IValue[] values) throws Exception
    {
        super(values);
    }

    @Override
    public String getName()
    {
        return "sin";
    }

    @Override
    public int getRequiredArguments()
    {
        return 1;
    }

    @Override
    public double get()
    {
        return Math.sin(this.args[0].get());
    }
}