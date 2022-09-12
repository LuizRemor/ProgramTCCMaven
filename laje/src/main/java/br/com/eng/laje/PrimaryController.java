package br.com.eng.laje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.eng.entities.Coeficientes;
import br.com.eng.entities.Laje;
import br.com.eng.entities.Materiais;
import br.com.eng.entities.Parede;
import br.com.eng.util.Services;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class PrimaryController implements Initializable {

	private Laje laje;

	private Parede parede;

	private Materiais materiais;
	
	private Coeficientes coeficientes;
	
	List<Coeficientes> coeficientesList = new ArrayList<>();

	private Services services = new Services();

	@FXML
	private TextField ladoX = new TextField();

	@FXML
	private TextField ladoY = new TextField();

	@FXML
	private TextField espessuraLaje = new TextField();

	@FXML
	private TextField espessuraParede = new TextField();

	@FXML
	private TextField alturaParede = new TextField();

	@FXML
	private TextField cargaAcidental = new TextField();

	@FXML
	private RadioButton psi0_3;

	@FXML
	private RadioButton psi0_4;

	@FXML
	private RadioButton psi0_5;

	@FXML
	private RadioButton psi0_6;

	@FXML
	private RadioButton paredeSim;

	@FXML
	private RadioButton paredeNao;

	@FXML
	private RadioButton tijoloFuradoSim;

	@FXML
	private RadioButton tijoloFuradoNao;

	@FXML
	private RadioButton agregadoBasaltoDiabasio;

	@FXML
	private RadioButton agregadoGranitoGnaisse;

	@FXML
	private RadioButton agregadoCalcario;

	@FXML
	private RadioButton agregadoArenito;

	@FXML
	private CheckBox checkYCima = new CheckBox();

	@FXML
	private CheckBox checkYBaixo = new CheckBox();

	@FXML
	private CheckBox checkXEsquerda = new CheckBox();

	@FXML
	private CheckBox checkXDireita = new CheckBox();

	public void btCalcular() {

		this.ladoX = new TextField("3");
		this.ladoY = new TextField("8");
		this.espessuraLaje = new TextField("10");
		this.espessuraParede = new TextField("14");
		this.alturaParede = new TextField("2.9");
		this.cargaAcidental = new TextField("1.5");
		this.psi0_4.setSelected(true);
		this.agregadoGranitoGnaisse.setSelected(true);

		laje = new Laje(this.ladoX, this.ladoY, this.espessuraLaje);

		parede = new Parede(this.alturaParede, this.espessuraParede);

		materiais = new Materiais();

		laje.setCargaAcidental(services.conversor(this.cargaAcidental));

		leCoeficientes();
		verificaDirecoes();
		defineCaso();
		areaDeInfluenciaDaParedePositiva();
		cargaPermanentePositiva();
		cargaTotalPositiva();
		definePsi();
		cargaDeServicoPositiva();
		defineAgregado();
		calculaEci();
		calculaAlphaI();
		calculaEcs();
		calculaFctm();
		calculaFctkInf();
		calculaFctd();
		calculaMomentoDeFissuracao();
		defineECalculaEquacaoMomentoDeServico();
		verificaMomentoDeFissuracao();
		inercia();
		defineCoeficienteKParaUmaDirecao();
		calculaFlechaDeCurtaDuracao();
		calculaFlechaDeLongaDuracao();
		calculaFlechaAdmissivel();
		comparaFlecha();
		areaDeInfluenciaDaParedeNegativa();
		cargaPermanenteNegativa();
		cargaTotalNegativa();
		defineCoeficientes();
		calculaMomentoDeProjeto();
		calculaX();

	}
	
	public void leCoeficientes() {
		
		coeficientesList = new ArrayList<>();
		
		File file = new File("src/main/resources/CoeficientesCasos.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line= br.readLine();
			
			while(line!= null) {
				
				String[] vect = line.split(";");
				
				BigDecimal caso   = new BigDecimal(vect[0]);
				BigDecimal lambda = new BigDecimal(vect[1]);
				BigDecimal miX    = new BigDecimal(vect[2]);
				BigDecimal miY    = new BigDecimal(vect[3]);
				BigDecimal miX1   = new BigDecimal(vect[4]);
				BigDecimal miY1   = new BigDecimal(vect[5]);
				BigDecimal kx     = new BigDecimal(vect[6]);
				BigDecimal ky     = new BigDecimal(vect[7]);
				BigDecimal kx1    = new BigDecimal(vect[8]);
				BigDecimal ky1    = new BigDecimal(vect[9]);
				
				Coeficientes coeficientes = new Coeficientes(caso, lambda, miX, miY, miX1, miY1, kx, ky, kx1, ky1);
				
				coeficientesList.add(coeficientes);
				
				line = br.readLine();
			}
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void verificaDirecoes() {
		
		laje.setLambda(laje.getLadoY().divide(laje.getLadoX(), MathContext.DECIMAL128));
		
		if(laje.getLambda().doubleValue() > 2.0) {
			
			laje.setLambda(new BigDecimal(99999.0));
			
			System.out.println("LAJE ARMADA EM UMA DIRECAO");
			
		}
		else {
			System.out.println("LAJE ARMADA EM DUAS DIRECOES");
		}
		
	}

	public void areaDeInfluenciaDaParedePositiva() {

		parede.calculaAreaDeInfluenciaDaParedePositiva(laje);

		System.out.println("Area de influencia: " + parede.getAreaDeInfluenciaPositiva().toString());

	}

	public void areaDeInfluenciaDaParedeNegativa() {

		parede.calculaAreaDeInfluenciaDaParedeNegativa(laje);

		System.out.println("Area de influencia Negativa: " + parede.getAreaDeInfluenciaNegativa().toString());

	}

	public void cargaPermanentePositiva() {

		laje.setCargaPermanentePositiva(laje.calculaCargaPermanentePositiva(materiais, parede));

		System.out.printf("Carga permamente: %.2f%n", laje.getCargaPermanentePositiva());

	}

	public void cargaPermanenteNegativa() {

		laje.setCargaPermanenteNegativa(laje.calculaCargaPermanenteNegativa(materiais, parede));

		System.out.printf("Carga permamente Negativa: %.2f%n", laje.getCargaPermanenteNegativa());

	}

	public void cargaTotalPositiva() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		laje.setCargaTotalPositiva(laje.getCargaPermanentePositiva().add(cargaAcidental));

		System.out.printf("Carga Total: %.2f%n", laje.getCargaTotalPositiva());

	}

	public void cargaTotalNegativa() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		laje.setCargaTotalNegativa(laje.getCargaPermanenteNegativa().add(cargaAcidental));

		System.out.printf("Carga Total Negativa: %.2f%n", laje.getCargaTotalNegativa());

	}

	public void cargaDeServicoPositiva() {

		laje.setCargaDeServicoPositiva(laje.calculaCargaDeServico(materiais, parede));

		System.out.printf("Carga de Servico: %.2f%n", laje.getCargaDeServicoPositiva());

	}

	public void calculaEci() {

		Double fckConcreto = materiais.getFckConcreto().doubleValue();

		Double agregado = materiais.getAgregado().doubleValue();

		Double eci = agregado * 5600.0 * Math.sqrt(fckConcreto);

		materiais.setEci(services.doubleEmBigDecimal(eci));

		System.out.printf("ECI: %.2f%n", materiais.getEci());

	}

	public void calculaAlphaI() {

		materiais.setAlphaI(new BigDecimal(0.8)
				.add(new BigDecimal(0.2).multiply(materiais.getFckConcreto().divide(new BigDecimal(80)))));

		System.out.printf("Alpha I: %.2f%n", materiais.getAlphaI());

	}

	public void calculaEcs() {

		materiais.setEcs(materiais.getAlphaI().multiply(materiais.getEci()));

		materiais.setEcs(services.mpaParakNPorCmQuadrado(materiais.getEcs()));

		System.out.printf("ECS: %.2f%n", materiais.getEcs());

	}

	public void calculaFctm() {

		Double fckConcreto = materiais.getFckConcreto().doubleValue();

		Double fctm = 0.3 * Math.pow(fckConcreto, (2.0 / 3.0));

		materiais.setFctm(services.doubleEmBigDecimal(fctm));

		System.out.printf("Fctm: %.3f%n", materiais.getFctm());

	}

	public void calculaFctkInf() {

		materiais.setFctkInf(new BigDecimal(0.7).multiply(materiais.getFctm()));

		System.out.printf("FctkInf: %.3f%n", materiais.getFctkInf());

	}

	public void calculaFctd() {

		materiais.setFctd(materiais.getFctkInf().divide(new BigDecimal(1.4)));

		materiais.setFctd(services.mpaParakNPorCmQuadrado(materiais.getFctd()));

		System.out.printf("Fctd: %.3f%n", materiais.getFctd());

	}

	public void calculaMomentoDeFissuracao() {

		BigDecimal espessuraLaje = services.metrosEmCentimetros(laje.getEspessura());

		BigDecimal fctm = services.mpaParakNPorCmQuadrado(materiais.getFctm());

		espessuraLaje = espessuraLaje.pow(2);

		laje.setMomentoDeFissuracao(
				new BigDecimal(0.25).multiply(fctm).multiply(new BigDecimal(100.0)).multiply(espessuraLaje));

		System.out.printf("Momento de fissuracao: %.3f%n", laje.getMomentoDeFissuracao());

	}

	public void verificaMomentoDeFissuracao() {

		Double momentoDeServico = laje.getMomentoDeServico().doubleValue();

		Double momentoDeFissuracao = laje.getMomentoDeFissuracao().doubleValue();

		if (momentoDeServico < momentoDeFissuracao) {

			System.out.println("OK, Segue o baile!");

		} else {

			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println();
			System.out.println("Aumentar a espessura da laje");
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");

		}

	}

	public void inercia() {

		laje.calculaInercia();

		System.out.printf("Inercia: %.2f%n", laje.getInercia());

	}

	public void calculaFlechaDeCurtaDuracao() {

		BigDecimal cargaDeServico;
		BigDecimal numerador;
		BigDecimal denominador;

		cargaDeServico = laje.getCargaDeServicoPositiva().multiply(new BigDecimal(1.0).divide(new BigDecimal(10000.0)),
				MathContext.DECIMAL128);

		numerador = cargaDeServico.multiply(laje.getLadoX().pow(4), MathContext.DECIMAL128);

		denominador = materiais.getEcs().multiply(laje.getInercia(), MathContext.DECIMAL128);

		laje.setFlechaDeCurtaDuracao(laje.getCoeficienteK().multiply(numerador, MathContext.DECIMAL128)
				.divide(denominador, MathContext.DECIMAL128));

		System.out.printf("Flecha de curta duracao: %.4f%n", laje.getFlechaDeCurtaDuracao());

	}

	public void calculaFlechaDeLongaDuracao() {

		laje.setFlechaDeLongaDuracao(
				new BigDecimal(2.32).multiply(laje.getFlechaDeCurtaDuracao(), MathContext.DECIMAL128));

		System.out.printf("Flecha de longa duracao: %.4f%n", laje.getFlechaDeLongaDuracao());

	}

	public void calculaFlechaAdmissivel() {

		laje.setFlechaAdmissivel(laje.getLadoX().divide(new BigDecimal(250.0)));

		System.out.printf("Flecha Admissivel: %.4f%n", laje.getFlechaAdmissivel());

	}

	public void comparaFlecha() {

		Double flechaAdmissivel = laje.getFlechaAdmissivel().doubleValue();

		Double flechaDeLongaDuracao = laje.getFlechaDeLongaDuracao().doubleValue();

		if (flechaAdmissivel > flechaDeLongaDuracao) {

			System.out.println("OK, Segue o baile!");

		} else {

			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println();
			System.out.println("Aumentar a espessura da laje");
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");

		}

	}

	public void definePsi() {

		if (psi0_3.isSelected()) {
			laje.setPsi(new BigDecimal(0.3));
		}

		else if (psi0_4.isSelected()) {
			laje.setPsi(new BigDecimal(0.4));
		}

		else if (psi0_5.isSelected()) {
			laje.setPsi(new BigDecimal(0.5));
		}

		else {
			laje.setPsi(new BigDecimal(0.6));
		}

	}

	public void defineECalculaEquacaoMomentoDeServico() {

		if (!checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(laje.getCargaDeServicoPositiva().multiply(ladoX.pow(2)).divide(new BigDecimal(8.0))
					.multiply(new BigDecimal(100.0)));

			System.out.println("Equacao M. Serv. = (Pserv*Lx^2)/8");
			System.out.printf("Momento de Servico: %.2f%n", laje.getMomentoDeServico());

		}

		else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(new BigDecimal(9.0).multiply(laje.getCargaDeServicoPositiva())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			System.out.println("Equacao M. Serv. = (9*Pserv*Lx^2)/128");
			System.out.printf("Momento de Servico: %.2f%n", laje.getMomentoDeServico());

		}

		else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(new BigDecimal(9.0).multiply(laje.getCargaDeServicoPositiva())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			System.out.println("Equacao M. Serv. = (9*Pserv*Lx^2)/128");
			System.out.printf("Momento de Servico: %.2f%n", laje.getMomentoDeServico());

		}

		else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(laje.getCargaDeServicoPositiva().multiply(ladoX.pow(2))
					.divide(new BigDecimal(24.0)).multiply(new BigDecimal(100.0)));

			System.out.println("Equacao M. Serv. = (Pserv*Lx^2)/24");
			System.out.printf("Momento de Servico: %.2f%n", laje.getMomentoDeServico());

		}

	}

	public void defineCaso() {

		if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(1.0));

			System.out.println("CASO 1");

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(2.0));

			System.out.println("CASO 2");

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(2.0));

			System.out.println("CASO 2");

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(3.0));

			System.out.println("CASO 3");

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(3.0));

			System.out.println("CASO 3");

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(4.0));

			System.out.println("CASO 4");

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(4.0));

			System.out.println("CASO 4");

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(5.0));

			System.out.println("CASO 5");

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(6.0));

			System.out.println("CASO 6");

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(7.0));

			System.out.println("CASO 7");

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(7.0));

			System.out.println("CASO 7");

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(8.0));

			System.out.println("CASO 8");

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(8.0));

			System.out.println("CASO 8");

		} else {
			
			laje.setCaso(new BigDecimal(9.0));

			System.out.println("CASO 9");
		}

	}

	public void defineAgregado() {

		if (agregadoBasaltoDiabasio.isSelected()) {
			materiais.setAgregado(materiais.getAgregadoBasaltoDiabasio());
		}

		else if (agregadoGranitoGnaisse.isSelected()) {
			materiais.setAgregado(materiais.getAgregadoGranitoGnaisse());
		}

		else if (agregadoCalcario.isSelected()) {
			materiais.setAgregado(materiais.getAgregadoCalcario());
		}

		else {
			materiais.setAgregado(materiais.getAgregadoArenito());
		}

	}

	public void defineCoeficienteKParaUmaDirecao() {

		if (!checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(1.3));

			System.out.printf("Coeficiente K: %.2f%n", laje.getCoeficienteK());

		}

		else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(0.53));

			System.out.printf("Coeficiente K: %.2f%n", laje.getCoeficienteK());

		}

		else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(0.53));

			System.out.printf("Coeficiente K: %.2f%n", laje.getCoeficienteK());

		}

		else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(0.26));

			System.out.printf("Coeficiente K: %.2f%n", laje.getCoeficienteK());

		}

	}
	
	public void defineCoeficientes() {
		
		for(Coeficientes coef : coeficientesList) {
			if(coef.getCaso().doubleValue()   == laje.getCaso().doubleValue() && 
			   coef.getLambda().doubleValue() == laje.getLambda().doubleValue()) {
				
				this.coeficientes = coef;
				
				System.out.println("for each: " + coef.toString());
				System.out.println("objeto: " + this.coeficientes.toString());
				
			}
		}
		
	}
	
	public BigDecimal defineCargaDoMomentoDeProjetoXEsquerda(BigDecimal carga) {
		
		if(checkXEsquerda.selectedProperty().getValue()) {
			
			carga = laje.getCargaTotalNegativa();
			
		}
		else {
			carga = laje.getCargaTotalPositiva();
		}

		return carga;
		
	}
	
	public BigDecimal defineCargaDoMomentoDeProjetoXDireita(BigDecimal carga) {
		
		if(checkXDireita.selectedProperty().getValue()) {
			
			carga = laje.getCargaTotalNegativa();
			
		}
		else {
			carga = laje.getCargaTotalPositiva();
		}

		return carga;
		
	}
	
	public BigDecimal defineCargaDoMomentoDeProjetoYCima(BigDecimal carga) {
		
		if(checkYCima.selectedProperty().getValue()) {
			
			carga = laje.getCargaTotalNegativa();
			
		}
		else {
			carga = laje.getCargaTotalPositiva();
		}

		return carga;
		
	}
	
	public BigDecimal defineCargaDoMomentoDeProjetoYBaixo(BigDecimal carga) {
		
		if(checkYBaixo.selectedProperty().getValue()) {
			
			carga = laje.getCargaTotalNegativa();
			
		}
		else {
			carga = laje.getCargaTotalPositiva();
		}

		return carga;
		
	}
	
	public void calculaMomentoDeProjeto() {
		

		BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());
		BigDecimal coeficienteDeSeguranca = new BigDecimal(1.4);
		
		laje.setMomentoDeProjetoX(coeficientes.getMiX().
								  multiply(laje.getCargaTotalPositiva()).
								  multiply(ladoX.pow(2)).
								  multiply(coeficienteDeSeguranca));
		
		System.out.printf("Mx = %.2f%n", laje.getMomentoDeProjetoX());
		
		laje.setMomentoDeProjetoXLinha(coeficientes.getMiX1().
								  	   multiply(laje.getCargaTotalNegativa()).
								  	   multiply(ladoX.pow(2)).
									   multiply(coeficienteDeSeguranca));
		
		System.out.printf("Mx' = %.2f%n", laje.getMomentoDeProjetoXLinha());
		
		laje.setMomentoDeProjetoY(coeficientes.getMiY().
								  multiply(laje.getCargaTotalPositiva()).
								  multiply(ladoX.pow(2)).
								  multiply(coeficienteDeSeguranca));
		
		System.out.printf("My = %.2f%n", laje.getMomentoDeProjetoY());
		
		laje.setMomentoDeProjetoYLinha(coeficientes.getMiY1().
									   multiply(laje.getCargaTotalNegativa()).
									   multiply(ladoX.pow(2)).
									   multiply(coeficienteDeSeguranca));
		
		System.out.printf("My' = %.2f%n", laje.getMomentoDeProjetoYLinha());
		
	}
	
	public void calculaX() {
		
		laje.calculaD();
		materiais.calculaFcd();
		
		Double numerador;
		Double denominador;
		Double raiz;
		Double x;
		
		numerador = 2*laje.getMomentoDeProjetoX().doubleValue();
		denominador = 0.85*materiais.getFcd().doubleValue()*100*laje.getD().pow(2).doubleValue();
		
		raiz = Math.sqrt(1-(numerador/denominador));
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		laje.setX(services.doubleEmBigDecimal(x));
		System.out.printf("X = %.2f%n", laje.getX());
		
		numerador = 2*laje.getMomentoDeProjetoXLinha().doubleValue();
		raiz = Math.sqrt(1-(numerador/denominador));
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		laje.setxLinha(services.doubleEmBigDecimal(x));
		System.out.printf("X' = %.2f%n", laje.getxLinha());
		
		numerador = 2*laje.getMomentoDeProjetoY().doubleValue();
		raiz = Math.sqrt(1-(numerador/denominador));
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		laje.setY(services.doubleEmBigDecimal(x));
		System.out.printf("Y = %.2f%n", laje.getY());
		
		numerador = 2*laje.getMomentoDeProjetoYLinha().doubleValue();
		raiz = Math.sqrt(1-(numerador/denominador));
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		laje.setyLinha(services.doubleEmBigDecimal(x));
		System.out.printf("Y' = %.2f%n", laje.getyLinha());
		
	}

	public TextField getLadoX() {

		return ladoX;

	}

	public void setLadoX(TextField ladoX) {

		this.ladoX = ladoX;

	}

	public TextField getLadoY() {

		return ladoY;

	}

	public void setLadoY(TextField ladoY) {

		this.ladoY = ladoY;

	}

	public TextField getEspessuraLaje() {

		return espessuraLaje;

	}

	public void setEspessuraLaje(TextField espessuraLaje) {

		this.espessuraLaje = espessuraLaje;

	}

	public TextField getCargaAcidental() {

		return cargaAcidental;

	}

	public void setCargaAcidental(TextField cargaAcidental) {

		this.cargaAcidental = cargaAcidental;

	}

	public TextField getEspessuraParede() {
		return espessuraParede;
	}

	public void setEspessuraParede(TextField espessuraParede) {
		this.espessuraParede = espessuraParede;
	}

	public TextField getAlturaParede() {
		return alturaParede;
	}

	public void setAlturaParede(TextField alturaParede) {
		this.alturaParede = alturaParede;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}