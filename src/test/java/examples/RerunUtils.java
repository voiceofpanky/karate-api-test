import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RerunUtils {

    private static final String RERUN_FILE = "target/rerun.txt";

    public static void saveFailedFeatures(Results results) {
        try {
            List<String> failed = new ArrayList<>();
            results.getFeatureResults().forEach(feature -> {
                if (feature.isFailed()) {
                    failed.add(feature.getPath());
                }
            });

            if (!failed.isEmpty()) {
                Files.createDirectories(Paths.get("target"));
                Files.write(Paths.get(RERUN_FILE), failed);
                System.out.println("⚠️  Saved failed features to: " + RERUN_FILE);
            } else {
                Files.deleteIfExists(Paths.get(RERUN_FILE));
                System.out.println("✅ No failed features. Rerun file cleared.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getFailedFeatures() {
        try {
            Path path = Paths.get(RERUN_FILE);
            if (Files.exists(path)) {
                return Files.readAllLines(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
