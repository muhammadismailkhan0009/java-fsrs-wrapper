package com.myriadcode.fsrs.mappers;

import com.myriadcode.fsrs.domain.enums.Rating;
import com.myriadcode.fsrs.domain.enums.State;
import com.myriadcode.fsrs.domain.models.Card;
import com.myriadcode.fsrs.domain.models.ReviewLog;
import com.myriadcode.fsrs.domain.models.ReviewResult;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

public class EngineMappers {

    private EngineMappers() {
    }

    public static Card toCard(Value jsCard) {
        return new Card(
                jsCard.getMember("difficulty").asDouble(),
                jsCard.getMember("due").asInstant(),
                jsCard.getMember("elapsed_days").asInt(),
                jsCard.getMember("lapses").asInt(),
                jsCard.getMember("last_review").asInstant(),
                jsCard.getMember("learning_steps").asInt(),
                jsCard.getMember("reps").asInt(),
                jsCard.getMember("scheduled_days").asInt(),
                jsCard.getMember("stability").asDouble(),
                State.fromInt(jsCard.getMember("state").asInt())
        );
    }

    public static Value toCardJs(Context ctx, Card c) {
        Value m = ctx.eval("js", "({})"); // empty JS object

        m.putMember("due", Date.from(c.due()));
        m.putMember("stability", c.stability());
        m.putMember("difficulty", c.difficulty());
        m.putMember("elapsed_days", c.elapsedDays());
        m.putMember("scheduled_days", c.scheduledDays());
        m.putMember("reps", c.reps());
        m.putMember("lapses", c.lapses());
        m.putMember("last_review", c.lastReview() == null ? null : Date.from(c.lastReview()));
        m.putMember("state", c.state().getValue());  // enum → number
        m.putMember("step", c.learningSteps());
        return m;
    }


    public static ReviewLog toReviewLog(Value jsLog) {
        return new ReviewLog(
                jsLog.getMember("difficulty").asDouble(),
                jsLog.getMember("due").asInstant(),
                jsLog.getMember("elapsed_days").asInt(),
                jsLog.getMember("last_elapsed_days").asInt(),
                jsLog.getMember("learning_steps").asInt(),
                Rating.fromInt(jsLog.getMember("rating").asInt()),
                jsLog.getMember("review").asInstant(),
                jsLog.getMember("scheduled_days").asInt(),
                jsLog.getMember("stability").asDouble(),
                State.fromInt(jsLog.getMember("state").asInt())
        );
    }


    public static ReviewResult toReviewResult(Value jsMap) {
        Map<Rating, ReviewResult.ReviewData> result = new EnumMap<>(Rating.class);

        for (String key : jsMap.getMemberKeys()) {
            Rating rating = Rating.fromInt(Integer.parseInt(key));
            Value entry = jsMap.getMember(key);

            Card card = toCard(entry.getMember("card"));
            ReviewLog log = toReviewLog(entry.getMember("log"));

            result.put(rating, new ReviewResult.ReviewData(card, log));
        }

        return new ReviewResult(result);
    }

}
