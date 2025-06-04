package Logicaa;

public class Carta {
	private int valor;     
	private int estado;    // 0: oculta, 1: visible temporalmente, 2: descubierta

	public Carta(int valor) {
		this.valor = valor;
		this.estado = 0; 
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public boolean estaDescubierta() {
		return estado == 2;
	}

	public boolean estaOculta() {
		return estado == 0;
	}

	public boolean estaVisible() {
		return estado == 1;
	}
}