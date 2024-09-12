import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;

public class Program {

    public static void main(String[] args){
        int a[]={1,2,3,4,5,6,8};
        int b[]={1,2,3,4,5,6,9,0};
        ArrayOutput.print(a);
        ArrayOutput.print(b);

        int c[] = new int[a.length+b.length];
        c=ArrayHandler.merge(a,b);
        ArrayOutput.print(c);
        ArrayHandler.sort(c);
        ArrayOutput.print(c);


        ArrayOutput.write(c,"output.txt");

    }
    
}