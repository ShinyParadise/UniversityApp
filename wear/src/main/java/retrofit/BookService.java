package retrofit;

import com.example.universityApp.dto.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    String BASE_URL = "https://raw.githubusercontent.com/";

    @GET("Lpirskaya/JsonLab/master/Books2022.json")
    Call<ArrayList<Book>> getBooks();
}
