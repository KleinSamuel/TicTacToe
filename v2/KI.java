package v2;

public class KI {
	
	private int[][] brett;
	
	
	public int[] makeMove(int[][] brett){
		setBrett(brett);
		int[] aus = new int[2];
		
		while(true){
			
			int x = 0 + (int)(Math.random()*3); 
			int y = 0 + (int)(Math.random()*3); 
			if(this.brett[x][y] == 0){
				aus[0] = x;
				aus[1] = y;
				
				return aus;
			}
		}
	}
	

	public void setBrett(int[][] brett) {
		this.brett = brett;
	}
	
}
