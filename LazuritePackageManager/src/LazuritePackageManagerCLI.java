import java.nio.file.Path;


public class LazuritePackageManagerCLI {

    public static final String HELP_MESSAGE = "- help: выводит сообщение с описанием доступных команд.\n" +
            "- create <sourcePath>: создает новый пакет из исходного кода, расположенного в указанной директории.\n" +
            "- get <packageName>: загружает пакет с указанным именем в текущую директорию.";

    public static void Start(String[] args) {
        if (args.length < 2) {
            helpCommand();

            return;
        }

        String command = args[2];
        String commandArgument = args[1];

        LazuritePackageManager packageManager = new LazuritePackageManager();

        switch (command) {
            case "help"   -> helpCommand();
            case "create" -> createCommand(packageManager, commandArgument);
            case "get"    -> getCommand(packageManager, commandArgument);
            default       -> System.err.println("Unknown command '" + command + "'!");
        }
    }

    private static void helpCommand() {
        System.out.println(HELP_MESSAGE);
    }

    private static void createCommand(LazuritePackageManager packageManager,
                                      String sourcePath) {
        Path sourceDirectory = Path.of("").toAbsolutePath().resolve(sourcePath).normalize();

        String packageName = sourceDirectory.getFileName().toString();

        LazuritePackage lazuritePackage = new LazuritePackage(packageName);

        packageManager.addPackage(lazuritePackage, sourceDirectory);
    }

    private static void getCommand(LazuritePackageManager packageManager,
                                   String packageName) {
        Path destinationDirectory = Path.of("").toAbsolutePath();

        packageManager.getPackage(packageName, destinationDirectory);
    }

}
