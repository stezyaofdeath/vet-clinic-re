package com.addicted2u.commands;

import com.google.gson.Gson;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Command {
    Object execute();

    /*default StringEntity getJSON(Map map) throws UnsupportedEncodingException {
        return new StringEntity(new Gson().toJson(map));
    }*/
}
