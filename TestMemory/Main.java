import com.google.common.hash.Hashing;

/**
 *
 * @author entropyfeng
 * @date 2019/12/24 16:00
 * Warning:--------------------
 * the ceiling of the array is 2^32-3
 * so i divide 2^32 ints into 3 arrays
 * index of arr1 ->[0,2^31-4]
 * index of arr2 ->[2^31-3,2^32-7]
 * index of arr3 ->[2^32-6,2^32-1]
 * and you should use jvm parameters-->
 * -XX:+UseG1GC -Xms30g -Xmx30g
 *
 */
public class Main {

    public static int[]arr1=new int[Integer.MAX_VALUE-2];
    public static int[]arr2=new int[Integer.MAX_VALUE-2];
    public static int[]arr3=new int[6];
    public static void posTransform(int i){
        long pos=((long)i)+Integer.MAX_VALUE+1;
        int pos1=0;
        int pos2=0;
        int pos3=0;
        if(pos<=(long)Math.pow(2,31)-4){
            pos1=(int)pos;
            arr1[pos1]++;
        }else if(pos<=(long)Math.pow(2,32)-7){
            pos2=(int)(pos-(long)(Math.pow(2,31)-3));
            arr2[pos2]++;
        }else {
            pos3=(int)(pos-(long)(Math.pow(2,32)-6));
            arr3[pos3]++;
        }


    }
    public static void checkResult(){
        System.out.println("begin check result");
        for (int i=0;i<arr1.length;i++){
            if(arr1[i]==0){
                System.out.println("lack arr1["+i+"]");
            }if(arr1[i]>1){
                System.out.println("arr1["+i+"]"+"repeat "+arr1[i]+" times");
            }
        }
        for (int i=0;i<arr2.length;i++){
            if(arr2[i]==0){
                System.out.println("lack arr2["+i+"]");
            }if(arr2[i]>1){
                System.out.println("arr2["+i+"]"+"repeat "+arr2[i]+" times");
            }
        }
        for (int i=0;i<arr3.length;i++){
            if(arr3[i]==0){
                System.out.println("lack arr3["+i+"]");
            }if(arr3[i]>1){
                System.out.println("arr3["+i+"]"+"repeat "+arr3[i]+" times");
            }
        }
        System.out.println("!--------------not exists repeat number !");
    }

    public static void main(String[] args) {


        for (long i=Integer.MIN_VALUE;i<=Integer.MAX_VALUE;i++){
            int pos= Hashing.murmur3_32().hashInt((int)i).asInt();
            posTransform(pos);
        }
    checkResult();



    }
}
