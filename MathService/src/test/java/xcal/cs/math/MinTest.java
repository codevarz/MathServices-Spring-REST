package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import xcal.cs.math.model.MinRequest;
import xcal.cs.math.model.MinResponse;

public class MinTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCases")
  public void testSuccess(Collection<Integer> numbers, int min) {
    MinRequest request = new MinRequest(numbers);
    MinResponse response =
        client.postForEntity("/min", request, MinResponse.class).getBody();

    Assert.assertEquals(response.getMin(), min);
  }

  @DataProvider(name = "successCases")
  private static Object[][] getSuccessCases() {
    return new Object[][] {
        { Arrays.asList(9, 12, 25, 3), 3 },
        { Arrays.asList(9, -12, 25, 3), -12 },
        { Arrays.asList(9, -12, 0, 3), -12 },
        { Arrays.asList(9, 9, 1, 3), 1 }
    };
  }

  @Test(dataProvider = "failureCases")
  public void testFailure(Collection<Integer> numbers) {
    try {
      MinRequest request = new MinRequest(numbers);
      client.postForEntity("/min", request, MinResponse.class);
      Assert.fail("Expected exception not thrown");
    } catch (HttpStatusCodeException e) {
      Assert.assertEquals(e.getRawStatusCode(), 400);
    }
  }

  @DataProvider(name = "failureCases")
  private static Object[][] getFailureCases() {
    return new Object[][] {
        { null },
        { Collections.emptyList() }
    };
  }
}
