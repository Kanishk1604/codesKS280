package codesKS2800;
/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

import java.awt.Font;

import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Cone;
import org.jogamp.vecmath.*;

public abstract class Lab1ShapesKS {
	protected abstract Node create_Object(); // use 'Node' for both Group and Shape3D

	public abstract Node position_Object();
}

class StarShape extends Lab1ShapesKS {
	protected Node create_Object() {
		float r = 0.6f, x, y; // vertices at 0.6 away from origin
		Point3f coor[] = new Point3f[5]; // declare 5 points for star shape
		LineArray lineArr = new LineArray(10, LineArray.COLOR_3 | LineArray.COORDINATES);
		for (int i = 0; i <= 4; i++) { // define coordinates for star shape
			x = (float) Math.cos(Math.PI / 180 * (90 + 72 * i)) * r;
			y = (float) Math.sin(Math.PI / 180 * (90 + 72 * i)) * r;
			coor[i] = new Point3f(x, y, -0.6f); // use z-value to position star shape
		}
		for (int i = 0; i <= 4; i++) {
			lineArr.setCoordinate(i * 2, coor[i]); // define point pairs for each line
			lineArr.setCoordinate(i * 2 + 1, coor[(i + 2) % 5]);
			lineArr.setColor(i * 2, CommonsKS.Red); // specify color for each pair of points
			lineArr.setColor(i * 2 + 1, CommonsKS.Green);
		}
		return new Shape3D(lineArr); // create and return a Shape3D
	}

	public Node position_Object() {
		return create_Object();
	}
}

class ConeShape extends Lab1ShapesKS {
	private TransformGroup objTG; // use 'objTG' to position an object

	public ConeShape() {
		Transform3D translator = new Transform3D(); // 4x4 matrix for translation
		translator.setTranslation(new Vector3f(0f, 0f, 0.3f));
		Transform3D rotator = new Transform3D(); // 4x4 matrix for rotation
		rotator.rotX(Math.PI / -2);
		Transform3D trfm = new Transform3D(); // 4x4 matrix for composition
		trfm.mul(translator); // apply translation next
		trfm.mul(rotator); // apply rotation first
		objTG = new TransformGroup(trfm); // set the combined transformation

		objTG.addChild(create_Object());
	}

	protected Node create_Object() {
		return new Cone(0.6f, 0.6f, CommonsKS.obj_Appearance(CommonsKS.Orange));
	}

	public Node position_Object() {
		return objTG;
	}
}

class StringShape extends Lab1ShapesKS {
	private TransformGroup objTG; // use 'objTG' to position an object
	private String str;

	public StringShape(String str_ltrs) {
		str = str_ltrs;
		Transform3D scaler = new Transform3D();
		scaler.setScale(0.2); // scaling 4x4 matrix
		objTG = new TransformGroup(scaler);
		objTG.addChild(create_Object()); // apply scaling to change the string's size
	}

	protected Node create_Object() {
		Font my2DFont = new Font("Arial", Font.PLAIN, 1); // font's name, style, size
		FontExtrusion myExtrude = new FontExtrusion();
		Font3D font3D = new Font3D(my2DFont, myExtrude);

		Point3f pos = new Point3f(-str.length() / 4f, 0, 3f);// position for the string
		Text3D text3D = new Text3D(font3D, str, pos); // create a text3D object
		return new Shape3D(text3D);
	}

	public Node position_Object() {
		return objTG;
	}
}

class circle extends Lab1ShapesKS {
	protected Node create_Object() {
		float r = 0.6f, x, y; // vertices at 0.6 away from origin
		Point3f coor[] = new Point3f[5]; // declare 5 points for star shape
		LineArray lineArr = new LineArray(10, LineArray.COLOR_3 | LineArray.COORDINATES);
		for (int i = 0; i <= 4; i++) { // define coordinates for star shape
			x = (float) Math.cos(Math.PI / 180 * (90 + 72 * i)) * r;
			y = (float) Math.sin(Math.PI / 180 * (90 + 72 * i)) * r;
			coor[i] = new Point3f(x, y, -0.6f); // use z-value to position star shape
		}
		for (int i = 0; i <= 4; i++) {
			lineArr.setCoordinate(i * 2, coor[i]); // define point pairs for each line
			lineArr.setCoordinate(i * 2 + 1, coor[(i + 2) % 5]);
			lineArr.setColor(i * 2, CommonsKS.Blue); // specify color for each pair of points
			lineArr.setColor(i * 2 + 1, CommonsKS.Green);
		}
		return new Shape3D(lineArr); // create and return a Shape3D
	}

	public Node position_Object() {
		return create_Object();
	}
}
