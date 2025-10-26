// import java.io.*;
// import java.nio.file.*;
// import java.util.*;

// public class RerunUtils {

//     private static final String RERUN_FILE = "target/rerun.txt";

//     public static void saveFailedFeatures(Results results) {
//         try {
//             List<String> failed = new ArrayList<>();
//             results.getFeatureResults().forEach(feature -> {
//                 if (feature.isFailed()) {
//                     failed.add(feature.getPath());
//                 }
//             });

//             if (!failed.isEmpty()) {
//                 Files.createDirectories(Paths.get("target"));
//                 Files.write(Paths.get(RERUN_FILE), failed);
//                 System.out.println("⚠️  Saved failed features to: " + RERUN_FILE);
//             } else {
//                 Files.deleteIfExists(Paths.get(RERUN_FILE));
//                 System.out.println("✅ No failed features. Rerun file cleared.");
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public static List<String> getFailedFeatures() {
//         try {
//             Path path = Paths.get(RERUN_FILE);
//             if (Files.exists(path)) {
//                 return Files.readAllLines(path);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return Collections.emptyList();
//     }
// }

// // If you want to skip passing tests on next Gradle run, modify your runner to check if a rerun.txt exists and only run those tests automatically:

// // List<String> failedFeatures = RerunUtils.getFailedFeatures();
// // Results results;

// // if (!failedFeatures.isEmpty()) {
// //     System.out.println("🧠 Running only previously failed tests: " + failedFeatures);
// //     results = Runner.path(failedFeatures).parallel(3);
// // } else {
// //     results = Runner.path("classpath:examples/users").parallel(3);
// // }