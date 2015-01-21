package ftims.zad9;

public class Cw9 {

	private int iloscWatkow = 16;
	private final int ROWS = 1024, COLS = 1024;
	private double[][] macierz = new double[ROWS][COLS+1];
	private long sum;
	private long timeStart;
	
	public void uruchomWatki() throws InterruptedException{
		Thread[] threads = new Thread[iloscWatkow];
		Jordan w;
		
		for(int r = 0; r < ROWS; r++) {
			double d = macierz[r][r];
			
			for(int c = 0; c < COLS +1 ; c++) {
				macierz[r][c] /= d;
			}
			timeStart = System.nanoTime();
			for(int i = 0 ; i < iloscWatkow; i++) {
				int start = i * (ROWS / iloscWatkow);
				int end = (i + 1) * (ROWS / iloscWatkow);
				w = new Jordan(start,end,macierz,r,COLS);
		    	threads[i] = new Thread(w);
		    	threads[i].start();
			}
			
			for(int i = 0 ; i < iloscWatkow; i++) {
				threads[i].join();
			}
			sum += (System.nanoTime() - timeStart);
		}
	}
	
	public long getSum() {
		return sum;
	}

	public void pokazMacierz(double[][] matrix) {
		for(int i = 0; i < ROWS ; i ++) {
			for(int j = 0; j < COLS+1 ; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public double[][] getMatrix() {
		return this.macierz;
	}
	
	public void setMatrix(double[][] matrix) {
		this.macierz = matrix;
	}
	
	public static void main(String[] args) throws InterruptedException {
		double[][] matrix = new double[1024][1025];
		for (int i = 0 ; i < 1024 ; i++) { 
			for (int j = 0 ; j < 1025 ; j++) {
				matrix[i][j] = Math.random();
			}
		}
		Cw9 cw = new Cw9();
		cw.setMatrix(matrix);
		long timeStart = System.nanoTime();
		cw.uruchomWatki();
		long timeStop = System.nanoTime();
		System.out.println("Sekewncyjna część " + ((timeStop-timeStart) - cw.getSum()) + " [ms]");
		System.out.println("Całość " + cw.getSum() + " [ms]") ;
		
	}
}
