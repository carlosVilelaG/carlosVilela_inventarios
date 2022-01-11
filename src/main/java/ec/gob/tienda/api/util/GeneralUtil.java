package ec.gob.tienda.api.util;

public class GeneralUtil {

	public static String keyword = "invierte ecuador";
	private static final int sizeOfIntInHalfBytes = 8;
	private static final int numberOfBitsInAHalfByte = 4;
	private static final int halfByte = 0x0F;
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	public static final String STOCK_FALTANTE_MENOR_A_CINCO = "STOCK FALTANTE MENOR A CINCO";
	public static final String STOCK_FALTANTE_ENTRE_CINCO_Y_DIEZ = "STOCK FALTANTE ENTRE CINCO Y DIEZ";
	public static final String STOCK_FALTANTE_MAYOR_A_DIEZ = "STOCK FALTANTE MAS DE DIEZ";
	public static final String STOCK_NO_EXISTE = "PRODUCTO NOP EXISTE";
	public static final String STOCK_OK = "STOCK OK";
	public static final String PETICION_10_PRODUCTOS = "PETICION_10_PRODUCTOS";
	public static final String PETICION_5_PRODUCTOS = "PETICION_5_PRODUCTOS";
}
