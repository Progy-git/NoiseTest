import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;


public class Chunk extends Node{
	
	static int x_size = 10;
	static int y_size = 10;
	static int z_size = 10;
	byte[][][] b = new byte[x_size][y_size][z_size];
	PerlinNoiseGenerator p1 = new PerlinNoiseGenerator(0);

	public Chunk(int xp, int yp, int zp){
		
		Material mat = new Material(Main.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		
		Main.perlin.init_noise3D(xp+zp);
        
		for(int x_ = 0; x_ < x_size; x_++){
			
			for(int y_ = 0; y_ < y_size; y_++){
			
				for(int z_ = 0; z_ < z_size; z_++){
					
					b[x_][(int)Main.perlin.function_noise3D(x_, y_, z_)][z_] = 1;
				}
			}
		}
		
		for(int x = 0; x < x_size; x++){
			
			for(int y = 0; y < y_size; y++){
				
				for(int z = 0; z < z_size; z++){
					
					if(b[x][y][z] == 1){
						
						this.attachChild(new Block(x+xp, y+yp, z+zp, 0));
					}
				}
			}
		}
		
		/*for(int x_ = 10; x_ < 20; x_++){
			
			int yp = (int)Main.perlin.function_noise1D2(x_);
			
			Geometry geom = new Geometry("Box", new Box(0.5f, 0.5f, 0.5f));
			geom.setMaterial(mat);
			geom.setLocalTranslation(x+x_, y+yp, z);
			Main.getInstance().getRootNode().attachChild(geom);
		}*/
	}
}