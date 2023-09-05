import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main (String[] args) {
        ArrayList<String> productMark = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        String Iden = "";
        String label = "";
        String detail = "";

        double price = 0;
        String ProductRecord = "";

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean finished = false;
        do {
            Iden = SafeInput.getNonZeroLenString(in, "Enter the ID number of the product");
            label = SafeInput.getNonZeroLenString(in, "Enter the name of the product");
            detail = SafeInput.getNonZeroLenString(in, "Enter the detail description about the product");
            price = SafeInput.getDouble(in, "Enter the cost to purchase the product");

            ProductRecord = Iden + ", " + label + ", " + detail + ", " + price;
            productMark.add(ProductRecord);

            finished = SafeInput.getYNConfirm(in, "Are you finished with entering your data on product?");

        }while(!finished);

        for (String p: productMark) {
            System.out.println(p);
        }
        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec: productMark) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Finished writing data file!");
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
}
