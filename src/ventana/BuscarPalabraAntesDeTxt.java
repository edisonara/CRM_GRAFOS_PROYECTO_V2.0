package ventana;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BuscarPalabraAntesDeTxt {
    public static String buscarYFormarNombres(String archivo) {
        StringBuilder nombres = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(".txt")) {
                    String palabraAntesDeTxt = obtenerPalabraAntesDeTxt(linea, ".txt");
                    nombres.append(palabraAntesDeTxt).append(".txt\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nombres.toString();
    }

    public static String obtenerPalabraAntesDeTxt(String linea, String textoBuscado) {
        int indexOfTexto = linea.indexOf(textoBuscado);
        if (indexOfTexto != -1) {
            int inicioDePalabra = linea.lastIndexOf(" ", indexOfTexto - 1);
            if (inicioDePalabra == -1) {
                inicioDePalabra = 0;
            } else {
                inicioDePalabra++;
            }
            return linea.substring(inicioDePalabra, indexOfTexto);
        }
        return "";
    }

    public static void main(String[] args) {
        String rutaRelativa = "archivo.txt"; // Cambia esto a la ruta relativa de tu archivo
        String nombresEncontrados = buscarYFormarNombres(rutaRelativa);

        if (!nombresEncontrados.isEmpty()) {
            System.out.println("Nombres encontrados antes de .txt:\n" + nombresEncontrados);
        } else {
            System.out.println("No se encontraron nombres antes de .txt en el documento.");
        }
    }
}
