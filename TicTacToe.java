import java.util.Arrays;

public class TicTacToe extends basicpackage.BasicBoard implements Game{

  public TicTacToe(int dimension){
    super();
    board = new int[dimension][dimension];
    setBoardname("TicTacToe");
  }
  
// Testet ob jemand gewonnen hat, 1 = Sp1, 2 = Sp2, 0 = Niemand
public int spielBeendet(){
	int tmp;  
	boolean beendet = true;
	
	for (int i = 0; i < board.length; i++) {
		tmp = board[i][0];
		for (int j = 0; j < board.length; j++) {
			if(board[i][j] != tmp || tmp == 0){
				beendet = false;
				break;
			}
		}
		if(beendet){
			return tmp;			
		}
		beendet = true;
	}
	
	for (int i = 0; i < board.length; i++) {
		tmp = board[0][i];
		for (int j = 0; j < board.length; j++) {
			if(board[j][i] != tmp || tmp == 0){
				beendet = false;
				break;
			}
		}
		if(beendet){
			return tmp;			
		}
		beendet = true;
	}
	
	tmp = board[0][0];
	for (int i = 0; i < board.length; i++) {
		if(board[i][i] != tmp || tmp == 0){
			beendet = false;
			break;
		}
	}
	if(beendet){
		return tmp;			
	}
	beendet = true;
	
	int l = board.length-1;
	tmp = board[0][l];
	for (int i = l; i >= 0; i--) {
		if(board[i][l-i] != tmp || tmp == 0){
			beendet = false;
			break;
		}
	}
	if(beendet){
		return tmp;			
	}
	
	return 0;
  }

// Testet ob unentschieden wenn Spielfeld voll
public boolean istUnentschieden(){
	boolean voll = true;
	for (int i = 0; i < board.length; i++) {
		for (int j = 0; j < board.length; j++) {
			if(board[i][j] == 0){
				voll = false;
			}
		}
	}
	
	if(voll && (spielBeendet() == 0)){
		return true;
	}else{
		return false;
	}
}

// Setzt an einer angegebenen Position
public boolean setze(int x, int y, int player){
	if(board[y][x] != 0){
		System.out.println("Da ist schon ein Stein du Depp!");
		return false;
	}else{
		board[y][x] = player;
		return true;
	}
}
  
  
  public void arrayAusgeben(){   
    for(int i = 0; i < board.length; i++){
      System.out.println(Arrays.toString(board[i]));
    }
  }
  
  public String gameStatus(){
    return "";
  }

  public static void main(String[] args){
    TicTacToe t = new TicTacToe(3);
    System.out.println(t.toString());
    System.out.println("------------------");
    System.out.println();
    
    
    t.arrayAusgeben();
    
    System.out.println(t.spielBeendet());
    
  }
  
}