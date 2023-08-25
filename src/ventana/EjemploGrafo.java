package ventana;
import grafo.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceDialog;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class EjemploGrafo extends Application {

    private Grafo grafo = new Grafo();
    private Grafo grafoComprobar = new Grafo();
    private Random random = new Random();


    public static void main(String[] args) {
        launch(args);
    }
    
    private List<String> getFileNamesFromFolder(String folderPath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(folderPath);
        
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        }
        
        return fileNames;
    }
    private double generateRandomWeight() {
        return random.nextDouble() * 10.0; // Generates a random weight between 0.0 and 10.0
    }


    @Override
    public void start(Stage primaryStage) {
    	String folderPath = "./your_folder_path/";
    	List<String> vertexNames = getFileNamesFromFolder(folderPath);
    	// Agregar vértices
    	for (String name : vertexNames) {
    	    Vertice vertex = new Vertice(name);
    	    System.out.println(name);
    	    grafo.agregarVertice(vertex);
    	}

    	// Agregar aristas...

    	String rutaRelativa = "./your_folder_path/document1.txt"; // Cambia esto a la ruta relativa de tu archivo
    	String nombresEncontrados = BuscarPalabraAntesDeTxt.buscarYFormarNombres(rutaRelativa);
    	List<Vertice> verticesList = new ArrayList<>(grafo.obtenerVertices());

    	for (Vertice antesVertex : verticesList) {
    	    for (Vertice despuesVertex : verticesList) {
    	        if (antesVertex != despuesVertex && nombresEncontrados!=(String)despuesVertex.obtenerDato() && random.nextBoolean()) {
    	            double peso = generateRandomWeight();
    	            System.out.println(nombresEncontrados);
    	            System.out.println(despuesVertex.obtenerDato());
    	            Vertice verte01 = new Vertice(nombresEncontrados);
    	            grafoComprobar.agregarVertice(verte01);
    	            
    	            grafo.agregarArista(antesVertex, despuesVertex, peso);
    	            
    	            System.out.println("Arista agregada entre " + antesVertex.obtenerDato() + " y " + despuesVertex.obtenerDato());
    	        }
    	    }
    	}

        
        
     
        
        /*
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B")
       

        grafo.agregarVertice(v1);
        grafo.agregarVertice(v2);
        
        
        grafo.agregarArista(v1, v2, 5.0);*/
        
        int complejidad = grafo.obtenerComplejidadNotacionO();
        Label complejidadLabel = new Label("Complejidad O: " + complejidad);
        complejidadLabel.setLayoutX(10);
        complejidadLabel.setLayoutY(10);
        

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTGRAY);

        // Dibuja los vértices
        double centerX = 400;
        double centerY = 300;
        double radius = 150;
        double angleStep = 2 * Math.PI / grafo.obtenerVertices().size();
        double angle = 0;
        int index = 0;

        for (Vertice vertice : grafo.obtenerVertices()) {
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);

            Circle circle = new Circle(x, y, 20, Color.DARKBLUE);
            root.getChildren().add(circle);

            Text text = new Text((String) vertice.obtenerDato());
            text.setX(x - 10);
            text.setY(y - 25);
            root.getChildren().add(text);

            angle += angleStep;
            index++;
        }

        // Dibuja las aristas
        for (Vertice vertice : grafo.obtenerVertices()) {
            double x1 = centerX + radius * Math.cos(angle - angleStep);
            double y1 = centerY + radius * Math.sin(angle - angleStep);

            for (Arista arista : grafo.obtenerAristas(vertice)) {
                Vertice destino = arista.obtenerDestino();
                double x2 = centerX + radius * Math.cos(angleStep * index);
                double y2 = centerY + radius * Math.sin(angleStep * index);

                Line line = new Line(x1, y1, x2, y2);
                root.getChildren().add(line);
                index++;
            }

            angle -= angleStep;
        }
        
        String mensaje = "";
        Alert dialog = new Alert(null);
        dialog.setTitle("Eliminar Nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Complejidad O: " + complejidad);

        root.getChildren().add(complejidadLabel);
        primaryStage.setTitle("Grafo Animado");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
