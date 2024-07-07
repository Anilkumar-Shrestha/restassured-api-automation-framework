package com.api.models;

import lombok.Data;
import lombok.Getter;

@Data
public final  class BaseSetup {

    protected String Environment;
    protected String remoteRun;
    protected String remoteUrl;
    protected String  AppApiUrl;
    protected String  ApiVersion;




    public String toString() {
        return "Environment: '" + this.Environment +
                "', AppApiUrl: '" + this.AppApiUrl +
                "', ApiVersion: '" + this.ApiVersion + "'";
    }
}
