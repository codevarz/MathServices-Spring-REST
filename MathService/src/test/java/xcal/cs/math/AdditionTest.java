package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import xcal.cs.math.model.AdditionRequest;
import xcal.cs.math.model.AdditionResponse;

public class AdditionTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCases")
  public void testSuccess(int augend, int addend, int sum) {
    AdditionRequest request = new AdditionRequest(augend, addend);
    AdditionResponse response =
        client.postForEntity("/add", request, AdditionResponse.class).getBody();

    Assert.assertEquals(response.getSum(), sum);
  }

  @DataProvider(name = "successCases")
  private static Object[][] getSuccessCases() {
    return new Object[][] {
        { 1, 1, 2 },
        { 2, 4, 6 },
        { 0, 0, 0 },
        { 0, 1, 1 },
        { 8, 0, 8 },
        { 2, -2, 0 },
        { 4, -6, -2 },
        { -2, 5, 3 },
        { Integer.MAX_VALUE, -1, Integer.MAX_VALUE - 1 },
        { Integer.MIN_VALUE, 1, Integer.MIN_VALUE + 1 }
    };
  }

  @Test(dataProvider = "overflowCases")
  public void testOverflow(int augend, int addend) {
    try {
      AdditionRequest request = new AdditionRequest(augend, addend);
      client.postForEntity("/add", request, AdditionResponse.class);
      Assert.fail("Expected exception not thrown");
    } catch (HttpStatusCodeException e) {
      Assert.assertEquals(e.getRawStatusCode(), 400);
    }
  }

  @DataProvider(name = "overflowCases")
  private static Object[][] getOverflowCases() {
    return new Object[][] {
        { 1, Integer.MAX_VALUE },
        { -1, Integer.MIN_VALUE },
        { 10, Integer.MAX_VALUE }
    };
  }
}
