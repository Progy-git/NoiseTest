
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;


public class Block extends Node{
	
	private final Geometry[] faces = new Geometry[6];
	private float x;
	private float y;
	private float z;
	private Material mat;

	public Block(int x, int y, int z, int i){
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		initFaces();
	}
	
	public void initFaces(){
		
		attachChild(makeAFace(FaceID.NORTH, 1));
		attachChild(makeAFace(FaceID.EAST, 1));
		attachChild(makeAFace(FaceID.SOUTH, 1));
		attachChild(makeAFace(FaceID.WEST, 1));
		attachChild(makeAFace(FaceID.UP, 1));
		attachChild(makeAFace(FaceID.DOWN, 1));
	}
	
	public Geometry makeAFace(FaceID faceID, float size) {
		
		Material mat = new Material(Main.getInstance().getAssetManager(), 
		        "Common/MatDefs/Misc/Unshaded.j3md");
		
		Mesh mesh = new Mesh();
		
		ColorRGBA color = null;

        Vector3f [] vertices = new Vector3f[4];
        int [] indexes = { 2,0,1, 1,3,2 };
        int [] indexes_ = { 1,0,2, 2,3,1 };
        
        float[] normals = new float[12];

        if(faceID == FaceID.NORTH) {
        	
            vertices[0] = new Vector3f(x,y,z);
            vertices[1] = new Vector3f(x+size,y,z);
            vertices[2] = new Vector3f(x,y+size,z);
            vertices[3] = new Vector3f(x+size,y+size,z);

            normals = new float[]{1,0,0, 1,0,0, 1,0,0, 1,0,0};
            
            indexes = indexes_;
            
            color = ColorRGBA.Brown;
        }
        else if(faceID == FaceID.EAST) {
        	
            vertices[0] = new Vector3f(x+size,y,z);
            vertices[1] = new Vector3f(x+size,y,z+size);
            vertices[2] = new Vector3f(x+size,y+size,z);
            vertices[3] = new Vector3f(x+size,y+size,z+size);

            normals = new float[]{-1,0,0, -1,0,0, -1,0,0, -1,0,0};
            
            indexes = indexes_;
            color = ColorRGBA.Brown;
        }
        else if(faceID == FaceID.SOUTH) {
        	
            vertices[0] = new Vector3f(x,y,z+size);
            vertices[1] = new Vector3f(x+size,y,z+size);
            vertices[2] = new Vector3f(x,y+size,z+size);
            vertices[3] = new Vector3f(x+size,y+size,z+size);

            normals = new float[]{0,0,-1, 0,0,-1, 0,0,-1, 0,0,-1};
            color = ColorRGBA.Brown;
        }
        else if(faceID == FaceID.WEST) {
        	
        	name = "W";
            vertices[0] = new Vector3f(x,y,z);
            vertices[1] = new Vector3f(x,y,z+size);
            vertices[2] = new Vector3f(x,y+size,z);
            vertices[3] = new Vector3f(x,y+size,z+size);

            normals = new float[]{0,0,1, 0,0,1, 0,0,1, 0,0,1};
            color = ColorRGBA.Brown;
        }
        else if(faceID == FaceID.UP) {
        	
            vertices[0] = new Vector3f(x,y+size,z);
            vertices[1] = new Vector3f(x+size,y+size,z);
            vertices[2] = new Vector3f(x,y+size,z+size);
            vertices[3] = new Vector3f(x+size,y+size,z+size);

            normals = new float[]{0,1,0, 0,1,0, 0,1,0, 0,1,0};
            
            indexes = indexes_;
            color = ColorRGBA.Green;
        } 
        else if(faceID == FaceID.DOWN) {
        	
            vertices[0] = new Vector3f(x,y,z);
            vertices[1] = new Vector3f(x+size,y,z);
            vertices[2] = new Vector3f(x,y,z+size);
            vertices[3] = new Vector3f(x+size,y,z+size);

            normals = new float[]{0,-1,0, 0,-1,0, 0,-1,0, 0,-1,0};
            color = ColorRGBA.Brown;
        }
        mat.setColor("Color", color);
        Vector2f[] texCoord = new Vector2f[4];
        texCoord[0] = new Vector2f(0,0);
        texCoord[1] = new Vector2f(1,0);
        texCoord[2] = new Vector2f(0,1);
        texCoord[3] = new Vector2f(1,1);
        
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        mesh.updateBound();
        
        faces[faceID.getID()] = new Geometry(faceID.toString(), mesh);
        faces[faceID.getID()].getMesh().scaleTextureCoordinates(new Vector2f(1, 1));
        faces[faceID.getID()].setMaterial(mat);

        return faces[faceID.getID()];
	}
	
	public Vector3f getLocation(){
		
		return new Vector3f(x, y, z);
	}
}