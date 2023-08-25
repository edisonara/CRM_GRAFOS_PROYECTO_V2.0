package ventana;
/*
<dependency>
<groupId>org.commonmark</groupId>
<artifactId>commonmark</artifactId>
<version>0.20.0</version>
</dependency>*/

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DocumentConnectionManager {
    private static Map<String, List<String>> documentConnections = new HashMap<>();

    public static void main(String[] args) {
        generateTextFile("document1.txt", "Document 1 content.", null);
        generateTextFile("document2.txt", "Document 2 content.", "document1.txt");

        // Print document connections
        for (Map.Entry<String, List<String>> entry : documentConnections.entrySet()) {
            String document = entry.getKey();
            List<String> connections = entry.getValue();
            
            System.out.println("Document: " + document);
            System.out.println("Connected to: " + connections);
            System.out.println();
        }
    }

    public static void generateTextFile(String fileName, String content, String connectedTo) {
        // Save the content to a text file
        try {
            Path filePath = Paths.get("your_folder_path/" + fileName);
            Files.write(filePath, content.getBytes());

            if (connectedTo != null) {
                documentConnections.computeIfAbsent(fileName, k -> new ArrayList<>()).add(connectedTo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
