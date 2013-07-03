/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamientoalgoritmo;

/**
 *
 * @author oky
 */
public class OrdenamientoAlgoritmo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num1, num2, num3, num4, num5, temporal;
        num1 = 5;
        num2 = 4;
        num3 = 3;
        num4 = 2;
        num5 = 1;
      

        if (num1>num5) {
          temporal = num1;
          num1=num5;
          num5=temporal;
        
           
        }
            
        
        if (num2 > num5) {
            temporal = num2;

        }
        if (num3 > num5) {
            temporal = num3;

        }
        if (num4 > num5) {
            temporal = num4;
        }

        System.out.println("" + num1 + num2 + num3 + num4 + num5);
    }
}
