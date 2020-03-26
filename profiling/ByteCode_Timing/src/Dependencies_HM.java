package ByteCode_Timing;

import java.lang.String;
import java.util.HashMap;
import java.util.ArrayList;

public class Dependencies_HM {
    private final HashMap<String, ArrayList<String>> dhm ;
    public Dependencies_HM() {
        dhm = new HashMap<String, ArrayList<String>>();

        /* Initialize buckets */

        for(int i = 0; i < ByteCode_Timing.ByteCodes_Array.Interesting_ByteCodes.length; i++) {
            dhm.put(ByteCode_Timing.ByteCodes_Array.Interesting_ByteCodes[i], new ArrayList<String>());
        }        

        /* dhm.put("EmptyLoop", new ArrayList<String>()); */
        
        String[] dep_array = new String[]{"astore_2"};
        add_to_hashmap("aconst_null", dep_array);

        dhm.get("aconst_null").add("EmptyLoop");
        dhm.get("aconst_null").add("astore_2");

        dhm.get("aload_1").add("astore_1");

        dhm.get("aload_2").add("astore_2");

        dhm.get("anewarray").add("iload");
        dhm.get("anewarray").add("astore_2");

        /* areturn */
        /* relies on invokestatic */

        /* 
        dhm.get("arraylength").add("aload_3");
        dhm.get("arraylength").add("istore");
        */

        /* athrow */
        /* relies on invokespecial */

        /*
        dhm.get("baload").add("aload_2");
        dhm.get("baload").add("iconst_0");
        dhm.get("baload").add("istore_3");

        dhm.get("bastore").add("aload_2");
        dhm.get("bastore").add("iconst_0");
        dhm.get("bastore").add("iload_3");

        dhm.get("bipush").add("istore_3");
    
        dhm.get("caload").add("aload_3");
        dhm.get("caload").add("iconst_0");
        dhm.get("caload").add("istore"); 

        dhm.get("castore").add("aload_3");
        dhm.get("castore").add("iconst_0");
        dhm.get("castore").add("iload");

        dhm.get("d2f").add("dload_3");
        dhm.get("d2f").add("fstore");
           
        dhm.get("d2i").add("dload_3");
        dhm.get("d2i").add("istore");

        dhm.get("d2l").add("dload_3");
        dhm.get("d2l").add("lstore");
        */
    }

    public void add_to_hashmap(String key, String[] dependencies) {
        for(int i = 0; i < dependencies.length; i++){
            dhm.get(key).add(dependencies[i]);
        }
    }
}
