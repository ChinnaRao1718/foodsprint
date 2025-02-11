package com.foodsprint.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
Map<Integer,CartItem> cart = new HashMap<Integer,CartItem>();
	
	
	public void addCartItem(CartItem ci) {
		
		int itemId = ci.getId();
		if(cart.containsKey(itemId)) {
			CartItem existingItem = cart.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+ci.getQuantity());	
		}
		else {
			cart.put(itemId, ci);
		}
		
	}
	
	
	
	public void updateCartItem(int itemId, int quantity) {
		if(cart.containsKey(itemId)) {
			if(quantity <= 0) {
				cart.remove(itemId);
			}
			else {
				cart.get(itemId).setQuantity(quantity);
			}
		}
		
		
	}
	
	
	
	public void removeCart(int itemId) {
		cart.remove(itemId);
	}
	
	public Map<Integer,CartItem> getCart() {
		return cart;
	}
	
	

}
