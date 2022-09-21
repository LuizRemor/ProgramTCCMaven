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
import br.com.eng.entities.LajeComParede;
import br.com.eng.entities.LajeSemParede;
import br.com.eng.entities.Materiais;
import br.com.eng.entities.Parede;
import br.com.eng.util.Services;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

@SuppressWarnings("deprecation")
public class PrimaryController implements Initializable {

	private LajeComParede lajeComParede;

	private LajeSemParede lajeSemParede;

	private Parede parede;

	private Materiais materiais;

	private Coeficientes coeficientes;
	
	private Coeficientes coeficientesMxKx;

	List<Coeficientes> coeficientesList = new ArrayList<>();
	
	List<Coeficientes> coeficientesMxKxList = new ArrayList<>();

	List<EspacamentoAco> espacamentoAcoList = new ArrayList<>();

	private Services services = new Services();

	@FXML
	private Label tijoloFurado = new Label();

	@FXML
	private Label tijoloFuradoS = new Label();

	@FXML
	private Label tijoloFuradoN = new Label();

	@FXML
	private Label textEspessuraDaParede = new Label();

	@FXML
	private Label textAlturaDaParede = new Label();

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

	@FXML
	private ChoiceBox<EspacamentoAco> acoXPositivo = new ChoiceBox<EspacamentoAco>();

	@FXML
	private ChoiceBox<EspacamentoAco> acoXNegativo = new ChoiceBox<EspacamentoAco>();

	@FXML
	private ChoiceBox<EspacamentoAco> acoYPositivo = new ChoiceBox<EspacamentoAco>();

	@FXML
	private ChoiceBox<EspacamentoAco> acoYNegativo = new ChoiceBox<EspacamentoAco>();

	private BigDecimal lajeDirecao = new BigDecimal(0.0);

	private BigDecimal caso = new BigDecimal(0.0);

	public void btNovaLaje() {

		this.ladoX.clear();
		this.ladoY.clear();
		this.espessuraLaje.clear();
		this.cargaAcidental.clear();
		this.espessuraParede.clear();
		this.alturaParede.clear();
		this.imprimeResultados.clear();
		this.acoXPositivo.getItems().clear();
		this.acoXNegativo.getItems().clear();
		this.acoYPositivo.getItems().clear();
		this.acoYNegativo.getItems().clear();
		
		this.checkYCima.setSelected(false);
		this.engasteCheckYCima.setVisible(false);
		this.checkXDireita.setSelected(false);
		this.engasteCheckXDireita.setVisible(false);
		this.checkYBaixo.setSelected(false);
		this.engastecheckYBaixo.setVisible(false);
		this.checkXEsquerda.setSelected(false);
		this.engasteCheckXEsquerda.setVisible(false);
		this.paredeSim.setSelected(false);
		this.paredeNao.setSelected(false);
		this.tijoloFuradoSim.setSelected(false);
		this.tijoloFuradoNao.setSelected(false);
		this.psi0_3.setSelected(false);
		this.psi0_4.setSelected(false);
		this.psi0_5.setSelected(false);
		this.psi0_6.setSelected(false);
		this.agregadoBasaltoDiabasio.setSelected(false);
		this.agregadoGranitoGnaisse.setSelected(false);
		this.agregadoCalcario.setSelected(false);
		this.agregadoArenito.setSelected(false);
		this.tijoloFuradoSim.setVisible(true);
		this.tijoloFuradoNao.setVisible(true);
		this.espessuraParede.setVisible(true);
		this.alturaParede.setVisible(true);
		this.tijoloFurado.setVisible(true);
		this.tijoloFuradoS.setVisible(true);
		this.tijoloFuradoN.setVisible(true);
		this.textEspessuraDaParede.setVisible(true);
		this.textAlturaDaParede.setVisible(true);

	}
	
	public void btMontaDuasDirecoesSemParede() {

		this.ladoX.setText("3.4");
		this.ladoY.setText("4.8");
		this.espessuraLaje.setText("9");
		this.cargaAcidental.setText("5");
		this.paredeNao.setSelected(true);
		this.psi0_4.setSelected(true);
		this.agregadoBasaltoDiabasio.setSelected(true);
		this.checkYBaixo.setSelected(true);
		this.engastecheckYBaixo.setVisible(true);
		this.checkXEsquerda.setSelected(true);
		this.engasteCheckXEsquerda.setVisible(true);

		lajeSemParede = new LajeSemParede(this.ladoX, this.ladoY, this.espessuraLaje);

		materiais = new Materiais();

		lajeSemParede.setCargaAcidental(services.conversor(this.cargaAcidental));

	}

	public void btMontaDuasDirecoesComParede() {

		this.ladoX.setText("3");
		this.ladoY.setText("5.9");
		this.espessuraLaje.setText("10");
		this.espessuraParede.setText("12");
		this.alturaParede.setText("3");
		this.cargaAcidental.setText("2");
		this.paredeSim.setSelected(true);
		this.tijoloFuradoSim.setSelected(true);
		this.psi0_4.setSelected(true);
		this.agregadoBasaltoDiabasio.setSelected(true);
		this.checkYCima.setSelected(true);
		this.engasteCheckYCima.setVisible(true);
		this.checkYBaixo.setSelected(true);
		this.engastecheckYBaixo.setVisible(true);

		lajeComParede = new LajeComParede(this.ladoX, this.ladoY, this.espessuraLaje);
		lajeSemParede = new LajeSemParede(this.ladoX, this.ladoY, this.espessuraLaje);

		parede = new Parede(this.alturaParede, this.espessuraParede);

		materiais = new Materiais();

		lajeComParede.setCargaAcidental(services.conversor(this.cargaAcidental));
		lajeSemParede.setCargaAcidental(services.conversor(this.cargaAcidental));

	}
	
	public void btMontaUmaDirecaoComParede() {

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

		lajeComParede = new LajeComParede(this.ladoX, this.ladoY, this.espessuraLaje);

		parede = new Parede(this.alturaParede, this.espessuraParede);

		materiais = new Materiais();

		lajeComParede.setCargaAcidental(services.conversor(this.cargaAcidental));

	}

	public void btMontaUmaDirecaoSemParede() {

		this.ladoX.setText("2.5");
		this.ladoY.setText("5.2");
		this.espessuraLaje.setText("11");
		this.cargaAcidental.setText("3");
		this.paredeNao.setSelected(true);
		this.psi0_4.setSelected(true);
		this.agregadoGranitoGnaisse.setSelected(true);
		this.checkYCima.setSelected(true);
		this.engasteCheckYCima.setVisible(true);
		this.checkXDireita.setSelected(true);
		this.engasteCheckXDireita.setVisible(true);
		this.checkYBaixo.setSelected(true);
		this.engastecheckYBaixo.setVisible(true);
		this.checkXEsquerda.setSelected(true);
		this.engasteCheckXEsquerda.setVisible(true);

		lajeSemParede = new LajeSemParede(this.ladoX, this.ladoY, this.espessuraLaje);

		materiais = new Materiais();

		lajeSemParede.setCargaAcidental(services.conversor(this.cargaAcidental));

	}

	public void invalidaCamposParede() {

		if (this.paredeNao.selectedProperty().getValue()) {

			this.tijoloFuradoSim.setVisible(false);
			this.tijoloFuradoNao.setVisible(false);
			this.espessuraParede.setVisible(false);
			this.alturaParede.setVisible(false);
			this.tijoloFurado.setVisible(false);
			this.tijoloFuradoS.setVisible(false);
			this.tijoloFuradoN.setVisible(false);
			this.textEspessuraDaParede.setVisible(false);
			this.textAlturaDaParede.setVisible(false);

		} else {
			this.tijoloFuradoSim.setVisible(true);
			this.tijoloFuradoNao.setVisible(true);
			this.espessuraParede.setVisible(true);
			this.alturaParede.setVisible(true);
			this.tijoloFurado.setVisible(true);
			this.tijoloFuradoS.setVisible(true);
			this.tijoloFuradoN.setVisible(true);
			this.textEspessuraDaParede.setVisible(true);
			this.textAlturaDaParede.setVisible(true);
			this.tijoloFuradoSim.setVisible(true);
			this.tijoloFuradoNao.setVisible(true);
		}
	}

	public void limpaResultados() {

		this.imprimeResultados.clear();

	}

	public void btCalcular() {
		
		lajeComParede = new LajeComParede(this.ladoX, this.ladoY, this.espessuraLaje);
		
		lajeSemParede = new LajeSemParede(this.ladoX, this.ladoY, this.espessuraLaje);

		materiais = new Materiais();

		lajeSemParede.setCargaAcidental(services.conversor(this.cargaAcidental));
		
		lajeComParede.setCargaAcidental(services.conversor(this.cargaAcidental));
		
		if(paredeSim.selectedProperty().getValue() == true) {
			
			parede = new Parede(this.alturaParede, this.espessuraParede);
			
		}
		
		calculaLambda();
		calculaLambdaInvertido();
		verificaDirecoes();

		if (this.lajeDirecao.doubleValue() == 2.0) {
			
			ajustaLambda();
			ajustaLambdaInvertido();
			
			if (paredeSim.selectedProperty().getValue() == true) {
				
				calculosLajeComParedeDuasDirecoes();

			} else {

				calculosLajeSemParedeDuasDirecoes();

			}
			

		} else {

			if (paredeSim.selectedProperty().getValue() == true) {
				
				lajeComParede.setLambda(new BigDecimal(99999.0));

				calculosLajeComParedeUmaDirecao();

			} else {

				lajeSemParede.setLambda(new BigDecimal(99999.0));
				
				calculosLajeSemParedeUmaDirecao();

			}
		}

	}

	public void calculosLajeSemParedeUmaDirecao() {
		
		String string = "-----------------------------\n"
					  + "LAJE SEM PAREDE - UMA DIRECAO\n"
					  + "-----------------------------\n";
		
		this.imprimeResultados.appendText(string);

		acoXPositivo.getItems().clear();
		acoXNegativo.getItems().clear();
		acoYPositivo.getItems().clear();
		acoYNegativo.getItems().clear();

		populaCoeficientes();
		populaEspacamentoAco();
		defineCaso();
		decideGamaTijolo();
		calculaAreaDeAcoMinima();
		cargaPermanenteSemParede();
		cargaTotalSemParede();
		definePsi();
		cargaDeServico();
		defineAgregado();
		calculaEci();
		calculaAlphaI();
		calculaEcs();
		calculaFctm();
		calculaFctkInf();
		calculaFctd();
		calculaMomentoDeFissuracao();
		defineECalculaEquacaoMomentoDeServicoSemParede();
		verificaMomentoDeFissuracao();
		inercia();
		defineCoeficienteKParaUmaDirecao();
		calculaFlechaDeCurtaDuracao();
		calculaFlechaDeLongaDuracao();
		calculaFlechaAdmissivel();
		comparaFlecha();
		defineCoeficientes();
		calculaMomentoDeProjetoSemParede();
		calculaXSemParede();
		calculaAcoSemParede();
		calculaArmaduraDeDistribuicao();
		defineAreaDeAcoSemParede();
		montaOpcoesDeEspacamentoX();
		montaOpcoesDeEspacamentoXNegativo();
		montaOpcoesDeEspacamentoY();
		montaOpcoesDeEspacamentoYNegativo();

	}

	public void calculosLajeComParedeUmaDirecao() {
		
		String string = "-----------------------------\n"
				      + "LAJE COM PAREDE - UMA DIRECAO\n"
				      + "-----------------------------\n";
	
		this.imprimeResultados.appendText(string);

		acoXPositivo.getItems().clear();
		acoXNegativo.getItems().clear();
		acoYPositivo.getItems().clear();
		acoYNegativo.getItems().clear();

		populaCoeficientes();
		populaEspacamentoAco();
		decideGamaTijolo();
		defineCaso();
		calculaAreaDeAcoMinima();
		areaDeInfluenciaDaParedePositiva();
		cargaPermanentePositivaComParede();
		cargaTotalPositivaComParede();
		definePsi();
		cargaDeServico();
		defineAgregado();
		calculaEci();
		calculaAlphaI();
		calculaEcs();
		calculaFctm();
		calculaFctkInf();
		calculaFctd();
		calculaMomentoDeFissuracao();
		defineECalculaEquacaoMomentoDeServicoUmaDirecaoComParede();
		verificaMomentoDeFissuracao();
		inercia();
		defineCoeficienteKParaUmaDirecao();
		calculaFlechaDeCurtaDuracao();
		calculaFlechaDeLongaDuracao();
		calculaFlechaAdmissivel();
		comparaFlecha();
		areaDeInfluenciaDaParedeNegativa();
		cargaPermanenteNegativaComParede();
		cargaTotalNegativa();
		defineCoeficientes();
		calculaMomentoDeProjetoComParede();
		calculaXComParede();
		calculaAcoComParede();
		calculaArmaduraDeDistribuicao();
		defineAreaDeAcoComParede();
		calculaReacoes();
		decideReacoes();
		montaOpcoesDeEspacamentoX();
		montaOpcoesDeEspacamentoXNegativo();
		montaOpcoesDeEspacamentoY();
		montaOpcoesDeEspacamentoYNegativo();
		
		this.paredeSim.setSelected(false);
		calculosLajeSemParedeUmaDirecao();

	}
	
	public void calculosLajeSemParedeDuasDirecoes() {
		
		String string = "-------------------------------\n"
				  	  + "LAJE SEM PAREDE - DUAS DIRECOES\n"
				  	  + "-------------------------------\n";
	
		this.imprimeResultados.appendText(string);

		acoXPositivo.getItems().clear();
		acoXNegativo.getItems().clear();
		acoYPositivo.getItems().clear();
		acoYNegativo.getItems().clear();
		
		populaCoeficientes();
		populaEspacamentoAco();
		populaCoeficientesMxKx();
		defineCaso();
		calculaAreaDeAcoMinima();
		cargaPermanenteSemParede();
		cargaTotalSemParede();
		definePsi();
		cargaDeServico();
		defineAgregado();
		calculaEci();
		calculaAlphaI();
		calculaEcs();
		calculaFctm();
		calculaFctkInf();
		calculaFctd();
		calculaMomentoDeFissuracao();
		calculaMomentoDeServicoDuasDirecoes();
		verificaMomentoDeFissuracao();
		inercia();
		defineCoeficienteKParaDuasDirecoes();
		calculaFlechaDeCurtaDuracao();
		calculaFlechaDeLongaDuracao();
		calculaFlechaAdmissivel();
		comparaFlecha();
		defineCoeficientes();
		calculaMomentoDeProjetoSemParede();
		calculaXSemParede();
		calculaAcoSemParede();
		defineAreaDeAcoSemParede();
		montaOpcoesDeEspacamentoX();
		montaOpcoesDeEspacamentoXNegativo();
		montaOpcoesDeEspacamentoY();
		montaOpcoesDeEspacamentoYNegativo();

	}
	
	public void calculosLajeComParedeDuasDirecoes() {
		
		String string = "-------------------------------\n"
			  	      + "LAJE COM PAREDE - DUAS DIRECOES\n"
			  	      + "-------------------------------\n";

		this.imprimeResultados.appendText(string);

		acoXPositivo.getItems().clear();
		acoXNegativo.getItems().clear();
		acoYPositivo.getItems().clear();
		acoYNegativo.getItems().clear();

		populaCoeficientes();
		populaEspacamentoAco();
		populaCoeficientesMxKx();
		defineCaso();
		decideGamaTijolo();
		calculaAreaDeAcoMinima();
		areaDeInfluenciaDaParedePositiva();
		cargaPermanentePositivaComParede();
		cargaTotalPositivaComParede();
		definePsi();
		cargaDeServico();
		defineAgregado();
		calculaEci();
		calculaAlphaI();
		calculaEcs();
		calculaFctm();
		calculaFctkInf();
		calculaFctd();
		calculaMomentoDeFissuracao();
		calculaMomentoDeServicoDuasDirecoes();
		verificaMomentoDeFissuracao();
		inercia();
		defineCoeficienteKParaDuasDirecoes();
		calculaFlechaDeCurtaDuracao();
		calculaFlechaDeLongaDuracao();
		calculaFlechaAdmissivel();
		comparaFlecha();
		areaDeInfluenciaDaParedeNegativa();
		cargaPermanenteNegativaComParede();
		cargaTotalNegativa();
		defineCoeficientes();
		calculaMomentoDeProjetoComParede();
		calculaXComParede();
		calculaAcoComParede();
		defineAreaDeAcoComParede();
		montaOpcoesDeEspacamentoX();
		montaOpcoesDeEspacamentoXNegativo();
		montaOpcoesDeEspacamentoY();
		montaOpcoesDeEspacamentoYNegativo();
		
		this.paredeSim.setSelected(false);
		calculosLajeSemParedeDuasDirecoes();

	}

	public void decideGamaTijolo() {
		
		if(this.tijoloFuradoSim.selectedProperty().getValue() == true) {
			
			materiais.setGamaTijolo(new BigDecimal(13.0));
			
		} else {
			
			materiais.setGamaTijolo(new BigDecimal(18.0));
			
		}
		
	}
	
	public void calculaAreaDeAcoMinima() {

		if (paredeSim.selectedProperty().getValue() == true) {

			lajeComParede.calculaAcoMinimo();
			lajeSemParede.calculaAcoMinimo();

		} else {

			lajeSemParede.calculaAcoMinimo();
		}

	}
	
	public void calculaLambda() {
		
		lajeComParede.setLambda(lajeComParede.getLadoY().divide(lajeComParede.getLadoX(), MathContext.DECIMAL128));
		lajeSemParede.setLambda(lajeSemParede.getLadoY().divide(lajeSemParede.getLadoX(), MathContext.DECIMAL128));
		
		if(lajeComParede.getLambda().doubleValue() > 2.0) {
			
			lajeComParede.setLambda(new BigDecimal(99999.0));
			
		}
		if(lajeSemParede.getLambda().doubleValue() > 2.0) {
			
			lajeSemParede.setLambda(new BigDecimal(99999.0));
			
		}
		
	}
	
	public void calculaLambdaInvertido() {
		
		lajeComParede.setLambdaInvertido(lajeComParede.getLadoX().divide(lajeComParede.getLadoY(), MathContext.DECIMAL128));
		lajeSemParede.setLambdaInvertido(lajeSemParede.getLadoX().divide(lajeSemParede.getLadoY(), MathContext.DECIMAL128));
		
	}

	public void verificaDirecoes() {

		if (paredeSim.selectedProperty().getValue() == true) {

			if (lajeComParede.getLambda().doubleValue() > 2.0) {

				this.lajeDirecao = new BigDecimal(1.0);

			} else {

				this.lajeDirecao = new BigDecimal(2.0);
				
			}

		} else {

			if (lajeSemParede.getLambda().doubleValue() > 2.0) {

				this.lajeDirecao = new BigDecimal(1.0);

			} else {

				this.lajeDirecao = new BigDecimal(2.0);

			}

		}

	}

	public void areaDeInfluenciaDaParedePositiva() {

		parede.calculaAreaDeInfluenciaDaParedePositiva(lajeComParede);

		services.imprimeResultados("Area de influencia: "
				+ parede.getAreaDeInfluenciaPositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void areaDeInfluenciaDaParedeNegativa() {

		parede.calculaAreaDeInfluenciaDaParedeNegativa(lajeComParede);

		services.imprimeResultados(
				"Area de influencia Negativa: "
						+ parede.getAreaDeInfluenciaNegativa().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void cargaPermanenteSemParede() {

		lajeSemParede.setCargaPermanente(lajeSemParede.calculaCargaPermanente(materiais));

		services.imprimeResultados("Carga permamente: "
				+ lajeSemParede.getCargaPermanente().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void cargaPermanentePositivaComParede() {

		lajeComParede.setCargaPermanentePositiva(lajeComParede.calculaCargaPermanentePositiva(materiais, parede));

		services.imprimeResultados(
				"Carga permamente: "
				+ lajeComParede.getCargaPermanentePositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void cargaPermanenteNegativaComParede() {

		lajeComParede.setCargaPermanenteNegativa(lajeComParede.calculaCargaPermanenteNegativa(materiais, parede));

		services.imprimeResultados(
				"Carga permamente Negativa: "
						+ lajeComParede.getCargaPermanenteNegativa().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
	}

	public void cargaTotalSemParede() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		lajeSemParede.setCargaTotal(lajeSemParede.getCargaPermanente().add(cargaAcidental));

		services.imprimeResultados(
				"Carga Total: " + lajeSemParede.getCargaTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void cargaTotalPositivaComParede() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		lajeComParede.setCargaTotalPositiva(lajeComParede.getCargaPermanentePositiva().add(cargaAcidental));

		services.imprimeResultados(
				"Carga Total: " + lajeComParede.getCargaTotalPositiva().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void cargaTotalNegativa() {

		BigDecimal cargaAcidental = services.conversor(this.cargaAcidental);

		lajeComParede.setCargaTotalNegativa(lajeComParede.getCargaPermanenteNegativa().add(cargaAcidental));

		services.imprimeResultados("Carga Total Negativa: "
				+ lajeComParede.getCargaTotalNegativa().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void cargaDeServico() {

		if (paredeSim.selectedProperty().getValue() == true) {

			lajeComParede.setCargaDeServico(lajeComParede.calculaCargaDeServico(materiais, parede));

			services.imprimeResultados(
					"Carga de Servico: "
							+ lajeComParede.getCargaDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		} else {

			lajeSemParede.setCargaDeServico(lajeSemParede.calculaCargaDeServico(materiais));

			services.imprimeResultados("Carga de Servico: "
					+ lajeSemParede.getCargaDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

	}

	public void calculaEci() {

		Double fckConcreto = materiais.getFckConcreto().doubleValue();

		Double agregado = materiais.getAgregado().doubleValue();

		Double eci = agregado * 5600.0 * Math.sqrt(fckConcreto);

		materiais.setEci(services.doubleEmBigDecimal(eci));

		services.imprimeResultados("ECI: " + materiais.getEci().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaAlphaI() {

		materiais.setAlphaI(new BigDecimal(0.8)
				.add(new BigDecimal(0.2).multiply(materiais.getFckConcreto().divide(new BigDecimal(80)))));

		services.imprimeResultados("Alpha I: " + materiais.getAlphaI().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
	}

	public void calculaEcs() {

		materiais.setEcs(materiais.getAlphaI().multiply(materiais.getEci()));

		materiais.setEcs(services.mpaParakNPorCmQuadrado(materiais.getEcs()));

		services.imprimeResultados("ECS: " + materiais.getEcs().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaFctm() {

		Double fckConcreto = materiais.getFckConcreto().doubleValue();

		Double fctm = 0.3 * Math.pow(fckConcreto, (2.0 / 3.0));

		materiais.setFctm(services.doubleEmBigDecimal(fctm));

		services.imprimeResultados("Fctm: " + materiais.getFctm().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaFctkInf() {

		materiais.setFctkInf(new BigDecimal(0.7).multiply(materiais.getFctm()));

		services.imprimeResultados("FctkInf: " + materiais.getFctkInf().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaFctd() {

		materiais.setFctd(materiais.getFctkInf().divide(new BigDecimal(1.4)));

		materiais.setFctd(services.mpaParakNPorCmQuadrado(materiais.getFctd()));

		services.imprimeResultados("Fctd: " + materiais.getFctd().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaMomentoDeFissuracao() {

		if (paredeSim.selectedProperty().getValue() == true) {

			BigDecimal espessuraLaje = services.metrosEmCentimetros(lajeComParede.getEspessura());

			BigDecimal fctm = services.mpaParakNPorCmQuadrado(materiais.getFctm());

			espessuraLaje = espessuraLaje.pow(2);

			lajeComParede.setMomentoDeFissuracao(
					new BigDecimal(0.25).multiply(fctm).multiply(new BigDecimal(100.0)).multiply(espessuraLaje));

			services.imprimeResultados(
					"Momento de fissuracao: "
							+ lajeComParede.getMomentoDeFissuracao().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		} else {

			BigDecimal espessuraLaje = services.metrosEmCentimetros(lajeSemParede.getEspessura());

			BigDecimal fctm = services.mpaParakNPorCmQuadrado(materiais.getFctm());

			espessuraLaje = espessuraLaje.pow(2);

			lajeSemParede.setMomentoDeFissuracao(
					new BigDecimal(0.25).multiply(fctm).multiply(new BigDecimal(100.0)).multiply(espessuraLaje));

			services.imprimeResultados(
					"Momento de fissuracao: "
							+ lajeSemParede.getMomentoDeFissuracao().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

	}

	public void verificaMomentoDeFissuracao() {

		if (paredeSim.selectedProperty().getValue() == true) {

			Double momentoDeServico = lajeComParede.getMomentoDeServico().doubleValue();

			Double momentoDeFissuracao = lajeComParede.getMomentoDeFissuracao().doubleValue();

			if (momentoDeServico < momentoDeFissuracao) {

				services.imprimeResultados("OK, Segue o baile!" + "\n", this.imprimeResultados);

			} else {

				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("Aumentar a espessura da laje" + "\n", this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);

			}
		} else {

			Double momentoDeServico = lajeSemParede.getMomentoDeServico().doubleValue();

			Double momentoDeFissuracao = lajeSemParede.getMomentoDeFissuracao().doubleValue();

			if (momentoDeServico < momentoDeFissuracao) {

				services.imprimeResultados("OK, Segue o baile!" + "\n", this.imprimeResultados);

			} else {

				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("Aumentar a espessura da laje" + "\n", this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);

			}

		}

	}

	public void inercia() {

		if (paredeSim.selectedProperty().getValue() == true) {

			lajeComParede.calculaInercia();

			services.imprimeResultados(
					"Inercia: " + lajeComParede.getInercia().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		} else {

			lajeSemParede.calculaInercia();

			services.imprimeResultados(
					"Inercia: " + lajeSemParede.getInercia().setScale(3, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}
	}

	public void calculaFlechaDeCurtaDuracao() {

		if (paredeSim.selectedProperty().getValue() == true) {

			BigDecimal cargaDeServico;
			BigDecimal numerador;
			BigDecimal denominador;
			BigDecimal ladoX = services.metrosEmCentimetros(lajeSemParede.getLadoX());

			cargaDeServico = lajeComParede.getCargaDeServico()
					.multiply(new BigDecimal(1.0).divide(new BigDecimal(10000.0)), MathContext.DECIMAL128);

			numerador = cargaDeServico.multiply(ladoX.pow(4), MathContext.DECIMAL128);

			denominador = materiais.getEcs().multiply(lajeComParede.getInercia(), MathContext.DECIMAL128);

			lajeComParede.setFlechaDeCurtaDuracao(lajeComParede.getCoeficienteK()
					.multiply(numerador, MathContext.DECIMAL128).divide(denominador, MathContext.DECIMAL128));

			services.imprimeResultados(
					"Flecha de curta duracao: "
							+ lajeComParede.getFlechaDeCurtaDuracao().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		} else {

			BigDecimal cargaDeServico;
			BigDecimal numerador;
			BigDecimal denominador;
			BigDecimal ladoX = services.metrosEmCentimetros(lajeSemParede.getLadoX());

			cargaDeServico = lajeSemParede.getCargaDeServico()
					.multiply(new BigDecimal(1.0).divide(new BigDecimal(10000.0)), MathContext.DECIMAL128);

			numerador = cargaDeServico.multiply(ladoX.pow(4), MathContext.DECIMAL128);

			denominador = materiais.getEcs().multiply(lajeSemParede.getInercia(), MathContext.DECIMAL128);

			lajeSemParede.setFlechaDeCurtaDuracao(lajeSemParede.getCoeficienteK()
					.multiply(numerador, MathContext.DECIMAL128).divide(denominador, MathContext.DECIMAL128));

			services.imprimeResultados(
					"Flecha de curta duracao: "
							+ lajeSemParede.getFlechaDeCurtaDuracao().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}
	}

	public void calculaFlechaDeLongaDuracao() {

		if (paredeSim.selectedProperty().getValue() == true) {

			lajeComParede.setFlechaDeLongaDuracao(
					new BigDecimal(2.32).multiply(lajeComParede.getFlechaDeCurtaDuracao(), MathContext.DECIMAL128));

			services.imprimeResultados(
					"Flecha de longa duracao: "
							+ lajeComParede.getFlechaDeLongaDuracao().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		} else {

			lajeSemParede.setFlechaDeLongaDuracao(
					new BigDecimal(2.32).multiply(lajeSemParede.getFlechaDeCurtaDuracao(), MathContext.DECIMAL128));

			services.imprimeResultados(
					"Flecha de longa duracao: "
							+ lajeSemParede.getFlechaDeLongaDuracao().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		}
	}

	public void calculaFlechaAdmissivel() {

		if (paredeSim.selectedProperty().getValue() == true) {

			lajeComParede.setFlechaAdmissivel(lajeComParede.getLadoX().divide(new BigDecimal(250.0)));

			services.imprimeResultados("Flecha Admissivel: "
					+ lajeComParede.getFlechaAdmissivel().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		} else {
			
			BigDecimal ladoX = services.metrosEmCentimetros(lajeSemParede.getLadoX());

			lajeSemParede.setFlechaAdmissivel(ladoX.divide(new BigDecimal(250.0)));

			services.imprimeResultados("Flecha Admissivel: "
					+ lajeSemParede.getFlechaAdmissivel().setScale(4, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		}
	}

	public void comparaFlecha() {

		if (paredeSim.selectedProperty().getValue() == true) {

			Double flechaAdmissivel = lajeComParede.getFlechaAdmissivel().doubleValue();

			Double flechaDeLongaDuracao = lajeComParede.getFlechaDeLongaDuracao().doubleValue();

			if (flechaAdmissivel > flechaDeLongaDuracao) {

				services.imprimeResultados("OK, Segue o baile! \n", this.imprimeResultados);

			} else {

				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("Aumentar a espessura da laje" + "\n", this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);

			}
		} else {

			Double flechaAdmissivel = lajeSemParede.getFlechaAdmissivel().doubleValue();

			Double flechaDeLongaDuracao = lajeSemParede.getFlechaDeLongaDuracao().doubleValue();

			if (flechaAdmissivel > flechaDeLongaDuracao) {

				services.imprimeResultados("OK, Segue o baile! \n", this.imprimeResultados);

			} else {

				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("Aumentar a espessura da laje" + "\n", this.imprimeResultados);
				services.imprimeResultados("" + "\n", this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);
				services.imprimeResultados("-------------------------------------------------" + "\n",this.imprimeResultados);

			}

		}
	}

	public void definePsi() {

		if (paredeSim.selectedProperty().getValue() == true) {

			if (psi0_3.isSelected()) {
				lajeComParede.setPsi(new BigDecimal(0.3));
			}

			else if (psi0_4.isSelected()) {
				lajeComParede.setPsi(new BigDecimal(0.4));
			}

			else if (psi0_5.isSelected()) {
				lajeComParede.setPsi(new BigDecimal(0.5));
			}

			else {
				lajeComParede.setPsi(new BigDecimal(0.6));
			}
		} else {

			if (psi0_3.isSelected()) {
				lajeSemParede.setPsi(new BigDecimal(0.3));
			}

			else if (psi0_4.isSelected()) {
				lajeSemParede.setPsi(new BigDecimal(0.4));
			}

			else if (psi0_5.isSelected()) {
				lajeSemParede.setPsi(new BigDecimal(0.5));
			}

			else {
				lajeSemParede.setPsi(new BigDecimal(0.6));
			}
		}

	}

	public void defineECalculaEquacaoMomentoDeServicoUmaDirecaoComParede() {

		if (!checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(lajeComParede.getLadoX());

			lajeComParede.setMomentoDeServico(lajeComParede.getCargaDeServico().multiply(ladoX.pow(2))
					.divide(new BigDecimal(8.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (Pserv*Lx^2)/8 \n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeComParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(lajeComParede.getLadoX());

			lajeComParede.setMomentoDeServico(new BigDecimal(9.0).multiply(lajeComParede.getCargaDeServico())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (9*Pserv*Lx^2)/128\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeComParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

		else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(lajeComParede.getLadoX());

			lajeComParede.setMomentoDeServico(new BigDecimal(9.0).multiply(lajeComParede.getCargaDeServico())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (9*Pserv*Lx^2)/128\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeComParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = services.CentimetrosEmMetros(lajeComParede.getLadoX());

			lajeComParede.setMomentoDeServico(lajeComParede.getCargaDeServico().multiply(ladoX.pow(2))
					.divide(new BigDecimal(24.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (Pserv*Lx^2)/24\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeComParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

	}
	
	public void calculaMomentoDeServicoDuasDirecoes() {
		
		//Tem que verificar onde tem engaste para usar a carga de serviço negativa.
		
		defineCoeficientesMxKx();
		
		if(paredeSim.selectedProperty().getValue() == true) {
			
			BigDecimal ladoX = lajeComParede.getLadoX();
			
			lajeComParede.setMomentoDeServico(this.coeficientesMxKx.getmX().
					                   		multiply(lajeComParede.getCargaDeServico().divide(new BigDecimal(10000.0))).
					                   		multiply(ladoX.pow(2)).
					                   		multiply(new BigDecimal(100.0)));
			services.imprimeResultados("Momento de Servico: "
					+ lajeComParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
			
			
		} else {
			
			BigDecimal ladoX = services.metrosEmCentimetros(lajeSemParede.getLadoX());
			
			lajeSemParede.setMomentoDeServico(this.coeficientesMxKx.getmX().
					                   		multiply(lajeSemParede.getCargaDeServico().divide(new BigDecimal(10000.0))).
					                   		multiply(ladoX.pow(2)).
					                   		multiply(new BigDecimal(100.0)));
			services.imprimeResultados("Momento de Servico: "
					+ lajeSemParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
		}
		
		
	}
	
	public void ajustaLambda() {

		if (paredeSim.selectedProperty().getValue() == true) {

			Double lambda = lajeComParede.getLambda().doubleValue();

			Double resto = lambda % 1;

			resto = resto * 10;

			resto = resto % 1;

			resto = resto * 10;

			if (resto < 5 && resto != 0.0) {

				resto = 5.0 / 100.0;

				lambda = lambda * 10;

				lambda = Math.floor(lambda);

				lambda = lambda / 10;

				lambda = lambda + resto;

			}

			else if (resto > 5 && resto != 0.0) {

				lambda = lambda * 10;

				lambda = Math.ceil(lambda);

				lambda = lambda / 10;

			}

			lajeComParede.setLambda(services.doubleEmBigDecimal(lambda));

			System.out.printf("Lambda = %.2f%n", lajeComParede.getLambda().doubleValue());

		} else {

			Double lambda = lajeSemParede.getLambda().doubleValue();

			Double resto = lambda % 1;

			resto = resto * 10;

			resto = resto % 1;

			resto = resto * 10;

			if (resto < 5 && resto != 0.0) {

				resto = 5.0 / 100.0;

				lambda = lambda * 10;

				lambda = Math.floor(lambda);

				lambda = lambda / 10;

				lambda = lambda + resto;

			}

			else if (resto > 5 && resto != 0.0) {

				lambda = lambda * 10;

				lambda = Math.ceil(lambda);

				lambda = lambda / 10;

			}

			lajeSemParede.setLambda(services.doubleEmBigDecimal(lambda));

			System.out.printf("Lambda = %.2f%n", lajeSemParede.getLambda().doubleValue());

		}

	}
	
	public void ajustaLambdaInvertido() {

		if (paredeSim.selectedProperty().getValue() == true) {

			Double lambda = lajeComParede.getLambdaInvertido().doubleValue();
			
			lambda = lambda * 10;
			
			lambda = Math.floor(lambda);
			
			lambda = lambda / 10;
			
			lajeComParede.setLambdaInvertido(services.doubleEmBigDecimal(lambda));
			
			System.out.printf("Lambda Invertido = %.2f%n", lajeComParede.getLambdaInvertido().doubleValue());
			
		} else {
			
			Double lambda = lajeSemParede.getLambdaInvertido().doubleValue();
			
			lambda = lambda * 10;
			
			lambda = Math.floor(lambda);
			
			lambda = lambda / 10;
			
			lajeSemParede.setLambdaInvertido(services.doubleEmBigDecimal(lambda));
			
			System.out.printf("Lambda Invertido = %.2f%n", lajeSemParede.getLambdaInvertido().doubleValue());
			
		}

	}
	
	public void defineCoeficientesMxKx() {
		
		if(paredeSim.selectedProperty().getValue() == true) {
		
		for (Coeficientes coef : coeficientesMxKxList) {
			if (coef.getCaso().doubleValue() == this.caso.doubleValue()
					&& coef.getLambda().doubleValue() == lajeComParede.getLambdaInvertido().doubleValue()) {

				this.coeficientesMxKx = coef;

				}
			}
		}
		else {
			for (Coeficientes coef : coeficientesMxKxList) {
				if (coef.getCaso().doubleValue() == this.caso.doubleValue()
						&& coef.getLambda().doubleValue() == lajeSemParede.getLambdaInvertido().doubleValue()) {

					this.coeficientesMxKx = coef;

					}
				}
		}

	}

	public void defineECalculaEquacaoMomentoDeServicoSemParede() {

		if (!checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = lajeSemParede.getLadoX();

			lajeSemParede.setMomentoDeServico(lajeSemParede.getCargaDeServico().multiply(ladoX.pow(2))
					.divide(new BigDecimal(8.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (Pserv*Lx^2)/8 \n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeSemParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = lajeSemParede.getLadoX();

			lajeSemParede.setMomentoDeServico(new BigDecimal(9.0).multiply(lajeSemParede.getCargaDeServico())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (9*Pserv*Lx^2)/128\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeSemParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

		else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = lajeSemParede.getLadoX();

			lajeSemParede.setMomentoDeServico(new BigDecimal(9.0).multiply(lajeSemParede.getCargaDeServico())
					.multiply(ladoX.pow(2)).divide(new BigDecimal(128.0)).multiply(new BigDecimal(100.0)));

			services.imprimeResultados("Equacao M. Serv. = (9*Pserv*Lx^2)/128\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeSemParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

		else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			BigDecimal ladoX = lajeSemParede.getLadoX();

			lajeSemParede.setMomentoDeServico(lajeSemParede.getCargaDeServico()
					.multiply(ladoX.pow(2), MathContext.DECIMAL128).divide(new BigDecimal(24.0), MathContext.DECIMAL128)
					.multiply(new BigDecimal(100.0), MathContext.DECIMAL128));

			services.imprimeResultados("Equacao M. Serv. = (Pserv*Lx^2)/24\n", this.imprimeResultados);
			services.imprimeResultados("Momento de Servico: "
					+ lajeSemParede.getMomentoDeServico().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);

		}

	}

	public void defineCaso() {

		if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(1.0);

			services.imprimeResultados("CASO 1\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(2.0);

			services.imprimeResultados("CASO 2\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(2.0);

			services.imprimeResultados("CASO 2\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(3.0);

			services.imprimeResultados("CASO 3\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(3.0);

			services.imprimeResultados("CASO 3\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(4.0);

			services.imprimeResultados("CASO 4\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(4.0);

			services.imprimeResultados("CASO 4\n", this.imprimeResultados);

		}else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(4.0);

			services.imprimeResultados("CASO 4\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(5.0);

			services.imprimeResultados("CASO 5\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(6.0);

			services.imprimeResultados("CASO 6\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(7.0);

			services.imprimeResultados("CASO 7\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& !checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(7.0);

			services.imprimeResultados("CASO 7\n", this.imprimeResultados);

		} else if (!checkXEsquerda.selectedProperty().getValue() && checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(8.0);

			services.imprimeResultados("CASO 8\n", this.imprimeResultados);

		} else if (checkXEsquerda.selectedProperty().getValue() && !checkXDireita.selectedProperty().getValue()
				&& checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

			this.caso = new BigDecimal(8.0);

			services.imprimeResultados("CASO 8\n", this.imprimeResultados);

		} else {

			this.caso = new BigDecimal(9.0);

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
	
	public void defineCoeficienteKParaDuasDirecoes() {
		
		if(paredeSim.selectedProperty().getValue() == true) {
			
			lajeComParede.setCoeficienteK(this.coeficientesMxKx.getkX());
			
			services.imprimeResultados("Coeficiente K: "
					+ lajeComParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
			
		} else {
			
			lajeSemParede.setCoeficienteK(this.coeficientesMxKx.getkX());
			
			services.imprimeResultados("Coeficiente K: "
					+ lajeSemParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
					this.imprimeResultados);
			
		}
	
		
		
	}

	public void defineCoeficienteKParaUmaDirecao() {

		if (paredeSim.selectedProperty().getValue() == true) {

			if (!checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

				lajeComParede.setCoeficienteK(new BigDecimal(1.3));

				services.imprimeResultados("Coeficiente K: "
						+ lajeComParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

			else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

				lajeComParede.setCoeficienteK(new BigDecimal(0.53));

				services.imprimeResultados("Coeficiente K: "
						+ lajeComParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

			else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

				lajeComParede.setCoeficienteK(new BigDecimal(0.53));

				services.imprimeResultados("Coeficiente K: "
						+ lajeComParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

			else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

				lajeComParede.setCoeficienteK(new BigDecimal(0.26));

				services.imprimeResultados("Coeficiente K: "
						+ lajeComParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

		} else {

			if (!checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

				lajeSemParede.setCoeficienteK(new BigDecimal(1.3));

				services.imprimeResultados("Coeficiente K: "
						+ lajeSemParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

			else if (checkYCima.selectedProperty().getValue() && !checkYBaixo.selectedProperty().getValue()) {

				lajeSemParede.setCoeficienteK(new BigDecimal(0.53));

				services.imprimeResultados("Coeficiente K: "
						+ lajeSemParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

			else if (!checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

				lajeSemParede.setCoeficienteK(new BigDecimal(0.53));

				services.imprimeResultados("Coeficiente K: "
						+ lajeSemParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

			else if (checkYCima.selectedProperty().getValue() && checkYBaixo.selectedProperty().getValue()) {

				lajeSemParede.setCoeficienteK(new BigDecimal(0.26));

				services.imprimeResultados("Coeficiente K: "
						+ lajeSemParede.getCoeficienteK().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
						this.imprimeResultados);

			}

		}

	}

	public void defineCoeficientes() {

		if(paredeSim.selectedProperty().getValue() == true) {
		
		for (Coeficientes coef : coeficientesList) {
			if (coef.getCaso().doubleValue() == this.caso.doubleValue()
					&& coef.getLambda().doubleValue() == lajeComParede.getLambda().doubleValue()) {

				this.coeficientes = coef;

				}
			}
		}
		else {
			for (Coeficientes coef : coeficientesList) {
				if (coef.getCaso().doubleValue() == this.caso.doubleValue()
						&& coef.getLambda().doubleValue() == lajeSemParede.getLambda().doubleValue()) {

					this.coeficientes = coef;

					}
				}
		}

	}

	public BigDecimal defineCargaDoMomentoDeProjetoComParedeXEsquerda(BigDecimal carga) {

		if (checkXEsquerda.selectedProperty().getValue()) {

			carga = lajeComParede.getCargaTotalNegativa();

		} else {
			carga = lajeComParede.getCargaTotalPositiva();
		}

		return carga;

	}

	public BigDecimal defineCargaDoMomentoDeProjetoComParedeXDireita(BigDecimal carga) {

		if (checkXDireita.selectedProperty().getValue()) {

			carga = lajeComParede.getCargaTotalNegativa();

		} else {
			carga = lajeComParede.getCargaTotalPositiva();
		}

		return carga;

	}

	public BigDecimal defineCargaDoMomentoDeProjetoComParedeYCima(BigDecimal carga) {

		if (checkYCima.selectedProperty().getValue()) {

			carga = lajeComParede.getCargaTotalNegativa();

		} else {
			carga = lajeComParede.getCargaTotalPositiva();
		}

		return carga;

	}

	public BigDecimal defineCargaDoMomentoDeProjetoComParedeYBaixo(BigDecimal carga) {

		if (checkYBaixo.selectedProperty().getValue()) {

			carga = lajeComParede.getCargaTotalNegativa();

		} else {
			carga = lajeComParede.getCargaTotalPositiva();
		}

		return carga;

	}

	public void calculaMomentoDeProjetoComParede() {

		BigDecimal ladoX = services.CentimetrosEmMetros(lajeComParede.getLadoX());
		BigDecimal coeficienteDeSeguranca = new BigDecimal(1.4);

		lajeComParede.setMomentoDeProjetoX(coeficientes.getMiX().multiply(lajeComParede.getCargaTotalPositiva())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"Mx = " + lajeComParede.getMomentoDeProjetoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeComParede.setMomentoDeProjetoXLinha(coeficientes.getMiX1().multiply(lajeComParede.getCargaTotalNegativa())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"Mx' = " + lajeComParede.getMomentoDeProjetoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeComParede.setMomentoDeProjetoY(coeficientes.getMiY().multiply(lajeComParede.getCargaTotalPositiva())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"My = " + lajeComParede.getMomentoDeProjetoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeComParede.setMomentoDeProjetoYLinha(coeficientes.getMiY1().multiply(lajeComParede.getCargaTotalNegativa())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"My' = " + lajeComParede.getMomentoDeProjetoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}
	
	public void calculaMomentoDeProjetoSemParede() {

		BigDecimal ladoX = lajeSemParede.getLadoX();
		BigDecimal coeficienteDeSeguranca = new BigDecimal(1.4);

		lajeSemParede.setMomentoDeProjetoX(coeficientes.getMiX().multiply(lajeSemParede.getCargaTotal())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"Mx = " + lajeSemParede.getMomentoDeProjetoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeSemParede.setMomentoDeProjetoXLinha(coeficientes.getMiX1().multiply(lajeSemParede.getCargaTotal())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"Mx' = " + lajeSemParede.getMomentoDeProjetoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeSemParede.setMomentoDeProjetoY(coeficientes.getMiY().multiply(lajeSemParede.getCargaTotal())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"My = " + lajeSemParede.getMomentoDeProjetoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeSemParede.setMomentoDeProjetoYLinha(coeficientes.getMiY1().multiply(lajeSemParede.getCargaTotal())
				.multiply(ladoX.pow(2)).multiply(coeficienteDeSeguranca));

		services.imprimeResultados(
				"My' = " + lajeSemParede.getMomentoDeProjetoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}
	
	public void calculaXSemParede() {

		lajeSemParede.calculaD();
		materiais.calculaFcd();

		Double numerador;
		Double denominador;
		Double raiz;
		Double x;

		numerador = 2 * lajeSemParede.getMomentoDeProjetoX().doubleValue();
		denominador = 0.85 * materiais.getFcd().doubleValue() * 100 * lajeSemParede.getD().pow(2).doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeSemParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeSemParede.setX(services.doubleEmBigDecimal(x));

		services.imprimeResultados("X = " + lajeSemParede.getX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		numerador = 2 * lajeSemParede.getMomentoDeProjetoXLinha().doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeSemParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeSemParede.setxLinha(services.doubleEmBigDecimal(x));

		services.imprimeResultados("X' = " + lajeSemParede.getxLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		numerador = 2 * lajeSemParede.getMomentoDeProjetoY().doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeSemParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeSemParede.setY(services.doubleEmBigDecimal(x));

		services.imprimeResultados("Y = " + lajeSemParede.getY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		numerador = 2 * lajeSemParede.getMomentoDeProjetoYLinha().doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeSemParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeSemParede.setyLinha(services.doubleEmBigDecimal(x));

		services.imprimeResultados("Y' = " + lajeSemParede.getyLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaXComParede() {

		lajeComParede.calculaD();
		materiais.calculaFcd();

		Double numerador;
		Double denominador;
		Double raiz;
		Double x;

		numerador = 2 * lajeComParede.getMomentoDeProjetoX().doubleValue();
		denominador = 0.85 * materiais.getFcd().doubleValue() * 100 * lajeComParede.getD().pow(2).doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeComParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeComParede.setX(services.doubleEmBigDecimal(x));

		services.imprimeResultados("X = " + lajeComParede.getX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		numerador = 2 * lajeComParede.getMomentoDeProjetoXLinha().doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeComParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeComParede.setxLinha(services.doubleEmBigDecimal(x));

		services.imprimeResultados("X' = " + lajeComParede.getxLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		numerador = 2 * lajeComParede.getMomentoDeProjetoY().doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeComParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeComParede.setY(services.doubleEmBigDecimal(x));

		services.imprimeResultados("Y = " + lajeComParede.getY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		numerador = 2 * lajeComParede.getMomentoDeProjetoYLinha().doubleValue();

		raiz = Math.sqrt(1 - (numerador / denominador));

		x = lajeComParede.getD().doubleValue() / 0.8 * (1 - raiz);

		lajeComParede.setyLinha(services.doubleEmBigDecimal(x));

		services.imprimeResultados("Y' = " + lajeComParede.getyLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}
	
	public void calculaAcoSemParede() {

		lajeSemParede.setAreaDeAcoX(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeSemParede.getX(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco X = " + lajeSemParede.getAreaDeAcoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeSemParede.setAreaDeAcoXLinha(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeSemParede.getxLinha(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco X' = " + lajeSemParede.getAreaDeAcoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeSemParede.setAreaDeAcoY(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeSemParede.getY(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco Y = " + lajeSemParede.getAreaDeAcoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeSemParede.setAreaDeAcoYLinha(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeSemParede.getyLinha(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco Y' = " + lajeSemParede.getAreaDeAcoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}
	
	
	public void calculaAcoComParede() {

		lajeComParede.setAreaDeAcoX(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeComParede.getX(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco X = " + lajeComParede.getAreaDeAcoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeComParede.setAreaDeAcoXLinha(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeComParede.getxLinha(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco X' = " + lajeComParede.getAreaDeAcoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeComParede.setAreaDeAcoY(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeComParede.getY(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco Y = " + lajeComParede.getAreaDeAcoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

		lajeComParede.setAreaDeAcoYLinha(
				new BigDecimal(0.85).multiply(new BigDecimal(0.8)).multiply(materiais.getFcd(), MathContext.DECIMAL128)
						.multiply(new BigDecimal(100.0)).multiply(lajeComParede.getyLinha(), MathContext.DECIMAL128)
						.divide(materiais.getFydAco(), MathContext.DECIMAL128));

		services.imprimeResultados(
				"Area de aco Y' = " + lajeComParede.getAreaDeAcoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void defineAreaDeAcoSemParede() {

		if (lajeSemParede.getAreaDeAcoX().doubleValue() < lajeSemParede.getAreaDeAcoMinima().doubleValue()) {

			lajeSemParede.setAreaDeAcoX(lajeSemParede.getAreaDeAcoMinima());

		} 
		if (lajeSemParede.getAreaDeAcoXLinha().doubleValue() < lajeSemParede.getAreaDeAcoMinima()
				.doubleValue()) {

			lajeSemParede.setAreaDeAcoXLinha(lajeSemParede.getAreaDeAcoMinima());

		} 

		if (lajeSemParede.getAreaDeAcoYLinha().doubleValue() < lajeSemParede.getAreaDeAcoMinima()
				.doubleValue()) {

			lajeSemParede.setAreaDeAcoYLinha(lajeSemParede.getAreaDeAcoMinima());

		}

		services.imprimeResultados(
				"Area de aco final X = " + lajeSemParede.getAreaDeAcoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		services.imprimeResultados("Area de aco final X' = "
				+ lajeSemParede.getAreaDeAcoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		services.imprimeResultados(
				"Area de aco final Y (distribuicao) = "
						+ lajeSemParede.getAreaDeAcoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		services.imprimeResultados("Area de aco final Y' = "
				+ lajeSemParede.getAreaDeAcoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}
	
	public void defineAreaDeAcoComParede() {

		if (lajeComParede.getAreaDeAcoX().doubleValue() < lajeComParede.getAreaDeAcoMinima().doubleValue()) {

			lajeComParede.setAreaDeAcoX(lajeComParede.getAreaDeAcoMinima());

		}
		if (lajeComParede.getAreaDeAcoXLinha().doubleValue() < lajeComParede.getAreaDeAcoMinima()
				.doubleValue()) {

			lajeComParede.setAreaDeAcoXLinha(lajeComParede.getAreaDeAcoMinima());

		} 

		if (lajeComParede.getAreaDeAcoYLinha().doubleValue() < lajeComParede.getAreaDeAcoMinima()
				.doubleValue()) {

			lajeComParede.setAreaDeAcoYLinha(lajeComParede.getAreaDeAcoMinima());

		}

		services.imprimeResultados(
				"Area de aco final X = " + lajeComParede.getAreaDeAcoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		services.imprimeResultados("Area de aco final X' = "
				+ lajeComParede.getAreaDeAcoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		services.imprimeResultados(
				"Area de aco final Y = "
				+ lajeComParede.getAreaDeAcoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		services.imprimeResultados("Area de aco final Y' = "
				+ lajeComParede.getAreaDeAcoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);

	}

	public void calculaArmaduraDeDistribuicao() {

		if(paredeSim.selectedProperty().getValue() == true) {
		
		BigDecimal calculoAreaDeAcoPrincipal;
		BigDecimal calculoAreaDeAcoMinima;
		BigDecimal definicaoNBR = new BigDecimal(0.9);

		calculoAreaDeAcoPrincipal = lajeComParede.getAreaDeAcoX().divide(new BigDecimal(5.0));
		calculoAreaDeAcoMinima = lajeComParede.getAreaDeAcoMinima().divide(new BigDecimal(2.0));

		if (calculoAreaDeAcoPrincipal.doubleValue() > calculoAreaDeAcoMinima.doubleValue()
				&& calculoAreaDeAcoPrincipal.doubleValue() > definicaoNBR.doubleValue()) {

			lajeComParede.setAreaDeAcoY(calculoAreaDeAcoPrincipal);

		} else if (calculoAreaDeAcoMinima.doubleValue() > calculoAreaDeAcoPrincipal.doubleValue()
				&& calculoAreaDeAcoMinima.doubleValue() > definicaoNBR.doubleValue()) {

			lajeComParede.setAreaDeAcoY(calculoAreaDeAcoMinima);

		} else {
			
			lajeComParede.setAreaDeAcoY(definicaoNBR);
			
			}
		}
		else {
			
			BigDecimal calculoAreaDeAcoPrincipal;
			BigDecimal calculoAreaDeAcoMinima;
			BigDecimal definicaoNBR = new BigDecimal(0.9);

			calculoAreaDeAcoPrincipal = lajeSemParede.getAreaDeAcoX().divide(new BigDecimal(5.0));
			calculoAreaDeAcoMinima = lajeSemParede.getAreaDeAcoMinima().divide(new BigDecimal(2.0));

			if (calculoAreaDeAcoPrincipal.doubleValue() > calculoAreaDeAcoMinima.doubleValue()
					&& calculoAreaDeAcoPrincipal.doubleValue() > definicaoNBR.doubleValue()) {

				lajeSemParede.setAreaDeAcoY(calculoAreaDeAcoPrincipal);

			} else if (calculoAreaDeAcoMinima.doubleValue() > calculoAreaDeAcoPrincipal.doubleValue()
					&& calculoAreaDeAcoMinima.doubleValue() > definicaoNBR.doubleValue()) {

				lajeSemParede.setAreaDeAcoY(calculoAreaDeAcoMinima);

			} else {
				
				lajeSemParede.setAreaDeAcoY(definicaoNBR);
				
				}
			
		}

	}
	
	public void calculaReacoes() {
		
		if(this.paredeSim.selectedProperty().getValue() == true) {
			
			BigDecimal ladoX = services.CentimetrosEmMetros(lajeComParede.getLadoX());
			
			lajeComParede.setReacaoX(this.coeficientes.getKx().
									multiply(lajeComParede.getCargaTotalPositiva()).
									multiply(ladoX).
									divide(new BigDecimal(10.0)));
			
			lajeComParede.setReacaoXLinha(this.coeficientes.getKx1().
									multiply(lajeComParede.getCargaTotalPositiva()).
									multiply(ladoX).
									divide(new BigDecimal(10.0)));
			
			lajeComParede.setReacaoY(this.coeficientes.getKy().
									multiply(lajeComParede.getCargaTotalPositiva()).
									multiply(ladoX).
									divide(new BigDecimal(10.0)));
			
			lajeComParede.setReacaoYLinha(this.coeficientes.getKy1().
									multiply(lajeComParede.getCargaTotalPositiva()).
									multiply(ladoX).
									divide(new BigDecimal(10.0)));

		}
		
	}
	
	public void decideReacoes() {
		
		if(lajeComParede.getReacaoX().doubleValue() == 0.0) {
			
			lajeComParede.setReacaoX(lajeComParede.getReacaoXLinha());
			
		}
		
		if(lajeComParede.getReacaoXLinha().doubleValue() == 0.0) {
			
			lajeComParede.setReacaoXLinha(lajeComParede.getReacaoX());
			
		}
		
		if(lajeComParede.getReacaoY().doubleValue() == 0.0) {
			
			lajeComParede.setReacaoY(lajeComParede.getReacaoYLinha());
			
		}
		
		if(lajeComParede.getReacaoYLinha().doubleValue() == 0.0) {
			
			lajeComParede.setReacaoYLinha(lajeComParede.getReacaoY());
			
		}
		
		services.imprimeResultados(
				"Reacao X = " + lajeComParede.getReacaoX().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		
		services.imprimeResultados(
				"Reacao X' = " + lajeComParede.getReacaoXLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		
		services.imprimeResultados(
				"Reacao Y = " + lajeComParede.getReacaoY().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		
		services.imprimeResultados(
				"Reacao Y' = " + lajeComParede.getReacaoYLinha().setScale(2, BigDecimal.ROUND_HALF_EVEN) + "\n",
				this.imprimeResultados);
		
	}

	public void montaOpcoesDeEspacamentoX() {

		if(paredeSim.selectedProperty().getValue() == true) {
		
		for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
			if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeComParede.getAreaDeAcoX().doubleValue()
					&& espacamentoAco.getAreaDeAco().doubleValue() <= lajeComParede.getAreaDeAcoX().doubleValue()
							+ 0.3) {

				acoXPositivo.getItems().add(espacamentoAco);

				}
			}
		}
		else {
			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeSemParede.getAreaDeAcoX().doubleValue()
						&& espacamentoAco.getAreaDeAco().doubleValue() <= lajeSemParede.getAreaDeAcoX().doubleValue()
								+ 0.3) {

					acoXPositivo.getItems().add(espacamentoAco);

					}
				}
		}

	}

	public void montaOpcoesDeEspacamentoXNegativo() {

		if (paredeSim.selectedProperty().getValue() == true) {

			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeComParede.getAreaDeAcoXLinha().doubleValue()	&&
						espacamentoAco.getAreaDeAco().doubleValue() <= lajeComParede.getAreaDeAcoXLinha().doubleValue() + 0.3) {

					acoXNegativo.getItems().add(espacamentoAco);

				}
			}
		}
		else {
			
			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeSemParede.getAreaDeAcoXLinha().doubleValue()	&&
						espacamentoAco.getAreaDeAco().doubleValue() <= lajeSemParede.getAreaDeAcoXLinha().doubleValue() + 0.3) {

					acoXNegativo.getItems().add(espacamentoAco);

				}
			}
		}

	}

	public void montaOpcoesDeEspacamentoY() {

		if (paredeSim.selectedProperty().getValue() == true) {

			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeComParede.getAreaDeAcoY().doubleValue()
						&& espacamentoAco.getAreaDeAco().doubleValue() <= lajeComParede.getAreaDeAcoY().doubleValue() + 0.3) {

					acoYPositivo.getItems().add(espacamentoAco);

				}
			}
		}
		else {
			
			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeSemParede.getAreaDeAcoY().doubleValue()
						&& espacamentoAco.getAreaDeAco().doubleValue() <= lajeSemParede.getAreaDeAcoY().doubleValue() + 0.3) {

					acoYPositivo.getItems().add(espacamentoAco);

				}
			}
			
		}
	}

	public void montaOpcoesDeEspacamentoYNegativo() {
		
		if (paredeSim.selectedProperty().getValue() == true) {

			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeComParede.getAreaDeAcoYLinha().doubleValue() &&
						espacamentoAco.getAreaDeAco().doubleValue() <= lajeComParede.getAreaDeAcoYLinha().doubleValue() + 0.3) {

					acoYNegativo.getItems().add(espacamentoAco);

				}
			}
		}
		else {
			
			for (EspacamentoAco espacamentoAco : espacamentoAcoList) {
				if (espacamentoAco.getAreaDeAco().doubleValue() >= lajeSemParede.getAreaDeAcoYLinha().doubleValue() &&
						espacamentoAco.getAreaDeAco().doubleValue() <= lajeSemParede.getAreaDeAcoYLinha().doubleValue() + 0.3) {

					acoYNegativo.getItems().add(espacamentoAco);

				}
			}
			
		}

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

			while (line != null) {

				String[] vect = line.split(";");

				BigDecimal caso = new BigDecimal(vect[0]);
				BigDecimal lambda = new BigDecimal(vect[1]);
				BigDecimal miX = new BigDecimal(vect[2]);
				BigDecimal miY = new BigDecimal(vect[3]);
				BigDecimal miX1 = new BigDecimal(vect[4]);
				BigDecimal miY1 = new BigDecimal(vect[5]);
				BigDecimal kx = new BigDecimal(vect[6]);
				BigDecimal ky = new BigDecimal(vect[7]);
				BigDecimal kx1 = new BigDecimal(vect[8]);
				BigDecimal ky1 = new BigDecimal(vect[9]);

				Coeficientes coeficientes = new Coeficientes(caso, lambda, miX, miY, miX1, miY1, kx, ky, kx1, ky1);

				coeficientesList.add(coeficientes);

				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void populaCoeficientesMxKx() {

		coeficientesMxKxList = new ArrayList<>();

		File file = new File("src/main/resources/CoeficientesMxKx.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();

			while (line != null) {

				String[] vect = line.split(";");

				BigDecimal caso = new BigDecimal(vect[0]);
				BigDecimal lambda = new BigDecimal(vect[1]);
				BigDecimal mX = new BigDecimal(vect[2]);
				BigDecimal kX = new BigDecimal(vect[3]);

				Coeficientes coeficientes = new Coeficientes(caso, lambda, mX, kX);

				coeficientesMxKxList.add(coeficientes);

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

			while (line != null) {

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

	public LajeComParede getLajeComParede() {
		return lajeComParede;
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

	public Services getServices() {
		return services;
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

	public TextField getLadoX() {
		return ladoX;
	}

	public TextField getLadoY() {
		return ladoY;
	}

	public TextField getEspessuraLaje() {
		return espessuraLaje;
	}

	public TextField getEspessuraParede() {
		return espessuraParede;
	}

	public TextField getAlturaParede() {
		return alturaParede;
	}

	public TextField getCargaAcidental() {
		return cargaAcidental;
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

	public TextArea getImprimeResultados() {
		return imprimeResultados;
	}

	public ChoiceBox<EspacamentoAco> getAcoXPositivo() {
		return acoXPositivo;
	}

	public ChoiceBox<EspacamentoAco> getAcoXNegativo() {
		return acoXNegativo;
	}

	public ChoiceBox<EspacamentoAco> getAcoYPositivo() {
		return acoYPositivo;
	}

	public ChoiceBox<EspacamentoAco> getAcoYNegativo() {
		return acoYNegativo;
	}

	public void setLajeComParede(LajeComParede lajeComParede) {
		this.lajeComParede = lajeComParede;
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

	public void setServices(Services services) {
		this.services = services;
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

	public void setLadoX(TextField ladoX) {
		this.ladoX = ladoX;
	}

	public void setLadoY(TextField ladoY) {
		this.ladoY = ladoY;
	}

	public void setEspessuraLaje(TextField espessuraLaje) {
		this.espessuraLaje = espessuraLaje;
	}

	public void setEspessuraParede(TextField espessuraParede) {
		this.espessuraParede = espessuraParede;
	}

	public void setAlturaParede(TextField alturaParede) {
		this.alturaParede = alturaParede;
	}

	public void setCargaAcidental(TextField cargaAcidental) {
		this.cargaAcidental = cargaAcidental;
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

	public void setImprimeResultados(TextArea imprimeResultados) {
		this.imprimeResultados = imprimeResultados;
	}

	public void setAcoXPositivo(ChoiceBox<EspacamentoAco> acoXPositivo) {
		this.acoXPositivo = acoXPositivo;
	}

	public void setAcoXNegativo(ChoiceBox<EspacamentoAco> acoXNegativo) {
		this.acoXNegativo = acoXNegativo;
	}

	public void setAcoYPositivo(ChoiceBox<EspacamentoAco> acoYPositivo) {
		this.acoYPositivo = acoYPositivo;
	}

	public void setAcoYNegativo(ChoiceBox<EspacamentoAco> acoYNegativo) {
		this.acoYNegativo = acoYNegativo;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}