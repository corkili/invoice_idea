package org.invoice.ocr;
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.deploy.util.StringUtils;
import com.sun.xml.internal.stream.Entity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OCR {
    public String ImageRecognition(String imgPath) {
        HttpClient httpclient = HttpClients.createDefault();

        StringBuilder stringBuilder = new StringBuilder();

        try {
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/vision/v1.0/ocr");

            builder.setParameter("language", "unk");
            builder.setParameter("detectOrientation ", "true");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "8ab7b6b0350040e99954ef3c4b588264");

            FileEntity reqEntity = new FileEntity(new File(imgPath));
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();



            if (entity != null) {
                //System.out.println(EntityUtils.toString(entity));//输出json

                JSONObject jsonObject = JSONObject.fromObject(EntityUtils.toString(entity));
                StringBuilder result = new StringBuilder();
                String regions = jsonObject.get("regions").toString();
                //System.out.println(regions);
                JSONArray regionsarray = jsonObject.getJSONArray("regions");
                for (int i = 0; i < regionsarray.size(); i++) {
                    Object lineobject = regionsarray.get(i);
                    JSONObject jsonlines = JSONObject.fromObject(lineobject);
                    JSONArray linesarray = jsonlines.getJSONArray("lines");
                    for (int j = 0; j < linesarray.size(); j++) {
                        Object wordsobject = linesarray.get(j);
                        JSONObject jsonwords = JSONObject.fromObject(wordsobject);
                        JSONArray wordsarray = jsonwords.getJSONArray("words");
                        for (int k = 0; k < wordsarray.size(); k++) {
                            JSONObject text = wordsarray.getJSONObject(k);
                            String result1 = text.getString("text");

                            stringBuilder.append(result1);
                            System.out.print("" + result1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }

}


