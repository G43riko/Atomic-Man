package Atomic.util;

public class Vector {
	private float x,y;
	
	public Vector(){
		this(0,0);
	};
	
	public Vector(float x, float y){
		this.x=x;
		this.y=y;
	};
	
	public Vector(Vector v){
		this.x = v.getX();
		this.y = v.getY();
	};
	
	public float dot(Vector v){
        return x * v.getX() + y * v.getY();
    };
    
	public float getLength(){
		return (float)Math.sqrt(this.x*this.x+this.y*this.y);
	};
	
	public float max(){
		return Math.max(this.x,this.y);
	};
	
	public float min(){
		return Math.min(this.x,this.y);
	};
	
	public void normalize(){
		float dlzka = this.getLength();
		this.x /= dlzka;
		this.y /= dlzka;
	}
	
	public Vector Normalized(){
		float dlzka = this.getLength();
		return new Vector(x/dlzka, y/dlzka);
	}
	
	public float cross(Vector r){
		return x * r.getY() - y * r.getX();
	}
	
	public void rotate(float angle){
		float rad=(float)Math.toRadians(angle);

		float cos=(float)Math.cos(rad);
		float sin=(float)Math.sin(rad);
		this.x = (x * cos - y * sin);
		this.y = (x * sin + y * cos);
	}
	
	public float dist(Vector v){
		//return distance between 2 point
		float dx = x - v.x;
		float dy = y - v.y;
		return (float)Math.sqrt(dx * dx + dy * dy);
	}
	
	public Vector Lerp(Vector dest, float lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}
	
	public float distSQ(Vector v) {
		float distX = x - v.x;
		float distY = y - v.y;
		return distX * distX + distY * distY;
	}
	
	public void negate(){
		this.x *= -1;
		this.y *= -1;
	}
	
	public float angleBetween(Vector v) {
		float dotProduct = dot(v);
		float angle = (float)Math.acos(dotProduct);
		return angle;
	}
	
	public Vector add(Vector v){
		return new Vector(x + v.getX(), y + v.getY());
	};
	
	public Vector add(float num){
		return new Vector(x + num, y + num);
	};
	
	public Vector sub(Vector v){
		return new Vector(x - v.getX(), y - v.getY());
	};
	
	public Vector sub(float num){
		return new Vector(x - num, y - num);
	}
	
	public Vector mul(Vector v){
		return new Vector(x * v.getX(), y * v.getY());
	};
	
	public Vector mul(float num){
		return new Vector(x * num, y * num);
	}
	
	public Vector div (Vector v){
		return new Vector(x / v.getX(), y / v.getY());
	};
	
	public Vector div (float num){
		return new Vector(x / num, y / num);
	};
	
	public Vector abs(){
		return new Vector(Math.abs(x), Math.abs(y));
	}

	public float getX() {return x;}
	public float getY() {return y;}

	public int getXi() {return (int)x;}
	public int getYi() {return (int)y;}
	
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	
	public void addToY(float y){this.y += y;}
	public void addToX(float x){this.x += x;}
	
	public void set(float x, float y){this.x = x;this.y = y;}
	
	public void set(Vector a){set(a.getX(), a.getY());}
	
	public String toString(){
		return "["+this.x+"x"+this.y+"]";
	}
	
	public Vector getInstatnce(){
		return new Vector(this);
	}
	
	public boolean equals(Vector v){
		return x == v.getX() && y == v.getY();
	}

	public String toDecimal(int i) {
		return "["+String.format("%0"+i+"d ",(int)x)+"x"+String.format("%0"+i+"d ",(int)y)+"]";
	};
}
