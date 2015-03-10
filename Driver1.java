/* Jamey Dogom
   Comp. 282
   Project 1 */   
   
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver1 {
   static ArrayList<Point> points = new ArrayList<Point>();
   public static void main(String args[])
   {
      String inputfile="input.txt";
      int p1=0,p2=0,n=0;
      Scanner scan = new Scanner(System.in);
   	
      System.out.println("Sample interaction:\nWelcome to Project 1: Boundries \nLoading points from "+inputfile +" The points on the convex hull are:");
   	
      try
      {
         File file = new File(inputfile);
         FileReader fileReader = new FileReader(file);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         String line;	
         String title = "";	     
         while ((line = bufferedReader.readLine()) != null)
         {
            String[] temp = line.split(" ");
            p1 = Integer.parseInt(temp[0]);
            p2 = Integer.parseInt(temp[1]);
           
            n++;
           
            points.add(new Point(p1,p2));
         }
         fileReader.close();
      
      }
      catch (IOException e)
      {
         System.out.println("could not find file");
      }
      convexhull();
        
      while(true)
      {
         System.out.println("Enter Test Points:");
         String input = scan.nextLine();
         if(input.equalsIgnoreCase("quit"))
         {
         
            System.exit(1);
         }
         String[] ip = input.split(" ");
         p1 = Integer.parseInt(ip[0]);
         p2 = Integer.parseInt(ip[1]);
         boolean find = false;	   
       
         if((p1+p2)%2!=0)
         {
            find = true;
            System.out.println("Inside");	    		
         }
       
         if(find == false)
         {
            System.out.println("Outside");
         }
       
      }
       
   }
   public static void convexhull()
   {
   	
      if (points.size() < 3) 
         return;
   
     
      int next[] = new int[points.size()];
      for (int i = 0; i < points.size(); i++)
         next[i] = -1;
   
       
      int l = 0;
      for (int i = 1; i < points.size(); i++)
         if (points.get(i).x < points.get(l).x)
            l = i;
   
      int p = l, q;
      do
      {
          
         q = (p+1)%points.size();
         for (int i = 0; i < points.size(); i++)
            if (orientation(points.get(p), points.get(i), points.get(q)) == 2)
               q = i;
      
         next[p] = q;  
         p = q; 
      } while (p != l);
   
      
      for (int i = 0; i < points.size(); i++)
      {
         if (next[i] != -1)
            System.out.print( "("+ points.get(i).x +", " + points.get(i).y +")\n");
      }
   }
   static int orientation(Point p, Point q, Point r)
   {
      int val = (q.y - p.y) * (r.x - q.x) -
                 (q.x - p.x) * (r.y - q.y);
   
      if (val == 0) 
         return 0;  
      return (val > 0)? 1: 2; 
   }

}

