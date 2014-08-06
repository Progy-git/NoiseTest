import java.util.Random;



public class PerlinNoiseGenerator {
	
	private Random random;
	private Random random2;
	private Random random3;
	private Random random4;
	static float[] noiseTab1D = new float[10];
	static float[] noiseTab1D2 = new float[10];
	static float[][] noiseTab2D = new float[200][200];
	static int pas = 10;
	private final int xsize = ((int)Chunk.x_size/pas)+1;
	private final int ysize = ((int)Chunk.y_size/pas)+1;
	private final int zsize = ((int)Chunk.z_size/pas)+1;
	float[][][] noiseTab3D = new float [xsize][ysize][zsize];
	float[][][] noiseTab3D1 = new float [xsize][ysize][zsize];
	float[][][] noiseTab3D2 = new float [xsize][ysize][zsize];
	float[][][] noiseTab3D3 = new float [xsize][ysize][zsize];
	private int wSeed;
	
	public PerlinNoiseGenerator(int i) {
		// TODO Auto-generated constructor stub
		wSeed = i;
	}
	
	//********************************************
	//******** 1D
	//********************************************
	
	public void generate(int x, int z){
		
		random = new Random(wSeed+x+z);
		random2 = new Random(wSeed+x+z+10);
		//System.out.println("********************");
		for(int i = 0; i < noiseTab1D.length; i++){
			
			noiseTab1D[i] = (float)random.nextDouble();
			
			if(i == 1){
				
				noiseTab1D[i] = (float)random2.nextDouble();
			}
			//System.out.println(noiseTab1D[i]);
		}
	}
	
	public void initNoise(){
		
		random = new Random(wSeed);
		random2 = new Random(wSeed+1);
		//System.out.println("********************");
		for(int i = 0; i < noiseTab1D.length; i++){
			
			if(i  != 11){
				
				noiseTab1D[i] = (float)random2.nextDouble();
			}
			else{
			noiseTab1D[i] = (float)random.nextDouble();}
			//System.out.println(noiseTab1D[i]);
		}
		
		for(int i = 0; i < noiseTab1D2.length; i++){
			
			noiseTab1D2[i] = (float)random2.nextDouble();
			//System.out.println(noiseTab1D[i]);
		}
	}
	
	public static float random_noise1D(int i)
	{
		
	    return noiseTab1D[i]*10;
	}
	
	public static float random_noise1D2(int i)
	{
		
	    return noiseTab1D2[i]*10;
	}
	
	public static double interpolation_lineaire1D(double a, double b, double x) {
		
		   return a * (1 - x) + b * x;
	}
	
	public static double interpolation_cos1D(double a, double b, double x) {
		
		   double k = (1 - Math.cos(x * Math.PI)) / 2;

		   return interpolation_lineaire1D(a, b, k);
	}
	
	public static double function_noise1D(double x) {
		//System.out.println("x "+x);
		   int i = (int) (x / pas);
		   
		   return interpolation_lineaire1D(random_noise1D(i), random_noise1D(i + 1), (x / pas) % 1);
	}
	
	public static double function_noise1D2(double x) {
		//System.out.println("x "+x);
		   int i = (int) (x / pas);
		   
		   return interpolation_lineaire1D(random_noise1D2(i), random_noise1D2(i + 1), (x / pas) % 1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//********************************************
	//******** 2D
	//********************************************
	
	public void init_noise2D(int k){
		
		random = new Random(k);
		random2 = new Random(k+20);
		
		for(int i = 0; i < 10; i++){
			
			for(int j = 0; j < 10; j++){
				
				if(i >= ((int)Chunk.x_size/pas)){
					
					noiseTab2D[i][j] = (float)random2.nextDouble();
				}
				else{
				
					noiseTab2D[i][j] = (float)random.nextDouble();
				}
			}
		}
	}
	
	public static float random_noise2D(int i, int j)
	{
	 
	    return noiseTab2D[i][j]*10;
	}
	
	public static double interpolation_lineaire2D(double a, double b, double c, double d, double x, double y) {
		
		   double i1 = interpolation_lineaire1D(a, b, x);
		   double i2 = interpolation_lineaire1D(c, d, x);
		   
		   return interpolation_lineaire1D(i1, i2, y);
	}
	
	double interpolation_cos2D(double a, double b, double c, double d, double x, double y) {
		
		   double x1 = interpolation_cos1D(a, b, x);
		   double x2 = interpolation_cos1D(c, d, x);
		   
		   return interpolation_cos1D(x1, x2, y);
	}
	
	public static double function_noise2D(double x, double y) {
		
		   int i = (int) (x / pas);
		   int j = (int) (y / pas);
		   
		   return interpolation_lineaire2D(random_noise2D(i, j), random_noise2D(i + 1, j), random_noise2D(i, j + 1), random_noise2D(i + 1, j + 1), (x / pas) % 1, (y / pas) % 1);
	}
	
	//********************************************
	//******** 3D
	//********************************************
	
	public void init_noise3D(int l){
		
		random = new Random(l);
		random2 = new Random(l+Chunk.x_size);
		
		System.out.println("--------------------------------------------------");
		
		for(int i = 0; i < xsize; i++){
			
			for(int j = 0; j < ysize; j++){
				
				for(int k = 0; k < zsize; k++){
					
					if(i == xsize-1){
						
						noiseTab3D1[i][j][k] = (float)random2.nextDouble();
					}
					else{
						
						noiseTab3D1[i][j][k] = (float)random.nextDouble();
					}
				}
			}
		}
		
		random = new Random(l);
		random3 = new Random(l+Chunk.z_size);
		
		System.out.println("--------------------------------------------------");
		
		for(int i = 0; i < xsize; i++){
			
			for(int j = 0; j < ysize; j++){
				
				for(int k = 0; k < zsize; k++){
					
					if(k == zsize-1){
						
						noiseTab3D2[i][j][k] = (float)random3.nextDouble();
					}
					else{
						
						noiseTab3D2[i][j][k] = (float)random.nextDouble();
					}
				}
			}
		}
		
		random = new Random(l);
		random4 = new Random(l+Chunk.z_size+Chunk.x_size);
		
		for(int i = 0; i < xsize; i++){
			
			for(int j = 0; j < ysize; j++){
				
				for(int k = 0; k < zsize; k++){
					
					if(k == zsize-1 && i == xsize-1){
						
						noiseTab3D3[i][j][k] = (float)random4.nextDouble();
					}
					else{
						
						noiseTab3D3[i][j][k] = (float)random.nextDouble();
					}
				}
			}
		}

		for(int i = 0; i < xsize; i++){
			
			for(int j = 0; j < ysize; j++){
				
				for(int k = 0; k < zsize; k++){
					
					if(noiseTab3D1[i][j][k] != noiseTab3D2[i][j][k] && noiseTab3D1[i][j][k] != noiseTab3D3[i][j][k] && noiseTab3D2[i][j][k] != noiseTab3D3[i][j][k]){
						System.out.println("a");
						noiseTab3D[i][j][k] = (float) ((noiseTab3D1[i][j][k]+noiseTab3D2[i][j][k]+noiseTab3D3[i][j][k])/3);
					}
					else{
						
						noiseTab3D[i][j][k] = (float) Math.floor(noiseTab3D1[i][j][k]);
					}
				}
			}
		}
	}

	public float random_noise3D(int i, int j, int k)
	{
		
	    return noiseTab3D[i][j][k]*10;
	}
	
	public double interpolation_lineaire3D(double a, double b, double c, double d, double e, double f, double g, double h, double x, double y, double z) {
		// TODO Auto-generated method stub
		
		double i1 = interpolation_lineaire1D(a, b, x);
		double i2 = interpolation_lineaire1D(c, d, x);
		
		double i3 = interpolation_lineaire1D(i1, i2, y);
		
		double i4 = interpolation_lineaire1D(e, f, x);
		double i5 = interpolation_lineaire1D(g, h, x);
		
		double i6 = interpolation_lineaire1D(i4, i5, y);
		   
		return interpolation_lineaire1D(i3, i6, z);
	}
	
	public double interpolation_cos3D(double a, double b, double c, double d, double e, double f, double g, double h, double x, double y, double z) {
		// TODO Auto-generated method stub
		
		double i1 = interpolation_cos1D(a, b, x);
		double i2 = interpolation_cos1D(c, d, x);
		
		double i3 = interpolation_cos1D(i1, i2, y);
		
		double i4 = interpolation_cos1D(e, f, x);
		double i5 = interpolation_cos1D(g, h, x);
		
		double i6 = interpolation_cos1D(i4, i5, y);
		   
		return interpolation_cos1D(i3, i6, z);
	}
	
	public double function_noise3D(double x, double y, double z) {
		
		   int i = (int) (x / pas);
		   int j = (int) (y / pas);
		   int k = (int) (z / pas);
		   
		   return interpolation_lineaire3D(random_noise3D(i, j, k), random_noise3D(i + 1, j, k), random_noise3D(i, j + 1, k), random_noise3D(i + 1, j + 1, k), random_noise3D(i, j, k+1), random_noise3D(i + 1, j, k+1), random_noise3D(i, j+1, k+1), random_noise3D(i+1, j+1, k+1), (x / pas) % 1, (y / pas) % 1, (z / pas) % 1);
	}
}