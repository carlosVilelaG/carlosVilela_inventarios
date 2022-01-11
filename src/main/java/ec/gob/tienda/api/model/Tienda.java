package ec.gob.tienda.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tiendas database table.
 * 
 */
@Entity
@Table(name="tiendas")
@NamedQuery(name="Tienda.findAll", query="SELECT t FROM Tienda t")
public class Tienda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	//bi-directional many-to-one association to DetVenta
	@OneToMany(mappedBy="tienda")
	private List<DetVenta> detVentas;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="tienda")
	private List<Inventario> inventarios;

	public Tienda() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DetVenta> getDetVentas() {
		return this.detVentas;
	}

	public void setDetVentas(List<DetVenta> detVentas) {
		this.detVentas = detVentas;
	}

	public DetVenta addDetVenta(DetVenta detVenta) {
		getDetVentas().add(detVenta);
		detVenta.setTienda(this);

		return detVenta;
	}

	public DetVenta removeDetVenta(DetVenta detVenta) {
		getDetVentas().remove(detVenta);
		detVenta.setTienda(null);

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
		inventario.setTienda(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setTienda(null);

		return inventario;
	}

}