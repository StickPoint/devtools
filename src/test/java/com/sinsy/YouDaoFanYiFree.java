package com.sinsy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class YouDaoFanYiFree {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入需要翻译的内容：");
        String content = reader.readLine();

        String url = "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&sessionFrom=https://www.baidu.com/link";
        String data = "from=AUTO&to=AUTO&smartresult=dict&client=fanyideskweb&salt=1500092479607&sign=c98235a85b213d482b8e65f6b1065e26&doctype=json&version=2.1&keyfrom=fanyi.web&action=FY_BY_CL1CKBUTTON&typoResult=true&i=" + URLEncoder.encode(content, "UTF-8");

        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        connection.setRequestProperty("Referer", "http://fanyi.youdao.com/");
        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        connection.setDoOutput(true);
        connection.getOutputStream().write(data.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        System.out.println(response);
    }
}
