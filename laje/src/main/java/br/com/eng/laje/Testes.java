package br.com.eng.laje;

public class Testes {

	public static void main(String[] args) {
		
		Double lambda = 1.01;
		
		Double resto = lambda % 1;
		
		resto = resto * 10;
		
		resto = resto % 1;
		
		resto = resto * 10;
		
		if(resto < 5 && resto!= 0.0) {
			
			resto = 5.0/100.0;
			
			lambda = lambda * 10;
			
			lambda = Math.floor(lambda);
			
			lambda = lambda / 10;
			
			lambda = lambda + resto;
			
		}
		
		else if(resto > 5 && resto!= 0.0){
			
			lambda = lambda * 10;
			
			lambda = Math.ceil(lambda);
			
			lambda = lambda / 10;
			
		}
		
		
		
		System.out.printf("resto = %.2f%n", resto);
		System.out.printf("lambda = %.2f%n", lambda);

	}

}
