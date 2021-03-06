package solution;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author Lavender
 *
 */
public class Q6 {
	
	public static int maxY(ArrayList<Point> points) {
		int size = points.size();
		int max = points.get(0).y;
		int i;
		
		for(i = 0; i<size;i++) {
			if(points.get(i).y > max) {
				max = points.get(i).y;
			}
		}
		return max;
	}
	
	
	public static int maxX(ArrayList<Point> points) {
		int size = points.size();
		int max = points.get(0).x;
		int i;
		
		for(i = 0; i<size;i++) {
			if(points.get(i).x > max) {
				max = points.get(i).x;
			}
		}
		return max;
	}
	
	public static int minY(ArrayList<Point> points) {
		int size = points.size();
		int min = points.get(0).y;
		int i;
		
		for(i = 0; i<size;i++) {
			if(points.get(i).y < min) {
				min = points.get(i).y;
			}
		}
		return min;
	}
	
	
	public static int minX(ArrayList<Point> points) {
		int size = points.size();
		int min = points.get(0).x;
		int i;
		
		for(i = 0; i<size;i++) {
			if(points.get(i).x < min) {
				min = points.get(i).x;
			}
		}
		return min;
	}
	
	/**
	 * Calculate the slope of two points
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double Slope(Point p1,Point p2) {
		//make the difference double
		double slope = (double)(p1.y-p2.y)/(p1.x-p2.x);
		return slope;
	}
	
	
	/**
	 * Check whether the point is inside the polygon
	 * @param polygon
	 * @param test
	 * @return
	 */
	public static String InPoly(ArrayList<Point> polygon, Point test) {
		String Y = "inside";
		String N = "outside";
		int size = polygon.size();
		int i;
		int intersect = 0;
		
		//Point is outside the polygon
		if(test.x < minX(polygon) || test.x > maxX(polygon)||test.y < minY(polygon) || test.y > maxY(polygon)) {
			return N;
		}
		
		//Point is on the polygon point
		for(i = 0; i<size;i++) {
			if(test == polygon.get(i)) {
				return Y;
			}
		}
		
		//Point is on the edge of the polygon
		for(i = 0; i<size-1;i++) {
			//if i = size, make the polygon close
			int j = i+1;
			if(i == size) {
				j = 0;
			}
			
			//the point is between the edge
			//xmin <= test.x <= xmax
			if(test.x >= Math.min(polygon.get(i).x,polygon.get(j).x)&& test.x <= Math.max(polygon.get(i).x,polygon.get(j).x)) {
				//vertical, x is the same
				if(polygon.get(i).x == polygon.get(j).x) {
					//test is on the vertical edge
					//ymin <= test.y <= ymax
					if(test.y >= Math.min(polygon.get(i).y,polygon.get(j).y)&& test.y <= Math.max(polygon.get(i).y,polygon.get(j).y)) {
						return Y;
					}
				}
				//horizontal, y is the same
				else if(polygon.get(i).y == polygon.get(j).y) {
					//test is on the horizontal edge
					// y is the same
					if(test.y == polygon.get(i).y) {
						return Y;
					}
				}
				// not vertical not horizontal
				else {
					//the divided value cannot be 0
					if(test.x !=polygon.get(i).x && test.x !=polygon.get(j).x) {
						if(Slope(test,polygon.get(i)) == Slope(test,polygon.get(j))) {
							return Y;
						}
					}
				}
				
			}
		}	
		
		//Count intersects
		for(i = 0; i<size-1;i++) {
			double y = test.y;
			//if i = size, make the polygon close
			int j = i+1;
			if(i == 3) {
				j = 0;
			}
			
			//xmin<test.x<xmax
			if(test.x >= Math.min(polygon.get(i).x,polygon.get(j).x)&& test.x <= Math.max(polygon.get(i).x,polygon.get(j).x)) {
				if(y < Slope(polygon.get(j),polygon.get(i))*(test.x - polygon.get(i).x) +polygon.get(i).y) {
					//count intersects
					intersect++;
				}
			}
		}
		//if the intersect is odd, the point is inside; otherwise, outside.
		if(intersect %2 == 0) {
			return N;
		}else {
			return Y;
		}
	}

	public static void main(String[] args) {
		/*polygon
		  	4 3
			2 6
			3 12
			2 17
			5 20
			9 21
			14 19
			20 14
			18 3
			11 7
		 */
		Point point1=new Point(4,3);
		Point point2=new Point(2,6);
		Point point3=new Point(3,12);
		Point point4=new Point(2,17);
		Point point5=new Point(5,20);
		Point point6=new Point(9,21);
		Point point7=new Point(14,19);
		Point point8=new Point(20,14);
		Point point9=new Point(18,3);
		Point point10=new Point(11,7);
		
		/*test point
		  	7 11 in
			10 14
			11 4
			12 21
			16 3
			16 10
			17 4
			18 7
			18 17
			20 7
		 */
		Point test1 = new Point(7,11);
		Point test2 = new Point(10,14);
		Point test3 = new Point(11,4);
		Point test4 = new Point(12,21);
		Point test5 = new Point(16,3);
		Point test6 = new Point(16,10);
		Point test7 = new Point(17,4);
		Point test8 = new Point(18,7);
		Point test9 = new Point(18,17);
		Point test10 = new Point(20,7);
		
		int i;
		//ArrayList of polygon
		ArrayList<Point> polygon = new ArrayList<Point>();
		polygon.add(point1);
		polygon.add(point2);
		polygon.add(point3);
		polygon.add(point4);
		polygon.add(point5);
		polygon.add(point6);
		polygon.add(point7);
		polygon.add(point8);
		polygon.add(point9);
		polygon.add(point10);
		
		//ArrayList of polygon
		ArrayList<Point> test = new ArrayList<Point>();
		test.add(test1);
		test.add(test2);
		test.add(test3);
		test.add(test4);
		test.add(test5);
		test.add(test6);
		test.add(test7);
		test.add(test8);
		test.add(test9);
		test.add(test10);
		
		for(i=0; i< test.size();i++) {
			System.out.println(test.get(i).x+" "+test.get(i).y+ " " +InPoly(polygon,test.get(i)));
		}

	}

}
