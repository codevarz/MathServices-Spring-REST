package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import xcal.cs.math.model.UnionRequest;
import xcal.cs.math.model.UnionResponse;

public class UnionTest {

  private final RestTemplate client = TestSupport.CLIENT;

  @Test(dataProvider = "successCases")
  public void testSuccess(
      Collection<Integer> left, Collection<Integer> right, Collection<Integer> union) {
    UnionRequest request = new UnionRequest(left, right);
    UnionResponse response =
        client.postForEntity("/union", request, UnionResponse.class).getBody();

    Assert.assertEquals(new HashSet<>(response.getUnion()), new HashSet<>(union));
  }

  @DataProvider(name = "successCases")
  private static Object[][] getSuccessCases() {
    return new Object[][] {
        { Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 4), Arrays.asList(1, 2, 3, 4) },
        { Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(1, 2, 3) },
        { Collections.emptyList(), Arrays.asList(2, 3), Arrays.asList(2, 3) },
        { Arrays.asList(2, 3), Collections.emptyList(), Arrays.asList(2, 3) },
        { Arrays.asList(-2, -3), Arrays.asList(2, 3), Arrays.asList(-2, -3, 2, 3) }
    };
  }

  @Test(dataProvider = "failureCases")
  public void testFailure(Collection<Integer> left, Collection<Integer> right) {
    try {
      UnionRequest request = new UnionRequest(left, right);
      client.postForEntity("/union", request, UnionResponse.class);
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

  @Test(timeOut = 10000)
  public void performanceTest() {
    final int size = 1000000;

    List<Integer> left = new ArrayList<>(size);
    List<Integer> right = new ArrayList<>(size);
    Random random = new Random(42);

    for (int i = 0; i < size; i++) {
      left.add(i, random.nextInt());
      right.add(i, random.nextInt());
    }

    UnionRequest request = new UnionRequest(left, right);
    client.postForEntity("/union", request, UnionResponse.class).getBody();
  }
}
