package com.example.universityApp.repositories.favBookRepo;

import androidx.annotation.NonNull;

import com.example.universityApp.db.dao.FavBookDAO;
import com.example.universityApp.dto.FavBook;

import java.util.LinkedList;
import java.util.List;

public class FavBooksRepositoryImpl implements FavBooksRepository {
    private final FavBookDAO favBookDAO;

    public FavBooksRepositoryImpl(FavBookDAO favBookDAO) {
        this.favBookDAO = favBookDAO;
    }

    @Override
    public List<FavBook> getAll() {
        return toFavBooks(favBookDAO.getAll());
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
}
