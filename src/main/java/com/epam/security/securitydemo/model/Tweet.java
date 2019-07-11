package com.epam.security.securitydemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    private String id;
    private String owner;
    @Max(240)
    private String text;
    private Date createdAt;
}
