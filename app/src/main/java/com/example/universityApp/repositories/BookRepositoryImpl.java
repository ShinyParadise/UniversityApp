package com.example.universityApp.repositories;

import androidx.annotation.NonNull;

import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.dto.Book;

import java.util.LinkedList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private final BookDAO bookDAO;

    public BookRepositoryImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAll() {
        List<com.example.universityApp.db.models.Book> dbBooks = bookDAO.getAll();
        return toBookItems(dbBooks);
    }

    @Override
    public Book insert(@NonNull Book book) {
        com.example.universityApp.db.models.Book dbBook = toDbBook(book);

        Long insertedId = bookDAO.insert(dbBook);
        book.setId(insertedId);

        return book;
    }


    @Override
    public void update(Book book) {
        bookDAO.update(toDbBook(book));
    }

    @Override
    public void delete(Book book) {
        bookDAO.delete(toDbBook(book));
    }


    @NonNull
    private List<Book> toBookItems(@NonNull List<com.example.universityApp.db.models.Book> dbBooks) {
        List<Book> books = new LinkedList<>();
        dbBooks.forEach(book -> {
            books.add(new Book(
                    book.name,
                    book.author,
                    book.genre,
                    book.publicationDate,
                    book.rating)
            );
        });

        return books;
    }

    @NonNull
    private com.example.universityApp.db.models.Book toDbBook(@NonNull Book book) {
        return new com.example.universityApp.db.models.Book(
                book.getAuthor(),
                book.getName(),
                book.getGenre(),
                book.getPublicationDate(),
                book.getRating(),
                book.getUser_id()
        );
    }

}
