package ec.gob.tienda.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="transacciones")
@NamedQuery(name="Transaccion.findAll", query="SELECT p FROM Transaccion p")
public class Transaccion implements Serializable{

	private static final long serialVersionUID = -1678984464813438042L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	private String producto;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private String cliente;	
	
	private String tienda;
		
	private BigDecimal subtotal;

	@Transient
	private Integer cantidadReporte;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getCantidadReporte() {
		return cantidadReporte;
	}

	public void setCantidadReporte(Integer cantidadReporte) {
		this.cantidadReporte = cantidadReporte;
	}
	
}
