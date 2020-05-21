package com.bridgelabz.bookstore.modelmapper;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import org.modelmapper.ModelMapper;

public class DTOEntityMapper {
    ModelMapper modelMapper = new ModelMapper();

    public BookDTO mapBookEntityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        modelMapper.map(book, bookDTO);
        return bookDTO;
    }

    public Book mapBookDTOToEntity(BookDTO bookDTO) {
        Book book = new Book();
        modelMapper.map(bookDTO, book);
        return book;
    }
}
