-- Insert 25 products for each category using the category UUIDs
DO $$
DECLARE
    category_uuid uuid;
BEGIN
    -- Beverages
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Beverages';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Beverage ' || n || CASE
            WHEN n % 5 = 1 THEN ' Coffee'
            WHEN n % 5 = 2 THEN ' Tea'
            WHEN n % 5 = 3 THEN ' Juice'
            WHEN n % 5 = 4 THEN ' Soda'
            ELSE ' Water'
        END,
        'A refreshing ' || CASE
            WHEN n % 5 = 1 THEN 'coffee'
            WHEN n % 5 = 2 THEN 'tea'
            WHEN n % 5 = 3 THEN 'juice'
            WHEN n % 5 = 4 THEN 'soda'
            ELSE 'water'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/beverage' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 50)::INTEGER || ', Fat: ' || (RANDOM() * 2)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Snacks
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Snacks';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Snack ' || n || CASE
            WHEN n % 5 = 1 THEN ' Chips'
            WHEN n % 5 = 2 THEN ' Nuts'
            WHEN n % 5 = 3 THEN ' Popcorn'
            WHEN n % 5 = 4 THEN ' Pretzels'
            ELSE ' Crackers'
        END,
        'A tasty ' || CASE
            WHEN n % 5 = 1 THEN 'chip'
            WHEN n % 5 = 2 THEN 'nut mix'
            WHEN n % 5 = 3 THEN 'popcorn'
            WHEN n % 5 = 4 THEN 'pretzel'
            ELSE 'cracker'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/snack' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 150)::INTEGER || ', Fat: ' || (RANDOM() * 10)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Desserts
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Desserts';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Dessert ' || n || CASE
            WHEN n % 5 = 1 THEN ' Cake'
            WHEN n % 5 = 2 THEN ' Ice Cream'
            WHEN n % 5 = 3 THEN ' Cookie'
            WHEN n % 5 = 4 THEN ' Pie'
            ELSE ' Pudding'
        END,
        'A delicious ' || CASE
            WHEN n % 5 = 1 THEN 'cake'
            WHEN n % 5 = 2 THEN 'ice cream'
            WHEN n % 5 = 3 THEN 'cookie'
            WHEN n % 5 = 4 THEN 'pie'
            ELSE 'pudding'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/dessert' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 200)::INTEGER || ', Fat: ' || (RANDOM() * 15)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Fruits
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Fruits';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Fruit ' || n || CASE
            WHEN n % 5 = 1 THEN ' Apple'
            WHEN n % 5 = 2 THEN ' Banana'
            WHEN n % 5 = 3 THEN ' Orange'
            WHEN n % 5 = 4 THEN ' Mango'
            ELSE ' Grape'
        END,
        'A fresh ' || CASE
            WHEN n % 5 = 1 THEN 'apple'
            WHEN n % 5 = 2 THEN 'banana'
            WHEN n % 5 = 3 THEN 'orange'
            WHEN n % 5 = 4 THEN 'mango'
            ELSE 'grape'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/fruit' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 60)::INTEGER || ', Fat: ' || (RANDOM() * 1)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Vegetables
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Vegetables';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Vegetable ' || n || CASE
            WHEN n % 5 = 1 THEN ' Carrot'
            WHEN n % 5 = 2 THEN ' Broccoli'
            WHEN n % 5 = 3 THEN ' Spinach'
            WHEN n % 5 = 4 THEN ' Pepper'
            ELSE ' Tomato'
        END,
        'A fresh ' || CASE
            WHEN n % 5 = 1 THEN 'carrot'
            WHEN n % 5 = 2 THEN 'broccoli'
            WHEN n % 5 = 3 THEN 'spinach'
            WHEN n % 5 = 4 THEN 'pepper'
            ELSE 'tomato'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/vegetable' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 40)::INTEGER || ', Fat: ' || (RANDOM() * 1)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Dairy
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Dairy';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Dairy ' || n || CASE
            WHEN n % 5 = 1 THEN ' Milk'
            WHEN n % 5 = 2 THEN ' Cheese'
            WHEN n % 5 = 3 THEN ' Yogurt'
            WHEN n % 5 = 4 THEN ' Butter'
            ELSE ' Cream'
        END,
        'A fresh ' || CASE
            WHEN n % 5 = 1 THEN 'milk'
            WHEN n % 5 = 2 THEN 'cheese'
            WHEN n % 5 = 3 THEN 'yogurt'
            WHEN n % 5 = 4 THEN 'butter'
            ELSE 'cream'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/dairy' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 100)::INTEGER || ', Fat: ' || (RANDOM() * 8)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Bakery
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Bakery';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Bakery ' || n || CASE
            WHEN n % 5 = 1 THEN ' Bread'
            WHEN n % 5 = 2 THEN ' Croissant'
            WHEN n % 5 = 3 THEN ' Muffin'
            WHEN n % 5 = 4 THEN ' Bagel'
            ELSE ' Pastry'
        END,
        'A freshly baked ' || CASE
            WHEN n % 5 = 1 THEN 'bread'
            WHEN n % 5 = 2 THEN 'croissant'
            WHEN n % 5 = 3 THEN 'muffin'
            WHEN n % 5 = 4 THEN 'bagel'
            ELSE 'pastry'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/bakery' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 150)::INTEGER || ', Fat: ' || (RANDOM() * 5)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Meat
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Meat';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Meat ' || n || CASE
            WHEN n % 5 = 1 THEN ' Chicken'
            WHEN n % 5 = 2 THEN ' Beef'
            WHEN n % 5 = 3 THEN ' Pork'
            WHEN n % 5 = 4 THEN ' Lamb'
            ELSE ' Turkey'
        END,
        'A fresh cut of ' || CASE
            WHEN n % 5 = 1 THEN 'chicken'
            WHEN n % 5 = 2 THEN 'beef'
            WHEN n % 5 = 3 THEN 'pork'
            WHEN n % 5 = 4 THEN 'lamb'
            ELSE 'turkey'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/meat' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 200)::INTEGER || ', Fat: ' || (RANDOM() * 15)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Seafood
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Seafood';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Seafood ' || n || CASE
            WHEN n % 5 = 1 THEN ' Salmon'
            WHEN n % 5 = 2 THEN ' Shrimp'
            WHEN n % 5 = 3 THEN ' Tuna'
            WHEN n % 5 = 4 THEN ' Cod'
            ELSE ' Crab'
        END,
        'A fresh ' || CASE
            WHEN n % 5 = 1 THEN 'salmon'
            WHEN n % 5 = 2 THEN 'shrimp'
            WHEN n % 5 = 3 THEN 'tuna'
            WHEN n % 5 = 4 THEN 'cod'
            ELSE 'crab'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/seafood' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 150)::INTEGER || ', Fat: ' || (RANDOM() * 10)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;

    -- Grains
    SELECT id INTO category_uuid FROM public.categories WHERE name = 'Grains';
    INSERT INTO public.products (name, description, price, image, category, nutrition)
    SELECT
        'Grain ' || n || CASE
            WHEN n % 5 = 1 THEN ' Rice'
            WHEN n % 5 = 2 THEN ' Pasta'
            WHEN n % 5 = 3 THEN ' Quinoa'
            WHEN n % 5 = 4 THEN ' Oats'
            ELSE ' Barley'
        END,
        'A pack of ' || CASE
            WHEN n % 5 = 1 THEN 'rice'
            WHEN n % 5 = 2 THEN 'pasta'
            WHEN n % 5 = 3 THEN 'quinoa'
            WHEN n % 5 = 4 THEN 'oats'
            ELSE 'barley'
        END,
        ROUND((RANDOM() * 19 + 1)::NUMERIC, 2),
        'https://example.com/images/grain' || n || '.jpg',
        category_uuid,
        'Calories: ' || (RANDOM() * 120)::INTEGER || ', Fat: ' || (RANDOM() * 3)::INTEGER || 'g'
    FROM generate_series(1, 25) AS n;
END $$;