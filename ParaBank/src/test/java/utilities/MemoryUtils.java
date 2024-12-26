package utilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
//import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class MemoryUtils {

    public static MemoryUsage getMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        return memoryBean.getHeapMemoryUsage();
    }

    public static double measureNetworkSpeed(String fileUrl) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(fileUrl);

        try {
            Instant start = Instant.now();
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream inputStream = entity.getContent()) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    long totalBytesRead = 0;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        totalBytesRead += bytesRead;
                    }

                    Instant end = Instant.now();
                    Duration duration = Duration.between(start, end);

                    double seconds = duration.toMillis() / 1000.0;
                    double megabits = (totalBytesRead * 8) / (1024.0 * 1024.0);

                    return megabits / seconds;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public static String generateHTMLReport(String title, MemoryUsage beforeMemoryUsage, MemoryUsage afterMemoryUsage, double beforeNetworkSpeed, double afterNetworkSpeed) {
        long memoryUsedBefore = beforeMemoryUsage.getUsed();
        long memoryUsedAfter = afterMemoryUsage.getUsed();
        long memoryConsumptionDifference = memoryUsedAfter - memoryUsedBefore;

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js\"></script>\n");
        sb.append("<title>").append(title).append("</title>\n");
        sb.append("<style>");
        sb.append(".thumbnail { max-width: 400px; }");
        sb.append("#memoryChart { max-width: 400px; max-height: 400px; }");
        sb.append("</style>");
        sb.append("</head>\n");
        sb.append("<body style=\"background-color: #F5F5F5; color: #333;\">\n");
        sb.append("<h1 style=\"color: #4CAF50;\">").append(title).append("</h1>\n");

        sb.append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; border-color: #333; color: #333;\">\n");
        sb.append("<tr><th colspan=\"2\" style=\"background-color: #FF5722; color: #FFF;\">Memory Consumption</th></tr>\n");
        sb.append("<tr><td>Memory Used Before</td><td>").append(memoryUsedBefore).append(" bytes</td></tr>\n");
        sb.append("<tr><td>Memory Used After</td><td>").append(memoryUsedAfter).append(" bytes</td></tr>\n");
        sb.append("<tr><td>Memory Consumption Difference</td><td>").append(memoryConsumptionDifference).append(" bytes</td></tr>\n");
        sb.append("</table>\n");

        sb.append("<br>");


        sb.append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; border-color: #333; color: #333;\">\n");
        sb.append("<tr><th colspan=\"2\" style=\"background-color: #FF5722; color: #FFF;\">Network Speed</th></tr>\n");
        sb.append("<tr><td>Network Speed Before</td><td>").append(beforeNetworkSpeed).append(" Mbps</td></tr>\n");
        sb.append("<tr><td>Network Speed After</td><td>").append(afterNetworkSpeed).append(" Mbps</td></tr>\n");
        sb.append("</table>\n");

        sb.append("<h2 style=\"background-color: #FF5722; color: #FFF;\">Memory Usage Chart</h2>\n");
        sb.append("<canvas id=\"memoryChart\"></canvas>\n");

        sb.append("<script>\n");
        sb.append("var ctx = document.getElementById('memoryChart').getContext('2d');\n");
        sb.append("var memoryChart = new Chart(ctx, {\n");
        sb.append("    type: 'bar',\n");
        sb.append("    data: {\n");
        sb.append("        labels: ['Memory Before', 'Memory After'],\n");
        sb.append("        datasets: [{\n");
        sb.append("            label: 'Memory Consumption',\n");
        sb.append("            data: [").append(memoryUsedBefore).append(", ").append(memoryUsedAfter).append("],\n");
        sb.append("            backgroundColor: [\n");
        sb.append("                'rgba(255, 99, 132, 0.7)',\n");
        sb.append("                'rgba(75, 192, 192, 0.7)',\n");
        sb.append("            ],\n");
        sb.append("            borderColor: [\n");
        sb.append("                'rgba(255, 99, 132, 1)',\n");
        sb.append("                'rgba(75, 192, 192, 1)',\n");
        sb.append("            ],\n");
        sb.append("            borderWidth: 1\n");
        sb.append("        }]\n");
        sb.append("    },\n");
        sb.append("    options: {\n");
        sb.append("        responsive: true,\n");
        sb.append("        legend: {\n");
        sb.append("            position: 'top',\n");
        sb.append("            labels: {\n");
        sb.append("                fontColor: '#333'\n");
        sb.append("            }\n");
        sb.append("        },\n");
        sb.append("        scales: {\n");
        sb.append("            yAxes: [{\n");
        sb.append("                ticks: {\n");
        sb.append("                    beginAtZero: true,\n");
        sb.append("                    fontColor: '#333'\n");
        sb.append("                }\n");
        sb.append("            }],\n");
        sb.append("            xAxes: [{\n");
        sb.append("                ticks: {\n");
        sb.append("                    fontColor: '#333'\n");
        sb.append("                }\n");
        sb.append("            }]\n");
        sb.append("        }\n");
        sb.append("    }\n");
        sb.append("});\n");
        sb.append("</script>\n");

        sb.append("</body>\n");
        sb.append("</html>");
        return sb.toString();
    }

    public static void writeToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openFileWithDefaultBrowser(String fileName) {
        try {
            File htmlFile = new File(fileName);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                throw new UnsupportedOperationException("Desktop browsing is not supported on this platform.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
