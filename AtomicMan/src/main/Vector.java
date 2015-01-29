package main;

public class Vector {
	private float x;
	private float y;
	
	public Vector(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector v){
		x += v.getX();
		y += v.getY();
	}
	
	public Vector mul(float v){
		return new Vector(x * v, y * v);
	}
	
	public void mul(Vector v){
		x *= v.getX();
		y *= v.getY();
	}
	
	public float getX() {
		return x;
	}
	public int getXi() {
		return (int)x;
	}
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	public int getYi() {
		return (int)y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
