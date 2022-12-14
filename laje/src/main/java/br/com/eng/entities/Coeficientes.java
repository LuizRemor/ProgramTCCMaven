package br.com.eng.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class Coeficientes implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal caso;
	private BigDecimal lambda;
	private BigDecimal mX;
	private BigDecimal kX;
	private BigDecimal miX;
	private BigDecimal miY;
	private BigDecimal miX1;
	private BigDecimal miY1;
	private BigDecimal kx;
	private BigDecimal ky;
	private BigDecimal kx1;
	private BigDecimal ky1;

	public Coeficientes() {

	}

	public Coeficientes(BigDecimal caso, BigDecimal lambda, BigDecimal miX, BigDecimal miY, BigDecimal miX1,
			BigDecimal miY1, BigDecimal kx, BigDecimal ky, BigDecimal kx1, BigDecimal ky1) {

		this.caso = caso;
		this.lambda = lambda;
		this.miX = miX;
		this.miY = miY;
		this.miX1 = miX1;
		this.miY1 = miY1;
		this.kx = kx;
		this.ky = ky;
		this.kx1 = kx1;
		this.ky1 = ky1;
	}
	
	

	public Coeficientes(BigDecimal caso, BigDecimal lambda, BigDecimal mX, BigDecimal kX) {

		this.caso = caso;
		this.lambda = lambda;
		this.mX = mX;
		this.kX = kX;
	}

	public BigDecimal getCaso() {
		return caso;
	}

	public void setCaso(BigDecimal caso) {
		this.caso = caso;
	}

	public BigDecimal getLambda() {
		return lambda;
	}

	public void setLambda(BigDecimal lambda) {
		this.lambda = lambda;
	}

	public BigDecimal getMiX() {
		return miX;
	}

	public void setMiX(BigDecimal miX) {
		this.miX = miX;
	}

	public BigDecimal getMiY() {
		return miY;
	}

	public void setMiY(BigDecimal miY) {
		this.miY = miY;
	}

	public BigDecimal getMiX1() {
		return miX1;
	}

	public void setMiX1(BigDecimal miX1) {
		this.miX1 = miX1;
	}

	public BigDecimal getMiY1() {
		return miY1;
	}

	public void setMiY1(BigDecimal miY1) {
		this.miY1 = miY1;
	}

	public BigDecimal getKx() {
		return kx;
	}

	public void setKx(BigDecimal kx) {
		this.kx = kx;
	}

	public BigDecimal getKy() {
		return ky;
	}

	public void setKy(BigDecimal ky) {
		this.ky = ky;
	}

	public BigDecimal getKx1() {
		return kx1;
	}

	public void setKx1(BigDecimal kx1) {
		this.kx1 = kx1;
	}

	public BigDecimal getKy1() {
		return ky1;
	}

	public void setKy1(BigDecimal ky1) {
		this.ky1 = ky1;
	}

	public BigDecimal getmX() {
		return mX;
	}

	public BigDecimal getkX() {
		return kX;
	}

	public void setmX(BigDecimal mX) {
		this.mX = mX;
	}

	public void setkX(BigDecimal kX) {
		this.kX = kX;
	}

	@Override
	public String toString() {
		return "Coeficientes [caso=" + caso + ", lambda=" + lambda + ", mX=" + mX + ", kX=" + kX + ", miX=" + miX
				+ ", miY=" + miY + ", miX1=" + miX1 + ", miY1=" + miY1 + ", kx=" + kx + ", ky=" + ky + ", kx1=" + kx1
				+ ", ky1=" + ky1 + "]";
	}

}