package hexlet.code;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable {
    @CommandLine.Parameters(description = "path to first file")
    private File filepath1;

    @CommandLine.Parameters(description = "path to second file")
    private File filepath2;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws IOException {
        Map<String, String> fileMap1 = Parser.start(filepath1);
        Map<String, String> fileMap2 = Parser.start(filepath2);
        String report = Differ.generate(fileMap1, fileMap2);
        System.out.println(report);
        return 0;
    }
}
