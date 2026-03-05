package com.automation.booking.model.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactModel {
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String description;
    private List<String> expectedErrors;
}
