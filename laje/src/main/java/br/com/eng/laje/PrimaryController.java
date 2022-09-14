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
import br.com.eng.entities.EspacamentoAco;
import br.com.eng.entities.Laje;
import br.com.eng.entities.Materiais;
import br.com.eng.entities.Parede;
import br.com.eng.util.Services;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

@SuppressWarnings("deprecation")
public class PrimaryController implements Initializable {

	private Laje laje;

	private Parede parede;

	private Materiais materiais;
	
	private Coeficientes coeficientes;
	
	List<Coeficientes> coeficientesList = new ArrayList<>();
	
	List<EspacamentoAco> espacamentoAcoList = new ArrayList<>();

	private Services services = new Services();
	
	@FXML
	private Text engasteCheckXEsquerda = new Text();
	
	@FXML
	private Text engasteCheckYCima = new Text();
	
	@FXML
	private Text engasteCheckXDireita = new Text();
	
	@FXML
	private Text engastecheckYBaixo = new Text();

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
	private RadioButton psi0_3 = new RadioButton();

	@FXML
	private RadioButton psi0_4 = new RadioButton();

	@FXML
	private RadioButton psi0_5 = new RadioButton();

	@FXML
	private RadioButton psi0_6 = new RadioButton();

	@FXML
	private RadioButton paredeSim = new RadioButton();

	@FXML
	private RadioButton paredeNao = new RadioButton();

	@FXML
	private RadioButton tijoloFuradoSim = new RadioButton();

	@FXML
	private RadioButton tijoloFuradoNao = new RadioButton();

	@FXML
	private RadioButton agregadoBasaltoDiabasio = new RadioButton();

	@FXML
	private RadioButton agregadoGranitoGnaisse = new RadioButton();

	@FXML
	private RadioButton agregadoCalcario = new RadioButton();

	@FXML
	private RadioButton agregadoArenito = new RadioButton();

	@FXML
	private CheckBox checkYCima = new CheckBox();

	@FXML
	private CheckBox checkYBaixo = new CheckBox();

	@FXML
	private CheckBox checkXEsquerda = new CheckBox();

	@FXML
	private CheckBox checkXDireita = new CheckBox();
	
	@FXML
	private TextArea imprimeResultados = new TextArea();
	
	public void montaUmaDirecaoComParede() {
		
		this.ladoX.setText("3");
		this.ladoY.setText("8");
		this.espessuraLaje.setText("10");
		this.espessuraParede.setText("14");
		this.alturaParede.setText("2.9");
		this.cargaAcidental.setText("1.5");
		this.paredeSim.setSelected(true);
		this.tijoloFuradoSim.setSelected(true);
		this.psi0_4.setSelected(true);
		this.agregadoGranitoGnaisse.setSelected(true);
		this.checkYCima.setSelected(true);
		this.engasteCheckYCima.setVisible(true);
		this.checkYBaixo.setSelected(true);
		this.engastecheckYBaixo.setVisible(true);

		laje = new Laje(this.ladoX, this.ladoY, this.espessuraLaje);

		parede = new Parede(this.alturaParede, this.espessuraParede);

		materiais = new Materiais();

		laje.setCargaAcidental(services.conversor(this.cargaAcidental));
		
	}
	
	public void limpaResultados() {
		
		this.imprimeResultados.clear();
		
	}

	public void btCalcular() {
		
		populaCoeficientes();
		populaEspacamentoAco();
		verificaDirecoes();
		defineCaso();
		calculaAreaDeAcoMinima();
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
		calculaAco();
		calculaArmaduraDeDistribuicao();
		defineAreaDeAco();

	}
	
	public void mostraEngastes() {
		
		if (checkXEsquerda.selectedProperty().getValue()) {
			engasteCheckXEsquerda.setVisible(true);
		} 
		
		else {
			engasteCheckXEsquerda.setVisible(false);
		}

		if (checkYCima.selectedProperty().getValue()) {
			engasteCheckYCima.setVisible(true);
		} 
		
		else {
			engasteCheckYCima.setVisible(false);
		}

		if (checkXDireita.selectedProperty().getValue()) {
			engasteCheckXDireita.setVisible(true);
		} 
		
		else {
			engasteCheckXDireita.setVisible(false);
		}

		if (checkYBaixo.selectedProperty().getValue()) {
			engastecheckYBaixo.setVisible(true);
		} 
		
		else {
			engastecheckYBaixo.setVisible(false);
		}
		
	}
	
	public void populaCoeficientes() {
		
		coeficientesList = new ArrayList<>();
		
		File file = new File("src/main/resources/CoeficientesCasos.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();
			
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
	
	public void populaEspacamentoAco() {
		
		espacamentoAcoList = new ArrayList<>();
		
		File file = new File("src/main/resources/EspacamentoAco.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();
			
			while(line!= null) {
				
				String[] vect = line.split(";");
				
				BigDecimal bitola = new BigDecimal(vect[0]);
				BigDecimal espacamento = new BigDecimal(vect[1]);
				BigDecimal areaDeAco = new BigDecimal(vect[2]);
				
				EspacamentoAco aco = new EspacamentoAco(bitola, espacamento, areaDeAco);
				
				espacamentoAcoList.add(aco);
				
				line = br.readLine();
			}
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void calculaAreaDeAcoMinima() {
		laje.calculaAcoMinimo();
	}
	
	public void verificaDirecoes() {
		
		laje.setLambda(laje.getLadoY().divide(laje.getLadoX(), MathContext.DECIMAL128));
		
		if(laje.getLambda().doubleValue() > 2.0) {
			
			laje.setLambda(new BigDecimal(99999.0));
			
			String string = "LAJE ARMADA EM UMA DIRECAO \n";
			
			this.imprimeResultados.appendText(string);
			
		}
		else {
			
			String string = "LAJE ARMADA EM DUAS DIRECOES \n";
			
			this.imprimeResultados.appendText(string);
		}
		
	}

	public void areaDeInfluenciaDaParedePositiva() {

		parede.calculaAreaDeInfluenciaDaParedePositiva(laje);

		services.imprimeResultados("Area de influencia: " 
								  + parede.getAreaDeInfluenciaPositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								  + "\n", this.imprimeResultados);

	}

	
	public void areaDeInfluenciaDaParedeNegativa() {

		parede.calculaAreaDeInfluenciaDaParedeNegativa(laje);

		services.imprimeResultados("Area de influencia Negativa: " 
								   + parede.getAreaDeInfluenciaNegativa().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void cargaPermanentePositiva() {

		laje.setCargaPermanentePositiva(laje.calculaCargaPermanentePositiva(materiais, parede));

		services.imprimeResultados("Carga permamente: " 
								   + laje.getCargaPermanentePositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);
		
	}

	public void cargaPermanenteNegativa() {

		laje.setCargaPermanenteNegativa(laje.calculaCargaPermanenteNegativa(materiais, parede));

		services.imprimeResultados("Carga permamente Negativa: " + 
									laje.getCargaPermanenteNegativa().setScale(2, BigDecimal.ROUND_HALF_EVEN) 
									+ "\n", this.imprimeResultados);
	}

	public void cargaTotalPositiva() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		laje.setCargaTotalPositiva(laje.getCargaPermanentePositiva().add(cargaAcidental));

		services.imprimeResultados("Carga Total: " 
								   + laje.getCargaTotalPositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void cargaTotalNegativa() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		laje.setCargaTotalNegativa(laje.getCargaPermanenteNegativa().add(cargaAcidental));

		services.imprimeResultados("Carga Total Negativa: " 
								   + laje.getCargaTotalNegativa().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void cargaDeServicoPositiva() {

		laje.setCargaDeServicoPositiva(laje.calculaCargaDeServico(materiais, parede));

		services.imprimeResultados("Carga de Servico: " 
					     		   + laje.getCargaDeServicoPositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN)
					     		   + "\n", this.imprimeResultados);
		
	}

	public void calculaEci() {

		Double fckConcreto = materiais.getFckConcreto().doubleValue();

		Double agregado = materiais.getAgregado().doubleValue();

		Double eci = agregado * 5600.0 * Math.sqrt(fckConcreto);

		materiais.setEci(services.doubleEmBigDecimal(eci));

		services.imprimeResultados("ECI: " 
								   + materiais.getEci().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void calculaAlphaI() {

		materiais.setAlphaI(new BigDecimal(0.8)
				.add(new BigDecimal(0.2).multiply(materiais.getFckConcreto().divide(new BigDecimal(80)))));

		services.imprimeResultados("Alpha I: " 
								   + materiais.getAlphaI().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);
	}

	public void calculaEcs() {

		materiais.setEcs(materiais.getAlphaI().multiply(materiais.getEci()));

		materiais.setEcs(services.mpaParakNPorCmQuadrado(materiais.getEcs()));

		services.imprimeResultados("ECS: " 
								   + materiais.getEcs().setScale(2, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void calculaFctm() {

		Double fckConcreto = materiais.getFckConcreto().doubleValue();

		Double fctm = 0.3 * Math.pow(fckConcreto, (2.0 / 3.0));

		materiais.setFctm(services.doubleEmBigDecimal(fctm));

		services.imprimeResultados("Fctm: " 
								   + materiais.getFctm().setScale(3, BigDecimal.ROUND_HALF_EVEN) 
								   + "\n", this.imprimeResultados);

	}

	public void calculaFctkInf() {

		materiais.setFctkInf(new BigDecimal(0.7).multiply(materiais.getFctm()));

		services.imprimeResultados("FctkInf: " 
								   + materiais.getFctkInf().setScale(3, BigDecimal.ROUND_HALF_EVEN)
							       + "\n", this.imprimeResultados);

	}

	public void calculaFctd() {

		materiais.setFctd(materiais.getFctkInf().divide(new BigDecimal(1.4)));

		materiais.setFctd(services.mpaParakNPorCmQuadrado(materiais.getFctd()));

		services.imprimeResultados("Fctd: " 
								   + materiais.getFctd().setScale(3, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void calculaMomentoDeFissuracao() {

		BigDecimal espessuraLaje = services.metrosEmCentimetros(laje.getEspessura());

		BigDecimal fctm = services.mpaParakNPorCmQuadrado(materiais.getFctm());

		espessuraLaje = espessuraLaje.pow(2);

		laje.setMomentoDeFissuracao(
				new BigDecimal(0.25).multiply(fctm).multiply(new BigDecimal(100.0)).multiply(espessuraLaje));

		services.imprimeResultados("Momento de fissuracao: " 
								   + laje.getMomentoDeFissuracao().setScale(3, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void verificaMomentoDeFissuracao() {

		Double momentoDeServico = laje.getMomentoDeServico().doubleValue();

		Double momentoDeFissuracao = laje.getMomentoDeFissuracao().doubleValue();

		if (momentoDeServico < momentoDeFissuracao) {

			services.imprimeResultados("OK, Segue o baile!", this.imprimeResultados);

		} else {

			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("" + "\n", this.imprimeResultados);
			services.imprimeResultados("Aumentar a espessura da laje"+ "\n", this.imprimeResultados);
			services.imprimeResultados("" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);

		}

	}

	public void inercia() {

		laje.calculaInercia();

		services.imprimeResultados("Inercia: " 
								   + laje.getInercia().setScale(3, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

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

		services.imprimeResultados("Flecha de curta duracao: " 
								   + laje.getFlechaDeCurtaDuracao().setScale(4, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void calculaFlechaDeLongaDuracao() {

		laje.setFlechaDeLongaDuracao(
				new BigDecimal(2.32).multiply(laje.getFlechaDeCurtaDuracao(), MathContext.DECIMAL128));

		services.imprimeResultados("Flecha de longa duracao: " 
								   + laje.getFlechaDeLongaDuracao().setScale(4, BigDecimal.ROUND_HALF_EVEN)
								   + "\n", this.imprimeResultados);

	}

	public void calculaFlechaAdmissivel() {

		laje.setFlechaAdmissivel(laje.getLadoX().divide(new BigDecimal(250.0)));

		services.imprimeResultados("Flecha Admissivel: " 
								+ laje.getFlechaAdmissivel().setScale(4, BigDecimal.ROUND_HALF_EVEN)
								+ "\n", this.imprimeResultados);

	}

	public void comparaFlecha() {

		Double flechaAdmissivel = laje.getFlechaAdmissivel().doubleValue();

		Double flechaDeLongaDuracao = laje.getFlechaDeLongaDuracao().doubleValue();

		if (flechaAdmissivel > flechaDeLongaDuracao) {

			services.imprimeResultados("OK, Segue o baile! \n", this.imprimeResultados);

		} else {

			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("" + "\n", this.imprimeResultados);
			services.imprimeResultados("Aumentar a espessura da laje"+ "\n", this.imprimeResultados);
			services.imprimeResultados("" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);
			services.imprimeResultados("-------------------------------------------------" + "\n", this.imprimeResultados);

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

			services.imprimeResultados("Equacao M. Serv. = (Pserv*Lx^2)/8 \n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: " 
										+ laje.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(new BigDecimal(9.0).multiply(laje.getCargaDeServicoPositiva())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (9*Pserv*Lx^2)/128\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: " 
										+ laje.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

		else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(new BigDecimal(9.0).multiply(laje.getCargaDeServicoPositiva())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (9*Pserv*Lx^2)/128\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
										+ laje.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(laje.getLadoX());

			laje.setMomentoDeServico(laje.getCargaDeServicoPositiva().multiply(ladoX.pow(2))
					.divide(new BigDecimal(24.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (Pserv*Lx^2)/24\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
										+ laje.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

	}

	public void defineCaso() {

		if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(1.0));

			services.imprimeResultados("CASO 1\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(2.0));

			services.imprimeResultados("CASO 2\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(2.0));

			services.imprimeResultados("CASO 2\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(3.0));

			services.imprimeResultados("CASO 3\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(3.0));

			services.imprimeResultados("CASO 3\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(4.0));

			services.imprimeResultados("CASO 4\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(4.0));

			services.imprimeResultados("CASO 4\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(5.0));

			services.imprimeResultados("CASO 5\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(6.0));

			services.imprimeResultados("CASO 6\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(7.0));

			services.imprimeResultados("CASO 7\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(7.0));

			services.imprimeResultados("CASO 7\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(8.0));

			services.imprimeResultados("CASO 8\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCaso(new BigDecimal(8.0));

			services.imprimeResultados("CASO 8\n", this.imprimeResultados);

		} else {
			
			laje.setCaso(new BigDecimal(9.0));

			services.imprimeResultados("CASO 9\n", this.imprimeResultados);
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

			services.imprimeResultados("Coeficiente K: "
										+ laje.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(0.53));

			services.imprimeResultados("Coeficiente K: "
										+ laje.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

		else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(0.53));

			services.imprimeResultados("Coeficiente K: "
										+ laje.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			laje.setCoeficienteK(new BigDecimal(0.26));

			services.imprimeResultados("Coeficiente K: "
										+ laje.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN)
										+ "\n", this.imprimeResultados);

		}

	}
	
	public void defineCoeficientes() {
		
		for(Coeficientes coef : coeficientesList) {
			if(coef.getCaso().doubleValue()   == laje.getCaso().doubleValue() && 
			   coef.getLambda().doubleValue() == laje.getLambda().doubleValue()) {
				
				this.coeficientes = coef;
				
				//services.imprimeResultados("for each: " + coef.toString(), this.imprimeResultados);
				//services.imprimeResultados("objeto: " + this.coeficientes.toString(), this.imprimeResultados);
				
			}
		}
		
	}
	
	public void defineEspacamentosAco() {
		
		
		
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
		
		services.imprimeResultados("Mx = "
									+ laje.getMomentoDeProjetoX().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		laje.setMomentoDeProjetoXLinha(coeficientes.getMiX1().
								  	   multiply(laje.getCargaTotalNegativa()).
								  	   multiply(ladoX.pow(2)).
									   multiply(coeficienteDeSeguranca));
		
		services.imprimeResultados("Mx' = "
									+ laje.getMomentoDeProjetoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		laje.setMomentoDeProjetoY(coeficientes.getMiY().
								  multiply(laje.getCargaTotalPositiva()).
								  multiply(ladoX.pow(2)).
								  multiply(coeficienteDeSeguranca));
		
		services.imprimeResultados("My = "
									+ laje.getMomentoDeProjetoY().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		laje.setMomentoDeProjetoYLinha(coeficientes.getMiY1().
									   multiply(laje.getCargaTotalNegativa()).
									   multiply(ladoX.pow(2)).
									   multiply(coeficienteDeSeguranca));
		
		services.imprimeResultados("My' = "
									+ laje.getMomentoDeProjetoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
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
		
		services.imprimeResultados("X = "
									+ laje.getX().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		
		numerador = 2*laje.getMomentoDeProjetoXLinha().doubleValue();
		
		raiz = Math.sqrt(1-(numerador/denominador));
		
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		
		laje.setxLinha(services.doubleEmBigDecimal(x));
		
		services.imprimeResultados("X' = "
									+ laje.getxLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		
		numerador = 2*laje.getMomentoDeProjetoY().doubleValue();
		
		raiz = Math.sqrt(1-(numerador/denominador));
		
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		
		laje.setY(services.doubleEmBigDecimal(x));
		
		services.imprimeResultados("Y = "
									+ laje.getY().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		
		numerador = 2*laje.getMomentoDeProjetoYLinha().doubleValue();
		
		raiz = Math.sqrt(1-(numerador/denominador));
		
		x = laje.getD().doubleValue()/0.8*(1-raiz);
		
		laje.setyLinha(services.doubleEmBigDecimal(x));
		
		services.imprimeResultados("Y' = "
									+ laje.getyLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
	}
	
	public void calculaAco() {
		
		laje.setAreaDeAcoX(new BigDecimal(0.85).
					       multiply(new BigDecimal(0.8)).
					       multiply(materiais.getFcd(), MathContext.DECIMAL128).
					       multiply(new BigDecimal(100.0)).
					       multiply(laje.getX(), MathContext.DECIMAL128).
					       divide(materiais.getFydAco(), MathContext.DECIMAL128));
		
		services.imprimeResultados("Area de aco X = "
									+ laje.getAreaDeAcoX().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		laje.setAreaDeAcoXLinha(new BigDecimal(0.85).
			       multiply(new BigDecimal(0.8)).
			       multiply(materiais.getFcd(), MathContext.DECIMAL128).
			       multiply(new BigDecimal(100.0)).
			       multiply(laje.getxLinha(), MathContext.DECIMAL128).
			       divide(materiais.getFydAco(), MathContext.DECIMAL128));
		
		services.imprimeResultados("Area de aco X' = "
									+ laje.getAreaDeAcoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		laje.setAreaDeAcoY(new BigDecimal(0.85).
			       multiply(new BigDecimal(0.8)).
			       multiply(materiais.getFcd(), MathContext.DECIMAL128).
			       multiply(new BigDecimal(100.0)).
			       multiply(laje.getY(), MathContext.DECIMAL128).
			       divide(materiais.getFydAco(), MathContext.DECIMAL128));
		
		services.imprimeResultados("Area de aco Y = "
									+ laje.getAreaDeAcoY().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
		laje.setAreaDeAcoYLinha(new BigDecimal(0.85).
			       multiply(new BigDecimal(0.8)).
			       multiply(materiais.getFcd(), MathContext.DECIMAL128).
			       multiply(new BigDecimal(100.0)).
			       multiply(laje.getyLinha(), MathContext.DECIMAL128).
			       divide(materiais.getFydAco(), MathContext.DECIMAL128));
		
		services.imprimeResultados("Area de aco Y' = "
									+ laje.getAreaDeAcoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
	}
	
	public void defineAreaDeAco() {
		
		if(laje.getAreaDeAcoX().doubleValue() < laje.getAreaDeAcoMinima().doubleValue()) {
			
			laje.setAreaDeAcoX(laje.getAreaDeAcoMinima());
			
		}
		else if(laje.getAreaDeAcoXLinha().doubleValue() < laje.getAreaDeAcoMinima().doubleValue()) {
			
			laje.setAreaDeAcoXLinha(laje.getAreaDeAcoMinima());
			
		}
		else if(laje.getAreaDeAcoYLinha().doubleValue() < laje.getAreaDeAcoMinima().doubleValue()) {
			
			laje.setAreaDeAcoYLinha(laje.getAreaDeAcoMinima());
			
		}
		
		services.imprimeResultados("Area de aco final X = "
									+ laje.getAreaDeAcoX().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		services.imprimeResultados("Area de aco final X' = "
									+ laje.getAreaDeAcoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		services.imprimeResultados("Area de aco final Y (distribuicao) = "
									+ laje.getAreaDeAcoY().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		services.imprimeResultados("Area de aco final Y' = "
									+ laje.getAreaDeAcoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN)
									+ "\n", this.imprimeResultados);
		
	}
	
	public void calculaArmaduraDeDistribuicao() {
		
		BigDecimal calculoAreaDeAcoPrincipal;
		BigDecimal calculoAreaDeAcoMinima;
		BigDecimal definicaoNBR = new BigDecimal(0.9);
		
		calculoAreaDeAcoPrincipal = laje.getAreaDeAcoX().divide(new BigDecimal(5.0));
		calculoAreaDeAcoMinima    = laje.getAreaDeAcoMinima().divide(new BigDecimal(2.0));
		
		if(calculoAreaDeAcoPrincipal.doubleValue() > calculoAreaDeAcoMinima.doubleValue() &&
		   calculoAreaDeAcoPrincipal.doubleValue() > definicaoNBR.doubleValue()) {
			
			laje.setAreaDeAcoY(calculoAreaDeAcoPrincipal);
			
		}
		else if(calculoAreaDeAcoMinima.doubleValue() > calculoAreaDeAcoPrincipal.doubleValue() && 
				calculoAreaDeAcoMinima.doubleValue() > definicaoNBR.doubleValue()) {
			
			laje.setAreaDeAcoY(calculoAreaDeAcoMinima);
			
		}
		else {
			laje.setAreaDeAcoY(definicaoNBR);
		}
		
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

	public Laje getLaje() {
		return laje;
	}

	public Parede getParede() {
		return parede;
	}

	public Materiais getMateriais() {
		return materiais;
	}

	public Coeficientes getCoeficientes() {
		return coeficientes;
	}

	public List<Coeficientes> getCoeficientesList() {
		return coeficientesList;
	}

	public List<EspacamentoAco> getEspacamentoAcoList() {
		return espacamentoAcoList;
	}

	public Text getEngasteCheckXEsquerda() {
		return engasteCheckXEsquerda;
	}

	public Text getEngasteCheckYCima() {
		return engasteCheckYCima;
	}

	public Text getEngasteCheckXDireita() {
		return engasteCheckXDireita;
	}

	public Text getEngastecheckYBaixo() {
		return engastecheckYBaixo;
	}

	public RadioButton getPsi0_3() {
		return psi0_3;
	}

	public RadioButton getPsi0_4() {
		return psi0_4;
	}

	public RadioButton getPsi0_5() {
		return psi0_5;
	}

	public RadioButton getPsi0_6() {
		return psi0_6;
	}

	public RadioButton getParedeSim() {
		return paredeSim;
	}

	public RadioButton getParedeNao() {
		return paredeNao;
	}

	public RadioButton getTijoloFuradoSim() {
		return tijoloFuradoSim;
	}

	public RadioButton getTijoloFuradoNao() {
		return tijoloFuradoNao;
	}

	public RadioButton getAgregadoBasaltoDiabasio() {
		return agregadoBasaltoDiabasio;
	}

	public RadioButton getAgregadoGranitoGnaisse() {
		return agregadoGranitoGnaisse;
	}

	public RadioButton getAgregadoCalcario() {
		return agregadoCalcario;
	}

	public RadioButton getAgregadoArenito() {
		return agregadoArenito;
	}

	public CheckBox getCheckYCima() {
		return checkYCima;
	}

	public CheckBox getCheckYBaixo() {
		return checkYBaixo;
	}

	public CheckBox getCheckXEsquerda() {
		return checkXEsquerda;
	}

	public CheckBox getCheckXDireita() {
		return checkXDireita;
	}

	public void setLaje(Laje laje) {
		this.laje = laje;
	}

	public void setParede(Parede parede) {
		this.parede = parede;
	}

	public void setMateriais(Materiais materiais) {
		this.materiais = materiais;
	}

	public void setCoeficientes(Coeficientes coeficientes) {
		this.coeficientes = coeficientes;
	}

	public void setCoeficientesList(List<Coeficientes> coeficientesList) {
		this.coeficientesList = coeficientesList;
	}

	public void setEspacamentoAcoList(List<EspacamentoAco> espacamentoAcoList) {
		this.espacamentoAcoList = espacamentoAcoList;
	}

	public void setEngasteCheckXEsquerda(Text engasteCheckXEsquerda) {
		this.engasteCheckXEsquerda = engasteCheckXEsquerda;
	}

	public void setEngasteCheckYCima(Text engasteCheckYCima) {
		this.engasteCheckYCima = engasteCheckYCima;
	}

	public void setEngasteCheckXDireita(Text engasteCheckXDireita) {
		this.engasteCheckXDireita = engasteCheckXDireita;
	}

	public void setEngastecheckYBaixo(Text engastecheckYBaixo) {
		this.engastecheckYBaixo = engastecheckYBaixo;
	}

	public void setPsi0_3(RadioButton psi0_3) {
		this.psi0_3 = psi0_3;
	}

	public void setPsi0_4(RadioButton psi0_4) {
		this.psi0_4 = psi0_4;
	}

	public void setPsi0_5(RadioButton psi0_5) {
		this.psi0_5 = psi0_5;
	}

	public void setPsi0_6(RadioButton psi0_6) {
		this.psi0_6 = psi0_6;
	}

	public void setParedeSim(RadioButton paredeSim) {
		this.paredeSim = paredeSim;
	}

	public void setParedeNao(RadioButton paredeNao) {
		this.paredeNao = paredeNao;
	}

	public void setTijoloFuradoSim(RadioButton tijoloFuradoSim) {
		this.tijoloFuradoSim = tijoloFuradoSim;
	}

	public void setTijoloFuradoNao(RadioButton tijoloFuradoNao) {
		this.tijoloFuradoNao = tijoloFuradoNao;
	}

	public void setAgregadoBasaltoDiabasio(RadioButton agregadoBasaltoDiabasio) {
		this.agregadoBasaltoDiabasio = agregadoBasaltoDiabasio;
	}

	public void setAgregadoGranitoGnaisse(RadioButton agregadoGranitoGnaisse) {
		this.agregadoGranitoGnaisse = agregadoGranitoGnaisse;
	}

	public void setAgregadoCalcario(RadioButton agregadoCalcario) {
		this.agregadoCalcario = agregadoCalcario;
	}

	public void setAgregadoArenito(RadioButton agregadoArenito) {
		this.agregadoArenito = agregadoArenito;
	}

	public void setCheckYCima(CheckBox checkYCima) {
		this.checkYCima = checkYCima;
	}

	public void setCheckYBaixo(CheckBox checkYBaixo) {
		this.checkYBaixo = checkYBaixo;
	}

	public void setCheckXEsquerda(CheckBox checkXEsquerda) {
		this.checkXEsquerda = checkXEsquerda;
	}

	public void setCheckXDireita(CheckBox checkXDireita) {
		this.checkXDireita = checkXDireita;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}