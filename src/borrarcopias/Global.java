package borrarcopias;

import java.io.IOException;

public class Global {
	public static String RUTA_COPIAS;
	public static Integer NUM_COPIAS_CONSERVAR;

	private static String FIC_CONF_GLOBAL = "BorrarCopias.conf";

	public static void cargaConfiguracionGlobal() throws IOException, NumberFormatException {
		InteraccionFic interfic = new InteraccionFic(FIC_CONF_GLOBAL, InteraccionFic.READ);
		RUTA_COPIAS = interfic.leeLinea().trim();
		NUM_COPIAS_CONSERVAR = Integer.parseInt(interfic.leeLinea().trim());
		interfic.finOpFichero();
	}
}
