package xcal.cs.math;

import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import xcal.cs.math.model.MultiplicationRequest;
import xcal.cs.math.model.MultiplicationResponse;

public class MultiplicationTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCases")
  public void testSuccess(long multiplicand, long multiplier, long product) {
    MultiplicationRequest request = new MultiplicationRequest(multiplicand, multiplier);
    MultiplicationResponse response =
        client.postForEntity("/multiply", request, MultiplicationResponse.class).getBody();

    Assert.assertEquals(response.getProduct(), product);
  }

  @DataProvider(name = "successCases")
  private static Object[][] getSuccessCases() {
    return new Object[][] {
        { 1, 1, 1 },
        { 2, 4, 8 },
        { 0, 0, 0 },
        { 0, 1, 0 },
        { 8, 0, 0 },
        { 8, 1, 8 },
        { 1, 8, 8 },
        { 2, -2, -4 },
        { -2, 5, -10 },
        { -2, -1, 2 }
    };
  }
}
