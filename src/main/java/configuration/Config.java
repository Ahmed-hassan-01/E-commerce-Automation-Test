package configuration;

public class Config {
    String login_url = "https://www.saucedemo.com/v1/index.html";
    String products_url = "https://www.saucedemo.com/v1/inventory.html";
    String cart_url = "https://www.saucedemo.com/v1/cart.html";
    String checkOutUrl_StepOne = "https://www.saucedemo.com/v1/checkout-step-one.html";
    String checkOutUrl_StepTwo = "https://www.saucedemo.com/v1/checkout-step-two.html";
    String jsonPathLogin_ValidData = "\\src\\main\\resources\\loginData.json";
    String jsonPathLogin_InValidData = "\\src\\main\\resources\\invalidLoginData.json";
    String jsonPathProductsData = "\\src\\main\\resources\\productsData.json";
    String jsonPathCheckOutData = "\\src\\main\\resources\\checkoutData.json";

    public String getPageUrl(String page) {

        switch (page) {
            case "login_url": {
                return login_url;
            }
            case "products_url": {
                return products_url;
            }
            case "cart_url": {
                return cart_url;
            }
            case "checkOutUrl_StepOne": {
                return checkOutUrl_StepOne;
            }
            case "checkOutUrl_StepTwo": {
                return checkOutUrl_StepTwo;
            }
            default:
                break;

        }

        return "notExist";

    }

    public String getJsonPath(String json) {

        switch (json) {
            case "loginValidData": {
                return jsonPathLogin_ValidData;
            }

            case "loginInvalidData": {
                return jsonPathLogin_InValidData;
            }
            case "productsData": {
                return jsonPathProductsData;
            }
            case "checkOutData": {
                return jsonPathCheckOutData;
            }

            default:
                break;
        }

        return "notExist";
    }
}