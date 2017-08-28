/*
##	Autor: Felipe Peixoto Gaiad
##	Início: 21.08.2017
##	Final: 23.08.2017
##	Instituição: Edx MOOCs
##	Curso: Microsoft: DEV276x Learn to Program in Java
##	Projeto: Trip Planner (Projeto final do módulo 1)
*/

import java.util.Scanner;

public class TripPlanner {
	public static void main(String[] args) {		
		greeting();
		travelTimeAndBudget();
		timeDifference();
		countryArea();
		howFar();
	}

	// Seção de Introdução
	public static void greeting() {
		//Imprime textos e coleta informações do usuário
		Scanner input = new Scanner(System.in);		
		System.out.println("\nWelcome to Vacation Planner!");
		System.out.print("What's your name? ");
		String name = input.nextLine();
		System.out.print("Nice to meet you " + name + ", where are you travelling to? ");
		String country = input.nextLine();
		System.out.println("Great! " + country + " sounds like a great trip!");
	}

	// Seção sobre Tempo e Orçamento da viagem
	public static void travelTimeAndBudget() {
		//Imprime textos e coleta informações do usuário
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nNow, how many days are you going to spend travelling? ");
		double daysTravelling = input.nextDouble();
		System.out.print("How much money, in USD, are you planning to spend on your trip? ");
		double money = input.nextDouble();
		System.out.print("What is the three letter currency symbol for your travel destination? ");
		String currencySymbol = input.next().toUpperCase();		
		System.out.print("How many " + currencySymbol + " are there in 1 USD? ");
		double exchange = input.nextDouble();

		// Inicializa e atribui valores a novas variaveis
		double perDayUSD = money/daysTravelling;
		double moneyExchange = money*exchange;
		double perDayNewCurrency = moneyExchange/daysTravelling;
		double convertToHours = daysTravelling*24;
		double convertToMinutes = convertToHours*60;

		// Deixa numeros com apenas duas casas decimais
		perDayUSD = round(perDayUSD);
		moneyExchange = round(moneyExchange);
		perDayNewCurrency = round(perDayNewCurrency);

		// Imprime mensagens no console
		System.out.println("\n\nIf you are travelling for " + (int)daysTravelling +
		" days that is the same as " + (int)convertToHours + " hours or " + (int)convertToMinutes + " minutes");
		System.out.println("If you are going to spend $" + (int)money + " USD that means per day you can spend up to $"
		+ perDayUSD + " USD");
		System.out.println("Your total budget in " + currencySymbol + " is " + moneyExchange + " " + currencySymbol +
		", which per day is " + perDayNewCurrency + " " + currencySymbol);		
	}

	// Seção sobre Fuso Horário
	public static void timeDifference() {
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nWhat is the time difference, in hours, between your home and your destination? ");
		double timeLag = input.nextInt();		
		double timeLagNoon;
		
		// Chama cálculo da previsão dos horários no destino
		timeLag = calculaTimeLag(timeLag);
		timeLagNoon = calculaTimeLagNoon(timeLag);
		
		// Imprime mensagens no console
		System.out.print("That means that when it is midnight at home it will be " + (int)(timeLag) + 
		":00 in your travel destination \nand when is is noon at home it will be " + (int)(timeLagNoon) + ":00");			
	}
	// Calcula o horário no destino quando for Meia Noite no horário local
	// (subitem da seção sobre Fuso Horário)
	public static double calculaTimeLag(double timeLag) {
		if(timeLag > 24 || timeLag < -24) {
			timeLag %= 24;				
		}
		if(timeLag < 0) {
			timeLag += 24;
		}
	return timeLag;
	}	
	// Calcula o horário no destino quando for Meio Dia no horário local
	// (subitem da seção sobre Fuso Horário)
	public static double calculaTimeLagNoon(double timeLag) {	
		double timeLagNoon = timeLag;
		if (timeLag > 12 || timeLag < -12) {
			timeLagNoon = timeLag % 12;
		} else if (timeLag <= 12 && timeLag >= -12) {
			timeLagNoon = timeLag + 12;
		}
	return timeLagNoon;
	}

	// Seção sobre área física do país a ser visitado
	public static void countryArea() {
		Scanner input = new Scanner(System.in);
		System.out.print("\n\n\nWhat is the square area of your destination country in Km2? ");
		double area = input.nextDouble();// Coletea dado digitado pelo usuário

		//Inicializa e atribui valores a novas variaveis
		double convertedArea = area/2.59;

		//Deixa numeros com apenas duas casas decimais
		convertedArea = round(convertedArea);

		//Imprime mensagens no console
		System.out.print("In miles2 that is " + convertedArea); 
	}

	public static void howFar() {
		Scanner input = new Scanner(System.in);
		System.out.println("\n\n\nOk! Let's see how far you are from your destination");
		System.out.print("Please type the Degrees, Minutes and Seconds from your LOCAL LATITUDE separated by blank spaces: ");
		double localLatDeg = input.nextDouble();
		double localLatMin = input.nextDouble();
		double localLatSec = input.nextDouble();
		double latLocal = toDecimalDegree(localLatDeg, localLatMin, localLatSec);

		System.out.print("Please type the Degrees, Minutes and Seconds from your LOCAL LONGITUDE separated by blank spaces: ");
		double localLgtDeg = input.nextDouble();
		double localLgtMin = input.nextDouble();
		double localLgtSec = input.nextDouble();
		double lgtLocal = toDecimalDegree(localLgtDeg, localLgtMin, localLgtSec);

		System.out.print("\nGreat! Now, type the Degrees, Minutes and Seconds from your DESTINATION LATITUDE separated by blank spaces: ");
		double destLatDeg = input.nextDouble();
		double destLatMin = input.nextDouble();
		double destLatSec = input.nextDouble();		
		double latDest = toDecimalDegree(destLatDeg, destLatMin, destLatSec);

		System.out.print("And finally, type the Degrees, Minutes and Seconds from your DESTINATION LONGITUDE separated by blank spaces: ");
		double destLgtDeg = input.nextDouble();
		double destLgtMin = input.nextDouble();
		double destLgtSec = input.nextDouble();
		double lgtDest = toDecimalDegree(destLgtDeg, destLgtMin, destLgtSec);

		double distance = haversines(latLocal, lgtLocal, latDest, lgtDest);
		distance = round(distance);

		System.out.println("\n Wow! You are " + distance + " Km from your destination!!! \nHave a nice trip! Bye!");

	}
	
	// Transforma LAT e LGT em graus decimais
	// (subitem da seção How Far)
	public static double toDecimalDegree(double degree, double minute, double second) {
		double coord = (second/60.0) + minute;
		coord = (coord/60.0) + degree;
		coord /= 57.2957795;
		return coord;
	}

	// Calcula distancia pela fórmula de Haversines
	// (subitem da seção How Far)
	public static double haversines(double latLocal, double lgtLocal, double latDest, double lgtDest) {
		double sin2Lats = Math.sin((latDest - latLocal)/2);
		sin2Lats *= sin2Lats; // resultado do seno ao quadrado da diferença das latitudes
		
		double cosLats = (Math.cos(latLocal))*(Math.cos(latDest)); //resultado da multiplicação dos cosenos das latitudes
		
		double sin2Lgts = Math.sin((lgtDest - lgtLocal)/2);
		sin2Lgts *= sin2Lgts; // resultado do coseno ao quadrado da diferença das longitudes

		double sqrtPhase = Math.sqrt(sin2Lats + (cosLats * sin2Lgts)); //raiz quadrada dos termos calculados anteriormente

		double dist = (2 * 6367.4445 * (Math.asin(sqrtPhase))); // arco seno da raiz quadrada calculada multiplicado pelo raio da Terra multiplicado por 2
		return dist;
	}

	// Calculo para deixar números fracionários com apenas duas casas decimais
	// (utilizado por todo o programa)
	public static double round(double raw) {
		int medium = (int)(raw*100);
		double cooked = (double)medium/100;
		return cooked;
	}
}
