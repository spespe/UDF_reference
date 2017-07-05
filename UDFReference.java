package pdp.udf.reference;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Created by Pietro Speri. Description: Return true if the first
 * string is matching one element present in the file located in the path
 * specified as second argument.
 */

@Description(name = "udfreference", value = "_FUNC_(string,string) - Return true "
        + "if the first string is matching one element present in the file located in the path specified as second argument.")
public class UDFReference extends UDF {
    public boolean evaluate(final String element, final String file) {
        if (element == null) {
            return false;
        }
        return reference(element, file);
    }

    private static boolean reference(final String element, final String file) {
        boolean value = false;
        try {
            FileSystem fs = FileSystem.get(new Configuration());
            if (!fs.exists(new Path(file))) {
                System.out.println("PATH: " + file);
                System.err.println("ATTENTION, THE FILE SPECIFIED DOES NOT EXIST!");
            }
            FSDataInputStream in = fs.open(new Path(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(element)) {
                    value = true;
                }
            }

            br.close();
            // Catching exception
        } catch (Exception e) {

            System.out.println("ERROR READING THE FILE: " + file + " EXCEPTION: " + e.getMessage());
        }
        return value;
    }
}
