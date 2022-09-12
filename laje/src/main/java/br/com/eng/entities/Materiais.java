package br.com.eng.entities;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.eng.util.Services;

public class Materiais {

	BigDecimal gamaConcreto = new BigDecimal(25.0);

	BigDecimal gamaContraPiso = new BigDecimal(21.0);

	BigDecimal gamaPiso = new BigDecimal(18.0);

	BigDecimal pesoForro = new BigDecimal(0.5);

	BigDecimal gamaTijolo = new BigDecimal(13.0);
	
	BigDecimal agregadoBasaltoDiabasio = new BigDecimal(1.2);
	
	BigDecimal agregadoGranitoGnaisse = new BigDecimal(1.0);
	
	BigDecimal agregadoCalcario = new BigDecimal(0.9);
	
	BigDecimal agregadoArenito = new BigDecimal(0.7);
	
	BigDecimal fckConcreto = new BigDecimal(25.0);
	
	BigDecimal fykAco = new BigDecimal(500);
	
	BigDecimal fydAco = new BigDecimal(43.48);
	
	BigDecimal agregado;
	
	BigDecimal eci;
	
	BigDecimal alphaI;
	
	BigDecimal ecs;
	
	BigDecimal fctm;
	
	BigDecimal fctkInf;
	
	BigDecimal fctd;
	
	BigDecimal fcd;
	
	Services services = new Services();
	
	public Materiais() {

	}
	
	public BigDecimal calculaFcd() {
		
		this.fcd = this.fckConcreto.divide(new BigDecimal(1.4), MathContext.DECIMAL128).divide(new BigDecimal(10.0), MathContext.DECIMAL128);
		
		return fcd;
		
	}
	
	public BigDecimal getAgregadoBasaltoDiabasio() {
		return agregadoBasaltoDiabasio;
	}



	public void setAgregadoBasaltoDiabasio(BigDecimal agregadoBasaltoDiabasio) {
		this.agregadoBasaltoDiabasio = agregadoBasaltoDiabasio;
	}



	public BigDecimal getAgregadoGranitoGnaisse() {
		return agregadoGranitoGnaisse;
	}



	public void setAgregadoGranitoGnaisse(BigDecimal agregadoGranitoGnaisse) {
		this.agregadoGranitoGnaisse = agregadoGranitoGnaisse;
	}



	public BigDecimal getAgregadoCalcario() {
		return agregadoCalcario;
	}



	public void setAgregadoCalcario(BigDecimal agregadoCalcario) {
		this.agregadoCalcario = agregadoCalcario;
	}



	public BigDecimal getAgregadoArenito() {
		return agregadoArenito;
	}



	public void setAgregadoArenito(BigDecimal agregadoArenito) {
		this.agregadoArenito = agregadoArenito;
	}



	public BigDecimal getGamaConcreto() {

		return gamaConcreto;

	}

	public void setGamaConcreto(BigDecimal gamaConcreto) {

		this.gamaConcreto = gamaConcreto;

	}

	public BigDecimal getGamaContraPiso() {

		return gamaContraPiso;

	}

	public void setGamaContraPiso(BigDecimal gamaContraPiso) {

		this.gamaContraPiso = gamaContraPiso;

	}

	public BigDecimal getGamaPiso() {

		return gamaPiso;

	}

	public void setGamaPiso(BigDecimal gamaPiso) {

		this.gamaPiso = gamaPiso;

	}

	public BigDecimal getPesoForro() {
		return pesoForro;
	}

	public void setPesoForro(BigDecimal pesoForro) {
		this.pesoForro = pesoForro;
	}

	public BigDecimal getGamaTijolo() {

		return gamaTijolo;

	}

	public void setGamaTijolo(BigDecimal gamaTijolo) {

		this.gamaTijolo = gamaTijolo;

	}

	public BigDecimal getFckConcreto() {
		return fckConcreto;
	}

	public void setFckConcreto(BigDecimal fckConcreto) {
		this.fckConcreto = fckConcreto;
	}

	public BigDecimal getEci() {
		return eci;
	}

	public void setEci(BigDecimal eci) {
		this.eci = eci;
	}

	public BigDecimal getEcs() {
		return ecs;
	}

	public void setEcs(BigDecimal ecs) {
		this.ecs = ecs;
	}

	public BigDecimal getAlphaI() {
		return alphaI;
	}

	public void setAlphaI(BigDecimal alphaI) {
		this.alphaI = alphaI;
	}

	public BigDecimal getFctm() {
		return fctm;
	}

	public void setFctm(BigDecimal fctm) {
		this.fctm = fctm;
	}

	public BigDecimal getFctkInf() {
		return fctkInf;
	}

	public void setFctkInf(BigDecimal fctkInf) {
		this.fctkInf = fctkInf;
	}

	public BigDecimal getFctd() {
		return fctd;
	}

	public void setFctd(BigDecimal fctd) {
		this.fctd = fctd;
	}

	public BigDecimal getAgregado() {
		return agregado;
	}

	public void setAgregado(BigDecimal agregado) {
		this.agregado = agregado;
	}

	public BigDecimal getFcd() {
		return fcd;
	}

	public void setFcd(BigDecimal fcd) {
		this.fcd = fcd;
	}

	public BigDecimal getFykAco() {
		return fykAco;
	}

	public BigDecimal getFydAco() {
		return fydAco;
	}

	public void setFykAco(BigDecimal fykAco) {
		this.fykAco = fykAco;
	}

	public void setFydAco(BigDecimal fydAco) {
		this.fydAco = fydAco;
	}

}