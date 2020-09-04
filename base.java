import java.util.*;

public class base
{
    public static void main(String[] args)
    {try{
        Base64.Decoder de=Base64.getDecoder();
        Base64.Encoder en=Base64.getEncoder();
        String str="{\"u\":134516484,\"p\":\"sbsjsjsusbebs\"}";
        byte[] b=str.getBytes("UTF-8");
        String ent=en.encodeToString(b);
        System.out.println(ent);
    }catch(Exception e){}}
}