package hexlet.code;

import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "getDiff", mixinStandardHelpOptions = true, version = "1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String formatName = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public final String call() throws IOException {
        System.out.println(Differ.generate(filepath1, filepath2, formatName));
        return "";
    }
}
