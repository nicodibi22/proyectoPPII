package proyecto.servicios.impl;

import java.util.Date;

public class CircuitBreakerManejoEstado {

	private boolean estaCerrado;
	
	private boolean estaAbierto;
	
	private boolean estaMedioAbierto;
	
	private int timeout;
	
	private Date fechaAbierto;
	
	private int intentosMaximos;
	
	private int intentosFallidos;
	
	public CircuitBreakerManejoEstado(int timeout, int intentosMax) {
		this.timeout = timeout;
		this.intentosMaximos = intentosMax;
		this.estaAbierto = false;
		this.estaMedioAbierto = false;
		this.estaCerrado = true;
	}
	
	public void reset() {
		this.estaAbierto = false;
		this.estaMedioAbierto = false;
		this.estaCerrado = true;
		this.intentosFallidos = 0;
		this.fechaAbierto = null;
	}
	
	public void aumentarIntentoFallido() {
		this.intentosFallidos++;
		
		if (this.intentosFallidos >= this.intentosMaximos) {
			
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
			this.estaMedioAbierto = true;
			this.estaAbierto = false;
		}
		
		return this.estaAbierto;
	}
	
	public boolean estaMedioAbierto() {
		return this.estaMedioAbierto;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public void setIntentos(int intentos) {
		this.intentosMaximos = intentos;
	}
	
	public int getTimeout() {
		return this.timeout;
	}
	
	public int getIntentos() {
		return this.intentosMaximos;
	}
}
