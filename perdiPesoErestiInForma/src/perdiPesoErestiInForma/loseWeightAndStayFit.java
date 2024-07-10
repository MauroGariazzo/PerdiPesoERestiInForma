package perdiPesoErestiInForma;
import java.util.Scanner;

public class loseWeightAndStayFit {
	private static Scanner scanner = new Scanner(System.in);
	private static String name;
	private static double weight;
	private static double height;
	private static int age;
	private static char sex;
	private static char appSex;
	private static int day = 0;
	private static double BMI;
	
	//lunedì, martedì, mercoledì, ecc
	private static int indexFood;
	private static String[][] foods = {{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""},{"","","","","",""}}; 
	private static double[][] foodsKcals = {{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
	
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	
	public static void main(String[] args) {
		start();
	}
	
	private static void start() {
		System.out.println("Benvenuto all'applicazione 'Perdi peso & Resti in forma'.\n"
				+ "Questa applicazione ti aiuterà a gestire la tua dieta settimanale in modo semplice e intuitivo,\n"
				+ "tenendo conto delle tue caratteristiche fisiche come peso, altezza, età e genere.\n"
				+ "Inizia subito a usare l'applicazione per monitorare la tua dieta e rimanere in forma!");
		
		insertUserAnagraphic();		
	}
	
	
	private static void insertUserAnagraphic() {	
		setName();
		System.out.print("Ciao "+name+"!\n");
		
		setSex();
		setWeight();
		System.out.println("Ti ringrazio per la collaborazione!");	
		setHeight();
		System.out.println("Perfetto! Ora non resta che l'ultima domanda!");
		setAge();
		
		//------------------------------------------------------------------------//
		BMI=BMICalculator();
		System.out.println("\nIl tuo BMI è: " + BMI);
		System.out.println("Il tuo stato di salute: " + stateOfHealth(BMI));
		System.out.println("\nMa ora mettiamoci immediatamente all'opera! Sotto di te vedi il mio menù. Inserisci il numero corrispondente alla tua scelta!");
		checkMenu();
	}
	
	private static void setName() {
		System.out.println("Ma iniziamo a conoscerci!\n");
		while(true) {
			System.out.print("Come ti chiami? ");
			name=scanner.nextLine();
		
			if(name == "") {
				System.out.println(ANSI_RED + "nome non valido" + ANSI_RESET);
			}
			else {
				break;
			}
		}				
	}
	
	private static void setSex() {		
		while(true)
		{		
			System.out.print("\nQual è il tuo sesso? (M/F) ");
			
			if(scanner.hasNextLine()) {
			    try{
			       sex = scanner.nextLine().toLowerCase().charAt(0);				
				    if(sex == 'm' || sex == 'f') {
					    sex = Character.toLowerCase(sex);
					    appSex=sex=='m'?'o':'a';
					    return;				
				    }
				
				    else {
					    System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
				    }
			    }
				
				catch(Exception e)
				{
					System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
			    }
			}
		}
	}
	
	private static void setWeight() {	
		System.out.print("Sperando di non essere indiscret" + appSex + ", ");
		//dischargeNewLine();
		while(true)
		{
			System.out.println("vorresti cortesemente comunicarmi il tuo peso espresso in chili? ");
			String input = scanner.nextLine();
			
			if(checkIfIsDouble(input)){
				weight = Double.parseDouble(input);
	            
	            if(weight <=2.13 || weight > 584) {
					System.out.println(ANSI_RED + "Sei sicur"+appSex+" di aver inserito correttamente il tuo peso?" + ANSI_RESET);
				}
				
			    if(weight > 2.13 && weight < 584) {
					return;				
				}
	        }
	        
	        else{
	        	System.out.println(ANSI_RED + "Puoi inserire solo dati numerici" + ANSI_RESET);
	        }
		}
	}
		
	
	private static void setHeight() {	
		System.out.print("Ora, ");
		//dischargeNewLine();
		while(true)
		{
			System.out.println("vorresti cortesemente comunicarmi la tua altezza espressa in metri?");
			String input = scanner.nextLine();
			
			if(checkIfIsDouble(input)){
				height = Double.parseDouble(input);
	            
				if(height <= 0.6524 || height > 2.51) {
					System.out.println(ANSI_RED + "Sei sicur"+appSex+" di aver inserito correttamente la tua altezza?" + ANSI_RESET);
									
				}
				else if(height > 0.6524 && height < 2.51){
					return;
				}
	        }
	        
	        else{
	        	System.out.println(ANSI_RED + "Puoi inserire solo dati numerici" + ANSI_RESET);
	        }
		}
	}
	
	

	
	private static void setAge() {		
		while(true)
		{
			System.out.print("Quanti anni hai? ");
			String input = scanner.nextLine();
			
			if(checkIfIsInt(input)){
				age = Integer.parseInt(input);
	            
				if(age >= 18 && age < 116) {
					return;				
				}
				else if(age < 18) {
					System.out.println(ANSI_RED + "Mi dispiace, ma quest'app è riservata ai maggiorenni!" + ANSI_RESET);
				}
				
				else{
					System.out.println(ANSI_RED + "Perdona la sfiducia, ma non credo tu possa essere così longev"+appSex+"!" + ANSI_RESET);
				}
	        }
	        
	        else{
	        	System.out.println(ANSI_RED + "Puoi inserire solo dati numerici" + ANSI_RESET);
	        }
		}
	}
			
	private static void dischargeNewLine() {
		if(scanner.hasNextLine()) {
			scanner.nextLine();
		}
	}
	
	private static boolean checkIfIsDouble(String input){
	    try{
	        Double.parseDouble(input);
	        return true;
	    }
	    
	    catch(NumberFormatException e){
	        return false;
	    }
	}
	
	private static boolean checkIfIsInt(String input){	    
	    try{
	        Integer.parseInt(input);
	        return true;
	    }
	    
	    catch(NumberFormatException e){
	        return false;
	    }
	}
	
	private static double BMICalculator() {
		return weight / Math.pow(height, 2);
	}
	
	private static String stateOfHealth(double BMI) {
		//dichiarazione delle soglie standard del BMI nel contesto di un maschio tra i 19 e i 24 anni
		double severeUnderweight=16;
		double quiteUnderweight=17;
		double underweight=19;
		double normalweight=24;
		double overweight=30;
		double obese1=35;
		double obese2=40;
		
		//modifica delle soglie sulla base dell'età: per ogni scatto di età la soglia del BMI sale di uno
		if (age>24&&age<=34) {
			severeUnderweight++;
			quiteUnderweight++;
			underweight++;
			normalweight++;
			overweight++;
			obese1++;
			obese2++;
		}
		if (age>34&&age<=44) {
			severeUnderweight++;
			quiteUnderweight++;
			underweight++;
			normalweight++;
			overweight++;
			obese1++;
			obese2++;
		}
		if (age>44&&age<=54) {
			severeUnderweight++;
			quiteUnderweight++;
			underweight++;
			normalweight++;
			overweight++;
			obese1++;
			obese2++;
		}
		if (age>54&&age<=64) {
			severeUnderweight++;
			quiteUnderweight++;
			underweight++;
			normalweight++;
			overweight++;
			obese1++;
			obese2++;
		}
		if (age>64) {
			severeUnderweight++;
			quiteUnderweight++;
			underweight++;
			normalweight++;
			overweight++;
			obese1++;
			obese2++;
		
		}
		//modifica delle soglie sulla base del sesso
		if(sex=='F') {
			severeUnderweight--;
			quiteUnderweight--;
			underweight--;
			normalweight--;
			overweight--;
			obese1--;
			obese2--;
		}
		
		String weightCheck="";//il messaggio weightCheck si aggiorna in base al valore del BMI
		if(BMI<severeUnderweight) {
			weightCheck="Accidenti! Sembra che tu sia gravemente sottopeso! Avremo un bel po' di lavoro da fare questa settimana :)";
		}else if(BMI>severeUnderweight&&BMI<=quiteUnderweight) {
			weightCheck="Accidenti! Sembra che tu sia leggermente sottopeso! Avremo un po' di lavoro da fare questa settimana :)";
		}else if(BMI>severeUnderweight&&BMI<=underweight) {
			weightCheck="Accidenti! Sembra che tu sia sottopeso! Avremo un po' di lavoro da fare questa settimana :)";
		}else if(BMI>underweight&&BMI<=normalweight) 			
		{
			weightCheck="Complimenti! Sei normopeso :)";
		}else if(BMI>normalweight&&BMI<=overweight) {
			weightCheck="Accidenti! Sembra che tu sia sovrappeso! Avremo un po' di lavoro da fare questa settimana :)";
		}else if(BMI>overweight&&BMI<=obese1){
			weightCheck="Accidenti! Sembra che tu sia obes"+appSex+" di tipo 1! Avremo un bel po' di lavoro da fare questa settimana :)";
		}else if(BMI>obese1&&BMI<=obese2){
			weightCheck="Accidenti! Sembra che tu sia obes"+appSex+" di tipo 2! Avremo un bel po' di lavoro da fare questa settimana :)";
		}else {
			weightCheck="Accidenti! Sembra che tu sia obes"+appSex+" di tipo 3! Avremo un bel po' di lavoro da fare questa settimana :)";
		}
		return weightCheck;
	}
	
	private static void checkMenu() {
		boolean exit = false;
		while(!exit)
		{			
			System.out.println(ANSI_GREEN + "giorno n° " + (day+1) + ANSI_RESET);
			
			System.out.println("\nPremi:\n"
				+ "1 per inserire nuovi alimenti per la tua giornata\n"
				+ "2 per iniziare una nuova settimana \n"
				+ "3 per iniziare una nuova giornata\n"
				+ "4 per inserire un alimento da tabella, consultando i vari macronutrienti\n"
				+ "5 per visualizzare gli alimenti e kcal assunti nella settimana\n"
				+ "e per uscire");		
			
			String stringInput = scanner.nextLine();
			if(stringInput.equals("")){
				System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
			    continue;
			}
			
			if(checkIfIsInt(stringInput)) {
				int intInput = Integer.parseInt(stringInput);
				
				switch(intInput) {
				case 1:
					manageDailyFoods(); //gestione alimenti del giorno
					break;
			
				case 2:
					startNewWeek(); //inizia una nuova settimana
					break;
				
				case 3:
					if(day<6) {
						startNewDay(); //inizia un nuovo giorno
					}
					else {
						System.out.println(ANSI_RED + "Sei arrivato a fine settimana! Premi 2 per iniziare una nuova settimana" + ANSI_RESET);
					}
					break;
				
				case 4:
					AggiungiAlimentoDallaTabella();
					break;
					
				case 5:
					VisualizzaTutto();
					break;
					
				default:
					System.out.println(ANSI_RED + "Numero non compreso nel range" + ANSI_RESET);
					break;						
				}							
			}
			
			else if(exit(stringInput)) {
				exit = true;
				System.out.println("Sei uscito!");
				
			}
			
			else{
	        	System.out.println(ANSI_RED + "Puoi inserire solo dati numerici" + ANSI_RESET);	        
	        }
		}
	}
	
	//metodo per la gestione del cibo. Aggiungere un nuovo alimento o recuperarlo dai giorni precedenti
	private static void manageDailyFoods() {
		String stringInput;
		int intInput;
		while(true)
		{
			System.out.println("Premi:\n1 per recuperare un alimento dai giorni passati\n2 per nuovo alimento \npremi 'e' per uscire");
			stringInput = scanner.nextLine();
			
			if(stringInput.equals("")){
				System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
			    continue;
			}
			
			if(checkIfIsInt(stringInput)) {
				intInput = Integer.parseInt(stringInput);
				
				if(intInput>=1 && intInput <= 2) {
					newFoodOrRechargeFoodFromPastDays(intInput);
				}
				
				else {
					System.out.println(ANSI_RED + "Numero non compreso nel range" + ANSI_RESET);
				}
			}
			
			else if(exit(stringInput)) {				
				return;
			}
			
			else{
	        	System.out.println(ANSI_RED + "Puoi inserire solo dati numerici" + ANSI_RESET);	        
	        }
			
		}
	}
	
	private static void newFoodOrRechargeFoodFromPastDays(int input) 
	{		
		switch(input) {				
			case 1:
				int selectedDay = selectedDay();
				
				if(selectedDay == -1) {
					return;
				}
				
				String[]eateanFoods = eatenFoodInThePreviousDay(selectedDay);
				double[]kCals = kcalsInThePreviousDay(selectedDay);
			    
				//controllo se l'array è vuoto(se è vuoto significa che l'utente sta cercando di caricare un alimento dal giorno successivo o da quello
				//odierno. 
				if(!checkEatenFoodsLength(eateanFoods)) {						
					return;
				}
				
				int selectedFood = selectedFoodFromPreviousDays(eateanFoods);//selectedFood corrisponde allo stesso indice delle calorie
				
				//Questo controllo verifica se l'utente sta cercando di recuperare un alimento da un array vuoto).
				if(checkUserChoice(selectedFood) && checkQuantityFoodForDay(indexFood)) {
					System.out.println(ANSI_GREEN + "Alimento caricato correttamente" + ANSI_RESET);										
					foods[day][indexFood] = eateanFoods[selectedFood];
					foodsKcals[day][indexFood] = kCals[selectedFood];
				}
				break;
			case 2:
				//aggiungere un NUOVO alimento per il giorno corrente	
				setNewFood();
				break;
			
			default:
				break;
		}
	}
	
	
	private static void startNewWeek() {		
		resetFoods(); 
		resetKcals();
		printWeeklyKcal();
		day = 0;
		setWeight();
		double BMI = BMICalculator();
		System.out.println("\nil tuo BMI è: " + BMI);
		System.out.println("il tuo stato di salute: " + stateOfHealth(BMI));
		System.out.println(ANSI_GREEN + "\nNuova settimana" + ANSI_RESET);	
	}
	
	private static void resetFoods() {
		for(int day = 0; day < foods.length; day++) {
			for(int foodIndex = 0; foodIndex < foods[day].length;foodIndex++) {
				foods[day][foodIndex] = "";
			}
		}
	}
	
	private static void resetKcals() {
		for(int day = 0; day < foodsKcals.length; day++) {
			for(int kcalsIndex = 0; kcalsIndex < foodsKcals[day].length;kcalsIndex++) {
				foodsKcals[day][kcalsIndex] = 0;
			}
		}
	}
	
	private static void startNewDay() {
		if(day<6) {
			//alimenti assunti espressi in Kcal
			printDailyKcal();
			day++;
			indexFood = 0;
		}
		
		else {
			System.out.print(ANSI_YELLOW + "sei arrivato a fine settimana, iniziane una nuova" + ANSI_RESET);
		}
	}
		
	//calorie del giorno trascorso
	private static void printDailyKcal() {
		double kcals = 0;
		for(double kcal:foodsKcals[day]) {
			kcals += kcal;
		}
		System.out.println("Le tue calorie giornaliere: " + kcals);
	}
	
	//calorie della settimana trascorsa
	private static void printWeeklyKcal() {
		double kcals = 0;
		for(int day=0; day < foodsKcals.length; day++) {
			for(double kcal:foodsKcals[day]) {
				kcals += kcal;
			}
		}
		System.out.println("Le tue calorie settimanali: " + kcals);
	}
	
	private static boolean checkEatenFoodsLength(String[]eateanFoods) {	
		if(eateanFoods.length > 0) {				
			return true;
		}
		return false;
	}
	
	private static boolean checkUserChoice(int userChoice) {	
		if(userChoice != -1) {				
			return true;
		}
		return false;
	}
	
	
	/*restituisce un array con tutti gli alimenti del giorno selezionato. Il giorno selezionato dall'utente è userDay*/
	private static String[] eatenFoodInThePreviousDay(int userDay) { 		
		while(true) {
						
			if(userDay-1 < day && userDay > 0) {					
				return foods[day -1];  //array con alimenti del giorno selezionato
			}
				
			else if(userDay >= day+1) {
				System.out.println(ANSI_YELLOW + "puoi recuperare un alimento solo da un giorno precedente a quello odierno" + ANSI_RESET);
				break;
			}
				
			else {
				System.out.println(ANSI_RED + "valore non compreso nel range" + ANSI_RESET);					
			}
		}		
		return new String[] {};			
	}
	
	//come per il metodo 
	private static double[] kcalsInThePreviousDay(int userDay) { 		
		while(true) {
						
			if(userDay-1 < day && userDay > 0) {					
				return foodsKcals[day -1];  //array con alimenti del giorno selezionato
			}
				
			else if(userDay >= day+1) {
				System.out.println(ANSI_YELLOW + "puoi recuperare un alimento solo da un giorno precedente a quello odierno" + ANSI_RESET);
				break;
			}
				
			else {
				System.out.println(ANSI_RED + "valore non compreso nel range" + ANSI_RESET);					
			}
		}		
		return new double[] {};			
	}
	
	//metodo per la scelta del giorno da cui si vuole recuperare un alimento
	private static int selectedDay() {		
		System.out.println("------------------------------------------------------------");
		System.out.println("In questa schermata puoi recuperare degli alimenti inseriti nei giorni precedenti: ");

		int intInput;
		
		while(true) {
			System.out.println("Inserisci il giorno della settimana (da 1 a 7) da cui vuoi recuperare un alimento, premi 'e' per uscire");			
			String stringInput = scanner.nextLine();
			stringInput = stringInput.trim();
			
			if(stringInput.equals("")){
				System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
			    continue;
			}
			
			
			if(checkIfIsInt(stringInput)) {
				intInput = Integer.parseInt(stringInput);
				
				if(intInput >= 1 && intInput <= day) {
					return intInput;
				}
				
				else if(intInput > day && intInput <=7) {
					System.out.println(ANSI_RED + "Puoi inserire solo un giorno precedente da quello odierno" + ANSI_RESET);
				}
				
				else {
					System.out.println(ANSI_RED + "Puoi inserire solo un giorno sia tra 1 e 7" + ANSI_RESET);
				}
			}
			
			//l'utente ha premuto 'e' per uscire, devo restituire comunque un valore
			else if(exit(stringInput)) {
				return -1;
			}
			
			else{	
				System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);				
			}			
		}	
	}
	
	//metodo che restituisce il singolo alimento scelto dall'utente (0. carne, 1. pesce)
	private static int selectedFoodFromPreviousDays(String[] dailyFood) {		
		//controllo se in quel determinato giorno sono presenti alimenti inseriti
		if(dailyFood[0] == "") {
			System.out.println(ANSI_RED + "nessun alimento trovato" + ANSI_RESET);
			return -1; //non ci sono alimenti trovati, restituisco 
		}		
		
		else {	
			int intInput;
			
			while(true) {
				/*metodo per la stampa degli alimenti mangiati i giorni precedenti,
				inoltre il metodo restiuisce il numero di alimenti che sono stati inseriti quel giorno, in modo da evitare che l'utente cerchi di scegliere
				un alimento fuori dal range*/
				int numOfPreviousDayFoods = displayAndGetPreviousDayFoodList(dailyFood);
				System.out.println("Premi 'e' per uscire");
				String stringInput = scanner.nextLine();
				
				if(stringInput.equals("")){
					System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
				    continue;
				}
				
				if(checkIfIsInt(stringInput)) {
					intInput = Integer.parseInt(stringInput);
					//se l'utente ha digitato un numero fuori dal range
					if(intInput > foods[0].length || intInput < 0 || intInput >= numOfPreviousDayFoods) {
						System.out.println(ANSI_RED + "Valore non compreso nel range" + ANSI_RESET);						
					}
					
					else if(dailyFood[intInput] != "") {
						return intInput;
					}
					
				}
				
				//l'utente ha premuto 'e' poter uscire dal menù
				else if(exit(stringInput)) {
					return -1;
				}
				
				else {						
					System.out.println(ANSI_RED + "Input non valido" + ANSI_RESET);
				}
			}
		}
	}
	
	//**********************da riverificare**************************//
	private static int displayAndGetPreviousDayFoodList(String[]dailyFood) {
		for(int i = 0; i < dailyFood.length; i++) {
			if(dailyFood[i] != "") {
				System.out.println("premi " + i + " per " + dailyFood[i]);
			}
		
			else {
				return i;
			}			
		}
		return foods[0].length;
	}
	
	//metodo per aggiungere un nuovo pasto
	private static void setNewFood() {
		//scanner.nextLine();
		//verifico se ho inserito il numero massimo di 5 pasti. Il limite è appunto di 5. Una volta raggiunti verrà stampato un messaggio di avviso
		if(checkQuantityFoodForDay(indexFood))
		{
		//fin quando non inserisci un numero corretto, viene richiesto l'input
			while(true) {
				System.out.println("Inserisci alimento: ");				
				String food = scanner.nextLine();
				
				//l'utente non può inserire stringhe vuote
				if(food == "") {
					System.out.println(ANSI_RED + "l'alimento non può essere vuoto" + ANSI_RESET);
					continue;
				}
				
				System.out.print("Inserisci kcal: ");
				String stringkCal = scanner.nextLine();
				//le kcal possono essere solo dei valori numerici	
				if(checkIfIsDouble(stringkCal)) {
					double kCal = Double.parseDouble(stringkCal);
					foods[day][indexFood] = food;
					foodsKcals[day][indexFood] = kCal;
					System.out.println(ANSI_GREEN + "Alimento caricato correttamente" + ANSI_RESET);
					indexFood++;
					break;
				}
			
				else {
					System.out.println(ANSI_RED + "Inserisci un numero" + ANSI_RESET);
				}			
			}
		}
		
		else {
			System.out.println(ANSI_YELLOW + "Hai inserito il massimo numero di alimenti per oggi" + ANSI_RESET);
		}
	}
	
	private static boolean checkQuantityFoodForDay(int indexFood) {
		if(indexFood < foods[0].length) { // foods[0].length (numero alimenti che possono essere registrati in un giorno)			
			return true;
		}			
		
		else {			
			return false;
		}
	}
	
	
	private static boolean exit(String input) {	
		if(Character.toLowerCase(input.charAt(0)) == 'e') {
			return true;
		}
		return false;
	}
		        
       
	public static void AggiungiAlimentoDallaTabella() {
        // Definizione della tabella degli alimenti - Non sapevo come prendere i dati dalla 
        String[][] macroMario = {
            {"Petto di pollo (Cotto)", "31", "3.6", "0", "125"},
            {"Salmone (Cotto)", "25", "13", "0", "85"},
            {"Petto di pollo (Cotto)", "31", "3.6", "0", "125"},
            {"Uova", "13", "11", "1.1", "55"},
            {"Riso Integrale", "2.6", "0.9", "23", "235"},
            {"Lenticchie (Cotto)", "9", "0.4", "20", "75"},
            {"Mandorle", "21", "49", "22", "85"},
            {"Quinoa", "4.4", "1.9", "21", "25"},
            {"Broccoli", "2.4", "0.4", "7", "135"},
            {"Mele", "0.3", "0.2", "14", "15"},
            {"Fiocchi di latte", "Tristezza", "Angoscia", "Ansia", "15"}
        };
        
        // Visualizza la tabella degli alimenti
        for (int i = 0; i < macroMario.length; i++) {
            System.out.println("Alimento: " + macroMario[i][0] + 
                               " | Proteine: " + macroMario[i][1] + "g" +
                               " | Grassi: " + macroMario[i][2] + "g" +
                               " | Carboidrati: " + macroMario[i][3] + "g" +
                               " | Calorie: " + macroMario[i][4] + " kcal");
        }
        
        // Chiedi all'utente di selezionare un alimento dalla tabella
        System.out.println("\nScrivi il nome di uno di questi alimenti: ");
        //dischargeNewLine();
        String cibotabella = scanner.nextLine();
               
        for (int i = 0; i < macroMario.length; i++) {
            if (macroMario[i][0].equalsIgnoreCase(cibotabella)) {
                // Verifica se c'è spazio per aggiungere l'alimento per il giorno corrente
                for (int j = 0; j < foods[day].length; j++) {
                    if (foods[day][j].isEmpty()) {
                        foods[day][j] = macroMario[i][0];
                        foodsKcals[day][j] = Double.parseDouble(macroMario[i][4]);
                        System.out.println(ANSI_GREEN + "Alimento aggiunto correttamente: " + macroMario[i][0] + " con " + macroMario[i][4] + " kcal" + ANSI_RESET);
                        indexFood++;
                        return;
                    }                     
                }                           
            }
            
        } 
        if(indexFood == foods[day].length) {
        	System.out.println(ANSI_YELLOW + "Hai inserito il massimo numero di alimenti per oggi" + ANSI_RESET);
        }
        else {
        System.out.println(ANSI_RED + "Alimento NON TROVATO:" + ANSI_RESET);
        }
    }
	
	
	// Metodo per visualizzare tutto
	private static void VisualizzaTutto() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Alimenti e kcal assunti nella settimana:");
		for(int i = 0; i < 7; i++) {
			System.out.print("\nGiorno " + (i+1) + ": ");
			for(int j = 0; j < foods[i].length; j++) {
				if(!foods[i][j].equals("")) {
					System.out.print(foods[i][j] + " -" + foodsKcals[i][j] + " kcal ; ");
				}
			}
		}
				System.out.println();
				checkMenu();
	}
	
}

