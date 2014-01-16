package borrarcopias;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BorrarCopias {

	private static String[] arrayObjToString(Object[] arrayObj) {
        String[] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new String[nArrayObj];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (String) arrayObj[i];
            }
        }
        
        return res;
    }

	private static void dejaNumCopiasConf(File directorio) {
		File[] ficheros = directorio.listFiles();
		int nFics = ficheros.length;
		File fic;
		ArrayList<String> ficsEliminar = new ArrayList<>();

		for (int i = 0; i < nFics; i++) {
			fic = ficheros[i];

			if (fic.isDirectory())
				dejaNumCopiasConf(ficheros[i]);
			else
				ficsEliminar.add(fic.getAbsolutePath());
		}

		if (!ficsEliminar.isEmpty()) {
			String[] ficsElimArray = arrayObjToString(ficsEliminar.toArray());
			Arrays.sort(ficsElimArray);
			int nFicsElim = ficsElimArray.length;

			for (int i = 0; i < nFicsElim - Global.NUM_COPIAS_CONSERVAR; i++) {
				fic = new File(ficsElimArray[i]);
				if (fic.delete())
					System.out.println("Copia eliminada - " + ficsElimArray[i]);
				else
					System.out.println("ERROR - Fallo eliminando" + ficsElimArray[i]);
			}
		}
	}

	public static void main(String[] args) {
		try {
			Global.cargaConfiguracionGlobal();

			File rutaOrig = new File(Global.RUTA_COPIAS);

			if (!rutaOrig.exists())
				System.out.println("ERROR - No se encuentra el directorio de rutas.");
			else {
				if (!rutaOrig.isDirectory())
					System.out.println("ERROR - La ruta especificada no hace referencia a un directorio.");
				else
					dejaNumCopiasConf(rutaOrig);
			}
		} catch (IOException | NumberFormatException ex) {
			System.out.println("ERROR - " + ex.toString());
		}
	}
	
}