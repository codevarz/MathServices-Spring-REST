package xcal.cs.math;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import xcal.cs.math.model.SubtractionRequest;
import xcal.cs.math.model.SubtractionResponse;

public class SubtractionTest {

	private final RestTemplate client = TestSupport.CLIENT;

	  @Test(dataProvider = "successCases")
	  public void testSuccess(int minuend, int subtrahend, int difference) {
	    SubtractionRequest request = new SubtractionRequest(minuend, subtrahend);
	    SubtractionResponse response =
	        client.postForEntity("/subtract", request, SubtractionResponse.class).getBody();

	    Assert.assertEquals(response.getDifference(), difference);
	  }

	  @DataProvider(name = "successCases")
	  private static Object[][] getSuccessCases() {
	    return new Object[][] {
	        { 2, 1, 1 },
	        { 2, 4, -2 },
	        { 0, 0, 0 },
	        { 0, 1, -1 },
	        { 8, 0, 8 },
	        { 2, -2, 4 },
	        { 4, 6, -2 },
	        { -2, 5, -7 },
	        { Integer.MAX_VALUE, 1, Integer.MAX_VALUE - 1 },
	        { Integer.MIN_VALUE + 1, 1, Integer.MIN_VALUE }
	    };
	  }

	  @Test(dataProvider = "overflowCases")
	  public void testOverflow(int minuend, int subtrahend) {
	    try {
	      SubtractionRequest request = new SubtractionRequest(minuend, subtrahend);
	      client.postForEntity("/subtract", request, SubtractionResponse.class);
	      Assert.fail("Expected exception not thrown");
	    } catch (HttpStatusCodeException e) {
	      Assert.assertEquals(e.getRawStatusCode(), 400);
	    }
	  }

	  @DataProvider(name = "overflowCases")
	  private static Object[][] getOverflowCases() {
	    return new Object[][] {
	        { 1, -Integer.MAX_VALUE },
	        { Integer.MIN_VALUE, 1},
	        { Integer.MIN_VALUE, 10}
	    };
	  }
}
