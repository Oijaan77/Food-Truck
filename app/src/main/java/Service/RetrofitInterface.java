package Service;

import java.util.HashMap;
import java.util.List;

import Domain.Login;
import Domain.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface to create stubs for server calls
 */
public interface RetrofitInterface {

    @POST("/login")
    Call<Login> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/order")
    Call<Void> executeOrder (@Body HashMap<String, String> map);

    @PUT("/orderUp")
    Call<Void> updateOrder (@Body HashMap<String, String> map);

    @GET("/")
    Call<List<getOrder>> getOrders();

    @GET("/orders")
    Call<List<getOrder>> getList(@Query("email") String email);
}
