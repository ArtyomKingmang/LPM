import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for manipulating and containing information about Lazurite Package
 */
public class LazuritePackage {

    /**
     * Constructor for package
     * @param name Package name
     * @param author Package author
     * @param versions Package versions
     */
    public LazuritePackage(String name,
                           String author,
                           ArrayList<String> versions) {
        this._name     = name;
        this._author   = author;
        this._versions = versions;
    }

    /**
     * Constructor for package
     * @param name Package name
     * @param author Package author
     * @param version Package version
     */
    public LazuritePackage(String name,
                           String author,
                           String version) {
        this._name     = name;
        this._author   = author;
        this._versions = new ArrayList<>(List.of(version));
    }

    // TODO delete
    public LazuritePackage(String name,
                           String author) {
        this(name, author, new ArrayList<>());
    }

    // TODO delete
    public LazuritePackage(String name) {
        this(name, "<author>");
    }

    /**
     * Add new version of package
     * @param version Version
     */
    public void addVersion(String version) {
        _versions.add(version);
    }

    /**
     * Has version of package
     * @param version Version
     * @return Has version of package
     */
    public boolean hasVersion(String version) {
        return _versions.contains(version);
    }

    /**
     * Remove version of package
     * @param version Version
     */
    public void removeVersion(String version) {
        _versions.remove(version);
    }

    /**
     * Get relative path of package
     * @return Relative path of package
     */
    public Path getPath() {
        return Path.of(_name);
    }

    /**
     * Get name of package
     * @return Name of package
     */
    public String getName() {
        return _name;
    }

    /**
     * Get author of package
     * @return Author of package
     */
    public String getAuthor() {
        return _author;
    }

    /**
     * Get versions of package
     * @return Versions of package
     */
    public ArrayList<String> getVersions() {
        return _versions;
    }

    /**
     * Is equals objects
     * @param object Object
     * @return Is equals objects
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        LazuritePackage lazuritePackage = (LazuritePackage) object;

        return Objects.equals(_name, lazuritePackage._name)
            && Objects.equals(_author, lazuritePackage._author)
            && Objects.equals(_versions, lazuritePackage._versions);
    }

    /**
     * Hash code of package
     * @return Hash code of package
     */
    @Override
    public int hashCode() {
        return Objects.hash(_name, _author, _versions);
    }

    /**
     * Package as string
     * @return Package as string
     */
    @Override
    public String toString() {
        return "LazuritePackage{" +
                "name='" + _name + '\'' +
                ", author='" + _author + '\'' +
                ", versions=" + _versions +
                '}';
    }

    /**
     * Name
     */
    private final String _name;

    /**
     * Author
     */
    private final String _author;

    /**
     * Versions
     */
    private final ArrayList<String> _versions;

}
