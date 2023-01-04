package io.shardmc.echo.util;

import java.io.Serializable;

public class Version implements Serializable {

    private final int major;
    private final int minor;
    private final int build;

    private Version(int major, int minor, int build) {
        this.major = major;
        this.minor = minor;
        this.build = build;
    }

    public static Version parse(String version) {
        String[] versions = version.split("\\.");
        return new Version(Integer.parseInt(versions[0]), Integer.parseInt(versions[1]), Integer.parseInt(versions[2]));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Version version) {
            return this.supports(version) && this.build == version.build;
        }

        return false;
    }

    public boolean supports(Version version) {
        return this.same(version) && this.minor == version.minor;
    }

    public boolean same(Version version) {
        return this.major == version.major;
    }

    @Override
    public String toString() {
        return this.major + "." + this.minor + this.build;
    }
}
