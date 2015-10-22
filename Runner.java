import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {

	static TicTacToe tic;
	static int currentPlayer;
	
	public static void start(int dim) throws IOException{
		tic = new TicTacToe(dim);
		currentPlayer = 1 + (int)(Math.random()*2); 
		
		
		while(tic.spielBeendet() == 0 && tic.istUnentschieden() == false){
			tic.arrayAusgeben();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Eingabe fuer Spieler "+currentPlayer+" (x y): ");
			String s = br.readLine();
			
			String[] arr = s.split(" ");
			
			boolean legitMove = tic.setze(Integer.parseInt(arr[0])-1, Integer.parseInt(arr[1])-1, currentPlayer);
			
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
		start(3);
	}
}
