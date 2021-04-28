import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class AjioDataProvider {
    @DataProvider(name="ajio-data-provider")
    public Object[][] dpMethod (Method m){
        if(m.getName()=="SearchItemNotPresent")
        {
            return new Object[][] {{"Smart Phones"}};
        }
        else if(m.getName()=="SearchItemPresent"||m.getName()=="addItemToBag"||m.getName()=="proceedToShiping"
                ||m.getName()=="verifyReturnPolicy"||m.getName()=="filterByGender"){
            return new Object[][] {{"Jackets"}};
        }
        else if(m.getName()=="searchBrand")
        {
            return new Object[][] {{"PUMA"}};
        }
        else if(m.getName()=="filterByPriceMinAndMax")
        {
            return new Object[][] {{"ADIDAS"}};
        }
        else if((m.getName()=="sortByPriceLowest")||(m.getName() =="sortByPriceHighest")) {
            return new Object[][] {{"Shoes"}};
        }

        return null;
    }
}
