package micronaut.circuit.breaker;

import io.micronaut.context.ApplicationContext;
import io.reactivex.Single;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

public class PutYourHandsUp {

    private AllTheSingle allTheSingle;

    @Before
    public void setUp() {
        allTheSingle = ApplicationContext.run().getBean(AllTheSingle.class);
    }

    @Test
    public void test() {
        allTheSingleErrors();
        allTheSingleErrors();
        allTheSingleErrors();
        allTheSingleErrors();
    }

    private void allTheSingleErrors() {
        Single errors;
        try {
            errors = allTheSingle.errors();
        } catch (Exception unexpected) {
            fail("Exception should not have been thrown here, expected Single.error(exception) returned from DefaultRetryInterceptor's retryState CircuitBreakerRetry.open()");
            throw unexpected;
        }

        try {
            errors.blockingGet();
        } catch (Exception expectedAndIgnored) {

        }
    }

}
