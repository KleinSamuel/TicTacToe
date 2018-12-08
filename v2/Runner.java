package v2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {

	static TicTacToe tic;
	static int currentPlayer;
	
	public static void start(int dim) throws IOException{
		tic = new TicTacToe(dim);
		currentPlayer = 1 + (int)(Math.random()*2); 
		
		
		while(tic.spielBeendet() == 0 && tic.istUnentschieden() == false){
			tic.arrayAusgeben();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Eingabe fuer Spieler "+currentPlayer+": ");
			System.out.print("X-Koordinate: ");
			String s = br.readLine();
			System.out.print("Y-Koordinate: ");
			String s2 = br.readLine();

			boolean wrongInput = true;
			
			while(wrongInput){
				try {
					if(Integer.parseInt(s)-1 < dim && Integer.parseInt(s)-1 >= 0 && Integer.parseInt(s2)-1 < dim && Integer.parseInt(s2)-1 >= 0){
						wrongInput = false;
						break;
					}else{
						System.out.println("min(x) und min(y) = 1");
						System.out.println("max(x) und max(y) = "+dim+".. wer rechnen kann und so..");
						System.out.println();
						System.out.println("Erneute Eingabe fuer Spieler "+currentPlayer+": ");
						System.out.print("X-Koordinate: ");
						s = br.readLine();
						System.out.print("Y-Koordinate: ");
						s2 = br.readLine();
					}								
				} catch (NumberFormatException e) {
					System.err.println("Joa gib hoid was gscheids ei oder?!");
					System.out.println("min(x) und min(y) = 1");
					System.out.println("max(x) und max(y) = "+dim+".. wer rechnen kann und so..");
					System.out.println();
					System.out.println("Erneute Eingabe fuer Spieler "+currentPlayer+": ");
					System.out.print("X-Koordinate: ");
					s = br.readLine();
					System.out.print("Y-Koordinate: ");
					s2 = br.readLine();
				} continue;
			}
			
			boolean legitMove = tic.setze(Integer.parseInt(s)-1, Integer.parseInt(s2)-1, currentPlayer);
			
			if(legitMove){
				if(currentPlayer == 1){
					currentPlayer = 2;
				}else{
					currentPlayer = 1;
				}
			}
		}
		
		if(tic.istUnentschieden()){
			System.out.println("-------");
			tic.arrayAusgeben();
			System.out.println("Unentschieden!");
		}else{
			System.out.println("-------");
			tic.arrayAusgeben();
			System.out.println("Spieler "+tic.spielBeendet()+" hat gewonnen!");
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean legitEingabe = false;
		int dimension = 0;
		System.out.print("Geben Sie die Groesse des Spielfelds ein: ");
		
		while(!legitEingabe){
			try {
				dimension = Integer.parseInt(br.readLine());
				
				if(dimension > 0){
					legitEingabe = true;					
				}else{
					throw new Exception();
				}
				
			} catch (Exception e) {
				System.err.print("Gib hoid a Zahl ei : ");
			}continue;
		}
		
		start(dimension);
	}
}
