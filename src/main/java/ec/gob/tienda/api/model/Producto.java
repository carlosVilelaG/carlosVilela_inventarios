package ec.gob.tienda.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String cod;

	private String name;

	private BigDecimal price;
	
	private Integer stock;
	
	//bi-directional many-to-one association to DetVenta
	@OneToMany(mappedBy="producto")
	private List<DetVenta> detVentas;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="producto")
	private List<Inventario> inventarios;

	public Producto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<DetVenta> getDetVentas() {
		return this.detVentas;
	}

	public void setDetVentas(List<DetVenta> detVentas) {
		this.detVentas = detVentas;
	}

	public DetVenta addDetVenta(DetVenta detVenta) {
		getDetVentas().add(detVenta);
		detVenta.setProducto(this);

		return detVenta;
	}

	public DetVenta removeDetVenta(DetVenta detVenta) {
		getDetVentas().remove(detVenta);
		detVenta.setProducto(null);

		return detVenta;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Inventario addInventario(Inventario inventario) {
		getInventarios().add(inventario);
		inventario.setProducto(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setProducto(null);

		return inventario;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", cod=" + cod + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}

}