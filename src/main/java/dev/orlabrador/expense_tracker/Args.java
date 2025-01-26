package dev.orlabrador.expense_tracker;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = "-help", help = true)
    public boolean help;

    @Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
    public Integer verbose = 1;

    @Parameter(names = "-name", description = "name of instance")
    public String name;

    @Parameter(names = "-debug", description = "Debug mode")
    public boolean debug = false;
}
