package com.sinsy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Yuguii {
    private final ScriptEngine engine;

    public Yuguii() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("js");
        String script = "function TL(a) { "
                + "var k = ''; "
                + "var b = 406644; "
                + "var b1 = 3293161072; "
                + "var jd = '.'; "
                + "var $b = '+-a^+6'; "
                + "var Zb = '+-3^+b+-f'; "
                + "for (var e = [], f = 0, g = 0; g < a.length; g++) { "
                + "var m = a.charCodeAt(g); "
                + "128 > m ? e[f++] = m : (2048 > m ? e[f++] = m >> 6 | 192 : (55296 == (m & 64512) && g + 1 < a.length && 56320 == (a.charCodeAt(g + 1) & 64512) ? (m = 65536 + ((m & 1023) << 10) + (a.charCodeAt(++g) & 1023), e[f++] = m >> 18 | 240, e[f++] = m >> 12 & 63 | 128) : e[f++] = m >> 12 | 224, e[f++] = m >> 6 & 63 | 128), e[f++] = m & 63 | 128) "
                + "} "
                + "a = b; "
                + "for (f = 0; f < e.length; f++) a += e[f], a = RL(a, $b); "
                + "a = RL(a, Zb); "
                + "a ^= b1 || 0; "
                + "0 > a && (a = (a & 2147483647) + 2147483648); "
                + "a %= 1E6; "
                + "return a.toString() + jd + (a ^ b) "
                + "}; "
                + "function RL(a, b) { var t = 'a'; var Yb = '+'; for (var c = 0; c < b.length - 2; c += 3) { var d = b.charAt(c + 2), d = d >= t ? d.charCodeAt(0) - 87 : Number(d), d = b.charAt(c + 1) == Yb ? a >>> d: a << d; a = b.charAt(c) == Yb ? a + d & 4294967295 : a ^ d } return a } ";

        engine.eval(script);
    }

    public String getTk(String text) throws ScriptException {
        Object result = this.engine.eval("TL('" + text + "')");
        return result.toString();
    }

    public static void main(String[] args) throws ScriptException, IOException {
        Yuguii js = new Yuguii();

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String content = reader.readLine();

            if (content.equals("q!")) {
                break;
            }

            String tk = js.getTk(content);

            if (content.length() > 4891) {
                System.out.println("翻译文本超过限制！");
            }

            String url = "http://translate.google.cn/translate_a/single?" +
                    "client=t" +
                    "&sl=en" +
                    "&tl=zh-CN" +
                    "&hl=zh-CN" +
                    "&dt=at" +
                    "&dt=bd" +
                    "&dt=ex" +
                    "&dt=ld" +
                    "&dt=md" +
                    "&dt=qca" +
                    "&dt=rw" +
                    "&dt=rm" +
                    "&dt=ss" +
                    "&dt=t" +
                    "&ie=UTF-8" +
                    "&oe=UTF-8" +
                    "&clearbtn=1" +
                    "&otf=1" +
                    "&pc=1" +
                    "&srcrom=0" +
                    "&ssel=0" +
                    "&tsel=0" +
                    "&kc=2" +
                    "&tk=" + tk +
                    "&q=" + URLEncoder.encode(content, StandardCharsets.UTF_8);

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0");
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            int end = response.indexOf("\",");
            if (end > 4) {
                System.out.println(response.substring(4, end));
            }
        }
    }
}
