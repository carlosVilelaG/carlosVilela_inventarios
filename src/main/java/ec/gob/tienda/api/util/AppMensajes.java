package ec.gob.tienda.api.util;

public enum AppMensajes {
	COD_EXITO(1000, "TRANSACCION EXITOSA"), COD_ERROR_GEN(1001, "ERROR GENERAL DEL SISTEMA"),
	COD_ERROR_BD(1002, "ERROR DE CONEXION A LA BASE DE INFORMACION"), COD_ERROR_ACCESO(1003, "ERROR DE AUTORIZACION"),
	COD_ERROR_AUTENTICATION(1004, "USER Y PASSWORD INCORRECTO"), COD_ERROR_TOKEN(1005, "USER Y PASSWORD INCORRECTO"),
	COD_ERROR_STOCK_PRODUCTO_INVALIDO(1,"STOCK DEL PRODUCTO NO PUEDE SER MENOR O IGUAL A CERO"),
	COD_ERROR_STOCK_UNIDAD_NO_DISPONIBLE(2,"UNIDADES NO DISPONIBLES (>10)"),
	COD_ERROR_NO_EXISTE_CLIENTE(3,"NO EXISTE EL CLIENTE"),
	COD_ERROR_FECHAS_NO_ADECUADAS(4,"FECHAS NO ADECUADAS PARA EL REPORTE"),
	COD_ERROR_CONSULTA_SIN_RESULTADO(4,"CONSULTA SIN RESULTADO");
    
	private Integer codigo;
	private String descripcion;

	private AppMensajes(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
