import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;


public class Main extends SimpleApplication{
	static Main main;
	static PerlinNoiseGenerator perlin;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		main = new Main();
		main.start();
	}
	
	public static SimpleApplication getInstance(){
		
		return main;
	}

	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub
		perlin = new PerlinNoiseGenerator(0);
		
		/*rootNode.attachChild(new Chunk(0, 0, -20));
		rootNode.attachChild(new Chunk(0, 0, -10));
		rootNode.attachChild(new Chunk(0, 0, 0));
		rootNode.attachChild(new Chunk(0, 0, 10));
		rootNode.attachChild(new Chunk(0, 0, 20));
		rootNode.attachChild(new Chunk(0, 0, 0));
		rootNode.attachChild(new Chunk(0, 0, 10));*/
		
		/*rootNode.attachChild(new Chunk(-20, 0, 0));
		rootNode.attachChild(new Chunk(-10, 0, 0));
		rootNode.attachChild(new Chunk(0, 0, 0));
		rootNode.attachChild(new Chunk(10, 0, 0));
		rootNode.attachChild(new Chunk(20, 0, 0));
		rootNode.attachChild(new Chunk(30, 0, 0));
		rootNode.attachChild(new Chunk(40, 0, 0));*/
		
		rootNode.attachChild(new Chunk(0, 0, 0));
		rootNode.attachChild(new Chunk(0, 0, 10));
		rootNode.attachChild(new Chunk(0, 0, 20));
		rootNode.attachChild(new Chunk(10, 0, 0));
		rootNode.attachChild(new Chunk(10, 0, 10));
		rootNode.attachChild(new Chunk(10, 0, 20));
		rootNode.attachChild(new Chunk(20, 0, 0));
		rootNode.attachChild(new Chunk(20, 0, 10));
		rootNode.attachChild(new Chunk(20, 0, 20));
		
		flyCam.setMoveSpeed(20);
	}
}