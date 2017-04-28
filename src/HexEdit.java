import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HexEdit
{
  
  public static void editFromRegistry(String in,String out) throws IOException{
    
    FileInputStream fis=new FileInputStream(in);
    FileOutputStream fout=new FileOutputStream(out);
    int index=0;
    ArrayList<Byte> data=new ArrayList<Byte>();
    int b;
    while( (b=fis.read())!= -1 ){
    
      data.add((byte)b);
      
    }
    //get rid of 0s
    ArrayList<Byte> data1=new ArrayList<Byte>();
    
    int n=data.size();
    for(int i=0;i<n;i++)
    {
      if(data.get(i) >40) data1.add(data.get(i));
    }
    
   
    
    //get rid of 2C(,) and 5C
    ArrayList<Byte> data2=new ArrayList<Byte>();
    
    for(int i=0;i<data1.size();i++){
      
      if(data1.get(i)!=44 &&data1.get(i)!=92){
        
        data2.add(data1.get(i));
      }
      
      
    }
    
    ArrayList<Byte> data3=new ArrayList<Byte>();
    for(int i=0;i<(data2.size()-1)/2;i++){
      byte a1=data2.get(2*i);
      byte a2=data2.get(2*i+1);
      
      if(a1>=48 && a1<=57) a1=(byte) (a1-48);
      if(a2>=48 && a2<=57) a2=(byte) (a2-48);
      if(a1>=97 && a1<=102) a1=(byte) (a1-97+10);
      if(a2>=97 && a2<=102) a2=(byte) (a2-97+10);
      byte c=(byte) (a1*16+a2);
      data3.add(c);
    }
    
    for(byte d:data3){
      fout.write(d);
    }
    
    printList(data1);
    printList(data2);
    printList(data3);
    
  }
  
  
  
  public static void printList(ArrayList<Byte> data){
    int i=0;
    
    for(byte b:data){
      if(i%8==0) System.out.println();
      System.out.print(b+" ");
      i++;
      if(i>80) break;
      
    }
    
   
    
    
    
    
  }
  
  

  public static void main(String[] args) throws IOException
  {
    // TODO Auto-generated method stub
    if (args[0] == null || args[1]==null)
    {

      System.out.println("you need to specify the file to do hex editing");

    }
    
    
    editFromRegistry(args[0],args[1]);
    
   
  }

}
