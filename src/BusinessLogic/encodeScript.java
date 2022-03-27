package BusinessLogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

public class encodeScript {
	
	private ArrayList<ArrayList<String>> database = new ArrayList<ArrayList<String>>();

	private String function(int x){
        // any random function
        Integer res = x*7 + 55;
        return res.toString();
    }
	
	private int inverse_function(String x){
        // inverse of above function
        int res = (Integer.parseInt(x) - 55)/7;
        return res;
    }
	
	public String Encode(byte[] data, String User_id, String text, int increment) throws IOException{

        byte[] foo = text.getBytes();
        int j = 0;
        // too simple cypher
        // add advanced conditions if u want to
        for(int i = 0; i < data.length; i+= increment){
            if(j == text.length())
                break;
            // do not modify first 44 bytes
            if(i > 44){
                data[i] = foo[j];
                j++;
            }
        }
        int k = j;
        for(int i = 0; i < data.length; i+= increment){
            if(k == 0)
                break;
            if(i > 44){
                k--;
            }
        }
        File file2 = new File("C:\\Users\\hp\\Desktop\\INPT_2021_2022\\CyberSecurity\\project_security_v1\\audio_enc.mpeg");
        FileOutputStream out = new FileOutputStream(file2);
        PrintWriter writer = new PrintWriter(file2);
        writer.print("");
        writer.close();

        file2 = new File("C:\\Users\\hp\\Desktop\\INPT_2021_2022\\CyberSecurity\\project_security_v1\\audio_enc.mpeg");
        out = new FileOutputStream(file2);

        out.write(data);
        out.close();
        System.out.println("Encoded");
        ArrayList<String> keys = new ArrayList<String>();
        // first one stores length, 2nd one increment, 3rd one stores random_key
        String password = UUID.randomUUID().toString();
        keys.add(function(j));
        keys.add(function(increment));
        keys.add(User_id + password); // stupid, but works
        database.add(keys);
        return password;
    }
	
	public String Decode(byte[] data, String user_id, String password){
        ArrayList<Byte> val = new ArrayList<Byte>();

        // The id and password can be compared with the one in Database and next steps can be followed if they match
        // here the database is just a global arraylist :P
        String search = user_id + password;
        boolean matches = false;
        int increment = 0;
        int key_len = 0;
        for(ArrayList<String> i : database){

            if(search.equals(i.get(2))){
                matches = true;
                increment = inverse_function(i.get(1));
                key_len = inverse_function(i.get(0));
            }
        }
        if(! matches){
            return "Failed. The Username or Password is incorrect";
        }
        for(int i = 0; i < data.length; i+= increment){
            if(key_len == 0)
                break;
            if(i > 44){
                val.add(data[i]);
                key_len--;
            }
        }
        Byte[] ans = val.toArray(new Byte[val.size()]);
        byte[] res = new byte[ans.length];
        for(int i = 0; i < ans.length; i++)
            res[i] = (byte) ans[i];
        String result = new String(res);
        return result;
    }
}
