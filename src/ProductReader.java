import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String str = "";
        ArrayList<String> product = new ArrayList<>();

        final int FIELD_LENGTH = 5;

        String idenification, Name, Description;
        double cost = 0;


        try {

            System.out.printf(String.format("%-10s %-15s %-15s %-9s ", "ID#", "Product Name", "Description", "Cost"));
            System.out.print("\n=========================================================================");
            String[] bounds;
            for (String amazon : product)
            {
                bounds = amazon.split(",");
                if(bounds.length == FIELD_LENGTH) {

                    idenification= bounds[0].trim();
                    Name = bounds[1].trim();
                    Description= bounds[2].trim();
                    cost= Double.parseDouble(bounds[4].trim());
                    String.format("%-15s %-15s %-15s %-15s", idenification, Name, Description, cost);

                    System.out.println();

                }
                else
                {
                    System.out.println("Corrupt file straight ahead: ");
                    System.out.println();
                }

            }
            System.out.println();

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.println(rec);
                }
                reader.close();
                System.out.println(rec);

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    private Formatter output;


}
