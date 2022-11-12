package com.example.universityApp.ui.bookList;

import androidx.lifecycle.ViewModel;

import com.example.universityApp.ui.UniversityApp;
import com.example.universityApp.dto.Book;
import com.example.universityApp.repositories.bookRepo.BookRepository;
import com.example.universityApp.retrofit.BookServiceController;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class BookListViewModel extends ViewModel {
    private ArrayList<Book> books = new ArrayList<>();
    private final BookServiceController bookServiceController;

    private final BookRepository repository;
    private final Executor executor;

    public BookListViewModel(BookRepository repository) {
        this.executor = UniversityApp.getAppExecutor();
        this.repository = repository;
        this.bookServiceController = new BookServiceController();
        fetchBooks();
    }

    private void fetchBooks() {
        executor.execute(() -> {
            books = new ArrayList<>(repository.getAll());
        });
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void fetchAndInsertBooksFromRepo() {
//        executor.execute(() -> {
//            repository.insert(new Book("Гарри Поттер и Дары Смерти", "Джоан Роулинг"));
//            repository.insert(new Book("Приключение Тома Сойера", "Марк Твен"));
//            repository.insert(new Book("Мастер и Маргарита", "Михаил Булгаков"));
//            repository.insert(new Book("Вишневый сад", "Антон Чехов", 2));
//            repository.insert(new Book("Социальная фантастика", "Евгений Замятин"));
//            repository.insert(new Book("Зеленая миля", "Стивен Кинг");
//        });
        executor.execute(() -> {
            books = bookServiceController.getBooks();
            books.forEach(repository::insert);
        });
    }
}
