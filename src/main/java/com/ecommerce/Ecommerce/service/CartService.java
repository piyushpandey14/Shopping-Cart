package com.ecommerce.Ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.entity.Cart;
import com.ecommerce.Ecommerce.entity.CartItem;
import com.ecommerce.Ecommerce.entity.Order;
import com.ecommerce.Ecommerce.entity.OrderItem;
import com.ecommerce.Ecommerce.entity.Product;
import com.ecommerce.Ecommerce.repository.CartRepository;
import com.ecommerce.Ecommerce.repository.OrderRepository;
import com.ecommerce.Ecommerce.repository.ProductRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository orderRepository;

	public Cart getCart(long userId) {
		Cart cart = cartRepository.findByCustomerId(userId);
		Cart cartDTO = cart;

		if (cart != null && !cart.getCartItem().isEmpty()) {
			cartDTO.setTotal(0.0);
			List<CartItem> items = new ArrayList<CartItem>();
			for (CartItem item : cart.getCartItem()) {
				Optional<Product> product = productRepository.findById(item.getProduct().getId());
				if (product.isPresent()) {
					if (product.get().getUnitStock() == 0)
						item.setStatus("Out of Stock");
					else if (product.get().getUnitStock() <= item.getQuantity())
						item.setQuantity(product.get().getUnitStock());
					cartDTO.setTotal(item.getQuantity() * product.get().getProductPrice());
					items.add(item);
				}
			}
			cartDTO.setCartItem(items);
		}
		return cartDTO;
	}

	public Cart saveOrUpdateCart(long userId, CartItem item) {
		Cart cart = cartRepository.findByCustomerId(userId);
		System.out.println("inside cart");
		Optional<Product> product = productRepository.findById(item.getProduct().getId());
		if (product.isPresent()) {
			if (cart == null) {
				if (item.getQuantity() <= product.get().getUnitStock()) {
					cart = new Cart();
					cart.addCartItem(item);
					cart.setTotal(product.get().getProductPrice() * item.getQuantity());
					cart.setCustomerId(userId);
				}

			} else {
				if (cart.getCartItem().contains(item)) {
					int index = cart.getCartItem().indexOf(item);
					if (cart.getCartItem().get(index).getQuantity() + item.getQuantity() <= product.get()
							.getUnitStock()) {
						cart.getCartItem().get(index)
								.setQuantity(cart.getCartItem().get(index).getQuantity() + item.getQuantity());
						cart.setTotal(cart.getTotal() + product.get().getProductPrice() * item.getQuantity());
					}
				} else {
					if (item.getQuantity() <= product.get().getUnitStock()) {
						cart.addCartItem(item);
						cart.setTotal(cart.getTotal() + product.get().getProductPrice() * item.getQuantity());
					}
				}
			}
			return cartRepository.save(cart);
		}
		return null;
	}

	public Cart deleteFromCart(long userId, CartItem item) {
		Cart cart = cartRepository.findByCustomerId(userId);
		Optional<Product> product = productRepository.findById(item.getProduct().getId());
		if (product.isPresent()) {
			if (cart != null) {
				int index = cart.getCartItem().indexOf(item);
				if (index > -1) {
					cart.setTotal(cart.getTotal()
							- (product.get().getProductPrice() * cart.getCartItem().get(index).getQuantity()));
					cart.getCartItem().remove(index);
				}
				return cartRepository.save(cart);
			}
		}
		return null;
	}

	public void clearShoppingCart(long userId) {
		cartRepository.delete(cartRepository.findByCustomerId(userId));
	}

	public String checkout(Long userId) {
		Cart cart = cartRepository.findByCustomerId(userId);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		double total = 0.0;

		if (cart != null && !cart.getCartItem().isEmpty()) {
			for (CartItem item : cart.getCartItem()) {
				Optional<Product> product = productRepository.findById(item.getProduct().getId());
				if (product.isPresent() && product.get().getUnitStock() != 0) {
					OrderItem orderItem = new OrderItem();
					orderItem.setPrice(item.getProduct().getProductPrice());
					orderItem.setProduct(item.getProduct());
					if (product.get().getUnitStock() <= item.getQuantity())
						orderItem.setQuantity(product.get().getUnitStock());
					orderItems.add(orderItem);
					total += orderItem.getQuantity() * orderItem.getPrice();
				}
			}
		}

		if (!orderItems.isEmpty()) {
			Order order = new Order();
			order.setTotal(total);
			order.setUserId(userId);
			order.setCartItem(orderItems);
			if (orderRepository.save(order) != null)
				;
			clearShoppingCart(userId);
			return "Order has been placed";
		}
		return "Cart not found";
	}
}