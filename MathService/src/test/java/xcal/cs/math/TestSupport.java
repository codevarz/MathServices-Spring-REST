package xcal.cs.math;

import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

class TestSupport {

  public static final RestTemplate CLIENT = createClient();

  private static final RestTemplate createClient() {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(ApplicationConfig.class);
    context.setServletContext(new MockServletContext());
    context.refresh();

    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    return new RestTemplate(new MockMvcClientHttpRequestFactory(mockMvc));
  }
}
