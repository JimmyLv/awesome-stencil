import com.netflix.hystrix.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.*;
@Service
public class ProductService {
    private static final String GROUP = "products";
    private static final int TIMEOUT = 60000;
    private final ProductDetailService productDetailService;
    private final ProductPricingService productPricingService;
    private final ProductRatingService productRatingService;
    private final ProductReviewService productReviewService;
    @Autowired
    public ProductService(ProductDetailService productDetailService, ProductPricingService productPricingService,
                          ProductRatingService productRatingService, ProductReviewService productReviewService) {
        this.productDetailService = productDetailService;
        this.productPricingService = productPricingService;
        this.productRatingService = productRatingService;
        this.productReviewService = productReviewService;
    }
    public Map<String, Map<String, Object>> getProductSummary(String productId) {
        List<Callable<AsyncResponse>> callables = new ArrayList<>();
        callables.add(new BackendServiceCallable("details", getProductDetails(productId)));
        callables.add(new BackendServiceCallable("pricing", getProductPricing(productId)));
        return doBackendAsyncServiceCall(callables);
    }
    public Map<String, Map<String, Object>> getProduct(String productId) {
        List<Callable<AsyncResponse>> callables = new ArrayList<>();
        callables.add(new BackendServiceCallable("details", getProductDetails(productId)));
        callables.add(new BackendServiceCallable("pricing", getProductPricing(productId)));
        callables.add(new BackendServiceCallable("ratings", getProductRatings(productId)));
        callables.add(new BackendServiceCallable("reviews", getProductReviews(productId)));
        return doBackendAsyncServiceCall(callables);
    }
    private static Map<String, Map<String, Object>> doBackendAsyncServiceCall(List<Callable<AsyncResponse>> callables) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List<Future<AsyncResponse>> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            executorService.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
            Map<String, Map<String, Object>> result = new HashMap<>();
            for (Future<AsyncResponse> future : futures) {
                AsyncResponse response = future.get();
                result.put(response.serviceKey, response.response);
            }
            return result;
        } catch (InterruptedException|ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Cacheable
    private HystrixCommand<Map<String, Object>> getProductDetails(String productId) {
        return new HystrixCommand<Map<String, Object>>(
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("getProductDetails"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(TIMEOUT)
                        )
        ) {
            @Override
            protected Map<String, Object> run() throws Exception {
                return productDetailService.getDetails(productId);
            }
            @Override
            protected Map getFallback() {
                return new HashMap<>();
            }
        };
    }
    private HystrixCommand<Map<String, Object>> getProductPricing(String productId) {
        // ... snip, see getProductDetails() ...
    }
    private HystrixCommand<Map<String, Object>> getProductRatings(String productId) {
        // ... snip, see getProductDetails() ...
    }
    private HystrixCommand<Map<String, Object>> getProductReviews(String productId) {
        // ... snip, see getProductDetails() ...
    }
    private static class AsyncResponse {
        private final String serviceKey;
        private final Map<String, Object> response;
        AsyncResponse(String serviceKey, Map<String, Object> response) {
            this.serviceKey = serviceKey;
            this.response = response;
        }
    }
    private static class BackendServiceCallable implements Callable<AsyncResponse> {
        private final String serviceKey;
        private final HystrixCommand<Map<String, Object>> hystrixCommand;
        public BackendServiceCallable(String serviceKey, HystrixCommand<Map<String, Object>> hystrixCommand) {
            this.serviceKey = serviceKey;
            this.hystrixCommand = hystrixCommand;
        }
        @Override
        public AsyncResponse call() throws Exception {
            return new AsyncResponse(serviceKey, hystrixCommand.execute());
        }
    }
}