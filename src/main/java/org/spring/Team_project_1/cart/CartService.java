package org.spring.Team_project_1.cart;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.cart.cartServiceImpl.CartServiceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService implements CartServiceImpl {

  private final CartRepository cartRepository;






  }
