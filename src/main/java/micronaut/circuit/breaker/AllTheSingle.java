package micronaut.circuit.breaker;

import io.micronaut.retry.annotation.CircuitBreaker;
import io.reactivex.Single;

public class AllTheSingle {

    @CircuitBreaker(delay = "0s", attempts = "3", multiplier = "1", maxDelay = "2s", reset = "10s")
    Single errors() {
        return Single.error(new RuntimeException());
    };

}
