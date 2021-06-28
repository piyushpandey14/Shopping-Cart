package com.ecommerce.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Ecommerce.entity.Cart;
import com.ecommerce.Ecommerce.entity.CartItem;
import com.ecommerce.Ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/")
	public ResponseEntity<?> getCart(@RequestHeader("user-id") long userId) {
		Cart cart = cartService.getCart(userId);
		if (cart != null)
			return ResponseEntity.ok(cart);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart not found");
	}

	@PostMapping("/")
	public ResponseEntity<?> addToCart(@RequestHeader("user-id") long userId, @RequestBody CartItem cartItem) {
		Cart cart = cartService.saveOrUpdateCart(userId, cartItem);
		if (cart != null)
			return ResponseEntity.ok(cart);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product trying to add not found");
	}

	@DeleteMapping("/")
	public ResponseEntity<?> removeFromCart(@RequestHeader("user-id") long userId, @RequestBody CartItem cartItem) {
		Cart cart = cartService.deleteFromCart(userId, cartItem);
		if (cart != null)
			return ResponseEntity.ok(cart);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item not found in cart");
	}

	@DeleteMapping("/clear")
	public ResponseEntity<String> clearCart(@RequestHeader("user-id") long userId) {
		cartService.clearShoppingCart(userId);
		return ResponseEntity.ok("Cart cleared successfully");
	}

	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestHeader("user-id") long userId) {
		String res = cartService.checkout(userId);
		return ResponseEntity.ok().body(res);
	}
}