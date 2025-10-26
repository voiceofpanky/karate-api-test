    package runner;
     import static org.junit.jupiter.api.Assertions.assertTrue; 
    import com.intuit.karate.Results;
    import com.intuit.karate.Runner;
    import org.junit.jupiter.api.Test;
   

    class KarateRunner {

        @Test
        void testParallel() {
            Results results = Runner.path("classpath:features")
                                   .tags("@smoke")
                                   .parallel(5); // Run 5 threads in parallel
            assertTrue(results.getFailCount() == 0, results.getErrorMessages());
        }

        // You can add more @Test methods to run specific feature files or tags
        // @Test
        // void testSpecificFeature() {
        //     Results results = Runner.path("classpath:com/example/features/my_feature.feature").parallel(1);
        //     assertTrue(results.getFailCount() == 0, results.getErrorMessages());
        // }
    }