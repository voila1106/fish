import java.util.*;
import java.net.*;
import java.io.*;
import java.math.*;

public class fish {
    static String addr = "http://nvd.hw5c77k.cn/user.php?";
    //static String addr = "http://129.28.68.123/?";
    static int count = 0;

    public static void main(String[] args) throws Exception {
        // 构建链接
        StringBuilder sb2 = new StringBuilder(addr);
        sb2.append("web=").append(System.currentTimeMillis() + "");
        StringBuilder sb3 = new StringBuilder("{\"u\":");
        // 账号
        sb3.append((long) (Math.random() * 10000000000l)).append(",\"p\":\"");
        // 密码
        for (int i = 0; i < 10; i++) {
            sb3.append((char) (random(0x7a - 0x30) + 0x30));
        }
        sb3.append("\"}");
        // System.out.println(sb3.toString());
        // bse64
        sb2.append("&token=").append(en(sb3.toString()));
        // System.out.println(sb2.toString());
        // 构建完成

        // 请求
        URL u = new URL(sb2.toString());
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Linux; Android 10; Redmi K30 Pro Build/QKQ1.191117.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/81.0.4044.138 Mobile Safari/537.36");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        con.disconnect();
        System.out.println(sb.toString());
        Thread th = new Thread() {
            @Override
            public void run() {
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            URL u = new URL(sb2.toString());
                            HttpURLConnection con = (HttpURLConnection) u.openConnection();
                            con.setDoOutput(true);
                            con.setRequestMethod("GET");
                            con.setRequestProperty("User-Agent",
                                    "Mozilla/5.0 (Linux; Android 10; Redmi K30 Pro Build/QKQ1.191117.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/81.0.4044.138 Mobile Safari/537.36");
                            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                            
                            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(con.getInputStream(), "utf-8"));
                            String line = null;
                            StringBuilder sb = new StringBuilder();
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                            
                            con.disconnect();
                            System.out.println(++count);
                            //count++;
                        } catch (Exception e) {
                        }
                    }
                };
                Timer t = new Timer();
                t.schedule(tt, 0, 10);
            }
        };
        //线程数
        int ths=50;
        for(int i=0;i<ths;i++)
        {
            th.run();
        }
    }

    /* base64编码 */
    private static String en(String str) {
        try {
            Base64.Encoder en = Base64.getEncoder();
            // String str="{\"u\":134516484,\"p\":\"sbsjsjsusbebs\"}";
            byte[] b = str.getBytes("UTF-8");
            String ent = en.encodeToString(b);
            // System.out.println(ent);
            return ent;
        } catch (Exception e) {
            return "";
        }
    }

    // 随机数
    private static int random(int max) {
        Random random = new Random();
        int s = random.nextInt(max);
        return s;
    }
}