package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XUrlImpl implements XUrl{

    private String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
    private String shortUrl = "http://short.url/";
    private StringBuilder s = new StringBuilder(9);
    private Map<String, String> map = new HashMap<>();
    private Map<String, Integer> hitCount = new HashMap<>();

    @Override
    public String registerNewUrl(String longUrl) {
        s.setLength(0);
        if(map.containsKey(longUrl)){
            return map.get(longUrl);
        }else{
            for(int i = 0; i<9; i++){
                int ch = (int)(alphaNumericStr.length() * Math.random());
                s.append(alphaNumericStr.charAt(ch));
            }
        }
        map.put(longUrl, shortUrl+s.toString());
        hitCount.put(longUrl, 0);
        return shortUrl+s.toString();
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(map.containsValue(shortUrl)) return null;
        else{
            map.put(longUrl, shortUrl);
            hitCount.put(longUrl, 0);
        }
        return map.get(longUrl);
    }

    @Override
    public String getUrl(String shortUrl) {
        for (Entry<String, String> entry: map.entrySet())
        {
            if (shortUrl.equals(entry.getValue())) {
                hitCount.put(entry.getKey(), hitCount.getOrDefault(entry.getKey(), 0)+1);
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(hitCount.containsKey(longUrl)) return hitCount.get(longUrl);
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        map.remove(longUrl);
        return longUrl;
    }

}