package homework1;

public class matrix {
	
	//! C-tor
	public matrix(int M, int N) {
		if(M == N){
			m_matrix = new int[M][N];
			System.out.println("start");
			fillMatrix();
			System.out.println("filling...");
		}else{
			return;			
		}
	}
	
	private void fillMatrix() {
		for (int i = 0; i < m_matrix.length; i++) {
			for(int j = 0; j < m_matrix[i].length; j++){
				if( (i == j) || (j == m_matrix[i].length -1 - i)){
					m_matrix[i][j] = 1;
				}else{
					m_matrix[i][j] = 0;					
				}
			}
		}		
	}
	
	public void printMatrix() {
		for (int i = 0; i < m_matrix.length; i++) {
			for (int j = 0; j < m_matrix[i].length; j++) {
				System.out.print(m_matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	int[][] m_matrix;
}	
	