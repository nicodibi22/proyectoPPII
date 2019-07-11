package proyecto.servicios.impl;

import java.util.Date;

public class Monitor {

	private boolean estaCerrado;
	
	private boolean estaAbierto;
	
	private int timeout;
	
	private Date fechaAbierto;
	
	private int intentosMaximos;
	
	private int intentosFallidos;
	
	public Monitor(int timeout, int intentosMax) {
		this.timeout = timeout;
		this.intentosMaximos = intentosMax;
		this.estaAbierto = false;
		this.estaCerrado = true;
	}
	
	public void reset() {
		this.estaAbierto = false;
		this.estaCerrado = true;
		this.intentosFallidos = 0;
		this.fechaAbierto = null;
	}
	
	public void aumentarIntentoFallido() {
		this.intentosFallidos++;
		
		if (this.intentosFallidos == this.intentosMaximos) {
			
			fechaAbierto = new Date();
			this.estaAbierto = true;
			this.estaCerrado = false;
		}
	}
	
	public boolean estaCerrado() {
		return this.estaCerrado;
	}
	
	public boolean estaAbierto() {
		
		if (this.estaCerrado || !this.estaAbierto)
			return false;
		
		Date fechaActual = new Date();
		
		if(fechaActual.getTime() > this.fechaAbierto.getTime() + timeout) {
			this.estaAbierto = false;
		}
		
		return this.estaAbierto;
	}
}
