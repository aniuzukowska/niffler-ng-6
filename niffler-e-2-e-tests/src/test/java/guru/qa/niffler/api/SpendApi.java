package guru.qa.niffler.api;

import ch.qos.logback.core.net.ssl.SSL;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.SpendJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.*;

import java.util.List;
import java.util.Optional;

public interface SpendApi {

  @POST("internal/spends/add")
  Call<SpendJson> addSpend(@Body SpendJson spend);

  @PATCH("internal/spends/edit")
  Call<SpendJson> editSpend(@Body SpendJson spend);

  @GET("internal/spends/{id}")
  Call<SpendJson> getSpendById(@Query("username") String username,
                               @Path("id") String id);

  @GET("internal/spends/all")
  Call<List<SpendJson>> getSpendAll(@Query("username") String username);

  @DELETE("internal/spends/remove")
  Call<Optional<String>> removeSpend(@Query("username") String username,
                             @Query("ids") List<String> ids);

  @POST("internal/categories/add")
  Call<CategoryJson> addCategory(@Body CategoryJson category);

  @PATCH("internal/categories/update")
  Call<CategoryJson> updateCategory(@Body CategoryJson category);

  @GET("internal/categories/all")
  Call<CategoryJson> getAllCategories(@Query("username") String username);
}
