package com.android.cervezapp.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.cervezapp.domain.util.SexoEnum;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;

	private String nombre;

	private String apellido;

	private SexoEnum sexo;

	private Date fechaNacimiento;

	private String telefono;

	private String email;

	private byte[] foto;

	private List<Bar> baresFavoritos = new ArrayList<Bar>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Bar> getBaresFavoritos() {
		return baresFavoritos;
	}

	public void setBaresFavoritos(List<Bar> baresFavoritos) {
		this.baresFavoritos = baresFavoritos;
	}

	public void addBarFavorito(Bar bar) {
		if (!this.baresFavoritos.contains(bar)) {
			this.baresFavoritos.add(bar);
		}
	}

	public void removeBarFavorito(Bar bar) {
		if (this.baresFavoritos.contains(bar)) {
			this.baresFavoritos.remove(bar);
		}
	}
}