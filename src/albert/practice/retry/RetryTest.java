package albert.practice.retry;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

//https://github.com/jhalterman/failsafe
@Slf4j
public class RetryTest {

    public static void main(String[] args) throws ConnectionException {
        @SuppressWarnings("unchecked")
        RetryPolicy retryPolicy = new RetryPolicy().retryOn(ConnectionException.class)
                .withDelay(2, TimeUnit.SECONDS).withMaxRetries(5);
        Failsafe.with(retryPolicy).run(() -> new RetryTest().connect());
    }

    public void connect() throws ConnectionException {
        log.debug("time=" + new Date(Calendar.getInstance().getTimeInMillis()));
        if (true) {
             throw new ConnectionException("connection fail!");
        }
    }
}
