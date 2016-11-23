package albert.practice.retry;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

//https://github.com/jhalterman/failsafe
@Slf4j
public class RetryTest {

    private int count = 0;

    public static void main(String[] args) throws ConnectionException {
        new RetryTest().connectWithRetry();
    }

    public Connection connectWithRetry() {
        // create a retry policy with 5 max retries and have 2 seconds delay among retries
        RetryPolicy retryPolicy = new RetryPolicy();
//        retryPolicy.retryOn(ConnectionException.class).withDelay(2, TimeUnit.SECONDS)
//                .withMaxRetries(5);

        // create a retry policy and sets the delay 5 seconds between retries, exponentially backing off to the maxDelay 120
        // seconds and multiplying successive delays by the delayFactor 2.
        retryPolicy.retryOn(ConnectionException.class).withMaxRetries(5).withBackoff(5, 120,
                TimeUnit.SECONDS, 2);

        // Using Fallbacks allow you to provide an alternative result for a failed execution.
        // In this example, it will retry again after 5 seconds.
        Connection conn = Failsafe.with(retryPolicy).withFallback(() -> retryIfFail())
                .get(() -> connect());

        return conn;
    }

    public void retryIfFail() throws InterruptedException {
        log.debug("GG at " + getCurrentTime());
        Thread.sleep(5000);

        log.debug("retry....." + getCurrentTime());
        connectWithRetry();
    }

    public Connection connect() throws ConnectionException {
        log.debug(" time = " + getCurrentTime());
        Connection conn = null;
        if (count < 9) {
            count++;
            throw new ConnectionException("connection fail!");
        } else {
            log.debug("get connection successfuly...");
        }
        return conn;
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        return dateFormat.format(new Date());
    }
}
