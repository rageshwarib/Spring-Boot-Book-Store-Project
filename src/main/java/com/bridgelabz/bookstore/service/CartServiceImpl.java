package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookCartDto;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.EmailDto;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.OrderId;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderIdRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.MapperService;
import com.bridgelabz.bookstore.utility.RabbitMq;
@Service
@Transactional
public class CartServiceImpl implements ICartService{
	private static final long order_Id = 1000;
	@Autowired
    private MapperService converterService;
	@Autowired
    private CartRepository cartRepository;
	@Autowired
    private BookRepository bookRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderIdRepository orderIdRepository;
    @Autowired
    private EmailDto emailDto;
    @Autowired
    private RabbitMq rabbitMq;


    @Override
    public void addToCart(CartDto cartDto, String token) {
    	if (jwtUtils.validateJwtToken(token)) {
    		Cart cart = converterService.convertToCartEntity(cartDto);
    		Long userId = jwtUtils.getUserIdFromJwtToken(token);
    		cart.setUserId(userId);
    		userRepository.findById(userId);
    		cartRepository.save(cart);
    	} else
    		throw new UserException(UserException.ExceptionType.JWT_NOT_VALID, "Token is not valid");
    }

    @Override
    public void removeFromCart(CartDto cartDto, String token) {
    	 if (jwtUtils.validateJwtToken(token)) {
    		 Cart cart = converterService.convertToCartEntity(cartDto);
    		 long userId = jwtUtils.getUserIdFromJwtToken(token);
    		 cart.setUserId(userId);
    		 cartRepository.deleteCartByBookIdAndUserId(cart.getBookId(), userId);
    		// return "Book Removed from cart Successfully";
    	 } else
    		 throw new UserException(UserException.ExceptionType.JWT_NOT_VALID, "Token is not valid");
    }

    @Override
    public List<BookCartDto> getBooks(String token) {
        List<BookCartDto> bookCartDto = new ArrayList<>();
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        try {
            List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
            for (Cart cart : allByUserId) {
                if (cart.getBookQuantity() > 0 && cart.getBookId() != 0) {
                	Book book = bookRepository.findById(cart.getBookId());
                	System.out.println(book);
                //	bookCartDto.add(converterService.convertToBookCartDto(cart));
                 //   cartRepository.deleteCartByBookIdAndUserId(cart.getBookId(), cart.getUserId());
                bookCartDto.add(new BookCartDto(cart.getBookId(),
                        book.getAuthor(),
                        book.getTitle(),
                        book.getImage(),
                        book.getPrice(),
                        cart.getBookQuantity()));
            }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return bookCartDto;
    }
    @Override
    public Long getOrderId(String token) {
     if (jwtUtils.validateJwtToken(token)) {
    	Long userId = jwtUtils.getUserIdFromJwtToken(token);
		Optional<User> byId = userRepository.findById(userId);
		String email = byId.get().getEmail();
		OrderId orderIdNew = new OrderId();
		OrderId orderId = orderIdRepository.findFirstByOrderByIdDesc();
		if (orderId == null) {
			orderIdNew.setUserId(userId);
			orderIdNew.setOrderId(order_Id);
			orderIdRepository.save(orderIdNew);
			sendEmailWithOrderDetails(email, order_Id);
			return orderIdNew.getOrderId();
		}
		orderIdNew.setUserId(userId);
		orderIdNew.setOrderId(order_Id + orderId.getId());
		orderIdRepository.save(orderIdNew);
		sendEmailWithOrderDetails(email, order_Id + orderId.getId());
		return orderIdNew.getOrderId();
     	}
     else 
    	 throw new UserException(UserException.ExceptionType.JWT_NOT_VALID, "Token is not valid");
    }
    
    private void sendEmailWithOrderDetails(String email, long orderId) {
        emailDto.setTo(email);
        emailDto.setFrom("${EMAIL}");
        emailDto.setSubject("Your Order is Placed");
        emailDto.setBody("Thank you for placing order with us, your order id is " + orderId);
        rabbitMq.sendingMsgToQueue(emailDto);
    }    
}
