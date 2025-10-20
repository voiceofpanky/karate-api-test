package examples.users;
import com.intuit.karate.Runner;
import com.intuit.karate.Results;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RunnerWithRetry {

    private static final int MAX_RETRIES = 2;

    @Test
    void runWithRerunLogic() {
        Results results = Runner.path("classpath:examples/users").parallel(3);
        RerunUtils.saveFailedFeatures(results);

        int retryCount = 0;
        while (results.getFailCount() > 0 && retryCount < MAX_RETRIES) {
            retryCount++;
            List<String> failedFeatures = RerunUtils.getFailedFeatures();
            if (failedFeatures.isEmpty()) break;

            System.out.println("🔁 Retrying failed features (attempt " + retryCount + "): " + failedFeatures);
            results = Runner.path(failedFeatures).parallel(3);
            RerunUtils.saveFailedFeatures(results);
        }

        Assertions.assertEquals(0, results.getFailCount(),
                "❌ Some tests still failed after " + (retryCount + 1) + " attempts.");
    }
}
