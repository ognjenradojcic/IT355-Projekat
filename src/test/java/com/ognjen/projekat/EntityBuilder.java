package com.ognjen.projekat;


import com.ognjen.projekat.model.*;
import com.ognjen.projekat.model.enums.Role;
import com.ognjen.projekat.repository.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class EntityBuilder {

    //User
    public static final Integer USER_ID = 1;
    public static final String USERNAME = "ogi123";
    public static final String PASSWORD = "ogi123";
    public static final String FIRST_NAME = "Ognjen";
    public static final String LAST_NAME = "Radojcic";
    public static final String PHONE = "065123123";
    public static final Role ROLE = Role.USER;
    public static final List<InvoiceEntity> INVOICE_ENTITIES = new ArrayList<>();
    public static final List<Invoice> INVOICES = new ArrayList<>();

    //Product
    public static final Integer PRODUCT_ID = 1;
    public static final String PRODUCT_NAME = "S23";
    public static final String MANUFACTURER = "SAMSUNG";
    public static final double PRICE = 1500;
    public static final CategoryEntity CATEGORY_ENTITY = categoryEntity();
    public static final Category CATEGORY = category();

    //Category
    public static final Integer CATEGORY_ID = 1;
    public static final String CATEGORY_NAME = "Phones";
    public static final List<ProductEntity> PRODUCT_ENTITIES = new ArrayList<>();
    public static final List<Product> PRODUCTS = new ArrayList<>();

    //Invoice
    public static final Integer INVOICE_ID = 1;
    public static final String ADDRESS = "Bulevar";
    public static final LocalDate ORDERED_AT = LocalDate.now();
    public static final UserEntity USER_ENTITY = userEntity();
    public static final User USER = user();
    public static final List<InvoiceItemEntity> INVOICE_ITEM_ENTITIES = new ArrayList<>();
    public static final List<InvoiceItem> INVOICE_ITEMS = new ArrayList<>();

    //InvoiceItem
    public static final Integer INVOICE_ITEM_ID = 1;
    public static final ProductEntity PRODUCT_ENTITY = productEntity();
    public static final Product PRODUCT = product();
    public static final Integer QUANTITY = 1;

    //Token
    public static final String ACCESS_TOKEN = "Access token test";
    public static final String REFRESH_TOKEN = "Refresh token test";

    public static UserEntity userEntity() {
        return UserEntity.builder()
                .id(USER_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .phone(PHONE)
                .role(ROLE)
                .invoices(INVOICE_ENTITIES)
                .build();
    }


    public static User user() {
        return User.builder()
                .id(USER_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .phone(PHONE)
                .role(ROLE)
                .invoices(INVOICES)
                .build();
    }

    public static CategoryEntity categoryEntity() {
        return CategoryEntity.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .products(PRODUCT_ENTITIES)
                .build();
    }

    public static Category category() {
        return Category.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .products(PRODUCTS)
                .build();
    }

    public static ProductEntity productEntity() {
        return ProductEntity.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .manufacturer(MANUFACTURER)
                .price(PRICE)
                .category(CATEGORY_ENTITY)
                .build();
    }

    public static Product product() {
        return Product.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .manufacturer(MANUFACTURER)
                .price(PRICE)
                .category(CATEGORY)
                .build();
    }

    public static InvoiceEntity invoiceEntity() {
        return InvoiceEntity.builder()
                .id(INVOICE_ID)
                .address(ADDRESS)
                .orderedAt(ORDERED_AT)
                .user(USER_ENTITY)
                .items(INVOICE_ITEM_ENTITIES)
                .build();
    }

    public static Invoice invoice() {
        return Invoice.builder()
                .id(INVOICE_ID)
                .address(ADDRESS)
                .orderedAt(ORDERED_AT)
                .user(USER)
                .items(INVOICE_ITEMS)
                .build();
    }

    public static InvoiceItemEntity invoiceItemEntity() {
        return InvoiceItemEntity.builder()
                .id(INVOICE_ITEM_ID)
                .product(PRODUCT_ENTITY)
                .quantity(QUANTITY)
                .build();
    }

    public static InvoiceItem invoiceItem() {
        return InvoiceItem.builder()
                .id(INVOICE_ITEM_ID)
                .product(PRODUCT)
                .quantity(QUANTITY)
                .build();
    }

    public static Tokens tokens() {
        return new Tokens(ACCESS_TOKEN, REFRESH_TOKEN);
    }

    private EntityBuilder() {
    }
}
