package deVilliers_214062813.Assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class readCSV
{
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static ArrayList<dataset> readCSVFiletoArrayList(String sFileName) throws Exception
    {
        ArrayList<dataset> listData = new ArrayList<>();
        //String scvFilePath = "Users/s214062813/Desktop/Honours/WRCV402 Evolutionary Computing and Intelligent Systems/Assignments/src"+sFileName;
        String scvFilePath = sFileName;
        BufferedReader brCSV = null;
        String sline = "";
        String scvSplitter = ",";



        try (BufferedReader br = new BufferedReader(new FileReader(scvFilePath))) {

            while ((sline = br.readLine()) != null) {

                // use comma as separator
                String[] country = sline.split(scvSplitter);
                for(String s : country)
                {
                    System.out.println(s);
                }

               // System.out.println("Arith [code= " + country[4] + " , name=" + country[5] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        return listData;
    }


    public static ArrayList<dataset> readfile(String sfile)
    {
        ArrayList<dataset> Marks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Streamdata.csv"));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                scanner = new Scanner(line);
                while (scanner.hasNext())
                {
                    String data = scanner.next();
                    String[] ArrLine = data.split(",");
                    dataset curDS = new dataset(ArrLine[0], ArrLine[1], ArrLine[2], ArrLine[3]);
                    System.out.println(curDS.toString());
                    Marks.add(curDS);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Marks;
    }

    public static ArrayList<datasetBD> readfile2(String sfile)
    {
        ArrayList<datasetBD> Marks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Streamdata.csv"));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                scanner = new Scanner(line);
                while (scanner.hasNext())
                {
                    String data = scanner.next();
                    String[] ArrLine = data.split(",");
                    datasetBD curDS = new datasetBD(ArrLine[0], ArrLine[1], ArrLine[2], ArrLine[3]);
                    System.out.println(curDS.toString());
                    Marks.add(curDS);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Marks;
    }

    public static void writeCsvFile(String fileName, Experiment e1) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName, true);
            //fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(e1.print());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}
