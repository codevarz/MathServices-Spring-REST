package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import xcal.cs.math.model.DivisionRequest;
import xcal.cs.math.model.DivisionResponse;

public class DivisionTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCasesForQuotient")
  public void testSuccessForQuotient(int dividend, int divisor, int quotient) {
    DivisionRequest request = new DivisionRequest(dividend, divisor);
    DivisionResponse response =
        client.postForEntity("/divide", request, DivisionResponse.class).getBody();

    Assert.assertEquals(response.getQuotient(), quotient);
  }
  
  @DataProvider(name = "successCasesForQuotient")
  private static Object[][] getSuccessCasesForQuotient() {
    return new Object[][] {
        { 1, 1, 1 },
        { 4, 2, 2 },
        { 4, 3, 1 },
        { 4, 4, 1 },
        { 9, 4, 2 },
        { 0, 5, 0 },
        { -10, 5, -2 },
        { 10, -4, -2 },
        { -10, -3, 3 }
    };
  }

  @Test(dataProvider = "successCasesForRemainder")
  public void testSuccessForRemainder(int dividend, int divisor, int remainder) {
    DivisionRequest request = new DivisionRequest(dividend, divisor);
    DivisionResponse response =
        client.postForEntity("/divide", request, DivisionResponse.class).getBody();

    Assert.assertEquals(response.getRemainder(), remainder);
  }

  @DataProvider(name = "successCasesForRemainder")
  private static Object[][] getSuccessCasesForRemainder() {
    return new Object[][] {
        { 1, 1, 0 },
        { 4, 2, 0 },
        { 4, 3, 1 },
        { 4, 4, 0 },
        { 9, 4, 1 },
        { 0, 5, 0 },
        { -10, 5, 0 },
        { 10, -4, 2 },
        { -10, 4, -2 }
    };
  }
  

  @Test(dataProvider = "divideByZeroCases")
  public void testDivideByZero(int dividend, int divisor) {
    try {
      DivisionRequest request = new DivisionRequest(dividend, divisor);
      client.postForEntity("/divide", request, DivisionResponse.class);
      Assert.fail("Expected exception not thrown");
    } catch (HttpStatusCodeException e) {
      Assert.assertEquals(e.getRawStatusCode(), 400);
    }
  }

  @DataProvider(name = "divideByZeroCases")
  private static Object[][] getDivideByZeroCases() {
    return new Object[][] {
        { 1, 0 },
        { -1, 0 },
        { 0, 0 },
        { Integer.MAX_VALUE, 0 }
    };
  }
}
