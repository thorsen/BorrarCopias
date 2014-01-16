package borrarcopias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Victor Fernandez
 */
public class InteraccionFic {
    private String ruta;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader buffReader;
    
    public static String LINEA_SEP = "--------------------------------------------------------------------------------";
    public static String SALTO_LINEA = "\r\n";
    
    public static String OVERWRITE = "WW";
    public static String WRITE = "W";
    public static String READ = "R";
    
    public InteraccionFic(String ruta, String operacion) throws IOException {
        this.ruta = ruta;
        iniOpFichero(ruta, operacion);
    }
    
    //Función para iniciar las operaciones con el fichero  
    public final void iniOpFichero(String ruta, String operacion) throws IOException {
        if (operacion.contentEquals(OVERWRITE)) 
			this.fileWriter = new FileWriter(ruta, false);
		else {
			if (operacion.contains(WRITE))
				this.fileWriter = new FileWriter(ruta, true);
			if (operacion.contains(READ)) {
				this.fileReader = new FileReader(ruta);
				this.buffReader = new BufferedReader(this.fileReader);
			}
		}
    }
    
    //Función para acabar las operaciones con el fichero  
    public void finOpFichero() throws IOException {
        if (this.fileWriter != null) {
            this.fileWriter.flush();
            this.fileWriter.close();

            //Liberamos el fichero, porque ya está cerrado
            this.fileWriter = null;
        }
        
        if (this.fileReader != null) {
            this.buffReader.close();
            this.fileReader.close();

            //Liberamos el fichero, porque ya está cerrado
            this.fileReader = null;
        }
    }

    public void escribeFic(String texto) throws IOException {
        this.fileWriter.write(texto);
    }
    
    public void escribeLineaFic(String texto) throws IOException {
        this.fileWriter.write(texto + SALTO_LINEA);
    }
    
    public static boolean creaDir(String ruta) {
        File dir = new File(ruta);
        
        return dir.mkdir();
    }
    
    public static boolean renombraDir(String rutaOri, String rutaFin) {
        File dirOri = new File(rutaOri);
        File dirFin = new File(rutaFin);
        
        return dirOri.renameTo(dirFin);
    }
    
    //Función que devuelve la línea leída
    public String leeLinea() throws IOException {
        return this.buffReader.readLine();
    }
}
