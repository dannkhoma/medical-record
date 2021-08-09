package com.nkhomad.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, U> {

    Long save(U u);

   T update(Long id, U u);
}
