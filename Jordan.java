package ftims.zad9;

public class Jordan implements Runnable {

	private int beginIndex;
	private int endIndex;
	private int currentRow;
	private int columns;
	private double[][] matrix;

	public Jordan(int beginIndex, int endIndex, double[][] matrix,int currentRow,int columns) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.matrix = matrix;
		this.currentRow = currentRow;
		this.columns = columns;
	}
	
	@Override
	public void run() {
        for(int i = beginIndex; i < endIndex; i++) {
        	if(i == currentRow) {
        		continue;
        	}
            double w = matrix[i][currentRow];
        	for(int c = 0; c < columns+1; c++) {
        		matrix[i][c] -= matrix[currentRow][c] * w;
        	}
        }
	}
}
