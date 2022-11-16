package com.example.universityApp.repositories.favBookRepo;

import androidx.annotation.NonNull;

import com.example.universityApp.db.dao.BookDAO;
import com.example.universityApp.db.dao.FavBookDAO;
import com.example.universityApp.dto.Book;
import com.example.universityApp.dto.FavBook;

import java.util.LinkedList;
import java.util.List;

public class FavBooksRepositoryImpl implements FavBooksRepository {
    private final FavBookDAO favBookDAO;
    private final BookDAO bookDAO;

    public FavBooksRepositoryImpl(FavBookDAO favBookDAO, BookDAO bookDAO) {
        this.favBookDAO = favBookDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAll(long userID) {
        List<com.example.universityApp.db.models.FavBook> favBookIDs = favBookDAO.getAllByUser(userID);

        List<Long> bookIDs = new LinkedList<>();
        favBookIDs.forEach(favBook -> bookIDs.add(favBook.book_id));

        List<Book> userBooks = new LinkedList<>();
        bookIDs.forEach(bookID -> userBooks.add(
                toBook(bookDAO.getById(bookID)))
        );

        return userBooks;
    }

    @Override
    public void insert(FavBook book) {
        com.example.universityApp.db.models.FavBook dbFavBook = toDbFavBook(book);
        favBookDAO.insert(dbFavBook);
    }

    @Override
    public void update(FavBook book) {
        favBookDAO.update(toDbFavBook(book));
    }

    @Override
    public void delete(FavBook book) {
        favBookDAO.delete(toDbFavBook(book));
    }

    @NonNull
    private List<FavBook> toFavBooks(
            @NonNull List<com.example.universityApp.db.models.FavBook> dbFavBooks
    ) {
        List<FavBook> favBooks = new LinkedList<>();
        dbFavBooks.forEach(favBook -> favBooks.add(new FavBook(favBook.user_id, favBook.book_id)));

        return favBooks;
    }

    @NonNull
    private com.example.universityApp.db.models.FavBook toDbFavBook(@NonNull FavBook book) {
        return new com.example.universityApp.db.models.FavBook(book.getUserId(), book.getBookId());
    }

    public Book toBook(@NonNull com.example.universityApp.db.models.Book dbBook) {
        return new Book(
                dbBook.name,
                dbBook.author,
                dbBook.genre,
                dbBook.publicationDate,
                dbBook.rating
        );
    }
}
