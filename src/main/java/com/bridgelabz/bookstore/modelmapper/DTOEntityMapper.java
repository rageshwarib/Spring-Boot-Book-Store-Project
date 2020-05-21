package com.bridgelabz.bookstore.modelmapper;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import org.modelmapper.ModelMapper;

public class DTOConverter {
    public BookDTO convertBookEntityToDTO(Book book) {
        ModelMapper modelMapper = new ModelMapper();
        BookDTO bookDTO = new BookDTO();
        modelMapper.map(book, bookDTO);
        return bookDTO;
    }
}
