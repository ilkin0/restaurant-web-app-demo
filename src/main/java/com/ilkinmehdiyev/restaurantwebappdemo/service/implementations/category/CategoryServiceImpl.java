package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.category;

import com.ilkinmehdiyev.restaurantwebappdemo.models.Category.Category;
import com.ilkinmehdiyev.restaurantwebappdemo.repo.category.CategoryRepo;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
