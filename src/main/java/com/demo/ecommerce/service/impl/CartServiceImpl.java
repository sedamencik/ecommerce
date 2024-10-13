package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.CartDTO;
import com.demo.ecommerce.dto.CartItemDTO;
import com.demo.ecommerce.entity.CartEntity;
import com.demo.ecommerce.entity.CartItemEntity;
import com.demo.ecommerce.mapper.CartItemMapper;
import com.demo.ecommerce.mapper.CartMapper;
import com.demo.ecommerce.repository.CartItemRepository;
import com.demo.ecommerce.repository.CartRepository;
import com.demo.ecommerce.repository.ProductRepository;
import com.demo.ecommerce.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;


    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartMapper cartMapper, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartDTO getCart(Long customerId) {
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        AtomicReference<CartDTO> cartDTO = new AtomicReference<>(new CartDTO(new ArrayList<>()));


        cartRepository.findByCustomerId(customerId).ifPresent(cart -> {

            //Hibernate.initialize(cart.getItems());

            cart.getItems().forEach(cartItem -> {

                CartItemDTO cartItemDTO = cartItemMapper.toDTO(cartItem);

                /*CartItemDTO cartItemDTO = new CartItemDTO(
                        cartItem.getProduct(),
                        cartItem.getQuantity()
                );*/
                //cartItemDTO.setProduct(cartItem.getProduct());
                //cartItemDTO.setQuantity(cartItem.getQuantity());
                cartItemDTOs.add(cartItemDTO);
            });

            cartDTO.set(cartMapper.toDTO(cart));

            /*cartDTO.setItems(cartItemDTOs);
            cartDTO.setTotalAmount(cart.getAmount());*/
        });
        return cartDTO.get();
    }

    @Override
    public void emptyCart(Long customerId) {
        cartRepository.findByCustomerId(customerId).ifPresent(cart -> {
            cart.getItems().clear(); // Koleksiyonu temizle
            cart.setAmount(0.0);
            cartRepository.save(cart);
/*
            cartItemRepository.findByCartId(cart.getId()).ifPresent(
                    cartItems -> {
                        cartItems.forEach(cartItem -> {
                            cartItemRepository.delete(cartItem);
                        });
                    }
            );*/
        });
    }

    @Override
    @Transactional
    public void addProductToCart(Long customerId, Long productId, Integer quantity) {

        CartDTO cartDTO = new CartDTO(new ArrayList<>());
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        CartItemDTO cartItemDTO = new CartItemDTO(null, null);

        cartRepository.findByCustomerId(customerId).ifPresent(cart -> {
            cartItemRepository.findByCartIdAndProductEntityId(cart.getId(), productId).ifPresentOrElse(
                    cartItem -> {
                        // Mevcut ürün varsa miktarı güncelle
                        cartItem.setQuantity(cartItem.getQuantity() + quantity);
                        cartItemRepository.save(cartItem);

                        // Toplam tutarı yeni miktar ve fiyatla güncelle
                        cart.setAmount(cart.getAmount() + (cartItem.getProductEntity().getPrice() * quantity));
                        cartRepository.save(cart);
                    },
                    () -> {
                        // Mevcut ürün yoksa, yeni bir CartItem oluştur
                        CartItemEntity newCartItemEntity = new CartItemEntity();
                        newCartItemEntity.setCart(cart.getId());
                        newCartItemEntity.setProductEntity(productRepository.findById(productId).orElseThrow(() ->
                                new RuntimeException("Ürün bulunamadı.")));
                        newCartItemEntity.setQuantity(quantity);


                        //cart.getItems().add(newCartItem);
                        List<CartItemEntity> cartItemEntities = cart.getItems();
                        cartItemEntities.add(newCartItemEntity);
                        cart.setItems(cartItemEntities);

                        // Toplam tutarı yeni miktar ve fiyatla güncelle
                        cart.setAmount(cart.getAmount() + newCartItemEntity.getProductEntity().getPrice() * newCartItemEntity.getQuantity());

                        // Yeni CartItemi veritabanına kaydet
                        cartItemRepository.save(newCartItemEntity);
                        cartRepository.save(cart);
                    }
            );

            /*cart.getItems().forEach(cartItem -> {
                cartItemDTO.setProduct(cartItem.getProduct());
                cartItemDTO.setQuantity(cartItem.getQuantity());
                cartItemDTOs.add(cartItemDTO);
            });

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
            */

        });
    }

    @Override
    public CartDTO removeProductFromCart(Long customerId, Long productId) {
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        CartItemDTO cartItemDTO = new CartItemDTO(null, null);

        Optional<CartEntity> cart = cartRepository.findByCustomerId(customerId);
        if (cart != null) {
            Optional<CartItemEntity> cartItem = cartItemRepository.findByCartIdAndProductEntityId(cart.get().getId(), productId);
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
            cartItemDTO.setProductEntity(item.getProductEntity());
            cartItemDTO.setQuantity(item.getQuantity());
            cartItemDTOs.add(cartItemDTO);
        });
        return new CartDTO(cartItemDTOs);
    }
}
