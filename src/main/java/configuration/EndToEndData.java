package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndToEndData {


    @JsonProperty("LoginUrl")
    public String LoginUrl;

    @JsonProperty("validLoginUser_Name")
    public String validLoginUser_Name;

    @JsonProperty("validLoginPassword")
    public String validLoginPassword;


    @JsonProperty("productName_1")
    public String productName_1;

    @JsonProperty("productName_2")
    public String productName_2;

    @JsonProperty("productName_3")
    public String productName_3;

    @JsonProperty("validCheckoutFirstName")
    public String validCheckoutFirstName;

    @JsonProperty("validCheckoutLastName")
    public String validCheckoutLastName;

    @JsonProperty("validCheckoutPostalCode")
    public String validCheckoutPostalCode;




}
