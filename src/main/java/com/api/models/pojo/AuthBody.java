package com.api.models.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder (setterPrefix = "set")
public class AuthBody {

    private String username;
    private String password;
}
