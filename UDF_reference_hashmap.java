package cmb.udf;

/**
 * Created by Pietro.Speri on 14/07/2017.
 */

import java.io.*;
import java.util.HashMap;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import static java.lang.String.valueOf;

/**
 * Created by Pietro Speri. Description: It returns true if the first
 * string is matching one element present in the file located in the path
 * specified as second argument.
 */

@Description(name = "UDF_reference", value = "_FUNC_(string,string) - Return true "
        + "if the first string is matching one element present in a file as second argument.")
public class UDF_reference extends UDF {
    protected HashMap<String, String> hshmp;

    public boolean evaluate(final  String element, final File file) throws HiveException {
        if (element == null) {
            return false;
        }
        if (hshmp==null) {
            hashmap(file);
        }
        if(hshmp.containsKey(element)){
            return true;
        } else {
            return false;
        }
    }

    private void hashmap(final File file) throws HiveException {
        hshmp = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String l;
            while ((l = br.readLine()) != null) {
                hshmp.put(valueOf(l.trim()), "1");
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new HiveException("THE FILE " + file + " DOESN'T EXIST");
        } catch (IOException e) {
            throw new HiveException("PROCESS FILE " + file + " FAILED, PLEASE CHECK FORMAT");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            System.out.println("STACKTRACE: ");
            e.printStackTrace();
        }
    }
}

