package com.example.universityApp.ui.bookList;

import androidx.lifecycle.ViewModel;

import com.example.universityApp.UniversityApp;
import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.BookRepository;
import com.example.universityApp.retrofit.BookServiceController;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class BookListViewModel extends ViewModel {
    private ArrayList<Book> books;
    private final BookServiceController bookServiceController;

    private BookRepository repository;
    private Executor executor;

    public BookListViewModel(BookRepository repository) {
        books = new ArrayList<>();
        bookServiceController = new BookServiceController();
        this.repository = repository;
        this.executor = UniversityApp.getAppExecutor();
        fetchBooks();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void fetchBooks() {
//        executor.execute(() -> {
//            repository.insert(new Book("Гарри Поттер и Дары Смерти", "Джоан Роулинг", 2));
//            repository.insert(new Book("Приключение Тома Сойера", "Марк Твен", 2));
//            repository.insert(new Book("Мастер и Маргарита", "Михаил Булгаков", 2));
//            repository.insert(new Book("Вишневый сад", "Антон Чехов", 2));
//            repository.insert(new Book("Социальная фантастика", "Евгений Замятин", 2));
//            repository.insert(new Book("Зеленая миля", "Стивен Кинг", 2));
//        });
        executor.execute(() -> {
            books = new ArrayList<>(repository.getAll());
        });
    }
}
