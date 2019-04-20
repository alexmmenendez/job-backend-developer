package br.com.jobbackenddeveloper.jobbackenddeveloper.output.wrapper;

import com.google.gson.Gson;

public abstract class Wrapper {

    public String toJson() {
        return new Gson().toJson(this);
    }

}