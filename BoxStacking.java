import java.util.Arrays;
import java.util.Scanner;


class Box implements Comparable<Box>{
	//1.Define a box which has its own length, breadth and height.
	//  Both area and height of the upper placed box should be smaller than area and height of the lower box respectively.
	//  We define area=length*breadth.
	int length, breadth, height, area;
	public Box(int x, int y, int z){
	    length = x;
	    breadth = y;
	    height = z;
	    area = length*breadth;
	}
	//  We should be sure that box is sorted in increasing order.
	public int compareTo(Box b){
	    return this.area - b.area;
	}
}

public class BoxStacking{
	public static void main(String[] args) {
	 //1.First input how many boxes you have
	 //Next input the length, breadth and height of the box
	    int n = 900;
	    //2.Get the dimensions of the box.
	    //  We can rotate the box.
	    //  Generate all 3 rotations of all boxes. The size of rotation array becomes 3 times the size of original array.
	    Box box[] = new Box[3*n];
	    int optHeight[] = new int[3*n];
	    for(int i = 0;i < n;i++){
	        int x = (int) (Math.random()*100), y = (int) (Math.random()*100), z = (int) (Math.random()*100);
	        box[3*i] = new Box(x, y, z);
	        box[3*i + 1] = new Box(y, z, x);
	        box[3*i + 2] = new Box(x, z, y);
	    }
	    //3.Sort the above generated 3n boxes in increasing order of base area.
	    Arrays.sort(box);
	    //4.After sorting the boxes, the problem is same as LIS with following optimal substructure property.
	    //  If the earlier box has smaller area and the height till that box is the greatest then we'll consider that stack.
	    //optHeight[i] means maximum possible Stack Height with box i at bottom of stack.
	    /*
	        optHeight[i] = { Max ( optHeight[j] ) + box[i].height } where j < i and length[j] < length[i] and breath[j] < breath[i].
	        If there is no such j then optHeight(i) = box[i].height
	    */
	    optHeight[0] = box[0].height;
	    int ans = optHeight[0];
	    long start=System.nanoTime();
	    for(int i = 1; i < 3*n; i++)
	    {
	        int maxHeight = 0;
	        for(int j = i-1; j >= 0; j--)
	        {
	            if(box[j].length < box[i].length && box[j].breadth < box[i].breadth && optHeight[j] > maxHeight)
	                maxHeight = optHeight[j];
	        }
	        optHeight[i] = maxHeight + box[i].height;
	        ans = Math.max(ans, optHeight[i]);
	    }
	    long end=System.nanoTime();
	    long time=end-start;
		System.out.println("execution time is:"+time+" ns");
	    System.out.println(ans);
	}
}

