package com.bridgelabz.bookstore.modelmapper;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DTOEntityMapper {
    ModelMapper modelMapper = new ModelMapper();

    public Page<BookDTO> mapBookEntityToDTO(Page<Book> bookPage) {
        return bookPage.map(entity -> {
            BookDTO bookDTO = new BookDTO();
            modelMapper.map(entity, bookDTO);
            return bookDTO;
        });
    }
}
