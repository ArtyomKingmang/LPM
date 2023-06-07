import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.io.*;

/**
 * Class for manipulating and containing information about Lazurite Package Manager
 */
public class LazuritePackageManager {

    /**
     * Constructing package manager
     * @param packages Packages
     * @param rootPath Root path
     */
    public LazuritePackageManager(ArrayList<LazuritePackage> packages,
                                  Path rootPath) {
        this._packages = packages;
        this._rootPath = rootPath;

        try {
            Files.list(_rootPath).forEach(path -> {
                File file = path.toFile();

                if (file.isDirectory()) {
                    LazuritePackage lazuritePackage = new LazuritePackage(file.getName());

                    addPackage(lazuritePackage);
                }
            });
        } catch (IOException exception) {
            System.err.println(exception.toString());
        }
    }

    /**
     * Constructing package manager with custom root path
     * @param rootPath Root path
     */
    public LazuritePackageManager(Path rootPath) {
        this(new ArrayList<>(), rootPath);
    }

    /**
     * Constructing package manager with default root path kind of "C:/Users/{USER_NAME}/.lzpm"
     */
    public LazuritePackageManager() {
        this(Path.of(System.getProperty("user.home")).resolve(".lpm"));
    }

    /**
     * Add package to package manager
     * @param lazuritePackage Package
     */
    public void addPackage(LazuritePackage lazuritePackage) {
        _packages.add(lazuritePackage);
    }

    /**
     * Add package to package manager and copying it to package manager directory
     * @param lazuritePackage Package
     * @param sourcePath Source path of package
     */
    public void addPackage(LazuritePackage lazuritePackage,
                           Path sourcePath) {
        addPackage(lazuritePackage);

        File sourceDirectory = sourcePath.toFile();
        File destinationDirectory = _rootPath.resolve(lazuritePackage.getName()).toFile();

        try {
            FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        } catch (IOException exception) {
            System.err.println(exception.toString());
        }
    }

    /**
     * Get package from package manager
     * @param packageName Package name
     * @return Package or null
     */
    public Optional<LazuritePackage> getPackage(String packageName) {
        return _packages.stream()
                        .filter(lazuritePackage -> lazuritePackage.getName().equals(packageName))
                        .findFirst();
    }

    /**
     * Get package from package manager with copying it to working directory
     * @param packageName Package name
     * @param destinationPath Path to copying package
     * @return Package or null
     */
    public Optional<LazuritePackage> getPackage(String packageName,
                                                Path destinationPath) {
        Optional<LazuritePackage> optionalLazuritePackage = getPackage(packageName);

        if (optionalLazuritePackage.isEmpty()) {
            System.err.println("Can`t found package '" + packageName + "'!");

            return optionalLazuritePackage;
        }

        LazuritePackage lazuritePackage = optionalLazuritePackage.get();

        File sourceDirectory = _rootPath.resolve(lazuritePackage.getPath()).toFile();
        File destinationDirectory = destinationPath.resolve(lazuritePackage.getName()).toFile();

        try {
            FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        } catch (IOException exception) {
            System.err.println(exception.toString());
        }

        return optionalLazuritePackage;
    }

    /**
     * Packages
     */
    private final ArrayList<LazuritePackage> _packages;

    /**
     * Root path of packages
     */
    private final Path _rootPath;

}
