package com.ilkinmehdiyev.restaurantwebappdemo.controller.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ilkinmehdiyev.restaurantwebappdemo.util.Constant.MENU_URL;

@RestController("MenuController")
@RequestMapping(MENU_URL)
@RequiredArgsConstructor
public class MenuController {
}
