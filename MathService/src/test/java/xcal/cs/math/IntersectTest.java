package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import xcal.cs.math.model.IntersectRequest;
import xcal.cs.math.model.IntersectResponse;

public class IntersectTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCases")
  public void testSuccess(
      Collection<Integer> left, Collection<Integer> right, Collection<Integer> intersect) {
    IntersectRequest request = new IntersectRequest(left, right);
    IntersectResponse response =
        client.postForEntity("/intersect", request, IntersectResponse.class).getBody();

    Assert.assertEquals(new HashSet<>(response.getIntersection()), new HashSet<>(intersect));
  }

  @DataProvider(name = "successCases")
  private static Object[][] getSuccessCases() {
    return new Object[][] {
        { Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 4), Arrays.asList(2, 3) },
        { Arrays.asList(1), Arrays.asList(2, 3), Collections.emptyList() },
        { Collections.emptyList(), Arrays.asList(2, 3), Collections.emptyList() },
        { Arrays.asList(-2, -3), Arrays.asList(2, 3), Collections.emptyList() }
    };
  }

  @Test(dataProvider = "failureCases")
  public void testFailure(Collection<Integer> left, Collection<Integer> right) {
    try {
      IntersectRequest request = new IntersectRequest(left, right);
      client.postForEntity("/intersect", request, IntersectResponse.class);
      Assert.fail("Expected exception not thrown");
    } catch (HttpStatusCodeException e) {
      Assert.assertEquals(e.getRawStatusCode(), 400);
    }
  }

  @DataProvider(name = "failureCases")
  private static Object[][] getFailureCases() {
    return new Object[][] {
        { null, Arrays.asList(1) },
        { Arrays.asList(12), null }
    };
  }
}
