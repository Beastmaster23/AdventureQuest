package application.models.utils;


/**
 * Vector makes it easier to do vector calculations
 * @author Interco
 */
public class Vector {
	

	private double x, y, z;
	
	public Vector() {
		setX(0);
		setY(0);
		setZ(0);
	}
	
	public Vector(double allValues) {
		setX(allValues);
		setY(allValues);
		setZ(allValues);
	}
	
	public Vector(double x, double y) {
		setX(x);
		setY(y);
		setZ(0);
	}
	
	public Vector(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		 
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		 
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
		
	}
	
	public void addVector(Vector vector) {
		this.x+=vector.x;
		this.y+=vector.y;
		this.z+=vector.z;
	}
	
	public void subVector(Vector vector) {
		this.x-=vector.x;
		this.y-=vector.y;
		this.z-=vector.z;
		 
	}
	
	public void multVector(Vector vector) {
		this.x*=vector.x;
		this.y*=vector.y;
		this.z*=vector.z;
		 
	}
	
	public void multVector(double value) {
		this.x*=value;
		this.y*=value;
		this.z*=value;
	}
	
	public void divVector(Vector vector) {
		if(vector.getX()!=0)
		this.x/=vector.x;
		if(vector.getY()!=0)
		this.y/=vector.y;
		if(vector.getZ()!=0)
		this.z/=vector.z;
	}
	
	public void divVector(double val) {
		if(val!=0) {
			this.x/=val;
			this.y/=val;
			this.z/=val;
		}
	}
	
	public void constrict(Vector maxVector) {
		if(this.x>maxVector.getX()) {
			this.x=maxVector.getX();
		}
		if(this.y>maxVector.getY()) {
			this.y=maxVector.getY();
		}
		if(this.z>maxVector.getZ()) {
			this.z=maxVector.getZ();
		}
		
		if(this.x<-maxVector.getX()) {
			this.x=-maxVector.getX();
		}
		if(this.y<-maxVector.getY()) {
			this.y=-maxVector.getY();
		}
		if(this.z<-maxVector.getZ()) {
			this.z=-maxVector.getZ();
		}
	}
	
	/**
	 * Adds 2 Vectors ie.
	 * vectorA + vectorB
	 * @param vectorA 
	 * @param vectorB
	 * @return new Vector instance
	 */
	public static Vector add(Vector vectorA, Vector vectorB) {
		Vector out=new Vector();
		out.x=vectorA.x+vectorB.x;
		out.y=vectorA.y+vectorB.y;
		out.z=vectorA.z+vectorB.z;
		 
		return out;
	}
	/**
	 * Subtracts vectorA from vectorB ie.
	 * vectorA-vectorB
	 * @param vectorA 
	 * @param vectorB
	 * @return new Vector instance
	 */
	public static Vector sub(Vector vectorA, Vector vectorB) {
		Vector out=new Vector();
		out.x=vectorA.x-vectorB.x;
		out.y=vectorA.y-vectorB.y;
		out.z=vectorA.z-vectorB.z;
		 
		return out;
	}
	/**
	 * Multiplies vectorA from vectorB ie.
	 * vectorA*vectorB
	 * @param vectorA 
	 * @param vectorB
	 * @return new Vector instance
	 */
	public static Vector mult(Vector vectorA, Vector vectorB) {
		Vector out=new Vector();
		out.x=vectorA.x*vectorB.x;
		out.y=vectorA.y*vectorB.y;
		out.z=vectorA.z*vectorB.z;
		 
		return out;
	}
	/**
	 * Divides vectorA from vectorB ie.
	 * vectorA/vectorB
	 * @param vectorA 
	 * @param vectorB
	 * @return new Vector instance
	 */
	public static Vector div(Vector vectorA, Vector vectorB) {
		Vector out=new Vector();
		if(vectorB.x!=0)
		out.x=vectorA.x/vectorB.x;
		if(vectorB.y!=0)
		out.y=vectorA.y/vectorB.y;
		if(vectorB.z!=0)
		out.z=vectorA.z/vectorB.z;
		 
		return out;
	}
	/**
	 * Calculates the magnitude of this vector. It's the length.
	 * @return Magnitude
	 */
	public double getMagnitude() {
		return Math.sqrt(x*x+y*y+z*z);
	}
	/**
	 * Sets the magnitude of this vector if it is not zero. 
	 * @param magnitude
	 */
	public void setMagnitude(double magnitude) {
		if(getMagnitude()==0)
			return ;
		double ratio=magnitude/getMagnitude();
		this.x*=ratio;
		this.y*=ratio;
		this.z*=ratio;
	}
	/**
	 * Calculates the distance between vectorA and vectorB
	 * @param distance
	 */
	public static double calculateDistance(Vector vectorA, Vector vectorB) {
		return Math.sqrt(Math.pow(vectorB.getX()-vectorA.x, 2)+Math.pow(vectorB.getY()-vectorA.y, 2)+Math.pow(vectorB.getZ()-vectorA.z, 2));
	}
	/**
	 * Normalizes this vector if it is not zero.
	 */
	public void normalize() {
		double mag=getMagnitude();
		divVector(mag);
	}
	
	public double getDirection() {
		return Math.atan2(getY(), getX());
	}
	
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", z=" + z + ", magnitude=" + getMagnitude() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
}
