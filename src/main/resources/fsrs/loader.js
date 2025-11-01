// evaluate the UMD bundle (already loaded by Java before this loader)
(function ensureFSRS() {
    // Probe globals to find the UMD export regardless of its name.
    const g = (typeof globalThis !== 'undefined') ? globalThis : this;

    function looksLikeFsrs(x) {
        try {
            return x && typeof x === 'object'
                && typeof x.fsrs === 'function'
                && typeof x.generatorParameters === 'function';
        } catch { return false; }
    }

    let found = null;
    for (const k of Object.getOwnPropertyNames(g)) {
        const v = g[k];
        if (looksLikeFsrs(v)) { found = v; break; }
    }

    if (!found) {
        // some UMDs export on "module.exports" branch if present; not the case here,
        // but keep a helpful error to diagnose quickly.
        throw new Error("FSRS UMD export not found on globalThis. Is dist/index.umd.js loaded?");
    }
    g.FSRS = found; // normalize to a stable name
})();
