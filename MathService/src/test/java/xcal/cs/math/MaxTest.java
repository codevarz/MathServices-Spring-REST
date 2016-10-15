package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import xcal.cs.math.model.MaxRequest;
import xcal.cs.math.model.MaxResponse;

public class MaxTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCases")
  public void testSuccess(Collection<Integer> numbers, int max) {
    MaxRequest request = new MaxRequest(numbers);
    MaxResponse response =
        client.postForEntity("/max", request, MaxResponse.class).getBody();

    Assert.assertEquals(response.getMax(), max);
  }

  @DataProvider(name = "successCases")
  private static Object[][] getSuccessCases() {
    return new Object[][] {
        { Arrays.asList(9, 12, 25, 3), 25 },
        { Arrays.asList(9, -12, 25, 3), 25 },
        { Arrays.asList(9, -12, 0, 3), 9 },
        { Arrays.asList(9, 9, 1, 3), 9 },
        { Arrays.asList(-9, -9, -1, -3), -1 }
    };
  }

  @Test(dataProvider = "failureCases")
  public void testFailure(Collection<Integer> numbers) {
    try {
      MaxRequest request = new MaxRequest(numbers);
      client.postForEntity("/max", request, MaxResponse.class);
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
