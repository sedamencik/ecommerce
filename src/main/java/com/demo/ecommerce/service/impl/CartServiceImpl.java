package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.CartDTO;
import com.demo.ecommerce.dto.CartItemDTO;
import com.demo.ecommerce.entity.Cart;
import com.demo.ecommerce.entity.CartItem;
import com.demo.ecommerce.repository.CartItemRepository;
import com.demo.ecommerce.repository.CartRepository;
import com.demo.ecommerce.repository.ProductRepository;
import com.demo.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartDTO getCart(Long customerId) {
        CartDTO cartDTO = new CartDTO(null);
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        CartItemDTO cartItemDTO = new CartItemDTO(null, null);
        cartRepository.findByCustomerId(customerId).ifPresent(cart -> {

            cart.getItems().forEach(cartItem -> {
                cartItemDTO.setProduct(cartItem.getProduct());
                cartItemDTO.setQuantity(cartItem.getQuantity());
                cartItemDTOs.add(cartItemDTO);
            });

            cartDTO.setItems(cartItemDTOs);
            cartDTO.setTotalAmount(cart.getAmount());
        });
        return cartDTO;
    }

    @Override
    public void emptyCart(Long customerId) {
        cartRepository.findByCustomerId(customerId).ifPresent(cart -> {
            cart.setItems(new ArrayList<>());
            cart.setAmount(0.0);
            cartRepository.save(cart);
        });
    }

    @Override
    public CartDTO addProductToCart(Long customerId, Long productId, Integer quantity) {

        CartDTO cartDTO = new CartDTO(new ArrayList<>());
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        CartItemDTO cartItemDTO = new CartItemDTO(null, null);

        cartRepository.findByCustomerId(customerId).ifPresent(cart -> {
            cartItemRepository.findByCartIdAndProductId(cart.getId(), productId).ifPresentOrElse(
                    cartItem -> {
                        // Mevcut ürün varsa miktarı güncelle
                        cartItem.setQuantity(cartItem.getQuantity() + quantity);
                        cartItemRepository.save(cartItem);

                        // Toplam tutarı yeni miktar ve fiyatla güncelle
                        cart.setAmount(cart.getAmount() + (cartItem.getProduct().getPrice() * quantity));
                        cartRepository.save(cart);
                    },
                    () -> {
                        // Mevcut ürün yoksa, yeni bir CartItem oluştur
                        CartItem newCartItem = new CartItem();
                        newCartItem.setCart(cart.getId());
                        newCartItem.setProduct(productRepository.findById(productId).orElseThrow(() ->
                                new RuntimeException("Ürün bulunamadı.")));
                        newCartItem.setQuantity(quantity);


                        //cart.getItems().add(newCartItem);
                        List<CartItem> cartItems = cart.getItems();
                        cartItems.add(newCartItem);
                        cart.setItems(cartItems);

                        // Toplam tutarı yeni miktar ve fiyatla güncelle
                        cart.setAmount(cart.getAmount() + newCartItem.getProduct().getPrice() * newCartItem.getQuantity());

                        // Yeni CartItemi veritabanına kaydet
                        cartItemRepository.save(newCartItem);
                        cartRepository.save(cart);
                    }
            );

            /*cart.getItems().forEach(cartItem -> {
                cartItemDTO.setProduct(cartItem.getProduct());
                cartItemDTO.setQuantity(cartItem.getQuantity());
                cartItemDTOs.add(cartItemDTO);
            });*/

            cartItemRepository.findByCartId(cart.getId()).ifPresent(
                    cartItems -> {
                        cartItems.forEach(cartItem -> {
                            cartItemDTO.setProduct(cartItem.getProduct());
                            cartItemDTO.setQuantity(cartItem.getQuantity());
                            cartItemDTOs.add(cartItemDTO);
                        });
                    }
            );

            cartDTO.setItems(cartItemDTOs);
            cartDTO.setTotalAmount(cart.getAmount());

        });
        return cartDTO;

    }

    @Override
    public CartDTO removeProductFromCart(Long customerId, Long productId) {
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        CartItemDTO cartItemDTO = new CartItemDTO(null, null);

        Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
        if (cart != null) {
            Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndProductId(cart.get().getId(), productId);
            if (cartItem != null) {
                if(cartItem.get().getQuantity() > 1){
                    cartItem.get().setQuantity(cartItem.get().getQuantity() - 1);
                    cartItemRepository.save(cartItem.get());}
                else{
                    cartItemRepository.delete(cartItem.get());
                }
            }else {
                try {
                    throw new Exception("Product not found in cart");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        cart.get().getItems().forEach(item -> {
            cartItemDTO.setProduct(item.getProduct());
            cartItemDTO.setQuantity(item.getQuantity());
            cartItemDTOs.add(cartItemDTO);
        });
        return new CartDTO(cartItemDTOs);
    }
}
