package ec.gob.tienda.api.dto;

import java.io.Serializable;
import java.util.Date;

public class InformacionTransaccion implements Serializable{

	private static final long serialVersionUID = -2591186299617530414L;

	Long   campo1;
	String campo2;
	String campo3;
	Date   campo4;
	
	
	public InformacionTransaccion(Long campo1, String campo2, String campo3) {
		this.campo1 = campo1;
		this.campo2 = campo2;
		this.campo3 = campo3;
	}
	
	public InformacionTransaccion(Long campo1, String campo2, Date campo4) {
		this.campo1 = campo1;
		this.campo2 = campo2;
		this.campo4 = campo4;
	}
	
	public Long getCampo1() {
		return campo1;
	}
	public void setCampo1(Long campo1) {
		this.campo1 = campo1;
	}
	public String getCampo2() {
		return campo2;
	}
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}
	public String getCampo3() {
		return campo3;
	}
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	public Date getCampo4() {
		return campo4;
	}

	public void setCampo4(Date campo4) {
		this.campo4 = campo4;
	}
	
		
}