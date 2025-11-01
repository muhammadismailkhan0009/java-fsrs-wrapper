package com.myriadcode.fsrs.configs;

/** Minimal config mapped to generatorParameters(). Extend later if needed. */
public record FsrsConfig(
        boolean enableFuzz,
        boolean enableShortTerm
) {
    public static FsrsConfig defaults() {
        return new FsrsConfig(true, false);
    }
}
