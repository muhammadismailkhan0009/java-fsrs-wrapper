package com.myriadcode.fsrs.internal;


import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsBridge implements AutoCloseable {

    private final Context ctx;
    private final Value fsrs;
    private final Value fsrsFactory;

    //    just for the sake of mappers.
    public Context getCtx() {
        return ctx;
    }

    public JsBridge() {
        this.ctx = Context.newBuilder("js")
                .allowAllAccess(true)
                .option("js.ecmascript-version", "2022")
                .build();

        ctx.eval("js", readResource("/fsrs/fsrs.js"));

        Value g = ctx.getBindings("js");
        this.fsrs = g.getMember("FSRS");
        if (fsrs == null)
            throw new IllegalStateException("FSRS functions not loaded");

        this.fsrsFactory = fsrs.getMember("fsrs");


    }

    public Value newCard(Value date) {
        var jsFunction = fsrs.getMember("createEmptyCard");
        return jsFunction.execute(date);
    }

    public Value generatorParameters() {
        var jsFunction = fsrs.getMember("generatorParameters");
        return jsFunction.execute();
    }

    public Value repeat(Value card, Value reviewTime) {
        var factory = fsrsFactory.execute();
        var function = factory.getMember("repeat");
        return function.execute(card, reviewTime);
    }

    private static String readResource(String path) {
        try (InputStream is = JsBridge.class.getResourceAsStream(path)) {
            if (is == null) throw new RuntimeException("Missing resource: " + path);
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        ctx.close();
    }
}
