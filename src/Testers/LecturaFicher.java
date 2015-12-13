package Testers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Maria on 14/11/2015.
 */
public class LecturaFicher
{

    public static void main(String[] args)
    {
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/Maria/Desktop/prueba_lectura.txt")))
        {

            String sCurrentLine;
            try
            {
                while ((sCurrentLine = br.readLine()) != null)
                {
                    System.out.println(sCurrentLine);
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
