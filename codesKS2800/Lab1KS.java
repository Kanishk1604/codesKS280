package codesKS2800;
/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;

public class Lab1KS extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static final int OBJ_NUM = 3;

	/* a function to build the content branch */
	public static BranchGroup create_Scene() {
		BranchGroup sceneBG = new BranchGroup(); // create the scene' BranchGroup
		TransformGroup sceneTG = new TransformGroup(); // create the scene's TransformGroup

		Lab1ShapesKS[] lab1Shapes = new Lab1ShapesKS[OBJ_NUM];
		// lab1Shapes[0] = new ConeShape();
		lab1Shapes[0] = new StarShape();
		lab1Shapes[1] = new StringShape("KS's Lab1");

		for (int i = 0; i < OBJ_NUM - 1; i++)
			sceneTG.addChild(lab1Shapes[i].position_Object());

		sceneBG.addChild(CommonsKS.add_Lights(CommonsKS.White, 1));
		sceneBG.addChild(CommonsKS.rotate_Behavior(7500, sceneTG));
		sceneBG.addChild(sceneTG); // make 'sceneTG' continuous rotating
		return sceneBG;
	}

	/* NOTE: Keep the constructor for each of the labs and assignments */
	public Lab1KS(BranchGroup sceneBG) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);

		SimpleUniverse su = new SimpleUniverse(canvas); // create a SimpleUniverse
		CommonsKS.define_Viewer(su, new Point3d(4.0d, 0.0d, 1.0d));

		sceneBG.addChild(CommonsKS.key_Navigation(su)); // allow key navigation
		sceneBG.compile(); // optimize the BranchGroup
		su.addBranchGraph(sceneBG); // attach the scene to SimpleUniverse

		setLayout(new BorderLayout());
		add("Center", canvas);
		frame.setSize(800, 800); // set the size of the JFrame
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		frame = new JFrame("KS's Lab1"); // NOTE: change XY to student's initials
		frame.getContentPane().add(new Lab1KS(create_Scene())); // create an instance of the class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
